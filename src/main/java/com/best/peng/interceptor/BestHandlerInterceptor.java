package com.best.peng.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.best.peng.domian.BestUser;

/**
 * 拦截器
 * @author zhoupeng
 *
 */
public class BestHandlerInterceptor implements HandlerInterceptor{

	//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		
	}

	//在请求处理之前进行调用(Controller方法调用之前)
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {

		//获取拦截的地址，便于登陆后跳转
		String backUrl=arg0.getRequestURI();
		System.out.println(arg0.getRequestURL());
		HttpSession session=arg0.getSession();
		BestUser user=(BestUser)session.getAttribute("user");
//		if(user==null){
//			arg1.sendRedirect("/login?backUrl="+backUrl);
//			return false;
//		}
		return true;// 只有返回true才会继续向下执行，返回false取消当前请求
	}

}
