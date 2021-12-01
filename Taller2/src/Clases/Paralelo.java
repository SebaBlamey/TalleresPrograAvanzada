package Clases;

import Herencia.Asignaturas;

public class Paralelo {
    private String  numeroParalelo;
    private Asignaturas asignaturas;
    private Profesores profesor;
    private int cantAlumnos;

    public Paralelo(String numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
        this.asignaturas = null;
        this.profesor = null;
        cantAlumnos = 0;
    }

    public String getNumeroParalelo() {
        return numeroParalelo;
    }

    public int getCantAlumnos() {
        return cantAlumnos;
    }

    public void setCantAlumnos(int cantAlumnos) {
        this.cantAlumnos = cantAlumnos;
    }

    public void setNumeroParalelo(String numeroParalelo) {
        this.numeroParalelo = numeroParalelo;
    }

    public Asignaturas getAsignaturas() {
        return asignaturas;
    }

    public void setAsignaturas(Asignaturas asignaturas) {
        this.asignaturas = asignaturas;
    }

    public Profesores getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesores profesor) {
        this.profesor = profesor;
    }

    @Override
    public String toString() {
        return "Paralelo{" +
                "numeroParalelo='" + numeroParalelo + '\'' +
                ", asignaturas=" + asignaturas +
                ", profesor=" + profesor +
                ", cantAlumnos=" + cantAlumnos +
                '}';
    }
}
