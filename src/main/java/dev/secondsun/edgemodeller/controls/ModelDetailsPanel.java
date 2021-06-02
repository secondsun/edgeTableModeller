package dev.secondsun.edgemodeller.controls;

import dev.secondsun.geometry.Model;
import dev.secondsun.geometry.Triangle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ModelDetailsPanel implements Initializable {
    private List<Triangle> triangles;
    private SimpleObjectProperty<Model> modelProperty;
    public ModelDetailsPanel() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                    "modeldetailsproperties.fxml").toURI().toURL());
            fxmlLoader.setRoot(this);
            fxmlLoader.setController(this);

            fxmlLoader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }

    }

    public Model getModelProperty() {
        return modelProperty.get();
    }

    public SimpleObjectProperty<Model> modelPropertyProperty() {
        return modelProperty;
    }

    public void setModelProperty(Model modelProperty) {
        this.modelProperty.set(modelProperty);
    }

    public void copyTriangles(Model model) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        modelProperty.addListener((observable, oldValue,newValue)->{
            triangles.clear();
            triangles.addAll(newValue.getTriangles());
        });
    }
}
