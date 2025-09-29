package com.example.demo.Decorator;

public class ReporteExportablePDF extends  ReporteDecorador {

    public ReporteExportablePDF(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        return reporte.generar() + exportarPDF();
    }

    private String exportarPDF() {
        return " + Exportando reporte a PDF";
    }
}
