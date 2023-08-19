BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);
CREATE TABLE course (
    course_id serial,
    course_name varchar(100) NOT NULL,
    description varchar(100) NOT NULL,
    difficulty varchar(50) NOT NULL,
    cost numeric DEFAULT 0.00,
    CONSTRAINT PK_course PRIMARY KEY (course_id)

);

CREATE TABLE user_course (
   user_id int NOT NULL,
   course_id int NOT NULL,
   CONSTRAINT PK_user_course PRIMARY KEY (user_id, course_id),
   CONSTRAINT FK_user_course_user FOREIGN KEY (user_id) REFERENCES users (user_id),
   CONSTRAINT FK_user_course_course FOREIGN KEY (course_id) REFERENCES course (course_id)

);

CREATE TABLE curriculum (
    curriculum_id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    title varchar(255) NOT NULL,
    content TEXT NOT NULL,
    FOREIGN KEY (course_id) REFERENCES course(course_id) ON DELETE CASCADE
);

CREATE TABLE pages (
  page_id SERIAL PRIMARY KEY,
  curriculum_id INT NOT NULL,
  page_number INT NOT NULL,
  title VARCHAR(255) NOT NULL,
  content TEXT NOT NULL,
  FOREIGN KEY (curriculum_id) REFERENCES curriculum(curriculum_id) ON DELETE CASCADE

  );

  CREATE TABLE homework(
  homework_id SERIAL PRIMARY KEY,
  parent_id INT NOT NULL,
  user_id INT,
  link TEXT,
  score int
  );


COMMIT TRANSACTION;
