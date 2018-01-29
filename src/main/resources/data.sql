INSERT INTO authority(
	id, authority_name)
	VALUES (-1, 'ROLE_ADMIN');

INSERT INTO authority(
	id, authority_name)
	VALUES (-2, 'TENANT');

INSERT INTO authority(
	id, authority_name)
	VALUES (-3, 'RESPONSIBLE_PERSON');

INSERT INTO authority(
	id, authority_name)
	VALUES (-4, 'INSTITUTION');

INSERT INTO authority(
	id, authority_name)
	VALUES (-5, 'FIRM');

INSERT INTO user(
	id, password, username)
	VALUES (-1, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a');

INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (-1, -1, -1);

INSERT INTO parliament(parliament_id) VALUES (-1);



INSERT INTO session(id, session_date, session_record, session_timetable, session_user_id, parliament_parliament_id)
  VALUES (-1, '2017-11-24 20:00:00', 'https://docs.google.com/document/d/1LG2YtbGc_R5C3tcXuRIcGYEV3GxQwnbRXZAWDjyJzls/edit', 'Nesto', -3, -1);

INSERT INTO session(id, session_date, session_record, session_timetable, session_user_id, parliament_parliament_id)
  VALUES (1, '2018-01-27 21:00:00', null, null, -3, -1);

INSERT INTO session(id, session_date, session_record, session_timetable, session_user_id, parliament_parliament_id)
  VALUES (2, '2018-01-30 21:00:00', null, null, -3, -1);

INSERT INTO topic(topic_id, topic_accepted, topic_description, pos_votes, neg_votes, topic_user_id, session_id)
  VALUES (-1, false, 'Da li cemo kreciti zgradu?', 0, 0, -3, -1);

INSERT INTO topic(topic_id, topic_accepted, topic_description, pos_votes, neg_votes, topic_user_id, session_id)
  VALUES (2, false, 'Tacka dnevnog reda 1', 0, 0, -3, -1);

INSERT INTO topic(topic_id, topic_accepted, topic_description, pos_votes, neg_votes, topic_user_id, session_id)
  VALUES (3, false, 'Tacka dnevnog reda 2', 0, 0, -3, -1);

INSERT INTO topic(topic_id, topic_accepted, topic_description, pos_votes, neg_votes, topic_user_id, session_id)
  VALUES (4, false, 'Tacka dnevnog reda 3', 0, 0, -3, -1);

INSERT INTO topic(topic_id, topic_accepted, topic_description, pos_votes, neg_votes, topic_user_id, session_id)
  VALUES (1, false, 'Da li cemo menjati ulazna vrata?', 0, 0, -3, 2);

INSERT INTO address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-1, 'Novi Sad', 'Serbia', '44/3','21000','Brace Ribnikara');

INSERT INTO address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-2, 'Novi Sad', 'Serbia', '66/3','21000','Gogoljeva');

INSERT INTO building(
  building_id,building_date_of_construction, address_id, building_manager_id, building_parliament_id)
  VALUES (-1,'2017-11-16 01:00:00', -1, null, null);

INSERT INTO building(
  building_id,building_date_of_construction, address_id, building_manager_id, building_parliament_id)
  VALUES (-2,'2017-11-16 01:00:00', -2, null, -1);

INSERT INTO location(
  dtype, location_id, basement_square, hallway_number_of_floors, apartment_floor, apartment_square, hallway_floor, hallway_square, building_building_id)
  VALUES ('Apartment',-1, null, null, 4, 44, null, null, -2);

INSERT INTO location(
  dtype, location_id, basement_square, hallway_number_of_floors, apartment_floor, apartment_square, hallway_floor, hallway_square, building_building_id)
  VALUES ('Apartment',-2, null, null, 5, 55, null, null, -2);

INSERT INTO tenant(
  id, password, username, firstname, tenant_is_building_manager, lastname, tenant_is_responsible)
  VALUES (-2,'$2a$10$m0vzull2J6eDyN4cqAsfguP2bbIZy//CuhPo9vRXgx4WFpdbZnUQq', 'hhh', 'hhh', false, 'hhh', false);
  
 INSERT INTO tenant(
  id, password, username, firstname, tenant_is_building_manager, lastname, tenant_is_responsible)
  VALUES (-3,'$2a$10$g8bQYJ1ZkWM8H3SLEe/oYukJ3jxUrwOm2bEHh2CQllT/NJIm87RbC', 'aaa', 'aaa', true, 'aaa', false);

INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (-2, -2, -2);

INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (-3, -2, -3);

INSERT INTO tenant_apartments(
	tenant_id, apartments_location_id)
	VALUES (-3, -2);

INSERT INTO tenant_apartments(
	tenant_id, apartments_location_id)
	VALUES (-2, -1);

INSERT INTO location_owners(
	apartment_location_id, owners_id)
	VALUES (-2, -3);

INSERT INTO location_owners(
	apartment_location_id, owners_id)
	VALUES (-1, -2);

INSERT INTO responsible_person(
	responsible_person_id, responsible_person_description, responsible_person_is_tenant, institution_id, tenant_id)
	VALUES (-1, 'kvar1', true, null, -2);

INSERT INTO building_responsible_persons(
	building_building_id, responsible_persons_responsible_person_id)
	VALUES (-2, -1);

INSERT INTO address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-3, 'Novi Sad', 'Serbia', '66/3','21000','Gogoljeva');

INSERT INTO institution(
  id, password, username, institution_name, address_id)
  VALUES (-4,'$2a$10$CvBkI9i9MlMbdYQAad3yB.YekOzSkw/nWwNo8VJQjBmV8IK8/Y3f2', 'kkk', 'kkk', -3);

INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (-4, -4, -4);

INSERT INTO address(
  id, address_city, address_country, address_number, address_postal_code, address_street)
  VALUES (-4, 'Novi Sad', 'Serbia', '67/3','21000','Gogoljeva');

INSERT INTO firm(
  id, password, username, firm_description, firm_name, address_id)
  VALUES (-5,'$2a$10$imgDY2q34lSSmApMya.nv.STB3uOo4mt4olWReZwrmXdHfRdRe44m', 'mmm', 'mmm', 'mmm', -4);

INSERT INTO user_authority(
	id, authority_id, user_id)
	VALUES (-5, -5, -5);

INSERT INTO responsible_person(
	responsible_person_id, responsible_person_description, responsible_person_is_tenant, institution_id, tenant_id)
	VALUES (-2, 'krecenje', false, -4, null);

INSERT INTO building_responsible_persons(
	building_building_id, responsible_persons_responsible_person_id)
	VALUES (-2, -2);

INSERT INTO bug(
	bug_id, bug_date_of_bug, bug_description, bug_finished, bill_id, responsible_firm_id, responsible_person_responsible_person_id, bug_paid)
	VALUES (-1, '2017-12-03 19:52:14', 'krecenje sobe', false, null, -5,-2, false);


INSERT INTO location_bugs(
	location_location_id, bugs_bug_id)
	VALUES (-2, -1);

INSERT INTO responsible_person_bugs(
	responsible_person_responsible_person_id, bugs_bug_id)
	VALUES (-2, -1);

INSERT INTO firm_bugs(
	firm_id, bugs_bug_id)
	VALUES (-5, -1);


INSERT INTO survey(
  survey_id,building_id, date_survey, survey_description)
  VALUES(-1,-2,'31/12/2017','Cistoca');

INSERT INTO question(
  question_id,choices,second_type,question_text,third_type,q1)
  VALUES(-1,'a,b,c,d',true,'fgh',false,-1);

INSERT INTO question(
  question_id,choices,second_type,question_text,third_type,q1)
  VALUES(-2,'1a,1b2,4d4',false,'mozda',true,-1);

INSERT INTO answer(
  answer_id,choiced,rate,text,a1)
  VALUES(-1,'asd',1,'zxc',-1);

INSERT INTO answer(
  answer_id,choiced,rate,text,a1)
  VALUES(-3,'zum',2,'muz',-1);

INSERT INTO answer(
  answer_id,choiced,rate,text,a1)
  VALUES(-2,'qwe',1,'fgh',-2);

INSERT INTO public_notification(
  public_notificationid, date_public_notification, username, text)
  VALUES(-1,'31/12/2017','Pera','NEMA VODE');






