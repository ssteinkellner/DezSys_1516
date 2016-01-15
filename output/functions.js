/*
autor: ssteinkellner
version: 2016.01.08
*/

function startStatistikAnimation(){
	var tabellen = document.querySelectorAll('table.stat');
	
	for(j=0;j<tabellen.length;j++){
		var balken = tabellen[j].querySelectorAll('td div');
		for(i=0;i<balken.length;i++){
			var b = balken[i];
			console.debug(b);
			b.style.width = b.innerHTML+'px';
		}
	}
}