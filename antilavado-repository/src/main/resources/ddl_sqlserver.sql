
    alter table BANK_ACCOUNTS 
        drop constraint FK_921fc3vjcj0xcjxm0xho79r1h;

    alter table BANK_ACCOUNTS 
        drop constraint FK_c6kqefxpenwu4u5aae5wkaf8p;

    alter table BANK_ACCOUNTS 
        drop constraint FK_406ovq2g8gu2o25juw29ma4hm;

    alter table CLIENTS 
        drop constraint FK_h323ko88kwrgt4ah3gs3iv36m;

    alter table CLIENTS 
        drop constraint FK_ia9u7hi0drq1fkif6uy7un35a;

    alter table CLIENTS 
        drop constraint FK_r8avuoeh58jfkn03djrqw5lhh;

    alter table CLIENTS 
        drop constraint FK_9thmbfqpmyhcplurfa5tylmuf;

    alter table DISTRICTS 
        drop constraint FK_i1nt8mbsp9pm405lwu87xmkm8;

    alter table DOMICILES 
        drop constraint FK_3q1l4nf4y60u6ilqk7pmonbiy;

    alter table DOMICILES 
        drop constraint FK_2gy35i0mncci56364vd69op5c;

    alter table DOMICILES 
        drop constraint FK_nhd6ntjhyisllxm0jex53pevd;

    alter table DOMICILES 
        drop constraint FK_fok8eur6jfgmqmqxff5plei6t;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop constraint FK_j225e57pfl86eyrp7wvxiq1as;

    alter table FILES_MOVEMENT 
        drop constraint FK_cw2njgv8n5u24jtfr87kjl3w0;

    alter table GENERAL_FILES 
        drop constraint FK_ghj29xc4dp5ssh9mgffyy8mfb;

    alter table LOCALITIES 
        drop constraint FK_pa2tub5ue9csmnhatpssde4vk;

    alter table LOCALITIES 
        drop constraint FK_sfgjb7ny137f1fp154ijlpwj9;

    alter table USERS_AUTHORITIES 
        drop constraint FK_5dan5n2o70hkqguh9dise46eh;

    alter table USERS_AUTHORITIES 
        drop constraint FK_sj95eivhw57gxc2uacv2ilrko;

    drop table ACCOUNTS_TYPES;

    drop table AFIP_ACTIVITIES;

    drop table AUTHORITIES;

    drop table BANKS;

    drop table BANK_ACCOUNTS;

    drop table CLIENTS;

    drop table COUNTRIES;

    drop table DISTRICTS;

    drop table DOCUMENTS_TYPES;

    drop table DOMICILES;

    drop table ECONOMIC_FINANCIAL_PROFILES;

    drop table FILES_MOVEMENT;

    drop table GENERAL_FILES;

    drop table LOCALITIES;

    drop table PROVINCES;

    drop table SOCIETIES_TYPE;

    drop table TAXPAYERS_TYPE;

    drop table TELEPHONES_TYPE;

    drop table USERS;

    drop table USERS_AUTHORITIES;

    create table ACCOUNTS_TYPES (
        ACCOUNTS_TYPE_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        primary key (ACCOUNTS_TYPE_ID)
    );

    create table AFIP_ACTIVITIES (
        AFIP_ACTIVITIES_ID numeric(19,0) identity not null,
        CODE varchar(3) not null,
        DESCRIPTION varchar(650) not null,
        PARENT varchar(3),
        primary key (AFIP_ACTIVITIES_ID)
    );

    create table AUTHORITIES (
        NAME varchar(50) not null,
        primary key (NAME)
    );

    create table BANKS (
        BANK_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        STATUS int not null,
        primary key (BANK_ID)
    );

    create table BANK_ACCOUNTS (
        BANK_ACCOUNT_ID numeric(19,0) identity not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        BRANCH_OFFICE varchar(50) not null,
        CBU varchar(14) not null,
        STATUS int not null,
        ACCOUNT_TYPE_ID numeric(19,0) not null,
        BANK_ID numeric(19,0) not null,
        CLIENT_ID numeric(19,0) not null,
        primary key (BANK_ACCOUNT_ID)
    );

    create table CLIENTS (
        CLIENT_ID numeric(19,0) identity not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        AFFIDAVIT_HOME bit,
        AFIP_ACTIVITY varchar(10) not null,
        AFIP_CONST bit,
        BIRTHDAY datetime not null,
        BUSINESS_NAME varchar(100) not null,
        CUIT_CUIL varchar(25) not null,
        DOCUMENT_NUMBER varchar(25),
        EMAIL varchar(50) not null,
        IS_INDIVIDUAL bit not null,
        LAST_NAMES varchar(100) not null,
        LUT bit not null,
        NAMES varchar(100) not null,
        NOSIS bit,
        PEP bit,
        PUBLIC_SERVICE bit,
        ROS bit not null,
        SOI bit not null,
        STATUS int not null,
        WORLDSYS bit,
        COUNTRY_ID numeric(19,0) not null,
        DOCUMENT_TYPE_ID numeric(19,0) not null,
        SOCIETY_TYPE_ID numeric(19,0) not null,
        TAXPAYER_TYPE_ID numeric(19,0) not null,
        primary key (CLIENT_ID)
    );

    create table COUNTRIES (
        COUNTRY_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        LOW_TAXATION int,
        primary key (COUNTRY_ID)
    );

    create table DISTRICTS (
        DISTRICT_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        PROVINCE_ID numeric(19,0) not null,
        primary key (DISTRICT_ID)
    );

    create table DOCUMENTS_TYPES (
        DOCUMENT_TYPE_ID numeric(19,0) not null,
        DESCRIPTION varchar(10) not null,
        STATUS int not null,
        primary key (DOCUMENT_TYPE_ID)
    );

    create table DOMICILES (
        DOMICILE_ID numeric(19,0) identity not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        DEPARTMENT varchar(4),
        FLOOR varchar(2),
        LEGAL_DOMICILE int not null,
        NUMBER int not null,
        POSTAL_CODE varchar(10) not null,
        REAL_DOMICILE int not null,
        STATUS int not null,
        STREET varchar(100) not null,
        CLIENT_ID numeric(19,0) not null,
        DISTRICT_ID numeric(19,0) not null,
        LOCALITY_ID numeric(19,0) not null,
        PROVINCE_ID numeric(19,0) not null,
        primary key (DOMICILE_ID)
    );

    create table ECONOMIC_FINANCIAL_PROFILES (
        BANK_ACCOUNT_ID numeric(19,0) identity not null,
        MARKET_BACKGROUND int not null,
        PATRIMONY int not null,
        REASON varchar(400),
        CLIENT_ID numeric(19,0) not null,
        primary key (BANK_ACCOUNT_ID, CLIENT_ID)
    );

    create table FILES_MOVEMENT (
        FILES_MOVEMENT_ID numeric(19,0) identity not null,
        APPLICANT varchar(50) not null,
        COMMENTS varchar(100),
        LOCATION varchar(50) not null,
        ORDER_DATE datetime not null,
        RETURN_DATE datetime,
        SHIPPING_DATE datetime not null,
        CLIENT_ID numeric(19,0) not null,
        primary key (FILES_MOVEMENT_ID, CLIENT_ID)
    );

    create table GENERAL_FILES (
        GENERAL_FILE_ID numeric(19,0) identity not null,
        [COLUMN] varchar(4) not null,
        ROW varchar(4) not null,
        STATUS varchar(50) not null,
        CLIENT_ID numeric(19,0) not null,
        primary key (GENERAL_FILE_ID, CLIENT_ID)
    );

    create table LOCALITIES (
        LOCALITY_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        DISTRICT_ID numeric(19,0) not null,
        PROVINCE_ID numeric(19,0) not null,
        primary key (LOCALITY_ID)
    );

    create table PROVINCES (
        PROVINCE_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        primary key (PROVINCE_ID)
    );

    create table SOCIETIES_TYPE (
        SOCIETY_TYPE_ID numeric(19,0) not null,
        DESCRIPTION varchar(100) not null,
        primary key (SOCIETY_TYPE_ID)
    );

    create table TAXPAYERS_TYPE (
        TAXPAYER_TYPE_ID numeric(19,0) not null,
        DESCRIPTION varchar(100) not null,
        primary key (TAXPAYER_TYPE_ID)
    );

    create table TELEPHONES_TYPE (
        TELEPHONE_TYPE_ID numeric(19,0) not null,
        DESCRIPTION varchar(50) not null,
        primary key (TELEPHONE_TYPE_ID)
    );

    create table USERS (
        USER_ID numeric(19,0) identity not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACCOUNT_NON_EXPIRED bit not null,
        ACCOUNT_NON_LOCKED bit not null,
        CREDENTIALS_NON_EXPIRED bit not null,
        DESCRIPTION varchar(255),
        EMAIL varchar(50) not null,
        ENABLED bit not null,
        FIRST_NAME varchar(50) not null,
        LAST_NAME varchar(50) not null,
        LOGIN varchar(50) not null,
        PASSWORD varchar(100) not null,
        primary key (USER_ID)
    );

    create table USERS_AUTHORITIES (
        USER_ID numeric(19,0) not null,
        AUTHORITY_NAME varchar(50) not null,
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
