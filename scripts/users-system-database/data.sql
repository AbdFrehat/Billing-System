ALTER SEQUENCE authorities_seq RESTART WITH 1;

INSERT INTO groups (group_name) VALUES ('USERS');
INSERT INTO groups (group_name) VALUES ('PROFILES');
INSERT INTO groups (group_name) VALUES ('ORDERS');
INSERT INTO groups (group_name) VALUES ('RECEIPTS');
INSERT INTO groups (group_name) VALUES ('EXPORT');
INSERT INTO groups (group_name) VALUES ('ARCHIVE & RESTORE');

INSERT INTO authorities (authority_name, group_id) VALUES('CREATE_USERS', 1);
INSERT INTO authorities (authority_name, group_id) VALUES('DELETE_USERS', 1);
INSERT INTO authorities (authority_name, group_id) VALUES('UPDATE_USERS', 1);
INSERT INTO authorities (authority_name, group_id) VALUES('GET_USERS', 1);
INSERT INTO authorities (authority_name, group_id) VALUES('CREATE_PROFILES', 2);
INSERT INTO authorities (authority_name, group_id) VALUES('DELETE_PROFILES', 2);
INSERT INTO authorities (authority_name, group_id) VALUES('UPDATE_PROFILES', 2);
INSERT INTO authorities (authority_name, group_id) VALUES('GET_PROFILES', 2);
INSERT INTO authorities (authority_name, group_id) VALUES('ASSIGN_USERS_PROFILES', 2);
INSERT INTO authorities (authority_name, group_id) VALUES('CREATE_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('DELETE_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('UPDATE_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('GET_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('GET_FREE_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('GET_OPT_ORDERS', 3);
INSERT INTO authorities (authority_name, group_id) VALUES('PRINT_RECEIPT', 4);
INSERT INTO authorities (authority_name, group_id) VALUES('EXPORT_ORDERS', 5);
INSERT INTO authorities (authority_name, group_id) VALUES('ARCHIVE_ORDERS', 6);
INSERT INTO authorities (authority_name, group_id) VALUES('RESTORE_ORDERS', 6);


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


