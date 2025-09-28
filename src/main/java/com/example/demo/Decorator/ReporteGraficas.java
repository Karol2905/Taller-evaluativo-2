package com.example.demo.Decorator;

public class ReporteGraficas  extends ReporteDecorador {

    public ReporteGraficas(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        return reporte.generar() + agregarGraficas();
    }

    private String agregarGraficas() {
        return " + Agregando gráficas al reporte";
    }
}
