<?php

if (version_compare(PHP_VERSION, '5.4') < 0){ ?>
<h3 style="color:red;">PHP 5.4 or higher needed!</h3>
<?php
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

include 'mainForm.php';
?>