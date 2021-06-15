CREATE TABLE book (
  id serial NOT NULL,
  author varchar(255),
  launch_date date NOT NULL,
  price decimal(65,2) NOT NULL,
  title varchar(255),
  CONSTRAINT book_pkey PRIMARY KEY (id)
);
