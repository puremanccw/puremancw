/**
 * 
 */
package com.puremancw.web.controller.base;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * <p>Tilte: BaseController</p>
 * @author puremancw
 * @date 2016年11月21日 下午3:21:56
 
 */
public abstract class BaseController {
	
	/**
	 * 获取shiro的session
	 * @return
	 */
	public Session getSession() {
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		return session;
	}
}
