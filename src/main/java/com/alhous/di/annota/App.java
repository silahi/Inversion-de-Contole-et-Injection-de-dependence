package com.alhous.di.annota;

import com.alhous.di.IRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author silah
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.alhous.di.annota");
        IRunner runner = context.getBean(IRunner.class);
        runner.run();
    }
}
