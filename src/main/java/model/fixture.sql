INSERT INTO auteur (name) VALUES ("Mozart");
INSERT INTO auteur (name) VALUES ("Big auteur");
INSERT INTO auteur (name) VALUES ("Victor Hugo");

INSERT INTO categorie (name) VALUES ("jeux_video");
INSERT INTO categorie (name) VALUES ("livre");
INSERT INTO categorie (name) VALUES ("album");
INSERT INTO categorie (name) VALUES ("film");

INSERT INTO genre (name) VALUES ("sf");
INSERT INTO genre (name) VALUES ("thriller");

INSERT INTO langue (name) VALUES ("francais");
INSERT INTO langue (name) VALUES ("vf");
INSERT INTO langue (name) VALUES ("anglais");

INSERT INTO origine (name) VALUES ("amazon");
INSERT INTO origine (name) VALUES ("ThePirateBay");

INSERT INTO plateforme (name) VALUES ("pc");
INSERT INTO plateforme (name) VALUES ("Playstation 3");

INSERT INTO support (name) VALUES ("dvd");
INSERT INTO support (name) VALUES ("papier");

INSERT INTO oeuvre (titre, note, date_acquisition, date_sortie, comment, auteur_id, genre_id, langue_id, origine_id, categorie_id) VALUES ("GTA4",3,now(),now(),"super bien", 3, 1, 1, 1, 1);
INSERT INTO oeuvre (titre, note, date_acquisition, date_sortie, comment, auteur_id, genre_id, langue_id, origine_id, categorie_id) VALUES ("Superman",3,now(),now(),"super bien", 3, 1, 1, 1, 4);
INSERT INTO oeuvre (titre, note, date_acquisition, date_sortie, comment, auteur_id, genre_id, langue_id, origine_id, categorie_id) VALUES ("Les misérables",3,now(),now(),"super bien", 3, 1, 1, 1, 2);
INSERT INTO oeuvre (titre, note, date_acquisition, date_sortie, comment, auteur_id, genre_id, langue_id, origine_id, categorie_id) VALUES ("Requiem",1,now(),now(),"super bien", 3, 1, 1, 1, 3);

INSERT INTO morceau (name, duree, oeuvre_id) VALUES ("toto","12:00:00",4);
INSERT INTO morceau (name, duree, oeuvre_id) VALUES ("toto","12:00:00" ,4);



