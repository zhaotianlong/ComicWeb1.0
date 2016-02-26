package interceptor;



import java.lang.reflect.Method;

import javax.jms.Session;
import javax.management.StringValueExp;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.sun.mail.handlers.message_rfc822;

import antlr.StringUtils;
import auth.RoleAuth;

public class RoleInterceptor extends HandlerInterceptorAdapter {

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}

	@Override
	public void afterConcurrentHandlingStarted(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		super.afterConcurrentHandlingStarted(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		return RoleControl(request, response, handler);
	}
	private boolean RoleControl(HttpServletRequest request,HttpServletResponse response, Object handler) {
		HttpSession rSession=request.getSession();
		if(handler instanceof HandlerMethod){
			HandlerMethod hm=(HandlerMethod) handler;
			Object target=hm.getBean();
			Class<?> clazz=hm.getBeanType();
			Method method=hm.getMethod();
			
			try{
				if(clazz!=null&&method!=null){
					boolean isClazzAnnotation=clazz.isAnnotationPresent(RoleAuth.class);
					boolean isMethodAnnotation=method.isAnnotationPresent(RoleAuth.class);
					RoleAuth ra=null;
					if(isMethodAnnotation)
						{
							System.out.println("我是方法的拦截器");
							ra=method.getAnnotation(RoleAuth.class);
						}
					else if(isClazzAnnotation)
						{
						System.out.println("我是类的拦截器");
							ra=clazz.getAnnotation(RoleAuth.class);
						}
					
					String[] value=ra.value();
					User obj=(User) rSession.getAttribute("user");
					String userRole=obj==null?"":obj.getRoleId().getRoleName().toString();
					System.out.println("我是权限"+userRole);
					//重定位到登录
					if("".equals(userRole)){
						response.sendRedirect("/ComicWeb/homepage/login");
					}
					boolean isEquals=false;
					for(String str :value)
					{
						if(str.equals(userRole))isEquals=true; 
					}
					if(!isEquals){
						response.setStatus(401);
						return false;
					}
					
				}
			}
			catch(Exception ex){
				
			}
			
			
		}
		return true;
	}
	

}






/*
*		注释方法的
*
*/
/* 
 boolean flag = false;
		System.out.println("拦截器设置handler:"+handler);
		if (handler.getClass().isAssignableFrom(HandlerMethod.class)) {
			System.out.println("handler拦截器设置");
			RoleAuth role=((HandlerMethod) handler).getMethod().getAnnotation(RoleAuth.class);
			if(role!=null){
				System.out.println("role拦截器设置");
				User user = (User) request.getSession().getAttribute("user");
				System.out.println("role拦截器设置"+user);
				if(user==null){
					response.sendRedirect("/ComicWeb/homepage/login");
					flag=false; 
				}
				else {
					String userRole=user.getRoleId().getRoleName();
					boolean flagSub=false;
					for(String str:role.value()){
						if(str.equals(userRole))flagSub=true;
					}
					
					if(!flagSub)
					{
						response.sendRedirect("/ComicWeb/error/notpage");
						flag=false;
					}
					else
						flag=true;
				}
			}
		}
		return flag;*/


