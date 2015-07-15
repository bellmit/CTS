package com.acs.enterprise.common.program.contactmanagement.view.helper;

import java.lang.reflect.Method;

public class Executor extends Thread{
	private Class klass;
	private Object target;
	private String methodName;
	private Object[] args;
	private Class[] types;
	private boolean executed;
	private Exception exception;
	private Object result;
	public Executor(Class klass, String methodName){
		this(klass,methodName,new Object[]{});
	}
	public Executor(Class klass, String methodName, Object[] args){
		this(klass,methodName,args,null);
	}
	public Executor(Class klass, String methodName, Object[] args, Class[]types){
		this.klass=klass;		
		this.methodName=methodName;
		this.args=args;
		this.types=types;
		
	}
	public Executor(Object target, String methodName){
		this(target,methodName,new Object[]{});
	}
	public Executor(Object target, String methodName, Object[] args){
		this.target=target;
		this.methodName=methodName;
		this.args=args;
	}
	public Executor(Object target, String methodName, Object[] args, Class[] types){
		this(target,methodName,args);
		this.types=types;
		
	}
	
	public synchronized void run() {
		try{
			if(target==null){
				target=klass.newInstance();				
			}else{
				klass = target.getClass();
			}
			if(types==null){
				types= getParameterTypes(args);
			}
			Method method = klass.getDeclaredMethod(methodName, types);
			method.setAccessible(true);
			result=method.invoke(target, args);
		}catch(Exception e){
			this.exception=e;
		}finally{
			executed=true;
			notify();
		}
		
	}
	private Class[] getParameterTypes(Object[] args){
		Class[] paramTypes = new Class[args.length];
		for(int i=0;i<args.length;i++){
			paramTypes[i]=args[i].getClass();
		}
		return paramTypes;
	}
	
	public synchronized Object get()throws Exception{
		if(!this.executed){
			wait();
		}
		if(exception!=null){
			throw exception;
		}
		return result;
			
	}
	
	

}
