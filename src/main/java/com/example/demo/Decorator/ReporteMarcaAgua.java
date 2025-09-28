package com.example.demo.Decorator;

public class ReporteMarcaAgua extends ReporteDecorador {

    public ReporteMarcaAgua(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        String contenidoOriginal = reporte.generar();
        return agregarMarcaAgua(contenidoOriginal);
    }

    private String agregarMarcaAgua(String contenido) {
        StringBuilder resultado = new StringBuilder();
        String marcaAgua = "CONFIDENCIAL";

        // Dividir el contenido en líneas
        String[] lineas = contenido.split("\n");

        // Agregar un encabezado con marca de agua
        resultado.append("\n");
        resultado.append("******************************************\n");
        resultado.append("*            ").append(marcaAgua).append("            *\n");
        resultado.append("******************************************\n\n");

        // Procesar cada línea del contenido original
        for (String linea : lineas) {
            // Agregar la línea original
            resultado.append(linea).append("\n");

            // Cada 5 líneas, insertar una marca de agua
            if (lineas.length > 5 && linea.trim().length() > 0) {
                resultado.append("--- ").append(marcaAgua).append(" ---\n");
            }
        }

        // Agregar un pie con marca de agua
        resultado.append("\n");
        resultado.append("******************************************\n");
        resultado.append("* Documento protegido - ").append(marcaAgua).append(" *\n");
        resultado.append("******************************************\n");

        return resultado.toString();
    }
}
