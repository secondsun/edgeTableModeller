package dev.secondsun.edgemodeller.controls;

import dev.secondsun.edgemodeller.controls.util.DecimalFieldFormatter;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.concurrent.Callable;

/**
 * This class controls a XYZField custom component
 * <p>
 * It is used by the camera form for the lookAt, and position.
 * Eventually it will be used by other things.
 */
public class XYZField extends GridPane {

    private ObjectBinding<XYZ> xyzBinding;

    public record XYZ(double x, double y, double z){};

    public ObjectBinding<XYZ> getXYZBinding() {
        return xyzBinding;
    }

    public XYZ XYZ() {
        return new XYZ(Double.parseDouble(getX()), Double.parseDouble(getY()), Double.parseDouble(getZ()));
    }


    @FXML
    private TextField x;
    @FXML
    private TextField y;
    @FXML
    private TextField z;

    public XYZField() {

        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "xyzfield.fxml").toURI().toURL());
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
        z.setTextFormatter(new DecimalFieldFormatter());

        this.xyzBinding = Bindings.createObjectBinding(new Callable<XYZ>(){
            @Override
            public XYZ call() throws Exception {
                return XYZ();
            }
        }, zProperty(), yProperty(), xProperty());

        xyzBinding.addListener(new ChangeListener<XYZ>() {
            @Override
            public void changed(ObservableValue<? extends XYZ> observableValue, XYZ oldValue, XYZ newValue) {
                System.out.println(oldValue + " became " + newValue);
            }
        });
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

    public void setZ(String value) {
        zProperty().set(value);
    }

    public String getZ() {
        return zProperty().get();
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
    public StringProperty zProperty() {
        return z.textProperty();
    }

}
