package guru.springframework.sfgdi;

import guru.springframework.sfgdi.config.SfgConfiguration;
import guru.springframework.sfgdi.config.SfgConstructorConfig;
import guru.springframework.sfgdi.controllers.*;
import guru.springframework.sfgdi.datasource.FakeDataSource;
import guru.springframework.sfgdi.services.PrototypeBean;
import guru.springframework.sfgdi.services.SingletonBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SfgDiApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(SfgDiApplication.class, args);

		MyController myController = (MyController) ctx.getBean("myController");
		System.out.println("------ primary bean ------");
		System.out.println(myController.sayHello());

		System.out.println("------ property -----");

		PropertyInjectedController propertyInjectedController = (PropertyInjectedController) ctx.getBean("propertyInjectedController");
		System.out.println(propertyInjectedController.getGreeting());

		System.out.println("------- Setters --------");

		SetterInjectedController setterInjectedController = (SetterInjectedController) ctx.getBean("setterInjectedController");
		System.out.println(setterInjectedController.getGreeting());

		System.out.println("-------- Constructor ---------");

		ConstructorInjectedController constructorInjectedController = (ConstructorInjectedController) ctx.getBean("constructorInjectedController");
		System.out.println(constructorInjectedController.getGreeting());

		System.out.println("-------- Profile ---------");

		I18nController i18nController = (I18nController) ctx.getBean("i18nController");
		System.out.println(i18nController.sayHello());

		PetController petController = ctx.getBean("petController", PetController.class);
		System.out.println("--- The Best Pet is ---");
		System.out.println(petController.whichPetIsTheBest());

		System.out.println("----- Scope of  BEANS ---------");
		SingletonBean singletonBean1 = (SingletonBean) ctx.getBean("singletonBean");
		System.out.println(singletonBean1.getMyScope());
		SingletonBean singletonBean2 = (SingletonBean) ctx.getBean("singletonBean");
		System.out.println(singletonBean2.getMyScope());

		PrototypeBean prototypeBean1 = (PrototypeBean) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean1.getMyScope());
		PrototypeBean prototypeBean2 = (PrototypeBean) ctx.getBean("prototypeBean");
		System.out.println(prototypeBean2.getMyScope());

		System.out.println("--------- Datasource properties ----------");
		FakeDataSource fakeDataSource = (FakeDataSource) ctx.getBean("fakeDataSource");
		System.out.println(fakeDataSource.getUsername());
		System.out.println(fakeDataSource.getPassword());
		System.out.println(fakeDataSource.getJdbcUrl());

		System.out.println("----------------Binding prop---------------");
		SfgConfiguration sfgConfiguration = (SfgConfiguration) ctx.getBean("sfgConfiguration");
		System.out.println(sfgConfiguration.getUsername());
		System.out.println(sfgConfiguration.getPassword());
		System.out.println(sfgConfiguration.getJdbcUrl());

		System.out.println("----------------Constructor Binding prop---------------");
		SfgConstructorConfig sfgConstructorConfig = (SfgConstructorConfig) ctx.getBean(SfgConstructorConfig.class);
		System.out.println(sfgConstructorConfig.getUsername());
		System.out.println(sfgConstructorConfig.getPassword());
		System.out.println(sfgConstructorConfig.getJdbcUrl());


	}

}
