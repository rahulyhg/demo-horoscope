create table horoscope (
  id integer primary key,
  zodiac varchar(20) unique not null,
  description varchar(5000) not null,
);