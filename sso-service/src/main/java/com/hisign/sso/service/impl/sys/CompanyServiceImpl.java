package com.hisign.sso.service.impl.sys;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hisign.sso.api.constant.UAOPConstant;
import com.hisign.sso.api.entity.sys.Company;
import com.hisign.sso.api.query.QueryCondition;
import com.hisign.sso.api.query.QueryFilter;
import com.hisign.sso.api.rest.exception.RestBusinessException;
import com.hisign.sso.api.service.sys.CompanyService;
import com.hisign.sso.common.id.SysIDGenerator;
import com.hisign.sso.persist.mapper.sys.CompanyMapper;
import com.hisign.sso.service.impl.helper.BatchCommitHelper;

/**
 * @Title:
 *  公司管理服务
 * @description:
 * 
 * @author lnj 
 */
@Path("companys")
@Produces({ ContentType.APPLICATION_JSON_UTF_8 })
@Service("companyService")
public class CompanyServiceImpl implements CompanyService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private CompanyMapper mapper;
	
	@Autowired
	private BatchCommitHelper batchCommitHelper; //批量提交Helper

	@Override
	@GET
	@Path("{id}")
	public Company getById(@PathParam("id") String id) {
		return mapper.getById(id);
	}

	@Override
	@GET
	@Path("{id}/delete")
	public Map<String, String> delete(@PathParam("id") String id) throws Exception {
		try {
			Company t =new Company();
			t.setId(id);
			t.initUpdParameter();
			t.setStatus(UAOPConstant.STATUS.DELETED);
			
			mapper.updateStatusById(t);
		} catch (Exception e) {
			logger.error("删除失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "删除失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("update")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> update(Company t) throws Exception {
		t.initUpdParameter();
		try {
			mapper.update(t);
		} catch (Exception e) {
			logger.error("修改失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "修改失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("add")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> add(Company t) throws Exception {
		String id = SysIDGenerator.getInstance().genId();
		t.setId(id);
		t.initBaseParameter();
		try {
			mapper.add(t);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("id", id);
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("batchadd")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, String> addBatch(List<Company> list) throws Exception {
		for(int i = 0; i < list.size(); i++){
			Company company = list.get(i);
			company.setId(SysIDGenerator.getInstance().genId());
			company.initBaseParameter();
		}

		try {
			batchCommitHelper.addBatch(CompanyMapper.class, list);
		} catch (Exception e) {
			logger.error("添加失败", e);
			throw new RestBusinessException(Status.NOT_ACCEPTABLE, "添加失败");
		}
		Map<String, String> retMap = new HashMap<String, String>();
		retMap.put("message", "成功");
		return retMap;
	}

	@Override
	@POST
	@Path("query")
	@Consumes({ MediaType.APPLICATION_JSON })
	public List<Company> query(Map<String, Object> map) {
		return mapper.query(map);
	}

	@Override
	@POST
	@Path("count")
	@Consumes({ MediaType.APPLICATION_JSON })
	public int count(Map<String, Object> map) {
		return mapper.count(map);
	}

	/**
	 * 分页查询
	 * @param map  查询条件
	 * @param pageNum 查询页
	 * @param pageSize 每页显示条数
	 * @param orderBy  排序
	 * @return
	 */
	@Override
	@POST
	@Path("pagequery")
	@Consumes({ MediaType.APPLICATION_JSON })
	public Map<String, Object> queryByPagination(QueryFilter queryFilter) {
		String orderBy = queryFilter.getOrderBy();
		String sort = queryFilter.getSort();
		orderBy = orderBy + " " + sort;
		
		Map<String,Object> map = new HashMap<String,Object>();
		QueryCondition condition = queryFilter.getQueryCondition();
		if(condition != null){
			String companyName = condition.getCompanyName();
			map.put("companyName", companyName);
			String superId = condition.getSuperId();
			map.put("superId", superId);
			String systemId = condition.getSystemId();
			map.put("systemId", systemId);
			String pinyin = condition.getPinyin();
			map.put("pinyin", pinyin);
			String firstLetter = condition.getFirstLetter();
			map.put("firstLetter", firstLetter);
		}
		
		queryFilter.calculateOffsetAndLimit(); //计算offset和Limit
		PageHelper.offsetPage(queryFilter.getOffset(), queryFilter.getLimit(), orderBy);
		
        Page<Company> page = (Page<Company>)mapper.query(map);
        
        //组织结果
        Map<String,Object> resultMap = new HashMap<String,Object>();
        resultMap.put("result", page.getResult());
        resultMap.put("total", page.getTotal());
        
        return resultMap;
	}

}
