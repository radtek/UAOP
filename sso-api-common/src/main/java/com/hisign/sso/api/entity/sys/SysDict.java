package com.hisign.sso.api.entity.sys;


import com.hisign.sso.api.rest.filter.RequestContext;
import com.hisign.sso.api.util.StringUtils;

/**
 * 系统字典表
 * 
 * @author chailiangzhi
 * @date 2016-7-25
 * 
 */
public class SysDict implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public static final String UNKOWN_ACCOUNT = "unkown"; //未知账户
	
	/**
	 * 主键ID
	 */
	private String id;
	/**
	 * 系统标识
	 */
	private String sysCode;
	/**
	 * 字典级别
	 */
	private String dictLevel;
	/**
	 * 字典代码
	 */
	private String dictKey;
	/**
	 * 字典父节点代码
	 */
	private String parentKey;
	/**
	 * 字典根节点代码
	 */
	private String rootKey;
	/**
	 * 字典值1
	 */
	private String dictValue1;
	/**
	 * 字典值2
	 */
	private String dictValue2;
	/**
	 * 字典值3
	 */
	private String dictValue3;
	/**
	 * 叶子节点标志
	 */
	private String leafFlag;
	/**
	 * 下载标志
	 */
	private String downloadFlag;
	/**
	 * 只读标志
	 */
	private String readonlyFlag;
	/**
	 * 显示顺序
	 */
	private Short dictSort;
	/**
	 * 字典PY输入的编码
	 */
	private String dictPy;
	/**
	 * 启用标志
	 */
	private String openFlag;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 创建时间
	 */
	private Long createDatetime;
	private String createDatetimeStr;
	/**
	 * 修改人
	 */
	private String updateUser;
	/**
	 * 修改时间
	 */
	private Long updateDatetime;
	private String updateDatetimeStr;
	/**
	 * 0数据,1目录
	 */
	private String type;
	/**
	 * 父节点类型
	 */
	private String parentRootKey;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getDictLevel() {
		return dictLevel;
	}

	public void setDictLevel(String dictLevel) {
		this.dictLevel = dictLevel;
	}

	public String getDictKey() {
		return dictKey;
	}

	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}

	public String getParentKey() {
		return parentKey;
	}

	public void setParentKey(String parentKey) {
		this.parentKey = parentKey;
	}

	public String getRootKey() {
		return rootKey;
	}

	public void setRootKey(String rootKey) {
		this.rootKey = rootKey;
	}

	public String getDictValue1() {
		return dictValue1;
	}

	public void setDictValue1(String dictValue1) {
		this.dictValue1 = dictValue1;
	}

	public String getDictValue2() {
		return dictValue2;
	}

	public void setDictValue2(String dictValue2) {
		this.dictValue2 = dictValue2;
	}

	public String getDictValue3() {
		return dictValue3;
	}

	public void setDictValue3(String dictValue3) {
		this.dictValue3 = dictValue3;
	}

	public String getLeafFlag() {
		return leafFlag;
	}

	public void setLeafFlag(String leafFlag) {
		this.leafFlag = leafFlag;
	}

	public String getDownloadFlag() {
		return downloadFlag;
	}

	public void setDownloadFlag(String downloadFlag) {
		this.downloadFlag = downloadFlag;
	}

	public String getReadonlyFlag() {
		return readonlyFlag;
	}

	public void setReadonlyFlag(String readonlyFlag) {
		this.readonlyFlag = readonlyFlag;
	}

	public Short getDictSort() {
		return dictSort;
	}

	public void setDictSort(Short dictSort) {
		this.dictSort = dictSort;
	}

	public String getDictPy() {
		return dictPy;
	}

	public void setDictPy(String dictPy) {
		this.dictPy = dictPy;
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public Long getCreateDatetime() {
		return createDatetime;
	}

	public void setCreateDatetime(Long createDatetime) {
		this.createDatetime = createDatetime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public Long getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(Long updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the parentRootKey
	 */
	public String getParentRootKey() {
		return parentRootKey;
	}

	/**
	 * @param parentRootKey
	 *            the parentRootKey to set
	 */
	public void setParentRootKey(String parentRootKey) {
		this.parentRootKey = parentRootKey;
	}

	/**
	 * @return the createDatetimeStr
	 */
	public String getCreateDatetimeStr() {
		return createDatetimeStr;
	}

	/**
	 * @param createDatetimeStr
	 *            the createDatetimeStr to set
	 */
	public void setCreateDatetimeStr(String createDatetimeStr) {
		this.createDatetimeStr = createDatetimeStr;
	}

	/**
	 * @return the updateDatetimeStr
	 */
	public String getUpdateDatetimeStr() {
		return updateDatetimeStr;
	}

	/**
	 * @param updateDatetimeStr
	 *            the updateDatetimeStr to set
	 */
	public void setUpdateDatetimeStr(String updateDatetimeStr) {
		this.updateDatetimeStr = updateDatetimeStr;
	}
	
	
	/**
	 * 初始化通用参数
	 * @return  
	 * @throws
	 */
	public void initBaseParameter(){
		this.openFlag = "1";
		createDatetime = System.currentTimeMillis();
		//未取到登录用户
		if(StringUtils.isEmpty(createUser)){
			if(StringUtils.isEmpty(RequestContext.getLoginAccount())){
				createUser = UNKOWN_ACCOUNT;
			}else{
				createUser = RequestContext.getLoginAccount();
			}
		}
	}
	
	/**
	 * 更新数据时刷新修改字段数据
	 * @return  
	 * @throws
	 */
	public void initUpdParameter(){
		updateDatetime = System.currentTimeMillis();
		//未取到登录用户
		if(StringUtils.isEmpty(updateUser)){
			if(StringUtils.isEmpty(RequestContext.getLoginAccount())){
				updateUser = UNKOWN_ACCOUNT;
			}else{
				updateUser = RequestContext.getLoginAccount();
			}
		}
	}
}