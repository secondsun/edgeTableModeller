module dev.secondsun {
    requires javafx.controls;
    requires javafx.fxml;

    opens dev.secondsun to javafx.fxml;

    opens dev.secondsun.controls to javafx.fxml;
    exports dev.secondsun;
}