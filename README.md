# DezSys10

Aufgabe: **Load Balancing**

**Beginn**: 2016-02-12

## Team

- Kölbl Alexander
- Steinkellner Sebastian

## Aufgabenstellung

Es soll ein Load Balancer mit mindestens 2 unterschiedlichen Load-Balancing Methoden (je **[6Pkt]**) implementiert werden.
Eine Kombination von mehreren Methoden ist möglich. Die Berechnung bzw. das Service ist frei wählbar!
Folgende Load Balancing Methoden stehen zur Auswahl:
- Weighted Distribution
- Least Connection
- Response Time
- Server Probes

Um die Komplexität zu steigern, soll zusätzlich eine "Session Persistence" implementiert werden. **[3Pkt]**

Vertiefend soll eine Open-Source Applikation aus folgender Liste ausgewählt und installiert werden. **[3Pkt]**
https://www.inlab.de/articles/free-and-open-source-load-balancing-software-and-projects.html

### Auslastung

Es sollen die einzelnen Server-Instanzen in folgenden Punkten belastet (Memory, CPU Cycles) werden können.
Bedenken Sie dabei, dass die einzelnen Load Balancing Methoden unterschiedlich auf diese Auslastung reagieren werden.
Dokumentieren Sie dabei aufkommenden Probleme ausführlich.

### Tests

Die Tests sollen so aufgebaut sein, dass in der Gruppe jedes Mitglied mehrere Server fahren und ein Gruppenmitglied mehrere Anfragen an den Load Balancer stellen. Für die Abnahme wird empfohlen, dass jeder Server eine Ausgabe mit entsprechenden Informationen ausgibt, damit die Verteilung der Anfragen demonstriert werden kann.

## Abgabe

Gepackt als ausführbares JAR:
- Protokoll mit Designüberlegungen / Umsetzung / Testszenarien
- Sourcecode (mit allen notwendigen Bibliotheken)
- Java-Doc
- Build-Management-Tool
