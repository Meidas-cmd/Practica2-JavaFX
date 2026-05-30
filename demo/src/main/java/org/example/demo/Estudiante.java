package org.example.demo;

import java.time.LocalDate;

public class Estudiante {
    private Integer nia;
    private String nombre;
    private LocalDate fecha_nacimiento;

    public Estudiante(Integer nia, String nombre, LocalDate fecha_nacimiento){
        this.nia=nia;
        this.nombre=nombre;
        this.fecha_nacimiento=fecha_nacimiento;
    }

    public Integer getNia() {
        return nia;
    }

    public void setNia(int nia) {
        this.nia = nia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(LocalDate fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nia=" + nia +
                ", nombre='" + nombre + '\'' +
                ", fecha_nacimiento=" + fecha_nacimiento +
                '}';
    }
}
