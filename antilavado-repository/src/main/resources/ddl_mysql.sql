
    alter table ALERT_MANAGEMENT 
        drop 
        foreign key FK_t12gsh1j11qd6qdhtnn2wixi3;

    alter table ALERT_MANAGEMENT 
        drop 
        foreign key FK_ssea8k94acbanq6betqy9epv9;

    alter table ALERT_MANAGEMENT 
        drop 
        foreign key FK_b4095dy0q9eak93uvkiwbsbv2;

    alter table BANK_ACCOUNTS 
        drop 
        foreign key FK_921fc3vjcj0xcjxm0xho79r1h;

    alter table BANK_ACCOUNTS 
        drop 
        foreign key FK_c6kqefxpenwu4u5aae5wkaf8p;

    alter table BANK_ACCOUNTS 
        drop 
        foreign key FK_406ovq2g8gu2o25juw29ma4hm;

    alter table CLIENTS 
        drop 
        foreign key FK_h323ko88kwrgt4ah3gs3iv36m;

    alter table CLIENTS 
        drop 
        foreign key FK_ia9u7hi0drq1fkif6uy7un35a;

    alter table CLIENTS 
        drop 
        foreign key FK_67ex4ohbxjtv01qr2ei6c1jmo;

    alter table CLIENTS 
        drop 
        foreign key FK_k97k58l0chha10uqns1ybxr1b;

    alter table CLIENTS 
        drop 
        foreign key FK_td2ddtuf329wd51u2p79vvwx4;

    alter table CLIENTS 
        drop 
        foreign key FK_papqlytbmxu0302gqa5mtd7oc;

    alter table CLIENTS 
        drop 
        foreign key FK_s1o9ee9fllkhu770sq8x5xqe9;

    alter table CLIENTS 
        drop 
        foreign key FK_a6fxpblandvmgvke49e9p483c;

    alter table CLIENTS 
        drop 
        foreign key FK_16bttfc99fpuqoc70aqlnm248;

    alter table CLIENTS 
        drop 
        foreign key FK_7ph64ak12r0ep4l0q5tv0w8l;

    alter table CLIENTS 
        drop 
        foreign key FK_n86k08iwo7affiju1c10u6sd0;

    alter table CLIENT_TRANSACTIONS 
        drop 
        foreign key FK_t25roxoxrg8h524x06elvckjt;

    alter table DETAIL_RISK_FACTOR 
        drop 
        foreign key FK_b1bujl4f0bsss3rywf30osuu7;

    alter table DETAIL_RISK_FACTOR 
        drop 
        foreign key FK_6uaqje9y2wwxuios1vv8pc9ua;

    alter table DISTRICTS 
        drop 
        foreign key FK_i1nt8mbsp9pm405lwu87xmkm8;

    alter table DOMICILES 
        drop 
        foreign key FK_3q1l4nf4y60u6ilqk7pmonbiy;

    alter table DOMICILES 
        drop 
        foreign key FK_5tyi1yk67t1qxieungecjkq33;

    alter table DOMICILES 
        drop 
        foreign key FK_2gy35i0mncci56364vd69op5c;

    alter table DOMICILES 
        drop 
        foreign key FK_nhd6ntjhyisllxm0jex53pevd;

    alter table DOMICILES 
        drop 
        foreign key FK_fok8eur6jfgmqmqxff5plei6t;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_rcrhi25ufnn0i9n8h9aobmavg;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_sr7rkfm79kyu5kokyhfnlbgvp;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_dipoktdow9bdljekobigny8fa;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_cjujnsijsi2ryrqheae5lb54l;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_d32lhxf46tqrj3yegrgq4o98c;

    alter table ECONOMIC_FINANCIAL_PROFILES 
        drop 
        foreign key FK_3dna5wrpy468o1uhj7o3ev640;

    alter table FILES_MOVEMENT 
        drop 
        foreign key FK_cw2njgv8n5u24jtfr87kjl3w0;

    alter table GENERAL_FILES 
        drop 
        foreign key FK_ghj29xc4dp5ssh9mgffyy8mfb;

    alter table GENERAL_FILES 
        drop 
        foreign key FK_d3y3ilpwuf1get5w6xa3mos6v;

    alter table LOCALITIES 
        drop 
        foreign key FK_pa2tub5ue9csmnhatpssde4vk;

    alter table LOCALITIES 
        drop 
        foreign key FK_sfgjb7ny137f1fp154ijlpwj9;

    alter table MATRIX_DETAIL 
        drop 
        foreign key FK_302o777s37gywdpl148hu3gf2;

    alter table MATRIX_DETAIL 
        drop 
        foreign key FK_klqncoc4q3v73esmokkg60n3p;

    alter table MATRIX_ERROR_LOGS 
        drop 
        foreign key FK_9rb1pv1vcofeu5yn8p7r3y7xx;

    alter table MATRIX_MASTER 
        drop 
        foreign key FK_6njos3po6uolnqvas5yab0c3j;

    alter table MATRIX_MASTER 
        drop 
        foreign key FK_joevh3pxjkx3d0my2eo9l6f9u;

    alter table PROVINCES 
        drop 
        foreign key FK_h88shusse6si87km4n2nt990k;

    alter table STATUS_TYPE 
        drop 
        foreign key FK_sww9ntw7081gbj3cwcqucbpgi;

    alter table TELEPHONES 
        drop 
        foreign key FK_tqr1tsyjlajuokddre71pkowv;

    alter table TELEPHONES 
        drop 
        foreign key FK_165kyhnfko6e3smi2fs4yxsgk;

    alter table USERS_AUTHORITIES 
        drop 
        foreign key FK_5dan5n2o70hkqguh9dise46eh;

    alter table USERS_AUTHORITIES 
        drop 
        foreign key FK_sj95eivhw57gxc2uacv2ilrko;

    alter table VALUES_TYPE 
        drop 
        foreign key FK_7h2s7b3i09kggyok28ghthhko;

    drop table if exists ACCOUNTS_TYPES;

    drop table if exists AFIP_ACTIVITIES;

    drop table if exists ALERT_MANAGEMENT;

    drop table if exists AUTHORITIES;

    drop table if exists BANKS;

    drop table if exists BANK_ACCOUNTS;

    drop table if exists CLIENTS;

    drop table if exists CLIENT_TRANSACTIONS;

    drop table if exists COUNTRIES;

    drop table if exists DETAIL_RISK_FACTOR;

    drop table if exists DISTRICTS;

    drop table if exists DOCUMENTS_TYPES;

    drop table if exists DOMICILES;

    drop table if exists ECONOMIC_FINANCIAL_PROFILES;

    drop table if exists FACTOR_LEVELS;

    drop table if exists FILES_MOVEMENT;

    drop table if exists GENERAL_FILES;

    drop table if exists LICENSE;

    drop table if exists LOCALITIES;

    drop table if exists MASTER_RISK_FACTOR;

    drop table if exists MATRIX_DETAIL;

    drop table if exists MATRIX_ERROR_LOGS;

    drop table if exists MATRIX_MASTER;

    drop table if exists PROVINCES;

    drop table if exists STATUS_TYPE;

    drop table if exists TELEPHONES;

    drop table if exists TELEPHONES_TYPE;

    drop table if exists USERS;

    drop table if exists USERS_AUTHORITIES;

    drop table if exists VALUES_TYPE;

    drop table if exists VALUES_TYPE_EXT;

    create table ACCOUNTS_TYPES (
        ACCOUNTS_TYPE_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        primary key (ACCOUNTS_TYPE_ID)
    );

    create table AFIP_ACTIVITIES (
        AFIP_ACTIVITIES_ID bigint not null auto_increment,
        CODE varchar(3) not null,
        DESCRIPTION longtext not null,
        PARENT varchar(3),
        primary key (AFIP_ACTIVITIES_ID)
    );

    create table ALERT_MANAGEMENT (
        ALERT_MANAGEMENT_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        DIFFERENCE decimal(19,2) not null,
        MONITORING longtext,
        PERIOD_AMOUNT decimal(19,2) not null,
        PERIOD_ANALYZED datetime,
        PERIOD_DESCRIPTION varchar(100),
        UIF_REPORT datetime,
        ASSIGNED_USER_ID bigint,
        CLIENT_ID bigint not null,
        UNUSUAL_OPERATION_STATUS_TYPE_ID bigint,
        primary key (ALERT_MANAGEMENT_ID)
    );

    create table AUTHORITIES (
        NAME varchar(50) not null,
        primary key (NAME)
    );

    create table BANKS (
        BANK_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        STATUS integer not null,
        primary key (BANK_ID)
    );

    create table BANK_ACCOUNTS (
        BANK_ACCOUNT_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACCOUNT_NUMBER varchar(14) not null,
        ACTIVE bit not null,
        BRANCH_OFFICE varchar(50) not null,
        CBU varchar(14) not null,
        ACCOUNT_TYPE_ID bigint not null,
        BANK_ID bigint not null,
        CLIENT_ID bigint not null,
        primary key (BANK_ACCOUNT_ID)
    );

    create table CLIENTS (
        CLIENT_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        AFFIDAVIT_HOME bit,
        AFIP_ACTIVITY varchar(10) not null,
        AFIP_CONST bit,
        BIRTHDAY datetime not null,
        BUSINESS_NAME varchar(100),
        CUIT_CUIL varchar(25) not null,
        DOCUMENT_NUMBER varchar(25),
        EMAIL varchar(50),
        IS_INDIVIDUAL bit not null,
        LAST_NAMES varchar(100),
        NAMES varchar(100),
        NOSIS bit,
        PUBLIC_SERVICE bit,
        STATUS integer,
        WORLDSYS bit,
        COUNTRY_ID bigint,
        DOCUMENT_TYPE_ID bigint,
        ECONOMIC_FINANCIAL_PROFILE_ID bigint not null,
        LUT_VALUES_TYPE_ID bigint,
        MARKET_EXPERIENCE_TYPE_VALUES_TYPE_ID bigint,
        OPERATION_FREQUENCY_TYPE_VALUES_TYPE_ID bigint,
        PEP_VALUES_TYPE_ID bigint,
        ROS_VALUES_TYPE_ID bigint,
        SOCIETY_TYPE_VALUES_TYPE_ID bigint,
        SOI_VALUES_TYPE_ID bigint,
        SPECIAL_CLIENT_VALUES_TYPE_ID bigint,
        primary key (CLIENT_ID)
    );

    create table CLIENT_TRANSACTIONS (
        INVOICE_DATE datetime not null,
        INVOICE_NUMBER varchar(255) not null,
        FILE_NAME varchar(50),
        IMPORT_DATE datetime not null,
        INVOICE_AMOUNT decimal(19,2) not null,
        CLIENT_ID bigint not null,
        primary key (CLIENT_ID, INVOICE_DATE, INVOICE_NUMBER)
    );

    create table COUNTRIES (
        COUNTRY_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        TAXATION integer,
        primary key (COUNTRY_ID)
    );

    create table DETAIL_RISK_FACTOR (
        DETAIL_RISK_FACTOR_ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACTIVE bit not null,
        DESCRIPTION varchar(150) not null,
        PONDERATION integer not null,
        MASTER_RISK_FACTOR_ID bigint not null,
        VALUES_TYPE_ID bigint,
        primary key (DETAIL_RISK_FACTOR_ID)
    );

    create table DISTRICTS (
        DISTRICT_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        PROVINCE_ID bigint not null,
        primary key (DISTRICT_ID)
    );

    create table DOCUMENTS_TYPES (
        DOCUMENT_TYPE_ID bigint not null,
        DESCRIPTION varchar(10) not null,
        STATUS integer not null,
        primary key (DOCUMENT_TYPE_ID)
    );

    create table DOMICILES (
        DOMICILE_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACTIVE bit,
        DEPARTMENT varchar(4),
        FLOOR varchar(2),
        LEGAL_DOMICILE bit,
        NUMBER integer not null,
        POSTAL_CODE varchar(10),
        REAL_DOMICILE bit,
        STREET varchar(100) not null,
        CLIENT_ID bigint not null,
        COUNTRY_ID bigint not null,
        DISTRICT_ID bigint not null,
        LOCALITY_ID bigint not null,
        PROVINCE_ID bigint not null,
        primary key (DOMICILE_ID)
    );

    create table ECONOMIC_FINANCIAL_PROFILES (
        ECONOMIC_FINANCIAL_PROFILE_ID bigint not null auto_increment,
        MONTHLY_ESTIMATE decimal(19,2) not null,
        FISCAL_SITUATION bigint,
        MONOTRIBUTO_CATEGORY bigint,
        MONTHLY_INCOME_AS_DDJJ bigint,
        MONTHLY_INCOME_AS_DDJJ2 bigint,
        MONTHLY_INCOME_AS_EECC bigint,
        MONTHLY_INCOME_DECLARED bigint,
        primary key (ECONOMIC_FINANCIAL_PROFILE_ID)
    );

    create table FACTOR_LEVELS (
        FACTOR_LEVELS_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACTIVE bit not null,
        COLOR varchar(10) not null,
        DESCRIPTION varchar(150) not null,
        LOWER_BOUND decimal(12,2) not null,
        PERCENTAGE integer not null,
        UPPER_BOUND decimal(12,2) not null,
        primary key (FACTOR_LEVELS_ID)
    );

    create table FILES_MOVEMENT (
        FILES_MOVEMENT_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        APPLICANT varchar(50) not null,
        COMMENTS varchar(100),
        LOCATION varchar(50) not null,
        ORDER_DATE datetime not null,
        RETURN_DATE datetime,
        SHIPPING_DATE datetime not null,
        CLIENT_ID bigint not null,
        primary key (FILES_MOVEMENT_ID)
    );

    create table GENERAL_FILES (
        GENERAL_FILE_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        `COLUMN` varchar(4) not null,
        ROW varchar(4) not null,
        CLIENT_ID bigint not null,
        STATUS bigint,
        primary key (GENERAL_FILE_ID)
    );

    create table LICENSE (
        LICENSE_ID bigint not null auto_increment,
        CREATED_DATE datetime not null,
        LICENSE_VALUE longtext not null,
        primary key (LICENSE_ID)
    );

    create table LOCALITIES (
        LOCALITY_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        DISTRICT_ID bigint not null,
        PROVINCE_ID bigint not null,
        primary key (LOCALITY_ID)
    );

    create table MASTER_RISK_FACTOR (
        MASTER_RISK_FACTOR_ID bigint not null,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ACTIVE bit not null,
        DESCRIPTION varchar(150) not null,
        PONDERATION integer not null,
        primary key (MASTER_RISK_FACTOR_ID)
    );

    create table MATRIX_DETAIL (
        MATRIX_DETAIL_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        DETAIL_RISK_FACTOR bigint not null,
        MATRIX_MASTER_ID bigint not null,
        primary key (MATRIX_DETAIL_ID)
    );

    create table MATRIX_ERROR_LOGS (
        MATRIX_ERROR_LOGS_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        ERROR_DATE datetime,
        MSG_ERROR longtext not null,
        CLIENT_ID bigint not null,
        primary key (MATRIX_ERROR_LOGS_ID)
    );

    create table MATRIX_MASTER (
        MATRIX_MASTER_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        TOTAL_PONDERATION integer not null,
        CLIENT_ID bigint not null,
        FACTOR_LEVEL_ID bigint not null,
        primary key (MATRIX_MASTER_ID)
    );

    create table PROVINCES (
        PROVINCE_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        COUNTRY_ID bigint not null,
        primary key (PROVINCE_ID)
    );

    create table STATUS_TYPE (
        STATUS_TYPE_ID bigint not null,
        DESCRIPTION varchar(100) not null,
        PARENT_ID bigint,
        primary key (STATUS_TYPE_ID)
    );

    create table TELEPHONES (
        TELEPHONE_ID bigint not null auto_increment,
        CREATED_BY varchar(255) not null,
        CREATED_DATE datetime not null,
        LAST_MODIFIED_BY varchar(255),
        LAST_MODIFIED_DATE datetime,
        NUMBER varchar(15) not null,
        CLIENT_ID bigint not null,
        TELEPHONE_TYPE_ID bigint not null,
        primary key (TELEPHONE_ID)
    );

    create table TELEPHONES_TYPE (
        TELEPHONE_TYPE_ID bigint not null,
        DESCRIPTION varchar(50) not null,
        primary key (TELEPHONE_TYPE_ID)
    );

    create table USERS (
        USER_ID bigint not null auto_increment,
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
        USER_ID bigint not null,
        AUTHORITY_NAME varchar(50) not null,
        primary key (USER_ID, AUTHORITY_NAME)
    );

    create table VALUES_TYPE (
        VALUES_TYPE_ID bigint not null,
        DESCRIPTION varchar(100) not null,
        PARENT_ID bigint,
        primary key (VALUES_TYPE_ID)
    );

    create table VALUES_TYPE_EXT (
        VALUES_TYPE_EXT_ID bigint not null,
        VALUE_FROM integer not null,
        VALUE_TO integer not null,
        primary key (VALUES_TYPE_EXT_ID)
    );

    alter table ACCOUNTS_TYPES 
        add constraint UK_ql1v7rdpd9s6wubojyihbogmu  unique (DESCRIPTION);

    create index AFIP_ACTIVITIES_CODE_ID_INDEX on AFIP_ACTIVITIES (CODE);

    create index AFIP_ACTIVITIES_PARENT_ID_INDEX on AFIP_ACTIVITIES (PARENT);

    alter table BANKS 
        add constraint UK_tkmanudhn143nsnv6w6af9jv7  unique (DESCRIPTION);

    alter table COUNTRIES 
        add constraint UK_4trhe8u4qab9vedrigtawgd4l  unique (DESCRIPTION);

    create index DETAIL_RISK_FACTOR_DESCRIPTION on DETAIL_RISK_FACTOR (DESCRIPTION);

    alter table DOCUMENTS_TYPES 
        add constraint UK_82past65bpakem46yucb3au1c  unique (DESCRIPTION);

    alter table FACTOR_LEVELS 
        add constraint UK_1f84ul5t3427o3ko8151t5qdl  unique (COLOR);

    alter table FACTOR_LEVELS 
        add constraint UK_8thjyilp01a6nxx87tf43u7rw  unique (DESCRIPTION);

    create index FILES_MOVEMENT_CLIENT_ID_INDEX on FILES_MOVEMENT (CLIENT_ID);

    create index GENERAL_FILES_CLIENT_ID_INDEX on GENERAL_FILES (CLIENT_ID);

    create index MATRIX_ERROR_LOGS_CLIENT_ID_INDEX on MATRIX_ERROR_LOGS (CLIENT_ID);

    create index MATRIX_MASTER_CLIENT_ID_INDEX on MATRIX_MASTER (CLIENT_ID);

    alter table STATUS_TYPE 
        add constraint UK_3bmq76rx5y31h9sv5hr606sqx  unique (DESCRIPTION);

    alter table TELEPHONES_TYPE 
        add constraint UK_bu7ac0ovlaljfs5xbmb8wq742  unique (DESCRIPTION);

    alter table USERS 
        add constraint UK_l3c3ahdulnjx8bt2ivgyvh1ss  unique (LOGIN);

    alter table VALUES_TYPE 
        add constraint UK_iyj1evyqjhu8nrvljuqhuyapy  unique (DESCRIPTION);

    alter table ALERT_MANAGEMENT 
        add constraint FK_t12gsh1j11qd6qdhtnn2wixi3 
        foreign key (ASSIGNED_USER_ID) 
        references USERS (USER_ID);

    alter table ALERT_MANAGEMENT 
        add constraint FK_ssea8k94acbanq6betqy9epv9 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table ALERT_MANAGEMENT 
        add constraint FK_b4095dy0q9eak93uvkiwbsbv2 
        foreign key (UNUSUAL_OPERATION_STATUS_TYPE_ID) 
        references STATUS_TYPE (STATUS_TYPE_ID);

    alter table BANK_ACCOUNTS 
        add constraint FK_921fc3vjcj0xcjxm0xho79r1h 
        foreign key (ACCOUNT_TYPE_ID) 
        references ACCOUNTS_TYPES (ACCOUNTS_TYPE_ID);

    alter table BANK_ACCOUNTS 
        add constraint FK_c6kqefxpenwu4u5aae5wkaf8p 
        foreign key (BANK_ID) 
        references BANKS (BANK_ID);

    alter table BANK_ACCOUNTS 
        add constraint FK_406ovq2g8gu2o25juw29ma4hm 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table CLIENTS 
        add constraint FK_h323ko88kwrgt4ah3gs3iv36m 
        foreign key (COUNTRY_ID) 
        references COUNTRIES (COUNTRY_ID);

    alter table CLIENTS 
        add constraint FK_ia9u7hi0drq1fkif6uy7un35a 
        foreign key (DOCUMENT_TYPE_ID) 
        references DOCUMENTS_TYPES (DOCUMENT_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_67ex4ohbxjtv01qr2ei6c1jmo 
        foreign key (ECONOMIC_FINANCIAL_PROFILE_ID) 
        references ECONOMIC_FINANCIAL_PROFILES (ECONOMIC_FINANCIAL_PROFILE_ID);

    alter table CLIENTS 
        add constraint FK_k97k58l0chha10uqns1ybxr1b 
        foreign key (LUT_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_td2ddtuf329wd51u2p79vvwx4 
        foreign key (MARKET_EXPERIENCE_TYPE_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_papqlytbmxu0302gqa5mtd7oc 
        foreign key (OPERATION_FREQUENCY_TYPE_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_s1o9ee9fllkhu770sq8x5xqe9 
        foreign key (PEP_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_a6fxpblandvmgvke49e9p483c 
        foreign key (ROS_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_16bttfc99fpuqoc70aqlnm248 
        foreign key (SOCIETY_TYPE_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_7ph64ak12r0ep4l0q5tv0w8l 
        foreign key (SOI_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENTS 
        add constraint FK_n86k08iwo7affiju1c10u6sd0 
        foreign key (SPECIAL_CLIENT_VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table CLIENT_TRANSACTIONS 
        add constraint FK_t25roxoxrg8h524x06elvckjt 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table DETAIL_RISK_FACTOR 
        add constraint FK_b1bujl4f0bsss3rywf30osuu7 
        foreign key (MASTER_RISK_FACTOR_ID) 
        references MASTER_RISK_FACTOR (MASTER_RISK_FACTOR_ID);

    alter table DETAIL_RISK_FACTOR 
        add constraint FK_6uaqje9y2wwxuios1vv8pc9ua 
        foreign key (VALUES_TYPE_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table DISTRICTS 
        add constraint FK_i1nt8mbsp9pm405lwu87xmkm8 
        foreign key (PROVINCE_ID) 
        references PROVINCES (PROVINCE_ID);

    alter table DOMICILES 
        add constraint FK_3q1l4nf4y60u6ilqk7pmonbiy 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table DOMICILES 
        add constraint FK_5tyi1yk67t1qxieungecjkq33 
        foreign key (COUNTRY_ID) 
        references COUNTRIES (COUNTRY_ID);

    alter table DOMICILES 
        add constraint FK_2gy35i0mncci56364vd69op5c 
        foreign key (DISTRICT_ID) 
        references DISTRICTS (DISTRICT_ID);

    alter table DOMICILES 
        add constraint FK_nhd6ntjhyisllxm0jex53pevd 
        foreign key (LOCALITY_ID) 
        references LOCALITIES (LOCALITY_ID);

    alter table DOMICILES 
        add constraint FK_fok8eur6jfgmqmqxff5plei6t 
        foreign key (PROVINCE_ID) 
        references PROVINCES (PROVINCE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_rcrhi25ufnn0i9n8h9aobmavg 
        foreign key (FISCAL_SITUATION) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_sr7rkfm79kyu5kokyhfnlbgvp 
        foreign key (MONOTRIBUTO_CATEGORY) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_dipoktdow9bdljekobigny8fa 
        foreign key (MONTHLY_INCOME_AS_DDJJ) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_cjujnsijsi2ryrqheae5lb54l 
        foreign key (MONTHLY_INCOME_AS_DDJJ2) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_d32lhxf46tqrj3yegrgq4o98c 
        foreign key (MONTHLY_INCOME_AS_EECC) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table ECONOMIC_FINANCIAL_PROFILES 
        add constraint FK_3dna5wrpy468o1uhj7o3ev640 
        foreign key (MONTHLY_INCOME_DECLARED) 
        references VALUES_TYPE (VALUES_TYPE_ID);

    alter table FILES_MOVEMENT 
        add constraint FK_cw2njgv8n5u24jtfr87kjl3w0 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table GENERAL_FILES 
        add constraint FK_ghj29xc4dp5ssh9mgffyy8mfb 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table GENERAL_FILES 
        add constraint FK_d3y3ilpwuf1get5w6xa3mos6v 
        foreign key (STATUS) 
        references STATUS_TYPE (STATUS_TYPE_ID);

    alter table LOCALITIES 
        add constraint FK_pa2tub5ue9csmnhatpssde4vk 
        foreign key (DISTRICT_ID) 
        references DISTRICTS (DISTRICT_ID);

    alter table LOCALITIES 
        add constraint FK_sfgjb7ny137f1fp154ijlpwj9 
        foreign key (PROVINCE_ID) 
        references PROVINCES (PROVINCE_ID);

    alter table MATRIX_DETAIL 
        add constraint FK_302o777s37gywdpl148hu3gf2 
        foreign key (DETAIL_RISK_FACTOR) 
        references DETAIL_RISK_FACTOR (DETAIL_RISK_FACTOR_ID);

    alter table MATRIX_DETAIL 
        add constraint FK_klqncoc4q3v73esmokkg60n3p 
        foreign key (MATRIX_MASTER_ID) 
        references MATRIX_MASTER (MATRIX_MASTER_ID);

    alter table MATRIX_ERROR_LOGS 
        add constraint FK_9rb1pv1vcofeu5yn8p7r3y7xx 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table MATRIX_MASTER 
        add constraint FK_6njos3po6uolnqvas5yab0c3j 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table MATRIX_MASTER 
        add constraint FK_joevh3pxjkx3d0my2eo9l6f9u 
        foreign key (FACTOR_LEVEL_ID) 
        references FACTOR_LEVELS (FACTOR_LEVELS_ID);

    alter table PROVINCES 
        add constraint FK_h88shusse6si87km4n2nt990k 
        foreign key (COUNTRY_ID) 
        references COUNTRIES (COUNTRY_ID);

    alter table STATUS_TYPE 
        add constraint FK_sww9ntw7081gbj3cwcqucbpgi 
        foreign key (PARENT_ID) 
        references STATUS_TYPE (STATUS_TYPE_ID);

    alter table TELEPHONES 
        add constraint FK_tqr1tsyjlajuokddre71pkowv 
        foreign key (CLIENT_ID) 
        references CLIENTS (CLIENT_ID);

    alter table TELEPHONES 
        add constraint FK_165kyhnfko6e3smi2fs4yxsgk 
        foreign key (TELEPHONE_TYPE_ID) 
        references TELEPHONES_TYPE (TELEPHONE_TYPE_ID);

    alter table USERS_AUTHORITIES 
        add constraint FK_5dan5n2o70hkqguh9dise46eh 
        foreign key (AUTHORITY_NAME) 
        references AUTHORITIES (NAME);

    alter table USERS_AUTHORITIES 
        add constraint FK_sj95eivhw57gxc2uacv2ilrko 
        foreign key (USER_ID) 
        references USERS (USER_ID);

    alter table VALUES_TYPE 
        add constraint FK_7h2s7b3i09kggyok28ghthhko 
        foreign key (PARENT_ID) 
        references VALUES_TYPE (VALUES_TYPE_ID);
