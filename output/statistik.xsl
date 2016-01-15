<!--
autor: ssteinkellner
version: 2016.01.08
-->
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns="http://www.w3.org/1999/xhtml">
	
	<xsl:output method="html" indent="yes" encoding="UTF-8"/>
	
	<xsl:template match="/data">
		<html>
			<head>
				<title>Statistik</title>
				<link rel="Stylesheet" type="text/css" href="main.css" />
				<link rel="Stylesheet" type="text/css" href="layout.css" />
				<script type="text/javascript" src="functions.js"></script>
			</head>
			<body>
				<div id="header">
					<h1>GPGPU Statistik</h1>
				</div>
				
				<xsl:apply-templates select="system" />

				<script type="text/javascript">setTimeout(function(){startStatistikAnimation('stat1');},300);</script>
				
				<span id="footer">created by Sebastian Steinkellner</span>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="system">
		<table class="stat" border="1">
			<tr>
				<th>System</th>
				<td>
					<table class="padded">
						<tr>
							<td>Betriebssystem</td>
							<td><xsl:value-of select="@os"/></td>
						</tr>
						<tr>
							<td>Programmiersprache</td>
							<td><xsl:value-of select="@lang"/></td>
						</tr>
						<tr>
							<td>Processing Unit</td>
							<td><xsl:value-of select="@unit"/></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<th>Aufgaben</th>
				<th>Sekunden</th>
			</tr>
			<xsl:apply-templates select="run" />
		</table>
	</xsl:template>
	
	<xsl:template match="run">
		<tr>
			<td><xsl:value-of select="tasks"/></td>
			<td>
				<div style="width: 0px;"><xsl:value-of select="time"/></div>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>