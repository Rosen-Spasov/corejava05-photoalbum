INSERT INTO USERS
VALUES(1, 'volen', 'Volen', 'Dimitrov', 'dimitrov')
/

INSERT INTO USERS
VALUES(2, 'rosen', 'Rosen', 'Spasov', 'spasov')
/

INSERT INTO USERS
VALUES(3, 'zahary', 'Zahary', 'Anastasov', 'anastasov')
/

INSERT INTO USERS
VALUES(4, 'marin', 'Marin', 'Dimitrov', 'dimitrov')
/

INSERT INTO CATEGORIES
VALUES(1, null, 1, 'CSKA', './PhotoAlbum/volen/CSKA')
/

INSERT INTO CATEGORIES
VALUES(2, null, 2, 'CSKA', './PhotoAlbum/rosen/CSKA')
/

INSERT INTO CATEGORIES
VALUES(3, null, 3, 'CSKA', './PhotoAlbum/zahary/CSKA')
/

INSERT INTO CATEGORIES
VALUES(4, null, 4, 'CSKA', './PhotoAlbum/marin/CSKA')
/

INSERT INTO CATEGORIES
VALUES(5, 1, null, 'CSKA', './PhotoAlbum/volen/CSKA/CSKA1')
/

INSERT INTO PHOTOS
VALUES(1, 'zahary_na_plaja.jpg', './PhotoAlbum/zahary/CSKA/zahary_na_plaja.jpg', 3)
/

INSERT INTO PHOTOS
VALUES(2, 'zahary_pravi_web.jpg', './PhotoAlbum/zahary/CSKA/zahary_pravi_web.jpg', 3)
/

INSERT INTO PHOTOS
VALUES(3, 'test.jpg', './PhotoAlbum/zahary/CSKA/test.jpg', 3)
/

COMMIT
/