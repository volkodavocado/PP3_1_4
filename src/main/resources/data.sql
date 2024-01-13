insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN');

insert into users (email, password)
values
('user@mail.ru', '$2a$12$JSYjkbR.SUVQpn/Vp7Y3bOD2.IsS17QvRqCtjK9OATUo/rVdFX/Ym');

insert into users_roles (user_id, role_id) values (1, 2);