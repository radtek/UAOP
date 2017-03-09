package com.hisign.sso.api.constant;

/**
 * @Title: 统一认证平台常量定义
 * @description:
 * 
 * @author lnj
 */
public class UAOPConstant {

	/**
	 * key-统一认证中心唯一标识
	 */
	public static final String KEY_SYSTEMID = "systemId";

	/**
	 * 统一认证中心唯一标识
	 */
	public static final String SYSTEMID = "UAOP";
	
	/**
	 * 未知SystemId
	 */
	public static final String UNKOWN_SYSTEMID = "UNKOWN";

	/**
	 * 用户开放标记
	 */
	public static final String USER_OPEN = "1";

	/**
	 * 客户端系统功能JSP目录
	 */
	public static final String JSP_SYS_DIR = "WEB-INF/jsp/sys/";

	/**
	 * 分页起始行
	 */
	public static final String SQL_PAGE_START_KEY = "start";

	/**
	 * 每页数据条数
	 */
	public static final String SQL_PAGE_SIZE_KEY = "size";

	/**
	 * 系统字符集编码
	 */
	public static final String CHARSET_NAME = "UTF-8";
	
	/**
	 * 系统参数主键
	 * @author Administrator
	 *
	 */
	public static final class SysParamKey{
		public static final String logtoken_timeout = "logtoken_timeout";
		public static final String system_logtoken_timeout = "system_logtoken_timeout";
	}

	/**
	 * 实体状态
	 *
	 */
	public static final class STATUS {
		public static final int NORMAL = 0; // 正常
		public static final int DELETED = 1; // 已删除
	}

	/**
	 * 组织机构类型
	 *
	 */
	public static final class OrganiseType {
		public static final int NORMAL = 0; // 普通类型
		public static final int SUBMISSION = 1; // 委托机构
		public static final int INDENTIFY = 2; // 鉴定机构

		// 现勘范围 100~199
		// 一体化范围200~299
		// 物证范围300~399
	}

	/**
	 * 用户类型
	 */
	public static final class UserType {
		public static final int NORMAL = 0; // 委托用户
		public static final int EXTSUBMISSION = 1; // 外部委托用户
		public static final int INDENTIFY = 2; // 鉴定用户

		public static final int XCKY = 100; // 现勘用户

		public static final int YTHPT = 200; // 一体化平台用户

		public static final int WZGL = 300; // 物证管理平台用户
	}

	/**
	 * 角色类型
	 */
	public static final class RoleType {
		public static final int NORMAL = 0; // 普通角色
		public static final int BUILDIN = 1; // 内置角色
	}

	/**
	 * 授权点类型
	 */
	public static final class ResourceType {
		public static final int MENU = 0; // 菜单授权点
		public static final int SERVICE = 1; // 服务授权点
	}

	/**
	 * 是否启用状态
	 */
	public static final class ActiveStatus {
		public static final int UNACTIVE = 0; // 不启用
		public static final int ACTIVE = 1; // 启用
	}

	/**
	 * 菜单类型
	 */
	public static final class MenuType {
		public static final int MENU = 0; // 菜单
		public static final int BUTTON = 1; // 按钮
	}
	
	/**
	 * 菜单所属应用类型
	 */
	public static final class AppType {
		public static final int WEB = 0; // web应用
		public static final int APP = 1; // 手机App应用
	}
	
	/**
	 * 菜单可见状态 
	 */
	public static final class VisibleState {
		public static final int VISIBLE = 0; // 可见的
		public static final int INVISIBLE = 1; // 不可见的
	}

	/**
	 * 是否为辅警
	 */
	public static final class IsPolice {
		public static final int NO = 0; // 否
		public static final int YES = 1; // 是
	}

	/**
	 * 消息topic定义
	 * 
	 * @author Administrator
	 *
	 */
	public static final class MsgTopic {
		public static final String UAOP_SSO = "UAOP_SSO"; // 统一认证平台所使用的topic
	}

	/**
	 * 消息类型定义 
	 * 消息类型范围: UAOP 900000~999999
	 * 现勘(XCKY) 100000~199999 
	 * 一体化(YTHPT) 200000~299999 
	 * 物证(WZGL) 300000~399999 
	 * 实验室(ALIMS) 800000~899999
	 */
	public static final class MsgType {
		public static final long USER_ADD = 900001l; // 添加用户
		public static final long USER_UPDATE = 900002l; // 修改用户
		public static final long USER_DELETE = 900003l; // 删除用户
		public static final long USER_ORGANISE_REL = 900004l; // 用户与组织机构关系变更
		public static final long USER_ROLE_REL = 900005l; // 用户与角色关系变更
		public static final long USER_PASS_MODIFY = 900006l; // 用户密码修改
		public static final long USER_RELOADALL = 900007l;    // 重新加载所有用户
		
		public static final long USER_ACCOUNT_ADD = 900051l; // 用户账号添加
		public static final long USER_ACCOUNT_MODIFY = 900052l; // 用户账号修改
		public static final long USER_ACCOUNT_DELETE = 900053l; // 用户账号删除
		public static final long USER_ACCOUNT_BATCHADD = 900054l; // 用户账号批量添加
		
		public static final long USER_BASEINFO_ADD = 900061l; // 用户账号添加
		public static final long USER_BASEINFO_MODIFY = 900062l; // 用户账号修改
		public static final long USER_BASEINFO_DELETE = 900063l; // 用户账号删除
		public static final long USER_BASEINFO_BATCHADD = 900064l; // 用户账号批量添加
		
		public static final long ORGANISE_ADD = 900101l; // 添加组织机构
		public static final long ORGANISE_UPDATE = 900102l; // 修改组织机构
		public static final long ORGANISE_DELETE = 900103l; // 删除组织机构
		public static final long ORGANISE_RELOADALL = 900105l;    // 重新加载所有组织机构
		
		public static final long ROLE_ADD = 900201l;    // 添加角色 
		public static final long ROLE_UPDATE = 900202l; // 修改角色
		public static final long ROLE_DELETE = 900203l; // 删除角色 
		public static final long ROLE_RELOADALL = 900205l;    // 重新加载所有角色
		
		public static final long DICT_ADD = 900301l;    // 添加字典项 
		public static final long DICT_UPDATE = 900302l; // 修改字典项
		public static final long DICT_DELETE = 900303l; // 删除字典项
		public static final long DICT_BATCHADD = 900304l;    // 批量添加字典项 
		public static final long DICT_RELOADALL = 900305l;    // 重新加载所有字典项
	}
	
	/**
	 * 发送状态
	 */
	public static final class SendStatus {
		public static final int NO = 0; // 否
		public static final int YES = 1; // 是
		public static final int FAIL = 2; // 失败
	}
	
	/**
	 * 反馈状态
	 */
	public static final class FeedbackStatus {
		public static final int NO = 0; // 否
		public static final int YES = 1; // 是
	}
}
