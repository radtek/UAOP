package com.hisign.sso.api.cache.dict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.hisign.sso.api.entity.sys.SysDict;

/**
 * @Title:
 *    数据字典排序比较
 * @description:
 *    针对List<SysDict>按照DictSort进行排序
 * @author lnj 
 */
public class DictComparator implements Comparator<SysDict> {

	@Override
	public int compare(SysDict o1, SysDict o2) {
		if( (o1 == null) || (o2 == null) ){
			return 0;
		}
		
		Short dictSort1 = o1.getDictSort();
		Short dictSort2 = o2.getDictSort();
		
		if( (dictSort1 == null) && (dictSort2 == null) ){
			return 0;
		}
		
		if(dictSort1 == null){
			return 1;
		}
		
		if(dictSort2 == null){
			return -1;
		}
		
		if(dictSort1.shortValue() < dictSort2.shortValue()){
			return -1;
		}else if(dictSort1.shortValue() > dictSort2.shortValue()){
			return 1;
		}
		
		return 0;
	}
	
	
	public static void main(String[] args) {
		SysDict d1 = new SysDict();
		d1.setDictKey("d1");
		short s1 = 1;
		d1.setDictSort(s1);
		
		SysDict d2 = new SysDict();
		d2.setDictKey("d2");
		short s2 = 2;
		d2.setDictSort(s2);
		
		SysDict d3 = new SysDict();
		d3.setDictKey("d3");
		short s3 = 3;
		d3.setDictSort(s3);
		
		List<SysDict> list = new ArrayList<SysDict>();
		list.add(d2);
		list.add(d1);
		list.add(d3);

		Collections.sort(list,new DictComparator());
		for(SysDict dict:list){
			System.out.println(""+dict.getDictKey());
		}
	}

}
