package com.alhous.di;

import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author silah
 */
public class RunnerImpl implements IRunner {

    @Override
    public void run() {
        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
        INombre nombre = (INombre) context.getBean("nombreImpl");
        IAddition addition = (IAddition) context.getBean("additionImpl");
        double res = addition.somme();
        System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + res);
    }

}
