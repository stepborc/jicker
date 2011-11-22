// ==UserScript==
// @name           GC little helper
// @namespace      http://www.amshove.net
// @version        6.8
// @include        http://www.geocaching.com/*
// @include        http://maps.google.de/*
// @include        http://maps.google.com/*
// @include        http://www.google.de/maps*
// @include        http://www.google.com/maps*
// @include        https://maps.google.de/*
// @include        https://maps.google.com/*
// @include        https://www.google.de/maps*
// @include        https://www.google.com/maps*
// @exclude        http://www.geocaching.com/seek/sendtogps.aspx*
// @resource jscolor http://www.amshove.net/greasemonkey/js/jscolor/jscolor.js
// @require http://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js
// @description    Some little things to make life easy (on www.geocaching.com).
// @copyright      Torsten Amshove <torsten@amshove.net>
// ==/UserScript==
//
// Author:         Torsten Amshove <torsten@amshove.net> & Michael Keppler <bananeweizen@gmx.de> & Lars-Olof Krause <mail@lok-soft.de>
// Version:        6.8             - 08.10.2011
// Changelog:      6.8             - Fix: Bug #99 - [gc.com update] Mail-Link does not transfer GC-ID 
//                                 - Fix: Bug #98 - [gc.com update] "Show area in google maps"-link disappeared
//                                 - Fix: Bug #97 - Dynamic Map doesn't work anymore 
//                 6.7             - Fix: Bug #96 - [gc.com update] Hide Avatar function of gc.com does not work (Added an advice to youse GClh option) 
//                                 - Fix: Bug #95 - [gc.com update] Logs are shown twice
//                 6.6             - Fix: Bug #92 - Owner disappeared in short VIP-List
//                                 - New: Issue #22 - Icon für "log inline" 
//                                 - Fix: Bug #91 - Inline-Log doesn't work - the links disappeared 
//                                 - Fix: Bug #89 - Prevent newlines if there is no cache-titel in mail
//                                 - Fix: Bug #88 - gclh config: Homezone radius labelled km instead of miles 
//                                 - New: Issue #90 - Search also in Usernames and not only in LogText 
//                                 - Small Fix for Chrome/Opera?! (See http://www.geoclub.de/viewtopic.php?f=117&t=58855)
//                 6.5             - New: Issue #86 - Search in LogText 
//                                 - Fix: Bug #68 - [gc.com update] Log-Filter doesn't work
//                                 - New: Issue #82 - Show thumbnails in logs side by side
//                                 - New: Issue #81 - Show Log-Text on mouse over in VIP-List
//                                 - New: Issue #14 - Show one entry per log at VIP-List 
//                                 - New: Issue #80 - Show "loading"-Image in VIP-List 
//                                 - New: Issue #78 - VIP-Icon at public profile 
//                                 - New: Issue #58 - Add a "Show routing information"-Link to Listing
//                                 - Small Fix: enable Link on google maps also for https
//                                 - New: Issue #73 - option to disable automatic reset for difference-counter at friendlist 
//                                 - New: Issue #54 - Divide coin & tb sum at public profile
//                                 - New: Issue #57 - Bigger images at gallery 
//                                 - Small Bugfix: Image-Hover in Gallery doesn't work
//                                 - New: Issue #55 - Change title-color of archived caches red 
//                                 - Small Bugfix: An error at thumbnail-function was not caught
//                 6.4             - Small Bugfix of v6.3 - Script breaks, if there is no Gallery in Listing
//                 6.3             - New: Issue #77 - Hide Avatars
//                                 - New: Issue #76 - Add a "Load all logs"-Link
//                                 - Fix: Bug #59 - [gc.com update] load all logs no longer working 
//                                 - Fix: Bug #72 - html in cachename on mail icon at disabled caches 
//                                 - Fix: Bug #74 - [gc.com update] Difference-counter at friendlist doesn't work
//                                 - Fix: Bug #71 - [gc.com update] VIP-Icons are not displayed in logs
//                                 - Fix: Bug #63 - [gc.com update] VIP-List doesn't work
//                                 - Fix: Bug #65 - [gc.com update] Mail-Icon and top-link are not displayed in logs
//                 6.2             - Fix: Bug #66 - [gc.com update] Thumbnails in Logs doesn't work
//                                 - Fix: Bug #67 - [gc.com update] Mouseover on images doesn't work
//                                 - Disabled: Log-Filter (Bug #68)
//                                 - Fix: Bug #64 & #60 - [gc.com update] "Hide spoiler warning" doesn't work  - [gc.com update] View Logbook link not visible 
//                 6.1             - New: Issue #30 - Show bigger Image on mouseover in gallery 
//                                 - Change: Issue #53 - Increase number of log-templates 
//                                 - Change: Issue #52 - Don't show thumbnail of spoilers 
//                                 - Change: Issue #42 - Count TBs and Coins separately
//                                 - New: Issue #48 - Filter for Logs
//                                 - New: Reset difference-counter at friendlist automatically if day changes
//                                 - Change: Issue #6 - Reset difference-counter at friendlist with a button
//                                 - Fix: Bug #45 - [gc.com update] Difference-counter at friendlist doesn't work if there are more than 1000 finds 
//                 6.0             - Fix: Bug #43 - JS-Links doesn't work in linklist on profile page
//                                 - Fix: Bug #41 - Trackable name is not read correctly from Mail-Icon
//                                 - Fix: Bug #34 - [gc.com update] VIP-Log-Icons disappeared 
//                 5.9             - Fix: Bug #32 - [gc.com update] Hide social buttons in linklist 
//                                 - Fix: Bug #33 - [gc.com update] Redundant Mail an VIP-Icons at logs 
//                 5.8             - New: Issue #9 - Thumbnails of images in listing an logs
//                                 - New: Issue #5 - Highlight "Related Website"
//                                 - New: Issue #13 - Show gallery-link at own caches in profile
//                                 - Fix: Bug #25 - AutoVisit - TB is visited
//                                 - Fix: Bug #29 - Mail Icon in Trackable Logs is missing 
//                                 - Fix: Bug #28 - Coin Series Info is sometimes missing
//                 5.7             - New: Issue #1 - Highlight myself in VIP-List
//                                 - Fix: VIP-Icon-Status at bookmark-tables
//                                 - Fix: Bug #26 - Owner not correctly determined in VIP-List
//                                 - New: ColorPicker for Homezone
//                 5.6             - Fix: eMail-Link on disabled / archived caches
//                                 - New: Loglenght counter (max 4000)
//                                 - New: Homezone color editable via menu
//                 5.5             - New: Bookmark it-Icon at nearest list
//                                 - Fix: if one VIP-Icon changes, all others change too
//                                 - Fix: VIP-Icon beside owner in list no shows the correct color
//                                 - Change: disable AutoVisit on logedit-page
//                                 - Fix: AutoVisit select by value, enable for Webcam caches
//                                 - New: TB-ID inserted in mail
//                                 - New: [URL]-Tag for bbcode
//                                 - New: Show version in configuration
//                 5.4             - New: Show Map-It button at Listing
//                                 - New: VIP-Icon at friendlist
//                                 - New: "All my VIPs"-List at profile-page
//                                 - Change: improved "show area on google maps"-link at listing
//                                 - Fix: Autovisit state wasn't saved
//                                 - Fix: Many things were broken by "to top"-feature -> fixed
//                 5.3             - New: "Top"-Link at Logs
//                                 - New: Show owner at VIP-List
//                                 - New: Show one icon per log at the VIP-List
//                                 - Change: My Statistics-Bookmark updated to other URL
//                                 - Fix: Default-Log-Type was selected on yes/no-question at NM-Logs
//                                 - New: Autovisit now selects "visited" only if you select LogType "found" or "attended"
//                                 - Fix: Autovisit now doesn't distrub "All visited"
//                                 - change: enable matrix statistics also on profile page
//                                 - change: use GC logo for link on google maps
//                                 - fix: google maps link can't be used directly after searching
//                 5.2             - New: VIP-List
//                 5.1             - New: new update advice
//                                 - New: show percentage of favourite points in listing
//                                 - Fix: redirect to map on search by keyword
//                                 - New: AutoVisit for TBs/Coins
//                 5.0             - Fix: hint-decryption
//                                 - Fix: show Coin-Series
//                                 - Fix: show BBCode while editing logs of trackables
//                                 - Fix: exclude script on "send to gps" page to prevent destroying the design
//                 4.9             - change: insert a dot where the line breaks are removed
//                                 - Fix: exception when setting focus
//                                 - New: strikeout title of archived/disabled caches
//                                 - New: beta map: hide found/hidden caches by default
//                                 - Fix: adapt to changes of 2011-06-28 (feedback button)
//                                 - New: show "n/81" in cache matrix (statistics page)
//                                 - Fix: don't automatically decrypt unencrypted hints
//                 4.8             - Fix: a bug in "remove advertise" function
//                 4.7             - Fix: workaround to not make &amp; of & in templates
//                                 - Fix: illegal character in signature/template for leading newlines (configuration has to be saved again to fix it!)
//                                 - New: hide hint behind a link
//                                 - New: remove spoiler warning
//                                 - New: remove link to advertisement instructions
//                                 - New: remove unneeded line breaks
//                                 - Fix: scroll to top when opening config dialog
//                                 - Some improvements to autoupdate
//                 4.6             - Fix: Click on grey background to close configuration
//                                 - Fix: newlines as first character of signatures and templates
//                                 - New Variable: #found_no# = founds (without +1)
//                                 - Added #found# Variable to Templates an TB-Signature
//                                 - Set cursor to first character in Log-fields
//                                 - Allow GCVote in inline logs
//                                 - Fix: do not redirect to map from links of friend list
//                                 - Append GCcode to Cache-Name on mail links
//                                 - Hide Config-Link in beta map, because it doesn't work fine
//                                 - BugFix: First box in sidebar was hidden
//                                 - Changed some default-settings
//                 4.5             - Show other Coordinate-Formats in Listing and on print-page
//                                 - Show amount of different Coins found in public profile
//                                 - Now the HomeCoords are also parsed from http://www.geocaching.com/account/default.aspx
//                                 - Fix: Insert template doesn't replace text now
//                                 - Fix: Redirect to Map
//                                 - Fix: Inline-Log does work on Pages without Gallery now
//                 4.4             - Added Log-Templates
//                                 - Fix: Default Log-Type should not override Field-Note-Log-Type
//                                 - Added some Code to support Google Chrome (not tested and not supported yet) - thanks to Bart
//                                 - Added Signal-Smilies
//                                 - Decrypt Hint on print-page
//                                 - Fix: Now it also works if there is a ?pf= in Listing-Link (Decrypted Log)
//                                 - Show TB-List in inline-Logs
//                                 - Removed: Insert Home-Coords in Searchfield - gc.com fixed it
//                                 - Save Home-Coords in Configuration
//                                 - Default-Value for Searchfield in Navigation
//                                 - Added Config-Link to Profile-Page
//                                 - Hide Linklist-Settings in Configuration (let it show with a link)
//                                 - New Design for Config and Find Player
//                 4.3             - Transfer TB-Tracking Number to Log-Field
//                                 - Repair gc.com-Bug: Linebreaks in decrypted hints
//                                 - Change background of found-caches to green (instead of grey)
//                                 - Log your visit (inline) Link in top menu
//                                 - styl-changes to search field (thanks to shen)
//                                 - Custom width for global page
//                                 - Hide Disclaimer on print-page
//                                 - Show Coin-Series-Name in TB-Listing
//                                 - Fix: Set Focus to textbox after clicking a Smiley or BBcode
//                                 - Fix: Log-Signature and Field Notes
//                 4.2             - Added: Smilies & BBCode on Log-Page
//                                 - Fix: overwrite of Log-Type on edit-Log
//                                 - Improve searchfield in linklist: Search direct by GC-ID, TB-ID or Tracking-Number
//                                 - Highlighted "founds since last load" on Friendlist
//                                 - Fix: Save target option of custom Bookmarks
//                 4.1             - Friendlist: Show amount of caches found since last load of the Friendlist
//                                 - Fix: Default-Log-Type doesn't override &LogType=
//                                 - Post log from Listing (inline)
//                                 - Fix: Hide Navigation on SignIn overlay
//                                 - Fix: Remove gc.com Links if logged out
//                                 - Fix: On auto-decrypt hint the decrypt link now changes to "encrypt"
//                                 - Choose wich Links are shown in Beta Map
//                                 - Fix: Show Log-Signature also on log.aspx?wp=xxx
//                                 - Removed leading line breaks from Log Signature
//                 4.0             - Update links to use beta-Map (disable via "default old map" in settings)
//                                 - Fix: Signature removes Log on edit
//                 3.9             - Edit button to own caches on profile page
//                                 - Set old map as Default
//                                 - Log-Signature-Variable: #found# (will be replaced with finds+1)
//                                 - Log & TB Signature
//                                 - Fix: get Cachetitle for Mail form log-page
//                                 - Searchfield in Linklist
//                                 - Linklist in beta map
//                 3.8             - Enable dynamic map
//                                 - Show Linklist as flat Navigation
//                                 - Remove links from Navigation
//                 3.7             - Insert Home-Coords into search-field
//                                 - Custom Map-width
//                                 - Fix: Homezone on Beta Map
//                                 - New Links: Profile Souvenirs & Statistics
//                 3.6             - Fix: Bookmark-List on Top -> now in Navigation
//                                 - Fix: Feedback-Link
//                                 - Rename: Bookmarks -> Linklist
//                                 - Fix: Bookmarks in Profile
//                                 - Fix: Google-Maps & KML-Link in Bookmark-Lists
//                                 - Fix: Hide Cache Notes
//                                 - Fix: Hide Disclaimer
//                                 - Removed: Hide L&F-Banner
//                 3.5             - BugFix: Saving Home-Coords (thanks to Birnbaum2001)
//                                 - Fill Homezone on Map (and remove "clickable")
//                 3.4             - Show Homezone on Map
//                 3.3             - Show Mail-Icon on log-Page
//                                 - Bugfix: Some JS not working on page "Your Profile"
//                 3.2             - Added "Log It" Icon to Nearest List
//                 3.1             - Bugfix: Mail-Icon was not displayed on pages with URL?id=..
//                 3.0             - Added www.google.de/maps
//                 2.9             - gc.com-update-fix: Link on found-counter at friendlist
//                 2.8             - Bugfix: Style problems in "Your Account Details"-Page
//                 2.7             - Bugfix: Problem with "Hide Cache Notes if empty"-Option for not-PM
//                 2.6             - Added feature to hide Cache Notes if there are no notes (Hide/Show Link appears)
//                                 - Added feature to hide Cache Notes completely
//                                 - Added feature to hide the L&F-Banner on top of the Logs (Listing)
//                                 - Fix: Added F2 to Submit-Feature also to Trackable-Page
//                 2.5             - Added feature to prevent "show all logs" if there are to many logs
//                                 - Added an signature for mails
//                                 - Fix: Shanged Feedback-Bookmark to new URL
//                                 - Fix: new Feedback-button can be hidden again
//                 2.4             - Bugfix: Disabled redirect also with bookmark "Nearest List (w/o Founds)"
//                 2.3             - Disabled redirect to map, if link "Neares List" is used
//                                 - Bugfix: Wrong cachename in mail-text if there are more than one open tab
//                 2.2             - Added feature to hide the boxes on the "My Profile"-Sidebar
//                 2.1             - Added feature to add custom bookmarks
//                                 - Added "Show in google maps"-Link to Bookmark-Overview-Page
//                 2.0             - Added links to bookmark-lists: "Download as kml" and "Show in google maps"
//                                 - Bugfix: Wrong coordinates from google maps
//                 1.9             - Added a Link to Google Maps on Cache-Page a Link on Google Maps to geocaching.com
//                                 - Replaced the Mail-Link with an Icon
//                                 - Removed: "Hide Facebook Button" - gc.com removed it
//                                 - "Show all Logs" now replaces the links, to prevent the redirect
//                                 - Bugfix: Gallery-Link on Friend-List
//                 1.8             - Add Links to Friendlist: Gallery, Hidden Caches
//                                 - Add Link to Found-List on Found-Counter at Friend-List
//                                 - Compatible to GCTidy Link (Thanks to tenrapid)
//                 1.7             - Bugfix: Mail-Link, hide disclaimer, decrypt hint (fixed the URL matching)
//                                 - Added My Trackabels-Bookmark
//                 1.6             - Bookmark hinzugefuegt: Neares-List ohne Funde
//                                 - TB-Log-Typ erweitert um "retrieved .."
//                                 - Konfigurierbar: Bookmarks links oder rechts
//                                 - Konfigurierbar: Bookmarks farbe und groesse
//                                 - Direkter E-Mail Link (+ einfuegen des Namens) in Cache-Listing und TB-Listing aufgenommen
//                                 - Merkt sich den Status der Checkboxen vom Mail-Formular
//                                 - Moeglichkeit, den Disclaimer im Listing auszublenden
//                                 - Moeglichkeit, den Hint automatisch zu entschluesseln
//                 1.5             - Bugfix: Home-Koordinaten berechnung gefixt
//                                 - Kleine vorlaufiger Fix: GCTidy-Link
//                 1.4             - Bugfix: Breite der "Du bist angemeldet als .." Zeile an deutsche Uebersetzung angepasst
//                 1.3             - Bugfix: Zeichensatzproblem bei Grad-Zeichen in RegEx
//                 1.2             - Bookmarks in einer Zeile
//                                 - Weitere Bookmarks hinzugefuegt (Tabs im oeffentlichen Profil, My Profile, Nearest List, Map)
//                                 - "Configuration" umbenannt in "little helper config"
//                                 - Default-Log-Type fuer Trackables
//                                 - Bookmarks umbenennen / Kuerzel geben
//                 1.1             - Konfigurations-Menue hinzugefuegt
//                                 - Bookmarks konfigurierbar und sortierbar gemacht
//                                 - Bookmark-Links erweitert
//                                 - Find Player-Menue
//                                 - Hide Facebook-Button
//                                 - Hide Feedback-Button
//                                 - Default Log Type
//                 1.0             - Bookmark-Liste fuer Profilseite und Header
//                                 - F2-Shortcut fuer den Log-Button
//                                 - Automatische Weiterleitung zur Kartenansicht
//                                 - Alle Logs Anzeigen


////////////////////////////////////////////////////////////////////////////
  
/**
 * create a bookmark to a page in the geocaching.com name space
 * @param {String} title
 * @param {String} href
 * @returns {Object} bookmark
 */
function bookmark(title, href) {
  var bm = new Object();
  bookmarks[bookmarks.length] = bm;
  bm['href'] = href;
  bm['title'] = title;
  return bm;
}

/**
 * create a bookmark to an external site
 * @param {String} title
 * @param {String} href
 */
function externalBookmark(title, href) {
  var bm = bookmark(title, href);
  bm['rel'] = "external";
  bm['target'] = "_blank";
}

/**
 * create a bookmark to a profile sub site
 * @param {String} title
 * @param {String} id
 */
function profileBookmark(title, id) {
  var bm = bookmark(title, "#");
  bm['id'] = id;
  bm['name'] = id;
}

/**
 * check if the current document location matches the the given path
 * @param {String} path partial or full path to a geocaching.com web page
 * @returns {Boolean} true if the current page matches the path
 */
function isLocation(path) {
  path = path.toLowerCase();
  if (path.indexOf("http") != 0) {
    if (path.charAt(0) != '/') {
      path = "/" + path;
    }
    path = "http://www.geocaching.com" + path;
  }
  return document.location.href.toLowerCase().indexOf(path) == 0;
}

// define bookmarks
var bookmarks = new Array();

bookmark("Watchlist", "/my/watchlist.aspx");
bookmark("Geocaches", "/my/geocaches.aspx");
bookmark("My Geocaches", "/my/owned.aspx");
bookmark("Trackable Items", "/my/travelbugs.aspx");
bookmark("Trackables Inventory", "/my/inventory.aspx");
bookmark("Trackables Collection", "/my/collection.aspx");
bookmark("Benchmarks", "/my/benchmarks.aspx");
bookmark("Member Features", "/my/subscription.aspx");
bookmark("Friends", "/my/myfriends.aspx");
bookmark("Account Details", "/account/default.aspx");
bookmark("Public Profile", "/profile/");
bookmark("Search", "/seek/nearest.aspx");
bookmark("Routes", "/my/userroutes.aspx#find");
bookmark("Field Notes", "/my/uploadfieldnotes.aspx");
bookmark("Pocket Queries", "/pocket/default.aspx");
bookmark("Saved GPX", "/pocket/saved.aspx");
bookmark("Bookmarks", "/bookmarks/default.aspx");
bookmark("Notifications", "/notify/default.aspx");
profileBookmark("Find Player", "lnk_findplayer");
bookmark("E-Mail", "/email/default.aspx");
bookmark("Statbar", "/my/statbar.aspx");
bookmark("Guidelines", "/about/guidelines.aspx");
externalBookmark("Forum", "http://forums.groundspeak.com/");
externalBookmark("Blog", "http://blog.geocaching.com/");
externalBookmark("Feedback", "http://feedback.geocaching.com/");
externalBookmark("Geoclub", "http://www.geoclub.de/");
profileBookmark("Profile Geocaches", "lnk_profilegeocaches");
profileBookmark("Profile Trackables", "lnk_profiletrackables");
profileBookmark("Profile Gallery", "lnk_profilegallery");
profileBookmark("Profile Bookmarks", "lnk_profilebookmarks");
bookmark("My Profile", "/my/");
profileBookmark("Nearest List", "lnk_nearestlist");
profileBookmark("Nearest Map", "lnk_nearestmap");
profileBookmark("Nearest List (w/o Founds)", "lnk_nearestlist_wo");
profileBookmark("My Trackables", "lnk_my_trackables");

// New Bookmarks under custom_Bookmarks ..

////////////////////////////////////////////////////////////////////////////

// Set defaults
var scriptName = "gc_little_helper";
var scriptVersion = "6.8";

var anzCustom = 10;
var anzTemplates = 10;

var bookmarks_def = new Array(16,18,13,14,17,12);

// Compatibility to Google Chrome - http://devign.me/greasemonkey-gm_getvaluegm_setvalue-functions-for-google-chrome/
var browser = "firefox";
if (!this.GM_getValue || (this.GM_getValue.toString && this.GM_getValue.toString().indexOf("not supported")>-1)) {
  this.GM_getValue=function (key,def) {
    return localStorage[key] || def;
  };
  this.GM_setValue=function (key,value) {
    return localStorage[key]=value;
  };
}
if(!this.uneval){
  this.uneval = function (value) {  };
  browser = "chrome";
}


// Settings: Submit Log on F2
settings_submit_log_button = GM_getValue("settings_submit_log_button",true);
// Settings: Log Inline
settings_log_inline = GM_getValue("settings_log_inline",true);
settings_log_inline_tb = GM_getValue("settings_log_inline_tb",false);
// Settings: Show Bookmarks
settings_bookmarks_show = GM_getValue("settings_bookmarks_show",true);
// Settings: Bookmarks on Top
settings_bookmarks_on_top = GM_getValue("settings_bookmarks_on_top",true);
settings_bookmarks_top_menu = GM_getValue("settings_bookmarks_top_menu","true");
settings_bookmarks_search = GM_getValue("settings_bookmarks_search","true");
settings_bookmarks_search_default = GM_getValue("settings_bookmarks_search_default","");
// Settings: Redirect to Map
settings_redirect_to_map = GM_getValue("settings_redirect_to_map",false);
// Settings: Hide Feedback Button
settings_hide_feedback = GM_getValue("settings_hide_feedback",false);
// Settings: Hide Disclaimer
settings_hide_disclaimer = GM_getValue("settings_hide_disclaimer",true);
// Settings: Hide Cache Notes
settings_hide_cache_notes = GM_getValue("settings_hide_cache_notes",false);
// Settings: Hide Cache Notes if empty
settings_hide_empty_cache_notes = GM_getValue("settings_hide_empty_cache_notes",true);
// Settings: Show all Logs
settings_show_all_logs = GM_getValue("settings_show_all_logs",true);
settings_show_all_logs_count = GM_getValue("settings_show_all_logs_count","5");
// Settings: Decrypt Hint
settings_decrypt_hint = GM_getValue("settings_decrypt_hint",false);
// Settings: Show Smilies & BBCode
settings_show_bbcode = GM_getValue("settings_show_bbcode",true);
// Settings: Show mail-Link
settings_show_mail = GM_getValue("settings_show_mail",true);
// Settings: Show google-maps Link
settings_show_google_maps = GM_getValue("settings_show_google_maps",true);
// Settings: Show Log It Icon
settings_show_log_it = GM_getValue("settings_show_log_it",true);
// Settings: Show Homezone
settings_show_homezone = GM_getValue("settings_show_homezone",true);
settings_homezone_radius = GM_getValue("settings_homezone_radius","10");
settings_homezone_color = GM_getValue("settings_homezone_color","#0000FF");
// Settings: default Map
settings_old_map = GM_getValue("settings_old_map",false);
if (settings_old_map) {
  map_url = "http://www.geocaching.com/map/default.aspx";
} else {
  map_url = "http://www.geocaching.com/map/beta/default.aspx";
}
// Settings: default Log Type
settings_default_logtype = GM_getValue("settings_default_logtype","-1");
// Settings: default TB-Log Type
settings_default_tb_logtype = GM_getValue("settings_default_tb_logtype","-1");
// Settings: Bookmarklist
settings_bookmarks_list = eval(GM_getValue("settings_bookmarks_list",uneval(bookmarks_def)));
settings_bookmarks_list_beta = eval(GM_getValue("settings_bookmarks_list_beta",uneval(bookmarks_def)));
if(browser == "chrome"){
  settings_bookmarks_list = new Array();
  settings_bookmarks_list_beta = new Array();
}
// Settinks: Dynamic Map
settings_dynamic_map = GM_getValue("settings_dynamic_map",true);
settings_hide_advert_link = GM_getValue('settings_hide_advert_link',true);
settings_hide_line_breaks = GM_getValue('settings_hide_line_breaks',true);
settings_hide_spoilerwarning = GM_getValue('settings_hide_spoilerwarning',true);
settings_hide_hint = GM_getValue('settings_hide_hint',true);
settings_strike_archived = GM_getValue('settings_strike_archived',true);
settings_map_hide_found = GM_getValue('settings_map_hide_found', false);
settings_map_hide_hidden = GM_getValue('settings_map_hide_hidden', false);
settings_show_fav_percentage = GM_getValue('settings_show_fav_percentage', false);
settings_show_vip_list = GM_getValue('settings_show_vip_list', true);
settings_autovisit = GM_getValue("settings_autovisit","true");
settings_show_thumbnails = GM_getValue("settings_show_thumbnails",true);
settings_hide_avatar = GM_getValue("settings_hide_avatar",false);
settings_show_big_gallery = GM_getValue("settings_show_big_gallery",false);
settings_automatic_friend_reset = GM_getValue("settings_automatic_friend_reset",true);
settings_show_long_vip = GM_getValue("settings_show_long_vip",false);


// Settings: Custom Bookmarks
var num = bookmarks.length;
for(var i=0; i<anzCustom; i++){
  bookmarks[num] = Object();
  
  if(typeof(GM_getValue("settings_custom_bookmark["+i+"]")) != "undefined" && GM_getValue("settings_custom_bookmark["+i+"]") != ""){
    bookmarks[num]['href'] = GM_getValue("settings_custom_bookmark["+i+"]");
  }else{
    bookmarks[num]['href'] = "#";
  }
  
  if(typeof(GM_getValue("settings_bookmarks_title["+num+"]")) != "undefined" && GM_getValue("settings_bookmarks_title["+num+"]") != ""){
    bookmarks[num]['title'] = GM_getValue("settings_bookmarks_title["+num+"]");
  }else{
    bookmarks[num]['title'] = "Custom"+i;
    GM_setValue("settings_bookmarks_title["+num+"]",bookmarks[num]['title']);
  }
  
  if(typeof(GM_getValue("settings_custom_bookmark_target["+i+"]")) != "undefined" && GM_getValue("settings_custom_bookmark_target["+i+"]") != ""){
    bookmarks[num]['target'] = GM_getValue("settings_custom_bookmark_target["+i+"]");
    bookmarks[num]['rel'] = "external";
  }else{
    bookmarks[num]['target'] = "";
  }
  
  bookmarks[num]['custom'] = true;
  num++;
}

// Some more Bookmarks ..
profileBookmark("Profile Souvenirs", "lnk_profilesouvenirs");
bookmark("Profile Statistics", "/my/statistics.aspx");

// Settings: Custom Bookmark-title
var bookmarks_orig_title = new Array();
for(var i=0; i<bookmarks.length; i++){
  if(typeof(GM_getValue("settings_bookmarks_title["+i+"]")) != "undefined" && GM_getValue("settings_bookmarks_title["+i+"]") != ""){
    bookmarks_orig_title[i] = bookmarks[i]['title']; // Needed for configuration
    bookmarks[i]['title'] = GM_getValue("settings_bookmarks_title["+i+"]");
  }
}

var global_log_it_icon = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAIAAAAA8CAYAAACuNrLFAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sKBBIqEByBYugAAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJTVBXgQ4XAAAJ3klEQVR42u1cbWwUxxl+Zu+OO4zPzhnFkOI4DhgJI1M7UKA0+AOl6IRcIdPyJeG4Kq0QqKUuWEVGgFpVjhTR2kYoUa2CYiqVqhJORIJB2IXGGDeWPwpycWuloiRt6mJM4PDX5W7vbrc/2DXj8e7e7tlnrmFeaXR367nd8T7P+7zvvDN7ADdu3Lhx48aNGzdu3Lhxe4aMfEmu8ayY/P8ADuFkmDXQ5UQiAOFgPzVSyE+TACTK+QgnyIwBLEfpK88mAUgU7ycG4HMCWJd9mgRyFBWQ400AAgADAwMLnE5nqc1m2wrgNY5bbBaJRP4UDAYbu7q6Lm7ZsuWBDqAs8LIOGWQrJIiFAKS/v39uenr6G4Ig/ITDN7MWDAZPHj58+I2GhgaRAVeiPksM+FKsJLBKADIwMLAgKSnpYwApHK446b8sj5w+fXr1oUOHHjEgSxTYEQZ8SUMhopKAWPX8hQsX3uXgzw4JSkpK8js6OiIU2Op7SeNV0lCDqCQwSwACAA8ePKg1kn27ax7OtPSj8+N7CEck3ZOtyJqP3d7lcBEx5hvk8XgAAD6fLyEA0xvPdMbp8/neWrx48dsKqGGKABHqc5ghgyUSELPgK9J/16jj6aufIPP5ZHz71SWw2wTdfhe7PsWVm5/hlz94FWOjI5wABrZnz551586dC1JghwGElBamXmlymCaBYHYgTqez1OjvriQ3bt6+j60F2RgXCYZGZdwdkTHwSMa/fTI+eSDj9n0J/7wvoWRNFua5HPjX0OiXRrJ9Pl9cyLh3794CAG4AyUpLAjBXaXMAOAE4ANgB2JRGzKq7YMb7ARCbzfZro45jgRDS3E4IhCAQAiLS4xZWWkSSEZaAYPhx/5SkORjxi7MCTnd3N0pLS5GdnY0lS5Zg8+bN6O7untLv0qVLKCgoQEZGBoqLi9HU1ASPxzPhxdEUgO1HfzZ7HtZWrlx5HECqknepJJjHkGAORQCBaUbFONjjMq+VH4MvySoR5IljBqlB3MD3er1YtmwZTp06BZvNhqqqKni9XjQ3N2P16tUAgP7+fuzatQs5OTk4e/YsZFlGVVXVtFVhhkJVKgBRaUHF41WwiUG9QFUCOVYFMJ0oJrsceDgahCTLsAuq98tKe6IE6ljGAiEkuxxxJ0B1dTVkWUZlZSU2bNiAwsJCHDx4ELIso7q6eqLf+fPnAQCVlZUoKipCcXExDhw4kCgRJhXAc4oKuCkVSALgUsKAnWo0OYyqsYYKYKlGEPCP4pXs59F4/Ta2FmRDIE+u/ZlPxsNxGS+kEqS7BVzs+hSjfhEvLXDDPxbfPODWrVsAgLVr104cU9/39fVNIQDdb926dYlEAFGRegcV56FRI1BnA4RpMoWrbDUEmCLD3pJcnGnpx+vH/whJfnyNja+8iN3e5XjRQ/DBR434sPcC/IFhiCER77aVYtPK7QmVzBGSkEsUKQoB9MAPUy3C1AsM/yG7SeBN3ZVwYBxlhZkoK8ycOPbzP9zCoM+PUxffgey6gW8WrkVG2lJ8+LfzeP+vb+H+8CDKN/w4bnduxYoVaGtrQ2dnJzIyMgAAnZ2dAIDc3NyJfqWlpTh+/Dh6enqwaNEiAEBHR8e0ry8IAiRJgiRJEAQh1tO4ldhPg696ekghhzo1ZBNBifL6KfmA6RGFQqF9VkfddOMelr+Uhqp3PsKd/15C/rI8RIQI8l7YiAgJ4esrvoHGtoa4us6RI0dACEFdXR2uXbuG9vZ2nDhxAoQQHD16dBIBAKCmpgbt7e1obW1FXV3dtK+/atUqAEBPT09M36+vr/8VlfHPZaaBTqo5NGYCiDYlNJsEkt7e3iYrAxfhRPc/7mHpV1Jx4vtfw+cjg3CQZGzO2f842XrtN1iS/lWIoeC0CzBaTbU1a9agubkZ8+fPx+7du1FeXo60tDRcvnx5YgYAYFL2v2PHDhw7dgz79+9XayAxj6+iogL5+fnwer0xTQNra2tFhgAuKvFzUlNAug5Ak0BLxUm02E4Y9ggAbENDQ286HI6KWG5E6S9W4VubihGGhKqNDXiz5Xtw2Zx4r6kZH/zsZkIWdy5cuIDy8nLk5eWhtbV11q9/5cqV323bti1VkfYggC8AjAMYAzAMYER5faS8H1X+7gcQoMICXSaeVBW0WSCAEA6HOwoLC/cSQqy7hAC0/f0CshZk4XP/fzA8PoQ/917HpvzXkffy2oQAfPv27cjMzITb7cbVq1dRW1uLwcFB1NbWYunSpbM6FlEUR9avX98biUQ8VMyXqJJwiKoNiEweQK8PsKuEprJ7lgCqrDhOnjyZXlZW9hdCiOUVwbPX3sa7138Lf2AMSa5kfKfgu9hV9MOE8fiWlhbU1NSgr68PKSkpyMnJQUVFBYqKimYd/JycnDMPHz7MVcAUGQUYVdojqg0rx8YoBVBJEdFTAMsEADCnqKgoqaGh4acej+dH4Daj1tzc/PuysjIxHA6/THl7whDAriQdLgBJO3fu9Ozbt68wKyurJCUlpZjDF5vduXOnvbGx8UZ9fb3g8/nydOQ+oJEDDFM5gBYBQjNFAIFSAIdKAKUc6VYKFc/hyaJFKiYvWtDTFLvZMuUzZuxeP7rAE7ck0G4wGBJlkBImb04IUU1NSuw6lStBgwTcpm7/okOAXsLH7gewtCPIbmFQ7MBo8Ok4FVTYR3u6+h2HxhyVEyA6AYKUAgSUph4TDcrAkg4JLK8F0F/SYmdQyQ3UQdqZIoQqZ3QI4GHAWP71QoBfef2CIkFQZ/oHoymgGQKw9WNJZ3B0lmqjKozqRkaRqVhxAlgjgCr7KgHGGRKIlBJEGJwMQ4HdojSxsT+knCOIqRsUZKoPu2vFRiWXPARMvs/0tm82BwgwJPBT4UCrCGTo/dEIwCaCWiQIQXsLEg2+uoyptW2JK8BUz5R0wmyIiv96oSCkAb7ek0OWkkCiAT5dIwhCe41aZe0cahoocAUwrQCSRtk3yBBBVQB2T4DegyIxhQB6cESDBNAAP0Qlhw6d+C9wBdCcXoMhgBYJgsxMQNQBP+YQwHo/YQZIKBKw8hWmYr/WXjVa/jkBtB/4ZMMAmxCGKM/XSwBnLAnUSggjOsfY7JVO/Gj55wQwJgB9P9mai5kHQ0w9HWw1ByBMNS8SJUGk1xD0pB+cAJrP+ksaU8Kn8mgY25d+pQEVGC9nPV7P85918KFTqWOVQEsRZu3hUCMSEA3PtjHEIFHiPieAMQES4vFwGICmBbDAvAcH31IuACTgD0REI4GWh0eTfE4AfRXQUwTgKf5EjBkQ+Y9EzWxSqDdTMEOeuBHArDfzn4mbmVlBNGJYAn6mQeAenjgK8VQIYOZ8nBAzC/i0gJ8tUDjos0sGbty4cePGjRs3bty4cePGzcj+B5C9EH9XK0MTAAAAAElFTkSuQmCC";
var global_mail_icon = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAKCAYAAAC9vt6cAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9oHHg0gKjtwF3IAAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJTVBXgQ4XAAABdElEQVQoz4WRMaviUBSEv5s8H8RO0Eq00SiCRMQigoiCYC2I/hhbwd8g1lpZWFioECwl2IqIwT5iGdDCK94tlhf27cK+gWmGYZg5R9i2rUzTpFAooOs6AEIINE1DCPEPv/T3+81yuURMJhNlmiYAtVqNSCTCT7her6zXa6SUaFJKms0m8XicxWLB5XIJjUqpkAD3+53tdovruvT7faSUfHyZi8UiyWQSx3HwfZ96vY4QIgy73W5sNhssy6LRaIRztT+rxWIxer0eUkpms1moe57HfD6n0+lQKpXQdT1s9fH3PqUUmUwG13UZjUaUy2V2ux2WZRGNRlFKfWv2LSAIAlzXJQgCBoMBz+eTw+HAcDjE8zym0ynVapVsNhtOCAOOxyOn04l8Pk+73Qbg8/OTSqWCUopcLkcikWC/33M+n2m1Wr9fPh6PVTqdxjAMbNvGMIwf3+j7Po7j8Hg8EJZlqW63SyqVQtO08Dj/gxCC1+vFarXiF7aOl1qte6kYAAAAAElFTkSuQmCC";

////////////////////////////////////////////////////////////////////////////

// Link on Google Maps
if(document.location.href.match(/^(http|https):\/\/maps\.google\.(de|com)/) || document.location.href.match(/^(http|https):\/\/www\.google\.(de|com)\/maps/)){
  if(settings_show_google_maps){
    var ref_link = document.getElementById("link");
    if(ref_link){
      function open_gc(){
        var matches = ref_link.href.match(/&ll=([-0-9]*\.[0-9]*),([-0-9]*\.[0-9]*)/);
        var zoom = ref_link.href.match(/z=([0-9]*)/);
        if (matches != null && zoom != null) {
          var gc_map_url = map_url + "?lat=" + matches[1] + "&lng=" + matches[2] + "&zm=" + zoom[1];
          window.open(gc_map_url);
        }
        else {
          alert("This map has no geographical coordinates in its link. Just zoom or drag the map, afterwards this will work fine.");
        }
      }
    
      var box = ref_link.parentNode;
      
      var gcImage = document.createElement("img");
      gcImage.setAttribute("src","http://www.geocaching.com/images/about/logos/geocaching/Logo_Geocaching_color_notext_32.png");
      gcImage.setAttribute("title", "Show area at geocaching.com");
      gcImage.setAttribute("alt", "Show area at geocaching.com");

      var link = document.createElement("a");
      link.setAttribute("title","Show area at geocaching.com");
      link.setAttribute("href","#");
      link.setAttribute("id","gc_com_lnk");

      link.appendChild(gcImage);
      box.appendChild(link);
      
      document.getElementById('gc_com_lnk').addEventListener("click", open_gc, false);
    }
  }
}else{
  
////////////////////////////////////////////////////////////////////////////
// Helper
// Run after Redirect
if(GM_getValue("run_after_redirect") != ""){
  try{
    eval("unsafeWindow."+GM_getValue("run_after_redirect"));
  }catch(e){
    // ignore exceptions
  }
  GM_setValue("run_after_redirect","no");
}

function getElementsByClass(classname){
  var result = new Array();
  var all_elements = document.getElementsByTagName("*");

  for(var i=0; i<all_elements.length;i++){
    if(all_elements[i].className == classname){
      result.push(all_elements[i]);
    }
  }

  return result;
}

function in_array(search,arr){
  for(var i=0; i<arr.length;i++) if(arr[i] == search) return true;
  return false;
}

function caseInsensitiveSort(a, b){  // http://www.codingforums.com/showthread.php?t=10477
  var ret = 0;
  a = a.toLowerCase();b = b.toLowerCase();
  if(a > b) 
    ret = 1;
  if(a < b) 
    ret = -1; 
  return ret;
}

// Show Update-Banner
if(GM_getValue("new_version",scriptVersion) > scriptVersion){
  var banner = "";
  banner = "<div align='center' style='background-color: #FF8888;'>There is an update available for <b>GC little helper</b> - you can update <a href='http://www.amshove.net/greasemonkey/updates.php' target='_blank'>here</a></div>";
  document.getElementsByTagName("body")[0].innerHTML = banner+document.getElementsByTagName("body")[0].innerHTML;
}
////////////////////////////////////////////////////////////////////////////

// F2 zum Log abschicken
if(settings_submit_log_button && (document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?(id|guid|ID|wp|LUID|PLogGuid)\=/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/log\.aspx\?(id|wid|guid|ID|PLogGuid)\=/))){
  function keydown(e){
    if(e.keyCode == 113){
      document.getElementById("ctl00_ContentBody_LogBookPanel1_LogButton").click();
    }
  }

  window.addEventListener('keydown', keydown, true);
}

// Bookmark-Liste im Profil
if(settings_bookmarks_show && (document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/$/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/default\.aspx/))){
  var side = document.getElementById("ctl00_ContentBody_WidgetMiniProfile1_LoggedInPanel");

  var header = document.createElement("h3");
  header.setAttribute("class","WidgetHeader");
  header.appendChild(document.createTextNode(" Linklist"));

  var div = document.createElement("div");
  div.setAttribute("class","WidgetBody ProfileWidget");

  var ul = document.createElement("ul");

  for(var i=0; i < settings_bookmarks_list.length; i++){
    var x = settings_bookmarks_list[i];
    if(typeof(x) == "undefined") continue;
    var a = document.createElement("a");

    for(attr in bookmarks[x]){
      if(attr != "custom") a.setAttribute(attr,bookmarks[x][attr]);
    }

    a.appendChild(document.createTextNode(bookmarks[x]['title']));

    var li = document.createElement("li");
    li.appendChild(a);

    ul.appendChild(li);
  }

  div.appendChild(ul);
  side.appendChild(header);
  side.appendChild(div);
}

// Bookmarks on top
if(settings_bookmarks_on_top && document.getElementById('Navigation')){
  var nav_list = document.getElementById('Navigation').childNodes[1];
  
  var menu = document.createElement("li");
  
  var headline = document.createElement("a");

  if(settings_bookmarks_top_menu){   // Navi vertikal
    headline.setAttribute("href","#");
    headline.setAttribute("title","Linklist");
    headline.setAttribute("accesskey","7");
    headline.innerHTML = "Linklist ▼";
    menu.appendChild(headline);
    
    var submenu = document.createElement("ul");
    submenu.setAttribute("class","SubMenu");
    submenu.setAttribute("style","visibility: hidden;");
    menu.appendChild(submenu);
  
    for(var i=0; i < settings_bookmarks_list.length; i++){
      var x = settings_bookmarks_list[i];
      if(typeof(x) == "undefined") continue;
  
      var sublink = document.createElement("li");
      var hyperlink = document.createElement("a");
      
      for(attr in bookmarks[x]){
        if(attr != "custom") hyperlink.setAttribute(attr,bookmarks[x][attr]);
      }
      hyperlink.appendChild(document.createTextNode(bookmarks[x]['title']));
  
      sublink.appendChild(hyperlink);
      submenu.appendChild(sublink);
    }
    nav_list.appendChild(menu);
  }else{                             // Navi horizontal
    for(var i=0; i < settings_bookmarks_list.length; i++){
      var x = settings_bookmarks_list[i];
      if(typeof(x) == "undefined") continue;

      var sublink = document.createElement("li");
      var hyperlink = document.createElement("a");
 
      for(attr in bookmarks[x]){
        if(attr != "custom") hyperlink.setAttribute(attr,bookmarks[x][attr]);
      }
      hyperlink.appendChild(document.createTextNode(bookmarks[x]['title']));

      sublink.appendChild(hyperlink);
      nav_list.appendChild(sublink);
    }
  }

  // Search field
  if(settings_bookmarks_search){
    var code = "function gclh_search(){";
    code += "  var search = document.getElementById('navi_search').value;";
    code += "  if(search.match(/^GC[A-Z0-9]{1,10}\\b/i) || search.match(/^TB[A-Z0-9]{1,10}\\b/i)) document.location.href = 'http://coord.info/'+search;";
    code += "  else if(search.match(/^[A-Z0-9]{6}\\b$/i)) document.location.href = '/track/details.aspx?tracker='+search;";
    code += "  else document.location.href = '/default.aspx?navi_search='+search;";
    code += "}";

    var script = document.createElement("script");
    script.innerHTML = code;
    document.getElementsByTagName("body")[0].appendChild(script);

    var searchfield = "<form style='display: inline;' action='/default.aspx' method='GET' onSubmit='gclh_search(); return false;'><input type='text' size='6' name='navi_search' id='navi_search' style='margin-top: 2px; padding: 1px; font-weight: bold; font-family: sans-serif; border: 2px solid #778555; border-radius: 7px 7px 7px 7px; -moz-border-radius: 7px; -khtml-border-radius: 7px; background-color:#d8cd9d' value='"+settings_bookmarks_search_default+"'></form>";
    var nav_list = document.getElementById('Navigation').childNodes[1];
    nav_list.innerHTML += searchfield;
  }

// menu      - <li class="">
// headline  -   <a href="#" title="Shop" accesskey="6" id="ctl00_hlNavShop">Shop ?</a>
// submenu   -   <ul class="SubMenu" style="visibility: hidden;">
// sublink   -     <li class="">
// hyperlink -       <a href="http://shop.groundspeak.com/" rel="external" title="Shop Geocaching" accesskey="j" id="ctl00_hlSubNavShop">Shop Geocaching</a></li>
// sublink   -     <li class="">
// hyperlink -       <a href="../about/buying.aspx" title="Guide to Buying a GPS Device" accesskey="k" id="ctl00_hlSubNavGPSGuide">Guide to Buying a GPS Device</a></li>
// submenu   -   </ul>
// menu      - </li>  
}

// Bookmarks on top - Beta Map
if(settings_bookmarks_on_top && document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/beta/) && document.getElementById('maps-hd')){
  var header = document.getElementById('maps-hd');
  var navi = header.childNodes[3].childNodes[1];
  var strong = navi.childNodes[1];
  //navi.removeChild(navi.childNodes[1]);

  for(var i=0; i < settings_bookmarks_list_beta.length; i++){
    var x = settings_bookmarks_list_beta[i];
    if(typeof(x) == "undefined") continue;

    var hyperlink = document.createElement("a");

    for(attr in bookmarks[x]){
      if(attr != "custom") hyperlink.setAttribute(attr,bookmarks[x][attr]);
    }
    hyperlink.appendChild(document.createTextNode(bookmarks[x]['title']));

    navi.insertBefore(hyperlink, strong);
    if (i != (settings_bookmarks_list_beta.length-1)) {
      navi.insertBefore(document.createTextNode(' | '), strong);
    }
  }
  navi.removeChild(strong);
}

// Remove gc.com Links in Navigation
if(document.getElementById('Navigation')){
  var liste = document.getElementById('Navigation').childNodes[1];
  if(GM_getValue('remove_navi_play') && document.getElementById('ctl00_hlNavPlay')) liste.removeChild(document.getElementById('ctl00_hlNavPlay').parentNode);
  if(GM_getValue('remove_navi_profile') && document.getElementById('ctl00_hlNavProfile')) liste.removeChild(document.getElementById('ctl00_hlNavProfile').parentNode);
  if(GM_getValue('remove_navi_join') && document.getElementById('ctl00_hlNavJoin')) liste.removeChild(document.getElementById('ctl00_hlNavJoin').parentNode);
  if(GM_getValue('remove_navi_community') && document.getElementById('ctl00_hlNavCommunity')) liste.removeChild(document.getElementById('ctl00_hlNavCommunity').parentNode);
  if(GM_getValue('remove_navi_videos') && document.getElementById('ctl00_hlNavVideos')) liste.removeChild(document.getElementById('ctl00_hlNavVideos').parentNode);
  if(GM_getValue('remove_navi_resources') && document.getElementById('ctl00_hlNavResources')) liste.removeChild(document.getElementById('ctl00_hlNavResources').parentNode);
  if(GM_getValue('remove_navi_shop') && document.getElementById('ctl00_hlNavShop')) liste.removeChild(document.getElementById('ctl00_hlNavShop').parentNode);
  if(GM_getValue('remove_navi_social', true)) document.getElementById("Navigation").removeChild(document.getElementById("Navigation").childNodes[3]);
}

// Redirect to Map
if(settings_redirect_to_map && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/nearest\.aspx\?/)){
//   var lat = document.location.href.match(/lat_h=([0-9.]*)/);
//   var lng = document.location.href.match(/long_h=([0-9.]*)/);
// 
//   if(!lat) var lat = document.location.href.match(/lat=([0-9.]*)/);
//   if(!lng) var lng = document.location.href.match(/lng=([0-9.]*)/);
//
//  if(!document.location.href.match(/&disable_redirect/)) document.location.href = map_url+"?lat="+lat[1]+"&lng="+lng[1];
  if(!document.location.href.match(/&disable_redirect/) && !document.location.href.match(/key=/) && document.getElementById('ctl00_ContentBody_LocationPanel1_lnkMapIt')){
    var match = document.getElementById('ctl00_ContentBody_LocationPanel1_lnkMapIt').href.match(/\.aspx\?(.*)/);
    if(match[1]) document.location.href = map_url+"?"+match[1];
  }
}

// Hide Feedback-Button
if(settings_hide_feedback) {
  function hide_feedback() {
    var button = document.getElementById('feedback-tab');
    if(button){
      button.parentNode.removeChild(button);
    }
  }
  
  window.addEventListener("load", hide_feedback, false);
}

// Hide Disclaimer
if(settings_hide_disclaimer && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*/)){
  var disc = getElementsByClass('DisclaimerWidget')[0];
  if(disc){
    disc.parentNode.removeChild(disc);
  }
}
if(settings_hide_disclaimer && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cdpf\.aspx/)){
  var disc = getElementsByClass('TermsWidget no-print')[0];
  if(disc){
    disc.parentNode.removeChild(disc);
  }
}

//remove paragraph containing the link to the advertisement instructions (not the advertisements itself!)
if (settings_hide_advert_link) {
  var links = document.getElementsByTagName('a');
  for(var i=0; i<links.length; i++){
    if(links[i].href.indexOf('advertising.aspx') > 0) {
      var del = links[i];
      while (del.parentNode != null && (del.parentNode.nodeName != 'P')) {
        del = del.parentNode;
      }
      if(del.parentNode) {
        del.parentNode.removeChild(del);
      }
      break;
    }
  }
}

if (settings_hide_line_breaks) {
  //remove line break after "Print" label
  var printHeader = document.getElementById('ctl00_ContentBody_uxPrintHeader');
  if (printHeader) {
    var br = printHeader.nextSibling.nextSibling;
    if (br) {
      br.parentNode.removeChild(br);
    }
  }
  // remove line break between "distance from home" and "Bundesland, Land"
  var distFromHome = document.getElementById('ctl00_ContentBody_lblDistFromHome');
  if (distFromHome) {
    var br = distFromHome.nextSibling.nextSibling;
    if (br && br.nodeName == 'BR') {
      br.parentNode.removeChild(br);
      // append dots to the former 2 lines to avoid confusion when reading
      if (("" + distFromHome.innerHTML).length > 0) {
        distFromHome.innerHTML += '.';
        var loc = document.getElementById('ctl00_ContentBody_Location');
        if (loc) {
          loc.innerHTML += '.';
        }
      }
    }
  }
}

// remove "Warning! Spoilers may be included in the descriptions or links."
if ( settings_hide_spoilerwarning) {
  var findCounts = document.getElementById('ctl00_ContentBody_lblFindCounts');
  if (findCounts) {
    var para = findCounts.nextSibling.nextSibling.nextSibling.nextSibling;
    if (para && para.nodeName == 'P') {
//      para.parentNode.removeChild(para);
      para.innerHTML = "&nbsp;";
    }
  }
}

// Hide Cache Notes
if(settings_hide_cache_notes && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*/)){
  var disc = getElementsByClass('NotesWidget')[0];
  if(disc){
    disc.parentNode.removeChild(disc);
  }
}

// Hide/Show Cache Notes
if(settings_hide_empty_cache_notes && !settings_hide_cache_notes && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*/)){
  var box = getElementsByClass('NotesWidget')[0];
  if(box){
    var code = 
      "function hide_notes() {" +
      "  if(document.getElementById('box_notes').style.display == 'none') {" +
      "    document.getElementById('box_notes').style.display = 'block';" +
      "  } else {" +
      "    document.getElementById('box_notes').style.display = 'none';" +
      "  }" +
      "}";
  
    var script = document.createElement("script");
    script.innerHTML = code;
    document.getElementsByTagName("body")[0].appendChild(script);

    box.setAttribute("id","box_notes");
    getElementsByClass("UserSuppliedContent")[0].innerHTML = "<font style='font-size: 10px;'><a href='#' onClick='hide_notes();'>Show/Hide Cache Notes</a></font><br><br>"+getElementsByClass("UserSuppliedContent")[0].innerHTML;
  
    function hide_on_load() {
      var notes = getElementsByClass('NotesWidget')[0];
      var notesText = document.getElementById("cache_note").innerHTML;
      if (notesText == "Click to enter a note" || notesText == "Klicken zum Eingeben einer Notiz") {
        notes.style.display = "none";
      }
    }
  
    window.addEventListener("load", hide_on_load, false);
  }
}

function trim(s) {
  var whitespace = ' \n ';
  for (var i = 0; i < whitespace.length; i++) {
    while (s.substring(0, 1) == whitespace.charAt(i)) {
      s = s.substring(1, s.length);
    }
    while (s.substring(s.length - 1, s.length) == whitespace.charAt(i)) {
      s = s.substring(0, s.length - 1);
    }
  }

  if(s.substring(s.length-6,s.length) == "&nbsp;") s = s.substring(0,s.length-6);
s
  return s;
}

if (settings_hide_hint) {
  //replace hint by a link which shows the hint dynamically
  var hint = document.getElementById('div_hint');
  if (hint) {
    var para = hint.previousSibling.previousSibling;
    if (para && para.nodeName == 'P') {
      if (trim(hint.innerHTML).length > 0) {
        var label = para.getElementsByTagName('strong')[0];
        var code = 
          "function hide_hint() {" +
          "  var hint = document.getElementById('div_hint');" +
          "  if(hint.style.display == 'none') {" +
          "    hint.style.display = 'block';" +
          "  } else {" +
          "    hint.style.display = 'none';" +
          "  }" +
//          "  if (document.getElementById('ctl00_ContentBody_EncryptionKey')) {" +
          "    hint.innerHTML = convertROTStringWithBrackets(hint.innerHTML);" +
//          "  }" +
          "  return false;" +
          "}";
        
        var script = document.createElement("script");
        script.innerHTML = code;
        document.getElementsByTagName("body")[0].appendChild(script);
        var link = document.createElement('a');
        link.setAttribute('href','javascript:void(0);');
        var text = document.createTextNode(""+label.innerHTML);
        link.appendChild(text);
        link.setAttribute('onclick', 'hide_hint();');
        para.previousSibling.previousSibling.appendChild(link);
        para.style.display = 'none';
      }
      hint.style.display = 'none';
      
      // remove hint description
      var decryptKey = document.getElementById('dk');
      if (decryptKey) {
        decryptKey.parentNode.removeChild(decryptKey);
      }      
    }
  }
}

//show disabled/archived caches with strikeout in title
if(settings_strike_archived && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*/)){
  var warnings = getElementsByClass('OldWarning');
  if (warnings[0]) {
    var cacheTitle = document.getElementById('ctl00_ContentBody_CacheName');
    if (cacheTitle) {
      var parent = cacheTitle.parentNode;
      if (parent) {
        parent.removeChild(cacheTitle);
        var strike = document.createElement('strike');
        parent.appendChild(strike);
        strike.appendChild(cacheTitle);
      }
    }
  }
}

// Show all Logs
//if(settings_show_all_logs && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*$/)){
//  if(settings_show_all_logs_count > 0){
//    var p = document.getElementsByTagName("p");
//    for(var i=0; i<p.length; i++){
//      if(p[i].innerHTML.match(/There are [0-9]* additional logs/)){
//        var matches = p[i].innerHTML.match(/[0-9]+/);
//        if(matches) if(matches[0] < settings_show_all_logs_count-5){
//          document.location.href = document.location.href+"&log=y";
//        }
//        break;
//      }
//    }
//  }else document.location.href = document.location.href+"&log=y";
//}
//if(settings_show_all_logs && settings_show_all_logs_count < 1){
//  function change_cache_link(){
//    var links = document.getElementsByTagName('a');
//    for(var i=0; i<links.length; i++){
//      if(links[i].href.match(/seek\/cache_details\.aspx\?/) && !links[i].href.match(/&log=y/)){
//        links[i].href += "&log=y";
//      }
//    }
//  }
//  
//  window.addEventListener("load", change_cache_link, false);
//}

// Decrypt Hint
if(settings_decrypt_hint && !settings_hide_hint && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx(\?|\?pf\=\&)(guid|wp)\=[a-zA-Z0-9-]*/)){
  if (document.getElementById('ctl00_ContentBody_EncryptionKey')) {
    unsafeWindow.dht(document.getElementById("ctl00_ContentBody_lnkDH"));
  }
}
if(settings_decrypt_hint && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cdpf\.aspx/)){
  if(document.getElementById('uxDecryptedHint')) document.getElementById('uxDecryptedHint').style.display = 'none';
  if(document.getElementById('uxEncryptedHint')) document.getElementById('uxEncryptedHint').style.display = '';
}

// Show Smilies & BBCode --- http://www.cachewiki.de/wiki/Formatierung
if(settings_show_bbcode && (document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?(id|guid|ID|wp|LUID|PLogGuid)\=/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/log\.aspx\?(id|wid|guid|ID|LUID|PLogGuid)\=/)) && document.getElementById('litDescrCharCount')){
  // Get foinds to replace #found# variable
  if(getElementsByClass('SignedInText')[0]){
    var text = getElementsByClass('SignedInText')[0].childNodes[7].innerHTML;
    var finds = parseInt(text.match(/([0-9,]{1,10})/)[1].replace(/,/g,""));
  }

  var code = "function gclh_insert(aTag,eTag){"; // http://aktuell.de.selfhtml.org/artikel/javascript/bbcode/
  code += "  var input = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo');";
  code += "  if(typeof input.selectionStart != 'undefined'){";
  code += "    var start = input.selectionStart;";
  code += "    var end = input.selectionEnd;";
  code += "    var insText = input.value.substring(start, end);";
  code += "    input.value = input.value.substr(0, start) + aTag + insText + eTag + input.value.substr(end);";
  code += "    /* Anpassen der Cursorposition */";
  code += "    var pos;";
  code += "    if (insText.length == 0) {";
  code += "      pos = start + aTag.length;";
  code += "    } else {";
  code += "      pos = start + aTag.length + insText.length + eTag.length;";
  code += "    }";
  code += "    input.selectionStart = pos;";
  code += "    input.selectionEnd = pos;";
  code += "  }";
  code += "  input.focus();";
  code += "}";

  code += "function gclh_insert_from_div(id){";
  code += "  var finds = '"+finds+"';";
  code += "  var input = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo');";
  code += "  var inhalt = document.getElementById(id).innerHTML;";
  code += "  inhalt = inhalt.replace(/\\&amp\\;/g,'&');";
  code += "  if(finds){";
  code += "    inhalt = inhalt.replace(/#found_no#/g,finds);";
  code += "    finds++;";
  code += "    inhalt = inhalt.replace(/#found#/g,finds);";
  code += "  }";
  code += "  if(typeof input.selectionStart != 'undefined' && inhalt){";
  code += "    var start = input.selectionStart;";
  code += "    var end = input.selectionEnd;";
  code += "    var insText = input.value.substring(start, end);";
  code += "    input.value = input.value.substr(0, start) + inhalt + input.value.substr(end);";
  code += "    /* Anpassen der Cursorposition */";
  code += "    var pos;";
  code += "    pos = start + inhalt.length;";
  code += "    input.selectionStart = pos;";
  code += "    input.selectionEnd = pos;";
  code += "  }";
  code += "  input.focus();";
  code += "}";

  var script = document.createElement("script");
  script.innerHTML = code;
  document.getElementsByTagName("body")[0].appendChild(script);

  var box = document.getElementById('litDescrCharCount');
  var liste = "";
  liste += "<a href='#' onClick='gclh_insert(\"[:)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:D]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_big.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[8D]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_cool.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:I]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_blush.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:P]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_tongue.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[}:)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_evil.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[;)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_wink.gif' border='0'></a>";
  liste += "<br>";
  liste += "<a href='#' onClick='gclh_insert(\"[:o)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_clown.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[B)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_blackeye.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[8]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_8ball.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:(]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_sad.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[8)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_shy.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:O]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_shock.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:(!]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_angry.gif' border='0'></a>";
  liste += "<br>";
  liste += "<a href='#' onClick='gclh_insert(\"[xx(]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_dead.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[|)]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_sleepy.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[:X]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_kisses.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[^]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_approve.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[V]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_dissapprove.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[?]\",\"\"); return false;'><img src='http://www.geocaching.com/images/icons/icon_smile_question.gif' border='0'></a>";
  liste += "<br>";
  liste += "<a href='#' onClick='gclh_insert(\":angry:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/mad.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":grin:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/big_smile.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":sad:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/sad.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":shocked:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/shock.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":smile:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/smile.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":surprise:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/surprise.gif' border='0'></a>";
  liste += "<br>";
  liste += "<a href='#' onClick='gclh_insert(\":tired:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/tired.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":yikes:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/ohh.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":tongue:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/tongue.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":bad:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/bad_boy_a.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":back:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/back.gif' border='0'></a>";
  liste += "<a href='#' onClick='gclh_insert(\":cute:\",\"\"); return false;'><img src='http://img.groundspeak.com/forums/emoticons/signal/cute.gif' border='0'></a>";
  liste += "<br>";
  liste += "BBCode:<br>";
  liste += "<a href='#' onClick='gclh_insert(\"[black]\",\"[/black]\"); return false;'><span style='font-size: 8px; background-color: black;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[blue]\",\"[/blue]\"); return false;'><span style='font-size: 8px; background-color: blue;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[red]\",\"[/red]\"); return false;'><span style='font-size: 8px; background-color: red;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[purple]\",\"[/purple]\"); return false;'><span style='font-size: 8px; background-color: purple;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[orange]\",\"[/orange]\"); return false;'><span style='font-size: 8px; background-color: orange;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[yellow]\",\"[/yellow]\"); return false;'><span style='font-size: 8px; background-color: yellow;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[green]\",\"[/green]\"); return false;'><span style='font-size: 8px; background-color: green;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<a href='#' onClick='gclh_insert(\"[white]\",\"[/white]\"); return false;'><span style='font-size: 8px; background-color: white;'>&nbsp;&nbsp;&nbsp;&nbsp;</span></a>";
  liste += "<br>";
  liste += "<a href='#' onClick='gclh_insert(\"[b]\",\"[/b]\"); return false;' style='color: #000000; text-decoration: none;'><b>B</b></a>&nbsp;";
  liste += "<a href='#' onClick='gclh_insert(\"[i]\",\"[/i]\"); return false;' style='color: #000000; text-decoration: none;'><i>I</i></a>&nbsp;";
  liste += "<a href='#' onClick='gclh_insert(\"[s]\",\"[/s]\"); return false;' style='color: #000000; text-decoration: none;'><s>S</s></a>&nbsp;";
  liste += "<a href='#' onClick='gclh_insert(\"[u]\",\"[/u]\"); return false;' style='color: #000000; text-decoration: none;'><u>U</u></a>&nbsp;";
  liste += "<a href='#' onClick='gclh_insert(\"[url=\"+prompt(\"URL\",\"http://\")+\"]\",\"[/url]\"); return false;'><img border='0' height='14px' src='data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAQCAYAAAAf8/9hAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sHGBAABIjrgkUAAAAIdEVYdENvbW1lbnQA9syWvwAAAP5JREFUOMulk21vwjAMhM99idTy/38oqlqqpvGzDywerDDYZimKEtuXs88xAP3DulcBZvbU5+5qXgFUgoDcXYD2ff964N0SzEyAAJlZnJtKZdu2Az1A67rKzDRNk9xd7q6cc8Tok8HTlVICwN1ZloVrNcRuAGamZVkEqO979X0ftN1dZqacs1JK18Y1zZGBu8dLpRQkUUqJO0nknAFY1zVydEsHiOR5ngHIOUc5jywAtm27d0hcLpdDzd8t5uBW23oehkHAQaE7eed55nQ66dU4VN0P91WFUkp09zcAzTtT+BO7+Ext2/79N9Ym1REdx1Hn81kpJQHquk4ppYcAH5ciQg1K86RjAAAAAElFTkSuQmCC'></a>";
  liste += "<br>";
  liste += "Templates:<br>";
  for(var i = 0; i < anzTemplates; i++){
    if(GM_getValue("settings_log_template_name["+i+"]","") != ""){
      liste += "<div id='gclh_template["+i+"]' style='display: none;'>"+GM_getValue("settings_log_template["+i+"]","")+"</div>";
      liste += "<a href='#' onClick='gclh_insert_from_div(\"gclh_template["+i+"]\"); return false;' style='color: #000000; text-decoration: none; font-weight: normal;'> - "+GM_getValue("settings_log_template_name["+i+"]","")+"</a><br>";
    }
  }
  box.innerHTML = liste;
}

//Maxlength of Logtext
if((document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?(id|guid|ID|wp|LUID|PLogGuid)\=/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/log\.aspx\?(id|wid|guid|ID|LUID|PLogGuid)\=/)) && document.getElementById('litDescrCharCount')){
  function limitLogText(limitField) {
    var limitNum = 4000;
    if (limitField.value.length > limitNum) {
      limitField.value = limitField.value.substring(0, limitNum);
      counterelement.innerHTML = '<font color="red">' + limitField.value.length + '/' + limitNum  + '</font>';
      limitField.scrollTop = limitField.scrollHeight;
      limitField.selectionStart = 4000;
      limitField.selectionEnd = 4000;
    }else{
      counterelement.innerHTML = limitField.value.length + '/' + limitNum;
    }
  }

  var logfield = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo');
  logfield.addEventListener("keyup", function(){ limitLogText(logfield); }, false);
  logfield.addEventListener("change", function(){ limitLogText(logfield); }, false);

  var counterpos = document.getElementById('litDescrCharCount').parentNode;
  var counterspan = document.createElement('p');
  counterspan.id = "logtextcounter";
  counterspan.innerHTML = "<b>Loglength:</b><br />"
  var counterelement = document.createElement('span');
  counterelement.innerHTML = "0/4000";
  counterspan.appendChild(counterelement);
  counterpos.appendChild(counterspan);
}

// Show eMail-Link beside Username
if(settings_show_mail && document.location.href.match(/^http:\/\/www\.geocaching\.com\/(seek\/cache_details|seek\/log|track\/details|track\/log)\.aspx(\?|\?pf\=\&)(guid|wp|tracker|id|LUID|ID|PLogGuid)\=[a-zA-Z0-9-]*/)){
  var links = document.getElementsByTagName('a');
  if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?.*/)){
    var name = "";
    var image = true;
    for(var i=0; i<links.length; i++){
      if(links[i].href.match(/http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/)){
        if(image){
          image = false;  // First hit is an Image
        }else{
          if(links[i].getElementsByTagName('span')[0] !== undefined){
            name = links[i].getElementsByTagName('span')[0].innerHTML;
          }else{
            name = links[i].innerHTML
          }
        }
      }
    }
  }else if(document.getElementById('ctl00_ContentBody_CacheName')){
    var name = document.getElementById('ctl00_ContentBody_CacheName').innerHTML;
    if(document.getElementById('ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode')) name += " ("+document.getElementById('ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode').innerHTML+")";
  }else if(document.getElementById('ctl00_ContentBody_lbHeading') && !document.location.href.match(/^http:\/\/www\.geocaching\.com\/(seek|track)\/log\.aspx\?.*/)){
    var name = document.getElementById('ctl00_ContentBody_lbHeading').innerHTML;
    if(document.getElementById('ctl00_ContentBody_BugDetails_BugTBNum') && document.getElementById('ctl00_ContentBody_BugDetails_BugTBNum').getElementsByTagName('strong')){
      var tbnr = document.getElementById('ctl00_ContentBody_BugDetails_BugTBNum').getElementsByTagName('strong')[0]; 
      if(tbnr != "")name = name + " (" + tbnr.innerHTML + ")";
    }
  }else if(document.getElementById('ctl00_ContentBody_LogBookPanel1_lbLogText')){
    var name = "";
    try {
      name = document.getElementById('ctl00_ContentBody_LogBookPanel1_lbLogText').childNodes[4].innerHTML;
    } catch(e) {}
  }else var name = ""; 

  for(var i=0; i<links.length; i++){
    if(links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=/) && links[i].parentNode.className != "logOwnerStats" && !links[i].childNodes[0].src){
      var guid = links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=(.*)/);
      guid = guid[1];

      var mail_link = document.createElement("a");
      var mail_img = document.createElement("img");
      mail_img.setAttribute("border","0");
      mail_img.setAttribute("title","Send a mail to this user");
      mail_img.setAttribute("src",global_mail_icon);
      mail_link.appendChild(mail_img);
      mail_link.setAttribute("href","http://www.geocaching.com/email/?guid="+guid+"&text="+name);

      links[i].parentNode.appendChild(document.createTextNode("   "));
      links[i].parentNode.appendChild(mail_link);

      //GM_setValue("run_after_redirect","document.getElementById(\"ctl00_ContentBody_SendMessagePanel1_tbMessage\").innerHTML = \""+name+"\";");
    }
  }
  
  global_cache_name = name;
}

// Switch title-color to red, if cache is archived
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/)){

  var warnings = getElementsByClass("OldWarning");
  for(var i=0; i<warnings.length; i++){
    if(warnings[i].innerHTML.match(/(archived|archiviert)/)){
      if(document.getElementById("ctl00_ContentBody_CacheName")) document.getElementById("ctl00_ContentBody_CacheName").parentNode.style.color = '#8C0B0B';
      break;
    }
  }
}

// Improve EMail-Site
if(settings_show_mail && document.location.href.match(/^http:\/\/www\.geocaching\.com\/email\//) && document.getElementById("ctl00_ContentBody_SendMessagePanel1_tbMessage")){
  // Prevent deleting content
  document.getElementById("ctl00_ContentBody_SendMessagePanel1_tbMessage").setAttribute("onfocus","");

  // Default settings
  document.getElementById("ctl00_ContentBody_SendMessagePanel1_chkSendAddress").checked = GM_getValue("email_sendaddress","checked");
  document.getElementById("ctl00_ContentBody_SendMessagePanel1_chkEmailCopy").checked = GM_getValue("email_mailcopy","checked");

  function chgDefaultSendaddress(){
    GM_setValue("email_sendaddress",document.getElementById("ctl00_ContentBody_SendMessagePanel1_chkSendAddress").checked);
  }

  function chgDefaultMailcopy(){
    GM_setValue("email_mailcopy",document.getElementById("ctl00_ContentBody_SendMessagePanel1_chkEmailCopy").checked);
  }

  document.getElementById('ctl00_ContentBody_SendMessagePanel1_chkSendAddress').addEventListener("click", chgDefaultSendaddress, false);
  document.getElementById('ctl00_ContentBody_SendMessagePanel1_chkEmailCopy').addEventListener("click", chgDefaultMailcopy, false);
  
  // Grab Text from URL
  var matches = document.location.href.match(/&text=(.*)/);
  if(matches) document.getElementById("ctl00_ContentBody_SendMessagePanel1_tbMessage").innerHTML = decodeURIComponent(matches[1]);
  
  // Add Mail-Signature
  if(typeof(GM_getValue("settings_mail_signature")) != "undefined" && GM_getValue("settings_mail_signature") != "") document.getElementById("ctl00_ContentBody_SendMessagePanel1_tbMessage").innerHTML += "\n\n"+GM_getValue("settings_mail_signature");
}

// Default Log Type && Log Signature
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?(id|guid|ID|PLogGuid|wp)\=/) && document.getElementById('ctl00_ContentBody_LogBookPanel1_ddLogType')){
  if(settings_default_logtype != "-1" && !document.location.href.match(/\&LogType\=/) && !document.location.href.match(/PLogGuid/)){
    var select = document.getElementById('ctl00_ContentBody_LogBookPanel1_ddLogType');
    var childs = select.childNodes;

    if(select.value == "-1"){
      for(var i=0; i<childs.length; i++){
        if(childs[i].value == settings_default_logtype){
          childs[i].setAttribute("selected","selected");
        }
      }
    }
  }

  // Signature
  if(document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML == "" || document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?PLogGuid\=/)){
    document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML += GM_getValue("settings_log_signature","");
  }

  // Set Cursor to Pos1
  function gclh_setFocus(){
    var input = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo');
    if(input){
      try {
        input.selectionStart = 0;
        input.selectionEnd = 0;
        input.focus();
      }
      catch (e) {
        // TODO: according to Google this exception occurs if the text field is not visible,
        // but I have no clue what exactly is wrong here 
      }
    }
  }
  window.addEventListener("load", gclh_setFocus, false);

  // Replace #found# variable
  if(getElementsByClass('SignedInText')[0]){
    var text = getElementsByClass('SignedInText')[0].childNodes[7].innerHTML;
    var finds = parseInt(text.match(/([0-9,]{1,10})/)[1].replace(/,/g,""));
    document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML.replace(/#found_no#/g,finds);
    finds++;
    document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML.replace(/#found#/g,finds);
  }
}

// Default TB Log Type && Log Signature
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/log\.aspx/)){
  if(settings_default_tb_logtype != "-1" && !document.location.href.match(/\&LogType\=/)){
    var select = document.getElementById('ctl00_ContentBody_LogBookPanel1_ddLogType');
    var childs = select.childNodes;

    for(var i=0; i<childs.length; i++){
      if(childs[i].value == settings_default_tb_logtype){
        childs[i].setAttribute("selected","selected");
      }
    }
  }

  // Signature
  if(document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo') && document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML == "") document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML = GM_getValue("settings_tb_signature","");

  // Set Cursor to Pos1
  function gclh_setFocus(){
    var input = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo');
    if(input){
      try {
        input.selectionStart = 0;
        input.selectionEnd = 0;
        input.focus();
      } catch (e) {
        // TODO: according to Google this exception occurs if the text field is not visible,
        // but I have no clue what exactly is wrong here 
      }
    }
  }
  window.addEventListener("load", gclh_setFocus, false);

  // Replace #found# variable
  if(getElementsByClass('SignedInText')[0] && document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo')){
    var text = getElementsByClass('SignedInText')[0].childNodes[7].innerHTML;
    var finds = parseInt(text.match(/([0-9,]{1,10})/)[1].replace(/,/g,""));
    document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML.replace(/#found_no#/g,finds);
    finds++;
    document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML = document.getElementById('ctl00_ContentBody_LogBookPanel1_uxLogInfo').innerHTML.replace(/#found#/g,finds);
  }
}

// Show Coin-series in TB-Listing
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/details\.aspx/)){
  var dl = getElementsByClass('BugDetailsList')[0];

  if(dl){
    var title = document.getElementsByTagName('title')[0].innerHTML;
    if(title){
      var matches = title.match(/\([A-Za-z0-9]*\) ([A-Za-z0-9-!–_.,\s]*) - /);
      if(matches) dl.innerHTML += "<dt>Series:</dt><dd>"+matches[1]+"</dd>";
    }
  }
}

// Improve Friendlist
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/myfriends\.aspx/)){
  var friends = getElementsByClass("FriendText");
  var day = new Date().getDate();
  var last_check = parseInt(GM_getValue("friends_founds_last","0"),10);

  if(settings_automatic_friend_reset && last_check != day){
    for(var i=0; i<friends.length; i++){
      var friend = friends[i];
      var name = friend.getElementsByTagName("a")[0];

      if(GM_getValue("friends_founds_new_"+name.innerHTML)){
        GM_setValue("friends_founds_"+name.innerHTML,GM_getValue("friends_founds_new_"+name.innerHTML));
      }
    }  
    GM_setValue("friends_founds_last",day);
  }
  
  for(var i=0; i<friends.length; i++){
    var friend = friends[i];
    var name = friend.getElementsByTagName("a")[0];
    var add = "";
    var founds = trim(friend.getElementsByTagName("dd")[4].innerHTML).replace(/[,.]*/g,"");
    var last_founds = GM_getValue("friends_founds_"+name.innerHTML);

    if(typeof(last_founds) == "undefined") last_founds = founds;
    if((founds - last_founds) > 0) add = " <font color='#00AA00'><b>(+"+(founds - last_founds)+")</b></font>";
    GM_setValue("friends_founds_new_"+name.innerHTML,trim(founds));
    
    friend.getElementsByTagName("dd")[4].innerHTML = "<a href='/seek/nearest.aspx?ul="+name.innerHTML+"&disable_redirect'>"+founds+"</a>"+add;
    
    friend.getElementsByTagName("p")[0].innerHTML = "<a name='lnk_profilegallery2' href='"+name.href+"'>Gallery</a> | <a href='/seek/nearest.aspx?u="+name.innerHTML+"&disable_redirect'>Hidden Caches</a> | "+friend.getElementsByTagName("p")[0].innerHTML;
  }

  function gclh_reset_counter(){
    var friends = getElementsByClass("FriendText");
  
    for(var i=0; i<friends.length; i++){
      var friend = friends[i];
      var name = friend.getElementsByTagName("a")[0];
      var founds = friend.getElementsByTagName("dd")[4].innerHTML.match(/>([0-9]*)/);
      if(founds[1]){
        GM_setValue("friends_founds_"+name.innerHTML,trim(founds[1]));
  
        friend.getElementsByTagName("dd")[4].innerHTML = "<a href='/seek/nearest.aspx?ul="+name.innerHTML+"&disable_redirect'>"+founds[1]+"</a>";
      }
    }
  }

  var button = document.createElement("input");
  button.setAttribute("type","button");
  button.setAttribute("value","Reset counter");
  button.addEventListener("click", gclh_reset_counter, false);

  document.getElementById('ctl00_ContentBody_FindUserPanel1_GetUsers').parentNode.insertBefore(button,document.getElementById('ctl00_ContentBody_FindUserPanel1_GetUsers').nextSibling);
}

// Show Google-Maps Link on Cache Page
if(settings_show_google_maps && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx\?/) && document.getElementById("ctl00_ContentBody_uxViewLargerMap") && document.getElementById("ctl00_ContentBody_LatLon") && document.getElementById("ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode")){
  var ref_link = document.getElementById("ctl00_ContentBody_uxViewLargerMap");
  var box = ref_link.parentNode;
//  var matches = ref_link.href.match(/lat=([-0-9]*\.[0-9]*)\&lng=([-0-9]*\.[0-9]*)/);
    
  box.appendChild(document.createElement("br"));
  
  var link = document.createElement("a");
  link.setAttribute("class","lnk");
  link.setAttribute("target","_blank");
  link.setAttribute("title","Show area at Google Maps");
//  link.setAttribute("href","http://maps.google.de/?ll="+matches[1]+","+matches[2]);
  link.setAttribute("href","http://maps.google.com/maps?q="+document.getElementById("ctl00_ContentBody_LatLon").innerHTML+" ("+document.getElementById("ctl00_ContentBody_CoordInfoLinkControl1_uxCoordInfoCode").innerHTML+")");
  
  var img = document.createElement("img");
  img.setAttribute("src","/images/silk/map_go.png");
  link.appendChild(img);
  
  link.appendChild(document.createTextNode(" "));
  
  var span = document.createElement("span");
  span.appendChild(document.createTextNode("Show area on Google Maps"));
  link.appendChild(span);
  
  box.appendChild(link);
}

// Show Google-Maps Link on Map Page
if(settings_show_google_maps && document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/default\.aspx/)){

/*
  var evObj = document.createEvent('MouseEvents');
  evObj.initEvent('click', true, false );
  document.getElementById("lp").dispatchEvent(evObj);
      
  //document.getElementById("lp").click();
  alert(document.getElementById("leurl").value);
  */
  /*var ref_link = document.getElementById("ctl00_ContentBody_uxViewLargerMap");
  var box = ref_link.parentNode;
  var matches = ref_link.href.match(/lat=([-0-9]*\.[0-9]*)\&lng=([-0-9]*\.[0-9]*)/);
    
  box.appendChild(document.createElement("br"));
  
  var link = document.createElement("a");
  link.setAttribute("class","lnk");
  link.setAttribute("target","_blank");
  link.setAttribute("title","Show area at Google Maps");
  link.setAttribute("href","http://maps.google.de/?ll="+matches[1]+","+matches[2]);
  
  var img = document.createElement("img");
  img.setAttribute("src","/images/silk/map_go.png");
  link.appendChild(img);
  
  link.appendChild(document.createTextNode(" "));
  
  var span = document.createElement("span");
  span.appendChild(document.createTextNode("Show area on Google Maps"));
  link.appendChild(span);
  
  box.appendChild(link);*/
}

// Show "Log It"-Button
if(settings_show_log_it && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/nearest\.aspx\?/)){
  var links = document.getElementsByTagName("a");
  
  for(var i=0; i<links.length; i++){
    if(links[i].href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx\?.*/) && links[i].innerHTML.match(/^<span>/)){
      links[i].parentNode.innerHTML = links[i].parentNode.innerHTML.replace("<br>","<a title='Log it' href='"+links[i].href.replace("cache_details","log")+"'><img src='/images/stockholm/16x16/add_comment.gif'></a><br>");
    }
  }
}

// Improve Bookmark-List
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/bookmarks\/view\.aspx\?guid=/)){
  var box = document.getElementById("ctl00_ContentBody_lbHeading").parentNode.parentNode.parentNode;
  var matches = document.location.href.match(/guid=([a-zA-Z0-9-]*)/);
  var uuid = matches[1];
  
  box.childNodes[3].innerHTML += "<br><a title=\"Download as kml\" href='http://www.geocaching.com/kml/bmkml.aspx?bmguid="+uuid+"'>Download as kml</a><br><a title=\"Show in google maps\" href='http://maps.google.com/?q=http://www.geocaching.com/kml/bmkml.aspx?bmguid="+uuid+"' target='_blank'>Show in google maps</a>";
}
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/bookmarks\/default\.aspx/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/lists\.aspx/)){
  var links = document.getElementsByTagName("a");
  
  for(var i=0; i<links.length; i++){
    if(links[i].title == "Download Google Earth KML"){

      var matches = links[i].href.match(/guid=([a-zA-Z0-9-]*)/);
      links[i].parentNode.innerHTML += "<br><a title='Show in google maps' href='http://maps.google.com/?q=http://www.geocaching.com/kml/bmkml.aspx?bmguid="+matches[1]+"' target='_blank'>Show in google maps</a>";
    }
  }
}

// Improve "My Profile"
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my/)){
  var code = "function hide_box(i){";
  code += "  if(document.getElementById('box_'+i).style.display == 'none'){";
  code += "    document.getElementById('box_'+i).style.display = 'block';";
  code += "    document.getElementById('lnk_'+i).src = 'http://www.geocaching.com/images/minus.gif';";
  code += "    document.getElementById('lnk_'+i).title = 'hide';";
  code += "  }else{";
  code += "    document.getElementById('box_'+i).style.display = 'none';";
  code += "    document.getElementById('lnk_'+i).src = 'http://www.geocaching.com/images/plus.gif';";
  code += "    document.getElementById('lnk_'+i).title = 'show';";
  code += "  }";
  code += "}";

  if(GM_getValue("show_box[0]","" == "none")) GM_setValue("show_box[0]","block"); // Bugfix: First Box was hidden because of the temporary "+" beside Linklist
  
  var script = document.createElement("script");
  script.innerHTML = code;
  document.getElementsByTagName("body")[0].appendChild(script);

  var boxes = getElementsByClass("WidgetHeader");
  function saveStates(){
    for(var i=1; i<boxes.length; i++){
      var box = boxes[i].parentNode.childNodes[3];
    
      if(boxes[i].innerHTML.match(/Bookmarks/)) continue;
    
      if(typeof(box) == "undefined") continue;
      
      var show = box.style.display;
      if(typeof(show) == "undefined" || show != "none") show = "block";
      
      GM_setValue("show_box["+i+"]",show);
    }
  }
  
  for(var i=1; i<boxes.length; i++){
    var box = boxes[i].parentNode.childNodes[3];
    if(typeof(box) != "undefined"){
      if(boxes[i].innerHTML.match(/Bookmarks/)) continue;
    
      box.setAttribute("id","box_"+i);
     
      if(typeof(GM_getValue("show_box["+i+"]")) != "undefined") box.style.display = GM_getValue("show_box["+i+"]");
    
      if(box.style.display == "none")
        boxes[i].innerHTML = "<img id='lnk_"+i+"' src='http://www.geocaching.com/images/plus.gif' onClick='hide_box(\""+i+"\");' title='show'> "+boxes[i].innerHTML;
      else
        boxes[i].innerHTML = "<img id='lnk_"+i+"' src='http://www.geocaching.com/images/minus.gif' onClick='hide_box(\""+i+"\");' title='hide'> "+boxes[i].innerHTML;
      
      document.getElementById("lnk_"+i).addEventListener("click",saveStates,false);
    }
  }
}

// Show Homezone-Circle on Map
if(settings_show_homezone && document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/beta/)){ // BETA map
  var code = "function drawCircle(){ ";
  code += "if(google.maps){";
  code += "  var home_coord = new google.maps.LatLng("+(GM_getValue("home_lat")/10000000)+", "+(GM_getValue("home_lng")/10000000)+");";
  code += "  var circle = new google.maps.Circle({center:home_coord,map:map,radius:"+settings_homezone_radius+"000,strokeColor:'" + settings_homezone_color + "',fillColor:'"+ settings_homezone_color +"',fillOpacity:0.1,clickable:false});";
  code += "}}";
  
  var script = document.createElement("script");
  script.innerHTML = code;
  document.getElementsByTagName("body")[0].appendChild(script);
    
  function drawCircle(){
    unsafeWindow.drawCircle();
  }
  
  window.addEventListener("load", drawCircle, false);
}
if(settings_show_homezone && document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/default.aspx/)){
  var code = "function drawCircle() {"; // Code from http://www.geocodezip.com/GoogleEx_markerinfowindowCircle.asp
  code += "var point = new GLatLng("+(GM_getValue("home_lat")/10000000)+", "+(GM_getValue("home_lng")/10000000)+");";
  code += "var radius = "+settings_homezone_radius+";";
  code += "  var cColor = '"+ settings_homezone_color +"';";
  code += "  var cWidth = 5;";
  code += "  var Cradius = radius;   ";
  code += "  var d2r = Math.PI/180; ";
  code += "  var r2d = 180/Math.PI; ";
  code += "  var Clat = (Cradius/3963)*r2d; ";
  code += "  var Clng = Clat/Math.cos(point.lat()*d2r); ";
  code += "  var Cpoints = []; ";
  code += "  for (var i=0; i < 33; i++) { ";
  code += "    var theta = Math.PI * (i/16); ";
  code += "    var CPlng = point.lng() + (Clng * Math.cos(theta)); ";
  code += "    var CPlat = point.lat() + (Clat * Math.sin(theta)); ";
  code += "    var P = new GLatLng(CPlat,CPlng);";
  code += "    Cpoints.push(P); ";
  code += "  }";
  code += "  map.addOverlay(new GPolyline(Cpoints,cColor,cWidth)); ";
  code += "}";
  
  var script = document.createElement("script");
  script.innerHTML = code;
  document.getElementsByTagName("body")[0].appendChild(script);
    
  function drawCircle(){
    unsafeWindow.drawCircle();
  }
  
  window.addEventListener("load", drawCircle, false);
  
  // Draw Homezone Link
  var drawlink = document.createElement("a");
  drawlink.setAttribute("onclick","drawCircle();");
  drawlink.href = "#";
  drawlink.id = "drawlink";
  drawlink.innerHTML = "Draw Homezone";
  document.getElementById('uxMapRefresh').parentNode.insertBefore(drawlink,document.getElementById('uxMapRefresh'));
  var br = document.createElement("br");
  document.getElementById('uxMapRefresh').parentNode.insertBefore(br,document.getElementById('uxMapRefresh'));
}

if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/beta/)) {
  // hide my finds
  var code = 
    "function hideCaches(id) {" +
    "  if (google.maps) {" +
    "    var button = document.getElementById(id);" +
    "    if (button) {" +
    "      button.click();" +
    "    }" +
    "  }" +
    "}";
  
  var script = document.createElement("script");
  script.innerHTML = code;
  document.getElementsByTagName("body")[0].appendChild(script);
    
  function hideFound(){
    unsafeWindow.hideCaches('chkMyFinds');
  }
  function hideHidden(){
    unsafeWindow.hideCaches('chkMyHides');
  }
  
  if (settings_map_hide_found) {
    window.addEventListener("load", hideFound, false);
  }
  if (settings_map_hide_hidden) {
    window.addEventListener("load", hideHidden, false);
  }
}

// Change Map width
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/default.aspx/) && document.getElementById("Content")){
  var map_width = GM_getValue("map_width","1200");
  if(document.getElementById("Content").childNodes[1]) document.getElementById("Content").childNodes[1].style.width = map_width+"px";
  if(document.getElementById("ctl00_divBreadcrumbs")) document.getElementById("ctl00_divBreadcrumbs").style.width = map_width+"px";
  if(document.getElementById("ctl00_divContentMain")) document.getElementById("ctl00_divContentMain").style.width = map_width+"px";
}

// Aplly Search-field in Navigation
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/default\.aspx\?navi_search=/)){
  var matches = document.location.href.match(/\?navi_search=(.*)/);
  if(matches) document.getElementById("tbSearch").value = decodeURIComponent(matches[1]).replace(/\+/g," ");

  function click_search(){
    document.getElementById("ibSearch").click();
  }

  window.addEventListener("load", click_search, false);
}

// Home-Coords in Search-Field
//if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/$/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/default\.aspx$/)){
//  if(GM_getValue("home_lat") && GM_getValue("home_lng")){
//    var txt = "";
//    var lat = GM_getValue("home_lat")/10000000;
//    var lng = GM_getValue("home_lng")/10000000;
//
///*    if(lat < 0) txt += "S ";
//    else txt += "N ";
//    txt += lat;
//
//    if(lng < 0) txt += " W ";
//    else txt += " E ";
//    txt += lng;*/
//
//    txt = "N "+lat+" E "+lng;
//
//    document.getElementById("tbSearch").value = txt;
//  }
//}

// Dynamic Map
if(settings_dynamic_map && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx\?/)){
  function load_dynamic(){
    unsafeWindow.loadDynamicMap();
  }

  window.addEventListener("load", load_dynamic, false);
}

// Set default map to old
if(settings_old_map){
  var links = document.getElementsByTagName("a");

  for(var i = 0; i < links.length; i++){
    if(links[i].href.match(/\/map\/beta\/default\.aspx/)){
      var match = links[i].href.match(/\/map\/beta\/default\.aspx(.*)/);
      if(match[1]){
        links[i].href = "/map/default.aspx"+match[1];
      }
    }
  }
}

// Edit-Link to own Caches in Profile
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/(default\.aspx|owned\.aspx)$/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/$/)){
  var links = document.getElementsByTagName("a");
  
  for(var i = 0; i < links.length; i++){
    if(links[i].href.match(/\/seek\/cache_details\.aspx\?/)){
      var headline = links[i].parentNode.parentNode.parentNode.childNodes[1].innerHTML;
      if(headline){
        var match = links[i].href.match(/\/seek\/cache_details\.aspx\?guid=(.*)/);
        if(match[1]) links[i].parentNode.innerHTML += " <a href='/hide/report.aspx?guid="+match[1]+"'><img src='/images/stockholm/16x16/page_white_edit.gif'></a>";
      }
    }
  }
}

// Image-Link at own caches
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/owned\.aspx/)){
  var links = document.getElementsByTagName("a");

  for(var i = 0; i < links.length; i++){
    if(links[i].href.match(/\/seek\/cache_details\.aspx\?/)){
      var headline = links[i].parentNode.parentNode.parentNode.childNodes[1].innerHTML;
      if(headline){
        var match = links[i].href.match(/\/seek\/cache_details\.aspx\?guid=(.*)/);
        if(match[1]) links[i].parentNode.innerHTML += " <a href='/seek/gallery.aspx?guid="+match[1]+"'><img src='/images/stockholm/16x16/photos.gif'></a>";
      }
    }
  }
}

// Post log from Listing (inline)
if(settings_log_inline && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/)){
  var links = document.getElementsByTagName('a');

  var menu = false;
  var watch = false;
  var gallery = false;
  for(var i = 0; i < links.length; i++){
    if(links[i].href.match(/log\.aspx/) && !menu){
      menu = links[i];
    }else if(links[i].href.match(/gallery\.aspx/) && !gallery){
      gallery = links[i];
    }else if(links[i].href.match(/watchlist\.aspx/) && !watch){
      watch = links[i];
    }
  }
  
//  var heads = document.getElementsByTagName("h3");
//  var head = false;
//  for(var i = 0; i < heads.length; i++){
//    if(heads[i].className == "clear"){
//      head = heads[i];
//      break;
//    }
//  }
  var head = document.getElementById("ctl00_ContentBody_MapLinks_MapLinks").parentNode.parentNode.nextSibling;
  
  function hide_iframe(){
    var frame = document.getElementById('gclhFrame');
    if(frame.style.display == "") frame.style.display = "none";
    else frame.style.display = "";
  }

  if(head && menu){
    var match = menu.href.match(/\?ID=(.*)/);
    if(match && match[1]){
      var iframe = document.createElement("iframe");
      iframe.setAttribute("id","gclhFrame");
      iframe.setAttribute("width","100%");
      iframe.setAttribute("height","600px");
      iframe.setAttribute("style","border: 0px; overflow: auto; display: none;");
      iframe.setAttribute("src","log.aspx?ID="+match[1]+"&gclh=small");

      var a = document.createElement("a");
      a.setAttribute("href","#gclhLogIt");
      a.setAttribute("name","gclhLogIt");
      var img = document.createElement("img");
      img.setAttribute("src",global_log_it_icon);
      img.setAttribute("border","0");
//      a.appendChild(document.createTextNode("Log your visit"));
      a.appendChild(img);
      a.addEventListener("click", hide_iframe, false);

      head.parentNode.insertBefore(a,head);
      head.parentNode.insertBefore(iframe,head);
    }

    var a = document.createElement("a");
    a.setAttribute("href","#gclhLogIt");
    a.setAttribute("class","lnk");
    a.innerHTML = "<img src='/images/stockholm/16x16/comment_add.gif'> <span>Log your visit (inline)</span>";
    a.addEventListener("click", hide_iframe, false);

    var li = document.createElement('li');
    li.appendChild(a);

    var link = false;
    if(gallery) link = gallery;
    else link = watch;
    
    if(link) link.parentNode.parentNode.insertBefore(li,link.parentNode);
  }
}
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?(ID|guid)\=[a-zA-Z0-9-]*\&gclh\=small/)){ // Hide everything to be smart for the iframe :)
  if(document.getElementsByTagName('html')[0]) document.getElementsByTagName('html')[0].style.backgroundColor = "#FFFFFF";

  if(document.getElementsByTagName("header")[0]) document.getElementsByTagName("header")[0].style.display = "none";
   
  if(document.getElementById('ctl00_divBreadcrumbs')) document.getElementById('ctl00_divBreadcrumbs').style.display = "none";

  if(getElementsByClass('BottomSpacing')[0]) getElementsByClass('BottomSpacing')[0].style.display = "none";
  if(getElementsByClass('BottomSpacing')[1]) getElementsByClass('BottomSpacing')[1].style.display = "none";
//  if(document.getElementById('ctl00_ContentBody_LogBookPanel1_WaypointLink')) document.getElementById('ctl00_ContentBody_LogBookPanel1_WaypointLink').parentNode.style.display = 'none';
//  if(document.getElementsByTagName("dt")[0]) document.getElementsByTagName("dt")[0].style.display = "none";

  if(document.getElementById('divAdvancedOptions')) document.getElementById('divAdvancedOptions').style.display = "none";
  if(!settings_log_inline_tb && document.getElementById('ctl00_ContentBody_LogBookPanel1_TBPanel')) document.getElementById('ctl00_ContentBody_LogBookPanel1_TBPanel').style.display = "none";

  if(document.getElementById('ctl00_ContentBody_uxVistOtherListingLabel')) document.getElementById('ctl00_ContentBody_uxVistOtherListingLabel').style.display = "none";
  if(document.getElementById('ctl00_ContentBody_uxVistOtherListingGC')) document.getElementById('ctl00_ContentBody_uxVistOtherListingGC').style.display = "none";
  if(document.getElementById('ctl00_ContentBody_uxVisitOtherListingButton')) document.getElementById('ctl00_ContentBody_uxVisitOtherListingButton').style.display = "none";

  if(document.getElementById('ctl00_divContentSide')) document.getElementById('ctl00_divContentSide').style.display = "none";

  if(document.getElementById('UtilityNav')) document.getElementById('UtilityNav').style.display = "none";

  if(document.getElementsByTagName("footer")[0]) document.getElementsByTagName("footer")[0].style.display = "none";

  function hide_feedback(){
    var button = document.getElementById('uservoice-feedback');
    if(button){
      button.parentNode.removeChild(button);
    }
  }
  window.addEventListener("load", hide_feedback, false);

  if(getElementsByClass('container')[1]) getElementsByClass('container')[1].style.display = "inline";

  var links = document.getElementsByTagName("a");
  for(var i=0; i<links.length; i++){
    links[i].setAttribute("target","_blank");
  }
}

// New Width
if(GM_getValue("settings_new_width") > 0 && GM_getValue("settings_new_width") != 950){
  var width = GM_getValue("settings_new_width");
  var css = ".container { width: "+width+"px; }";
  css += "#Content .container { width: "+width+"px; }";
  css += ".span-24 { width: "+width+"px; }";
  css += ".span-20 { width: "+(width-160)+"px; }";
  css += ".span-16 { width: "+(width-330)+"px; }";
  css += ".span-17 { width: "+(width-280)+"px; }";
  css += ".span-19 { width: "+(width-200)+"px; }";

  var head = document.getElementsByTagName('head')[0];
  var style = document.createElement('style');
  style.type = 'text/css';
  style.innerHTML = css;
  head.appendChild(style);
}

// Show Favourite percentage
if(settings_show_fav_percentage && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details.aspx/)){
  function gclh_load_score(){
    unsafeWindow.showFavoriteScore();

    setTimeout(function(){
      var fav = getElementsByClass('favorite-container')[0];
      if(fav){
        var score = document.getElementById('uxFavoriteScore').innerHTML.match(/<strong>(.*)<\/strong>/);
        if(score[1]){
          var val = getElementsByClass("favorite-value");
          if(val[0]){
            fav.innerHTML = "<span class='favorite-value'> "+val[0].innerHTML+"</span><br>&nbsp;&nbsp;&nbsp;&nbsp;"+score[1]+" &nbsp;&nbsp;&nbsp;&nbsp;<img id='imgFavoriteArrow' src='/images/arrow-down.png' alt='Expand' title='Expand'>";
          }
        }
      }
    },2000);
  }
  if(getElementsByClass('favorite-container')[0]) window.addEventListener("load", gclh_load_score, false);
}

// Show amount of different Coins in public profile
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/profile\//) && document.getElementById('ctl00_ContentBody_ProfilePanel1_lnkCollectibles').className == "Active"){
  var tables = getElementsByClass("Table");
  if(tables){
    var table = tables[0];
    var rows = table.getElementsByTagName("tr");
    var tbs = 0;
    var tbs2 = 0;
    var coins = 0;
    var coins2 = 0;

    for(var i=1; i<(rows.length-1); i++){
      if(rows[i].innerHTML.match(/geocoin/i)){
        coins++;
        coins2 = coins2 + parseInt(rows[i].childNodes[5].innerHTML,10);
      }else{
        tbs++;
        tbs2 = tbs2 + parseInt(rows[i].childNodes[5].innerHTML,10)
      }
    }
    var last = rows[rows.length-1];
//    last.childNodes[1].innerHTML = "<strong>"+(rows.length-2)+"</strong>";
    last.childNodes[5].childNodes[1].innerHTML = "<center>"+last.childNodes[5].childNodes[1].innerHTML+"<br>("+tbs2+"+"+coins2+")</center>";
    last.childNodes[1].innerHTML = "<strong><center>"+(rows.length-2)+"<br>("+tbs+"+"+coins+")</center></strong>";

    var table = tables[1];
    var rows = table.getElementsByTagName("tr");
    var tbs = 0;
    var tbs2 = 0;
    var coins = 0;
    var coins2 = 0;

    for(var i=1; i<(rows.length-1); i++){
      if(rows[i].innerHTML.match(/geocoin/i)){
        coins++;
        coins2 = coins2 + parseInt(rows[i].childNodes[5].innerHTML,10);
      }else{
        tbs++;
        tbs2 = tbs2 + parseInt(rows[i].childNodes[5].innerHTML,10)
      }
    }
    var last = rows[rows.length-1];
//    last.childNodes[1].innerHTML = "<strong>"+(rows.length-2)+"</strong>";
//    last.childNodes[1].innerHTML = "<strong>"+coins+"<br>"+tbs+"</strong>";
    last.childNodes[5].childNodes[1].innerHTML = "<center>"+last.childNodes[5].childNodes[1].innerHTML+"<br>("+tbs2+"+"+coins2+")</center>";
    last.childNodes[1].innerHTML = "<strong><center>"+(rows.length-2)+"<br>("+tbs+"+"+coins+")</center></strong>";
  }
}

// Auto-Visit
if(settings_autovisit && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx/) && !document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/log\.aspx\?LUID=/)){
  function gclh_autovisit_save(){
    var match = this.value.match(/([0-9]*)/);
    if(!this.checked){
      GM_setValue("autovisit_"+match[1],false);
    }else{
      GM_setValue("autovisit_"+match[1],true);
    }
  }

  // Add new option
  var selects = document.getElementsByTagName("select");
  for (var i=0; i < selects.length; i++){
    if(selects[i].id.match(/ctl00_ContentBody_LogBookPanel1_uxTrackables_repTravelBugs_ctl[0-9]*_ddlAction/)){
      var val = selects[i].childNodes[1].value;
      var autovisit = document.createElement("input");
      autovisit.setAttribute("type","checkbox");
      autovisit.setAttribute("id",selects[i].id+"_auto");
      autovisit.setAttribute("value",val);
      if(GM_getValue("autovisit_"+val,false)){
        autovisit.setAttribute("checked","checked");
        selects[i].selectedIndex = 2;
      }
      autovisit.addEventListener("click", gclh_autovisit_save, false);

      selects[i].parentNode.appendChild(autovisit);
      selects[i].parentNode.appendChild(document.createTextNode(" AutoVisit"));

//      var autovisit = document.createElement("option");
//      autovisit.value = val+"_Visited";
//      autovisit.innerHTML = "AutoVisit";
//      selects[i].appendChild(autovisit);
//      autovisit.addEventListener("click", gclh_autovisit_save, false);
//      selects[i].childNodes[1].addEventListener("click", gclh_autovisit_save , false);
    }
  }

  // Select AutoVisit
  function gclh_autovisit(){
    var logtype = document.getElementById("ctl00_ContentBody_LogBookPanel1_ddLogType").value;
    if(logtype == 2 || logtype == 10 || logtype == 11){
      var selects = document.getElementsByTagName("select");
      for (var i=0; i < selects.length; i++){
        if(selects[i].id.match(/ctl00_ContentBody_LogBookPanel1_uxTrackables_repTravelBugs_ctl[0-9]*_ddlAction/)){
          var val = selects[i].childNodes[1].value;
          if(GM_getValue("autovisit_"+val,false)){
            var logoptions = selects[i].getElementsByTagName("option");
            for(var k = 0; k < logoptions.length; k++){
              if(logoptions[k].value == val + "_Visited"){
                selects[i].selectedIndex = k;
                break;
              }
            }
            document.getElementById("ctl00_ContentBody_LogBookPanel1_uxTrackables_hdnSelectedActions").value += val+"_Visited,";
          }
        }
      }
    }else{
      var selects = document.getElementsByTagName("select");
      for (var i=0; i < selects.length; i++){
        if(selects[i].id.match(/ctl00_ContentBody_LogBookPanel1_uxTrackables_repTravelBugs_ctl[0-9]*_ddlAction/) && selects[i].value.match(/_Visited/)){
          selects[i].selectedIndex = 0;
        }
      }
    }
    unsafeWindow.setSelectedActions();
  }

  if(document.getElementById("ctl00_ContentBody_LogBookPanel1_ddLogType")){
    window.addEventListener("load", gclh_autovisit, false);
    document.getElementById("ctl00_ContentBody_LogBookPanel1_ddLogType").addEventListener("click", gclh_autovisit, false);
  }
}

// VIP
if(settings_show_vip_list && getElementsByClass("SignedInProfileLink")[0] && (document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/default\.aspx/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/myfriends\.aspx/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/profile\//))){
  var img_vip_off = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAKCAYAAAC9vt6cAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sHDhEzBX83tZcAAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJTVBXgQ4XAAAAsElEQVQoz6WSsQ2DUAxEz4gdfkdBQQUlDAU9E0ALHQWLsMAfA8o/BNVLkYCS0ETkGstn6+kk2yShPxRLEtxjmJmio8nzXN57SZL3XkVRnEtHNTNlWaZ5nj9AAHRdR9M0ANR1Td/38Iz2UZdlIUmS0zsB67rinGPfd5xzbNt2AUgiTVOmaboCAMqypG1bqqo6ve8E77oAhmEgiiLGcbwHCCEQxzEhhJ8B9hrcPqP9+0gPbh/tf/c8szwAAAAASUVORK5CYII=";
  var img_vip_on = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAKCAYAAAC9vt6cAAAAAXNSR0IArs4c6QAAAAZiS0dEAP8A/wD/oL2nkwAAAAlwSFlzAAALEwAACxMBAJqcGAAAAAd0SU1FB9sHDhE0Aq4StvMAAAAZdEVYdENvbW1lbnQAQ3JlYXRlZCB3aXRoIEdJTVBXgQ4XAAAAzklEQVQoz6WSwQvBcBTHP7/lanFT3DdzV9yw+RNc8E/s6A/YSa6KUrs4u4omB6KUKJoc5a+Q5rRlOCz7Xl7feu/zXu89AXjEUAKgszb/KrbKPSTfDJo2t8MdgNvhzrBlB0l+tMo9+o0R+8kxgASAgqFynrsAnGYumqF+deysTepmhZW9/QZouoLrXHk+nlwWVzRd+TnytOtQahfDOwBI51LImSTLwQo5I5POpn5O8Cnp3WiGyma8o1BXIi8yDKgpCEmQr0YHCMCLc0YR95Fe0bc6eQ97MqYAAAAASUVORK5CYII=";
  var vips = GM_getValue("vips",false);
  if(!vips) vips = new Array();
  else vips = eval(vips);
  var myself = getElementsByClass("SignedInProfileLink")[0].innerHTML;

  // Add to VIP - image
  function gclh_add_vip(){
    var user = trim(this.name);

    vips.push(user);
    vips.sort(caseInsensitiveSort);
    GM_setValue("vips",uneval(vips));

    var icons = document.getElementsByName(user);
    for(var i=0; i<icons.length; i++){
      var img = icons[i].childNodes[0];
      img.setAttribute("src",img_vip_on);
      img.setAttribute("title","Remove User "+user+" from VIP-List");

      icons[i].removeEventListener("click",gclh_add_vip,false);
      icons[i].addEventListener("click",gclh_del_vip,false);
    }

    gclh_build_vip_list();
  }

  function gclh_del_vip(){
    var vips_new = new Array();
    var user = trim(this.name);

    for(var i=0; i<vips.length; i++){
      if(vips[i] != user) vips_new.push(vips[i]);
    }
    vips = vips_new;
    GM_setValue("vips",uneval(vips));

    var icons = document.getElementsByName(user);
    for(var i=0; i<icons.length; i++){
      var img = icons[i].childNodes[0];
      img.setAttribute("src",img_vip_off);
      img.setAttribute("title","Add User "+user+" to VIP-List");

      icons[i].removeEventListener("click",gclh_del_vip,false);
      icons[i].addEventListener("click",gclh_add_vip,false);
    }

    gclh_build_vip_list();
  }

  // Listing
  if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/)){
    var all_users = new Array();
    var log_infos = new Object();
    var log_infos_long = new Array();
    var index = 0;
    var links = document.getElementsByTagName('a');
    for(var i=0; i<links.length; i++){
      if(links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=/) && links[i].parentNode.className != "logOwnerStats" && !links[i].childNodes[0].src){
        if(links[i].id) links[i].name = links[i].id; // To be able to jump to this location
  
        var matches = links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=([a-zA-Z0-9]*)/);
        var user = trim(links[i].innerHTML);
  
        if(links[i].parentNode.className == "minorCacheDetails" && matches && !owner){
          var owner = matches[1];
        }
        if(!owner_name && owner && matches && matches[1] == owner){
          var owner_name = user;
        }
  
//        // Infos for List
//        all_users.push(user);
//        if(!log_infos[user]) log_infos[user] = new Array();
//        log_infos[user][index] = new Object();
//        log_infos_long[index] = new Object();
//        log_infos_long[index]["user"] = user;
//        try {
//          var src = links[i].parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[0].childNodes[0].childNodes[0].src;
//          if(src){
//            log_infos[user][index]["icon"] = src;
//            log_infos_long[index]["icon"] = src;
//            if(links[i].id){
//              log_infos[user][index]["id"] = links[i].id;
//              log_infos_long[index]["id"] = links[i].id;
//            }
//
//            try {
//              var date = links[i].parentNode.parentNode.parentNode.parentNode.childNodes[1].childNodes[1].childNodes[0].textContent;
//              if(date){
//                log_infos[user][index]["date"] = date;
//                log_infos_long[index]["date"] = date;
//              }
//            } catch (e) { }
//          }
//        } catch(e) { }
  
        // Icon
        var link = document.createElement("a");
        var img = document.createElement("img");
        img.setAttribute("border","0");
        link.appendChild(img);
        link.setAttribute("href","javascript:void(0);");
        link.setAttribute("name",user);
  
        if(in_array(user,vips)){
          img.setAttribute("src",img_vip_on);
          img.setAttribute("title","Remove User "+user+" from VIP-List");
          link.addEventListener("click",gclh_del_vip,false);
        }else{
          img.setAttribute("src",img_vip_off);
          img.setAttribute("title","Add User "+user+" to VIP-List");
          link.addEventListener("click",gclh_add_vip,false);
        } 
  
        links[i].parentNode.appendChild(document.createTextNode("   "));
        links[i].parentNode.appendChild(link);
  
//        index++;
      }
    }
  
    // Show VIP List
    var map = document.getElementById("lnkSmallMap").parentNode;
    var box = document.createElement("div");
    var headline = document.createElement("h3");
    var body = document.createElement("div");
    box.setAttribute("class","CacheDetailNavigationWidget NoPrint");
    headline.setAttribute("class","WidgetHeader");
    body.setAttribute("class","WidgetBody");
    body.setAttribute("id","gclh_vip_list");
    headline.innerHTML = "<img width=\"16\" height=\"16\" title=\"VIP-List\" alt=\"VIP-List\" src=\"http://www.geocaching.com/images/icons/icon_attended.gif\"> VIP-List";
    box.appendChild(headline);
    box.appendChild(body);
    box.innerHTML = "<br>"+box.innerHTML;
    map.parentNode.insertBefore(box,map);

    var css = "a.gclh_log:hover { " +
      "  text-decoration:underline;" +
      "  position: relative;" +
      "}" +
      "a.gclh_log span {" +
      "  visibility: hidden;" +
      "  position: absolute;" +
      "  top:-310px;" +
      "  left:-705px;" +
      "  width: 700px;" +
      "  padding: 2px;" +
      "  text-decoration:none;" +
      "  text-align:left;" +
      "  vertical-align:top;" +
//      "  white-space: nowrap;" +
      "  color: #000000;" +
      "}" +
      "a.gclh_log:hover span { " +
      "  visibility: visible;" +
      "  top: 10px;" +
      "  border: 1px solid #8c9e65;" +
      "  background-color:#dfe1d2;" +
      "}";
    GM_addStyle(css);
  
    function gclh_build_vip_list(){
      var show_owner = true;
      var list = document.getElementById("gclh_vip_list");
      list.innerHTML = "";

      function gclh_build_long_list(){
        for(var i=0; i<log_infos_long.length; i++){
          var user = log_infos_long[i]["user"];
          if(in_array(user,vips) || user == owner_name){
            if(!log_infos_long[i]["date"]) continue;

            var span = document.createElement("span");
            var profile = document.createElement("a");
            profile.setAttribute("href","http://www.geocaching.com/profile/?u="+user);
            profile.innerHTML = user;
            if(owner_name && owner_name == user){
              profile.style.color = '#8C0B0B';
            }else if(user == myself){
              profile.style.color = '#778555';
            }
            span.appendChild(profile);

            // VIP-Link
            var link = document.createElement("a");
            var img = document.createElement("img");
            img.setAttribute("border","0");
            link.appendChild(img);
            link.setAttribute("href","javascript:void(0);");
            link.setAttribute("name",user);
  
            if(owner_name && owner_name == user && !in_array(user,vips)){
              img.setAttribute("src",img_vip_off);
              img.setAttribute("title","Add User "+user+" to VIP-List");
              link.addEventListener("click",gclh_add_vip,false);
            }else{
              img.setAttribute("src",img_vip_on);
              img.setAttribute("title","Remove User "+user+" from VIP-List");
              link.addEventListener("click",gclh_del_vip,false);
            }
  
            // Log-Date and Link
            var log_text = document.createElement("span");
            log_text.innerHTML = log_infos_long[i]["log"];
            var log_img = document.createElement("img");
            var log_link = document.createElement("a");
            log_link.setAttribute("href","#"+log_infos_long[i]["id"]);
            log_link.className = "gclh_log";
            log_img.setAttribute("src",log_infos_long[i]["icon"]);
            log_img.setAttribute("border","0");
            log_link.appendChild(document.createTextNode(log_infos_long[i]["date"]));
            log_link.appendChild(log_text);

            list.appendChild(log_img);
            list.appendChild(document.createTextNode("   "));
            list.appendChild(log_link);
            list.appendChild(document.createTextNode("   "));
            list.appendChild(span);
            list.appendChild(document.createTextNode("   "));
            list.appendChild(link);

            list.appendChild(document.createElement("br"));
          }
        }
      }
  
      function gclh_build_list(user){
        if(!show_owner && owner_name && owner_name == user) return true;
        if(in_array(user,all_users) || (owner_name == user)){
          var span = document.createElement("span");
          var profile = document.createElement("a");
          profile.setAttribute("href","http://www.geocaching.com/profile/?u="+user);
          profile.innerHTML = user;
          if(show_owner && owner_name && owner_name == user){
            span.appendChild(document.createTextNode("Owner: "));
            show_owner = false;
          }else if(user == myself){
            span.appendChild(document.createTextNode("Me: "));
          }
          span.appendChild(profile);
  
          // VIP-Link
          var link = document.createElement("a");
          var img = document.createElement("img");
          img.setAttribute("border","0");
          link.appendChild(img);
          link.setAttribute("href","javascript:void(0);");
          link.setAttribute("name",user);

          if(owner_name && owner_name == user && !in_array(user,vips)){
            img.setAttribute("src",img_vip_off);
            img.setAttribute("title","Add User "+user+" to VIP-List");
            link.addEventListener("click",gclh_add_vip,false);
          }else{
            img.setAttribute("src",img_vip_on);
            img.setAttribute("title","Remove User "+user+" from VIP-List");
            link.addEventListener("click",gclh_del_vip,false);
          }
  
          list.appendChild(span);
          list.appendChild(document.createTextNode("   "));
          list.appendChild(link);
  
          // Log-Links
          for(var x=0; x<log_infos[user].length; x++){
            if(log_infos[user][x] && log_infos[user][x]["icon"] && log_infos[user][x]["id"]){
              var image = document.createElement("img");
              var log_text = document.createElement("span");
              log_text.innerHTML = log_infos[user][x]["log"];
              image.setAttribute("src",log_infos[user][x]["icon"]);
              image.setAttribute("border","0");
  
              if(log_infos[user][x]["date"]){
                image.setAttribute("title",log_infos[user][x]["date"]);
                image.setAttribute("alt",log_infos[user][x]["date"]);
              }
  
              var a = document.createElement("a");
              a.setAttribute("href","#"+log_infos[user][x]["id"]);
              a.className = "gclh_log";
              a.appendChild(image); 
              a.appendChild(log_text);
             
              list.appendChild(document.createTextNode(" "));
              list.appendChild(a);
            }
          }
  
          list.appendChild(document.createElement("br"));
        }
      }
  
      if(settings_show_long_vip){
        gclh_build_long_list();
      }else{
        if(owner_name){
          log_infos[owner_name] = new Array();
          gclh_build_list(owner_name);
        }
        for(var i=0; i<vips.length; i++){
          gclh_build_list(vips[i]);
        }
      }

 
//      if(!window.location.href.match(/log=y/)){
//        var advice = document.createElement("div");
//        advice.setAttribute("style","background-color: #FCE6A4; width: 100%; padding: 2px; text-align: center;");
//        advice.innerHTML = "this list is not complete<br>please load <a href='"+(window.location.href.replace(/#/,""))+"&log=y'>all</a> logs";
//        list.appendChild(document.createElement("br"));
//        list.appendChild(advice);
//      }
    }
    gclh_build_vip_list();
  // Listing (All-VIP-List)
  }else if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/default\.aspx/) && document.getElementById("ctl00_ContentBody_uxBanManWidget")){
    var widget = document.createElement("div");
    var headline = document.createElement("h3");
    var box = document.createElement("div");

    widget.setAttribute("class","YourProfileWidget");
    headline.setAttribute("class","WidgetHeader");
    headline.appendChild(document.createTextNode("All my VIPs"));
    box.setAttribute("id","box_vips");
    box.setAttribute("class","WidgetBody");

    widget.appendChild(headline);
    widget.appendChild(box);
    document.getElementById("ctl00_ContentBody_uxBanManWidget").parentNode.insertBefore(widget,document.getElementById("ctl00_ContentBody_uxBanManWidget"));

    function gclh_build_vip_list(){
      var box = document.getElementById("box_vips");
      if(!box) return false;
      box.innerHTML = "";

      for(var i=0; i<vips.length; i++){
        var user = vips[i];
        var span = document.createElement("span");
        var profile = document.createElement("a");
        profile.setAttribute("href","http://www.geocaching.com/profile/?u="+user);
        profile.innerHTML = user;
        span.appendChild(profile);
  
        // VIP-Link
        var link = document.createElement("a");
        var img = document.createElement("img");
        img.setAttribute("border","0");
        link.appendChild(img);
        link.setAttribute("href","javascript:void(0);");
        link.setAttribute("name",user);
        img.setAttribute("src",img_vip_on);
        img.setAttribute("title","Remove User "+user+" from VIP-List");
        link.addEventListener("click",gclh_del_vip,false);
  
        box.appendChild(span);
        box.appendChild(document.createTextNode("   "));
        box.appendChild(link);
        box.appendChild(document.createElement("br"));
      }
    }
    gclh_build_vip_list();
  }else if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/myfriends\.aspx/)){
  // Friendlist - VIP-Icon
    function gclh_build_vip_list(){} // There is no list to show, but ths function will be called from gclh_del_vip/gclh_add_vip
    var links = document.getElementsByTagName('a');
    for(var i=0; i<links.length; i++){
      if(links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=/) && links[i].id){
        // VIP-Link
        var user = links[i].innerHTML;
        var link = document.createElement("a");
        var img = document.createElement("img");
        img.setAttribute("border","0");
        link.appendChild(img);
        link.setAttribute("href","javascript:void(0);");
        link.setAttribute("name",user);

        if(in_array(user,vips)){
          img.setAttribute("src",img_vip_on);
          img.setAttribute("title","Remove User "+user+" from VIP-List");
          link.addEventListener("click",gclh_del_vip,false);
        }else{
          img.setAttribute("src",img_vip_off);
          img.setAttribute("title","Add User "+user+" to VIP-List");
          link.addEventListener("click",gclh_add_vip,false);
        }

        links[i].parentNode.appendChild(document.createTextNode("   "));
        links[i].parentNode.appendChild(link);
      }
    }
  }else if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/profile\//) && document.getElementById("ctl00_ContentBody_ProfilePanel1_lblMemberName")){
  // Public Profile - VIP-Icon
    function gclh_build_vip_list(){} // There is no list to show, but ths function will be called from gclh_del_vip/gclh_add_vip
    var user = trim(document.getElementById("ctl00_ContentBody_ProfilePanel1_lblMemberName").innerHTML);
    var link = document.createElement("a");
    var img = document.createElement("img");
    img.setAttribute("border","0");
    link.appendChild(img);
    link.setAttribute("href","javascript:void(0);");
    link.setAttribute("name",user);

    if(in_array(user,vips)){
      img.setAttribute("src",img_vip_on);
      img.setAttribute("title","Remove User "+user+" from VIP-List");
      link.addEventListener("click",gclh_del_vip,false);
    }else{
      img.setAttribute("src",img_vip_off);
      img.setAttribute("title","Add User "+user+" to VIP-List");
      link.addEventListener("click",gclh_add_vip,false);
    }

    document.getElementById("ctl00_ContentBody_ProfilePanel1_lblMemberName").appendChild(document.createTextNode("   "));
    document.getElementById("ctl00_ContentBody_ProfilePanel1_lblMemberName").appendChild(link);
  }
}

//// Show "top"-Link in Logs
//if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/)){
//  var a = document.createElement("a");
//  a.setAttribute("href","#");
//  a.setAttribute("name","gclh_top");
//  document.getElementsByTagName("body")[0].insertBefore(a,document.getElementsByTagName("body")[0].childNodes[0]);
//  var links = document.getElementsByTagName('a');
//  for(var i=0; i<links.length; i++){
//    if(links[i].href.match(/http:\/\/www\.geocaching\.com\/profile\/\?guid=/) && links[i].id){
//      var link = document.createElement("a");
//      link.innerHTML = "↑";
//      link.setAttribute("title","Top");
//      link.setAttribute("href","#gclh_top");
//      link.setAttribute("style","color: #000000; text-decoration: none;");
//      links[i].parentNode.appendChild(document.createTextNode("   "));
//      links[i].parentNode.appendChild(link);      
//    }
//  }
//}

// Show Bookmark-It Icon
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/nearest\.aspx?/)){
  var links = document.getElementsByTagName("a");

  for(var i=0; i<links.length; i++){
    if(links[i].href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx\?.*/) && links[i].innerHTML.match(/^<span>/)){
      var wpt = 2;
      try{
        var match = links[i].parentNode.previousSibling.childNodes[1].childNodes[0].src.match(/([0-9]*)\.gif/);
        if(match[1]) wpt = match[1];
      }catch(e) { }
      links[i].parentNode.innerHTML = links[i].parentNode.innerHTML.replace("<br>","&nbsp;<a title='Bookmark it' href='"+links[i].href.replace("seek\/cache_details","bookmarks\/mark")+"&WptTypeID="+wpt+"'><img src='/images/stockholm/16x16/book_open_mark.gif'></a><br>");
    }
  }  
}

// Highlight related web page link
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/) && document.getElementById("ctl00_ContentBody_uxCacheUrl")){
  var lnk = document.getElementById("ctl00_ContentBody_uxCacheUrl");

  var html = "<fieldset class=\"DisclaimerWidget\">";
  html += "  <legend class=\"warning\">Please note</legend>";
  html += "  <p class=\"NoBottomSpacing\">";
  html += lnk.parentNode.innerHTML;
  html += "  </p>";
  html += "</fieldset>";

  lnk.parentNode.innerHTML = html;
}

// Show thumbnails
if(settings_show_thumbnails && document.location.href.match(/^http:\/\/www\.geocaching\.com\/(seek\/cache_details\.aspx?|seek\/gallery\.aspx?|profile\/)/)){
  var links = document.getElementsByTagName("a");
  
  var css = "a.gclh_thumb:hover { " + 
    "  text-decoration:underline;" +
    "  position: relative;" +
    "}" +
    "a.gclh_thumb span {" +
    "  visibility: hidden;" +
    "  position: absolute;" +
    "  top:-310px;" +
    "  left:0px;" +
    "  padding: 2px;" +
    "  text-decoration:none;" +
    "  text-align:left;" +
    "  vertical-align:top;" +
    "}" +
    "a.gclh_thumb:hover span { " +
    "  visibility: visible;" +
    "  top: 10px;" + 
    "  border: 1px solid #8c9e65;" +
    "  background-color:#dfe1d2;" +
    "}";

  GM_addStyle(css);

  if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/)){
    var newImageTmpl = "" +
//    "<tr>" +
//    "      <td>" +
    "          <a class='tb_images lnk gclh_thumb' rel='tb_images[grp${LogID}]' href='http://img.geocaching.com/cache/log/${FileName}' title='${Descr}'>" +
    "              <img title='${Name}' alt='${Name}' src='http://img.geocaching.com/cache/log/thumb/${FileName}'>" +
    "              <span><img src='http://img.geocaching.com/cache/log/${FileName}'> ${Name}</span>" +
    "          </a>&nbsp;&nbsp;" +
//    "      </td>" +
//    "  </tr>";
    "";

    var code = "function gclh_updateTmpl() { " +
    "  delete $.template['tmplCacheLogImages'];" +
    "  $.template(\"tmplCacheLogImages\",\""+newImageTmpl+"\");" +
    "}";

    var script = document.createElement("script");
    script.innerHTML = code;
    document.getElementsByTagName("body")[0].appendChild(script);

    unsafeWindow.gclh_updateTmpl();
  }

  var tds = new Array();
  for(var i=0; i<links.length; i++){
    if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/) && links[i].href.match(/^http:\/\/img\.geocaching\.com\/cache/) && !links[i].innerHTML.match(/(spoiler|hinweis)/i)){
      var thumb = links[i].childNodes[0];
      var span = links[i].childNodes[1];
      if(!thumb || !span) continue;
      if(links[i].href.match(/cache\/log/)){
        thumb.src = links[i].href.replace(/cache\/log/,"cache/log/thumb");
      }else{
        thumb.src = links[i].href;
        thumb.style.height = "100px";
      }
      thumb.title = span.innerHTML;
      thumb.alt = span.innerHTML;
      
      links[i].className = links[i].className+" gclh_thumb";

      var big_img = document.createElement("img");
      big_img.src = links[i].href;

      span.insertBefore(big_img,span.childNodes[0]);

      links[i].parentNode.removeChild(links[i].nextSibling);
//    }else if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/gallery\.aspx?/) && links[i].href.match(/^http:\/\/www\.geocaching\.com\/seek\//) && links[i].childNodes[0] && links[i].childNodes[0].tagName == 'IMG'){
    }else if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/(seek\/gallery\.aspx?|profile\/)/) && links[i].href.match(/^http:\/\/img\.geocaching\.com\/(cache|track)\//) && links[i].childNodes[1] && links[i].childNodes[1].tagName == 'IMG'){
      var thumb = links[i].childNodes[1];
      var span = document.createElement('span');
      var img = document.createElement('img');

      // Bigger Images in Gallery
      if(settings_show_big_gallery){
        thumb.style.width = "300px";
        thumb.style.height = "auto";
        thumb.src = thumb.src.replace(/thumb\//,"");
        tds.push(thumb.parentNode.parentNode);
      }

      img.src = thumb.src.replace(/thumb\//,"");
      span.appendChild(img);
      span.appendChild(document.createTextNode(thumb.parentNode.parentNode.childNodes[7].childNodes[0].innerHTML));
      
      links[i].className = links[i].className+" gclh_thumb";

      links[i].appendChild(span);
    }
  }

  // Show gallery-Images in 2 instead of 4 cols
  if(settings_show_big_gallery && document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/gallery\.aspx?/) && tds.length > 0 && document.getElementById("ctl00_ContentBody_GalleryItems_DataListGallery")){
    var tbody = document.createElement("tbody");
    var tr = document.createElement("tr");
    var x = 0;
    for(var i=0; i<tds.length; i++){
      if(x == 0){
        tr.appendChild(tds[i]);
        x++;
      }else{
        tr.appendChild(tds[i]);
        tbody.appendChild(tr);
        tr = document.createElement("tr");
        x = 0;
      }
    }
    document.getElementById("ctl00_ContentBody_GalleryItems_DataListGallery").removeChild(document.getElementById("ctl00_ContentBody_GalleryItems_DataListGallery").firstChild);
    document.getElementById("ctl00_ContentBody_GalleryItems_DataListGallery").appendChild(tbody);
  }else if(settings_show_big_gallery && document.location.href.match(/^http:\/\/www\.geocaching\.com\/profile\//) && tds.length > 0 && document.getElementById("ctl00_ContentBody_ProfilePanel1_UserGallery_DataListGallery")){
    var tbody = document.createElement("tbody");
    var tr = document.createElement("tr");
    var x = 0;
    for(var i=0; i<tds.length; i++){
      if(x == 0){
        tr.appendChild(tds[i]);
        x++;
      }else{
        tr.appendChild(tds[i]);
        tbody.appendChild(tr);
        tr = document.createElement("tr");
        x = 0;
      }
    }
    document.getElementById("ctl00_ContentBody_ProfilePanel1_UserGallery_DataListGallery").removeChild(document.getElementById("ctl00_ContentBody_ProfilePanel1_UserGallery_DataListGallery").firstChild);
    document.getElementById("ctl00_ContentBody_ProfilePanel1_UserGallery_DataListGallery").appendChild(tbody);
  }
}

// Log-Template definieren
//if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_logbook\.aspx/)){
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/)){
  var new_tmpl = '<tr class="log-row" data-encoded="${IsEncoded}" >' +
    '        <td>' +
    '            <div class="FloatLeft LogDisplayLeft" >' +
    '                <p class="logOwnerProfileName">' +
    '                    <strong><a id="${LogID}" name="${LogID}" href="/profile/?guid=${AccountGuid}">${UserName}</a>';

  if(settings_show_mail) new_tmpl += ' <a href="http://www.geocaching.com/email/?guid=${AccountGuid}&text='+global_cache_name+'"><img border=0 title="Send a mail to this user" src="'+global_mail_icon+'"></a>';
  if(settings_show_vip_list) new_tmpl += ' <a href="javascript:void(0);" name="${UserName}" class="gclh_vip"><img class="gclh_vip" border=0></a>';

  new_tmpl += '&nbsp;&nbsp;<a title="Top" href="#gclh_top" style="color: #000000; text-decoration: none;">↑</a>';
  
  new_tmpl += '          </strong></p>' +
    '                <p class="logOwnerBadge">' + 
    '                    {{if creator}}<img title="${creator.GroupTitle}" src="${creator.GroupImageUrl}" align="absmiddle" style="vertical-align:middle">${creator.GroupTitle}{{/if}}' +
    '                </p>' +
    '                <p class="logOwnerAvatar">' +
    '                    <a href="/profile/?guid=${AccountGuid}">';

  if(!settings_hide_avatar){
    new_tmpl += '            {{if AvatarImage}}' +
    '                        <img width="48" height="48" src="http://img.geocaching.com/user/avatar/${AvatarImage}">' +
    '                        {{else}}' +
    '                        <img width="48" height="48" src="/images/default_avatar.jpg">' +
    '                        {{/if}}';
  }

  new_tmpl += '          </a></p>' +
    '                <p class="logOwnerStats">' +
    '                    {{if GeocacheFindCount > 0 }}' +
    '                      <img title="Caches Found" src="/images/icons/icon_smile.png"> ${GeocacheFindCount}' +
    '                    {{/if}}' +
    '                    {{if GeocacheFindCount > 0 && ChallengesCompleted > 0 }}' +
    '                      &nbsp;·&nbsp;' +
    '                    {{/if}}' +
    '                    {{if ChallengesCompleted > 0 }}' +
    '                      <img title="Challenges Completed" src="/images/challenges/types/sm/challenge.png"> ${ChallengesCompleted}' +
    '                    {{/if}}' +
    '                </p>' +
    '            </div>' +
    '            <div class="FloatLeft LogDisplayRight">' +
    '                <div class="HalfLeft LogType">' +
    '                    <strong>' +
    '                        <img title="${LogType}" alt="${LogType}" src="/images/icons/${LogTypeImage}">&nbsp;${LogType}</strong></div>' +
    '                <div class="HalfRight AlignRight">' +
    '                    <span class="minorDetails LogDate">${Visited}</span></div>' +
    '                <div class="Clear LogContent">' +
    '                    {{if LatLonString.length > 0}}' +
    '                    <strong>${LatLonString}</strong>' +
    '                    {{/if}}' +
    '                    <p class="LogText">{{html LogText}}</p>' +
    '                    {{if Images.length > 0}}' +
    '                        <table cellspacing="0" cellpadding="3" class="LogImagesTable">';
  if(settings_show_thumbnails) new_tmpl += '<tr><td>';
  new_tmpl += '              {{tmpl(Images) "tmplCacheLogImages"}}';
  if(settings_show_thumbnails) new_tmpl += '</td></tr>';
  new_tmpl +=  '             </table>' +
    '                    {{/if}}' +
    '                    <div class="AlignRight">' +
    '                        <small><a title="View Log" href="log.aspx?LUID=${LogGuid}" target="_blank">' +
    '                            {{if (userInfo.ID==AccountID)}}' +
    '                               View / Edit Log / Images' +
    '                            {{else}}' +
    '                               View Log' +
    '                            {{/if}}' +
    '                        </a></small>&nbsp;' +
    '                        {{if (userInfo.ID==AccountID)}}' +
    '                        <small><a title="Upload Image" href="upload.aspx?LID=${LogID}" target="_blank">Upload Image</a></small>' +
    '                        {{/if}}' +
    '                    </div>' +
    '                </div>' +
    '            </div>' +
    '        </td>' +
    '    </tr>';
}

// Overwrite Log-Template and Log-Load-Function
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx?/)){
  // to Top Link
  var a = document.createElement("a");
  a.setAttribute("href","#");
  a.setAttribute("name","gclh_top");
  document.getElementsByTagName("body")[0].insertBefore(a,document.getElementsByTagName("body")[0].childNodes[0]);

  var new_tmpl_block = document.createElement("script");
  new_tmpl_block.innerHTML = new_tmpl;
  new_tmpl_block.setAttribute("id","tmpl_CacheLogRow_gclh");
  document.getElementsByTagName("body")[0].appendChild(new_tmpl_block);

  // disable Function on Page
  unsafeWindow.currentPageIdx = 2;
  unsafeWindow.totalPages = 1;
  unsafeWindow.isBusy = true;
  unsafeWindow.initalLogs = initalLogs = {"status":"success", "data": [], "pageInfo": { "idx":2, "size": 0, "totalRows": 1, "totalPages": 1, "rows": 1 } };
  // Hide initial Logs
  var tbodys = document.getElementById("cache_logs_table").getElementsByTagName("tbody");
  for(var i=0; i<tbodys.length; i++){
    document.getElementById("cache_logs_table").removeChild(tbodys[i]);
  }

  // Helper: Add VIP-Icon
  function gclh_add_vip_icon(){
   for(var i = 0; i < document.getElementById("cache_logs_table").getElementsByTagName("a").length; i++){
      if(document.getElementById("cache_logs_table").getElementsByTagName("a")[i].className == "gclh_vip"){
        var link = document.getElementById("cache_logs_table").getElementsByTagName("a")[i];
        var img = link.childNodes[0];
        var user = link.name;

        if(in_array(user,vips)){
          img.src = img_vip_on;
          img.title = "Remove User "+user+" from VIP-List";
          link.addEventListener("click",gclh_del_vip,false);
        }else{
          img.src = img_vip_off;
          img.title = "Add User "+user+" to VIP-List";
          link.addEventListener("click",gclh_add_vip,false);
        }
      }
    }
  }
  
  // Rebuild function - but with full control :)
  function gclh_dynamic_load(logs,num){
    var isBusy = false;
    var gclh_currentPageIdx = 1, gclh_totalPages = 1;
    var logInitialLoaded = false;
    unsafeWindow.$(window).endlessScroll({
      fireOnce: true,
      fireDelay: 500,
      bottomPixels: ($(document).height() - $("#cache_logs_container").offset().top) + 50,
      ceaseFire: function(){
        // stop the scrolling if the last page is reached.
        return (gclh_totalPages < gclh_currentPageIdx);
      },
      callback: function() {
        if (!isBusy && !document.getElementById("gclh_all_logs_marker")) {
          isBusy = true;
          unsafeWindow.$tfoot.show();
          
          for(var i=0; i<10; i++){
            num++; // num kommt vom vorherigen laden "aller" logs
            if(logs[num]){
              var newBody = unsafeWindow.$(document.createElement("TBODY"));
              unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs[num]).appendTo(newBody);
              newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
              unsafeWindow.$("#cache_logs_table").append(newBody.children());
            }
          }

          gclh_add_vip_icon();
                
          unsafeWindow.$tfoot.hide();
          isBusy = false;
        }
      }
    });
  }

  // Load all Logs-Link
  function gclh_load_all_link(logs){
    function gclh_load_all_logs(){
      if(logs){
        var tbodys = document.getElementById("cache_logs_table").getElementsByTagName("tbody");
        for(var i=0; i<tbodys.length; i++){
          document.getElementById("cache_logs_table").removeChild(tbodys[i]);
        }

        for(var i=0; i<logs.length; i++){
          if(logs[i]){
            var newBody = unsafeWindow.$(document.createElement("TBODY"));
            unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs[i]).appendTo(newBody);
            newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
            unsafeWindow.$("#cache_logs_table").append(newBody.children());
          }
        }

        gclh_add_vip_icon();

        // Marker to disable dynamic log-load
        var marker = document.createElement("a");
        marker.setAttribute("id","gclh_all_logs_marker");
        document.getElementsByTagName("body")[0].appendChild(marker);
      }
    }
  
    var load_all = document.createElement("a");
    load_all.appendChild(document.createTextNode("Show all logs"));
    load_all.setAttribute("href","javascript:void(0);");
    document.getElementById("ctl00_ContentBody_uxLogbookLink").parentNode.appendChild(document.createTextNode(" | "));
    document.getElementById("ctl00_ContentBody_uxLogbookLink").parentNode.appendChild(load_all);
  
    load_all.addEventListener("click",gclh_load_all_logs,false);
  }

  // Filter Log-Types
  function gclh_filter_logs(logs){
    function gclh_filter_logs(){
      if(!this.childNodes[0]) return false;
      var log_type = this.childNodes[0].title;
      if(!log_type) return false;
      if(!logs) return false;

      var tbodys = document.getElementById("cache_logs_table").getElementsByTagName("tbody");
      for(var i=0; i<tbodys.length; i++){
        document.getElementById("cache_logs_table").removeChild(tbodys[i]);
      }
 
      for(var i=0; i<logs.length; i++){
        if(logs[i] && logs[i].LogType == log_type){
          var newBody = unsafeWindow.$(document.createElement("TBODY"));
          unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs[i]).appendTo(newBody);
          newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
          unsafeWindow.$("#cache_logs_table").append(newBody.children());
        }
      }
 
      gclh_add_vip_icon();
 
      // Marker to disable dynamic log-load
      var marker = document.createElement("a");
      marker.setAttribute("id","gclh_all_logs_marker");
      document.getElementsByTagName("body")[0].appendChild(marker);
    }
 
    if(!document.getElementById("ctl00_ContentBody_lblFindCounts").childNodes[0]) return false;
    var legend = document.getElementById("ctl00_ContentBody_lblFindCounts").childNodes[0];
    var new_legend = document.createElement("p");
    new_legend.className = "LogTotals";

    for(var i=0; i<legend.childNodes.length; i++){
      if(legend.childNodes[i].tagName == "IMG"){
        var link = document.createElement("a");
        link.setAttribute("href","javascript:void(0);");
        link.style.textDecoration = 'none';
        link.addEventListener("click",gclh_filter_logs,false);
      
        link.appendChild(legend.childNodes[i].cloneNode(true));
        i++;
        link.appendChild(legend.childNodes[i].cloneNode(true));
        new_legend.appendChild(link);
      }
    } 
    document.getElementById('ctl00_ContentBody_lblFindCounts').replaceChild(new_legend,legend);
  }

  function gclh_search_logs(logs){
    function gclh_search(e){
      if(e.keyCode != 13) return false;
      if(!logs) return false;
      var search_text = this.value;
      if(!search_text) return false;

      search_text = search_text.replace(/Ä/,"&#xC4;");
      search_text = search_text.replace(/ä/,"&#xE4;");
      search_text = search_text.replace(/Ö/,"&#xD6;");
      search_text = search_text.replace(/ö/,"&#xF6;");
      search_text = search_text.replace(/Ü/,"&#xDC;");
      search_text = search_text.replace(/ü/,"&#xFC;");
      var regexp = new RegExp("("+search_text+")","i");

      var tbodys = document.getElementById("cache_logs_table").getElementsByTagName("tbody");
      for(var i=0; i<tbodys.length; i++){
        document.getElementById("cache_logs_table").removeChild(tbodys[i]);
      }

      for(var i=0; i<logs.length; i++){
        if(logs[i] && (logs[i].UserName.match(regexp) || logs[i].LogText.match(regexp))){
          var newBody = unsafeWindow.$(document.createElement("TBODY"));
          unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs[i]).appendTo(newBody);
          newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
          unsafeWindow.$("#cache_logs_table").append(newBody.children());
        }
      }

      gclh_add_vip_icon();

      // Marker to disable dynamic log-load
      var marker = document.createElement("a");
      marker.setAttribute("id","gclh_all_logs_marker");
      document.getElementsByTagName("body")[0].appendChild(marker);
    }

    if(!document.getElementById("ctl00_ContentBody_lblFindCounts").childNodes[0]) return false;
    var form = document.createElement("form");
    var search = document.createElement("input");
    form.setAttribute("action","javascript:void(0);");
    form.appendChild(search);
    form.style.display = "inline";
    search.setAttribute("type","text");
    search.setAttribute("size","10");
    search.addEventListener("keyup",gclh_search,false);
    document.getElementById('ctl00_ContentBody_lblFindCounts').childNodes[0].appendChild(document.createTextNode("Search in logtext: "));
    document.getElementById('ctl00_ContentBody_lblFindCounts').childNodes[0].appendChild(form);
  }
  
  // Load "num" Logs
  function gclh_load_logs(num){
    var logs = new Array();
    var numPages = 1;
    var curIdx = 1;

    if(document.getElementById("gclh_vip_list")){
      var span_loading = document.createElement("span");
      span_loading.innerHTML = '<img src="/images/loading2.gif" class="StatusIcon" alt="Loading" />Loading Cache Logs...';
      document.getElementById("gclh_vip_list").appendChild(span_loading);
    }
    
    function gclh_load_helper(){
      if(numPages >= curIdx){
      unsafeWindow.$tfoot.show();
        GM_xmlhttpRequest({
          method: "GET",
          url: "/seek/geocache.logbook?tkn="+unsafeWindow.userToken+"&idx="+curIdx+"&num=100&decrypt=false",
          onload: function(response){
            var json = JSON.parse(response.responseText);
            if(numPages == 1){
              numPages = json.pageInfo.totalPages;
            }

            unsafeWindow.$tfoot.hide();
            
            logs = logs.concat(json.data);
            
            curIdx++;
            
            for(var i = 0; i < json.data.length; i++){
              var user = json.data[i].UserName;
                            
              if(settings_show_vip_list){
                all_users.push(user);
              
                if(!log_infos[user]) log_infos[user] = new Array();
                log_infos[user][index] = new Object();
                log_infos[user][index]["icon"] = "/images/icons/"+json.data[i].LogTypeImage;
                log_infos[user][index]["id"] = json.data[i].LogID;
                log_infos[user][index]["date"] = json.data[i].Visited;
                log_infos[user][index]["log"] = json.data[i].LogText;
                log_infos_long[index] = new Object();
                log_infos_long[index]["user"] = user;
                log_infos_long[index]["icon"] = "/images/icons/"+json.data[i].LogTypeImage;
                log_infos_long[index]["id"] = json.data[i].LogID;
                log_infos_long[index]["date"] = json.data[i].Visited;
                log_infos_long[index]["log"] = json.data[i].LogText;
                index++;
              }
            }
            
            if(json.pageInfo.totalRows > logs.length){
              gclh_load_helper();
            }else{
              // Add Links
              gclh_load_all_link(logs); // Load all Logs
              gclh_filter_logs(logs); // Filter Logs
              gclh_search_logs(logs); // Search Field

              if(num == 0){
                var newBody = unsafeWindow.$(document.createElement("TBODY"));
                unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs).appendTo(newBody);
                newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
                unsafeWindow.$("#cache_logs_table").append(newBody.children());
              }else{
                for(var i=0; i<num; i++){
                  if(logs[i]){
                    var newBody = unsafeWindow.$(document.createElement("TBODY"));
                    unsafeWindow.$("#tmpl_CacheLogRow_gclh").tmpl(logs[i]).appendTo(newBody);
                    newBody.find("a.tb_images").fancybox({'type': 'image', 'titlePosition': 'inside'});
                    unsafeWindow.$("#cache_logs_table").append(newBody.children());
                  }
                }
                gclh_dynamic_load(logs,num);
              }

              if(settings_show_vip_list){
                gclh_build_vip_list();
                
              gclh_add_vip_icon();
              }
            }
          }
        });
      }
    }
    
    gclh_load_helper();
  }
  
  if(settings_show_all_logs) gclh_load_logs(settings_show_all_logs_count);
  else gclh_load_logs(5);
}

// Show Icons at Logbook
//if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_logbook\.aspx/)){
//  var new_tmpl_block = document.createElement("script");
//  new_tmpl_block.innerHTML = "function gclh_change_tmpl() {"+
//  "delete $.template(\"tmplCacheLogRow\");"+
//  "$(\"#tmpl_CacheLogRow\").template(\"tmplCacheLogRow\");"+
//  "}";
//  new_tmpl_block.setAttribute("id","tmpl_CacheLogRow_gclh");
//  document.getElementsByTagName("body")[0].appendChild(new_tmpl_block);
//
//  document.getElementById('tmpl_CacheLogRow').innerHTML = new_tmpl;
//  delete unsafeWindow.$.template("tmplCacheLogRow");
//  unsafeWindow.$("#tmpl_CacheLogRow").template("tmplCacheLogRow");
//}

////////////////////////////////////////////////////////////////////////////

// Helper: from N/S/E/W Deg Min.Sec to Dec
function toDec(coords){
  var match = coords.match(/^(N|S) ([0-9][0-9]). ([0-9][0-9])\.([0-9][0-9][0-9]) (E|W) ([0-9][0-9][0-9]). ([0-9][0-9])\.([0-9][0-9][0-9])$/);

  if(match){
    var dec1 = parseInt(match[2],10) + (parseFloat(match[3]+"."+match[4])/60);
    if(match[1] == "S") dec1 = dec1 * -1;
    dec1 = Math.round(dec1*10000000)/10000000;

    var dec2 = parseInt(match[6],10) + (parseFloat(match[7]+"."+match[8])/60);
    if(match[5] == "W") dec2 = dec2 * -1;
    dec2 = Math.round(dec2*10000000)/10000000;

    return new Array(dec1,dec2);
  }else return false;
}

// Helper: from Deg to DMS
function DegtoDMS(coords){
  var match = coords.match(/^(N|S) ([0-9][0-9]). ([0-9][0-9])\.([0-9][0-9][0-9]) (E|W) ([0-9][0-9][0-9]). ([0-9][0-9])\.([0-9][0-9][0-9])$/);

  var lat1 = parseInt(match[2],10);
  var lat2 = parseInt(match[3],10);
  var lat3 = parseFloat("0."+match[4])*60;
  lat3 = Math.round(lat3*10000)/10000;

  var lng1 = parseInt(match[6],10); 
  var lng2 = parseInt(match[7],10);
  var lng3 = parseFloat("0."+match[8])*60;
  lng3 = Math.round(lng3*10000)/10000;

  return match[1]+" "+lat1+"° "+lat2+"' "+lat3+"\" "+match[5]+" "+lng1+"° "+lng2+"' "+lng3+"\"";
}

// Helper: from Dec to Deg
function DectoDeg(lat,lng){
  lat = lat/10000000;
  var pre = "";
  if(lat > 0) pre = "N";
  else{ pre = "S"; lat = lat * -1; }
  var tmp1 = parseInt(lat);
  var tmp2 = (lat-tmp1)*60;
  tmp1 = String(tmp1);
  if(tmp1.length == 1) tmp1 = "0"+tmp1;
  tmp2 = Math.round(tmp2*10000)/10000;
  tmp2 = String(tmp2);
  if(tmp2.length == 3) tmp2 = tmp2+"000";
  else if(tmp2.length == 4) tmp2 = tmp2+"00";
  else if(tmp2.length == 5) tmp2 = tmp2+"0";
  var new_lat = pre+" "+tmp1+"° "+tmp2;

  lng = lng/10000000;
  var pre = "";
  if(lng > 0) pre = "E";
  else{ pre = "W"; lng = lng * -1; }
  var tmp1 = parseInt(lng);
  var tmp2 = (lng-tmp1)*60;
  tmp1 = String(tmp1);
  if(tmp1.length == 2) tmp1 = "0"+tmp1;
  else if(tmp1.length == 1) tmp1 = "00"+tmp1;
  tmp2 = Math.round(tmp2*10000)/10000;
  tmp2 = String(tmp2);
  if(tmp2.length == 3) tmp2 = tmp2+"000";
  else if(tmp2.length == 4) tmp2 = tmp2+"00";
  else if(tmp2.length == 5) tmp2 = tmp2+"0";
  var new_lng = pre+" "+tmp1+"° "+tmp2;

  return new_lat+" "+new_lng;
}

// Show other Coord-Formats in Listing
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/) && document.getElementById('ctl00_ContentBody_LatLon')){
  var box = document.getElementById('ctl00_ContentBody_LocationSubPanel').firstChild;
  var coords = document.getElementById('ctl00_ContentBody_LatLon').innerHTML;
  if(coords.match(/^(N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9]$/)){
    var dec = toDec(coords);
    var lat = dec[0];
    var lng = dec[1];
    if(lat < 0) lat = "S "+(lat*-1);
    else lat = "N "+lat;
    if(lng < 0) lng = "W "+(lng*-1);
    else lng = "E "+lng;
    box.innerHTML += " - Dec: "+lat+" "+lng;

    var dms = DegtoDMS(coords);
    box.innerHTML += " - DMS: "+dms;
  }
}
// ... and on print-page
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cdpf\.aspx/)){
  var box = getElementsByClass("UTM Meta")[0];
  var coords = getElementsByClass("LatLong Meta")[0];
  if(box && coords){
    var match = coords.innerHTML.match(/((N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9])/);
    if(match && match[1]){
      coords = match[1];
      var dec = toDec(coords);
      var lat = dec[0];
      var lng = dec[1];
      if(lat < 0) lat = "S "+(lat*-1);
      else lat = "N "+lat;
      if(lng < 0) lng = "W "+(lng*-1);
      else lng = "E "+lng;
      box.innerHTML += "<br>Dec: "+lat+" "+lng;

      var dms = DegtoDMS(coords);
      box.innerHTML += "<br>DMS: "+dms;
    }
  }
}

// Show Map-It button at Listing
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/) && document.getElementById('ctl00_ContentBody_LatLon')){
  var coords = toDec(document.getElementById("ctl00_ContentBody_LatLon").innerHTML);
  var link = document.getElementById("ctl00_ContentBody_LatLon").nextSibling.nextSibling;
  var a = document.createElement("a");
  a.setAttribute("href",map_url+"?ll="+coords[0]+","+coords[1]);
  a.appendChild(document.createTextNode("Map this Location"));
  link.appendChild(document.createTextNode(" - "));
  link.appendChild(a);
}

// Show Route-It button at Listing
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/seek\/cache_details\.aspx/) && document.getElementById('ctl00_ContentBody_LatLon') && GM_getValue("home_lat")){
  var coords = toDec(document.getElementById("ctl00_ContentBody_LatLon").innerHTML);
  var link = document.getElementById("ctl00_ContentBody_LatLon").nextSibling.nextSibling;
  var a = document.createElement("a");
  var name = ""
  if(document.getElementById("ctl00_ContentBody_CacheName")) name = "+("+trim(document.getElementById("ctl00_ContentBody_CacheName").innerHTML)+")";
  a.setAttribute("href","http://maps.google.com/?saddr="+(GM_getValue("home_lat")/10000000)+","+(GM_getValue("home_lng")/10000000)+"+(HomeCoords)&daddr="+coords[0]+","+coords[1]+name);
  a.setAttribute("target","_blank");
  a.appendChild(document.createTextNode("Route to this Location"));
  link.appendChild(document.createTextNode(" - "));
  link.appendChild(a);
}

// Save HomeCoords for special bookmarks - From Index
/* Da stehen keine Koords mehr ...
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/$/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/default\.aspx$/)){
  var search_value = document.getElementById("ctl00_ContentBody_saddress").value;

  if(search_value.match(/^(N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9]$/)){
    var latlng = toDec(search_value);

    if(GM_getValue("home_lat",0) != parseInt(latlng[0]*10000000)) GM_setValue("home_lat",parseInt(latlng[0]*10000000)); // * 10000000 because GM don't know float
    if(GM_getValue("home_lng",0) != parseInt(latlng[1]*10000000)) GM_setValue("home_lng",parseInt(latlng[1]*10000000));
  }
}*/

// Transfer TB-Tracking Number to Log-Field
// if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/default\.aspx$/)){
//   function gclh_save_tracking(){
//     var tracking = document.getElementById('ctl00_ContentBody_txtTrackingNumber').value;
//     if(tracking && !tracking.match(/^TB/i)) GM_setValue("last_tracking_nr",tracking);
//   }
//   document.getElementById('ctl00_ContentBody_btnLookupCode').addEventListener("click",gclh_save_tracking,false);
//   document.getElementById('ctl00_ContentBody_txtTrackingNumber').addEventListener("keypress",function(e){if(e.which == 13) gclh_save_tracking();},false);
// }
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/details\.aspx\?tracker=/)){
  var matches = document.location.href.match(/tracker=([a-zA-Z0-9]*)/);
  if(matches && !matches[1].match(/^TB/i)) GM_setValue("last_tracking_nr",matches[1]);
}
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/track\/log\.aspx/) && document.getElementById('ctl00_ContentBody_LogBookPanel1_tbCode')){
  if(GM_getValue("last_tracking_nr","") != ""){
    document.getElementById('ctl00_ContentBody_LogBookPanel1_tbCode').value = GM_getValue("last_tracking_nr");
    GM_setValue("last_tracking_nr","");
  }
}

// Fix decrypted Hint linefeeds
if(document.getElementById('div_hint')){
  function gclh_repair_hint(){
    document.getElementById('div_hint').innerHTML = document.getElementById('div_hint').innerHTML.replace(/<c>/g,"<p>");
    document.getElementById('div_hint').innerHTML = document.getElementById('div_hint').innerHTML.replace(/<\/c>/g,"</p>");
  }
  gclh_repair_hint();
  document.getElementById('ctl00_ContentBody_lnkDH').addEventListener("click", gclh_repair_hint, false);
}

// Improve Search Lists color
var css = "table.Table tr.TertiaryRow td, .TertiaryRow, table.Table tr td.TertiaryRow { background-color: #c2e0c3; }";
var head = document.getElementsByTagName('head')[0];
var style = document.createElement('style');
style.type = 'text/css';
style.innerHTML = css;
head.appendChild(style);

// Hide Navi on SignIn-Overlay
function hide_navi(){
  var navi = document.getElementById('Navigation');
  if(navi.style.display == "") navi.style.display = "none";
  else navi.style.display = "";
}
if(document.getElementById('hlSignIn')) document.getElementById('hlSignIn').addEventListener("click",hide_navi,false);
if(document.getElementById('ctl00_hlSignInClose')) document.getElementById('ctl00_hlSignInClose').addEventListener("click",hide_navi,false);

// Save HomeCoords for special bookmarks - From Manage Home Location
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/account\/ManageLocations\.aspx/)){
  function setCoordsHelper(){
    var search_value = document.getElementById("search").value;

    if(search_value.match(/^(N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9]$/)){
      var latlng = toDec(search_value);

      if(GM_getValue("home_lat",0) != parseInt(latlng[0]*10000000)) GM_setValue("home_lat",parseInt(latlng[0]*10000000)); // * 10000000 because GM don't know float
      if(GM_getValue("home_lng",0) != parseInt(latlng[1]*10000000)) GM_setValue("home_lng",parseInt(latlng[1]*10000000));
    }
  }

  window.addEventListener("load", setCoordsHelper, false); // On first hit, the search-field ist filled after loading - so we have to wait
}

// Save HomeCoords - From Account Details
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/account\/default\.aspx/)){
  var link = document.getElementById('ctl00_ContentBody_uxMapLocations_ctl01_uxMapLocation');

  if(link){
    var match = link.innerHTML.match(/((N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9])/);
    if(match[1]){
      var latlng = toDec(match[1]);

      if(GM_getValue("home_lat",0) != parseInt(latlng[0]*10000000)) GM_setValue("home_lat",parseInt(latlng[0]*10000000)); // * 10000000 because GM don't know float
      if(GM_getValue("home_lng",0) != parseInt(latlng[1]*10000000)) GM_setValue("home_lng",parseInt(latlng[1]*10000000));
    }
  }
}

// Save uid for special bookmarks - From My Profile
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\//)){
  var links = document.getElementsByTagName("a");

  for(var i=0; i<links.length; i++){
    if(links[i].href.match(/\/track\/search\.aspx\?o=1\&uid=/)){
      var uid = links[i].href.match(/\/track\/search\.aspx\?o=1\&uid=(.*)/);
      uid = uid[1];

      if(GM_getValue["uid"] != uid) GM_setValue("uid",uid);
    }
  }
}

// count cache matrix on statistics page or profile page
if (isLocation("my/statistics.aspx") || isLocation("profile/?guid")) {
  var table = document.getElementById('ctl00_ContentBody_StatsDifficultyTerrainControl1_uxDifficultyTerrainTable');
  if (null == table) {
    // on the profile page the ID is different than on the statistics page
    table = document.getElementById("ctl00_ContentBody_ProfilePanel1_StatsDifficultyTerrainControl1_uxDifficultyTerrainTable");
  }
  if (table) {
    var zeros = 0;
    var cells = table.getElementsByTagName('td');
    for (var i = 0; i < cells.length; i++) {
      var cell = cells[i];
      if (cell.className == 'stats_cellzero') {
        zeros++;
      }
    }
    var foundMatrix = (9*9)-zeros; 
    var link = document.getElementById('uxDifficultyTerrainHelp');
    if (link) {
      var headline = link.previousSibling;
      if (headline) {
        headline.nodeValue += (' (' + foundMatrix + '/' + (9*9) + ')'); 
      }
    }
  }
}

/**
 * check whether the user has set his home coordinates
 * @returns {Boolean}
 */
function homeCoordinatesSet() {
  if(typeof(GM_getValue("home_lat")) == "undefined" || typeof(GM_getValue("home_lng")) == "undefined"){
    if (window.confirm("To use this link, you have to set your home coordinates.")) {
      document.location.href = "http://www.geocaching.com/account/ManageLocations.aspx";
    }
    return false;
  }
  return true;
}

// Helper
function addLinkEvent(name,fkt){
  if(document.getElementsByName(name).length > 0){
    var links = document.getElementsByName(name);
    for(var i = 0; i < links.length; i++){
      links[i].addEventListener("click", fkt, false);
    }
  }else if(document.getElementById(name)){
    document.getElementById(name).addEventListener("click", fkt, false);
  }
}

// Redirect to Neares List/Map
function linkToNearesList(){
  if (homeCoordinatesSet()) {
    document.location.href = "http://www.geocaching.com/seek/nearest.aspx?lat="+(GM_getValue("home_lat")/10000000)+"&lng="+(GM_getValue("home_lng")/10000000)+"&dist=25&disable_redirect";
  }
}
addLinkEvent('lnk_nearestlist',linkToNearesList);

function linkToNearesMap(){
  if (homeCoordinatesSet()) {
    document.location.href = map_url+"?lat="+(GM_getValue("home_lat")/10000000)+"&lng="+(GM_getValue("home_lng")/10000000);
  }
}
addLinkEvent('lnk_nearestmap',linkToNearesMap);

// Redirect to Neares List without Founds
function linkToNearesListWo(){
  if (homeCoordinatesSet()) {
    document.location.href = "http://www.geocaching.com/seek/nearest.aspx?lat="+(GM_getValue("home_lat")/10000000)+"&lng="+(GM_getValue("home_lng")/10000000)+"&dist=25&f=1&disable_redirect";
  }
}
addLinkEvent('lnk_nearestlist_wo',linkToNearesListWo);

// Redirect to My Trackables
function linkToMyTrackables(){
  if(typeof(GM_getValue("uid")) == "undefined"){
    if(window.confirm("To use this Link, the script has to know your uid. Just load the \"My Profile\" site and the script will save it automatically.")) document.location.href = "http://www.geocaching.com/my/";
  }else{
    document.location.href = "http://www.geocaching.com/track/search.aspx?o=1&uid="+GM_getValue("uid");
  }
}
addLinkEvent('lnk_my_trackables',linkToMyTrackables);

// Redirect + JS-Exec
function linkToGeocaches(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkUserStats','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profilegeocaches',linkToGeocaches);

function linkToTrackables(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkCollectibles','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profiletrackables',linkToTrackables);

function linkToGallery(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkGallery','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profilegallery',linkToGallery);
addLinkEvent('lnk_profilegallery2',linkToGallery);
//var links = document.getElementsByName('lnk_profilegallery2');
//for(var i=0; i<links.length; i++){ // Friendlist
//  links[i].addEventListener("click", linkToGallery, false);
//}

function linkToBookmarks(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkLists','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profilebookmarks',linkToBookmarks);

function linkToSouvenirs(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkSouvenirs','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profilesouvenirs',linkToSouvenirs);

function linkToStatistics(){
  GM_setValue("run_after_redirect","__doPostBack('ctl00$ContentBody$ProfilePanel1$lnkStatistics','')");
  document.location.href = "/profile/default.aspx";
}
addLinkEvent('lnk_profilestatistics',linkToStatistics);

// Close the Overlays
function btnClose(){
  if(document.getElementById('bg_shadow')) document.getElementById('bg_shadow').style.display = "none";
  if(document.getElementById('settings_overlay')) document.getElementById('settings_overlay').style.display = "none";
  if(document.getElementById('findplayer_overlay')) document.getElementById('findplayer_overlay').style.display = "none";
}

// Create and hide the "Find Player" Form
function createFindPlayerForm(){
  if(document.getElementById('bg_shadow')){
    // If shadow-box already created, just show it
    if(document.getElementById('bg_shadow').style.display == "none"){
      document.getElementById('bg_shadow').style.display = "";
    }
  }else{
    var html = "";
    // Seite abdunkeln
    html += "<div id='bg_shadow' style='width: 100%; height: 100%; background-color: #000000; position:fixed; top: 0; left: 0; opacity: 0.5; filter: alpha(opacity=50);'></div>";
    document.getElementsByTagName('body')[0].innerHTML += html;

    document.getElementById('bg_shadow').addEventListener("click", btnClose, false);
  }

  if(document.getElementById('findplayer_overlay') && document.getElementById('findplayer_overlay').style.display == "none"){
    // If menu already created, just show it
    document.getElementById('findplayer_overlay').style.display = "";
  }else{
    var html = "";
    html += "<style>";
    html += "#findplayer_overlay {";
    html += "  background-color: #d8cd9d; ";
    html += "  width:350px;";
    html += "  border: 2px solid #778555; ";
    html += "  overflow: auto; ";
    html += "  padding:10px; ";
    html += "  position: absolute; ";
    html += "  left:30%; ";
    html += "  top:60px; ";
    html += "  z-index:101; ";
    html += "  -moz-border-radius:30px; ";
    html += "  -khtml-border-radius:30px; ";
    html += "  border-radius: 30px;";
    html += "  overflow: auto;";
    html += "}";
    html += ".gclh_form {";
    html += "  background-color: #d8cd9d;";
    html += "  border: 2px solid #778555;";
    html += "  -moz-border-radius: 7px;";
    html += "  -khtml-border-radius: 7px;";
    html += "  border-radius: 7px;";
    html += "  padding-left: 5px;";
    html += "  padding-right: 5px;";
    html += "}";
    html += "</style>";
    // Overlay erstellen
    html += "<div id='findplayer_overlay' align='center'>";
    html += "<h3 style='margin:5px;'>Find Player</h3>";
    html += "<form action=\"/find/default.aspx\" method=\"post\" name=\"aspnetForm\">";
    html += "<input class='gclh_form' type='hidden' name='__VIEWSTATE' value=''>";
    html += "<input class='gclh_form' id='findplayer_field' class=\"Text\" type=\"text\" maxlength=\"100\" name=\"ctl00$ContentBody$FindUserPanel1$txtUsername\"/>";
    html += "<input class='gclh_form' type=\"submit\" value=\"Go\" name=\"ctl00$ContentBody$FindUserPanel1$GetUsers\"/><input class='gclh_form' id='btn_close' type='button' value='close'>";
    html += "</form>";
    html += "</div>";
    document.getElementsByTagName('body')[0].innerHTML += html;

    document.getElementById("findplayer_field").focus();

    document.getElementById('btn_close').addEventListener("click", btnClose, false);
  }
}

if(document.getElementById('lnk_findplayer')){
  document.getElementById('lnk_findplayer').addEventListener("click", createFindPlayerForm, false);
}

function checkbox(setting_id, label) {
  return "<input type='checkbox' "+(eval(setting_id) ? "checked='checked'" : "" )+" id='" + setting_id + "'> " + label;
}

/*
// Sync settings
function get_settings(){
  var vals = [];
  for each (var val in GM_listValues()) {
    vals.push(GM_getValue(val));
  }
  alert(uneval(vals));
}
//get_settings();
*/

// Configuration Menu
function gclh_showConfig(){
  // the configuration is always displayed at the top, so scroll away from logs or other lower stuff
  scroll(0, 0);

  if(document.getElementById('bg_shadow')){
    // If shadow-box already created, just show it
    if(document.getElementById('bg_shadow').style.display == "none"){
      document.getElementById('bg_shadow').style.display = "";
    }
  }else{
    // Seite abdunkeln
    var shadow = document.createElement("div");
    shadow.setAttribute("id","bg_shadow");
    shadow.setAttribute("style","width: 100%; height: 100%; background-color: #000000; position:fixed; top: 0; left: 0; opacity: 0.5; filter: alpha(opacity=50);");
    document.getElementsByTagName('body')[0].appendChild(shadow);
//    html += "<div id='bg_shadow' style='width: 100%; height: 100%; background-color: #000000; position:fixed; top: 0; left: 0; opacity: 0.5; filter: alpha(opacity=50);'></div>";
//    document.getElementsByTagName('body')[0].innerHTML += html;
    document.getElementById('bg_shadow').addEventListener("click", btnClose, false);
  }

  if(document.getElementById('settings_overlay') && document.getElementById('settings_overlay').style.display == "none"){
    // If menu already created, just show it
    document.getElementById('settings_overlay').style.display = "";
  }else{
    var css = document.createElement("style");
    var html = "";
//    html += "<style>";
    html += "#settings_overlay {";
    html += "  background-color: #d8cd9d; ";
    html += "  width:600px;";
    html += "  border: 2px solid #778555; ";
    html += "  overflow: auto; ";
    html += "  padding:10px; ";
    html += "  position: absolute; ";
    html += "  left:30%; ";
    html += "  top:10px; ";
    html += "  z-index:101; ";
    html += "  -moz-border-radius:30px; ";
    html += "  -khtml-border-radius:30px; ";
    html += "  border-radius: 30px;";
    html += "  overflow: auto;";
    html += "}";
    html += "";
    html += ".gclh_headline {";
    html += "  height: 21px; ";
    html += "  margin:5px; ";
    html += "  background-color: #778555; ";
    html += "  color: #FFFFFF;";
    html += "  -moz-border-radius:30px; ";
    html += "  -khtml-border-radius:30px; ";
    html += "  border-radius: 30px;";
    html += "  text-align: center;";
    html += "}";
    html += "";
    html += ".gclh_headline2 {";
    html += "  margin: 5px;";
    html += "}";
    html += "";
    html += ".gclh_content {";
    html += "  padding: 10px;";
    html += "  font-family: Verdana;";
    html += "  font-size: 14px;";
    html += "}";
    html += "";
    html += ".gclh_form {";
    html += "  background-color: #d8cd9d;";
    html += "  border: 2px solid #778555;";
    html += "  -moz-border-radius: 7px;";
    html += "  -khtml-border-radius: 7px;";
    html += "  border-radius: 7px;";
    html += "  padding-left: 5px;";
    html += "  padding-right: 5px;";
    html += "}";
    html += "";
    html += ".gclh_ref {";
    html += "  color: #000000;";
    html += "  text-decoration: none;";
    html += "  border-bottom: dotted 1px black;";
    html += "}";
    html += "";
    html += ".gclh_small {";
    html += "  font-size: 10px;";
    html += "}";
    html += "";
    // Highlight
    html += "a.gclh_info {";
    html += "  color: #000000;";
    html += "  text-decoration: none;";
    html += "}";
    html += "";
    html += "a.gclh_info:hover {";
    html += "  position: relative;";
    html += "}";
    html += "";
    html += "a.gclh_info span {";
    html += "  visibility: hidden;";
    html += "  position: absolute; top:-310px; left:0px;";
    html += "  padding: 2px;";
    html += "  text-decoration: none;";
    html += "  text-align: left;";
    html += "  vertical-align: top;";
    html += "  font-size: 12px;";
    html += "}";
    html += "";
    html += "a.gclh_info:hover span {";
    html += "  width: 250px;";
    html += "  visibility: visible;";
    html += "  top: 10px;";
    html += "  border: 1px solid #000000;";
    html += "  background-color: #d8cd9d;";
    html += "}";
//    html += "</style>";
    css.innerHTML = html;
  
    var div = document.createElement("div");
    div.setAttribute("id","settings_overlay");
    var html = "";  
//    html += "<div id='settings_overlay'>";
    html += "<h3 class='gclh_headline'>GC little helper <font class='gclh_small'>v"+scriptVersion+"</font></h3>";
    html += "<div class='gclh_content'>";
    html += "";
    html += "<h4 class='gclh_headline2'>Global</h4>";
    html += "Home-Coords: <input class='gclh_form' type='text' id='settings_home_lat_lng' value='"+DectoDeg(GM_getValue("home_lat"),GM_getValue("home_lng"))+"'> <a class='gclh_info' href='#'><b>?</b><span class='gclh_span'>The Home-Coords are filled automatically if you update your Home-Coords on gc.com. If it doesn\'t work you can insert them here. These Coords are used for some special Links (Nearest List, Nearest Map, ..) and for the homezone-circle on the map.</span></a><br>";
    html += checkbox('settings_bookmarks_on_top', "Show <a class='gclh_ref' href='#gclh_linklist'>Linklist</a> on top") + "<br/>";
    html += checkbox('settings_bookmarks_show', "Show <a class='gclh_ref' href='#gclh_linklist'>Linklist</a> in profile") + "<br/>";
    html += checkbox('settings_hide_feedback', 'Hide Feedback-Button') + "<br/>";
    html += checkbox('settings_hide_advert_link', 'Hide link to advertisement instructions') + "<br/>";
    html += checkbox('settings_hide_line_breaks', 'Hide superfluous line breaks') + "<br/>";
    html += "Page-Width: <input class='gclh_form' type='text' size='3' id='settings_new_width' value='"+GM_getValue("settings_new_width",950)+"'> px<br>";
    html += checkbox('settings_automatic_friend_reset', 'Reset Defference-Counter on Friendlist automatically') + "<br/>";
    html += checkbox('settings_show_big_gallery', 'Show bigger Images in Gallery') + "<br/>";
    html += "";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'>Nearest List</h4>";
    html += checkbox('settings_redirect_to_map', 'Redirect to Map') + "<br/>";
    html += checkbox('settings_show_log_it', 'Show Log-It Icon') + "<br/>";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'>Maps</h4>";
    html += checkbox('settings_show_homezone', 'Show Homezone') + " - Radius: <input class='gclh_form' type='text' size='2' id='settings_homezone_radius' value='"+settings_homezone_radius+"'> miles<br>";
    html += "Homezone-Color: <input class='gclh_form' type='text' size='5' id='settings_homezone_color' value='"+settings_homezone_color+"'><br>";
    html += checkbox('settings_old_map', 'Set old map as default') + "<br/>";
    html += checkbox('settings_map_hide_found', 'Hide found caches by default') + "<br/>";
    html += checkbox('settings_map_hide_hidden', 'Hide own caches by default') + "<br/>";
    html += "Map-Width: <input class='gclh_form' type='text' size='3' id='map_width' value='"+GM_getValue("map_width",1200)+"'> px<br>";
    html += "";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'>Listing</h4>";
    html += checkbox('settings_log_inline', 'Log Cache from Listing (inline)') + " - " + checkbox('settings_log_inline_tb', 'Show TB-List') + "<br/>";
    html += checkbox('settings_hide_empty_cache_notes', 'Hide Cache Notes if empty') + "<br/>";
    html += checkbox('settings_hide_cache_notes', 'Hide Cache-Notes completely') + "<br/>";
    html += checkbox('settings_hide_disclaimer', 'Hide Disclaimer') + "<br/>";
    html += checkbox('settings_hide_spoilerwarning', 'Hide spoiler warning') + "<br/>";
    html += checkbox('settings_show_all_logs', 'Show ') + " <input class='gclh_form' type='text' size='2' id='settings_show_all_logs_count' value='"+settings_show_all_logs_count+"'> logs (0 = all)<br>";
    html += checkbox('settings_hide_hint', 'Hide hint behind a link') + "<br/>";
    html += checkbox('settings_decrypt_hint', 'Decrypt Hint') + "<br/>";
    html += checkbox('settings_show_mail', 'Show Mail Link beside Usernames') + "<br/>";
    html += checkbox('settings_show_google_maps', 'Show Link to and from google maps') + "<br/>";
    html += checkbox('settings_dynamic_map', 'Show dynamic map') + "<br/>";
    html += checkbox('settings_strike_archived', 'Strike through title of archived/disabled caches') + "<br/>";
    html += checkbox('settings_show_fav_percentage', 'Show percentage of favourite points') + "<br/>";
    html += checkbox('settings_show_vip_list', 'Show VIP-List') + "<br/>";
    html += checkbox('settings_show_long_vip', 'Show long VIP-List (one row per log)') + "<br/>";
    html += checkbox('settings_show_thumbnails', 'Show Thumbnails of Images') + "<br/>";
    html += checkbox('settings_hide_avatar', 'Hide Avatars in Listing') + "<br/>";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'>Logging</h4>";
    html += checkbox('settings_submit_log_button', 'Submit Log Text on F2') + "<br/>";
    html += checkbox('settings_show_bbcode', 'Show Smilies and BBCode') + "<br/>";
    html += checkbox('settings_autovisit', 'Enable AutoVisit-Feature for TBs/Coins') + "<br/>";
    html += "Log-Templates: <font class='gclh_small'>(BBCodes have to be enabled - #found# will be replaced with founds+1 - #found_no# will be replaced with founds)</font><br>";
    for(var i = 0; i < anzTemplates; i++){
      html += "&nbsp;&nbsp;<input class='gclh_form' type='text' size='15' id='settings_log_template_name["+i+"]' value='"+GM_getValue('settings_log_template_name['+i+']','')+"'> ";
      html += "<a onClick=\"if(document.getElementById(\'settings_log_template_div["+i+"]\').style.display == \'\') document.getElementById(\'settings_log_template_div["+i+"]\').style.display = \'none\'; else document.getElementById(\'settings_log_template_div["+i+"]\').style.display = \'\'; return false;\" href='#'><img src='http://www.geocaching.com/images/stockholm/16x16/page_white_edit.gif' border='0'></a><br>";
      html += "<div id='settings_log_template_div["+i+"]' style='display: none;'>&nbsp;&nbsp;&nbsp;&nbsp;<textarea class='gclh_form' rows='6' cols='30' id='settings_log_template["+i+"]'>&zwnj;"+GM_getValue("settings_log_template["+i+"]","")+"</textarea></div>";
    }
    html += "Default Log-Type: &nbsp; &nbsp; &nbsp;<select class='gclh_form' id='settings_default_logtype'>";
    html += "  <option value=\"-1\" "+(settings_default_logtype == "-1" ? "selected=\"selected\"" : "")+">- Select Type of Log -</option>";
    html += "  <option value=\"2\" "+(settings_default_logtype == "2" ? "selected=\"selected\"" : "")+">Found it</option>";
    html += "  <option value=\"3\" "+(settings_default_logtype == "3" ? "selected=\"selected\"" : "")+">Didn't find it</option>";
    html += "  <option value=\"4\" "+(settings_default_logtype == "4" ? "selected=\"selected\"" : "")+">Write note</option>";
    html += "  <option value=\"7\" "+(settings_default_logtype == "7" ? "selected=\"selected\"" : "")+">Needs Archived</option>";
    html += "  <option value=\"45\" "+(settings_default_logtype == "45" ? "selected=\"selected\"" : "")+">Needs Maintenance</option>";
    html += "</select><br>";
    html += "Default TB-Log-Type: <select class='gclh_form' id='settings_default_tb_logtype'>";
    html += "  <option value=\"-1\" "+(settings_default_tb_logtype == "-1" ? "selected=\"selected\"" : "")+">- Select Type of Log -</option>";
    html += "  <option value=\"13\" "+(settings_default_tb_logtype == "13" ? "selected=\"selected\"" : "")+">Retrieve from ..</option>";
    html += "  <option value=\"19\" "+(settings_default_tb_logtype == "19" ? "selected=\"selected\"" : "")+">Grab it from ..</option>";
    html += "  <option value=\"4\" "+(settings_default_tb_logtype == "4" ? "selected=\"selected\"" : "")+">Write note</option>";
    html += "  <option value=\"48\" "+(settings_default_tb_logtype == "48" ? "selected=\"selected\"" : "")+">Discovered It</option>";
    html += "</select><br>";
    html += "Cache-Signature: <font class='gclh_small'>(#found# will be replaced with founds+1 - #found_no# will be replaced with founds)</font><br>";
    html += "<textarea class='gclh_form' rows='8' cols='40' id='settings_log_signature'>&zwnj;"+GM_getValue("settings_log_signature","")+"</textarea><br>";
    html += "TB-Signature: <font class='gclh_small'>(#found# will be replaced with founds+1 - #found_no# will be replaced with founds)</font><br>";
    html += "<textarea class='gclh_form' rows='8' cols='40' id='settings_tb_signature'>&zwnj;"+GM_getValue("settings_tb_signature","")+"</textarea><br>";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'>Mail-Form</h4>";
    html += "Signature:<br>";
    html += "<textarea class='gclh_form' rows='8' cols='40' id='settings_mail_signature'>&zwnj;"+GM_getValue("settings_mail_signature","")+"</textarea><br>";
    html += "<br>";
    html += "";
    html += "<h4 class='gclh_headline2'><a name='gclh_linklist'></a>Linklist / Navigation <a class='gclh_small' href='#gclh_linklist' id='gclh_show_linklist_btn'>show</a></h4>";
    html += "<div id='gclh_settings_linklist' style='display: none;'>";
    html += "Remove from Navigation:<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_play') ? "checked='checked'" : "" )+" id='remove_navi_play'> Play<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_profile') ? "checked='checked'" : "" )+" id='remove_navi_profile'> Your Profile<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_join') ? "checked='checked'" : "" )+" id='remove_navi_join'> Join<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_community') ? "checked='checked'" : "" )+" id='remove_navi_community'> Community<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_videos') ? "checked='checked'" : "" )+" id='remove_navi_videos'> Videos<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_resources') ? "checked='checked'" : "" )+" id='remove_navi_resources'> Resources<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_shop') ? "checked='checked'" : "" )+" id='remove_navi_shop'> Shop<br>";
    html += "<input type='checkbox' "+(GM_getValue('remove_navi_social',true) ? "checked='checked'" : "" )+" id='remove_navi_social'> Social Buttons<br>";
    html += "<br>";
    html += "<input type='checkbox' "+(settings_bookmarks_search ? "checked='checked'" : "" )+" id='settings_bookmarks_search'> Show Searchfield - Default Value: <input class='gclh_form' type='text' id='settings_bookmarks_search_default' value='"+settings_bookmarks_search_default+"' size='4'><br>";
    html += "<input type='checkbox' "+(settings_bookmarks_top_menu ? "checked='checked'" : "" )+" id='settings_bookmarks_top_menu'> Show Linklist as Drop-Down<br>";
    html += "<br>";
    html += "<table>";
    html += "  <tr>";
    html += "    <td colspan='2'><font class='gclh_small'>(Second check to enable on Beta Map)</font></td>";
    html += "    <th>Sort</th>";
    html += "    <th>Custom Name</th>";
    html += "  </tr>";

    // Create reverse-Array
    var sort = new Array();
    for(var i=0; i<settings_bookmarks_list.length; i++){
      sort[settings_bookmarks_list[i]] = i;
    }
    var sort_beta = new Array();
    for(var i=0; i<settings_bookmarks_list_beta.length; i++){
      sort_beta[settings_bookmarks_list_beta[i]] = i;
    }

    // Create the Bookmark-Options
    var cust = 0;
    for(var i=0; i<bookmarks.length; i++){
      var options = "";
      for(var x=0; x<bookmarks.length; x++){
        options += "<option value='"+x+"' "+(sort[i] == x ? "selected='selected'" : "" )+">"+x+"</option>";
      }

      html += "  <tr>";
      html += "    <td align='left'><input type='checkbox' "+(typeof(sort[i]) != "undefined" ? "checked='checked'" : "" )+" id='settings_bookmarks_list["+i+"]'><input type='checkbox' "+(typeof(sort_beta[i]) != "undefined" ? "checked='checked'" : "" )+" id='settings_bookmarks_list_beta["+i+"]'></td>";
      html += "    <td align='left'>";
      if(typeof(bookmarks[i]['custom']) != "undefined" && bookmarks[i]['custom'] == true){
        html += "<input class='gclh_form' type='text' id='settings_custom_bookmark["+cust+"]' value='"+bookmarks[i]['href']+"' size='15'> ";
        html += "<input type='checkbox' title='Open in new Window' "+(bookmarks[i]['target'] == "_blank" ? "checked='checked'" : "" )+" id='settings_custom_bookmark_target["+cust+"]'>";
        cust++;
      }else{
        html += "<a class='gclh_ref' ";
        for(attr in bookmarks[i]){
          html += attr+"='"+bookmarks[i][attr]+"' ";
        }
        html += ">"+(typeof(bookmarks_orig_title[i]) != "undefined" && bookmarks_orig_title[i] != "" ? bookmarks_orig_title[i] : bookmarks[i]['title'])+"</a>";
      }
      html += "</td>";
      html += "    <td align='left'><select class='gclh_form' id='bookmarks_sort["+i+"]'>"+options+"</select></td>";
      html += "    <td align='left'><input class='gclh_form' id='bookmarks_name["+i+"]' type='text' size='15' value='"+(typeof(GM_getValue("settings_bookmarks_title["+i+"]")) != "undefined" ? GM_getValue("settings_bookmarks_title["+i+"]") : "")+"'></td>"; 
      html += "  </tr>";
    }
    html += "</table>";
    html += "</div>";
    html += "<br>";
    html += "";
    html += "<br>";
    html += "<input class='gclh_form' type='button' value='save' id='btn_save'> <input class='gclh_form' type='button' value='close' id='btn_close'> <div width='400px' align='right' class='gclh_small' style='float: right;'>GC little helper by <a href='http://www.amshove.net/' target='_blank'>Torsten Amshove</a></div>";
    html += "</div>";
//    html += "</div>";
    div.innerHTML = html;

//    document.getElementsByTagName('body')[0].innerHTML += html;
    document.getElementsByTagName('body')[0].appendChild(css);
    document.getElementsByTagName('body')[0].appendChild(div);
    
    var code = GM_getResourceText("jscolor");
    code += 'var homezonepic = new jscolor.color(document.getElementById("settings_homezone_color"), {required:true, adjust:true, hash:true, caps:true, pickerMode:\'HSV\', pickerPosition:\'right\'});'
    var script = document.createElement("script");
    script.innerHTML = code;
    document.getElementsByTagName("body")[0].appendChild(script);


    function gclh_show_linklist(){
      var linklist = document.getElementById('gclh_settings_linklist');
      var lnk = document.getElementById('gclh_show_linklist_btn');

      if(linklist.style.display == 'none'){
        linklist.style.display = '';
        lnk.innerHTML = "hide";
      }else{
        linklist.style.display = 'none';
        lnk.innerHTML = "show";
      }
    }
    document.getElementById('gclh_show_linklist_btn').addEventListener("click",gclh_show_linklist,false);

    // Give the buttons an function
    document.getElementById('btn_close').addEventListener("click", btnClose, false);
    document.getElementById('btn_save').addEventListener("click", btnSave, false);
  }

  // Save Button
  function btnSave(){
    var value = document.getElementById("settings_home_lat_lng").value;
    if(value.match(/^(N|S) [0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9] (E|W) [0-9][0-9][0-9]. [0-9][0-9]\.[0-9][0-9][0-9]$/)){
      var latlng = toDec(value);
      if(GM_getValue("home_lat",0) != parseInt(latlng[0]*10000000)) GM_setValue("home_lat",parseInt(latlng[0]*10000000)); // * 10000000 because GM don't know float
      if(GM_getValue("home_lng",0) != parseInt(latlng[1]*10000000)) GM_setValue("home_lng",parseInt(latlng[1]*10000000));
    }
    GM_setValue("settings_bookmarks_search_default",document.getElementById('settings_bookmarks_search_default').value);
    GM_setValue("settings_show_all_logs_count",document.getElementById('settings_show_all_logs_count').value);
    GM_setValue("settings_homezone_radius",document.getElementById('settings_homezone_radius').value);
    GM_setValue("settings_homezone_color",document.getElementById('settings_homezone_color').value);
    GM_setValue("map_width",document.getElementById('map_width').value);
    GM_setValue("settings_new_width",document.getElementById('settings_new_width').value);
    GM_setValue("settings_default_logtype",document.getElementById('settings_default_logtype').value);
    GM_setValue("settings_default_tb_logtype",document.getElementById('settings_default_tb_logtype').value);
    GM_setValue("settings_mail_signature",document.getElementById('settings_mail_signature').value.replace(/‌/g,"")); // Fix: Entfernt das Steuerzeichen
    GM_setValue("settings_log_signature",document.getElementById('settings_log_signature').value.replace(/‌/g,""));
    GM_setValue("settings_tb_signature",document.getElementById('settings_tb_signature').value.replace(/‌/g,""));

    var checkboxes = new Array(
      'settings_submit_log_button',
      'settings_log_inline',
      'settings_log_inline_tb',
      'settings_bookmarks_show',
      'settings_bookmarks_on_top',
      'settings_bookmarks_search',
      'settings_redirect_to_map',
      'settings_hide_feedback',
      'settings_hide_disclaimer',
      'settings_hide_cache_notes',
      'settings_hide_empty_cache_notes',
      'settings_show_all_logs',
      'settings_decrypt_hint',
      'settings_show_bbcode',
      'settings_show_mail',
      'settings_show_google_maps',
      'settings_show_log_it',
      'settings_dynamic_map',
      'settings_show_homezone',
      'settings_old_map',
      'remove_navi_play',
      'remove_navi_profile',
      'remove_navi_join',
      'remove_navi_community',
      'remove_navi_videos',
      'remove_navi_resources',
      'remove_navi_shop',
      'remove_navi_social',
      'settings_bookmarks_top_menu',
      'settings_hide_advert_link',
      'settings_hide_line_breaks',
      'settings_hide_spoilerwarning',
      'settings_hide_hint',
      'settings_strike_archived',
      'settings_map_hide_found',
      'settings_map_hide_hidden',
      'settings_show_fav_percentage',
      'settings_show_vip_list',
      'settings_autovisit',
      'settings_show_thumbnails',
      'settings_hide_avatar',
      'settings_show_big_gallery',
      'settings_automatic_friend_reset',
      'settings_show_long_vip'
    );
    for (var i = 0; i < checkboxes.length; i++) {
      GM_setValue(checkboxes[i], document.getElementById(checkboxes[i]).checked);
    }

    // Save Log-Templates
    for(var i = 0; i < anzTemplates; i++){
      var name = document.getElementById('settings_log_template_name['+i+']');
      var text = document.getElementById('settings_log_template['+i+']');
      if(name && text){
        GM_setValue('settings_log_template_name['+i+']',name.value);
        GM_setValue('settings_log_template['+i+']',text.value.replace(/‌/g,"")); // Fix: Entfernt das Steuerzeichen
      }
    }

    // Create the confusing settings_bookmarks_list Array :)
    var queue = new Array();
    var tmp = new Array();
    for(var i=0; i<bookmarks.length; i++){
      if(document.getElementById('settings_bookmarks_list['+i+']')){ // Avoid errors
        if(document.getElementById('settings_bookmarks_list['+i+']').checked){ // If this Bookmark should be used, go and look at the sort-order
          if(document.getElementById('bookmarks_sort['+i+']')){ // Avoid errors
            if(typeof(tmp[document.getElementById('bookmarks_sort['+i+']').value]) == "undefined"){ // If sort-order not used by now, use it
              tmp[document.getElementById('bookmarks_sort['+i+']').value] = i;
            }else{
              queue.push(i);
              for(var x=0; x<bookmarks.length; x++){ // Find the next free sort-order
                if(typeof(tmp[x]) == "undefined"){
                  tmp[x] = i;
                  break;
                }
              }
            }
          }
        }
      }
      if(document.getElementById('bookmarks_name['+i+']') && document.getElementById('bookmarks_name['+i+']') != ""){ // Set custom name
        GM_setValue("settings_bookmarks_title["+i+"]",document.getElementById('bookmarks_name['+i+']').value);
      }
    }
    GM_setValue("settings_bookmarks_list",uneval(tmp));

    var queue = new Array();
    var tmp = new Array();
    for(var i=0; i<bookmarks.length; i++){
      if(document.getElementById('settings_bookmarks_list_beta['+i+']')){ // Avoid errors
        if(document.getElementById('settings_bookmarks_list_beta['+i+']').checked){ // If this Bookmark should be used, go and look at the sort-order
          if(document.getElementById('bookmarks_sort['+i+']')){ // Avoid errors
            if(typeof(tmp[document.getElementById('bookmarks_sort['+i+']').value]) == "undefined"){ // If sort-order not used by now, use it
              tmp[document.getElementById('bookmarks_sort['+i+']').value] = i;
            }else{
              queue.push(i);
              for(var x=0; x<bookmarks.length; x++){ // Find the next free sort-order
                if(typeof(tmp[x]) == "undefined"){
                  tmp[x] = i;
                  break;
                }
              }
            }
          }
        }
      }
      if(document.getElementById('bookmarks_name['+i+']') && document.getElementById('bookmarks_name['+i+']') != ""){ // Set custom name
        GM_setValue("settings_bookmarks_title["+i+"]",document.getElementById('bookmarks_name['+i+']').value);
      }
    }
    GM_setValue("settings_bookmarks_list_beta",uneval(tmp));
    
    // Save custom-Link URLs
    for(var i=0; i<anzCustom; i++){
      GM_setValue("settings_custom_bookmark["+i+"]",document.getElementById("settings_custom_bookmark["+i+"]").value);
      if(document.getElementById('settings_custom_bookmark_target['+i+']').checked) GM_setValue('settings_custom_bookmark_target['+i+']',"_blank");
      else GM_setValue('settings_custom_bookmark_target['+i+']',"");
    }

    document.location.reload(true);
  }
}
if(this.GM_registerMenuCommand && !document.location.href.match(/^http:\/\/www\.geocaching\.com\/map\/beta/)) GM_registerMenuCommand("little helper config", gclh_showConfig); // Hide on Beta-Map
if((document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/[#a-zA-Z-_]*$/) || document.location.href.match(/^http:\/\/www\.geocaching\.com\/my\/default\.aspx/)) && document.getElementById('ctl00_ContentBody_WidgetMiniProfile1_logOutLink')){
  var lnk = " | <a href='#' id='gclh_config_lnk'>GClh Konfig</a>";
  document.getElementById('ctl00_ContentBody_WidgetMiniProfile1_logOutLink').parentNode.innerHTML += lnk;
  document.getElementById('gclh_config_lnk').addEventListener("click", gclh_showConfig, false);
}
// Hide Avatars option
if(document.location.href.match(/^http:\/\/www\.geocaching\.com\/account\/ManagePreferences\.aspx/) && document.getElementById("ctl00_ContentBody_uxShowCacheLogAvatars")){
  var avatar_checkbox = document.getElementById("ctl00_ContentBody_uxShowCacheLogAvatars");
  var hinweis = document.createElement("font");
  var link = document.createElement("a");
  link.setAttribute("href","javascript:void(0);");
  link.appendChild(document.createTextNode("here"));
  link.addEventListener("click", gclh_showConfig, false);
  hinweis.setAttribute("color","#FF0000");
  hinweis.appendChild(document.createTextNode("You are using \"GC little helper\" - you have to change this option "));
  hinweis.appendChild(link);
  avatar_checkbox.checked = !settings_hide_avatar;
  avatar_checkbox.disabled = "disabled";
  avatar_checkbox.parentNode.appendChild(document.createElement("br"));
  avatar_checkbox.parentNode.appendChild(hinweis);
}

// Check for Updates
function checkVersion(){
  var url = "http://www.amshove.net/greasemonkey/updates.php";
  var time = new Date().getTime();
  var next_check = 24 * 60 * 60 * 1000; // Milliseconds
  var last_check = parseInt(GM_getValue("update_last_check"),10);
  var token = GM_getValue("token","");
  if(token == "") GM_setValue("token",""+Math.random());

  if(!last_check) last_check = 0;

  if((last_check + next_check) < time){
    if(GM_xmlhttpRequest) GM_xmlhttpRequest({
      method: 'GET',
      url: url,
      headers: {'User-Agent' : 'GM ' + scriptName + ' v' + scriptVersion + ' ' + last_check + ' ' + token},
      onload: function(result) {
        var version = result.responseText.match(/^([a-zA-Z0-9-_.]*)=([0-9.]*)/);
        var changelog = result.responseText.match(/changelog=((.*\n*)*)/);

        GM_setValue("new_version",version[2]);

        if(version[1] == scriptName && version[2] != scriptVersion){
          var text = "Version "+version[2]+" of "+scriptName+" greasemonkey script is available.\n"+
                  "You are currently using version "+scriptVersion+".\n\n"+
                  "Click OK to upgrade.\n";
          if(changelog) text += "\n\nChangelog:\n"+changelog[1];
          if(window.confirm(text)) GM_openInTab(url);
        }
      }
    });
    GM_setValue('update_last_check', time.toString());
  }
}

checkVersion();
} // Google Maps site