PGDMP  (    +                |            tourismagency    16.2    16.2 "    5           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            6           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            7           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            8           1262    17112    tourismagency    DATABASE     y   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'en_US.UTF-8';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    17122    hotel    TABLE     �  CREATE TABLE public.hotel (
    hotel_id integer NOT NULL,
    hotel_name text NOT NULL,
    hotel_address text NOT NULL,
    hotel_mail text NOT NULL,
    hotel_phone text NOT NULL,
    hotel_star text NOT NULL,
    hotel_car_park boolean NOT NULL,
    hotel_wifi boolean NOT NULL,
    hotel_pool boolean NOT NULL,
    hotel_fitness boolean NOT NULL,
    hotel_concierge boolean NOT NULL,
    hotel_spa boolean NOT NULL,
    hotel_room_service boolean NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false            �            1259    17121    hotel_hotel_id_seq    SEQUENCE     �   ALTER TABLE public.hotel ALTER COLUMN hotel_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.hotel_hotel_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    218            �            1259    17147    pension    TABLE     �   CREATE TABLE public.pension (
    pension_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
    DROP TABLE public.pension;
       public         heap    postgres    false            �            1259    17146    pension_pension_id_seq    SEQUENCE     �   ALTER TABLE public.pension ALTER COLUMN pension_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.pension_pension_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    224            �            1259    17154    reservation    TABLE     �  CREATE TABLE public.reservation (
    reservation_id integer NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    check_out_date date NOT NULL,
    total_price double precision NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL,
    guest_count integer NOT NULL,
    number_of_night integer NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false            �            1259    17157    reservation_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.reservation ALTER COLUMN reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.reservation_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    225            �            1259    17139    room    TABLE     �  CREATE TABLE public.room (
    room_id integer NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type text NOT NULL,
    stock integer NOT NULL,
    adult_price double precision NOT NULL,
    child_price double precision NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean NOT NULL,
    minibar boolean NOT NULL,
    game_console boolean NOT NULL,
    cash_box boolean NOT NULL
);
    DROP TABLE public.room;
       public         heap    postgres    false            �            1259    17138    room_room_id_seq    SEQUENCE     �   ALTER TABLE public.room ALTER COLUMN room_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.room_room_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    222            �            1259    17129    season    TABLE     �   CREATE TABLE public.season (
    season_id integer NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
    DROP TABLE public.season;
       public         heap    postgres    false            �            1259    17132    season_season_id_seq    SEQUENCE     �   ALTER TABLE public.season ALTER COLUMN season_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.season_season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    17114    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_password text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17113    user_user_id_seq    SEQUENCE     �   ALTER TABLE public."user" ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    216            *          0    17122    hotel 
   TABLE DATA           �   COPY public.hotel (hotel_id, hotel_name, hotel_address, hotel_mail, hotel_phone, hotel_star, hotel_car_park, hotel_wifi, hotel_pool, hotel_fitness, hotel_concierge, hotel_spa, hotel_room_service) FROM stdin;
    public          postgres    false    218   6)       0          0    17147    pension 
   TABLE DATA           E   COPY public.pension (pension_id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    224   n*       1          0    17154    reservation 
   TABLE DATA           �   COPY public.reservation (reservation_id, room_id, check_in_date, check_out_date, total_price, guest_name, guest_citizen_id, guest_mail, guest_phone, guest_count, number_of_night) FROM stdin;
    public          postgres    false    225   �*       .          0    17139    room 
   TABLE DATA           �   COPY public.room (room_id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box) FROM stdin;
    public          postgres    false    222   �+       +          0    17129    season 
   TABLE DATA           N   COPY public.season (season_id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    219   ,       (          0    17114    user 
   TABLE DATA           N   COPY public."user" (user_id, user_name, user_password, user_role) FROM stdin;
    public          postgres    false    216   �,       9           0    0    hotel_hotel_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.hotel_hotel_id_seq', 8, true);
          public          postgres    false    217            :           0    0    pension_pension_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.pension_pension_id_seq', 9, true);
          public          postgres    false    223            ;           0    0    reservation_reservation_id_seq    SEQUENCE SET     L   SELECT pg_catalog.setval('public.reservation_reservation_id_seq', 4, true);
          public          postgres    false    226            <           0    0    room_room_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.room_room_id_seq', 12, true);
          public          postgres    false    221            =           0    0    season_season_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.season_season_id_seq', 11, true);
          public          postgres    false    220            >           0    0    user_user_id_seq    SEQUENCE SET     >   SELECT pg_catalog.setval('public.user_user_id_seq', 9, true);
          public          postgres    false    215            �           2606    17128    hotel hotel_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (hotel_id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    218            �           2606    17153    pension pension_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.pension
    ADD CONSTRAINT pension_pkey PRIMARY KEY (pension_id);
 >   ALTER TABLE ONLY public.pension DROP CONSTRAINT pension_pkey;
       public            postgres    false    224            �           2606    17164    reservation reservation_pkey 
   CONSTRAINT     f   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (reservation_id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    225            �           2606    17145    room room_pkey 
   CONSTRAINT     Q   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (room_id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    222            �           2606    17137    season season_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.season
    ADD CONSTRAINT season_pkey PRIMARY KEY (season_id);
 <   ALTER TABLE ONLY public.season DROP CONSTRAINT season_pkey;
       public            postgres    false    219            �           2606    17120    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    216            *   (  x�u��N1���O�K���=��"��bb��l�%��/�+�;,�e�EQ;i������P�K�S�o��
(��{j�їx��\n�vr��$pQ"��g�r�N�6�ݶ{#pyL��8t���d�Xe|W�s�ݻ��D���P(�0ݿɑq]-qO���^����C_(�'&��Ђg�d;��t�&0?�N����(��Qh?<�Q�@Wp/W�Y~���#�6�ͤ���.u��	U}1���qd�5��RXs��Sܒ�u;�]���:�ǩ�c���;��ΦERf���ZA}�w��      0   c   x�3�4��H�IS(H�+����2�4A0����UH*JM�NK,.�2�A2�4E2
����(8�'�pYrZp:��($�(8�)h8�9ir��qqq b3(�      1   �   x�}�;�0�z}��(�������fe��I�(E�w�`��A��h��ȵ����,��0'�.qI
�e�#�}�o�2O��&�#!�U����������j�M!�P��wݨ}�E!�-86���6{!���z�Q��c��S��v1k�3
�������<�      .   k   x�U�A
�0ϛW�Ij+��^�Z���n+�2$��b�u���8�4�EP�Sx�%��q��"�2�b6�E$�C��G�׳:���3����^�:_옑��?�#k      +   _   x�u�A
�0��������K���h,
fVRB��*>�##�ǱPPTXɶ��sE�S�1��ب��e�k��Yc�o�B��C��`�	=�$      (   S   x�3�L�K-�4426�tt����2����I�D2��H,*
YZ�q����G��r�qf��f�祢Is&��� ���� �!     