import java.util.ArrayList;
import java.util.Objects;

public class Quest {
    private final String questID;
    private ArrayList<Stage> stageData = new ArrayList<>();
    private Status questStatus;
    private String desc;

    public enum Status {
        NOT_ACCEPTED, ACCEPTED, FAILED, COMPLETE, FINISHED
    }

    public Quest(String id) {
        questID = id;
        questStatus = Status.NOT_ACCEPTED;
    }
    public void createStage(String stageID){
        if (uniqueCheck(stageID)){
            stageData.add(new Stage(stageID));
        }
    }
    public void createStage(Stage stage) {
        if (uniqueCheck(stage.getID())) {
            stageData.add(stage);
        }
    }

    //Get Methods
    public String getID() {
        return questID;
    }
    public Status getStatus(){
        return questStatus;
    }
    public String getDesc() {
        return Objects.requireNonNullElse(desc, "The quest Description has not been set.");
    }

    //Stage Related Get Methods
    public int getListLength() {
        return stageData.size();
    }
    public Stage getStageByID(String stageID) {
        if (stageData != null) {
            for (Stage n : stageData) {
                if (n.getID().equals(stageID)) {
                    return n;
                }
            }
        }
        return null;
    }
    public ArrayList<Stage> getStageByStatus(boolean status) {
        ArrayList<Stage> statusStages = new ArrayList<>();
        if (stageData != null) {
            for (Stage n : stageData) {
                if (n.getStatus() == status) {
                    statusStages.add(n);
                }
            }
        }
        return statusStages;
    }
    public ArrayList<Stage> getAllStages() {
        return stageData;
    }
    public Stage getFirstIncompleteStage(){
        if (stageData != null) {
            for (Stage n : stageData) {
                if (!n.getStatus()){
                    return n;
                }
            }
        }
        return null;
    }

    //Set Methods
    public void setStatus(Status status) {
        questStatus = status;
    }
    public void setDesc(String questDescription) {
        desc = questDescription;
    }

    //
    private boolean uniqueCheck(String id) {
        for (Stage n : stageData) {
            if(n.getID().equals(id)){
                return false;
            }
        }
        return true;
    }
}
