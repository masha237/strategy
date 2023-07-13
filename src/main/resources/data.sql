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

INSERT INTO recipe_status (id, type)
SELECT 1, 'NOT_VERIFIED'
WHERE NOT EXISTS (SELECT * FROM recipe_status WHERE id=1);
INSERT INTO recipe_status (id, type)
SELECT 2, 'VERIFIED'
WHERE NOT EXISTS (SELECT * FROM recipe_status WHERE id=2);
