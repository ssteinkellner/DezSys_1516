<?php
$err = '';
$msg = '';
$dbg = '';

$eintraege = [
	['name' => 'Test', 'tags' => 'test', 'file' => 'test.html'],
	['name' => 'Lehrer', 'tags' => 'lehrer, schule, unterricht', 'file' => 'lehrer.html'],
	['name' => 'Wikipedia', 'tags' => 'wissen, sammlung, vorbild, weiterleitung', 'file' => 'wikipedia.php'],
	['name' => 'TGM', 'tags' => 'schule, unterricht, bauwerk', 'file' => 'tgm.html'],
];

include 'mainForm.php';
?>