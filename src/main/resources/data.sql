insert into roles(role_name) values ('ROLE_USER'), ('ROLE_ADMIN');

INSERT INTO profile ( id, email, first_name, last_name)
VALUES ( 69, 'cgarcia@gmail.com', 'Carlos', 'Garcia');
INSERT INTO profile ( id, email, first_name, last_name)
VALUES ( 70, 'bond@gmail.com', 'James', 'Bond');

INSERT INTO users (username, password) VALUES ('Jason', '$2a$12$QmyJ39JiFxmpOFrNqyvXw.q0aVHX6163W2zSMslupMvM0nu/ZSy4u');
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('Jason', 'ROLE_ADMIN');

INSERT INTO users (username, password) VALUES ('Jackson', '$2a$12$QmyJ39JiFxmpOFrNqyvXw.q0aVHX6163W2zSMslupMvM0nu/ZSy4u');
INSERT INTO users_roles (users_username, roles_role_name) VALUES ('Jackson', 'ROLE_USER');

INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (50, 'Artemis', 'Greek', 'Forrest Green', '50% cotton en 50% alpaca', 4, 50, 'Greek Artemis is spun from Peruvian sheep wool. The yarn is characterised by its lovely colours which are a mixture of different shades dyed into the wool before it is spun.  ', '10x10 = 21stx28rows', 'Alpaca');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (52, 'Hades', 'Greek', 'Steeple', '50% cotton en 50% cashmere', 6, 50, 'Greek Hades is a chunky single ply yarn with a rugged structure. It is well suited for thick outdoor sweaters and also works really well used for soft furnishings such as plaids and pillows.','10x10 = 24stx28rows', 'Cotton');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (53, 'Demeter', 'Greek', 'Mustard', '100% hennep', 2, 25, 'Greek Demeter is an ultra thin, two ply yarn made from 100% Alpaca. We use this yarn both individually, especially for scarves, but mainly we knit this yarn together with a strand of one of our other yarns', '10x10 = 23stx30rows', 'Plant');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (54, 'Hestia', 'Greek', 'Flaming Orange', '50% cotton en 50% yak', 4, 25, 'Greek Hestia is a chunky single ply yarn with a rugged structure. It is well suited for thick outdoor sweaters and also works really well used for soft furnishings such as plaids and pillow, not least when worked together with Silk Mohair.', '10x10 = 23stx30rows', 'Yak');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (55, 'Poseidon', 'Greek', 'Submerged', '100% wool', 4, 25, 'Greek Poseidon is spun from Peruvian sheep wool. The yarn is characterised by its lovely colours which are a mixture of different shades dyed into the wool before it is spun.' , '10x10 = 20stx30rows', 'Sheep');
INSERT INTO products (id, name, brand, color, blend, needle_size, length, description, gauge, category )
VALUES (56, 'Hera', 'Greek', 'Lilac', '10% possum, 20% cotton, 70% wool', 3, 50, 'Greek Hera is an ultra thin, two ply yarn made from 100% Alpaca. We use this yarn both individually, especially for scarves, but mainly we knit this yarn together with a strand of one of our other yarns', '10x10 = 28stx35rows', 'Exotic');


INSERT INTO review ( id, full_name, review)
VALUES (33, 'Taylor Zakhar Perez', 'What an amazing site is this! 11/10');
INSERT INTO review ( id, full_name, review)
VALUES (34, 'Zac Efron', 'Wow amazing! 10/10');
INSERT INTO review ( id, full_name, review)
VALUES (35, 'Chris Hemsworth', 'I love this site! 10/10');
INSERT INTO review ( id, full_name, review)
VALUES (36, 'Robert Downey Jr', 'Matthijs his site was better but still oke. 7/10');
INSERT INTO review ( id, full_name, review)
VALUES (37, 'Emma Stone', 'This is so cute!! 9/10');

INSERT INTO reservation (id, full_name, email, street, street_number, zipcode, city, amount, comment, product_id)
VALUES (50, 'Ricky Martin', 'rmartin@gmail.com', 'Main Street', 123, '45511', 'Barcelona', 20, 'Ai dios Mio, Muy bueno', 56);



