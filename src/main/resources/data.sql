INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 1, 'pass', 'Marko', 'Markovic', '244444', 'markovic@gmail.com', '2020-05-07', 0, true, 'marko', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 2, 'pass', 'Ivana', 'Ivanovic', '232714', 'ivanovic@gmail.com', '2020-06-17', 0, true, 'ivana', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 3, 'pass', 'Jovan', 'Jovanovic', '555333', 'veliki@gmail.com', '2020-06-17', 1, true, 'veliki_menadzer', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 4, 'pedja', 'Predrag', 'Despotovic', '245564', 'despotovicpedja@gmail.com', '2020-06-18', 0, true, 'Pedja', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved, super_admin) VALUES ('Admin', 5, 'adminpass', 'Admin', 'Adminovic', 'XXXXXX', 'admin@bioskopi.rs', '0001-01-01', 2, true, 'SuperAdmin', true, true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 6, 'pass', 'Nikola', 'Nikolic', '564212', 'nikola@bioskopi.rs', '2020-06-19', 1, true, 'nikola_mngr', false);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 7, 'pass', 'Darko', 'Darkovic', '564212', 'darko@bioskopi.rs', '2020-06-19', 1, true, 'darko_mngr', true);


INSERT INTO Bioskop(id, naziv, adresa, br_telefona, email) VALUES (1, 'Bioskop1', 'Mihajla Pupina 20', '455000', 'bioskop1@bioskopi.rs');
INSERT INTO Bioskop(id, naziv, adresa, br_telefona, email) VALUES (2, 'Bioskop2', 'Radnicka 12', '4785211', 'bioskop2@bioskopi.rs');

INSERT INTO Sala(id, naziv, kapacitet, bioskop_id) VALUES (1, 'mala sala', 60, 2);
INSERT INTO Sala(id, naziv, kapacitet, bioskop_id) VALUES (2, 'velika sala', 100, 1);

--INSERT INTO BIOSKOP_SALE(BIOSKOP_ID, SALE_ID) VALUES (1, 2);

INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (1, 'Lajanje na zvezde', 'oppis', 'komedija', 95, 8.5);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (2, 'Pljačka trećeg rajha', '2 ludaka', 'komedija', 105, 8.7);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (3, 'Kad porastem biću kengur', 'kengur', 'komedija', 99, 8.6);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (4, 'Balkanska međa', 'kengur', 'triler', 151, 8.1);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (5, 'Balkanski špijun', 'opis', 'drama', 99, 9);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (6, 'Ko to tamo peva', 'opis', 'komedija', 97, 8.9);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (7, 'Varljivo leto 68.', 'opis', 'komedija', 91, 8.6);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (8, 'Mi nismo anđeli', 'opis', 'komedija', 98, 8.4);

INSERT INTO KORISNIK_BIOSKOPI(MENADZERI_ID, BIOSKOPI_ID) VALUES (3, 1);

INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (1, 1, 250, '2020-7-25 15:00');
INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (2, 1, 250, '2020-7-25 16:00');
INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (3, 1, 350, '2020-7-26 15:00');
INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (4, 1, 450, '2020-7-26 17:00');
INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (5, 2, 250, '2020-7-26 15:00');
INSERT INTO Projekcija (film_id,bioskop_id, cena, datumvreme) VALUES (6, 2, 250, '2020-7-25 16:00');

--INSERT INTO SALA_LISTA_TERMINSKIH_PROJEKCIJA(sala_id, lista_terminskih_projekcija_id) VALUES ()