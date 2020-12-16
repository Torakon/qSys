import java.util.ArrayList;
import java.util.Objects;

public class Quest {
    private final String questID;
    private ArrayList<Stage> stageData = new ArrayList<>();
    private Status questStatus;
    private String desc;

    public enum Status {
        NOT_ACCEPTED, ACCEPTED, FAILED, COMPLETE, RESOLVED
    }

    public Quest(String id) {
        questID = id;
        questStatus = Status.NOT_ACCEPTED;
    }
    public Quest createStage(String stageID) {
        if (uniqueCheck(stageID)) {
            stageData.add(new Stage(stageID));
        }
        return this;
    }
    public Quest createStage(Stage stage) {
        if (uniqueCheck(stage.getID())) {
            stageData.add(stage);
        }
        return this;
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
    public Stage getFirstIncompleteStage() {
        if (stageData != null) {
            for (Stage n : stageData) {
                if (!n.getStatus()) {
                    return n;
                }
            }
        }
        return null;
    }

    //Set Methods
    public Quest setStatus(Status status) {
        questStatus = status;
        return this;
    }
    public Quest setDesc(String questDescription) {
        desc = questDescription;
        return this;
    }

    //
    private boolean uniqueCheck(String id) {
        for (Stage n : stageData) {
            if(n.getID().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
