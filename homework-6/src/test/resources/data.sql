insert into authors(`name`, `surname`)
values ('Борис', 'Акунин'),
       ('Лев', 'Толстой'),
       ('Александр', 'Пушкин');

insert into genres (`name`)
values ('Фэнтези'),
       ('Роман');

insert into books (`name`, `author_id`, `genre_id`)
values ('Яма', 1, 1),
       ('Анна Каренина', 2, 2),
       ('Евгений Онегин', 3, 2);

insert into comments (`text`, `book_id`)
values ('text', 2),
       ('text2', 2),
       ('text3', 1),
       ('text4', 3)
