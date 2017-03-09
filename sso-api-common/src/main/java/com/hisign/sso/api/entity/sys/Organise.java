package com.hisign.sso.api.entity.sys;

import java.io.Serializable;
import java.util.List;

import com.hisign.sso.api.entity.SysBaseEntity;

/**
 * @Title:
 *   组织机构
 * @description:
 * 
 * @Company: hisign.com.cn
 * @author lnj 
 * @create time：2016年7月6日  上午10:21:23
 * @version 1.0
 */
public class Organise extends SysBaseEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//组织机构ID
	private String orgId;
	
	//机构代码 
	private String orgCode;
	
	//组织机构名称
	private String name;
	
	//上级组织机构ID
	private String superId;
	
	//系统唯一标识
	@Deprecated
	private String systemId;
	
	//组织机构类型:部门/组
	private Integer type;
	
	//组织机构描述
	private String note;
	
	//组织机构拼音全称
	private String pinyin;
	
	//组织机构拼音首字母
	private String firstLetter;
	
	/**
	 * 简称
	 */
	private String shortenedName;
	
	/**
	 * 联系电话
	 */
    private String phone;
    
    /**
     * 传真
     */
    private String fax;
    
    /**
     * 通讯地址
     */
    private String address;
    
    /**
     * 邮编
     */
    private String zipcode;
    
    /**
     * 所属公司Id
     */
    private String companyId;
    
    /**
     * 所属公司名
     */
    private String companyName;
	
	private List<Organise> children;

    private String userCount;
    
    /**
     * 机构类型
     */
    private String oType;
    
    /**
     * 机构类型名
     */
    private String oTypeName;
    
    /**
     * 机构行政级别
     */
    private String oLevel;
    
    /**
     * 机构行政级别名
     */
    private String oLevelName;
    
    /**
     * 是否科室
     */
    private String isLab;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getSuperId() {
		return superId;
	}

	public void setSuperId(String superId) {
		this.superId = superId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public List<Organise> getChildren() {
		return children;
	}

	public void setChildren(List<Organise> children) {
		this.children = children;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public String getShortenedName() {
		return shortenedName;
	}

	public void setShortenedName(String shortenedName) {
		this.shortenedName = shortenedName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the userCount
	 */
	public String getUserCount() {
		return userCount;
	}

	/**
	 * @param userCount the userCount to set
	 */
	public void setUserCount(String userCount) {
		this.userCount = userCount;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getoType() {
		return oType;
	}

	public void setoType(String oType) {
		this.oType = oType;
	}

	public String getoLevel() {
		return oLevel;
	}

	public void setoLevel(String oLevel) {
		this.oLevel = oLevel;
	}

	public String getIsLab() {
		return isLab;
	}

	public void setIsLab(String isLab) {
		this.isLab = isLab;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getoTypeName() {
		return oTypeName;
	}

	public void setoTypeName(String oTypeName) {
		this.oTypeName = oTypeName;
	}

	public String getoLevelName() {
		return oLevelName;
	}

	public void setoLevelName(String oLevelName) {
		this.oLevelName = oLevelName;
	}
	
}
