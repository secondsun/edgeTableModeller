package dev.secondsun;

import dev.secondsun.geometry.Model;
import dev.secondsun.util.Plane;

public class SceneNode {
    private String title;
    private Plane splitPane;
    private Model model;

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

    public Model getModel() {
        return model;
    }

    public SceneNode setModel(Model model) {
        this.model = model;
        return this;
    }

    public SceneNode(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
