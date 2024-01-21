ALTER SEQUENCE authorities_seq RESTART WITH 1;

INSERT INTO authorities (authority_name) VALUES('CREATE_USERS');
INSERT INTO authorities (authority_name) VALUES('DELETE_USERS');
INSERT INTO authorities (authority_name) VALUES('UPDATE_USERS');
INSERT INTO authorities (authority_name) VALUES('GET_USERS');
INSERT INTO authorities (authority_name) VALUES('CREATE_PROFILES');
INSERT INTO authorities (authority_name) VALUES('DELETE_PROFILES');
INSERT INTO authorities (authority_name) VALUES('UPDATE_PROFILES');
INSERT INTO authorities (authority_name) VALUES('GET_PROFILES');
INSERT INTO authorities (authority_name) VALUES('CREATE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('DELETE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('UPDATE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('GET_ORDERS');
INSERT INTO authorities (authority_name) VALUES('GET_FREE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('GET_OPT_ORDERS');
INSERT INTO authorities (authority_name) VALUES('EXPORT_ORDERS');
INSERT INTO authorities (authority_name) VALUES('ARCHIVE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('RESTORE_ORDERS');
INSERT INTO authorities (authority_name) VALUES('PRINT_RECEIPT');
INSERT INTO authorities (authority_name) VALUES('ASSIGN_USERS_PROFILES');


ALTER SEQUENCE profiles_seq RESTART WITH 1;

INSERT INTO profiles (profile_name) VALUES ('admin');

INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 1);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 2);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 3);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 4);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 5);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 6);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 7);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 8);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 9);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 10);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 11);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 12);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 13);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 14);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 15);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 16);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 17);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 18);
INSERT INTO profiles_authorities (profile_id, authority_id) VALUES (1, 19);


