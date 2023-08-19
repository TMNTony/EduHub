BEGIN TRANSACTION;


INSERT INTO users (username,password_hash,role) VALUES ('user1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('user3','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin1','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');
INSERT INTO users (username,password_hash,role) VALUES ('admin2','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

INSERT INTO course(course_name, description, difficulty, cost) VALUES ('Welcome Aboard', 'You need to buy our App!', 'easy', 0.00);
INSERT INTO course(course_name, description, difficulty, cost) VALUES ('Intro', 'How does it work?', 'easy', 0.00);
INSERT INTO course(course_name, description, difficulty, cost) VALUES ('Ready', 'What are you waiting for?', 'easy', 0.00);

INSERT INTO curriculum(course_id, title, content) VALUES (1, 'Welcome', 'You need to buy our App!');
INSERT INTO curriculum(course_id, title, content) VALUES (1, 'Intro', 'How does it work?');
INSERT INTO curriculum(course_id, title, content) VALUES (1, 'Welcome', 'What are you waiting for?');
INSERT INTO curriculum(course_id, title, content) VALUES (2, 'Welcome', 'You need to buy our App!');
INSERT INTO curriculum(course_id, title, content) VALUES (2, 'Intro', 'How does it work?');
INSERT INTO curriculum(course_id, title, content) VALUES (2, 'Welcome', 'What are you waiting for?');
INSERT INTO curriculum(course_id, title, content) VALUES (3, 'Welcome', 'You need to buy our App!');
INSERT INTO curriculum(course_id, title, content) VALUES (3, 'Intro', 'How does it work?');
INSERT INTO curriculum(course_id, title, content) VALUES (3, 'Welcome', 'What are you waiting for?');

INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(1, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(1, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(1, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(2, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(2, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(2, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(3, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(3, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(3, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(4, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(4, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(4, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(5, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(5, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(5, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(6, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(6, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(6, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(7, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(7, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(7, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(8, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(8, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(8, 3, 'Page THREE', 'Here is page THREE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(9, 1, 'Page ONE', 'Here is page ONE');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(9, 2, 'Page TWO', 'Here is page TWO');
INSERT INTO pages(curriculum_id, page_number, title, content) VALUES(9, 3, 'Page THREE', 'Here is page THREE');
COMMIT TRANSACTION;
