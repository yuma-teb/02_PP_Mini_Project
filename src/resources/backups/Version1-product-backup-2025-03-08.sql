--
-- PostgreSQL database dump
--

-- Dumped from database version 17.0
-- Dumped by pg_dump version 17.0

-- Started on 2025-03-08 07:24:47

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

ALTER TABLE IF EXISTS ONLY public.products DROP CONSTRAINT IF EXISTS products_pkey;
ALTER TABLE IF EXISTS public.products ALTER COLUMN id DROP DEFAULT;
DROP SEQUENCE IF EXISTS public.products_id_seq;
DROP TABLE IF EXISTS public.products;
SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 219 (class 1259 OID 16541)
-- Name: products; Type: TABLE; Schema: public; Owner: kimleang
--

CREATE TABLE public.products (
    id integer NOT NULL,
    name character varying(50),
    unit_price numeric(10,2),
    qty integer,
    import_date date
);


ALTER TABLE public.products OWNER TO kimleang;

--
-- TOC entry 220 (class 1259 OID 16544)
-- Name: products_id_seq; Type: SEQUENCE; Schema: public; Owner: kimleang
--

CREATE SEQUENCE public.products_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.products_id_seq OWNER TO kimleang;

--
-- TOC entry 4853 (class 0 OID 0)
-- Dependencies: 220
-- Name: products_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: kimleang
--

ALTER SEQUENCE public.products_id_seq OWNED BY public.products.id;


--
-- TOC entry 4698 (class 2604 OID 16545)
-- Name: products id; Type: DEFAULT; Schema: public; Owner: kimleang
--

ALTER TABLE ONLY public.products ALTER COLUMN id SET DEFAULT nextval('public.products_id_seq'::regclass);


--
-- TOC entry 4846 (class 0 OID 16541)
-- Dependencies: 219
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: kimleang
--

COPY public.products (id, name, unit_price, qty, import_date) FROM stdin;
1	Cola	10.00	10	2025-03-05
2	Cola	10.00	10	2025-03-05
3	Laptop	10.00	10	2025-03-05
4	Tablet	10.00	10	2025-03-05
5	Keyboard	10.00	10	2025-03-05
6	Mouse	10.00	10	2025-03-05
7	Chair	10.00	10	2025-03-05
8	Desk	10.00	10	2025-03-05
9	Smartwatch	10.00	10	2025-03-05
\.


--
-- TOC entry 4854 (class 0 OID 0)
-- Dependencies: 220
-- Name: products_id_seq; Type: SEQUENCE SET; Schema: public; Owner: kimleang
--

SELECT pg_catalog.setval('public.products_id_seq', 11, true);


--
-- TOC entry 4700 (class 2606 OID 16547)
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: kimleang
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (id);


-- Completed on 2025-03-08 07:24:47

--
-- PostgreSQL database dump complete
--

