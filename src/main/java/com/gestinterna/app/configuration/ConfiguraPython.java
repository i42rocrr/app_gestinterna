package com.gestinterna.app.configuration;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;

@Configuration
public class ConfiguraPython {
    @Bean
    public Context obtenerContext() {
        String venvExePath = new File("/graalenv/bin/graalpy")
                .getPath()
                .toString();


        return Context
                .newBuilder("python")
                .allowAllAccess(true)
                .allowNativeAccess(true)
                .option("python.ForceImportSite", "true")
                .option("python.PythonPath", ".")
                .option("python.Executable", venvExePath)
                .option("python.NativeModules", "true")
                .build();
    }

    @Bean
    public Source obtenerSource() {
        try {
            return Source
                .newBuilder(
                "python",
                        new File("/src/main/python/servicios/ResultadosPyImpl.py")
                        //new File("C:\\Users\\rafael.rojas\\Documents\\TFG\\GitHub\\app_gestinterna\\src\\main\\python\\servicios\\ResultadosPyImpl.py")
                )
                .build();
        } catch (IOException ioException) {
            System.out.println("Algo pasó en el Bean de generación del context de python......................");
            System.out.println("-Causa del error: " + ioException.getCause());
            System.out.println("-Mensaje de error: " + ioException.getMessage());
            System.out.println("-Localizado en: " + ioException.getLocalizedMessage());
            return null;
        }
    }
}
