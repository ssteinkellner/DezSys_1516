/*
autor: ssteinkellner
version: 2016.01.08
*/

function startStatistikAnimation(tableid){
	var tabelle = document.getElementById(tableid);
	
	var balken = tabelle.querySelectorAll('td div');
	for(i=0;i<balken.length;i++){
		var b = balken[i];
		console.debug(b);
		b.style.width = b.innerHTML+'px';
	}
}