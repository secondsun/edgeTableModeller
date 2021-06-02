package dev.secondsun.edgemodeller.controls;

import dev.secondsun.geometry.Triangle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class TriangleListCell extends ListCell<Triangle> {

    @FXML
    private XYZField vertex1;
    @FXML
    private XYZField vertex2;
    @FXML
    private XYZField vertex3;

    @FXML
    private TextField color;

    @FXML
    private TextField textureId;

    @FXML
    private XYField textureOrigin;
    @FXML
    private XYField textureUV;

    @FXML private Button update;
    private FXMLLoader mLLoader;

    @FXML
    private VBox root;

    @Override
    protected void updateItem(Triangle item, boolean empty) {
        super.updateItem(item, empty);
        if (empty || item == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("trianglelistitem.fxml"));
                mLLoader.setController(this);
                mLLoader.setRoot(new VBox());
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            var vertex = item.v1;
            vertex1.setX(vertex.x + "");
            vertex1.setY(vertex.y + "");
            vertex1.setZ(vertex.z + "");
            vertex = item.v2;
            vertex2.setX(vertex.x + "");
            vertex2.setY(vertex.y + "");
            vertex2.setZ(vertex.z + "");
            vertex = item.v3;
            vertex3.setX(vertex.x + "");
            vertex3.setY(vertex.y + "");
            vertex3.setZ(vertex.z + "");

            color.setText("#" + String.format("%x", item.textureId));
            textureId.setText("#" + String.format("%x", item.textureId));
            if (item.texture != null) {
                textureUV.setX(item.texture.u()+"");
                textureUV.setY(item.texture.v()+"");
                textureOrigin.setX(item.texture.origin().x+"");
                textureOrigin.setY(item.texture.origin().y+"");

            } else {
                textureUV.setX("0");
                textureUV.setY("0");
                textureOrigin.setX("0");
                textureOrigin.setY("0");
            }
            setText( null );
            setGraphic(root);
        }
    }
}
