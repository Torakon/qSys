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
    public Stage getStageByID() {
        for(Stage n : stageData){
            if (n.getID().equals(questID)){
                return n;
            }
        }
        return null;
    }
    public Status getStatus(){
        return questStatus;
    }

    //Set Methods
    public void addStage(String stageID){
        stageData.add(new Stage(stageID));
    }
    public void setStatus(Status status) {
        questStatus = status;
    }
}
