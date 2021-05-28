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
 * This class controls a XYField custom component
 * <p>
 * It is used by the camera form for the lookAt, and position.
 * Eventually it will be used by other things.
 */
public class XYField extends GridPane {

    private ObjectBinding<XY> xyBinding;

    public record XY(double x, double y){};

    public ObjectBinding<XY> getXYBinding() {
        return xyBinding;
    }

    public XY XY() {
        return new XY(Double.parseDouble(getX()), Double.parseDouble(getY()));
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
        
        this.xyBinding = Bindings.createObjectBinding(new Callable<XY>(){
            @Override
            public XY call() throws Exception {
                return XY();
            }
        },  yProperty(), xProperty());

        xyBinding.addListener(new ChangeListener<XY>() {
            @Override
            public void changed(ObservableValue<? extends XY> observableValue, XY oldValue, XY newValue) {
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
