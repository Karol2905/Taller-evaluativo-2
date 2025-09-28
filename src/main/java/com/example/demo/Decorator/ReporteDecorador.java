package com.example.demo.Decorator;

public abstract class ReporteDecorador implements Reporte {
    protected Reporte reporte;

    public ReporteDecorador(Reporte reporte) {
        this.reporte = reporte;
    }

    @Override
    public String generar() {
        return reporte.generar();
    }
}
