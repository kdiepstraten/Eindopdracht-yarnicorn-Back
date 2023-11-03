insert into roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO users (username, password)
VALUES ('MightyCarlos', 'Taylor');


INSERT INTO profile ( id, email, first_name, last_name)
VALUES ( 69, 'cgarcia@gmail.com', 'Carlos', 'Garcia');


INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (23, 'Artemis', 'Isager', 'Beetle Wing', '50% cotton en 50% alpaca', 4, 50, 'Wol perfect voor een sjaal', '10x10 = 23stx30rows', 'cotton');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (25, 'Hades', 'Isager', 'Steeple', '50% cotton en 50% cashmere', 6, 50, 'Wol perfect voor een trui', '10x10 = 23stx30rows', 'cashmere');

INSERT INTO review ( id, full_name, review)
VALUES (33, 'Taylor Zakhar Perez', 'What an amazing site is this! 11/10');

INSERT INTO reservation (id, full_name, email, street, street_number, zipcode, city, amount, comment)
VALUES (45, 'Taylor Zakhar Perez', 'tperez@gmail.com', 'Broadway blv', 10011, 10021, 'New York', 10, 'I cant wait to make a sweater')



