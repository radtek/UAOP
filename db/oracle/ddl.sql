-- DROP Table UAOP_SYSUSER;
CREATE TABLE UAOP_SYSUSER
(
  ACCOUNT            VARCHAR(128) NOT NULL,   
  USER_NAME          VARCHAR(128)  NOT NULL,   
  PASS               VARCHAR(128) NOT NULL,
  USER_ID            VARCHAR(128),
  USER_TYPE          NUMERIC(3,0) NOT NULL,
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(ACCOUNT)
);

-- DROP Table UAOP_SYSUSER_ROLE;
CREATE TABLE UAOP_SYSUSER_ROLE
(
   ROLE_ID     VARCHAR(32) NOT NULL,
   ACCOUNT     VARCHAR(128) NOT NULL,
   PRIMARY KEY(ACCOUNT,ROLE_ID)
);


-- DROP Table UAOP_ROLE;
CREATE TABLE UAOP_ROLE
(
  ROLE_ID           VARCHAR(32) NOT NULL,
  ROLE_NAME         VARCHAR(128) NOT NULL,
  SUPER_ID          VARCHAR(32),
  TYPE              NUMERIC(3,0) NOT NULL,
  NOTE              VARCHAR(1024),
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(ROLE_ID)
);

-- DROP Table UAOP_ROLE_RESOURCE;
CREATE TABLE UAOP_ROLE_RESOURCE
(
   ROLE_ID           VARCHAR(32) NOT NULL,
   RESOURCE_ID       VARCHAR(128) NOT NULL,
   RESOURCE_TYPE     NUMERIC(3,0) NOT NULL,
   PRIVILEGE_VALUE   NUMERIC(20,0),
   SYSTEM_ID         VARCHAR(32) NOT NULL,
   PRIMARY KEY(ROLE_ID,RESOURCE_ID,SYSTEM_ID)
);

-- DROP Table UAOP_MENU_RESOURCE;
CREATE TABLE UAOP_MENU_RESOURCE
(
  SYSTEM_ID       VARCHAR(32) NOT NULL,
  RESOURCE_ID     VARCHAR(256) NOT NULL,
  RESOURCE_NAME   VARCHAR(512),
  SUPER_ID        VARCHAR(256),
  URL             VARCHAR(2048),
  ICON            VARCHAR(256),
  VISIBLE_STATE   NUMERIC(3,0), 
  ORDER_NUM       NUMERIC(5,0), 
  NOTE            VARCHAR(1024),
  PRIMARY KEY(RESOURCE_ID,SYSTEM_ID)
);


-- DROP Table UAOP_SERVICE_RESOURCE;
CREATE TABLE UAOP_SERVICE_RESOURCE
(
  SYSTEM_ID       VARCHAR(32) NOT NULL,
  RESOURCE_ID     VARCHAR(256) NOT NULL,
  SERVICE_NAME    VARCHAR(512),
  SUPER_ID        VARCHAR(256),
  URL             VARCHAR(2048),
  ICON            VARCHAR(256),
  VISIBLE_STATE   NUMERIC(3,0), 
  ORDER_NUM       NUMERIC(5,0), 
  INTERFACE       VARCHAR(256),
  NOTE            VARCHAR(1024),
  PRIMARY KEY(RESOURCE_ID,SYSTEM_ID)
);



-- DROP Table UAOP_USER_INFO;
CREATE TABLE UAOP_USER_INFO
(
  USER_ID            VARCHAR(128) NOT NULL,
  USER_NAME          VARCHAR(128)  NOT NULL,   
  SEX                NUMERIC(3,0)  NOT NULL,
  CID                VARCHAR(20)   NOT NULL,
  IS_POLICE          NUMERIC(3,0),
  POLICE_ID          VARCHAR(128)  NOT NULL,
  CONTACT            VARCHAR(256),
  AVATAR             VARCHAR(128),
  POST               VARCHAR(64),
  BIRTH              NUMERIC(20,0), 
  POLI               VARCHAR(64),
  PHONE              VARCHAR(32),
  FAX                VARCHAR(32),
  ADDRESS            VARCHAR(256),
  ZIPCODE            VARCHAR(32),
  PROVINCE           VARCHAR(64),
  CITY               VARCHAR(64),
  COUNTY             VARCHAR(64),
  EXT_STR1           VARCHAR(256),
  EXT_STR2           VARCHAR(256),
  EXT_STR3           VARCHAR(256),
  STATUS             NUMERIC(3,0) NOT NULL,
  CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
  CREATE_TIME        NUMERIC(20,0) NOT NULL, 
  LAST_MODIFY_TIME   NUMERIC(20,0), 
  LAST_MODIFY_ACCOUNT     VARCHAR(128),
  LAST_TERMINAL      VARCHAR(128),
  LAST_SYS           VARCHAR(32),
  PRIMARY KEY(USER_ID)
);


-- DROP Table UAOP_USER_ORGANIZATION;
CREATE TABLE UAOP_USER_ORGANIZATION
(
    USER_ID      VARCHAR(128) NOT NULL,
    ORG_ID       VARCHAR(128) NOT NULL,
    TYPE		 NUMERIC(3,0)  NOT NULL,
    ADMIN        NUMERIC(3,0),
    PRIMARY KEY(USER_ID,ORG_ID)
);

-- DROP Table UAOP_ORGANIZATION;
CREATE TABLE UAOP_ORGANIZATION
(
    ORG_ID       VARCHAR(128)  NOT NULL,
    ORG_CODE     VARCHAR(128)  NOT NULL,
    ORG_NAME     VARCHAR(128)  NOT NULL,
    SUPER_ID     VARCHAR(128),
  	SYSTEM_ID    VARCHAR(32) NOT NULL,
	TYPE     	 NUMERIC(10),
	NOTE         VARCHAR(512),
	PINYIN       VARCHAR(128),
	FIRST_LETTER      VARCHAR(64),
	STATUS             NUMERIC(3,0) NOT NULL,
    CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
    CREATE_TIME        NUMERIC(20,0) NOT NULL, 
    LAST_MODIFY_TIME   NUMERIC(20,0), 
    LAST_MODIFY_ACCOUNT     VARCHAR(128),
    LAST_TERMINAL      VARCHAR(128),
    LAST_SYS           VARCHAR(32),
    PRIMARY KEY(ORG_ID)
);


-- DROP Table UAOP_SYSPARAM;
CREATE TABLE UAOP_SYSPARAM
(
    SYSTEM_ID            VARCHAR(32) NOT NULL,
    PARAM_TYPE           VARCHAR(32)  NOT NULL,
    PARAM_NAME           VARCHAR(128)  NOT NULL,
  	PARAM_VALUE          VARCHAR(512),
	CREATE_USER          VARCHAR(128),
	CREATE_TIME          NUMERIC(20,0),
	LAST_MODIFY_TIME     NUMERIC(20,0),
	EXT_STR1             VARCHAR(128),
	EXT_STR2             VARCHAR(128),
	EXT_STR3             VARCHAR(128),
    PRIMARY KEY(SYSTEM_ID,PARAM_TYPE,PARAM_NAME)
);


-- Create table
create table UAOP_LOG_TOKEN
(
  TOKEN          VARCHAR2(32) not null,
  ACCOUNT        VARCHAR2(128) not null,
  SYS_CODE       VARCHAR2(32),
  TOKEN_TIME     VARCHAR2(20),
  RANDOM_VAL     VARCHAR2(10),
  EFFECTIVE_TIME NUMBER,
  INVALID_TIME   NUMBER,
  CREATE_DATE    DATE,
  USER_ID        VARCHAR2(128)
);
-- Add comments to the table 
comment on table UAOP_LOG_TOKEN
  is '口令日志表';
-- Add comments to the columns 
comment on column UAOP_LOG_TOKEN.TOKEN
  is '固定32位';
comment on column UAOP_LOG_TOKEN.ACCOUNT
  is '用户账号';
comment on column UAOP_LOG_TOKEN.SYS_CODE
  is '系统编码';
comment on column UAOP_LOG_TOKEN.TOKEN_TIME
  is '日期(yyyyMMddHHmmss)14位日期';
comment on column UAOP_LOG_TOKEN.RANDOM_VAL
  is '6位随机数';
comment on column UAOP_LOG_TOKEN.EFFECTIVE_TIME
  is '令牌生效时间(长整形)';
comment on column UAOP_LOG_TOKEN.INVALID_TIME
  is '令牌失效时间(长整形)';
comment on column UAOP_LOG_TOKEN.CREATE_DATE
  is '创建时间';
comment on column UAOP_LOG_TOKEN.USER_ID
  is '用户ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UAOP_LOG_TOKEN
  add constraint PK_UAOP_LOG_TOKEN primary key (TOKEN);


-- DROP Table UAOP_SYS_DICT;
create table UAOP_SYS_DICT
(
  ID              VARCHAR(32) not null,
  SYS_CODE        VARCHAR(32),
  DICT_LEVEL      VARCHAR(4) not null,
  DICT_KEY        VARCHAR(50) not null,
  PARENT_KEY      VARCHAR(50),
  ROOT_KEY        VARCHAR(50),
  DICT_VALUE1     VARCHAR(200) not null,
  DICT_VALUE2     VARCHAR(200),
  DICT_VALUE3     VARCHAR(200),
  LEAF_FLAG       VARCHAR(4),
  DOWNLOAD_FLAG   VARCHAR(4),
  READONLY_FLAG   VARCHAR(4) not null,
  DICT_SORT       NUMERIC(4,0),
  DICT_PY         VARCHAR(50),
  OPEN_FLAG       VARCHAR(4) not null,
  REMARK          VARCHAR(4000),
  CREATE_USER     VARCHAR(50) not null,
  CREATE_DATETIME NUMERIC(20,0) not null,
  UPDATE_USER     VARCHAR(50),
  UPDATE_DATETIME NUMERIC(20,0),
  PARENT_ROOT_KEY VARCHAR(50),
  TYPE            VARCHAR(2),
  PRIMARY KEY(ID)
);


-- DROP Table UAOP_LOG_RECORD;
CREATE TABLE UAOP_LOG_RECORD
(
    ID                   NUMERIC(20,0),
    SYSTEM_ID            VARCHAR(32) NOT NULL,
    ACCOUNT              VARCHAR(128) NOT NULL,   
    USER_NAME            VARCHAR(128),   
  	TERMINAL_ID          VARCHAR(128),
	MODULE_ID            VARCHAR(128),
	OPERATE_ID           VARCHAR(128),
	OPERATE_TIME         NUMERIC(20,0),
	OPERATE_RESULT       NUMERIC(3,0),
	NOTE                 VARCHAR(1024),
	PRIMARY KEY(ID)
);


-- 增加是否启用状态
ALTER TABLE UAOP_SYSUSER add ACTIVE_STATUS NUMERIC(3,0) DEFAULT 1;

-- 增加菜单类型
ALTER TABLE UAOP_MENU_RESOURCE add MENU_TYPE NUMERIC(3,0)  DEFAULT 0;

-- 角色增加系统标志
ALTER TABLE UAOP_ROLE add SYSTEM_ID  VARCHAR(32);
ALTER TABLE UAOP_ROLE add ACTIVE_STATUS NUMERIC(3,0) DEFAULT 1;

-- 菜单增加英文名称
ALTER TABLE UAOP_MENU_RESOURCE add RESOURCE_EN_NAME   VARCHAR(512);

-- 菜单类型默认值0
ALTER TABLE UAOP_MENU_RESOURCE modify MENU_TYPE NUMERIC(3,0)  DEFAULT 0;
-- 设置菜单类型默认值0
UPDATE UAOP_MENU_RESOURCE SET MENU_TYPE=0;

-- 删除UAOP_ROLE_RESOURCE的主键ID，使用联合主键
ALTER TABLE UAOP_ROLE_RESOURCE DROP PRIMARY KEY;
ALTER TABLE UAOP_ROLE_RESOURCE DROP COLUMN ID;
ALTER TABLE UAOP_ROLE_RESOURCE ADD PRIMARY KEY (ROLE_ID,RESOURCE_ID,SYSTEM_ID);


-- begin 按照一体化平台，扩充公共字段(20161128)
-- 组织机构
ALTER TABLE UAOP_ORGANIZATION add SHORTENED_NAME   VARCHAR(128);   --单位简称
ALTER TABLE UAOP_ORGANIZATION add ADDRESS          VARCHAR(512);   --地址
ALTER TABLE UAOP_ORGANIZATION add PHONE            VARCHAR(32);    --联系电话
ALTER TABLE UAOP_ORGANIZATION add ZIPCODE          VARCHAR(32);    --邮编
ALTER TABLE UAOP_ORGANIZATION add FAX              VARCHAR(32);    --传真

-- 用户基本信息
ALTER TABLE UAOP_USER_INFO add NATION                          VARCHAR(32);    --民族
ALTER TABLE UAOP_USER_INFO add NATIVE_PLACE                    VARCHAR(128);   --户籍地
ALTER TABLE UAOP_USER_INFO add MOBILE_PHONE                    VARCHAR(32);    --手机号
ALTER TABLE UAOP_USER_INFO add ADDPOLI_DATE                    NUMERIC(20,0);  --加入政治面貌时间
-- end 按照一体化平台，扩充公共字段(20161128)

-- 用户和账号表增加备注
ALTER TABLE UAOP_SYSUSER   add NOTE                            VARCHAR(512);    --备注
ALTER TABLE UAOP_USER_INFO add NOTE                            VARCHAR(512);    --备注

-- Create table
create table UAOP_DEVICE_REGIST
(
  ID                  VARCHAR2(32) not null,
  DEVICE_TYPE         VARCHAR2(20) not null,
  DEVICE_ID           VARCHAR2(64) not null,
  UNIT_CODE           VARCHAR2(12) not null,
  HISIGN_PN           VARCHAR2(100) not null,
  OPEN_FLAG           VARCHAR2(2) not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table UAOP_DEVICE_REGIST
  add constraint PK_UAOP_DEVICE_REGIST primary key (ID);

-- Create table
create table UAOP_VERSION_RELEASE
(
  ID                  VARCHAR2(32) not null,
  RESOURCE_ID      VARCHAR2(256) not null,
  PRODUCT_NAME        VARCHAR2(100) not null,
  PRODUCT             VARCHAR2(100) not null,
  VERSION_NUMBER      VARCHAR2(100) not null,
  VERSION_FEATURE     VARCHAR2(2000),
  FILE_NAME           VARCHAR2(100) not null,
  FILE_PATH           VARCHAR2(2000) not null,
  VALIDATE_STR        VARCHAR2(50) not null,
  OPEN_FLAG           VARCHAR2(10) default '0' not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Add comments to the columns 
comment on column UAOP_VERSION_RELEASE.ID
  is '主键';
comment on column UAOP_VERSION_RELEASE.RESOURCE_ID
  is 'UAOP_MENU_RESOURCE的RESOURCE_ID';
comment on column UAOP_VERSION_RELEASE.PRODUCT_NAME
  is '产品名称';
comment on column UAOP_VERSION_RELEASE.PRODUCT
  is '产品缩写';
comment on column UAOP_VERSION_RELEASE.VERSION_NUMBER
  is '版本号';
comment on column UAOP_VERSION_RELEASE.VERSION_FEATURE
  is '版本说明';
comment on column UAOP_VERSION_RELEASE.FILE_NAME
  is '程序文件名';
comment on column UAOP_VERSION_RELEASE.FILE_PATH
  is '程序安装包路径';
comment on column UAOP_VERSION_RELEASE.VALIDATE_STR
  is '程序md5';
comment on column UAOP_VERSION_RELEASE.OPEN_FLAG
  is '是否可用 1-可用 0-不可用';
-- Create/Recreate primary, unique and foreign key constraints 
alter table UAOP_VERSION_RELEASE
  add constraint PK_VERSION_RELEASE primary key (ID);

-- Create table
create table MPP_FEEDBACK
(
  ID                  VARCHAR2(32) not null,
  TYPE                VARCHAR2(20) not null,
  CONTENT             VARCHAR2(4000) not null,
  ATTACH1             VARCHAR2(60),
  ATTACH2             VARCHAR2(60),
  ATTACH3             VARCHAR2(60),
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MPP_FEEDBACK
  add constraint PK_MPP_FEEDBACK primary key (ID);

-- Create table
create table MPP_WALK_INFO
(
  ID                  VARCHAR2(32) not null,
  USER_ID             VARCHAR2(32) not null,
  STEP_COUNT          VARCHAR2(20) not null,
  DISTANCE            VARCHAR2(64) not null,
  CALORIE             VARCHAR2(12) not null,
  REPORT_TIME         NUMBER(20) not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MPP_WALK_INFO
  add constraint PK_MPP_WALK_INFO primary key (ID);
  
-- Create table
create table MPP_WALK_INFO_DAY
(
  ID                  VARCHAR2(32) not null,
  USER_ID             VARCHAR2(32) not null,
  STEP_COUNT          VARCHAR2(20) not null,
  DISTANCE            VARCHAR2(64) not null,
  CALORIE             VARCHAR2(12) not null,
  REPORT_TIME         NUMBER(20) not null,
  STATUS              NUMBER(3) not null,
  CREATE_ACCOUNT      VARCHAR2(128) not null,
  CREATE_TIME         NUMBER(20) not null,
  LAST_MODIFY_TIME    NUMBER(20),
  LAST_MODIFY_ACCOUNT VARCHAR2(128),
  LAST_TERMINAL       VARCHAR2(128),
  LAST_SYS            VARCHAR2(32)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MPP_WALK_INFO_DAY
  add constraint PK_MPP_WALK_INFO_DAY primary key (ID);

-- 增加菜单所属前台类型 0:web 1：移动app
ALTER TABLE UAOP_MENU_RESOURCE add APP_TYPE NUMERIC(3,0)  DEFAULT 0;
-- 设置菜单类型默认值0
UPDATE UAOP_MENU_RESOURCE SET APP_TYPE=0;


-- 用户基本信息
ALTER TABLE UAOP_USER_INFO add EMAIL      VARCHAR(128);    --邮箱

-- 用户账号信息 增加ACCOUNT_ID 为兼容现勘账户，唯一不能为空
ALTER TABLE UAOP_SYSUSER add ACCOUNT_ID      VARCHAR(128);    --账户Id
UPDATE UAOP_SYSUSER set ACCOUNT_ID = sys_guid();
ALTER TABLE UAOP_SYSUSER modify ACCOUNT_ID   VARCHAR(128) not null;
ALTER TABLE UAOP_SYSUSER add CONSTRAINT UNI_ACCOUNT_ID UNIQUE(ACCOUNT_ID);



-- DROP Table UAOP_COMPANY;
CREATE TABLE UAOP_COMPANY
(
    ID                 VARCHAR(128)  NOT NULL,
    COMPANY_NAME       VARCHAR(128)  NOT NULL,
    SUPER_ID           VARCHAR(128),
  	SYSTEM_ID          VARCHAR(32),
	NOTE               VARCHAR(512),
	PINYIN             VARCHAR(128),
	FIRST_LETTER       VARCHAR(64),
	STATUS             NUMERIC(3,0) NOT NULL,
    CREATE_ACCOUNT     VARCHAR(128) NOT NULL,
    CREATE_TIME        NUMERIC(20,0) NOT NULL, 
    LAST_MODIFY_TIME   NUMERIC(20,0), 
    LAST_MODIFY_ACCOUNT     VARCHAR(128),
    LAST_TERMINAL      VARCHAR(128),
    LAST_SYS           VARCHAR(32),
    PRIMARY KEY(ID)
);


-- 组织机构添加所属公司Id
ALTER TABLE UAOP_ORGANIZATION add COMPANY_ID      VARCHAR(128);    --公司ID

-- 设置菜单可见状态默认值为0
UPDATE UAOP_MENU_RESOURCE SET VISIBLE_STATE=0;

-- 增加角色英文名称
ALTER TABLE UAOP_ROLE add  ROLE_EN_NAME    VARCHAR(128); -- 角色英文名称

-- 修改组织机构SystemID可为空
ALTER TABLE UAOP_ORGANIZATION modify  SYSTEM_ID   NULL; 


-- 用户账号信息 增加所属机构类型
ALTER TABLE UAOP_SYSUSER add  UNIT_LEVEL  VARCHAR(32);    --所属机构类型
update UAOP_SYSUSER set UNIT_LEVEL = '0';
ALTER TABLE UAOP_SYSUSER modify  UNIT_LEVEL  NOT NULL;    --所属机构类型
 

-- 组织机构信息添加机构类型，机构级别，是否科室字段
ALTER TABLE UAOP_ORGANIZATION add  O_TYPE  VARCHAR(32);    --机构类型
update UAOP_ORGANIZATION set O_TYPE = '0';
ALTER TABLE UAOP_ORGANIZATION modify  O_TYPE  NOT NULL;    --机构类型

ALTER TABLE UAOP_ORGANIZATION add  O_LEVEL  VARCHAR(32);   --机构行政级别
ALTER TABLE UAOP_ORGANIZATION add  IS_LAB  VARCHAR(32);    --是否科室


-- 增加用户与业务系统关系表
-- DROP Table UAOP_USER_SYSTEM;
CREATE TABLE UAOP_USER_SYSTEM
(
    USER_ID            VARCHAR(128)  NOT NULL,
    SYSTEM_ID          VARCHAR(32)   NOT NULL,
    PRIMARY KEY(USER_ID,SYSTEM_ID)
);

-- Create table
create table MPP_APP_PARAM
(
  ID               VARCHAR2(32) not null,
  RESOURCE_ID      VARCHAR2(256) not null,
  ORG_CODE         VARCHAR2(128) not null,
  PARAM_NAME       VARCHAR2(128) not null,
  PARAM_VALUE      VARCHAR2(512),
  CREATE_USER      VARCHAR2(128),
  CREATE_TIME      NUMBER(20),
  LAST_MODIFY_USER VARCHAR2(128),
  LAST_MODIFY_TIME NUMBER(20),
  LAST_TERMINAL    VARCHAR2(128),
  LAST_SYS         VARCHAR2(32)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table MPP_APP_PARAM
  add constraint PK_MPP_APP_PARAM primary key (ID);

-- 用户基本信息
ALTER TABLE UAOP_USER_INFO modify SEX  NULL;    --修改性别可为空
ALTER TABLE UAOP_USER_INFO modify POLICE_ID  NULL;    --修改警员编号可为空

-- 通告通知管理
-- DROP Table UAOP_NOTICE;
create table UAOP_NOTICE
(
  ID               VARCHAR(32) NOT NULL,   --主键
  TITLE            VARCHAR(256) NOT NULL,  --标题
  CONTENT          BLOB,                   --内容
  SEND_STATUS      NUMERIC(3,0) NOT NULL,  --发送状态:0:未发送 1:已发送
  SENDER           VARCHAR(32),            --发送人
  SEND_TIME        NUMERIC(20,0),          --发送时间
  SEND_UNIT        VARCHAR(128),           --发送单位
  SEND_TYPE        NUMERIC(3,0) NOT NULL,  --发送类型:0:发送给单位 1:发送给个人
  RECIEVE_UNIT     VARCHAR(512),           --接受单位Id,多个单位之间以逗号分隔
  RECIEVE_USER     VARCHAR(512),           --接受人userId,多个之间以逗号分隔
  FEEDBACK_STATUS  NUMERIC(3,0) NOT NULL,  --反馈状态:0:未反馈 1:已反馈
  STATUS           NUMERIC(3,0) NOT NULL,  --删除状态:0:正常   1:已删除
  CREATE_USER      VARCHAR2(128),
  CREATE_TIME      NUMBER(20),
  LAST_MODIFY_USER VARCHAR2(128),
  LAST_MODIFY_TIME NUMBER(20),
  LAST_TERMINAL    VARCHAR2(128),
  LAST_SYS         VARCHAR2(32),
  PRIMARY KEY(ID)
);


-- 通告通知
-- DROP Table UAOP_NOTICE_RECIEVE;
create table UAOP_NOTICE_RECIEVE
(
  ID                 VARCHAR(32)  NOT NULL,   --主键
  NOTICE_ID          VARCHAR(32)  NOT NULL,   --通知Id
  FEEDBACK_TYPE      NUMERIC(3,0) NOT NULL,   --反馈类型:0:未处理 1:签收 2:反馈
  FEEDBACK_CONTENT   VARCHAR(4000),
  ATTACH_PATH        VARCHAR(256),            --附件url:0:多个附件之间以逗号分隔
  ACCEPTOR           VARCHAR(32),             --签收人
  ACCEPT_UNIT	     VARCHAR(128),            --签收单位
  ACCEPT_TIME        NUMERIC(20,0),            --签收时间
  FEEDBACKER         VARCHAR(32),             --反馈人
  FEEDBACK_UNIT	     VARCHAR(128),            --反馈单位
  FEEDBACK_TIME      NUMERIC(20,0),            --反馈时间
  PRIMARY KEY(ID)
);

-- 短信通知(短信发送接口处理成功即物理删除)
-- DROP Table UAOP_SMS_NOTIFY;
create table UAOP_SMS_NOTIFY
(
  ID               VARCHAR(32) NOT NULL,    --主键
  SMS_CONTENT      VARCHAR(1024) NOT NULL,  --短信内容
  RECIEVE_USER     VARCHAR(512),            --接收人手机号,多个之间以逗号分隔
  CREATE_USER      VARCHAR(128),            --创建人
  CREATE_TIME      NUMERIC(20,0),
  PRIMARY KEY(ID) 
);

-- 短信通知记录历史
-- DROP Table UAOP_SMS_NOTIFY_RECORD;
create table UAOP_SMS_NOTIFY_RECORD
(
  ID               VARCHAR(32) NOT NULL,    --主键
  NOTIFY_ID        VARCHAR(32) NOT NULL,    --关联UAOP_SMS_NOTIFY主键
  SMS_CONTENT      VARCHAR(1024) NOT NULL,  --短信内容
  RECIEVE_USER     VARCHAR(512),            --接收人手机号,多个之间以逗号分隔
  SENDER           VARCHAR(32),             --发送人
  SEND_TIME        NUMERIC(20,0),           --发送时间
  SEND_STATUS      NUMERIC(3,0) NOT NULL,   --0未处理,1处理成功,2处理失败
  RESULT           VARCHAR(128),            --处理结果中文描述
  CREATE_USER      VARCHAR(128),            --创建人
  CREATE_TIME      NUMERIC(20,0),
  PRIMARY KEY(ID) 
);