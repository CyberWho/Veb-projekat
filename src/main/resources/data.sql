INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 1, 'pass', 'Marko', 'Markovic', '244444', 'markovic@gmail.com', '2020-05-07', 0, true, 'marko', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 2, 'pass', 'Ivana', 'Ivanovic', '232714', 'ivanovic@gmail.com', '2020-06-17', 0, true, 'ivana', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 3, 'pass', 'Jovan', 'Jovanovic', '555333', 'veliki@gmail.com', '2020-06-17', 1, true, 'veliki_menadzer', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Gledalac', 4, 'pedja', 'Predrag', 'Despotovic', '245564', 'despotovicpedja@gmail.com', '2020-06-18', 0, true, 'Pedja', true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved, super_admin) VALUES ('Admin', 5, 'adminpass', 'Admin', 'Adminovic', 'XXXXXX', 'admin@bioskopi.rs', '0001-01-01', 2, true, 'SuperAdmin', true, true);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 6, 'pass', 'Nikola', 'Nikolic', '564212', 'nikola@bioskopi.rs', '2020-06-19', 1, true, 'nikola_mngr', false);
INSERT INTO Korisnik(type, id, lozinka, ime, prezime, kontakt_tel, email, datum, uloga, aktivan, korisnicko_ime, approved) VALUES ('Menadzer', 7, 'pass', 'Darko', 'Darkovic', '564212', 'darko@bioskopi.rs', '2020-06-19', 1, true, 'darko_mngr', true);


INSERT INTO Bioskop(id, naziv, adresa, br_telefona, email) VALUES (1, 'Bioskop1', 'Mihajla Pupina 20', '455000', 'bioskop1@bioskopi.rs');
INSERT INTO Bioskop(id, naziv, adresa, br_telefona, email) VALUES (2, 'Bioskop2', 'Radnicka 12', '4785211', 'bioskop2@bioskopi.rs');

INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (1, 'Lajanje na zvezde', 'oppis', 'komedija', 95, 8.5);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (2, 'Pljačka trećeg rajha', '2 ludaka', 'komedija', 105, 8.7);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (3, 'Kad porastem biću kengur', 'kengur', 'komedija', 99, 8.6);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (4, 'Balkanska međa', 'kengur', 'triler', 151, 8.1);
INSERT INTO Film(id, naziv, opis, zanr, trajanje, prosecna_ocena) VALUES (5, 'Balkanski špijun', 'opis', 'drama', 99, 9);

INSERT INTO KORISNIK_BIOSKOPI(MENADZERI_ID, BIOSKOPI_ID) VALUES (3, 1);