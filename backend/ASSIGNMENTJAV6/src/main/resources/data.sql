INSERT INTO users (username, password, full_name, role)
VALUES ('admin', '$2a$10$u1G0VbE0hY3c/7zQqG8vSe4T4iKJQwG3oB1rDq8tKk3Y1rO7B9m6G', 'Admin', 'ROLE_ADMIN');
-- admin123

INSERT INTO users (username, password, full_name, role)
VALUES ('user', '$2a$10$K7Qz0bQdK/7u1H8V4eZ7uuaeFj1K2LzqYwQdF0qGv1K7Y3dP9sR1e', 'User', 'ROLE_USER');
-- user123

INSERT INTO book (id, title, author, price, description)
VALUES (1, 'Clean Code', 'Robert C. Martin', 25.0, 'A Handbook of Agile Software Craftsmanship');
INSERT INTO book (id, title, author, price, description)
VALUES (2, 'The Pragmatic Programmer', 'Andy Hunt', 30.0, 'Your Journey to Mastery');
