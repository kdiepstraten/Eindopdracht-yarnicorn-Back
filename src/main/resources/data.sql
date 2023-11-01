insert into roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES ('MightyCarlos', 'Taylor2');


INSERT INTO profile (confirm_password, email, first_name, last_name, password, username)
VALUES ('Taylor2', 'cgarcia@gmail.com', 'Carlos', 'Garcia', 'Taylor2', 'MightyCarlos');
