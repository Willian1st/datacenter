package com.wg.cms.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import com.wg.cms.bean.User;
import com.wg.cms.impl.UserService;

public class SystemAuthorizingRealm extends AuthorizingRealm {
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private UserService userService;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 获取当前登录的用户名,等价于(String)principals.fromRealm(this.getName()).iterator().next()
		String currentUsername = (String) super.getAvailablePrincipal(principals);
		User member = userService.selectByUserName(currentUsername);
		if (member == null) {
			logger.info("用户不存在");
			throw new AuthenticationException("msg:用户不存在。");
		}
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();

		return simpleAuthorInfo;

	}

	/**
	 * 认证回调函数, 登录时调用
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		// 获取基于用户名和密码的令牌
		// 实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User member = userService.selectByUserName(token.getUsername());
		if (member != null) {
			AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(member.getUsername(), member.getPassword(),
					this.getName());
			this.setSession("currentUser", member.getUsername());
			return authcInfo;
		}
		return null;

	}

	/**
	 * 保存登录名
	 */
	private void setSession(Object key, Object value) {
		Session session = getSession();
		logger.info("会话默认超时时间为:" + session.getTimeout() + "毫秒");
		if (null != session) {
			session.setAttribute(key, value);
		}
	}

	private Session getSession() {
		try {
			Subject subject = SecurityUtils.getSubject();
			Session session = subject.getSession(false);
			if (session == null) {
				session = subject.getSession();
			}
			if (session != null) {
				return session;
			}
		} catch (InvalidSessionException e) {

		}
		return null;
	}
}
