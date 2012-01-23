// ==UserScript==
// @name           GC Custom Print
// @namespace      http://jicker.de
// @version        0.1
// @include        http://www.geocaching.com/*
// @description    A Custom Print solution for Geocaching.com.
// @copyright      Stephan Borchert <sborcweb@googlemail.com>
// ==/UserScript==
//
// Author:         Stephan Borchert <stepborc@googlemail.com>
// Version:        0.0             - 11.10.2011
// Changelog:      0.0             - Initial
//                 0.1             - 2012-01-23 code clean for uploading
//Set a link for Custom Print in the Printarea
var lnk = " | <a id='cst_print_link'>Custom Print</a>";
document.getElementById('ctl00_ContentBody_lnkPrintDirectionsSimple').parentNode.innerHTML += lnk;
document.getElementById('cst_print_link').addEventListener("click", cst_print_show, false);

function cst_print_show(){
  parseListing();
  //declare listing style
  var css = "* { font-family:Courier New,Courier; align=left;} h3 { color:black; font-size:24px; margin-bottom:2px} headline { font-size:24px; }";
    //Start of listing
  var newPage = "<html><head>" + "<style type=\"text/css\">" + css + "</style>" + " </head><body>";
    //concatenate all atributes to listing
  newPage += "<h3>" + gccode + ": " + gcname + "</h3>";
    newPage += "<b>Owner:</b> " + gcowner + " | <b>seit:</b> " + gchidden + " | Koord: " + gckoord + "<br>";
    newPage += "<b>Size:</b> " + gcsize + " | <b>Difficulty:</b> " + gcdifficult + " | <b>Terrain:</b> " + gcterrain + "<br>";
    newPage += "<p>" + shortDesc + "</p>";
    newPage += "<p>" + longDesc + "</p>";
    newPage += "<p>" + gcerror + "</p>";
    //End of Listing
    newPage += "</body></html>";
    popup=window.open("about:blank", "CustomPrint" );
    popup.document.write( newPage );
    popup.focus();
}
function parseListing(){
  //try{
  //Get GC-Code
  var gccode = document.getElementById('ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode').innerHTML;
  //Get Cache name
  var gcname = document.getElementById('ctl00_ContentBody_CacheName').textContent;
  //get cache details
  var gccachedetails = document.getElementById('cacheDetails').innerText;
  //Get gcowner
  var gcownerStart = gccachedetails.search(/A cache by/i);
  var gcownerEnd = gccachedetails.search(/HIDDEN/i);
  var gcowner = gccachedetails.substr((gcownerStart + 11),(gcownerEnd-gcownerStart-11))
  //Get gchidden
  var gchidden = gccachedetails.substr((gcownerEnd + 9),(gccachedetails.length)) ;
  //Get gckoord
  var gckoord = document.getElementById('uxLatLon').innerHTML
  //Get Size
  var gcsize = document.getElementsByTagName("img")[11].alt;
  //var gcsize = document.getElementsByTagName("img")[11];
  gcsize = gcsize.replace(/Size: /, "");
  //Get gcdifficult
  var gcdifficult = document.getElementById('ctl00_ContentBody_uxLegendScale').innerHTML;
  //Get gcterrain
  var gcterrain = document.getElementById('ctl00_ContentBody_Localize12').innerHTML;
  var imgName = "";
  //Get short cache description
  var shortDesc = document.getElementById('ctl00_ContentBody_ShortDescription').innerHTML;
  //Get long cache description
  var longDesc = document.getElementById('ctl00_ContentBody_LongDescription').innerHTML;
  //}catch(e){
    var gcerror = e;
  //}
}