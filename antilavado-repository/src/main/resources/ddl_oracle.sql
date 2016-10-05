
    drop table ACCOUNTS_TYPES cascade constraints;

    drop table AFIP_ACTIVITIES cascade constraints;

    drop table AUTHORITIES cascade constraints;

    drop table BANKS cascade constraints;

    drop table BANK_ACCOUNTS cascade constraints;

    drop table CLIENTS cascade constraints;

    drop table COUNTRIES cascade constraints;

    drop table DISTRICTS cascade constraints;

    drop table DOCUMENTS_TYPES cascade constraints;

    drop table DOMICILES cascade constraints;

    drop table ECONOMIC_FINANCIAL_PROFILES cascade constraints;

    drop table FILES_MOVEMENT cascade constraints;

    drop table GENERAL_FILES cascade constraints;

    drop table LOCALITIES cascade constraints;

    drop table PROVINCES cascade constraints;

    drop table SOCIETIES_TYPE cascade constraints;

    drop table TAXPAYERS_TYPE cascade constraints;

    drop table TELEPHONES_TYPE cascade constraints;

    drop table USERS cascade constraints;

    drop table USERS_AUTHORITIES cascade constraints;

    drop sequence hibernate_sequence;

    create table ACCOUNTS_TYPES (
        ACCOUNTS_TYPE_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        primary key (ACCOUNTS_TYPE_ID)
    );

    create table AFIP_ACTIVITIES (
        AFIP_ACTIVITIES_ID number(19,0) not null,
        CODE varchar2(3 char) not null,
        DESCRIPTION varchar2(650 char) not null,
        PARENT varchar2(3 char),
        primary key (AFIP_ACTIVITIES_ID)
    );

    create table AUTHORITIES (
        NAME varchar2(50 char) not null,
        primary key (NAME)
    );

    create table BANKS (
        BANK_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        STATUS number(10,0) not null,
        primary key (BANK_ID)
    );

    create table BANK_ACCOUNTS (
        BANK_ACCOUNT_ID number(19,0) not null,
        CREATED_BY varchar2(255 char) not null,
        CREATED_DATE timestamp not null,
        LAST_MODIFIED_BY varchar2(255 char),
        LAST_MODIFIED_DATE timestamp,
        BRANCH_OFFICE varchar2(50 char) not null,
        CBU varchar2(14 char) not null,
        STATUS number(10,0) not null,
        ACCOUNT_TYPE_ID number(19,0) not null,
        BANK_ID number(19,0) not null,
        CLIENT_ID number(19,0) not null,
        primary key (BANK_ACCOUNT_ID)
    );

    create table CLIENTS (
        CLIENT_ID number(19,0) not null,
        CREATED_BY varchar2(255 char) not null,
        CREATED_DATE timestamp not null,
        LAST_MODIFIED_BY varchar2(255 char),
        LAST_MODIFIED_DATE timestamp,
        AFFIDAVIT_HOME number(1,0),
        AFIP_ACTIVITY varchar2(10 char) not null,
        AFIP_CONST number(1,0),
        BIRTHDAY timestamp not null,
        BUSINESS_NAME varchar2(100 char) not null,
        CUIT_CUIL varchar2(25 char) not null,
        DOCUMENT_NUMBER varchar2(25 char),
        EMAIL varchar2(50 char) not null,
        IS_INDIVIDUAL number(1,0) not null,
        LAST_NAMES varchar2(100 char) not null,
        LUT number(1,0) not null,
        NAMES varchar2(100 char) not null,
        NOSIS number(1,0),
        PEP number(1,0),
        PUBLIC_SERVICE number(1,0),
        ROS number(1,0) not null,
        SOI number(1,0) not null,
        STATUS number(10,0) not null,
        WORLDSYS number(1,0),
        COUNTRY_ID number(19,0) not null,
        DOCUMENT_TYPE_ID number(19,0) not null,
        SOCIETY_TYPE_ID number(19,0) not null,
        TAXPAYER_TYPE_ID number(19,0) not null,
        primary key (CLIENT_ID)
    );

    create table COUNTRIES (
        COUNTRY_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        LOW_TAXATION number(10,0),
        primary key (COUNTRY_ID)
    );

    create table DISTRICTS (
        DISTRICT_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        PROVINCE_ID number(19,0) not null,
        primary key (DISTRICT_ID)
    );

    create table DOCUMENTS_TYPES (
        DOCUMENT_TYPE_ID number(19,0) not null,
        DESCRIPTION varchar2(10 char) not null,
        STATUS number(10,0) not null,
        primary key (DOCUMENT_TYPE_ID)
    );

    create table DOMICILES (
        DOMICILE_ID number(19,0) not null,
        CREATED_BY varchar2(255 char) not null,
        CREATED_DATE timestamp not null,
        LAST_MODIFIED_BY varchar2(255 char),
        LAST_MODIFIED_DATE timestamp,
        DEPARTMENT varchar2(4 char),
        FLOOR varchar2(2 char),
        LEGAL_DOMICILE number(10,0) not null,
        NUMBER number(10,0) not null,
        POSTAL_CODE varchar2(10 char) not null,
        REAL_DOMICILE number(10,0) not null,
        STATUS number(10,0) not null,
        STREET varchar2(100 char) not null,
        CLIENT_ID number(19,0) not null,
        DISTRICT_ID number(19,0) not null,
        LOCALITY_ID number(19,0) not null,
        PROVINCE_ID number(19,0) not null,
        primary key (DOMICILE_ID)
    );

    create table ECONOMIC_FINANCIAL_PROFILES (
        BANK_ACCOUNT_ID number(19,0) not null,
        MARKET_BACKGROUND number(10,0) not null,
        PATRIMONY number(10,0) not null,
        REASON varchar2(400 char),
        CLIENT_ID number(19,0) not null,
        primary key (BANK_ACCOUNT_ID, CLIENT_ID)
    );

    create table FILES_MOVEMENT (
        FILES_MOVEMENT_ID number(19,0) not null,
        APPLICANT varchar2(50 char) not null,
        COMMENTS varchar2(100 char),
        LOCATION varchar2(50 char) not null,
        ORDER_DATE timestamp not null,
        RETURN_DATE timestamp,
        SHIPPING_DATE timestamp not null,
        CLIENT_ID number(19,0) not null,
        primary key (FILES_MOVEMENT_ID, CLIENT_ID)
    );

    create table GENERAL_FILES (
        GENERAL_FILE_ID number(19,0) not null,
        "COLUMN" varchar2(4 char) not null,
        ROW varchar2(4 char) not null,
        STATUS varchar2(50 char) not null,
        CLIENT_ID number(19,0) not null,
        primary key (GENERAL_FILE_ID, CLIENT_ID)
    );

    create table LOCALITIES (
        LOCALITY_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        DISTRICT_ID number(19,0) not null,
        PROVINCE_ID number(19,0) not null,
        primary key (LOCALITY_ID)
    );

    create table PROVINCES (
        PROVINCE_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        primary key (PROVINCE_ID)
    );

    create table SOCIETIES_TYPE (
        SOCIETY_TYPE_ID number(19,0) not null,
        DESCRIPTION varchar2(100 char) not null,
        primary key (SOCIETY_TYPE_ID)
    );

    create table TAXPAYERS_TYPE (
        TAXPAYER_TYPE_ID number(19,0) not null,
        DESCRIPTION varchar2(100 char) not null,
        primary key (TAXPAYER_TYPE_ID)
    );

    create table TELEPHONES_TYPE (
        TELEPHONE_TYPE_ID number(19,0) not null,
        DESCRIPTION varchar2(50 char) not null,
        primary key (TELEPHONE_TYPE_ID)
    );

    create table USERS (
        USER_ID number(19,0) not null,
        CREATED_BY varchar2(255 char) not null,
        CREATED_DATE timestamp not null,
        LAST_MODIFIED_BY varchar2(255 char),
        LAST_MODIFIED_DATE timestamp,
        ACCOUNT_NON_EXPIRED number(1,0) not null,
        ACCOUNT_NON_LOCKED number(1,0) not null,
        CREDENTIALS_NON_EXPIRED number(1,0) not null,
        DESCRIPTION varchar2(255 char),
        EMAIL varchar2(50 char) not null,
        ENABLED number(1,0) not null,
        FIRST_NAME varchar2(50 char) not null,
        LAST_NAME varchar2(50 char) not null,
        LOGIN varchar2(50 char) not null,
        PASSWORD varchar2(100 char) not null,
        primary key (USER_ID)
    );

    create table USERS_AUTHORITIES (
        USER_ID number(19,0) not null,
        AUTHORITY_NAME varchar2(50 char) not null,
        primary key (USER_ID, AUTHORITY_NAME)
    );

    alter table ACCOUNTS_TYPES 
        add constraint UK_ql1v7rdpd9s6wubojyihbogmu  unique (DESCRIPTION);

    create index AFIP_ACTIVITIES_CODE_ID_INDEX on AFIP_ACTIVITIES (CODE);

    create index AFIP_ACTIVITIES_PARENT_ID_INDEX on AFIP_ACTIVITIES (PARENT);

    alter table BANKS 
        add constraint UK_tkmanudhn143nsnv6w6af9jv7  unique (DESCRIPTION);

    alter table COUNTRIES 
        add constraint UK_4trhe8u4qab9vedrigtawgd4l  unique (DESCRIPTION);

    alter table DISTRICTS 
        add constraint UK_658sq5mddm3hovobxi0xhp5gk  unique (DESCRIPTION);

    alter table DOCUMENTS_TYPES 
        add constraint UK_82past65bpakem46yucb3au1c  unique (DESCRIPTION);

    create index ECONOMIC_FINANCIAL_PROFILES_CLIENT_ID_INDEX on ECONOMIC_FINANCIAL_PROFILES (CLIENT_ID);

    create index FILES_MOVEMENT_CLIENT_ID_INDEX on FILES_MOVEMENT (CLIENT_ID);

    create index GENERAL_FILES_CLIENT_ID_INDEX on GENERAL_FILES (CLIENT_ID);

    alter table LOCALITIES 
        add constraint UK_jn0vk5xvwwy8kivf5ob49wu84  unique (DESCRIPTION);

    alter table PROVINCES 
        add constraint UK_424ecbxfts31kfbkkprajy3nu  unique (DESCRIPTION);

    alter table SOCIETIES_TYPE 
        add constraint UK_iqrov9k8km3kb1n69may0ayl2  unique (DESCRIPTION);

    alter table TAXPAYERS_TYPE 
        add constraint UK_3jkpgg40bvpk3f9w9hfgheqit  unique (DESCRIPTION);

    alter table TELEPHONES_TYPE 
        add constraint UK_bu7ac0ovlaljfs5xbmb8wq742  unique (DESCRIPTION);

    alter table USERS 
        add constraint UK_l3c3ahdulnjx8bt2ivgyvh1ss  unique (LOGIN);

    alter table BANK_ACCOUNTS 
        add constraint FK_921fc3vjcj0xcjxm0xho79r1h 
        foreign key (ACCOUNT_TYPE_ID) 
        references ACCOUNTS_TYPES;

    alter table BANK_ACCOUNTS 
        add constraint FK_c6kqefxpenwu4u5aae5wkaf8p 
        foreign key (BANK_ID) 
        references BANKS;

    alter table BANK_ACCOUNTS 
        add constraint FK_406ovq2g8gu2o25juw29ma4hm 
        foreign key (CLIENT_ID) 
        references CLIENTS;

    alter table CLIENTS 
        add constraint FK_h323ko88kwrgt4ah3gs3iv36m 
        foreign key (COUNTRY_ID) 
        references COUNTRIES;

    alter table CLIENTS 
        add constraint FK_ia9u7hi0drq1fkif6uy7un35a 
        foreign key (DOCUMENT_TYPE_ID) 
        references DOCUMENTS_TYPES;

    alter table CLIENTS 
        add constraint FK_r8avuoeh58jfkn03djrqw5lhh 
        foreign key (SOCIETY_TYPE_ID) 
        references SOCIETIES_TYPE;

    alter table CLIENTS 
        add constraint FK_9thmbfqpmyhcplurfa5tylmuf 
        foreign key (TAXPAYER_TYPE_ID) 
        references TAXPAYERS_TYPE;

    alter table DISTRICTS 
        add constraint FK_i1nt8mbsp9pm405lwu87xmkm8 
        foreign key (PROVINCE_ID) 
        references PROVINCES;

    alter table DOMICILES 
        add constraint FK_3q1l4nf4y60u6ilqk7pmonbiy 
        foreign key (CLIENT_ID) 
        references CLIENTS;

    alter table DOMICILES 
        add constraint FK_2gy35i0mncci56364vd69op5c 
        foreign key (DISTRICT_ID) 
        references DISTRICTS;

    alter table DOMICILES 
        add constraint FK_nhd6ntjhyisllxm0jex53pevd 
        foreign key (LOCALITY_ID) 
        references LOCALITIES;

    alter table DOMICILES 
        add constraint FK_fok8eur6jfgmqmqxff5plei6t 
        foreign key (PROVINCE_ID) 
        references PROVINCES;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_j225e57pfl86eyrp7wvxiq1as 
        foreign key (CLIENT_ID) 
        references CLIENTS;

    alter table FILES_MOVEMENT 
        add constraint FK_cw2njgv8n5u24jtfr87kjl3w0 
        foreign key (CLIENT_ID) 
        references CLIENTS;

    alter table GENERAL_FILES 
        add constraint FK_ghj29xc4dp5ssh9mgffyy8mfb 
        foreign key (CLIENT_ID) 
        references CLIENTS;

    alter table LOCALITIES 
        add constraint FK_pa2tub5ue9csmnhatpssde4vk 
        foreign key (DISTRICT_ID) 
        references DISTRICTS;

    alter table LOCALITIES 
        add constraint FK_sfgjb7ny137f1fp154ijlpwj9 
        foreign key (PROVINCE_ID) 
        references PROVINCES;

    alter table USERS_AUTHORITIES 
        add constraint FK_5dan5n2o70hkqguh9dise46eh 
        foreign key (AUTHORITY_NAME) 
        references AUTHORITIES;

    alter table USERS_AUTHORITIES 
        add constraint FK_sj95eivhw57gxc2uacv2ilrko 
        foreign key (USER_ID) 
        references USERS;

    create sequence hibernate_sequence;
