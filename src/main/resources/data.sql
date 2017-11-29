INSERT INTO buildings.authority(
	id, authority_name)
	VALUES (-1, 'ROLE_ADMIN');

INSERT INTO buildings.authority(
	id, authority_name)
	VALUES (-2, 'TENANT');

INSERT INTO buildings.authority(
	id, authority_name)
	VALUES (-3, 'RESPONSIBLE_PERSON');

INSERT INTO buildings.user(
	id, password, username)
	VALUES (-1, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a');

INSERT INTO buildings.user_authority(
	id, authority_id, user_id)
	VALUES (-1, -1, -1);

INSERT INTO buildings.address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-1, 'Novi Sad', 'Serbia', '44/3','21000','Brace Ribnikara');

INSERT INTO buildings.address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-2, 'Novi Sad', 'Serbia', '66/3','21000','Gogoljeva');

INSERT INTO buildings.building(
  building_id,building_date_of_construction, address_id, building_manager_id, building_parliament_id)
  VALUES (-1,'2017-11-16 01:00:00', -1, null, null);

INSERT INTO buildings.building(
  building_id,building_date_of_construction, address_id, building_manager_id, building_parliament_id)
  VALUES (-2,'2017-11-16 01:00:00', -2, null, null);

INSERT INTO buildings.parliament(parliament_id) VALUES (-1);

INSERT INTO buildings.session(id, session_date, session_record, session_timetable, session_user_id, parliament_parliament_id)
  VALUES (-1, '2017-11-30 19:00:00', 'nesto', null, null, null);