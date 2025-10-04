insert into users (id, name, middle_name, last_name, email, password, role, created_at, updated_at, is_active)
values (
    '888fa8e9-a498-4cac-bfa7-b734d33ea9b3',
    'ADMIN David',
    'Chavez',
    'Rodriguez',
    'admin@gmail.com',
    '$2a$10$wMlJFcLeY1Ng0UVWECa4beACZJFXxS3IWjXY6NSQFf0fzgryHXVCu',
    'ADMIN',
    '2025-09-29T03:37:27.820324852Z',
    '2025-09-29T03:37:27.820325314Z',
    true
);

insert into users (id, name, middle_name, last_name, email, password, role, created_at, updated_at, is_active)
values (
    'fd3d5d38-a559-44b8-b412-95851f610450',
    'ORGANIZER David',
    'Chavez',
    'Rodriguez',
    'organizer@gmail.com',
    '$2a$10$wMlJFcLeY1Ng0UVWECa4beACZJFXxS3IWjXY6NSQFf0fzgryHXVCu',
    'ORGANIZER',
    '2025-09-29T03:37:27.820324852Z',
    '2025-09-29T03:37:27.820325314Z',
    true
);

insert into users (id, name, middle_name, last_name, email, password, role, created_at, updated_at, is_active)
values (
    'e536ef97-c4b9-4d21-ac60-2643bd9b45c2',
    'WEDDING Planer is a guest David',
    'Chavez',
    'Rodriguez',
    'wedding-planer@gmail.com',
    '$2a$10$wMlJFcLeY1Ng0UVWECa4beACZJFXxS3IWjXY6NSQFf0fzgryHXVCu',
    'GUEST',
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

insert into invitations (id, event_id, unique_code, status, sent_at, responded_at, created_at, updated_at)
values (
    'c12c69d4-1fbc-4d67-8878-2ada8a89748f',
    '5f549545-ab73-47b4-9a30-cb9d796bfc39',
    'e0900ea6-0bac-41fe-86a2-12a80948c678',
    0,
    null,
    null,
    '2025-09-29T20:12:46.742065Z',
    '2025-09-29T20:12:46.742065Z'
);

insert into invitations (id, event_id, unique_code, status, sent_at, responded_at, created_at, updated_at)
values (
    '2c545e3d-fca8-48f1-aff2-13518911fb70',
    '5f549545-ab73-47b4-9a30-cb9d796bfc39',
    '221c3a47-b43c-4e61-81e7-75c26995a79e',
    0,
    null,
    null,
    '2025-09-29T20:12:46.742065Z',
    '2025-09-29T20:12:46.742065Z'
);

insert into guests (id, name, email, phone, company, created_at, updated_at, is_active)
values(
    'd6a53885-d780-4e92-b304-ba8db6a77cb0',
    'Guest 1',
    'guest1@gmail.com',
    '',
    '',
    '2025-09-30T01:42:53.322634297Z',
    '2025-09-30T01:42:53.322634297Z',
    true
);

insert into guests (id, name, email, phone, company, created_at, updated_at, is_active)
values(
    '422221dd-1bde-4480-97f9-5e4554da1993',
    'Guest 2',
    'guest2@gmail.com',
    '',
    '',
    '2025-09-30T01:42:53.322634297Z',
    '2025-09-30T01:42:53.322634297Z',
    true
);

