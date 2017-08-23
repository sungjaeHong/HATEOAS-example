delete from Contact;
insert into Contact (age, firstname, lastname, id) values (37,'Joe', 'Dalton', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (35,'William', 'Dalton', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (33,'Jack', 'Dalton', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (31,'Averell', 'Dalton', nextval('hibernate_sequence'));

insert into Contact (age, firstname, lastname, id) values (78,'Ma', 'Dalton', nextval('hibernate_sequence'));

insert into Contact (age, firstname, lastname, id) values (28,'Mickey', 'Mouse', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (25,'Minnie', 'Mouse', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (29,'Donald', 'Duck', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (27,'Daisy', 'Duck', nextval('hibernate_sequence'));

insert into Contact (age, firstname, lastname, id) values (8,'Riri', 'Duck', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (8,'Fifi', 'Duck', nextval('hibernate_sequence'));
insert into Contact (age, firstname, lastname, id) values (8,'Loulou', 'Duck', nextval('hibernate_sequence'));
DELETE FROM Address;
INSERT INTO Address (city, dong, id, contact_id) VALUES ('seoul', 'samsungdong', nextval('hibernate_sequence'), 1);
INSERT INTO Address (city, dong, id, contact_id) VALUES ('seoul', 'jungkokdong', nextval('hibernate_sequence'), 3);
INSERT INTO Address (city, dong, id, contact_id) VALUES ('seoul', 'sinlimdong', nextval('hibernate_sequence'), 5);