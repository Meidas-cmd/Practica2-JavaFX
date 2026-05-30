package org.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DatosPersonas {

    private static ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    public static void insertarEstudiante(Estudiante estudiante){

        listaEstudiantes.add(estudiante);
    }
}
