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
    public void setDesc(String stageDescription) {
        desc = stageDescription;
    }
    public void setStatus(boolean bStatus){
        stageComplete = bStatus;
    }
    public void setXY(int x, int y) {
        markerXY.setLocation(x, y);
    }
    public void setXY(Point2D location) {
        markerXY = location;
        usesMarker = true;
    }
    public void setMarkerRadius(int radius) {
        markerRad = radius;
        usesMarker = true;
    }
    public void isMarked() {
        usesMarker = false;
    }
    public void addCounter(int max) {
        usesCounter = true;
        maxCounter = max;
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
