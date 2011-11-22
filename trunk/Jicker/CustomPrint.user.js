// ==UserScript==
// @name           GC Custom Print
// @namespace      http://www.amshove.net
// @version        0.0
// @include        http://www.geocaching.com/*
// @require http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js
// @description    A Custom Print solution for Geocaching.com.
// @copyright      Stephan Borchert <stepborc@googlemail.com>
// ==/UserScript==
//
// Author:         Stephan Borchert <stepborc@googlemail.com>
// Version:        0.0             - 11.10.2011
// Changelog:      0.0             - Initial 
//
//Set a link for Custom Print in the Printarea
var lnk = " | <a id='cst_print_link'>Custom Print</a>";
document.getElementById('ctl00_ContentBody_lnkPrintDirectionsSimple').parentNode.innerHTML += lnk;
document.getElementById('cst_print_link').addEventListener("click", cst_print_show, false);

//Get GC-Code
//var gccode = document.getElementById('ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode').value;

function cst_print_show(){
//	var css = "* { font-family:Courier New,Courier; align=left;} h3 { color:black; font-size:24px; margin-bottom:2px}";
//	var newPage = "<html><head>" + "<style type=\"text/css\">" + css + "</style>" + " </head><body>";
//	newPage += "GCCode: " + gccode;
//	newPage += "</body></html>";
	
	popup=window.open('','','width=200,height=100');
//	popup.document.open();
//	popup.document.write(newPage);
    popup.document.write("<p>Test</p>");
	popup.focus();	
}