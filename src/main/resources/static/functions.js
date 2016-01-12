/**
 * diese funktion wird mit einer tabellenzeile gefüttert und gibt die eintrags-eigenschaften als assoziatives array zurück
 */
function convertRow(row){
	var elements = row.children;
	
	var entry = {
		'name': elements[1].innerHTML,
		'tags': elements[2].innerHTML
	};
	
	if(elements[3].children.length > 0){
		entry['file'] = elements[3].children[0].innerHTML;
	}else{
		entry['file'] = elements[3].innerHTML;
	}
	
	return entry;
}

/**
 * diese funktion wird mit dem aufrufenden element gefüttert.
 * sie füllt das html form zum löschen aus, macht dieses sichtbar und fokusiert darauf
 * example: onclick="del(this)"
 */
function del(element){
	closeDialogsExept('delete');
	
	var entry = convertRow(element.parentNode.parentNode);
	var dialog = document.forms.delete;
	
	dialog.querySelector('span.fileName').innerHTML = entry.name;
	dialog.name.value = entry.name;
	
	dialog.className = '';
	dialog.querySelector('input[type=button]').focus();
}

/**
 * diese funktion wird mit dem aufrufenden element gefüttert.
 * sie füllt das html form zum ändern aus, setzt den titel, macht es sichtbar und fokusiert darauf
 * example: onclick="edit(this)"
 */
function edit(element){
	closeDialogsExept('put');
	
	var entry = convertRow(element.parentNode.parentNode);
	var dialog = document.forms.put;
	
	dialog.querySelector('h3').innerHTML = 'Eintrag bearbeiten';
	dialog.name.value = entry.name;
	dialog.oldName.value = entry.name;
	dialog.tags.value = entry.tags;
	dialog.file.value = entry.file;
	dialog.mode.value = 'update';
	
	dialog.className = '';
	dialog.name.focus();
}

/**
 * diese funktiopn setzt den titel des html form zum ändern, macht es sichtbar und fokusiert darauf
 */
function create(){
	closeDialogsExept('put');
	
	var dialog = document.forms.put;
	
	dialog.querySelector('h3').innerHTML = 'Eintrag anlegen';
	dialog.name.value = '';
	dialog.tags.value = '';
	dialog.file.value = '';
	dialog.mode.value = 'insert';
	
	dialog.className = '';
	dialog.name.focus();
}

/**
 * macht alle dialoge ausser dem angegebenen unsichtbar
 */
function closeDialogsExept(thisOne){
	var dialogs = ['put','delete'];
	
	for(dialog in dialogs) {
		if(dialog != thisOne){
			document.forms[dialog].className='hidden';
		}
	}
}

function closeDialogs(){
	closeDialogsExept(null);
}