package dev.secondsun.edgemodeller;

import dev.secondsun.edgemodeller.controls.XYField;
import dev.secondsun.edgemodeller.controls.XYZField;
import dev.secondsun.geometry.playfield.Cube;
import dev.secondsun.geometry.playfield.DormRoom;
import dev.secondsun.util.BSPTree;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

    private BSPTree worldModel = new DormRoom().getBSPTree();
    private ObjectBinding<BSPTree> worldModelBinding;

    public void initialize() {

         this.worldModelBinding = new ObjectBinding<BSPTree>() {
            @Override
            protected BSPTree computeValue() {
                return worldModel;
            }
        };
        loadTreeItems();
    }

    private void loadTreeItems() {
        var treeNode = new TreeItem<>(new SceneNode(worldModel.getRoot()));
        sceneTreeView.setRoot(treeNode);
        buildTree(treeNode);

        worldModelBinding.addListener(new ChangeListener<BSPTree>() {
            @Override
            public void changed(ObservableValue<? extends BSPTree> observableValue, BSPTree oldValue, BSPTree newValue) {
                var treeNode = new TreeItem<>(new SceneNode(newValue.getRoot()));
                sceneTreeView.setRoot(treeNode);
                buildTree(treeNode);
            }
        });

    }

    private void buildTree(TreeItem<SceneNode> treeNode) {
        var rootNode = treeNode.getValue();

        if (rootNode.getFront() != null) {
            var front = new TreeItem<>(rootNode.getFront());
            treeNode.getChildren().add(front);
            buildTree(front);
        }
        if (rootNode.getBehind() != null) {
            var behind = new TreeItem<>(rootNode.getBehind());
            treeNode.getChildren().add(behind);
            buildTree(behind);
        }
    }

}
