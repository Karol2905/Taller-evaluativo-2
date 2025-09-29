package com.example.demo.Decorator;

import java.util.Random;

public class ReporteGraficas extends ReporteDecorador {

    public ReporteGraficas(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        return reporte.generar() + generarGraficas();
    }

    private String generarGraficas() {
        StringBuilder graficas = new StringBuilder("\n=== Gráficas del Reporte ===\n");

        // Simular una gráfica de barras
        graficas.append("Gráfica de Barras:\n");
        Random random = new Random();
        for (int i = 1; i <= 5; i++) {
            int valor = random.nextInt(20) + 1;
            graficas.append(String.format("Dato %d: %s%n", i, "|".repeat(valor)));
        }

        // Simular una gráfica de líneas
        graficas.append("\nGráfica de Líneas:\n");
        int prevValor = random.nextInt(10);
        for (int i = 0; i < 5; i++) {
            int valor = random.nextInt(10);
            graficas.append(" ".repeat(valor)).append("*\n");
            if (valor > prevValor) {
                graficas.append(" ".repeat(prevValor)).append("/\n");
            } else if (valor < prevValor) {
                graficas.append(" ".repeat(valor)).append("\\\n");
            }
            prevValor = valor;
        }

        return graficas.toString();
    }
}
