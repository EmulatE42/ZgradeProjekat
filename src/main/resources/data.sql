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

INSERT INTO buildings.parliament(parliament_id) VALUES (-1);

INSERT INTO buildings.session(id, session_date, session_record, session_timetable, session_user_id, parliament_parliament_id)
  VALUES (-1, '2017-11-30 19:00:00', 'nesto', null, null, -1);

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
  VALUES (-2,'2017-11-16 01:00:00', -2, null, -1);

INSERT INTO buildings.location(
  dtype, location_id, basement_square, hallway_number_of_floors, apartment_floor, apartment_square, hallway_floor, hallway_square, building_building_id)
  VALUES ('Apartment',-1, null, null, 4, 44, null, null, -2);

INSERT INTO buildings.location(
  dtype, location_id, basement_square, hallway_number_of_floors, apartment_floor, apartment_square, hallway_floor, hallway_square, building_building_id)
  VALUES ('Apartment',-2, null, null, 5, 55, null, null, -2);

INSERT INTO buildings.tenant(
  id, password, username, firstname, tenant_is_building_manager, lastname)
  VALUES (-2,'$2a$10$m0vzull2J6eDyN4cqAsfguP2bbIZy//CuhPo9vRXgx4WFpdbZnUQq', 'hhh', 'hhh', false, 'hhh');

INSERT INTO buildings.tenant(
  id, password, username, firstname, tenant_is_building_manager, lastname)
  VALUES (-3,'$2a$10$g8bQYJ1ZkWM8H3SLEe/oYukJ3jxUrwOm2bEHh2CQllT/NJIm87RbC', 'aaa', 'aaa', false, 'aaa');

INSERT INTO buildings.user_authority(
	id, authority_id, user_id)
	VALUES (-2, -2, -2);

INSERT INTO buildings.user_authority(
	id, authority_id, user_id)
	VALUES (-3, -2, -3);

INSERT INTO buildings.tenant_apartments(
	tenant_id, apartments_location_id)
	VALUES (-3, -2);

INSERT INTO buildings.tenant_apartments(
	tenant_id, apartments_location_id)
	VALUES (-2, -1);

INSERT INTO buildings.location_owners(
	apartment_location_id, owners_id)
	VALUES (-2, -3);

INSERT INTO buildings.location_owners(
	apartment_location_id, owners_id)
	VALUES (-1, -2);

INSERT INTO buildings.responsible_person(
	responsible_person_id, responsible_person_description, responsible_person_is_tenant, institution_id, tenant_id)
	VALUES (-1, 'kvar1', true, null, -2);

INSERT INTO buildings.building_responsible_persons(
	building_building_id, responsible_persons_responsible_person_id)
	VALUES (-2, -1);