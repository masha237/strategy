INSERT INTO community_role (id, type)
SELECT 1, 'ADMIN'
WHERE NOT EXISTS (SELECT * FROM community_role WHERE id=1);
INSERT INTO community_role (id, type)
SELECT 2, 'MODERATOR'
WHERE NOT EXISTS (SELECT * FROM community_role WHERE id=2);
INSERT INTO community_role (id, type)
SELECT 3, 'VERIFIED_USER'
WHERE NOT EXISTS (SELECT * FROM community_role WHERE id=3);
INSERT INTO community_role (id, type)
SELECT 4, 'USER'
WHERE NOT EXISTS (SELECT * FROM community_role WHERE id=4);