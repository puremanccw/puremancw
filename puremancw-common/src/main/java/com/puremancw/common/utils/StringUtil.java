/**
 * 
 */
package com.puremancw.common.utils;

import java.util.UUID;

/**
 * <p>Tilte: StringUtil</p>
 * @author puremancw
 * @date 2016年11月18日 下午4:26:00
 
 */
public class StringUtil {
	private StringUtil() {}
	
	/**
	 * 获取去掉横线的长度为32的UUID串.
	 * @return
	 */
	public static String get32UUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
