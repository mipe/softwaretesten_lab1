package at.ticketline.dao;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import at.ticketline.log.LogFactory;
import at.ticketline.log.Logger;

public class Utils {
	protected static final Logger log = LogFactory.getLogger(Utils.class);
	
	public static List<Field> getAllFields(Class<?> clazz) {
		List<Field> l = new ArrayList<Field>();
		if (clazz == null) {
			return l;
		}
		l.addAll(Arrays.asList(clazz.getDeclaredFields()));
		if (clazz.getSuperclass() != null) {
			l.addAll(Utils.getAllFields(clazz.getSuperclass()));
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> c : interfaces) {
			l.addAll(Utils.getAllFields(c));
		}
		return l;
	}

	public static List<Method> getAllMethods(Class<?> clazz) {
		List<Method> l = new ArrayList<Method>();
		if (clazz == null) {
			return l;
		}
		l.addAll(Arrays.asList(clazz.getDeclaredMethods()));
		if (clazz.getSuperclass() != null) {
			l.addAll(Utils.getAllMethods(clazz.getSuperclass()));
		}
		Class<?>[] interfaces = clazz.getInterfaces();
		for (Class<?> c : interfaces) {
			l.addAll(Utils.getAllMethods(c));
		}
		return l;
	}
	
	public static void callLifecycleMethod(Class<? extends Annotation> annotation,Object o) {
		if ((annotation != PostConstruct.class) && (annotation != PreDestroy.class)) {
			throw new RuntimeException("Invalid lifecycle annotation");
		}
		
		List<Method> methods = Utils.getAllMethods(o.getClass());
    	int count = 0;
    	Method lifecycleMethod = null;
    	for (Method m : methods) {
    		if (m.isAnnotationPresent(annotation)) {
    			count++;
    			lifecycleMethod = m;
    		}
    	}
    	if (count == 0) {
    		return;
    	} else if (count > 1) {
    		throw new DaoException("Multiple " + annotation.getSimpleName() + " annotations in class " + o.getClass().getName());
    	}
		if (lifecycleMethod.getParameterTypes().length > 0) {
			throw new DaoException("Parameters on " + annotation.getSimpleName() + " method " + lifecycleMethod.getName() + " are not allowed");
		}
		Utils.log.info("Invoking " + annotation.getSimpleName() + " method #0##1()",o.getClass().getName(),lifecycleMethod.getName());
		try {
			lifecycleMethod.invoke(o);
		} catch (Exception e) {
			throw new DaoException("Exception in " + annotation.getSimpleName() + " method " + lifecycleMethod.getName(),e);
		}
	}
}
