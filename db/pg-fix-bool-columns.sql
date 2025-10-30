-- Convert MySQL-style tinyint(1)/int flags (0/1) to PostgreSQL boolean
-- Run this after importing your existing data into Postgres

-- sys_app: is_enable int -> boolean
ALTER TABLE sys_app
    ALTER COLUMN is_enable TYPE boolean USING (is_enable <> 0);

-- sys_office: is_enable int -> boolean
ALTER TABLE sys_office
    ALTER COLUMN is_enable TYPE boolean USING (is_enable <> 0);

-- sys_permission: is_menu, is_enable int -> boolean
ALTER TABLE sys_permission
    ALTER COLUMN is_menu TYPE boolean USING (is_menu <> 0),
    ALTER COLUMN is_enable TYPE boolean USING (is_enable <> 0);

-- sys_role: is_enable int -> boolean
ALTER TABLE sys_role
    ALTER COLUMN is_enable TYPE boolean USING (is_enable <> 0);

-- sys_user: is_enable int -> boolean
ALTER TABLE sys_user
    ALTER COLUMN is_enable TYPE boolean USING (is_enable <> 0);

-- Optional: ensure sequences are at least MAX(id)
DO $$
BEGIN
  PERFORM setval(pg_get_serial_sequence('sys_app','id'),       GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_app),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_office','id'),    GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_office),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_permission','id'),GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_permission),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_role','id'),      GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_role),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_user','id'),      GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_user),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_role_permission','id'), GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_role_permission),1), true);
  PERFORM setval(pg_get_serial_sequence('sys_user_role','id'), GREATEST((SELECT COALESCE(MAX(id),1) FROM sys_user_role),1), true);
EXCEPTION WHEN undefined_function THEN
  -- pg_get_serial_sequence may be null if ids were not created as serial/bigserial
  NULL;
END $$;

