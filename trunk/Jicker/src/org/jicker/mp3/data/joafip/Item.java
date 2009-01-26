package org.jicker.mp3.data.joafip;

/**
 * item entity<br>
 * comparable compare item code ( the main key )
 */
public class Item implements Comparable<Item> {

	/** item code is immutable : it is the main key */
	private final String code;

	/** the item price */
	private float price;

	public Item(final String code) {
		super();
		this.code = code;
	}

	public Item(final String code, final float price) {
		super();
		this.code = code;
		this.price = price;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(final float price) {
		this.price = price;
	}

	public String getCode() {
		return code;
	}

	@Override
	public int compareTo(final Item otherItem) {
		// /!\ JOAFIP persistable object rule
		// do not use code.compareTo(otherItem.code)
		// see http://joafip.sourceforge.net/presentation/ppojo.html#directfield
		return code.compareTo(otherItem.getCode());
	}

	@Override
	public String toString() {
		return code + ";" + price + ";";
	}
}
