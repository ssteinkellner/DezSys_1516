-- Autor: SSteinkellenr
-- Datum: 2015-12-14
-- Zweck: Datenbank fuer Aufgabe SOA und Rest

\c template1
DROP DATABASE IF EXISTS soarest;
CREATE DATABASE soarest;
\c soarest


CREATE TABLE wissen (
    titel     VARCHAR(30),
	begriffe  VARCHAR(255),
	datei     VARCHAR(255),
	PRIMARY KEY (Titel)
);
-- in Datei sollte die Referenz auf den ausfuehrlichen Artikel gespeichert werden