<!DOCTYPE html>
<html>
	<head>
		<title>ReSTful</title>
	</head>
	<body>
		<h1>ReSTful</h1>
		
		<h2>vorhandene Eintr&auml;ge</h2>
		
		<table border=1>
			<tr>
				<th>Name</th>
				<th>Suchbegriffe</th>
				<th>Datei</th>
			</tr>
			<tr>
				<td>Beispiel</td>
				<td>beispiel, bsp</td>
				<td>
					<a href="files/beispiel.html">beispiel.html</a>
				</td>
			</tr>
			<?php if(isset($eintraege)) foreach($eintraege as $eintrag){ ?>
			<tr>
				<td><?php echo $eintrag['name']; ?></td>
				<td><?php echo $eintrag['tags']; ?></td>
				<td>
					<a href="files/<?php echo $eintrag['file']; ?>"><?php echo $eintrag['file']; ?></a>
				</td>
			</tr>
			<?php } ?>
		</table>
		
		
		<h2>Eintrag anlegen</h2>
		
		<form method="PUT">
			<input type="text" name="name" placeHolder="Name">
			<input type="text" name="tags" placeHolder="Suchbegriffe">
			<input type="text" name="file" placeHolder="Datei">
			
			<input type="submit" value="speichern">
		</form>
		
		
		<h2>Eintrag bearbeiten</h2>
		
		<form method="PUT">
			<select name="name">
				<option disabled selected label="bitte w&auml;hlen">
				<?php if(isset($eintraege)) foreach($eintraege as $eintrag){ ?>
				<option label="<?php echo $eintrag['name']; ?>">
				<?php } ?>
			</select>
			<input type="text" name="tags" placeHolder="Suchbegriffe">
			<input type="text" name="file" placeHolder="Datei">
			
			<input type="submit" value="speichern">
		</form>
		
		
		<h2>Eintrag l&ouml;schen</h2>
		
		<form method="DELETE">
			<select name="name">
				<option disabled selected label="bitte w&auml;hlen">
				<?php if(isset($eintraege)) foreach($eintraege as $eintrag){ ?>
				<option label="<?php echo $eintrag['name']; ?>">
				<?php } ?>
			</select>
			<input type="submit" value="l&ouml;schen">
		</form>
		
		
		
		<div style="position:fixed; bottom:0; right:0; padding:5px;">
			created by Sebastian Steinkellner
		</div>
	</body>
</html>