package dev.secondsun.edgemodeller.controls;

import dev.secondsun.edgemodeller.controls.util.DecimalFieldFormatter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.concurrent.Callable;

/**
 * This class controls a XYField custom component
 * <p>
 * It is used by the camera form for the lookAt, and position.
 * Eventually it will be used by other things.
 */
public class XYField extends GridPane {

  private SimpleObjectProperty<XY> xySimpleObjectProperty = new SimpleObjectProperty<>();

  public ObjectProperty<XY> XYProperty() {
    return xySimpleObjectProperty;
  }

  public record XY(double x, double y){};


    public XY XY() {
        try {
            return new XY(Double.parseDouble(getX()), Double.parseDouble(getY()));
        } catch (Exception ex) {
            System.out.println(ex);
            return new XY(0,0);
        }
    }


    @FXML
    private TextField x;
    @FXML
    private TextField y;

    public XYField() {

        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "xyfield.fxml").toURI().toURL());
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

            fxmlLoader.load();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }


    public void initialize() {
        x.setTextFormatter(new DecimalFieldFormatter());
        y.setTextFormatter(new DecimalFieldFormatter());

      ChangeListener listener = ((observableValue, oldValue, newValue) -> {
        System.out.println(oldValue + " became " + newValue);
        xySimpleObjectProperty.setValue(XY());
      });

      xProperty().addListener(listener);
      yProperty().addListener(listener);

    }

    public String getX() {
        return xProperty().get();
    }

    public void setY(String value) {
        yProperty().set(value);
    }

    public String getY() {
        return yProperty().get();
    }

    public void setX(String value) {
        xProperty().set(value);
    }

    public StringProperty xProperty() {
        return x.textProperty();
    }
    public StringProperty yProperty() {
        return y.textProperty();
    }

}
