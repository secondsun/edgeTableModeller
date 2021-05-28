package dev.secondsun.edgemodeller;

import dev.secondsun.util.BSPTree;
import dev.secondsun.util.BSPTree.Node;
import dev.secondsun.util.Plane;
import javafx.beans.binding.ObjectBinding;

public class SceneNode {
    private String title;
    private Plane splitPane;
    private ObjectBinding<BSPTree> model;

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

    public SceneNode setModel(ObjectBinding<BSPTree> model) {
        this.model = model;
        return this;
    }

    public SceneNode(Node title) {
        if (title.partition == null) {
            this.title = "Model";
        } else {
            this.title = "Partition";
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

    @Override
    public String toString() {
        return title;
    }
}
