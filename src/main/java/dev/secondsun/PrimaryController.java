package dev.secondsun;

import dev.secondsun.controls.XYField;
import dev.secondsun.controls.XYZField;
import dev.secondsun.geometry.playfield.Cube;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class PrimaryController {

    @FXML
    private XYZField cameraLocationXYZ;

    @FXML
    private XYZField cameraLookAtXYZ;
    @FXML
    private XYField worldCenter;

    @FXML
    private XYField fieldOfView;

    @FXML
    TreeView<SceneNode> sceneTreeView;

    public void initialize() {
        loadTreeItems();
        //System.out.println(cameraLocationXYZ.XYZ());
    }

    private void loadTreeItems(String... items) {
        var rootNode = new SceneNode("Root Node");
        rootNode.setModel(new Cube());

        var root = new TreeItem<>(rootNode);

        sceneTreeView.setRoot(root);
    }

}
