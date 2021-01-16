package com.alhous.di.annota;

import com.alhous.di.IRunner;
import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author silah
 */
@Component
public class RunnerImpl implements IRunner {

    @Override
    public void run() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.alhous.di.annota.dao", "com.alhous.di.annota.metier");
        INombre nombre = (INombre) context.getBean("nombreImplV2");
        IAddition addition = (IAddition) context.getBean("additionImplV2");
        System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + addition.somme());
    }

}
