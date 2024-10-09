-- Table: luxmed.companies

-- DROP TABLE IF EXISTS luxmed.companies;

CREATE TABLE IF NOT EXISTS luxmed.companies
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT companies_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS luxmed.companies
    OWNER to admin;

-- Table: luxmed.departments

-- DROP TABLE IF EXISTS luxmed.departments;

CREATE TABLE IF NOT EXISTS luxmed.departments
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    company_id integer,
    CONSTRAINT departments_pkey PRIMARY KEY (id),
    CONSTRAINT fk_company_department FOREIGN KEY (company_id)
        REFERENCES luxmed.companies (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS luxmed.departments
    OWNER to admin;

-- Table: luxmed.teams

-- DROP TABLE IF EXISTS luxmed.teams;

CREATE TABLE IF NOT EXISTS luxmed.teams
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    department_id integer,
    CONSTRAINT teams_pkey PRIMARY KEY (id),
    CONSTRAINT fk_team_department FOREIGN KEY (department_id)
        REFERENCES luxmed.departments (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS luxmed.teams
    OWNER to admin;


-- Table: luxmed.projects

-- DROP TABLE IF EXISTS luxmed.projects;

CREATE TABLE IF NOT EXISTS luxmed.projects
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    team_id integer,
    CONSTRAINT projects_pkey PRIMARY KEY (id),
    CONSTRAINT fk_project_team FOREIGN KEY (team_id)
        REFERENCES luxmed.teams (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS luxmed.projects
    OWNER to admin;


-- Table: luxmed.managers

-- DROP TABLE IF EXISTS luxmed.managers;

CREATE TABLE IF NOT EXISTS luxmed.managers
(
    id integer NOT NULL GENERATED ALWAYS AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 2147483647 CACHE 1 ),
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    surname character varying(255) COLLATE pg_catalog."default" NOT NULL,
    project_id integer,
    CONSTRAINT managers_pkey PRIMARY KEY (id),
    CONSTRAINT fk_manager_project FOREIGN KEY (project_id)
        REFERENCES luxmed.projects (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS luxmed.managers
    OWNER to admin;