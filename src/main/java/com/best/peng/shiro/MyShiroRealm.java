package com.best.peng.shiro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.best.peng.global.BestConstant;
import com.best.peng.service.BestUserService;
import com.best.peng.service.MenuService;
import com.best.peng.service.RoleService;
import com.best.peng.sys.entity.BestUser;
import com.best.peng.sys.entity.Menu;
import com.best.peng.util.ValidateHelper;

/**
 * User         : zp
 * Date         : 17/2/19
 * Description  : 身份校验核心类
 */

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    BestUserService bestUserService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;


    /**
     * 认证信息.(身份验证)
     * Authentication 是用来验证用户身份
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
    	//System.out.println("身份认证方法：MyShiroRealm.doGetAuthenticationInfo()");

    	String username = (String) token.getPrincipal();//用户名
        String password = new String((char[]) token.getCredentials());//密码
        
    	//查询用户信息
        BestUser user = bestUserService.findBestUserByEmail(username);
        
        //账号不存在
        if(user == null) {
            throw new UnknownAccountException("账号或密码不正确");
        }
        
        //密码错误
        if(!bestUserService.findByBestUserByEmailAndPwd(username, password)) {
            throw new IncorrectCredentialsException("账号或密码不正确");
        }
        
        //账号锁定
        if(user.getStatus() == 0){
        	/**
    		 * 如果用户的status为禁用。那么就抛出<code>DisabledAccountException</code>
    		 */
        	throw new LockedAccountException("账号已被锁定,请联系管理员");
        }
        
        //更新登录时间
        bestUserService.updateBestUserLoginDate(user.getEmail());
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
        return info;
    }

    /**
     * 此方法调用  hasRole,hasPermission的时候才会进行回调.
     *
     * 权限信息.(授权):
     * 1、如果用户正常退出，缓存自动清空；
     * 2、如果用户非正常退出，缓存自动清空；
     * 3、如果我们修改了用户的权限，而用户不退出系统，修改的权限无法立即生效。
     * （需要手动编程进行实现；放在service进行调用）
     * 在权限修改后调用realm中的方法，realm已经由spring管理，所以从spring中获取realm实例，
     * 调用clearCached方法；
     * :Authorization 是授权访问控制，用于对用户进行的操作授权，证明该用户是否允许进行当前操作，如访问某个链接，某个资源文件等。
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        /*
        * 当没有使用缓存的时候，不断刷新页面的话，这个代码会不断执行，
        * 当其实没有必要每次都重新设置权限信息，所以我们需要放到缓存中进行管理；
        * 当放到缓存中时，这样的话，doGetAuthorizationInfo就只会执行一次了，
        * 缓存过期之后会再次执行。
        */
        //System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");

        BestUser user  = (BestUser)principals.getPrimaryPrincipal();

        Integer userId = user.getId();
        Integer roleId=user.getRole().getId();
		
		List<String> permsList = null;
		
		//系统管理员，拥有最高权限
		if(BestConstant.ADMIN_EMAIL.equals(user.getEmail())){
			List<Menu> menuList = menuService.list();
			permsList = new ArrayList<>(menuList.size());
			for(Menu menu : menuList){
				permsList.add(menu.getPermissionCode());
			}
		}else{
			//当前用户角色的权限
			permsList = roleService.findUserRolePermission(roleId);
		}

		//用户权限列表
		Set<String> permsSet = new HashSet<String>();
		for(String perms : permsList){
			if(ValidateHelper.isEmptyString(perms)){
				continue;
			}
			permsSet.addAll(Arrays.asList(perms.trim().split(",")));
		}
		
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permsSet);

        return info;
    }

}
