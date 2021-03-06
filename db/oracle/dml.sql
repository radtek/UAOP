

-- uaop rest端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REST', 'rest_port', '58811', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- uaop rmi端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REST', 'rmi_port', '58812', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- zookeeper地址
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'ZOOKEEPER', 'zkconnect', '127.0.0.1:52181', 'UAOP', 1467079528845, 1478141177709, null, null, null);
-- uaop rest log端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REST', 'rest_log_port', '58816', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- uaop rmi log端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'RMI', 'rmi_log_port', '58817', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- 文件服务器地址
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'FILE_SERVER', 'fileServerAddr', 'http://172.16.0.96:3000', 'UAOP', 1466993719222, 1466993719222, null, null, null);
-- 消息服务器类型
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'MQCLIENT', 'msgservertype', 'kafka', 'UAOP', 1467077359794, 1478141177712, null, null, null);
-- 消息服务器
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'MQCLIENT', 'kafka_brokerlist', '172.16.0.114:59092', 'UAOP', 1467077359794, 1478141177714, null, null, null);
-- 消息服务器地址
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'MQCLIENT', 'kafka_zkroot', 'kafka', 'UAOP', 1467077359794, 1478141177717, null, null, null);
-- redis配置
delete from uaop_sysparam where SYSTEM_ID='UAOP' and PARAM_TYPE='REDIS';
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_hostName', '172.16.0.113', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_port', '56379', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_password', 'uaop', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_dbIndex', '0', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_timeout', '2000', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_pool_maxTotal', '3000', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_pool_maxIdle', '3000', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_pool_maxWaitMillis', '1000', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_pool_testOnBorrow', 'false', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'REDIS', 'redis_pool_testOnReturn', 'false', 'UAOP', 1466993719222, 1466994131586, '', '', '');
-- Token超时间隔配置
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'TOKEN', 'logtoken_timeout', '7200', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'TOKEN', 'system_logtoken_timeout', '5400', 'UAOP', 1466993719222, 1466994131586, '', '', '');

-- OMP 配置参数
-- OMP rest端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('OMP', 'REST', 'omp_rest_port', '58815', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- OMP rmi端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('OMP', 'REST', 'omp_rmi_port', '58816', 'UAOP', 1466993719222, 1466994131586, null, null, null);

-- ALIMS 配置参数
-- alims rest端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('ALIMS', 'REST', 'alims_rest_port', '58821', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- alims rmi端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('ALIMS', 'REST', 'alims_rmi_port', '58822', 'UAOP', 1466993719222, 1466994131586, null, null, null);


-- XCKY 配置参数
-- XCKY rest端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('XCKY', 'REST', 'xcky_rest_port', '58831', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- XCKY rmi端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('XCKY', 'REST', 'xcky_rmi_port', '58832', 'UAOP', 1466993719222, 1466994131586, null, null, null);


-- MPP 配置参数
-- mpp rest端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('MPP', 'REST', 'mpp_rest_port', '59911', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- mpp rmi端口
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('MPP', 'REST', 'mpp_rmi_port', '59912', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- mpp 授权管理中心地址
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('MPP', 'ADDR', 'authorizeUrl', 'http://172.16.0.108:9080/', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- mpp 极光appKey
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('MPP', 'ADDR', 'jiguang.appKey', 'fc5c2eb50194914f96e61d16', 'UAOP', 1466993719222, 1466994131586, null, null, null);
-- mpp 极光masterSecret
insert into UAOP_SYSPARAM (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('MPP', 'ADDR', 'jiguang.masterSecret', 'e501ab754837f363d6d577e6', 'UAOP', 1466993719222, 1466994131586, null, null, null);

-- FastDFS文件服务器地址
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'FDFS', 'fdfsDownServer', 'http://172.16.0.112/', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('UAOP', 'FDFS', 'fdfsUpServer', 'http://192.168.0.112:58820/fdfs/api/file/upload', 'UAOP', 1466993719222, 1466994131586, '', '', '');

-- FastDFS文件服务器的REST上传服务参数
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'REST', 'fdfs_rest_port', '58820', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.soTimeout', '1501', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.connectTimeout', '601', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.thumbImage.width', '150', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.thumbImage.height', '150', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.previewImage.width', '800', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.previewImage.height', '800', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fdfs.trackerList', '172.16.0.112:22122', 'UAOP', 1466993719222, 1466994131586, '', '', '');
insert into uaop_sysparam (SYSTEM_ID, PARAM_TYPE, PARAM_NAME, PARAM_VALUE, CREATE_USER, CREATE_TIME, LAST_MODIFY_TIME, EXT_STR1, EXT_STR2, EXT_STR3)
values ('FDFS', 'SYSTEM', 'fast.webServerUrl', 'NONE', 'UAOP', 1466993719222, 1466994131586, '', '', '');


-- 增加系统标志字典项,用于系统名称展示
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='System';
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='SYSTEM';
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000001', 'UAOP', '0', 'UAOP', null, 'SYSTEM', '统一认证', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000002', 'UAOP', '0', 'OMP', null, 'SYSTEM', '统一运维', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000003', 'UAOP', '0', 'XCKY', null, 'SYSTEM', '勘验系统', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000004', 'UAOP', '0', 'ALIMS', null, 'SYSTEM', '检验鉴定', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000005', 'UAOP', '0', 'YTHPT', null, 'SYSTEM', '一体化', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000006', 'UAOP', '0', 'WZGL', null, 'SYSTEM', '物证保管', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('00000000000000000000000000000007', 'UAOP', '0', 'MPP', null, 'SYSTEM', '移动警务', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');


-- 增加所属机构类型字典项,用于所属机构类型名称展示
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='UnitLevelModel';
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='OTypeModel';
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='OTYPEMODEL';
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('b94d4cebfbda42609caa0bfe24fda68b', 'UAOP', '0', '0', null, 'OTYPEMODEL', '部', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('4216ca9259584b6e98451ccd4f44d7a1', 'UAOP', '0', '1', null, 'OTYPEMODEL', '省', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('263c84c1fe1643e9aad0aa2776691eac', 'UAOP', '0', '2', null, 'OTYPEMODEL', '市', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('2e181145ce534a118fd4bdbe060e57f1', 'UAOP', '0', '3', null, 'OTYPEMODEL', '分县局', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('58f03c674fc24ba9858ed19261d7cd0d', 'UAOP', '0', '4', null, 'OTYPEMODEL', '派出所', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');

-- 增加所属机构行政级别字典项,用于所属机构行政级别名称展示
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='OLevelModel';
delete from uaop_sys_dict t where t.Sys_Code='UAOP' and  t.root_key='OLEVELMODEL';
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('b485345935434afeb5de4ff71bd7e398', 'UAOP', '0', '0', null, 'OLEVELMODEL', '国家级', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('1e806d4f1b7149d78db794f012004fd4', 'UAOP', '0', '1', null, 'OLEVELMODEL', '省部级', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('3c26045e630847b09ee51693e0197c93', 'UAOP', '0', '2', null, 'OLEVELMODEL', '司厅局级', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('ee17edb9c467431aaf37f379f44066b0', 'UAOP', '0', '3', null, 'OLEVELMODEL', '县处级', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');
insert into UAOP_SYS_DICT (ID, SYS_CODE, DICT_LEVEL, DICT_KEY, PARENT_KEY, ROOT_KEY, DICT_VALUE1, DICT_VALUE2, DICT_VALUE3, LEAF_FLAG, DOWNLOAD_FLAG, READONLY_FLAG, DICT_SORT, DICT_PY, OPEN_FLAG, REMARK, CREATE_USER, CREATE_DATETIME, UPDATE_USER, UPDATE_DATETIME, PARENT_ROOT_KEY, TYPE)
values ('b2cbe0d130f64558b73af0874537ea38', 'UAOP', '0', '4', null, 'OLEVELMODEL', '乡镇科', null, null, null, null, '0', null, null, '1', null, 'UAOP', 1481940720000, null, null, null, '0');


commit;



