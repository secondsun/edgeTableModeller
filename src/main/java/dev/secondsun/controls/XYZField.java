package dev.secondsun.controls;

import dev.secondsun.controls.util.DecimalFieldFormatter;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;

/**
 * This class controls a XYZField custom component
 * <p>
 * It is used by the camera form for the lookAt, and position.
 * Eventually it will be used by other things.
 */
public class XYZField extends GridPane {

    public record XYZ(double x, double y, double z){};


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
