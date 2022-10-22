INSERT INTO
    ROLES(id, name)
VALUES
    (gen_random_uuid (), 'USER'),
    (gen_random_uuid (), 'MODERATOR'),
    (gen_random_uuid (), 'ADMIN')
ON CONFLICT DO NOTHING;