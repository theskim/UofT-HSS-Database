DROP TABLE IF EXISTS CourseReview;
DROP TABLE IF EXISTS Course;
DROP TYPE IF EXISTS course_avg;
DROP TYPE IF EXISTS course_type;

CREATE TYPE course_type AS ENUM (
	'HSS', 'CS'
);

CREATE TYPE course_avg AS ENUM (
	'A+', 'A', 'A-', 'B+', 'B', 'B-', 'C+', 'C', 'C-', 'D+', 'D', 'D-', 'F', 'N'
);

CREATE TABLE IF NOT EXISTS Course (
    id SERIAL PRIMARY KEY,
    dept TEXT NOT NULL,
    code INT NOT NULL,
    campus TEXT NOT NULL,
    title TEXT NOT NULL,
    url TEXT,
    workload INT NOT NULL DEFAULT 0,
    cavg course_avg NOT NULL DEFAULT 'N',
    ctype course_type NOT NULL DEFAULT 'HSS',
    summer BOOLEAN NOT NULL DEFAULT FALSE,
    fall BOOLEAN NOT NULL DEFAULT FALSE,
    winter BOOLEAN NOT NULL DEFAULT FALSE,
    UNIQUE (dept, code, campus)
);

CREATE TABLE IF NOT EXISTS CourseReview (
    review_id SERIAL PRIMARY KEY,
    course_id INT NOT NULL,
    review TEXT NOT NULL,
    workload INT NOT NULL,
    cavg course_avg NOT NULL DEFAULT 'N',
    recommand BOOLEAN NOT NULL,

    FOREIGN KEY (course_id) REFERENCES Course(id)
      ON DELETE CASCADE ON UPDATE CASCADE
);
