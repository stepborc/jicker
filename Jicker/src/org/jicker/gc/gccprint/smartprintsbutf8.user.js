﻿// ==UserScript==
// @name           SmartPrintSB
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
	// stepborc: Anpassung an eigene CSS Vorstellungen
	// css = "* { font-size:10px; font-family:Arial,Helvetica; align=left;} h1 {
	// color:red; font-size:48px; }";
	css = "* { font-family:Courier New,Courier; align=left;} h3 { color:black; font-size:24px; margin-bottom:2px}";
	var propertyTable = "<table border=1 cellspacing=0 nowrap>"
			+ "<tr><th>Koordinaten</th><th>Cachegroesse</th><th>Difficulty</th><th>Terrain</th><th>Owner</th><th>Gelegt</th><th>Cachetype</th></tr>"
			+ "<tr><td>" + KOs + "</td><td>" + size + "</td><td>" + diff
			+ "</td><td>" + terrain + "</td><td>" + owner + "</td><td>"
			+ dateHidden + "</td><td>" + cacheType + "</td></tr></table>";
	var newPage = "<html><head>" + "<style type=\"text/css\">" + css
	+ "</style>" + " </head><body><!--NOKOUTIL--><h3>"  + gccode + ": "+ gcname +  "</h3>" + propertyTable + "<br>" + allnodes;
//	newPage = newPage + longDesc;
//	/*	var newPage = "<html><head>" + "<style type=\"text/css\">" + css
//			+ "</style>" + " </head><body><!--NOKOUTIL-->" + "<h3>" + gccode
//			+ ": " + name + "</h3>" + propTable;
//	newPage += shortDesc + "<br>";
//	newPage += longDesc + "<br>";
//	if (hints != "") {
//		newPage += "<b>HINT:</b> " + hints + "<br>";
//	}
//	if (attrib != "") {
//		newPage += "<b>Attribute</b>: " + attrib + "<br>";
//	}
//	if (waypoints != "") {
//		newPage += "<b>Waypoints" + waypoints + "</b><br>";
//	}
//	if (images != "") {
//		newPage += images + "<br>";
//	}
//	// stepborc: Logs ausblenden
//	// if (log != "") {
//	// newPage += log + "<br>";
//	// }
//	*/
	newPage += "</body></html>";
	// stepborc:
	popup = window.open("about:blank", "smartPrint");
	popup.document.open();
	popup.document.write(newPage);
	popup.document.close();
	
}

// Dekodiert den Hint
// Schwierigkeit bereiten HTML-Tag und Sonderzeichen in der Form '&xxx;' und
// '<BR>'
function rot_13(text) {
	var keycode = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
	var rot13 = new String()

	for ( var i = 0; i < text.length; i++) {
		var codechar = text.substring(i, i + 1)
		var pos = keycode.indexOf(codechar.toUpperCase())
		// alert('Forschleifenindex: ' + i + ' pos:' + pos + ' Zeichen:' +
		// codechar);
		if (pos >= 0) {
			pos = (pos + keycode.length / 2) % keycode.length
			codechar = (codechar == codechar.toUpperCase()) ? keycode
					.substring(pos, pos + 1) : keycode.substring(pos, pos + 1)
					.toLowerCase()
			rot13 = rot13 + codechar;
		} else {
			switch (codechar) {
			case "<":
				if (text.substring(i, i + 4).trim().toUpperCase() == "<BR>"
						|| text.substring(i, i + 4).trim().toUpperCase() == "<BR/>") {
					rot13 = rot13 + "<BR>";
					i = i + 3;
				} else if (text.substring(i, i + 3).toUpperCase() == "<P>") {
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
			// Wenn "eckige" Klammer, den Text bis zur
			// schliessenden Klammer nicht decodieren
			case "[":
				while (text.substring(i, i + 1) != "]") {
					rot13 = rot13 + text.substring(i, i + 1);
					i++;
				}
				rot13 = rot13 + "]";
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
/*
// Icon
imgType = getXEle("//a[@title='About Cache Types']/img/@alt").textContent
		.substring(0, 5);
*/
// Name
//var gcname = document.getElementById("ctl00_ContentBody_CacheName").textContent;
var gcname = "GCname";
// GC-Code
//var gccode = document.getElementById("ctl00_uxWaypointName").textContent;
//gccode = gccode.trim();
var gccode = "GCcode";

// Cachedetails
// get element with most cache attributes
//
var all = document.getElementById("yui-g");
// owner
//var owner = all.childNodes[1].childNodes[1].childNodes[0].childNodes[1].childNodes[3].childNodes[1].childNodes[0].childNodes[1].childNodes[3].innerHTML;
var owner = "Owner";
// dateHidden
//var dateHidden = all.childNodes[1].childNodes[1].childNodes[0].childNodes[1].childNodes[3].childNodes[1].childNodes[0].childNodes[3].childNodes[2].textContent;
var dateHidden = "DateHidden";
//size
    //var size = all.childNodes[1].childNodes[1].childNodes[0].childNodes[1].childNodes[3].childNodes[1].childNodes[2].childNodes[1].childNodes[5].textContent;
    //size = size.substring(1, size.length - 1);
    var size = "Size";
//diff
//    var diff = document.images[2].alt;
//    if (diff.length == 12){
//    	diff = diff.substring(0,3);
//    } else {
//    	diff = diff.substring(0,1)
//    }
var diff = "Difficulty";
//terrain
//    var terrain = document.images[3].alt;
//    if (terrain == 12){
//    	terrain = terrain.substring(0,3);
//    }else{
//    	terrain = terrain.substring(0,1);
//    }
var terrain = "Terrain";
//cacheType
//    var cacheType = document.images[0].alt;
//    cacheType = cacheType.substring(0, (cacheType.length - 6));
var cacheType = "CacheType"
    //
//var allnodes = all.childNodes[1].childNodes[1].childNodes[0].childNodes[1].childNodes[3].childNodes[1].childNodes[2].innerHTML;
var allnode = "AllNodes";

//var allnodes = all.parentNode.innerHTML;
//var allnodes = all.firstChild.nodeName;
/*
// Owner
// auskommentiert am 03.06.2010
// owner =
// getXEle("//span[@id=\"ctl00_ContentBody_CacheOwner\"]/a").textContent;

// Versteckdatum
dateHidden = document.getElementById("ctl00_ContentBody_DateHidden").textContent;

// Grï¿½ï¿½e
//size = getXEle("//img[contains(@alt,\"Size:\")]/following-sibling::small").textContent;
//size = getXEle("//img[contains(@alt,\"Size:\")]/").textContent;

// Diff
diff = getXEle("//span[@id=\"ctl00_ContentBody_Difficulty\"]/img")
		.getAttribute("alt");
diff = diff.substring(0, diff.indexOf(" "));
// Terrain
terr = getXEle("//span[@id=\"ctl00_ContentBody_Terrain\"]/img").getAttribute(
		"alt");
terr = terr.substring(0, terr.indexOf(" "));
*/
// Koordinaten
//KOs = document.getElementById("ctl00_ContentBody_LatLon").childNodes[0].textContent;
//KOs = document.getElementById("ctl00_ContentBody_LatLon").textContent;
//KOs = "N" + KOs.substring(2,KOs.length);
//KOs = KOs.substring(0,11) + " E" + KOs.substring(14,KOs.length);
var KOs = "Kordinaten";
/*
// Beschreibungstext
shortDesc = document.getElementById("ctl00_ContentBody_ShortDescription");
shortDesc = (null == shortDesc ? "" : shortDesc.innerHTML);
shortDesc = shortDesc.replace(/align="center"/gi, "align=\"left\"")
*/
// Langbeschreibung
//longDesc = document.getElementById("ctl00_ContentBody_LongDescription").innerHTML;
//longDesc = longDesc.replace(/align="center"/gi, "align=\"left\"");
//longDesc = longDesc.replace(/<center>/gi, "<left>");
//longDesc = longDesc.replace(/<\/center>/gi, "<left>");
//longDesc = longDesc.replace(/width="100%"/gi, "");
var longDesc = "Lange Beschreibung";
/*
// Hints dekodieren
// hints = document.getElementById("ctl00_ContentBody_Hints")
// hints = (null == hints ? "" : hints.innerHTML);
hintstate = document.getElementById("ctl00_ContentBody_lnkDH");
if (hintstate.textContent == "Decrypt" || hintstate.textContent == "Encrypt") {
	hints = document.getElementById("div_hint");
	// Hier wird das Element mit innerHTML überegeb, damit die HTML-Tags
	// erhalten bleiben
	// z.B. <BR>
	hints = rot_13(hints.innerHTML);
} else {
	hints = "Keine verdeckten Hinweise vorhanden."
}
// hints = document.getElementById("div_hint");
// alert(dht(hints));
*/
// Waypoints aufbereiten
var waypoints = document.getElementById("ct100_ContentBody_Waypoints");
/*
wps = getXEle("//table[@class='Table']")
waypoints = "";
wpanzahl = 0;
wplaenge = 0;
wp_colspan = 1;
wp_colspan_measure = false;
if (wps != null && wps.childNodes.length > 0) {
	wpanzahl = wps.childNodes[3].childNodes.length;
	waypoints = "<table border=1 cellspacing = 0 nowrap>"
	for (i = 1; i < wpanzahl; i = i + 2) {
		wplaenge = wps.childNodes[3].childNodes[i].childNodes.length;
		if (wplaenge == 7) {
			wp_line = "<tr><td>--></td>";
			// alert(wps.childNodes[3].childNodes[i].childNodes[3].innerHTML);
			wp_line = wp_line + "<td colspan = " + wp_colspan + ">"
					+ wps.childNodes[3].childNodes[i].childNodes[3].innerHTML
					+ "&nbsp" + "</td>";
			wp_line = wp_line + "</tr>";
		} else if (wplaenge == 15) {
			wp_line = "<tr>"
			for (j = 1; j < wplaenge; j++) {
				if (wps.childNodes[3].childNodes[i].childNodes[j].childNodes.length > 0) {
					if (wp_colspan_measure == false) {
						wp_colspan++;
					}
					if (wps.childNodes[3].childNodes[i].childNodes[j].childNodes[0].attributes != null
							&& wps.childNodes[3].childNodes[i].childNodes[j].childNodes[0].attributes.length == 4) {
						wp_line = wp_line
								+ "<td>"
								+ wps.childNodes[3].childNodes[i].childNodes[j].childNodes[0].attributes[2].nodeValue
								+ "&nbsp</td>";
					} else {
						// alert(wps.childNodes[3].childNodes[i].childNodes[j].innerHTML);
						wp_line = wp_line
								+ "<td>"
								+ wps.childNodes[3].childNodes[i].childNodes[j].textContent
								+ "&nbsp</td>";
					}
				}
			}
			wp_line = wp_line + "</tr>";
			wp_colspan_measure = true;
		}
		waypoints = waypoints + wp_line;
	}
	waypoints = waypoints + "</table>";
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
*/
// Smartprint-Knopf einblenden, wenn alles geklappt hat.

var printIcon = document.getElementById("ctl00_ContentBody_lnkPrintFriendly");
//var printIcon = "PrintIcon";
//var printIconParent = printIcon.parentNode;
//var printIconParent = "printIconParent";

var eleSP = document.createElement("a");
eleSP.innerHTML = "<span style=\"font-size:10px;color:red;text-decoration:underline;cursor:pointer;\">SmartPrint</span>&nbsp;";
eleSP.setAttribute("onClick", "javascript:doSmartPrint();");

//printIconParent.insertBefore(eleSP, printIcon);
