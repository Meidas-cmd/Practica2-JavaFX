package org.example.demo;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.time.LocalDate;

public class HelloController {

    static Connection conexion;

    public TableView<Estudiante> estudiantesTableView;
    public TableColumn<Estudiante,Integer> niaTableColumn;
    public TableColumn<Estudiante,String> nombreTableColumn;
    public TableColumn<Estudiante,LocalDate> fechaNacimientoTableColumn;
    public Label mensajeLabel;
    public TextField niaTextField;
    public TextField nameTextField;
    public DatePicker fechaPicker;
    public Button insertarButtonId;
    public Button guardarButtonId;

    @FXML
    public void initialize(){

        conexion = Datos.conexion();


        niaTableColumn.setCellValueFactory(datos-> new SimpleIntegerProperty(datos.getValue().getNia()).asObject());
        nombreTableColumn.setCellValueFactory(datos-> new SimpleStringProperty(datos.getValue().getNombre()));
        fechaNacimientoTableColumn.setCellValueFactory(datos-> new ReadOnlyObjectWrapper<>(datos.getValue().getFecha_nacimiento()));

        estudiantesTableView.setItems(Datos.consulta(conexion));
    }

    public void editarButton() {

        Estudiante seleccionado = estudiantesTableView.getSelectionModel().getSelectedItem();

        if (seleccionado == null){
            mensajeLabel.setText("No hay nada seleccionado");
        }else {
            insertarButtonId.setDisable(true);
            guardarButtonId.setDisable(false);
            niaTextField.setDisable(true);
            niaTextField.setText(seleccionado.getNia().toString());
            nameTextField.setText(seleccionado.getNombre());
            fechaPicker.setValue(seleccionado.getFecha_nacimiento());

            mensajeLabel.setText("Estudiante modificado");
        }
    }

    public void borrarButton() {
        Estudiante seleccionado = estudiantesTableView.getSelectionModel().getSelectedItem();

        if (seleccionado == null){
            mensajeLabel.setText("No hay nada seleccionado");
        }else {
            Datos.eliminar(conexion, seleccionado);
            mensajeLabel.setText("Estudiante borrado.");
        }

        estudiantesTableView.setItems(Datos.consulta(conexion));

    }

    public void insertarButton() {
        Integer nia = Integer.parseInt(niaTextField.getText());
        String nombre = nameTextField.getText();
        LocalDate fecha_nacimiento = fechaPicker.getValue();
        Datos.insertar(conexion,new Estudiante(nia,nombre,fecha_nacimiento));

        Estudiante estudiante = new Estudiante(nia,nombre,fecha);
        DatosPersonas.insertarEstudiante(estudiante);
        niaTextField.clear();
        nameTextField.clear();
    }

    public void guardarButton() {

        Integer nia = Integer.parseInt(niaTextField.getText());
        String nombre = nameTextField.getText();
        LocalDate fecha_nacimiento = fechaPicker.getValue();
        Datos.modificar(conexion,new Estudiante(nia,nombre,fecha_nacimiento));

        insertarButtonId.setDisable(false);
        guardarButtonId.setDisable(true);
        niaTextField.clear();
        nameTextField.clear();
        niaTextField.setDisable(false);
        fechaPicker.setValue(null);

        estudiantesTableView.setItems(Datos.consulta(conexion));



    }
}
