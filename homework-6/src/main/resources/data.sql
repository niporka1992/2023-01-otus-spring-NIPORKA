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

insert into comments (`book_id`, `text`)
values (1, 'text'),
       (1, 'text2'),
       (2, 'text3');

