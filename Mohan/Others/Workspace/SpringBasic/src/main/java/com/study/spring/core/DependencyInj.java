package com.study.spring.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.spring.app.ConstructorMSG;
import com.study.spring.app.Message;
import com.study.spring.app.SpringStaticFactory;

public class DependencyInj {
	
	public static AbstractApplicationContext appContext;
	public static void main(String args[])
	{
		
		appContext=new ClassPathXmlApplicationContext("spring.xml");
		
		setterInjection();
		constructorInjection();
		staticFactoryMethod();
		instanceFactoryMethod();
		appContext.registerShutdownHook();
	}
	private static void instanceFactoryMethod() {
		// TODO Auto-generated method stub
		Message msg=(Message)appContext.getBean("instancemsg");
		System.out.println("instanceFactoryMethod:"+msg.printMessage());
		
	}
	private static void staticFactoryMethod() {
		// TODO Auto-generated method stub
		SpringStaticFactory spFact=(SpringStaticFactory)appContext.getBean("staticFactory");
		System.out.println(""+spFact.printMessage());
	}
	private static void setterInjection()
	{
		//SetterInjection
				Message msg=(Message)appContext.getBean("message");
				System.out.println("Setter Inject==>"+msg.printMessage());
	}
	private static void constructorInjection()
	{
		//ConstructorInjection
		ConstructorMSG conMsg=new ConstructorMSG("constructor");
		System.out.println(conMsg.printMessage());
		
		//Argument Constructor Injection
		conMsg=new ConstructorMSG("constructor1");
		System.out.println(conMsg.printMessages());
	}

}
