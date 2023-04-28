insert into authors(`name`, `surname`)
values ('Джоан', 'Роулинг'),
       ('Лев', 'Толстой'),
       ('Патрик', 'Зюскинд');

insert into genres (`name`)
values ('Фэнтези'),
       ('Роман');

insert into books (`name`, `author_id`, `genre_id`)
values ('Гарри Поттер', 1, 1),
       ('Война и мир', 2, 2),
       ('Парфюмер', 3, 2);

insert into comments (`text`, `book_id`)
values ('text', 1),
       ('text2', 1),
       ('text3', 2),
       ('text', 3)
