<?php

if(version_compare(PHP_VERSION, '5.4') < 0){
	echo '<h3 style="color:red;">PHP 5.4 or higher needed!</h3>'."\n";
	include 'foot.php';
	exit(0);
} 

$err = '';
$msg = '';
$dbg = '';

$eintraege = [
	['name' => 'Beispiel', 'tags' => 'bsp', 'file' => 'beispiel.html'],
	['name' => 'Test', 'tags' => 'test', 'file' => 'test.html'],
	['name' => 'Lehrer', 'tags' => 'lehrer, schule, unterricht', 'file' => 'lehrer.html'],
	['name' => 'Wikipedia', 'tags' => 'wissen, sammlung, vorbild, weiterleitung', 'file' => 'wikipedia.php'],
	['name' => 'TGM', 'tags' => 'schule, unterricht, bauwerk', 'file' => 'tgm.html'],
];

if(isset($_POST['mode'])) switch($_POST['mode']){
	case "insert":
		$eintrag = [];
		
		foreach(['name','tags','file'] as $param){
			if(isset($_POST[$param])){
				$eintrag[$param] = $_POST[$param];
			}else{
				$err .= 'Parameter <i>'.$param.'</i> fehlt!'."<br />\n";
				break;
			}
		}
		
		array_push($eintraege, $eintrag);
		$success = true;
		
		if($success){
			$msg .= $_POST['name'].' erfolgreich erstellt.'."<br />\n";
		}else{
			$err .= 'Fehler beim erstellen von '.$_POST['name'].'!'."<br />\n";
		}
		
		break;
	case "update":
		$eintrag = [];
		
		if(!isset($_POST['oldName'])){
			$err .= 'Parameter <i>oldName</i> fehlt!'."<br />\n";
			break;
		}
		
		foreach(['name','tags','file'] as $param){
			if(isset($_POST[$param])){
				$eintrag[$param] = $_POST[$param];
			}else{
				$err .= 'Parameter <i>'.$param.'</i> fehlt!'."<br />\n";
				break;
			}
		}
		
		$success = false;
		foreach($eintraege as $index => $eintrag2){
			$dbg .= $eintrag2['name'].' == '.$_POST['oldName']."<br />\n";
			if($eintrag2['name'] == $_POST['oldName']){
				$eintraege[$index] = $eintrag;
				$success = true;
				break;
			}
		}
		
		if($success){
			$msg .= $_POST['oldName'].' erfolgreich zu '.$_POST['name'].' ge&auml;ndert.'."<br />\n";
		}else{
			$err .= 'Fehler beim &auml;ndern von '.$_POST['oldName'].'!'."<br />\n";
		}
		
		break;
	case "delete":
		if(!isset($_POST['name'])){
			$err .= 'Parameter <i>name</i> fehlt!'."<br />\n";
			break;
		}
		
		$success = false;
		foreach($eintraege as $index => $eintrag){
			$dbg .= $eintrag['name'].' == '.$_POST['name']."<br />\n";
			if($eintrag['name'] == $_POST['name']){
				unset($eintraege[$index]);
				$success = true;
				break;
			}
		}
		
		if($success){
			$msg .= $_POST['name'].' erfolgreich gel&ouml;scht.'."<br />\n";
		}else{
			$err .= 'Fehler beim l&ouml;schen von '.$_POST['name'].'!'."<br />\n";
		}
		
		break;
}

include 'mainForm.php';
?>