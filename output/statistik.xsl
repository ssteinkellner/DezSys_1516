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
				<link rel="Stylesheet" type="text/css" href="main.css"></link>
				<script type="text/javascript" src="functions.js"></script>
			</head>
			<body>
				<table id="stat1" border="1">
					<tr>
						<th>Durchlaeufe</th>
						<th>Sekunden</th>
					</tr>
					<xsl:apply-templates select="run" />
				</table>

				<script type="text/javascript">setTimeout(function(){startStatistikAnimation('stat1');},300);</script>
				
				<span style="position: fixed; bottom: 0; right: 0; padding: 5px;">created by Sebastian Steinkellner</span>
			</body>
		</html>
	</xsl:template>
	
	<xsl:template match="run">
		<tr>
			<td><xsl:value-of select="rounds"/></td>
			<td>
				<div style="width: 0px;"><xsl:value-of select="time"/></div>
			</td>
		</tr>
	</xsl:template>
</xsl:stylesheet>