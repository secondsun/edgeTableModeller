package dev.secondsun;

public class SceneNode {
    private String title;

    public SceneNode(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return title;
    }
}
