package com.muppet.core.ioc.annotation;

import com.muppet.core.ioc.ApplicationContext;
import com.muppet.core.ioc.BeanInitializationException;
import com.muppet.core.ioc.InitializeException;
import com.muppet.data.util.Utils;
import com.muppet.util.reflect.ReflectUtil;

import java.lang.reflect.Field;


public class AutowiredExecutor {
	
	
	private ApplicationContext context = null;
	
	public AutowiredExecutor(ApplicationContext context){
		this.context = context;
	}
	
	public void execute(Field field, Object target){
		Autowired autowired = field.getDeclaredAnnotation(Autowired.class);
		if ( Utils.notEmpty(autowired) ){
			String beanName = autowired.beanName();
			Class beanClazz = autowired.type();
			boolean required = autowired.required();
			if( !beanName.equals(Autowired.BEAN_NAME_DEFAULT)){
				Object object = null;
				//如果获取不到相关bean,检查是否时必须的，如果是报错，否则返回即可
				try{
					object = this.context.getBean(beanName);
				}catch (InitializeException e) {
					if(required){
						throw new BeanInitializationException(
								String.format("the bean which type is %s has the property named  %s without mapping bean ,autowired failed "
										,target.getClass(), field.getName()));
					}else{
						return ;
					}
				}
				//检查获取到的bean 是否是相关属性的子类型
				if(field.getType().isAssignableFrom(object.getClass())){
					ReflectUtil.setValue(field, target, object);
				}else{
					throw new BeanInitializationException(
							String.format("the bean named %s is not the child type of %s", beanName, field.getType().getName()));
				}
			}else if( beanClazz.equals(Autowired.TYPE_DEFAULT)){
				beanClazz = field.getType();
			}
			
			Object object = null;
			try{
				object = this.context.getBean(beanClazz);
			}catch (InitializeException e) {
				if(required){
					throw new BeanInitializationException(
							String.format("the bean which type is %s has the property named  %s without mapping bean ,autowired failed "
									,target.getClass(), field.getName()));
				}else{
					return;
				}
			}
			if(field.getType().isAssignableFrom( object.getClass())){
				ReflectUtil.setValue(field, target, object);
			}else{
				throw new BeanInitializationException(
						String.format("bean type of %s initialization error happend ,the bean which type is %s is not the child type of property named %s "
								, target.getClass()
								, object.getClass().getName()
								, field.getName()));
			}
		}else{
			//如果没有Autowired注释，直接返回
			return;
		}
		
	}
}
