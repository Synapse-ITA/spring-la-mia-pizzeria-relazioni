-- insert pizza
INSERT INTO pizza (name, price, description, image) VALUES ('Margherita', 9.99, 'Pizza Margherita classica', '/images/margherita.jpg');
INSERT INTO pizza (name, price, description, image) VALUES ('Capricciosa', 11.99, 'Pizza Capricciosa speciale', '/images/capricciosa.jpg');
-- insert offerte speciali
INSERT INTO special_offers (pizza_id, offer_title, start_time, end_time) VALUES (1, 'Discount20', '2024-01-01', '2024-02-01');
INSERT INTO special_offers (pizza_id, offer_title, start_time, end_time) VALUES (1, 'Discount10', '2024-02-02', '2024-03-02');

INSERT INTO ingredients (name, description) VALUES('pomodoro', 'passata di pomodoro');
INSERT INTO ingredients (name, description) VALUES('mozzarella', 'mozzarella di bufala');
INSERT INTO ingredients (name, description) VALUES('aglio', '');