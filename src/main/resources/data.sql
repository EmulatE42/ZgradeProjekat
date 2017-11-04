INSERT INTO buildings.authority(
	id, authority_name)
	VALUES (-1, 'ROLE_ADMIN');

INSERT INTO buildings.user(
	id, password, username)
	VALUES (-1, '$2a$10$S3rxpwjnJUrmgMrnMCJo8eIRCFvCcmzuPi5Y3Okz67i/2sj6xMfau', 'a');

INSERT INTO buildings.user_authority(
	id, authority_id, user_id)
	VALUES (-1, -1, -1);