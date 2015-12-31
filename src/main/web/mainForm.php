<!DOCTYPE html>
<html>
	<head>
		<title>ReSTful</title>
		<link rel="Stylesheet" type="text/css" href="index.css">
		<script type="text/javascript" src="index.js"></script>
	</head>
	<body>
		<h1>ReSTful</h1>
		
		<span style="color:#FF0000; display:block;">
			<?php if(isset($err) && !empty($err)) echo $err; ?>
		</span>
		<span style="color:#00AAAA; display:block;">
			<?php if(isset($msg) && !empty($msg)) echo $msg; ?>
		</span>
		<span style="color:#00AA00; display:block;">
			<?php if(isset($dbg) && !empty($dbg)) echo $dbg; ?>
		</span>
		
		<h2>vorhandene Eintr&auml;ge</h2>
		
		<span onClick="create();" class="bt">Eintrag anlegen</span>
		<table border=1>
			<tr>
				<th>Aktionen</th>
				<th>Name</th>
				<th>Suchbegriffe</th>
				<th>Datei</th>
			</tr>
			<tr>
				<td>
					<span onClick="edit(this);" class="bt">bearbeiten</span>,
					<span onClick="del(this);" class="bt">l&ouml;schen</span>
				</td>
				<td>Beispiel</td>
				<td>beispiel, bsp</td>
				<td>
					<a href="files/beispiel.html">beispiel.html</a>
				</td>
			</tr>
			<?php if(isset($eintraege)) foreach($eintraege as $eintrag){ ?>
			<tr>
				<td>
					<span onClick="edit(this);" class="bt">bearbeiten</span>,
					<span onClick="del(this);" class="bt">l&ouml;schen</span>
				</td>
				<td><?php echo $eintrag['name']; ?></td>
				<td><?php echo $eintrag['tags']; ?></td>
				<td>
					<a href="files/<?php echo $eintrag['file']; ?>"><?php echo $eintrag['file']; ?></a>
				</td>
			</tr>
			<?php } ?>
		</table>
		
		<form name="put" class="hidden" method="POST">
			<h2><!-- Eintrag anlegen | Eintrag bearbeiten --></h2>
			
			<input type="text" name="name" placeHolder="Name">
			<input type="text" name="tags" placeHolder="Suchbegriffe">
			<input type="text" name="file" placeHolder="Datei">
			
			<input type="hidden" name="mode" value="<!-- insert | update | delete -->">
			<input type="submit" value="Speichern">
			<input type="button" onclick="closeDialogs();" value="Abbrechen">
		</form>
		
		<form name="delete" class="hidden" method="POST">
			<h2>Eintrag l&ouml;schen</h2>
		
			<input type="hidden" name="name">
			<p>
				Sind Sie sicher, dass Sie <span class="fileName"></span> l&ouml;schen wollen?
			</p>
			
			<input type="hidden" name="mode" value="delete">
			<input type="submit" value="Fortfahren">
			<input type="button" onclick="closeDialogs();" value="Abbrechen">
		</form>
		
		<div style="position:fixed; bottom:0; right:0; padding:5px;">
			created by Sebastian Steinkellner
		</div>
	</body>
</html>