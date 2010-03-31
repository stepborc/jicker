/*// Waypoints aufbereiten
wps = getXEle("//table[@class='Table']")
waypoints = "";

if (null != wps && wps.childNodes.length > 0) {
	alert('Waypoints identifiziert:' + wps.nodeName);
	var cnlaenge = wps.childNodes.length;
	alert(cnlaenge  + ' Zellen');
	alert(wps.childNodes[3].textContent + '_'+ wps.childNodes[3].childNodes.length);
	for(i = 1;i <= wps.childNodes[3].childNodes.length;i = i + 2){
		alert('i:' + i +' ' + wps.childNodes[3].childNodes[i].nodeName + '->' +wps.childNodes[3].childNodes[i].textContent);
	}
}
*/