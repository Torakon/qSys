import java.util.ArrayList;

public class Quest {
    private String questID;
    private ArrayList<Stage> stageData = new ArrayList<>();
    private Status questStatus;

    public enum Status {
        NOT_ACCEPTED, ACCEPTED, FAILED, COMPLETE
    }

    public Quest(String id) {
        questID = id;
    }

    //Get Methods
    public String getID() {
        return questID;
    }
    public Status getStatus(){
        return questStatus;
    }

    //Stage Related Get Methods
    public int getListLength() {
        return stageData.size();
    }
    public Stage getStageByID(String stageID) {
        for(Stage n : stageData){
            if (n.getID().equals(stageID)){
                return n;
            }
        }
        return null;
    }
    //Set Methods
    public void addStage(String stageID){
        stageData.add(new Stage(stageID));
    }
    public void setStatus(Status status) {
        questStatus = status;
    }
}
