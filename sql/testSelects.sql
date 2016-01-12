-- Autor: SSteinkellenr
-- Datum: 2015-12-14
-- Zweck: Datenbank fuer Aufgabe SOA und Rest

INSERT INTO wissen VALUES
('So wird getestet','lol, hallo, abc','test1.php'),
('Ich bin hier nur am Testen','das, ist, aber, lustig','test2.php'),
('Vom Testen und Probieren','haha, omg, rofl','test3.php'),
('Ich teste gerne','hoppla, ups, wow','test4.php');


SELECT * FROM wissen;

SELECT * FROM wissen
WHERE begriffe like '%ha%';

SELECT * FROM wissen
WHERE begriffe like '%ha%'
  AND begriffe like '%ol%';
  
SELECT * FROM wissen
WHERE begriffe like '%ha%'
   OR begriffe like '%wo%';
