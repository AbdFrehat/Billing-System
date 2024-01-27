CREATE SEQUENCE profiles_seq START 1;
CREATE SEQUENCE authorities_seq START 1;
CREATE SEQUENCE groups_seq START 1;

CREATE TABLE groups (
    group_id INTEGER DEFAULT NEXTVAL('groups_seq'),
    group_name VARCHAR(32) NOT NULL,
    PRIMARY KEY (group_id)
);

CREATE TABLE authorities 
(
    authority_id INTEGER DEFAULT NEXTVAL('authorities_seq'),
    group_id INTEGER,
    authority_name VARCHAR(32) NOT NULL,
    PRIMARY KEY (authority_id),
    FOREIGN KEY (group_id) REFERENCES groups (group_id)
);

CREATE TABLE profiles
(
    profile_id INTEGER DEFAULT NEXTVAL('profiles_seq'),
    profile_name VARCHAR(255) NOT NULL,
    PRIMARY KEY (profile_id),
	UNIQUE(profile_name)
);

CREATE TABLE profiles_authorities
(
    profile_id INTEGER,
    authority_id INTEGER,
    PRIMARY KEY (profile_id, authority_id),
    FOREIGN KEY (authority_id) REFERENCES authorities(authority_id),
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id)
);

CREATE TABLE users
(
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    profile_id INTEGER NOT NULL,
    create_at TIMESTAMP NOT NULL,
    is_enabled INTEGER NOT NULL,
    is_account_expired INTEGER NOT NULL,
    is_credential_expired INTEGER NOT NULL,
    is_account_locked INTEGER NOT NULL,
    last_password_changed TIMESTAMP NOT NULL,
    country VARCHAR(64) NOT NULL,
    city VARCHAR(64) NOT NULL,
    street VARCHAR(64) NOT NULL,
    PRIMARY KEY (username),
    UNIQUE (email),
    FOREIGN KEY (profile_id) REFERENCES profiles(profile_id)
);



