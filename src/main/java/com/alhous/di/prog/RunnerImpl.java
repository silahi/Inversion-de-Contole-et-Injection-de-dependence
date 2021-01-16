package com.alhous.di.prog;

import com.alhous.di.IRunner;
import com.alhous.di.dao.INombre;
import com.alhous.di.metier.IAddition;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author silah
 */
public class RunnerImpl implements IRunner {

    @Override
    public void run() {
        try {
            Path path = new File("config.properties").toPath();
            Optional<String> nombreImplLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("nombreImpl=")).findFirst();
            Optional<String> additionImplLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("additionImpl=")).findFirst();

            if (nombreImplLine.isPresent() && additionImplLine.isPresent()) {
                Class cnombre = Class.forName(nombreImplLine.get().split("=")[1]);
                INombre nombre = (INombre) cnombre.newInstance();

                Class caddition = Class.forName(additionImplLine.get().split("=")[1]);
                IAddition addition = (IAddition) caddition.newInstance();

                Method setNombreMethod = caddition.getMethod("setNombre", INombre.class);
                setNombreMethod.invoke(addition, nombre);

                double res = addition.somme();
                System.out.println(nombre.getNombre1() + " + " + nombre.getNombre2() + " = " + res);

            }
        } catch (IOException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            Logger.getLogger(RunnerImpl.class.getName()).log(Level.SEVERE, "Message : " + ex.getMessage(), ex);
        }
    }

}
