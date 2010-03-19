// Pretty Print Script for cachedescriptions on geocaching.com
// version 0.1 BETA!
// 2008-08-20
// Copyright (c) 2008, PHerison
// Released under the GPL license
// http://www.gnu.org/copyleft/gpl.html
//
// --------------------------------------------------------------------
//
// This is a Greasemonkey user script.  To install it, you need
// Greasemonkey 0.3 or later: http://greasemonkey.mozdev.org/
// Then restart Firefox and revisit this script.
// Under Tools, there will be a new menu item to "Install User Script".
// Accept the default configuration and install.
//
// To uninstall, go to Tools/Manage User Scripts,
// select "PrettyPrint for GC.com", and click Uninstall.
//
// --------------------------------------------------------------------
//
// ==UserScript==
// @name          PrettyPrint for GC.com
// @description   Removes certain things from the cachedescription page
// @include       http://www.geocaching.com/seek/cache_details.aspx?*
// ==/UserScript==

var toggleElements = function(event)
{
var displayState = event.target.getAttribute('displaystate');
if (displayState == 'none')
  displayState = '';
else
  displayState = 'none';
event.target.setAttribute('displaystate', displayState);

for (var i=0; i<elementsToHide.length; i++)
{
  elementsToHide[i].style.display = displayState;
}

// de-/encrypt Hint
var tmp = document.getElementById('Hints');

var s = "ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz";

for (var i=0, l=tmp.childNodes.length; i<l; i++)
{
  if (tmp.childNodes[i].nodeType == 3)
  {
   var ret = "";
   var hints = tmp.childNodes[i].data;
   for (var j=0; j < hints.length ; j++)
   {
    var c = hints.substr(j,1);
    if (s.indexOf(c) > -1)
     ret += s.charAt(s.indexOf(c)+13);
    else
     ret += c;
   }
   tmp.childNodes[i].data = ret;
  }
}

event.preventDefault();
return false;
};

// replicate Attributes as text ...
var atts = "";
results = document.evaluate("//table[2]/tbody/tr/td/img", document, null, XPathResult.ANY_TYPE, null);
while (aResult = results.iterateNext())
{
if (aResult.getAttribute('title') != "blank" && aResult.getAttribute('title') != "no coordinates" && aResult.getAttribute('title') != null && aResult.getAttribute('title') != "available")
{
  if (atts != "") atts += " | "
   atts += aResult.getAttribute('title') + " ";
}
}

/// ...and insert them before coordinates section
var newAttributes = document.createElement('span');
var tmp = document.createElement('img');
tmp.setAttribute('src','../images/stockholm/16x16/info.gif');
tmp.setAttribute('hieght','16');
tmp.setAttribute('width','16');
tmp.setAttribute('align','absmiddle');
newAttributes.appendChild(tmp);
var tmp = document.createElement('b');
tmp.appendChild(document.createTextNode(" Attributes: "));
newAttributes.appendChild(tmp);
newAttributes.appendChild(document.createTextNode(atts));
newAttributes.appendChild(document.createElement('br'));
newAttributes.appendChild(document.createElement('br'));
// find coordinates section
results = document.evaluate("//table/tbody/tr[1]/td[1]/table", document, null, XPathResult.ANY_TYPE, null);
var ziel = results.iterateNext();
ziel.parentNode.insertBefore(newAttributes, ziel);


var elementsToHide = new Array();

// find Printlink and Downloadlinks
var results = document.evaluate("//tbody[count(tr)=4]", document, null, XPathResult.ANY_TYPE, null);
var aResult = results.iterateNext();
elementsToHide.push(aResult.firstChild.nextSibling.nextSibling);
elementsToHide.push(aResult.firstChild.nextSibling.nextSibling.nextSibling.nextSibling);
elementsToHide.push(aResult.firstChild.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling.nextSibling);

// find Printlink and Downloadlinks
results = document.evaluate("//div[@class='yui-b' and position()=2]", document, null, XPathResult.ANY_TYPE, null);
elementsToHide.push(results.iterateNext());

// all on the right side
results = document.evaluate("//form[@id='Form1']/table/tbody/tr[1]/td[2]", document, null, XPathResult.ANY_TYPE, null);
while (aResult = results.iterateNext())
elementsToHide.push(aResult);

// all between hint and spoiler
//results = document.evaluate("//form[@id='Form1']/table/tbody/tr[1]/td[1]/*[position() >29 and position()<37]", document, null, XPathResult.ANY_TYPE, null);
results = document.evaluate("//form[@id='Form1']/table/tbody/tr[1]/td[1]/*[position() >31]", document, null, XPathResult.ANY_TYPE, null);
while (aResult = results.iterateNext())
elementsToHide.push(aResult);

// decryption table
elementsToHide.push(document.getElementById('EncryptionKey'));


// Create a link to control toggling
var icon = document.createElement('img');
icon.setAttribute('height','16');
icon.setAttribute('width','16');
icon.setAttribute('border','0');
icon.setAttribute('align','absmiddle');
icon.setAttribute('alt','printer');
icon.setAttribute('src','../images/silk/printer.png')

var span = document.createElement('span');
span.appendChild(document.createTextNode('Toggle Display'));

var link = document.createElement('a');
link.setAttribute('href', '#');
link.setAttribute('displaystate', 'none');
link.setAttribute('class', 'lnk');
link.setAttribute('style', 'font-family: Verdana; font-size: xx-small;');
//span.addEventListener('mouseover', curPointer, true);
//span.addEventListener('mouseout', curDefault, true);
link.addEventListener('click', toggleElements, true);
link.appendChild(icon);
link.appendChild(document.createTextNode(' '));
link.appendChild(span);

var printerlink = document.getElementById('CacheName');
printerlink.parentNode.appendChild(link);