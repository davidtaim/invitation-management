insert into roles (id, name, is_active, created_at, updated_at)
values (
    '51eefd57-27c4-498a-9f5b-8c5d4d8da9a3',
    'ADMIN',
    true, 
    '2025-09-26T05:58:57.808904Z',
    '2025-09-26T05:58:57.808904Z'
);

insert into roles (id, name, is_active, created_at, updated_at)
values (
    '67c5fb73-7580-4e04-b8e8-ebd2ab97e692',
    'GUEST',
    true,
    '2025-09-26T06:13:55.141285400Z',
    '2025-09-26T06:13:55.141285400Z'
);

insert into roles (id, name, is_active, created_at, updated_at)
values (
    '2b0306c1-9a27-4385-94b1-d00a00c91bda',
    'ORGANIZER',
    true,
    '2025-09-29T03:49:12.410040943Z',
    '2025-09-29T03:49:12.410040943Z'
);

insert into users (id, name, email, password, role_id, created_at, updated_at, is_active)
values (
    '888fa8e9-a498-4cac-bfa7-b734d33ea9b3',
    'ADMIN David',
    'admin@gmail.com',
    '$2a$10$wMlJFcLeY1Ng0UVWECa4beACZJFXxS3IWjXY6NSQFf0fzgryHXVCu',
    '51eefd57-27c4-498a-9f5b-8c5d4d8da9a3',
    '2025-09-29T03:37:27.820324852Z',
    '2025-09-29T03:37:27.820325314Z',
    true
);

insert into users (id, name, email, password, role_id, created_at, updated_at, is_active)
values (
    'fd3d5d38-a559-44b8-b412-95851f610450',
    'ORGANIZER David',
    'organizer@gmail.com',
    '$2a$10$wMlJFcLeY1Ng0UVWECa4beACZJFXxS3IWjXY6NSQFf0fzgryHXVCu',
    '67c5fb73-7580-4e04-b8e8-ebd2ab97e692',
    '2025-09-29T03:37:27.820324852Z',
    '2025-09-29T03:37:27.820325314Z',
    true
);

insert into events (id, organizer_id, title, description, start_datetime, end_datetime, location, 
                    status, created_at, updated_at)
values (
    '5f549545-ab73-47b4-9a30-cb9d796bfc39',
    'fd3d5d38-a559-44b8-b412-95851f610450',
    'Boda de 1',
    'Descripcion de boda 1',
    '2025-09-29T03:37:27.820325Z',
    '2025-09-29T03:37:27.820325Z',
    'Salon de fiestas 1',
    0,
    '2025-09-29T03:37:27.820325Z',
    '2025-09-29T03:37:27.820325Z'
);

insert into events (id, organizer_id, title, description, start_datetime, end_datetime, location, 
                    status, created_at, updated_at)
values (
    '302958e0-d1c1-4df6-afc9-0f63503e407f',
    'fd3d5d38-a559-44b8-b412-95851f610450',
    'Boda de 2',
    'Descripcion de boda 2',
    '2025-09-29T03:37:27.820325Z',
    '2025-09-29T03:37:27.820325Z',
    'Salon de fiestas 2',
    0,
    '2025-09-29T03:37:27.820325Z',
    '2025-09-29T03:37:27.820325Z'
);