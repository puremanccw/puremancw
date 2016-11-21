/**
 * 
 */
package com.puremancw.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>Tilte: ObjectUtil</p>
 * @author puremancw
 * @date 2016年11月21日 上午9:47:19
 
 */
public class ObjectUtil {
	
	public static boolean isEmpty(Object obj) {
		return obj == null || obj.toString().length() == 0;
	}
	
	public static boolean isNull(Object obj) {
		return obj == null;
	}
	
	public static Map<String, String> objectToMap(Object obj) {
		if(obj instanceof Map) {
			Map<String, String> map = new HashMap<String, String>();
			for(Object key : ((Map) obj).keySet()) {
				map.put(key.toString(), ((Map) obj).get(key).toString());
			}
			return map;
		} else {
			
		}
		return null;
	}
}
