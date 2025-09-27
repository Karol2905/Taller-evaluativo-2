package com.example.demo.Decorator;

public class ReporteMarcaAgua  extends ReporteDecorador {

    public ReporteMarcaAgua(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        return reporte.generar() + agregarMarcaAgua();
    }

    private String agregarMarcaAgua() {
        return " + Agregando marca de agua al reporte";
    }
}
