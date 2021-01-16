package com.alhous.di.prog;

import com.alhous.di.IRunner;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

/**
 *
 * @author silah
 */
public class App {

    public static void main(String[] args) throws Exception {
        Path path = new File("config.properties").toPath();
        Optional<String> runnerLine = Files.readAllLines(path).stream().filter(line -> line.startsWith("runner=")).findFirst();
        if (runnerLine.isPresent()) {
            Class crunner = Class.forName(runnerLine.get().split("=")[1]);
            IRunner runner = (IRunner) crunner.newInstance();
            runner.run();
        }
    }
}
