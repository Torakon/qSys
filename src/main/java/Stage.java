import java.awt.geom.Point2D;
import java.util.Objects;

public class Stage {
    private final String stageID;
    private String desc;
    private boolean stageComplete = false;
    private Point2D markerXY = new Point2D.Double(0, 0);
    private int markerRad = 0;
    private boolean usesCounter = false;
    private boolean usesMarker = false;
    private int progress = 0;
    private int maxCounter = 0;

    public Stage(String id){
        stageID = id;
    }

    //Get Methods
    public String getID(){
        return stageID;
    }
    public String getDesc() {
        if (!usesCounter) {
            return Objects.requireNonNullElse(desc, "The stage Description has not been set");
        }
        return Objects.requireNonNullElse(desc + " (" + getProgress() + "/" +getMaxCounter() + ")",
                "The stage Description has not been set");
    }
    public Point2D getXY() { return markerXY; }
    public int getMarkerRadius() {
        return markerRad;
    }
    public boolean getStatus() {
        return stageComplete;
    }
    public int getProgress() {
        return progress;
    }
    public int getMaxCounter() {
        return maxCounter;
    }
    public boolean checkMarker() { return usesMarker; }
    public boolean checkCounter() { return usesCounter; }

    //Set Methods
    public Stage setDesc(String stageDescription) {
        desc = stageDescription;
        return this;
    }
    public Stage setStatus(boolean bStatus){
        stageComplete = bStatus;
        return this;
    }
    public Stage setXY(int x, int y) {
        markerXY.setLocation(x, y);
        return this;
    }
    public Stage setXY(Point2D location) {
        markerXY = location;
        usesMarker = true;
        return this;
    }
    public Stage setMarkerRadius(int radius) {
        markerRad = radius;
        usesMarker = true;
        return this;
    }
    public void isMarked() {
        usesMarker = false;
    }
    public Stage addCounter(int max) {
        usesCounter = true;
        maxCounter = max;
        return this;
    }
    public void incrementCounter() {
        if (usesCounter) {
            progress++;
            if (progress >= maxCounter) {
                setStatus(true);
            }
        }
    }
    public void decrementCounter() {
        if (usesCounter && progress > 0) {
            progress--;
            if (progress < maxCounter && stageComplete) {
                setStatus(false);
            }
        }
    }
}
