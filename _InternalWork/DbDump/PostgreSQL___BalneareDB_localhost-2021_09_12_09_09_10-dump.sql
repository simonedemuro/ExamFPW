--
-- PostgreSQL database dump
--

-- Dumped from database version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.8 (Ubuntu 12.8-0ubuntu0.20.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: BalneareDB; Type: DATABASE; Schema: -; Owner: fpw
--

CREATE DATABASE "BalneareDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';


ALTER DATABASE "BalneareDB" OWNER TO fpw;

\connect "BalneareDB"

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: postgres
--

CREATE SCHEMA public;


ALTER SCHEMA public OWNER TO postgres;

--
-- Name: SCHEMA public; Type: COMMENT; Schema: -; Owner: postgres
--

COMMENT ON SCHEMA public IS 'standard public schema';


SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: accomodation; Type: TABLE; Schema: public; Owner: fpw
--

CREATE TABLE public.accomodation (
    "Id" integer NOT NULL,
    acc_date date NOT NULL,
    "n_Booked" integer NOT NULL
);


ALTER TABLE public.accomodation OWNER TO fpw;

--
-- Name: accomodation_Id_seq; Type: SEQUENCE; Schema: public; Owner: fpw
--

CREATE SEQUENCE public."accomodation_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."accomodation_Id_seq" OWNER TO fpw;

--
-- Name: accomodation_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fpw
--

ALTER SEQUENCE public."accomodation_Id_seq" OWNED BY public.accomodation."Id";


--
-- Name: available_slot; Type: TABLE; Schema: public; Owner: fpw
--

CREATE TABLE public.available_slot (
    "Id" integer NOT NULL,
    data date NOT NULL,
    day_part character(2) NOT NULL,
    n_available integer,
    CONSTRAINT available_slot_day_part_check CHECK (((day_part = 'AM'::bpchar) OR (day_part = 'PM'::bpchar)))
);


ALTER TABLE public.available_slot OWNER TO fpw;

--
-- Name: available_slot_Id_seq; Type: SEQUENCE; Schema: public; Owner: fpw
--

CREATE SEQUENCE public."available_slot_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."available_slot_Id_seq" OWNER TO fpw;

--
-- Name: available_slot_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fpw
--

ALTER SEQUENCE public."available_slot_Id_seq" OWNED BY public.available_slot."Id";


--
-- Name: invoice; Type: TABLE; Schema: public; Owner: fpw
--

CREATE TABLE public.invoice (
    "Id" integer NOT NULL,
    "Id_user" bigint NOT NULL,
    "Id_accomodation" bigint NOT NULL,
    price numeric(2,0) NOT NULL,
    description character varying(1000)
);


ALTER TABLE public.invoice OWNER TO fpw;

--
-- Name: invoice_Id_seq; Type: SEQUENCE; Schema: public; Owner: fpw
--

CREATE SEQUENCE public."invoice_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."invoice_Id_seq" OWNER TO fpw;

--
-- Name: invoice_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fpw
--

ALTER SEQUENCE public."invoice_Id_seq" OWNED BY public.invoice."Id";


--
-- Name: reservation; Type: TABLE; Schema: public; Owner: fpw
--

CREATE TABLE public.reservation (
    "Id" integer NOT NULL,
    "Id_user" bigint NOT NULL,
    "Id_accomodation" bigint NOT NULL
);


ALTER TABLE public.reservation OWNER TO fpw;

--
-- Name: reservation_Id_seq; Type: SEQUENCE; Schema: public; Owner: fpw
--

CREATE SEQUENCE public."reservation_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."reservation_Id_seq" OWNER TO fpw;

--
-- Name: reservation_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fpw
--

ALTER SEQUENCE public."reservation_Id_seq" OWNED BY public.reservation."Id";


--
-- Name: user; Type: TABLE; Schema: public; Owner: fpw
--

CREATE TABLE public."user" (
    "Id" integer NOT NULL,
    "isAdmin" boolean DEFAULT false NOT NULL,
    username character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    surname character varying(255) NOT NULL,
    birthday date NOT NULL,
    fiscalnumber character(16) NOT NULL,
    sex character(1) NOT NULL,
    email character varying(1000) NOT NULL,
    phone character varying(255) NOT NULL,
    invoiceoptin boolean NOT NULL
);


ALTER TABLE public."user" OWNER TO fpw;

--
-- Name: user_Id_seq; Type: SEQUENCE; Schema: public; Owner: fpw
--

CREATE SEQUENCE public."user_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public."user_Id_seq" OWNER TO fpw;

--
-- Name: user_Id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: fpw
--

ALTER SEQUENCE public."user_Id_seq" OWNED BY public."user"."Id";


--
-- Name: accomodation Id; Type: DEFAULT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.accomodation ALTER COLUMN "Id" SET DEFAULT nextval('public."accomodation_Id_seq"'::regclass);


--
-- Name: available_slot Id; Type: DEFAULT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.available_slot ALTER COLUMN "Id" SET DEFAULT nextval('public."available_slot_Id_seq"'::regclass);


--
-- Name: invoice Id; Type: DEFAULT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.invoice ALTER COLUMN "Id" SET DEFAULT nextval('public."invoice_Id_seq"'::regclass);


--
-- Name: reservation Id; Type: DEFAULT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.reservation ALTER COLUMN "Id" SET DEFAULT nextval('public."reservation_Id_seq"'::regclass);


--
-- Name: user Id; Type: DEFAULT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public."user" ALTER COLUMN "Id" SET DEFAULT nextval('public."user_Id_seq"'::regclass);


--
-- Data for Name: accomodation; Type: TABLE DATA; Schema: public; Owner: fpw
--

COPY public.accomodation ("Id", acc_date, "n_Booked") FROM stdin;
\.


--
-- Data for Name: available_slot; Type: TABLE DATA; Schema: public; Owner: fpw
--

COPY public.available_slot ("Id", data, day_part, n_available) FROM stdin;
15	2021-08-10	PM	5
\.


--
-- Data for Name: invoice; Type: TABLE DATA; Schema: public; Owner: fpw
--

COPY public.invoice ("Id", "Id_user", "Id_accomodation", price, description) FROM stdin;
\.


--
-- Data for Name: reservation; Type: TABLE DATA; Schema: public; Owner: fpw
--

COPY public.reservation ("Id", "Id_user", "Id_accomodation") FROM stdin;
\.


--
-- Data for Name: user; Type: TABLE DATA; Schema: public; Owner: fpw
--

COPY public."user" ("Id", "isAdmin", username, password, name, surname, birthday, fiscalnumber, sex, email, phone, invoiceoptin) FROM stdin;
2	t	demuro	65879	Simone	Demuro	1997-03-25	ASDFGHJKLQWERTYU	M	s.demuro2@studenti.unica.it	111222333444	f
11	f	TestUser-1780272314	password	Ciccio	Pasticcio	1997-03-25	ASDFGHJKLZXCVBNM	M	cicciPasticci@alice.it	3334445555	f
\.


--
-- Name: accomodation_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: fpw
--

SELECT pg_catalog.setval('public."accomodation_Id_seq"', 1, false);


--
-- Name: available_slot_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: fpw
--

SELECT pg_catalog.setval('public."available_slot_Id_seq"', 16, true);


--
-- Name: invoice_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: fpw
--

SELECT pg_catalog.setval('public."invoice_Id_seq"', 1, false);


--
-- Name: reservation_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: fpw
--

SELECT pg_catalog.setval('public."reservation_Id_seq"', 1, false);


--
-- Name: user_Id_seq; Type: SEQUENCE SET; Schema: public; Owner: fpw
--

SELECT pg_catalog.setval('public."user_Id_seq"', 11, true);


--
-- Name: accomodation accomodation_pk; Type: CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.accomodation
    ADD CONSTRAINT accomodation_pk PRIMARY KEY ("Id");


--
-- Name: available_slot available_slot_pk; Type: CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.available_slot
    ADD CONSTRAINT available_slot_pk PRIMARY KEY ("Id");


--
-- Name: invoice invoice_pk; Type: CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY ("Id");


--
-- Name: reservation reservation_pk; Type: CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pk PRIMARY KEY ("Id");


--
-- Name: user user_pk; Type: CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY ("Id");


--
-- Name: available_slot_data_day_part_uindex; Type: INDEX; Schema: public; Owner: fpw
--

CREATE UNIQUE INDEX available_slot_data_day_part_uindex ON public.available_slot USING btree (data, day_part);


--
-- Name: user_username_uindex; Type: INDEX; Schema: public; Owner: fpw
--

CREATE UNIQUE INDEX user_username_uindex ON public."user" USING btree (username);


--
-- Name: invoice invoice_accomodation_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_accomodation_id_fk FOREIGN KEY ("Id_accomodation") REFERENCES public.accomodation("Id");


--
-- Name: invoice invoice_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_user_id_fk FOREIGN KEY ("Id_user") REFERENCES public."user"("Id");


--
-- Name: reservation reservation_accomodation_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_accomodation_id_fk FOREIGN KEY ("Id_accomodation") REFERENCES public.accomodation("Id");


--
-- Name: reservation reservation_user_id_fk; Type: FK CONSTRAINT; Schema: public; Owner: fpw
--

ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_user_id_fk FOREIGN KEY ("Id_user") REFERENCES public."user"("Id");


--
-- PostgreSQL database dump complete
--

