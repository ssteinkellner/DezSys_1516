-- Autor: SSteinkellenr
-- Datum: 2015-12-14
-- Zweck: Datenbank fuer Aufgabe SOA und Rest

\c template1
DROP DATABASE IF EXISTS soarest;
DROP USER IF EXISTS soarest_user;

CREATE USER soarest_user PASSWORD 'soarest_passwort';
CREATE DATABASE soarest owner = soarest_user;
\c soarest


CREATE TABLE wissen (
    titel     VARCHAR(30),
	begriffe  VARCHAR(255),
	datei     VARCHAR(255),
	PRIMARY KEY (Titel)
);
-- in Datei sollte die Referenz auf den ausfuehrlichen Artikel gespeichert werden