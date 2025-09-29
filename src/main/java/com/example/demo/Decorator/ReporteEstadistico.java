package com.example.demo.Decorator;

public class ReporteEstadistico  extends ReporteDecorador {

    public ReporteEstadistico(Reporte reporte) {
        super(reporte);
    }

    @Override
    public String generar() {
        return reporte.generar() + agregarEstadisticas();
    }

    private String agregarEstadisticas() {
        return " + Agregando estadísticas al reporte";
    }
}
