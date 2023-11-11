```java
package world3d;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.SceneGraphObject;
import javax.vecmath.Point3d;
import com.sun.j3d.utils.universe.SimpleUniverse;

public class WorldRenderer {

    private Canvas3D canvas3D;
    private SimpleUniverse universe;
    private BranchGroup sceneRoot;

    public WorldRenderer() {
        // Setup the canvas for rendering
        canvas3D = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        // Create a SimpleUniverse with the canvas
        universe = new SimpleUniverse(canvas3D);
        // Create the root of the branch graph
        sceneRoot = new BranchGroup();
        sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
        sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
        sceneRoot.setCapability(BranchGroup.ALLOW_DETACH);
    }

    public Canvas3D getCanvas3D() {
        return canvas3D;
    }

    public void setViewpoint(Point3d point) {
        universe.getViewingPlatform().setNominalViewingTransform();
        universe.getViewer().getView().setBackClipDistance(100.0);
        universe.getViewer().getView().setFrontClipDistance(0.1);
    }

    public void addSceneObject(SceneGraphObject sceneObject) {
        sceneRoot.addChild(sceneObject);
    }

    public void renderWorld() {
        // Compile the scene graph to optimize it
        sceneRoot.compile();
        // Add the scene to the universe
        universe.addBranchGraph(sceneRoot);
    }

    public void detachWorld() {
        sceneRoot.detach();
    }

    // Main method to test the WorldRenderer
    public static void main(String[] args) {
        WorldRenderer worldRenderer = new WorldRenderer();
        // Add objects to the scene here using worldRenderer.addSceneObject(...)
        // Set the viewpoint or other properties if necessary
        // Finally, render the world
        worldRenderer.renderWorld();
    }
}
```