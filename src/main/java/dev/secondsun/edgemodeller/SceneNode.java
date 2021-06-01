package dev.secondsun.edgemodeller;

import dev.secondsun.geometry.Model;
import dev.secondsun.util.BSPTree;
import dev.secondsun.util.BSPTree.Node;
import dev.secondsun.util.Plane;
import javafx.beans.binding.ObjectBinding;

public class SceneNode {

    private Model model;
    private String title;
    private Plane splitPane;

    private SceneNode front, behind;

    public SceneNode getBehind() {
        return behind;
    }

    public SceneNode setBehind(SceneNode behind) {
        this.behind = behind;
        return this;
    }

    public SceneNode getFront() {
        return front;
    }

    public SceneNode setFront(SceneNode front) {
        this.front = front;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public SceneNode setTitle(String title) {
        this.title = title;
        return this;
    }

    public Plane getSplitPane() {
        return splitPane;
    }

    public SceneNode setSplitPane(Plane splitPane) {
        this.splitPane = splitPane;
        return this;
    }


    public SceneNode(Node title) {
        if (title.partition == null) {
            this.title = "Model";
            this.model = title.bounds.model;
        } else {
            this.title = "Partition";
            this.splitPane = title.partition;
        }

        addFrontChildren(title);
        addBehindChildren(title);

    }

    private void addBehindChildren(Node title) {
        if (title.behind != null) {
            this.behind = new SceneNode(title.front);
        }
    }

    private void addFrontChildren(Node title) {
        if (title.front != null) {
            this.front = new SceneNode(title.front);
        }
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return title;
    }
}
