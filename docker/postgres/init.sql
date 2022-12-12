CREATE DATABASE "green-note" owner=postgres;
\connect "green-note"
CREATE SCHEMA IF NOT EXISTS "logs" AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS "user" AUTHORIZATION postgres;
CREATE SCHEMA IF NOT EXISTS "reports" AUTHORIZATION postgres;
