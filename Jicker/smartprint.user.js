// ==UserScript==
// @name           SmartPrint
// @namespace      http://rflexor.cachezentrale.de
// @include        http://www.geocaching.com/seek/*
// ==/UserScript==

// TODO: TBs

// Beschafft das erste auf den ï¿½bergebenen XPath passende Element
function getXEle(path) {
	var result = document.evaluate(path, document, null,
			XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue;
	return result;
}
// Beschafft eine Liste der auf den ï¿½bergebenen XPath passenden Elemente
function getXEles(path) {
	var result = document.evaluate(path, document, null,
			XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null);
	return result;
}

// Datenausgabe in neuem Fenster
function smartPrint() {
	//css = "* { font-size:10px; font-family:Arial,Helvetica; align=left;} h1 { color:red; font-size:48px; }";
	css = "* { font-family:Arial,Helvetica; align=left;} h1 { color:red; font-size:48px; }";
	var propTable = "<table border=1 cellspacing=0 nowrap>"
			+ "<tr><th>Koordinaten</th><th>Cachegroesse</th><th>Difficulty</th><th>Terrain</th><th>Owner</th><th>Gelegt</th></tr>"
			+ "<tr><td>" + KOs + "</td><td>" + size
			+ "</td><td>" + diff + "</td><td>" + terr + "</td><td>"+owner+"</td><td>" + dateHidden + "</td>"
			+ "<tr></table>";
	var newPage = "<html><head>" + "<style type=\"text/css\">" + css
			+ "</style>" + " </head><body><!--NOKOUTIL-->"
			+ "<h3>" + gccode + ": " + name + "</h3>" + propTable;
	// + "<br>";

	// if (lf!="") {
	// newPage+=lf+"<br><br>";
	// }
	newPage += shortDesc + "<br>";
	newPage += longDesc + "<br>";
	if (hints != "") {
		newPage += "<b>HINT:</b> " + hints + "<br>";
	}
	if (attrib != "") {
		newPage += "<b>Attribute</b>: " + attrib + "<br>";
	}
	if (waypoints != "") {
		newPage += "<b>" + waypoints + "</b><br>";
	}
	if (images != "") {
		newPage += images + "<br>";
	}
	if (log != "") {
		newPage += log + "<br>";
	}
	newPage += "</body></html>";

	popup = window.open("about:blank", "smartPrint");
	popup.document.open();
	popup.document.write(newPage);
	popup.document.close();
}

// Dekodiert den Hint
// Schwierigkeit bereiten HTML-Tag und Sonderzeichen in der Form '&xxx;' und '<BR>'
function rot_13(text) {
	var keycode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	var rot13 = new String()

	for ( var i = 0; i < text.length; i++) {
		var codechar = text.substring(i, i + 1)
		var pos = keycode.indexOf(codechar.toUpperCase())

		if (pos >= 0) {
			pos = (pos + keycode.length / 2) % keycode.length
			codechar = (codechar == codechar.toUpperCase()) ? keycode
					.substring(pos, pos + 1) : keycode.substring(pos, pos + 1)
					.toLowerCase()
			rot13 = rot13 + codechar;
		} else {
			switch (codechar) {
			case "<":
				if (text.substring(i, i + 4).toUpperCase() == "<BR>") {
					rot13 = rot13 + "<BR>";
					i = i + 3;
				}else if (text.substring(i, i + 3).toUpperCase() == "<P>"){
					rot13 = rot13 + "<P>";
					i = i + 2;
				} else {
					rot13 = rot13 + codechar;
				}
				break;
			case "&":
				// Wenn ein &-Zeichen im Text auftaucht, das nächste Semikolon
				// suchen
				rot13 = rot13 + codechar;
				var laenge = 1;
				while (text.substring(i + laenge, i + 1 + laenge) != ";") {
					codechar = text.substring(i + laenge, i + 1 + laenge);
					rot13 = rot13 + codechar;
					laenge++;
				}
				i = i + laenge - 1;
				break;
			default:
				rot13 = rot13 + codechar;
				break;
			}
		}
	}

	return rot13;
}
// Smartprint-Aktion registrieren
unsafeWindow.doSmartPrint = function() {
	window.setTimeout(smartPrint);
};

// Daten aus dem Cachelisting abgreifen

// Icon
imgType = getXEle("//a[@title='About Cache Types']/img/@alt").textContent
		.substring(0, 5);
// Name
name = document.getElementById("ctl00_ContentBody_CacheName").textContent;
// GC-Code
gccode = document.getElementById("ctl00_cacheCodeWidget").textContent;
gccode = gccode.trim();
// Owner
owner = getXEle("//span[@id=\"ctl00_ContentBody_CacheOwner\"]/a").textContent;
// Versteckdatum
dateHidden = document.getElementById("ctl00_ContentBody_DateHidden").textContent;
// Grï¿½ï¿½e
size = getXEle("//img[contains(@alt,\"Size:\")]/following-sibling::small").textContent;
// Diff
diff = getXEle("//span[@id=\"ctl00_ContentBody_Difficulty\"]/img")
		.getAttribute("alt");
diff = diff.substring(0, diff.indexOf(" "));
// Terrain
terr = getXEle("//span[@id=\"ctl00_ContentBody_Terrain\"]/img").getAttribute(
		"alt");
terr = terr.substring(0, terr.indexOf(" "));
// Koordinaten
KOs = document.getElementById("ctl00_ContentBody_LatLon").childNodes[0].textContent;
// Beschreibungstext
shortDesc = document.getElementById("ctl00_ContentBody_ShortDescription");
shortDesc = (null == shortDesc ? "" : shortDesc.innerHTML);
shortDesc = shortDesc.replace(/align="center"/gi, "align=\"left\"")
// Langbeschreibung
longDesc = document.getElementById("ctl00_ContentBody_LongDescription").innerHTML;
longDesc = longDesc.replace(/align="center"/gi, "align=\"left\"");
longDesc = longDesc.replace(/<center>/gi, "<left>");
longDesc = longDesc.replace(/<\/center>/gi, "<left>");
longDesc = longDesc.replace(/width="100%"/gi, "");
// Hints dekodieren
hints = document.getElementById("ctl00_ContentBody_Hints")
hints = (null == hints ? "" : hints.innerHTML);
hintstate = document.getElementById("ctl00_ContentBody_Encrypt");
if (hintstate.textContent == "Decrypt") {
	hints = rot_13(hints);
}
// Waypoints aufbereiten
wps = getXEle("//strong[contains(text(),'Additional Waypoints')]/following-sibling::*/following::table[1]/tbody");
alert(wps);
waypoints = "";

if (null != wps && wps.childNodes.length > 0) {
	alert('In der If');
	wpidx = 1;
	for (i = 1; i < wps.childNodes.length - 4; i += 4) {
		waypoints += "WP" + wpidx + ": "
				+ wps.childNodes[i].childNodes[7].textContent + "&nbsp;"
				+ wps.childNodes[i].childNodes[9].textContent + "&nbsp;"
				+ wps.childNodes[i].childNodes[11].textContent + "&nbsp;"
				+ wps.childNodes[i + 2].childNodes[3].textContent + "<br>";
		wpidx++;
	}
}

// Bilder sammeln
imgspan = document.getElementById("ctl00_ContentBody_Images");
images = "";
if (null != imgspan) {
	newImg = document.createElement("span");
	for (i = 0; i < imgspan.childNodes.length; i++) {
		if (imgspan.childNodes[i].tagName == "A") {
			newlink = document.createElement("img");
			newlink.setAttribute("src", imgspan.childNodes[i]
					.getAttribute("href"));
			newlink.setAttribute("border", "0");

			newp = document.createElement("p");
			newp.appendChild(newlink);
			// Text aus dem Image lesen

			newImg
					.appendChild(document
							.createTextNode(imgspan.childNodes[i].childNodes[2].textContent));
			newImg.appendChild(document.createElement("br"));
			// Dann Image selbst anzeigen

			newImg.appendChild(newp);
		} else {
			newImg.appendChild(imgspan.childNodes[i].cloneNode(true));
		}
		newImg.appendChild(document.createElement("br"));
	}
	images = newImg.innerHTML;

}
// Log-ï¿½bersicht
log = "";
logs = getXEles("//table[@class='LogsTable Table']/tbody/tr/td");
if (null != logs) {
	logTmp = document.createElement("span");
	for ( var i = 1; i < logs.snapshotLength - 1; i++) {
		var fndEntry = logs.snapshotItem(i);
		logTmp.appendChild(fndEntry.cloneNode(true));
		logTmp.appendChild(document.createElement("br"));
	}
	log = logTmp.innerHTML;
}

lf = "";
lfs = getXEles("//table[@class='LogsTable Table']//strong/img");
if (null != lfs) {
	for ( var i = 0; i < lfs.snapshotLength; i++) {
		fndimg = lfs.snapshotItem(i);
		var icon = fndimg.getAttribute("src");
		var myType = "???";
		if (icon.indexOf("icon_smile") > 0) {
			myType = ":-)";
		} else if (icon.indexOf("icon_sad") > 0) {
			myType = ":-(";
		}
		lf = lf + myType + " ";
		lf = lf + fndimg.nextSibling.textContent;
		lf = lf + fndimg.nextSibling.nextSibling.textContent + " | ";
	}
}

// Attributes
attrib = "";

attribs = getXEles("//div[@class='CacheDetailNavigationWidget Spacing']/img");
if (null != attribs) {
	for ( var i = 1; i < attribs.snapshotLength; i++) {
		if (attribs.snapshotItem(i).getAttribute("alt") != "blank") {
			attrib = attrib + (attrib == "" ? "" : ", ")
					+ attribs.snapshotItem(i).getAttribute("alt");
		}
	}
}

// Smartprint-Knopf einblenden, wenn alles geklappt hat.

var printIcon = document.getElementById("ctl00_ContentBody_lnkPrintFriendly");
var printIconParent = printIcon.parentNode;

var eleSP = document.createElement("a");
eleSP.innerHTML = "<span style=\"font-size:10px;color:red;text-decoration:underline;cursor:pointer;\">SmartPrint</span>&nbsp;";
eleSP.setAttribute("onClick", "javascript:doSmartPrint();");

printIconParent.insertBefore(eleSP, printIcon);
