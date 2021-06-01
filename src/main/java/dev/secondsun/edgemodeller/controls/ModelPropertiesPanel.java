package dev.secondsun.edgemodeller.controls;

import dev.secondsun.geometry.Model;
import dev.secondsun.util.Plane;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

/**
 * This control shows details for either a Model or a Partition despite its name.
 */
public class ModelPropertiesPanel extends GridPane implements Initializable {

  private SimpleObjectProperty<Plane> partitionProperty = new SimpleObjectProperty<>();
  private SimpleObjectProperty<Model> modelProperty = new SimpleObjectProperty<>();

  @FXML private VBox emptyBox;
  @FXML private VBox partitionBox;
  @FXML private VBox modelBox;


  public ModelPropertiesPanel() {

    try {
      FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
          "partitionproperties.fxml").toURI().toURL());
      fxmlLoader.setRoot(this);
      fxmlLoader.setController(this);

      fxmlLoader.load();
    } catch (Exception exception) {
      throw new RuntimeException(exception);
    }
  }
  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    partitionProperty.addListener((observable, oldValue, newValue) -> {
        emptyBox.setVisible(false);
      modelBox.setVisible(false);
      partitionBox.setVisible(true);
    });

    modelProperty.addListener((observable, oldValue, newValue) -> {
      emptyBox.setVisible(false);
      modelBox.setVisible(true);
      partitionBox.setVisible(false);
    });

  }

  public Plane getPartitionProperty() {
    return partitionProperty.get();
  }

  public SimpleObjectProperty<Plane> partitionPropertyProperty() {
    return partitionProperty;
  }

  public void setPartitionProperty(Plane partitionProperty) {
    this.partitionProperty.set(partitionProperty);
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
}
