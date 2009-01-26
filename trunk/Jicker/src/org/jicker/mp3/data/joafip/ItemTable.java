package org.jicker.mp3.data.joafip;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;

import net.sf.joafip.java.util.PLinkedList;
import net.sf.joafip.java.util.PTreeMap;
import net.sf.joafip.java.util.PTreeSet;

public class ItemTable {

	/** map of {@link Item} by item code */
	private final Map<String, Item> itemByCodeMap =
	/**/new PTreeMap<String, Item>();

	/** map of {@link Item} by item price, can have multiple item for same price */
	private final NavigableMap<Float, Set<Item>> itemByPrice =
	/**/new PTreeMap<Float, Set<Item>>();

	public Item addItem(final Item item) {
		final Item previousItem = itemByCodeMap.put(item.getCode(), item);
		if (previousItem != item) {// NOPMD must compare object instance
			removeOfPriceMap(previousItem);
			addToPriceMap(item);
		}
		return previousItem;
	}

	public Item getItemByCode(final String itemCode) {
		return itemByCodeMap.get(itemCode);
	}

	public Collection<Item> getAllItem() {
		return itemByCodeMap.values();
	}

	public List<Item> getItemByPrice(final float fromPrice, final float toPrice) {
		final NavigableMap<Float, Set<Item>> subMap = itemByPrice
				.subMap(fromPrice, true/* fromInclusive */, toPrice, true/* toInclusive */);
		final List<Item> list = new PLinkedList<Item>();
		for (Set<Item> set : subMap.values()) {
			list.addAll(set);
		}
		return list;
	}

	public Item removeItem(final String itemCode) {
		final Item removed = itemByCodeMap.remove(itemCode);
		removeOfPriceMap(removed);
		return removed;
	}

	private void addToPriceMap(final Item toAdd) {
		final float price = toAdd.getPrice();
		Set<Item> set = itemByPrice.get(price);
		if (set == null) {
			set = new PTreeSet<Item>();
			itemByPrice.put(price, set);
		}
		set.add(toAdd);
	}

	private void removeOfPriceMap(final Item toRemove) {
		if (toRemove != null) {
			final float price = toRemove.getPrice();
			final Set<Item> set = itemByPrice.get(price);
			if (set != null) {
				set.remove(toRemove);
			}
		}
	}
}
