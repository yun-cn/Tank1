package com.zldigital.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhouxiang 
 * @version Dec 17, 2012 12:09:42 PM
 */
@SuppressWarnings({"unchecked","unused"})
public class ObjectUtils {
	
	/**
	 * 现只支持Map和LIst判断
	 * @param obj �?要判断的对象（List || Map�?
	 * @return
	 */	
	public static boolean isEmptyOrNull(Object obj){
		if(obj == null)
			return true;
		if(obj instanceof Map){
			Map map = (Map)obj;
			if(map == null || map.isEmpty() || map.size()==0)
				return true;
		}
		if(obj instanceof List){
			List list = (List)obj;
			if(list == null || list.isEmpty() || list.size()==0)
				return true;
		}
		
		return false;
	}
	
	/**
	 * 判断对象是否为空
	 * @param obj �?要判断的对象
	 * @return
	 */
	public static boolean isNull(Object obj) {

		if (obj == null)
			return true;
		else
			return false;
	}
	
	

	public static boolean isNullOrEmptyString(Object o)
    {
        if(o == null)
            return true;
        if(o instanceof String)
        {
            String str = (String)o;
            if(str.length() == 0)
                return true;
        }
        return false;
    }
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		Map map = new HashMap();
		map.put(1, 1);
		List list = new ArrayList();
		if(!isEmptyOrNull(map)){
			System.out.println(1);
		}
	}
}
