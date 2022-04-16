package dev.secondsun.edgemodeller.vo;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import dev.secondsun.util.BSPTree;
import dev.secondsun.util.Resources;

import java.awt.image.BufferedImage;
import java.io.Externalizable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
/**
 * Projects are the root element of a scene we are modelling. They contain the references to the textures as well as the BSP tree we are going to display.
 */
public class Project implements Externalizable {

    public Project() {}
    private final Resources resources = new Resources();
    public record Resource(BufferedImage texture, Integer id){};

    private BSPTree tree = new BSPTree();
    
    public Resources getResources() {
        return resources;
    }

    public Project addResource(BufferedImage image) {
        var id = resources.setImage(image);
        return this;
    }

    public static Project load(File file) {
        try (var fs = new FileInputStream(file); var ois = new ObjectInputStream(fs)) {
            Project p = (Project)ois.readObject();
            return p;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void save(File outFile) {
        try (var fos = new FileOutputStream(outFile); var oos = new ObjectOutputStream(fos)) {
            oos.writeObject(this);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
     
    }
    @Override
    public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
        
    }

    @Override
    public void writeExternal(ObjectOutput arg0) throws IOException {
        Gson gson = new Gson();
        var json = gson.toJson(tree);
        arg0.writeObject(json);
        
        
    }

}
