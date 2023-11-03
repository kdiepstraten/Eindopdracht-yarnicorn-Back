insert into roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES ('MightyCarlos', 'Taylor');


INSERT INTO profile ( id, confirm_password, email, first_name, last_name, password, username )
VALUES ( 69, 'Taylor2', 'cgarcia@gmail.com', 'Carlos', 'Garcia', 'Taylor2', 'MightyCarlos');

INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (23, 'Artemis', 'Isager', 'Beetle Wing', '50% cotton en 50% alpaca', 4, 50, 'Wol perfect voor een sjaal', '10x10 = 23stx30rows', 'cotton');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (25, 'Hades', 'Isager', 'Steeple', '50% cotton en 50% cashmere', 6, 50, 'Wol perfect voor een trui', '10x10 = 23stx30rows', 'cashmere');