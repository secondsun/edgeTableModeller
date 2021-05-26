package dev.secondsun;

import dev.secondsun.controls.XYField;
import dev.secondsun.controls.XYZField;
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
        loadTreeItems("initial 1", "initial 2", "initial 3");
        //System.out.println(cameraLocationXYZ.XYZ());
    }

    private void loadTreeItems(String... items) {
        TreeItem<SceneNode> root = new TreeItem<>(new SceneNode("Root Node"));
        root.setExpanded(true);
        for (String itemString : items) {
            root.getChildren().add(new TreeItem<>(new SceneNode(itemString)));
        }

        sceneTreeView.setRoot(root);
    }

}
