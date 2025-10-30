-- PostgreSQL DDL for smart-sso (schema only)
-- Converted from MySQL definitions in db/smart-sso2.sql

-- Drop existing tables if present (no FKs defined here to keep it simple)
DROP TABLE IF EXISTS sys_user_role;
DROP TABLE IF EXISTS sys_role_permission;
DROP TABLE IF EXISTS sys_user;
DROP TABLE IF EXISTS sys_role;
DROP TABLE IF EXISTS sys_permission;
DROP TABLE IF EXISTS sys_office;
DROP TABLE IF EXISTS sys_app;

-- Applications
CREATE TABLE IF NOT EXISTS sys_app (
    id              BIGSERIAL PRIMARY KEY,
    code            VARCHAR(50) NOT NULL,
    name            VARCHAR(128) NOT NULL,
    sort            INTEGER NOT NULL,
    is_enable       BOOLEAN NOT NULL,
    client_id       VARCHAR(20) NOT NULL,
    client_secret   VARCHAR(128) NOT NULL,
    create_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    CONSTRAINT unique_code UNIQUE (code),
    CONSTRAINT unique_client_id UNIQUE (client_id)
);

-- Offices
CREATE TABLE IF NOT EXISTS sys_office (
    id              BIGSERIAL PRIMARY KEY,
    parent_id       BIGINT,
    name            VARCHAR(100) NOT NULL,
    sort            INTEGER NOT NULL,
    is_enable       BOOLEAN NOT NULL,
    create_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Permissions
CREATE TABLE IF NOT EXISTS sys_permission (
    id              BIGSERIAL PRIMARY KEY,
    app_id          BIGINT NOT NULL,
    parent_id       BIGINT,
    name            VARCHAR(50) NOT NULL,
    url             VARCHAR(255) NOT NULL,
    sort            INTEGER NOT NULL,
    icon            VARCHAR(100),
    is_menu         BOOLEAN NOT NULL,
    is_enable       BOOLEAN NOT NULL,
    create_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Roles
CREATE TABLE IF NOT EXISTS sys_role (
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(50) NOT NULL,
    sort            INTEGER NOT NULL,
    description     VARCHAR(255) NOT NULL,
    is_enable       BOOLEAN NOT NULL,
    create_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- Role-Permission mapping
CREATE TABLE IF NOT EXISTS sys_role_permission (
    id              BIGSERIAL PRIMARY KEY,
    role_id         BIGINT NOT NULL,
    permission_id   BIGINT NOT NULL,
    app_id          BIGINT NOT NULL
);

-- Users
CREATE TABLE IF NOT EXISTS sys_user (
    id              BIGSERIAL PRIMARY KEY,
    office_id       BIGINT NOT NULL,
    name            VARCHAR(50),
    account         VARCHAR(50) NOT NULL,
    password        VARCHAR(100) NOT NULL,
    last_login_time TIMESTAMP WITHOUT TIME ZONE,
    login_count     INTEGER NOT NULL,
    is_enable       BOOLEAN NOT NULL,
    create_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    update_time     TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- User-Role mapping
CREATE TABLE IF NOT EXISTS sys_user_role (
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT NOT NULL,
    role_id         BIGINT NOT NULL
);

-- Optional: add indexes commonly used by queries
CREATE INDEX IF NOT EXISTS idx_sys_user_account ON sys_user(account);
CREATE INDEX IF NOT EXISTS idx_sys_permission_app ON sys_permission(app_id);
CREATE INDEX IF NOT EXISTS idx_sys_permission_parent ON sys_permission(parent_id);
CREATE INDEX IF NOT EXISTS idx_sys_role_permission_role ON sys_role_permission(role_id);
CREATE INDEX IF NOT EXISTS idx_sys_role_permission_perm ON sys_role_permission(permission_id);
CREATE INDEX IF NOT EXISTS idx_sys_user_role_user ON sys_user_role(user_id);
CREATE INDEX IF NOT EXISTS idx_sys_user_role_role ON sys_user_role(role_id);

-- Notes:
-- - MySQL INT(1) flags (is_enable, is_menu) are BOOLEAN here. Use TRUE/FALSE when seeding.
-- - DATETIME maps to TIMESTAMP WITHOUT TIME ZONE.
-- - AUTO_INCREMENT maps to BIGSERIAL. After manual inserts with explicit ids,
--   run: SELECT setval(pg_get_serial_sequence('<table>','id'), GREATEST(MAX(id),1)) FROM <table>;
-- - No foreign keys are added to keep portability with existing code and data.

