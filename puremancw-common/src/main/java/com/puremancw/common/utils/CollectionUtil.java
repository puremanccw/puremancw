/**
 * 
 */
package com.puremancw.common.utils;

import java.util.Collection;

/**
 * <p>Tilte: CollectionUtils</p>
 * @author puremancw
 * @date 2016年11月18日 下午5:34:46
 
 */
public class CollectionUtil {
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.size() == 0;
	}
}
