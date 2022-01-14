-- noinspection SpellCheckingInspectionForFile

--
-- PostgreSQL database dump
--

-- Dumped from database version 14.0
-- Dumped by pg_dump version 14.0

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

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: article_stocks; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.article_stocks (
    uid uuid NOT NULL,
    article_uid uuid NOT NULL,
    stock bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.article_stocks OWNER TO warehouse_user;

--
-- Name: articles; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.articles (
    uid uuid NOT NULL,
    item_name character varying NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.articles OWNER TO warehouse_user;

--
-- Name: categories; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.categories (
    uid uuid NOT NULL,
    item_name character varying NOT NULL,
    description text,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.categories OWNER TO warehouse_user;

--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO warehouse_user;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO warehouse_user;

--
-- Name: product_articles; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.product_articles (
    uid uuid NOT NULL,
    product_uid uuid NOT NULL,
    article_uid uuid NOT NULL,
    amount_of bigint NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL
);


ALTER TABLE public.product_articles OWNER TO warehouse_user;

--
-- Name: products; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.products (
    uid uuid NOT NULL,
    item_name character varying NOT NULL,
    image_urls text,
    price double precision NOT NULL,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone NOT NULL,
    category_uid uuid
);


ALTER TABLE public.products OWNER TO warehouse_user;

--
-- Name: roles; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.roles (
    uid uuid NOT NULL,
    item_name character varying NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.roles OWNER TO warehouse_user;

--
-- Name: transaction_products; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.transaction_products (
    uid uuid NOT NULL,
    transaction_uid uuid NOT NULL,
    product_uid uuid NOT NULL,
    amount_of bigint NOT NULL,
    created_at timestamp without time zone NOT NULL
);


ALTER TABLE public.transaction_products OWNER TO warehouse_user;

--
-- Name: transactions; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.transactions (
    uid uuid NOT NULL,
    created_at timestamp without time zone NOT NULL,
    user_uid uuid NOT NULL
);


ALTER TABLE public.transactions OWNER TO warehouse_user;

--
-- Name: users; Type: TABLE; Schema: public; Owner: warehouse_user
--

CREATE TABLE public.users (
    uid uuid NOT NULL,
    item_name character varying NOT NULL,
    email character varying NOT NULL,
    password character varying NOT NULL,
    role_uid uuid NOT NULL,
    created_at timestamp without time zone DEFAULT now() NOT NULL,
    updated_at timestamp without time zone DEFAULT now() NOT NULL
);


ALTER TABLE public.users OWNER TO warehouse_user;

--
-- Data for Name: article_stocks; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('f5b8be6d-43f8-4424-9bf9-d9532533f0d1', '89a9b9a6-5c88-4b56-b793-398e33e47f1f', 234, '2022-01-02 10:18:47.156', '2022-01-02 10:18:47.156');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('4e245867-177d-41bd-b16a-42778fcfa52c', '2b6b42a5-432e-488f-9c89-e755ec9ce1a0', 345, '2022-01-02 10:18:47.166', '2022-01-02 10:18:47.166');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('4a573ec9-7a34-4d8b-b11f-872cda601710', '8b1aeb0c-733c-4c01-9f7f-36a283b72032', 456, '2022-01-02 10:18:47.177', '2022-01-02 10:18:47.177');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('2855aecf-9079-40d0-bf01-7ebfae305442', '05c60229-8943-43a4-bf43-6faab3e17a04', 567, '2022-01-02 10:18:47.189', '2022-01-02 10:18:47.189');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('ccaf853d-2dee-4936-b97b-03019fb78692', 'f03650c9-df10-4b97-b037-771dda487c18', 789, '2022-01-02 10:18:47.211', '2022-01-02 10:18:47.211');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('7dc0863b-fb42-4edb-8007-fc286c900261', '8a060e33-540f-430d-8ee2-bc013240b56d', 890, '2022-01-02 10:18:47.221', '2022-01-02 10:18:47.221');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('33a89492-b520-4123-8ad2-cf97ec6361f6', '695a5be1-8bde-4ae0-bf07-437083d947ca', 901, '2022-01-02 10:18:47.23', '2022-01-02 10:18:47.23');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('315f5045-0de1-43bd-b402-93084d0ab88c', '2614cd07-304c-492d-8c7b-26a666641940', 123, '2022-01-02 10:18:47.24', '2022-01-02 10:18:47.241');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('38c4b6cc-9d1c-4fe3-a705-db74e6f60479', '59022cd2-ef6c-4e18-99a2-923866a74cd9', 234, '2022-01-02 10:18:47.253', '2022-01-02 10:18:47.253');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('e9fe5e5c-c892-497f-a9cd-e71fb17c8f3c', '785ce2b1-cb83-48b8-99c3-c7bcb40cb774', 345, '2022-01-02 10:18:47.261', '2022-01-02 10:18:47.261');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('02857b26-2a40-4af3-be7a-8a25b22ca89a', '97d303a6-6253-46d2-ace2-689706d1857a', 456, '2022-01-02 10:18:47.27', '2022-01-02 10:18:47.27');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('b88e0dd2-817c-45ae-a85e-9b68eeb23c36', '2cca05f1-5f31-4102-b2aa-f27cd25254eb', 567, '2022-01-02 10:18:47.279', '2022-01-02 10:18:47.279');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('8b641bfb-aa06-4490-8266-1479bc6a9601', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 0, '2022-01-02 10:18:47.142', '2022-01-02 10:29:32.144');
INSERT INTO public.article_stocks (uid, article_uid, stock, created_at, updated_at) VALUES ('d4e8a568-fe1e-4532-8fe0-cba83ec2085a', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 673, '2022-01-02 10:18:47.2', '2022-01-02 10:29:32.156');


--
-- Data for Name: articles; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 'leg', '2022-01-02 10:18:47.128', '2022-01-02 10:18:47.128');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('89a9b9a6-5c88-4b56-b793-398e33e47f1f', 'screw', '2022-01-02 10:18:47.151', '2022-01-02 10:18:47.151');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('2b6b42a5-432e-488f-9c89-e755ec9ce1a0', 'seat', '2022-01-02 10:18:47.161', '2022-01-02 10:18:47.161');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('8b1aeb0c-733c-4c01-9f7f-36a283b72032', 'table top', '2022-01-02 10:18:47.172', '2022-01-02 10:18:47.172');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('05c60229-8943-43a4-bf43-6faab3e17a04', 'screw 5mm', '2022-01-02 10:18:47.183', '2022-01-02 10:18:47.183');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('4a00161f-21e8-4f4d-adff-c957f6114b1e', 'screw 6mm', '2022-01-02 10:18:47.194', '2022-01-02 10:18:47.194');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('f03650c9-df10-4b97-b037-771dda487c18', 'nut 5mm', '2022-01-02 10:18:47.206', '2022-01-02 10:18:47.206');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('8a060e33-540f-430d-8ee2-bc013240b56d', 'nut 6mm', '2022-01-02 10:18:47.216', '2022-01-02 10:18:47.216');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('695a5be1-8bde-4ae0-bf07-437083d947ca', 'allan key 5mm', '2022-01-02 10:18:47.225', '2022-01-02 10:18:47.225');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('2614cd07-304c-492d-8c7b-26a666641940', 'allan key 6mm', '2022-01-02 10:18:47.236', '2022-01-02 10:18:47.236');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('59022cd2-ef6c-4e18-99a2-923866a74cd9', 'nail 3mm', '2022-01-02 10:18:47.246', '2022-01-02 10:18:47.246');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('785ce2b1-cb83-48b8-99c3-c7bcb40cb774', 'nail 4mm', '2022-01-02 10:18:47.257', '2022-01-02 10:18:47.257');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('97d303a6-6253-46d2-ace2-689706d1857a', 'glue 50ml', '2022-01-02 10:18:47.266', '2022-01-02 10:18:47.266');
INSERT INTO public.articles (uid, item_name, created_at, updated_at) VALUES ('2cca05f1-5f31-4102-b2aa-f27cd25254eb', 'glue 300ml', '2022-01-02 10:18:47.274', '2022-01-02 10:18:47.274');


--
-- Data for Name: categories; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.categories (uid, item_name, description, created_at, updated_at) VALUES ('6f0c233d-a33e-43f7-b6e8-50b4f7acf059', 'Chairs', 'Chairs. Ik ben makelaar in koffi, en woon op de Lauriergracht No 37. Het is mijn gewoonte niet, romans te schrijven, of zulke dingen, en het heeft dan ook lang geduurd, voor ik er toe overging een paar riem papier extra te bestellen, en het werk aan te vangen, dat gij, lieve lezer, zoâven in de hand hebt genomen, en dat ge lezen moet als ge makelaar in koffie zijt, of als ge wat anders zijt. Niet alleen dat ik nooit iets schreef wat naar een roman geleek, maar ik houd er zelfs niet van, iets dergelijks te lezen, omdat ik een man van zaken ben.', '2022-01-02 10:16:33.715', '2022-01-02 10:16:33.715');
INSERT INTO public.categories (uid, item_name, description, created_at, updated_at) VALUES ('6fe82c4a-9f5d-49f4-9292-0440547bbfc4', 'Tables', 'Tables. Ik ben makelaar in koffi, en woon op de Lauriergracht No 37. Het is mijn gewoonte niet, romans te schrijven, of zulke dingen, en het heeft dan ook lang geduurd, voor ik er toe overging een paar riem papier extra te bestellen, en het werk aan te vangen, dat gij, lieve lezer, zoâven in de hand hebt genomen, en dat ge lezen moet als ge makelaar in koffie zijt, of als ge wat anders zijt. Niet alleen dat ik nooit iets schreef wat naar een roman geleek, maar ik houd er zelfs niet van, iets dergelijks te lezen, omdat ik een man van zaken ben.', '2022-01-02 10:16:33.734', '2022-01-02 10:16:33.734');
INSERT INTO public.categories (uid, item_name, description, created_at, updated_at) VALUES ('1e8d8cdc-0e5b-4105-ad75-df3087d8a912', 'Child toys', 'Child toys. Ik ben makelaar in koffi, en woon op de Lauriergracht No 37. Het is mijn gewoonte niet, romans te schrijven, of zulke dingen, en het heeft dan ook lang geduurd, voor ik er toe overging een paar riem papier extra te bestellen, en het werk aan te vangen, dat gij, lieve lezer, zoâven in de hand hebt genomen, en dat ge lezen moet als ge makelaar in koffie zijt, of als ge wat anders zijt. Niet alleen dat ik nooit iets schreef wat naar een roman geleek, maar ik houd er zelfs niet van, iets dergelijks te lezen, omdat ik een man van zaken ben.', '2022-01-02 10:16:33.739', '2022-01-02 10:16:33.739');


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-1', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.380259', 1, 'EXECUTED', '8:c4cb63127612e4d2e61c5765f00c29d5', 'createTable tableName=article_stocks', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-2', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.419602', 2, 'EXECUTED', '8:bbaa86e9a1e4deec7b3ed610114f4485', 'createTable tableName=articles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-3', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.452147', 3, 'EXECUTED', '8:12d65b4be11299b4f619b80fb310789b', 'createTable tableName=product_articles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-4', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.48121', 4, 'EXECUTED', '8:2d0198c807bfdf8f3e6f8d1ee8463d49', 'createTable tableName=products', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-5', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.515474', 5, 'EXECUTED', '8:8a6fc09d38a3c955791a713bd47ed3c4', 'createTable tableName=transaction_products', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-6', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.547857', 6, 'EXECUTED', '8:3c5019d9e90c2f67b6755989f8cf01f5', 'createTable tableName=transactions', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-7', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.577221', 7, 'EXECUTED', '8:98ed85b0733f682c0d3e580b9cee381c', 'addForeignKeyConstraint baseTableName=article_stocks, constraintName=fk_article_stocks_article, referencedTableName=articles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-8', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.601019', 8, 'EXECUTED', '8:572a6e793d49ea96b3adf20aac216cd4', 'addUniqueConstraint constraintName=article_stocks_article_uid_key, tableName=article_stocks', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-9', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.63364', 9, 'EXECUTED', '8:cd80e2b5f4cd3439e5a3208c04a6898c', 'addForeignKeyConstraint baseTableName=product_articles, constraintName=fk_product_articles_article, referencedTableName=articles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-10', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.65288', 10, 'EXECUTED', '8:9a52121fa0fd99566ae9d13eebb3f810', 'addForeignKeyConstraint baseTableName=product_articles, constraintName=fk_product_articles_product, referencedTableName=products', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-11', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.674518', 11, 'EXECUTED', '8:6011a6efbda61bba5ca5a714b72a391c', 'addForeignKeyConstraint baseTableName=transaction_products, constraintName=fk_transaction_products_product, referencedTableName=products', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639735226942-12', 'averageflow', 'db/changelog/1639736694.xml', '2022-01-01 21:33:54.693271', 12, 'EXECUTED', '8:20cfe1ec3b1c363f918b7f85d9cdaf8b', 'addForeignKeyConstraint baseTableName=transaction_products, constraintName=fk_transaction_products_transaction, referencedTableName=transactions', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639743127-1', 'averageflow', 'db/changelog/1639743127.xml', '2022-01-01 21:33:54.72654', 13, 'EXECUTED', '8:3be4c75a634a9c8e43f6e371eb5ee6a4', 'createTable tableName=categories', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639763669-1', 'averageflow', 'db/changelog/1639763669.xml', '2022-01-01 21:33:54.745216', 14, 'EXECUTED', '8:2a218b54c0e7fca72f43e999cb7e39ff', 'addColumn tableName=products', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1639763669-12', 'averageflow', 'db/changelog/1639763669.xml', '2022-01-01 21:33:54.775137', 15, 'EXECUTED', '8:77724db349f09dd07e2c7cb2869a7287', 'addForeignKeyConstraint baseTableName=products, constraintName=fk_category_products, referencedTableName=categories', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1640688769-1', 'averageflow', 'db/changelog/1640688769.xml', '2022-01-01 21:33:54.863537', 16, 'EXECUTED', '8:3adf313e0f7801a443497a8b8ca725c2', 'createTable tableName=users; createTable tableName=roles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1640688769-2', 'averageflow', 'db/changelog/1640688769.xml', '2022-01-01 21:33:54.887913', 17, 'EXECUTED', '8:91d348c4f743b684def30e45286693d6', 'addForeignKeyConstraint baseTableName=users, constraintName=fk_role_users, referencedTableName=roles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1640693469-1', 'averageflow', 'db/changelog/1640693469.xml', '2022-01-01 21:33:54.975482', 18, 'EXECUTED', '8:f2f9987f1e18de31d16a256dfc53491b', 'loadData tableName=roles', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1640905471-1', 'averageflow', 'db/changelog/1640905471.xml', '2022-01-01 21:33:54.986828', 19, 'EXECUTED', '8:c9976819fc50ad712ed0090654923633', 'addColumn tableName=transactions', '', NULL, '4.6.2', NULL, NULL, '1072833595');
INSERT INTO public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) VALUES ('1640905471-2', 'averageflow', 'db/changelog/1640905471.xml', '2022-01-01 21:33:54.999414', 20, 'EXECUTED', '8:920b6a2ec487532476a70be31167c6bb', 'addForeignKeyConstraint baseTableName=transactions, constraintName=fk_user_transactions, referencedTableName=users', '', NULL, '4.6.2', NULL, NULL, '1072833595');


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.databasechangeloglock (id, locked, lockgranted, lockedby) VALUES (1, false, NULL, NULL);


--
-- Data for Name: product_articles; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('44303059-cef8-4b57-b90f-f5db2764da45', 'f193e247-9a8f-4d24-8466-bd9a3c14c623', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 4, '2022-01-02 10:24:00.396', '2022-01-02 10:24:00.396');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('d3cc0a63-d52f-4df5-a76f-4512f5b80ac2', 'f193e247-9a8f-4d24-8466-bd9a3c14c623', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 123, '2022-01-02 10:24:00.401', '2022-01-02 10:24:00.401');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('b6dc3982-1cf4-4240-b1ad-40bee2817cac', 'bad0f78d-baeb-4b7c-8cbe-9ba480212375', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 5, '2022-01-02 10:24:00.42', '2022-01-02 10:24:00.42');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('123a2cff-1bdc-45ad-b288-5ad642ce6e84', 'bad0f78d-baeb-4b7c-8cbe-9ba480212375', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 123, '2022-01-02 10:24:00.422', '2022-01-02 10:24:00.422');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('c0fb69f2-0bb7-4c5e-a460-fdccafeae6e3', 'b92bd05e-bd5d-4ee2-8ea4-65c665bac850', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 6, '2022-01-02 10:24:00.439', '2022-01-02 10:24:00.439');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('16d3d6e3-4e62-4a48-9a39-2bcf09cc851f', 'b92bd05e-bd5d-4ee2-8ea4-65c665bac850', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 123, '2022-01-02 10:24:00.441', '2022-01-02 10:24:00.441');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('c03808e2-4c15-4fda-a3dc-f4282b0495a8', '6fcab02a-6d3f-4d15-9655-1bfc21372633', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 7, '2022-01-02 10:24:00.46', '2022-01-02 10:24:00.46');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('b36301e3-5d06-4376-a84f-905b62ccc745', '6fcab02a-6d3f-4d15-9655-1bfc21372633', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 123, '2022-01-02 10:24:00.461', '2022-01-02 10:24:00.461');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('797e137f-81b2-44ba-9456-67ac8803403b', '5f37d8e1-f27f-43ec-ae63-244c6ebeee5f', '4a00161f-21e8-4f4d-adff-c957f6114b1e', 8, '2022-01-02 10:24:00.481', '2022-01-02 10:24:00.481');
INSERT INTO public.product_articles (uid, product_uid, article_uid, amount_of, created_at, updated_at) VALUES ('e6616c29-6fb6-4db5-992c-ef9d40e72f8c', '5f37d8e1-f27f-43ec-ae63-244c6ebeee5f', 'a4205119-6927-48fb-a9e3-cc92f5e9a6fd', 123, '2022-01-02 10:24:00.483', '2022-01-02 10:24:00.483');


--
-- Data for Name: products; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.products (uid, item_name, image_urls, price, created_at, updated_at, category_uid) VALUES ('f193e247-9a8f-4d24-8466-bd9a3c14c623', 'BERGMUND dining chair', '{"urls":["https://www.ikea.com/nl/nl/images/products/bergmund-stoelovertrek-ljungen-lichtgroen__0859535_pe780959_s5.jpg"]}', 14.95, '2022-01-02 10:24:00.381', '2022-01-02 10:24:00.381', '6f0c233d-a33e-43f7-b6e8-50b4f7acf059');
INSERT INTO public.products (uid, item_name, image_urls, price, created_at, updated_at, category_uid) VALUES ('bad0f78d-baeb-4b7c-8cbe-9ba480212375', 'MÖRBYLÅNGA dining table', '{"urls":["https://www.ikea.com/nl/nl/images/products/morbylanga-tafel-eikenfineer-bruin-gelazuurd__0737108_pe740890_s5.jpg"]}', 399, '2022-01-02 10:24:00.414', '2022-01-02 10:24:00.414', '6fe82c4a-9f5d-49f4-9292-0440547bbfc4');
INSERT INTO public.products (uid, item_name, image_urls, price, created_at, updated_at, category_uid) VALUES ('b92bd05e-bd5d-4ee2-8ea4-65c665bac850', 'YPPERLIG dining table', '{"urls":["https://www.ikea.com/nl/nl/images/products/ypperlig-tafel-essen__0737127_pe740896_s5.jpg"]}', 199, '2022-01-02 10:24:00.433', '2022-01-02 10:24:00.433', '6fe82c4a-9f5d-49f4-9292-0440547bbfc4');
INSERT INTO public.products (uid, item_name, image_urls, price, created_at, updated_at, category_uid) VALUES ('6fcab02a-6d3f-4d15-9655-1bfc21372633', 'LAGKAPTEN / ALEX bureau', '{"urls":["https://www.ikea.com/nl/nl/images/products/lagkapten-alex-bureau-wit-gelazuurd-eikeneffect-wit__1022005_pe832395_s5.jpg"]}', 99.94, '2022-01-02 10:24:00.454', '2022-01-02 10:24:00.454', '6fe82c4a-9f5d-49f4-9292-0440547bbfc4');
INSERT INTO public.products (uid, item_name, image_urls, price, created_at, updated_at, category_uid) VALUES ('5f37d8e1-f27f-43ec-ae63-244c6ebeee5f', 'GULLIGAST children''s mobiel', '{"urls":["https://www.ikea.com/nl/nl/images/products/gulligast-mobiel__0928848_pe789986_s5.jpg"]}', 7.99, '2022-01-02 10:24:00.475', '2022-01-02 10:24:00.475', '1e8d8cdc-0e5b-4105-ad75-df3087d8a912');


--
-- Data for Name: roles; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.roles (uid, item_name, created_at, updated_at) VALUES ('d2bb0986-93ac-49e5-a3a2-1735a420160a', 'ADMIN', '2022-01-01 21:33:54.962468', '2022-01-01 21:33:54.962468');
INSERT INTO public.roles (uid, item_name, created_at, updated_at) VALUES ('b9b4b937-537d-4444-8b39-73c3586e092e', 'READ_ONLY', '2022-01-01 21:33:54.962468', '2022-01-01 21:33:54.962468');


--
-- Data for Name: transaction_products; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.transaction_products (uid, transaction_uid, product_uid, amount_of, created_at) VALUES ('b1fe7357-8345-4a6a-ac69-92a2e76020ca', 'b6375bb9-c885-4cdc-974e-b437e708dd46', 'bad0f78d-baeb-4b7c-8cbe-9ba480212375', 1, '2022-01-02 10:29:32.193');


--
-- Data for Name: transactions; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.transactions (uid, created_at, user_uid) VALUES ('b6375bb9-c885-4cdc-974e-b437e708dd46', '2022-01-02 10:29:32.188', '5686e49f-2277-40c3-9b1b-46b4ec4464f6');


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: warehouse_user
--

INSERT INTO public.users (uid, item_name, email, password, role_uid, created_at, updated_at) VALUES ('5686e49f-2277-40c3-9b1b-46b4ec4464f6', 'averageflow', 'jjba@protonmail.ch', '$2a$10$6Zz71QkrbGP60Eoovy8t3en1pn8vpmzCr0fMC3QB5G9FM2Za0mrKS', 'd2bb0986-93ac-49e5-a3a2-1735a420160a', '2022-01-01 23:00:56.676', '2022-01-01 23:00:56.676');


--
-- Name: article_stocks article_stocks_article_uid_key; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.article_stocks
    ADD CONSTRAINT article_stocks_article_uid_key UNIQUE (article_uid);


--
-- Name: article_stocks article_stocks_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.article_stocks
    ADD CONSTRAINT article_stocks_pkey PRIMARY KEY (uid);


--
-- Name: articles articles_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.articles
    ADD CONSTRAINT articles_pkey PRIMARY KEY (uid);


--
-- Name: categories categories_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (uid);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: product_articles product_articles_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.product_articles
    ADD CONSTRAINT product_articles_pkey PRIMARY KEY (uid);


--
-- Name: products products_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT products_pkey PRIMARY KEY (uid);


--
-- Name: roles roles_item_name_key; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_item_name_key UNIQUE (item_name);


--
-- Name: roles roles_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.roles
    ADD CONSTRAINT roles_pkey PRIMARY KEY (uid);


--
-- Name: transaction_products transaction_products_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.transaction_products
    ADD CONSTRAINT transaction_products_pkey PRIMARY KEY (uid);


--
-- Name: transactions transactions_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT transactions_pkey PRIMARY KEY (uid);


--
-- Name: users users_email_key; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_email_key UNIQUE (email);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (uid);


--
-- Name: article_stocks fk_article_stocks_article; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.article_stocks
    ADD CONSTRAINT fk_article_stocks_article FOREIGN KEY (article_uid) REFERENCES public.articles(uid);


--
-- Name: products fk_category_products; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.products
    ADD CONSTRAINT fk_category_products FOREIGN KEY (category_uid) REFERENCES public.categories(uid);


--
-- Name: product_articles fk_product_articles_article; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.product_articles
    ADD CONSTRAINT fk_product_articles_article FOREIGN KEY (article_uid) REFERENCES public.articles(uid);


--
-- Name: product_articles fk_product_articles_product; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.product_articles
    ADD CONSTRAINT fk_product_articles_product FOREIGN KEY (product_uid) REFERENCES public.products(uid);


--
-- Name: users fk_role_users; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT fk_role_users FOREIGN KEY (role_uid) REFERENCES public.roles(uid);


--
-- Name: transaction_products fk_transaction_products_product; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.transaction_products
    ADD CONSTRAINT fk_transaction_products_product FOREIGN KEY (product_uid) REFERENCES public.products(uid);


--
-- Name: transaction_products fk_transaction_products_transaction; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.transaction_products
    ADD CONSTRAINT fk_transaction_products_transaction FOREIGN KEY (transaction_uid) REFERENCES public.transactions(uid);


--
-- Name: transactions fk_user_transactions; Type: FK CONSTRAINT; Schema: public; Owner: warehouse_user
--

ALTER TABLE ONLY public.transactions
    ADD CONSTRAINT fk_user_transactions FOREIGN KEY (user_uid) REFERENCES public.users(uid) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

