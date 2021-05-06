package quests;

import java.util.ArrayList;
import java.util.Objects;

/**
 * An object that represents a Quest. Allows for the creation of Quests with multiple stages,
 * assigning a Status to the object as well as setting relevant Quest information. It also
 * facilitates the addition of Stages that are stored as an Array List of Stage objects.
 */
public class Quest {
    private final String questID;
    private ArrayList<Stage> stageData = new ArrayList<>();
    private String desc;

    public enum Status {
        NOT_ACCEPTED, ACCEPTED, FAILED, COMPLETE, RESOLVED
    }
    private Status questStatus;

    /**
     * Constructor that sets a Quest objects identifier and ensures that the current status
     * is initialised as NOT_ACCEPTED.
     * @param id The String to be used as the identifier.
     */
    public Quest(String id) {
        questID = id;
        questStatus = Status.NOT_ACCEPTED;
    }

    /**
     * Creates a Stage object to be added the stageData Array List. Checks the supplied String
     * to ensure that no other Stage associated with this Quest already uses it.
     * @param stageID A specified String to be used as the ID for the Stage object.
     * @return The Stage object that was created. This will return null if not unique.
     */
    public Stage createStage(String stageID) {
        Stage created = new Stage(stageID);
        if (uniqueCheck(stageID)) {
            stageData.add(new Stage(stageID));
            return created;
        } else {
            System.out.println("Proposed stage does not have a unique ID.");
            return null;
        }
    }

    /**
     * Adds a Stage object to the stageData ArrayList. Checks the supplied String to ensure
     * that no other Stage associated with this Quest already uses it.
     * @param stage Stage object to be added to the Quest.
     * @return The Stage object that was added. This will return null if not unique.
     */
    public Stage createStage(Stage stage) {
        if (uniqueCheck(stage.getID())) {
            stageData.add(stage);
            return stage;
        } else {
            System.out.println("Proposed stage does not have a unique ID.");
            return null;
        }
    }

    //Get Methods

    /**
     * Gets the identifier associated with this Quest.
     * @return The String that this Quest is using as it's identifier.
     */
    public String getID() {
        return questID;
    }

    /**
     * Gets the current Quest Status for this Quest.
     * @return An Enum representation of the current Status.
     */
    public Status getStatus(){
        return questStatus;
    }

    /**
     * Gets the description stored for this object, if none has been set then will return
     * a specific String.
     * @return The stored description if it has been set, specific String if null.
     */
    public String getDesc() {
        return Objects.requireNonNullElse(desc, "The quest Description has not been set.");
    }

    //Stage Related Get Methods

    /**
     * Gets the number of Stage objects currently being stored.
     * @return The number of Stages stored.
     */
    public int getListLength() {
        return stageData.size();
    }

    /**
     * Returns a specific Stage object that has the supplied stageID.
     * @param stageID A specified String that will be used to search.
     * @return Stage object with matching stageID. Will return null if none found.
     */
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

    /**
     * Gets all Stage objects that share the specified Stage Status, will return null if none
     * found.
     * @param status Enum that represents the status of the Stage object.
     * @return An Array List containing all matching Stage objects found.
     */
    public ArrayList<Stage> getStageByStatus(Stage.Status status) {
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

    /**
     * Gets all Stages stored by this Quest object. Will return null if none created.
     * @return An Array List of Stage objects consisting of all current Stages stored by this object.
     */
    public ArrayList<Stage> getAllStages() {
        return stageData;
    }

    /**
     * Gets the first stage stored by this object that has a Status which is not COMPLETE. Will
     * return null if none found.
     * @return The first Stage object to be found that is not COMPLETE for this Quest.
     */
    public Stage getFirstIncompleteStage() {
        if (stageData != null) {
            for (Stage n : stageData) {
                if (n.getStatus() == Stage.Status.STARTED || n.getStatus() == Stage.Status.NOT_STARTED) {
                    return n;
                }
            }
        }
        return null;
    }

    //Set Methods

    /**
     * Sets the current Status of this Quest object.
     * @param status The Enum representation of the available Quest Status to set.
     * @return The Quest object that is being accessed.
     */
    public Quest setStatus(Status status) {
        questStatus = status;
        return this;
    }

    /**
     * Sets the description of this Quest Object.
     * @param questDescription The String to be stored as this Quests description.
     * @return The Quest object that is being accessed.
     */
    public Quest setDesc(String questDescription) {
        desc = questDescription;
        return this;
    }

    //End of Set Methods

    /**
     * For use just by Quest, will check that no Quests are currently using the supplied String
     * as their identifier.
     * @param id The String to search for.
     * @return boolean that will return false if the supplied String is found, otherwise true.
     */
    private boolean uniqueCheck(String id) {
        for (Stage n : stageData) {
            if(n.getID().equals(id)) {
                return false;
            }
        }
        return true;
    }
}
