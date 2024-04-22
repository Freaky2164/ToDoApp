INSERT INTO t_todo_list (id, userId, name)
VALUES
    ('164df0d2-9d0e-451c-aa6a-a6065a8caf94', 'e94a9d63-69b1-4c49-99e8-2c76c9a1588c', 'Einkaufsliste'),
    ('c6576e28-b539-4f11-8eac-6b50e01e4302', 'e94a9d63-69b1-4c49-99e8-2c76c9a1588c', 'Arbeitsprojekte');

INSERT INTO t_todo (id, todoListId, name, description, due_date, reminder_date, done, creation_date, completion_date)
VALUES
    ('f0b45e6b-98cb-4b2a-a2a1-24d1cf16c81d', '164df0d2-9d0e-451c-aa6a-a6065a8caf94', 'Milch kaufen', 'Nicht vergessen, laktosefreie Milch zu kaufen.', '2024-04-05', NULL, false, '2024-03-28', NULL),
    ('57cb80ad-fa0c-47b5-9417-31f96d84cb0f', '164df0d2-9d0e-451c-aa6a-a6065a8caf94', 'Geburtstagsgeschenk besorgen', 'Überlegen, was John zum Geburtstag schenken könnte.', '2024-04-10', NULL, false, '2024-03-28', NULL),
    ('e057429d-97da-46a3-9a1f-f9d2a209890b', 'c6576e28-b539-4f11-8eac-6b50e01e4302', 'Projektbericht schreiben', 'Fortschrittsbericht für das Projekt XYZ verfassen.', '2024-04-15', '2024-04-10', false, '2024-03-28', NULL);

INSERT INTO t_user (id, mail, password)
VALUES
    ('e94a9d63-69b1-4c49-99e8-2c76c9a1588c', 'max.mustermann@example.com', 'hashed_password_goes_here');

INSERT INTO t_notification (id, userId, name, webHookUrl)
VALUES
    ('67145dc1-0a7c-45fb-b8fc-787dcbcc0bb9', 'e94a9d63-69b1-4c49-99e8-2c76c9a1588c', 'Einkaufsliste Webhook', 'https://example.com/einkaufsliste/webhook'),
    ('90f8a9c3-78d9-4d02-99dc-36a15d8aaedd', 'e94a9d63-69b1-4c49-99e8-2c76c9a1588c', 'Projektbericht Webhook', 'https://example.com/projektbericht/webhook');
