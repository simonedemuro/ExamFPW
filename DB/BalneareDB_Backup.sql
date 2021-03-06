PGDMP     0    .    	            y        
   BalneareDB #   12.8 (Ubuntu 12.8-0ubuntu0.20.04.1) #   12.8 (Ubuntu 12.8-0ubuntu0.20.04.1) %    ?           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            ?           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            ?           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            ?           1262    24576 
   BalneareDB    DATABASE     ~   CREATE DATABASE "BalneareDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LC_COLLATE = 'en_US.UTF-8' LC_CTYPE = 'en_US.UTF-8';
    DROP DATABASE "BalneareDB";
                fpw    false            ?            1259    24665    available_slot    TABLE       CREATE TABLE public.available_slot (
    "Id" integer NOT NULL,
    data date NOT NULL,
    day_part character(2) NOT NULL,
    n_available integer,
    CONSTRAINT available_slot_day_part_check CHECK (((day_part = 'AM'::bpchar) OR (day_part = 'PM'::bpchar)))
);
 "   DROP TABLE public.available_slot;
       public         heap    fpw    false            ?            1259    24663    available_slot_Id_seq    SEQUENCE     ?   CREATE SEQUENCE public."available_slot_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 .   DROP SEQUENCE public."available_slot_Id_seq";
       public          fpw    false    209            ?           0    0    available_slot_Id_seq    SEQUENCE OWNED BY     S   ALTER SEQUENCE public."available_slot_Id_seq" OWNED BY public.available_slot."Id";
          public          fpw    false    208            ?            1259    24642    invoice    TABLE     )  CREATE TABLE public.invoice (
    "Id" integer NOT NULL,
    "Id_user" bigint NOT NULL,
    price numeric(2,0) NOT NULL,
    description character varying(1000),
    reservation_date date NOT NULL,
    reservation_period character varying(1000) NOT NULL,
    num_reserved_slot integer NOT NULL
);
    DROP TABLE public.invoice;
       public         heap    fpw    false            ?            1259    24640    invoice_Id_seq    SEQUENCE     ?   CREATE SEQUENCE public."invoice_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 '   DROP SEQUENCE public."invoice_Id_seq";
       public          fpw    false    207            ?           0    0    invoice_Id_seq    SEQUENCE OWNED BY     E   ALTER SEQUENCE public."invoice_Id_seq" OWNED BY public.invoice."Id";
          public          fpw    false    206            ?            1259    24624    reservation    TABLE     ?   CREATE TABLE public.reservation (
    "Id" integer NOT NULL,
    "Id_user" bigint NOT NULL,
    reservation_date date DEFAULT CURRENT_DATE NOT NULL,
    num_reserved_slot integer NOT NULL,
    reservation_period character varying(1000)
);
    DROP TABLE public.reservation;
       public         heap    fpw    false            ?            1259    24622    reservation_Id_seq    SEQUENCE     ?   CREATE SEQUENCE public."reservation_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public."reservation_Id_seq";
       public          fpw    false    205            ?           0    0    reservation_Id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public."reservation_Id_seq" OWNED BY public.reservation."Id";
          public          fpw    false    204            ?            1259    24590    user    TABLE     ?  CREATE TABLE public."user" (
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
    DROP TABLE public."user";
       public         heap    fpw    false            ?            1259    24588    user_Id_seq    SEQUENCE     ?   CREATE SEQUENCE public."user_Id_seq"
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public."user_Id_seq";
       public          fpw    false    203            ?           0    0    user_Id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public."user_Id_seq" OWNED BY public."user"."Id";
          public          fpw    false    202            ?            1259    24696    view_user_totslot    VIEW       CREATE VIEW public.view_user_totslot AS
 SELECT u.username,
        CASE
            WHEN u."isAdmin" THEN 'admin'::text
            ELSE 'simple'::text
        END AS role,
    u.name,
    u.surname,
    u.sex,
    u.birthday,
    u.fiscalnumber,
    u.email,
    u.phone,
        CASE
            WHEN u.invoiceoptin THEN 'Si'::text
            ELSE 'No'::text
        END AS invoiceoptin,
    (COALESCE(sum(r.num_reserved_slot), (0)::bigint) + COALESCE(sum(i.num_reserved_slot), (0)::bigint)) AS tot_num_res
   FROM ((public."user" u
     LEFT JOIN public.invoice i ON ((u."Id" = i."Id_user")))
     LEFT JOIN public.reservation r ON ((u."Id" = r."Id_user")))
  GROUP BY u.username, u."isAdmin", u.name, u.surname, u.sex, u.birthday, u.fiscalnumber, u.email, u.phone, u.invoiceoptin;
 $   DROP VIEW public.view_user_totslot;
       public          fpw    false    203    203    203    203    203    203    203    207    207    205    205    203    203    203    203            ,           2604    24668    available_slot Id    DEFAULT     z   ALTER TABLE ONLY public.available_slot ALTER COLUMN "Id" SET DEFAULT nextval('public."available_slot_Id_seq"'::regclass);
 B   ALTER TABLE public.available_slot ALTER COLUMN "Id" DROP DEFAULT;
       public          fpw    false    208    209    209            +           2604    24645 
   invoice Id    DEFAULT     l   ALTER TABLE ONLY public.invoice ALTER COLUMN "Id" SET DEFAULT nextval('public."invoice_Id_seq"'::regclass);
 ;   ALTER TABLE public.invoice ALTER COLUMN "Id" DROP DEFAULT;
       public          fpw    false    206    207    207            )           2604    24627    reservation Id    DEFAULT     t   ALTER TABLE ONLY public.reservation ALTER COLUMN "Id" SET DEFAULT nextval('public."reservation_Id_seq"'::regclass);
 ?   ALTER TABLE public.reservation ALTER COLUMN "Id" DROP DEFAULT;
       public          fpw    false    205    204    205            '           2604    24593    user Id    DEFAULT     h   ALTER TABLE ONLY public."user" ALTER COLUMN "Id" SET DEFAULT nextval('public."user_Id_seq"'::regclass);
 :   ALTER TABLE public."user" ALTER COLUMN "Id" DROP DEFAULT;
       public          fpw    false    202    203    203            ?          0    24665    available_slot 
   TABLE DATA           K   COPY public.available_slot ("Id", data, day_part, n_available) FROM stdin;
    public          fpw    false    209   .       ?          0    24642    invoice 
   TABLE DATA              COPY public.invoice ("Id", "Id_user", price, description, reservation_date, reservation_period, num_reserved_slot) FROM stdin;
    public          fpw    false    207   ?/       ?          0    24624    reservation 
   TABLE DATA           o   COPY public.reservation ("Id", "Id_user", reservation_date, num_reserved_slot, reservation_period) FROM stdin;
    public          fpw    false    205   .0       ?          0    24590    user 
   TABLE DATA           ?   COPY public."user" ("Id", "isAdmin", username, password, name, surname, birthday, fiscalnumber, sex, email, phone, invoiceoptin) FROM stdin;
    public          fpw    false    203   ?0       ?           0    0    available_slot_Id_seq    SEQUENCE SET     G   SELECT pg_catalog.setval('public."available_slot_Id_seq"', 130, true);
          public          fpw    false    208            ?           0    0    invoice_Id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public."invoice_Id_seq"', 8, true);
          public          fpw    false    206            ?           0    0    reservation_Id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public."reservation_Id_seq"', 18, true);
          public          fpw    false    204            ?           0    0    user_Id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public."user_Id_seq"', 19, true);
          public          fpw    false    202            7           2606    24670     available_slot available_slot_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.available_slot
    ADD CONSTRAINT available_slot_pk PRIMARY KEY ("Id");
 J   ALTER TABLE ONLY public.available_slot DROP CONSTRAINT available_slot_pk;
       public            fpw    false    209            4           2606    24650    invoice invoice_pk 
   CONSTRAINT     R   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_pk PRIMARY KEY ("Id");
 <   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_pk;
       public            fpw    false    207            2           2606    24629    reservation reservation_pk 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pk PRIMARY KEY ("Id");
 D   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pk;
       public            fpw    false    205            /           2606    24608    user user_pk 
   CONSTRAINT     N   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pk PRIMARY KEY ("Id");
 8   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pk;
       public            fpw    false    203            5           1259    24675 #   available_slot_data_day_part_uindex    INDEX     o   CREATE UNIQUE INDEX available_slot_data_day_part_uindex ON public.available_slot USING btree (data, day_part);
 7   DROP INDEX public.available_slot_data_day_part_uindex;
       public            fpw    false    209    209            0           1259    24598    user_username_uindex    INDEX     R   CREATE UNIQUE INDEX user_username_uindex ON public."user" USING btree (username);
 (   DROP INDEX public.user_username_uindex;
       public            fpw    false    203            9           2606    24651    invoice invoice_user_id_fk    FK CONSTRAINT     ~   ALTER TABLE ONLY public.invoice
    ADD CONSTRAINT invoice_user_id_fk FOREIGN KEY ("Id_user") REFERENCES public."user"("Id");
 D   ALTER TABLE ONLY public.invoice DROP CONSTRAINT invoice_user_id_fk;
       public          fpw    false    203    2863    207            8           2606    24630 "   reservation reservation_user_id_fk    FK CONSTRAINT     ?   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_user_id_fk FOREIGN KEY ("Id_user") REFERENCES public."user"("Id");
 L   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_user_id_fk;
       public          fpw    false    205    2863    203            ?   ?  x?M??m?@??N/H??c
x????#A,??~??	O߯???ݷ????+f???Ki??l?.l???s???;x?0??q?i?	sڂmø?c??={,??X???8#0cqF`?⌨̪#???>?m?(?hG??ЎҺv?6?????-?(mkGiG;J???cǴ?4׎?B;J??Q?d?=???????????[Z???2+??!??h??nZ????,?????:?Fa????8?^?m"
?N?B???U?nܨw?F?;7??ݹQ???5??ġ???\<pk?????&???]??&??<ph?????&ܚ<?h????'v???
???q???????L8ڐ+i?m??>??>??????Y??j2?_?_~????~??l??Ҏ~ﱹ???~۔E?j??Zk??H      ?   X   x???44?41?H??ITHITHJML?P(???I?T??S((J-.?,??4202?5??52?tI̱R???}?E?|9??b???? m      ?   G   x?34?44?4202?5??52?4?tI̱R???*8?*8"??+?r?pZ"?3E?gd?????/F??? /??      ?     x?U??N?0??????A??0?G?2Ы1&N)?T????zc??MvNWw? ?2?Y(DUJ8kcWMʨ?eFx?\???ۻ???????????qz?j??֠q\u?a?R????y#?%??r?8?tN[p?{????̖?-U?v?֣?????tz??5?h!D??Β~??
?U[?Z?l??
???\????-?x??'?hBE??Iy!y"???飹???#ed?ˮ??Q?PeU??;F?TmU     