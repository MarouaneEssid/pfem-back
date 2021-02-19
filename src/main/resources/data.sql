INSERT INTO role (id, name) VALUES
(1, 'ADMIN'),
(2, 'COLLAB'),
(3, 'STUDENT');
commit;
INSERT INTO technology (`id`, `name`) VALUES
(1, 'java'),
(2, 'python'),
(3, 'angular'),
(4, 'react');
commit;

INSERT INTO user (`id`, `email`, `firstname`, `lastname`, `password`,`school`, `role_id`) VALUES
(1, 'khalil@hamdi.com', 'khalil', 'Hamdi', '$2a$10$h2GbnBb/KiObulLPdzsKn.NqxIReQaZpMF/lPF6w3ayEJQLF5K54a','School', 1),
(2, 'farouk@guizeni.com', 'farouk', 'Guizeni', '$2a$10$h2GbnBb/KiObulLPdzsKn.NqxIReQaZpMF/lPF6w3ayEJQLF5K54a','School', 1),
(3, 'amira@allani.com', 'amira', 'Alleni', '$2a$10$h2GbnBb/KiObulLPdzsKn.NqxIReQaZpMF/lPF6w3ayEJQLF5K54a','School', 1),
(4, 'marouane@essid.com', 'marouane', 'Essid', '$2a$10$h2GbnBb/KiObulLPdzsKn.NqxIReQaZpMF/lPF6w3ayEJQLF5K54a','School', 1),
(5, 'youssef@benfadhel.com', 'youssef', 'Ben fadhel', '$2a$10$h2GbnBb/KiObulLPdzsKn.NqxIReQaZpMF/lPF6w3ayEJQLF5K54a','School', 1);
COMMIT;
INSERT INTO subject (`id`, `description`, `end_date`, `publication_date`, `start_date`, `title`, `user_id`) VALUES
(1, 'this is project 1', '2021-01-13 09:51:04.000000', '2021-01-14 09:51:04.000000', '2021-01-12 00:00:00.000000', 'project 1', 2),
(2, 'this is project 2', '2021-05-07 00:00:00.000000', '2021-01-07 00:00:00.000000', '2021-02-07 00:00:00.000000', 'project 2', 2),
(3, 'this is project 3', '2021-05-07 00:00:00.000000', '2021-01-07 00:00:00.000000', '2021-02-07 00:00:00.000000', 'project 3', 2);
commit;

INSERT INTO application (`id`, `cover_letter`, `resume`, `subject_id`, `user_id`) VALUES
(1, 'this is a cover letter of user 3', 'resume/3', 1, 3),
(2, 'this is a cover letter of user 4', 'resume/4', 2, 4),
(3, 'this is a cover letter of user 4', 'resume/4', 3, 5);
commit;
