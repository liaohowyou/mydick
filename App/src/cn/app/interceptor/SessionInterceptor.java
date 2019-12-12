package cn.app.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/**
 * 拦截器继承自HandlerInterceptorAdapter
 * @author Administrator
 *
 */
public class SessionInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("userSession") != null){
			return true;
		}else if(request.getSession().getAttribute("devUserSession") != null){
			return true;
		}else{
			response.sendRedirect("/App/index.jsp");
			return false;
		}
		
	}
            
}
