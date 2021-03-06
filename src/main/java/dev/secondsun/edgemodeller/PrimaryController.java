package dev.secondsun.edgemodeller;


import dev.secondsun.edgemodeller.controls.ModelPlanePanel;
import dev.secondsun.edgemodeller.controls.TriangleListCell;
import dev.secondsun.edgemodeller.controls.XYField;
import dev.secondsun.edgemodeller.controls.XYField.XY;
import dev.secondsun.edgemodeller.controls.XYZField;
import dev.secondsun.edgemodeller.controls.XYZField.XYZ;
import dev.secondsun.edgemodeller.vo.Project;
import dev.secondsun.game.ScanLineEngine;
import dev.secondsun.geometry.Model;
import dev.secondsun.geometry.Vertex;
import dev.secondsun.geometry.Vertex2D;
import dev.secondsun.geometry.playfield.DormRoom;
import dev.secondsun.util.BSPTree;
import java.awt.image.BufferedImage;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

public class PrimaryController {

  private Project project = new Project();

  public record Camera(XYZ location, XYZ lookAt, XY worldCenter, XY fieldOfView) {};

  @FXML
  private Canvas renderView;

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

  @FXML
  private ModelPlanePanel modelProps;


  private Model model = new DormRoom(project.getResources());
  private ScanLineEngine engine = new ScanLineEngine(355, 286, model, project.getResources());

  private BSPTree worldModel = model.getBSPTree();

  public void initialize() {

    ChangeListener listener = (
        (observable, oldValue, newValue) -> {
          System.out.println("redraw");
          var cam = new Camera(cameraLocationXYZ.XYZ(),cameraLookAtXYZ.XYZ(), worldCenter.XY(), fieldOfView.XY());
          model.lookAt(new dev.secondsun.geometry.Camera(
                  new Vertex((float) cam.location.x(), (float) cam.location.y(),
                      (float) cam.location.z()),
                  new Vertex((float) cam.lookAt.x(), (float) cam.lookAt.y(), (float) cam.lookAt.z())),
              new Vertex2D((float) cam.fieldOfView.x(), (float) cam.fieldOfView.y()),
              new Vertex2D((float) cam.worldCenter.x(), (float) cam.worldCenter.y()));
          var tris = model.getTriangles();
          var image = engine.draw(tris);
          System.out.println("redraw");
          renderView.getGraphicsContext2D().drawImage(toFXImage(image), 0, 0);
          model = new DormRoom(project.getResources());
        }

    );

    cameraLocationXYZ.XYZProperty().addListener(listener);
    cameraLookAtXYZ.XYZProperty().addListener(listener);
    worldCenter.XYProperty().addListener(listener);
    fieldOfView.XYProperty().addListener(listener);
    listener.changed(null,null,null);

    loadTreeItems();
    sceneTreeView.getSelectionModel().selectedItemProperty().addListener(
        new ChangeListener<TreeItem<SceneNode>>() {
          @Override
          public void changed(ObservableValue<? extends TreeItem<SceneNode>> observable,
              TreeItem<SceneNode> oldValue, TreeItem<SceneNode> newValue) {
            var value = newValue.getValue();
            if (value.getSplitPane() != null) {
              modelProps.partitionPropertyProperty().set(value.getSplitPane());
            } else {
              modelProps.modelPropertyProperty().set(value.getModel());
            }

          }
        });



  }

  private Image toFXImage(BufferedImage image) {
    WritableImage wr = null;
    if (image != null) {
      wr = new WritableImage(image.getWidth(), image.getHeight());
      PixelWriter pw = wr.getPixelWriter();
      for (int x = 0; x < image.getWidth(); x++) {
        for (int y = 0; y < image.getHeight(); y++) {
          pw.setArgb(x, y, image.getRGB(x, y));
        }
      }
    }

    return new ImageView(wr).getImage();
  }

  private void loadTreeItems() {
    var treeNode = new TreeItem<>(new SceneNode(worldModel.getRoot()));
    sceneTreeView.setRoot(treeNode);
    buildTree(treeNode);

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
