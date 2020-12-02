import java.awt.geom.Point2D;

public class Stage {
    private String stageID;
    private boolean stageComplete = false;
    private Point2D markerXY = new Point2D.Double(0, 0);
    private int markerRad = 0;

    public Stage(String id){
        stageID = id;
    }

    //Get Methods
    public String getID(){
        return stageID;
    }
    public Point2D getXY() { return markerXY; }
    public int getMarkerRadius() {
        return markerRad;
    }
    public boolean getStatus() {
        return stageComplete;
    }

    //Set Methods
    public void setStatus(boolean bStatus){
        stageComplete = bStatus;
    }
    public void setXY(int x, int y) {
        markerXY.setLocation(x, y);
    }
    public void setXY(Point2D XY){
        markerXY = XY;
    }
    public void setMarkerRadius(int rad) {
        markerRad = rad;
    }
    //add Component System for different quest types?
}
