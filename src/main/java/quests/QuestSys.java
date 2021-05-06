package quests;

import java.util.ArrayList;

/**
 * A class designed to keep track of current quests and allow the developer to retrieve the quest
 * object relevant to their input either by ID, all of a current status or all currently tracked.
 *
 * Quests are stored in an Array List 'questData' of Quest objects and require a unique ID in
 * order to be added. This is so that the developer can be sure they are retrieving the correct
 * Quest object instead of the first one found with the specified ID.
 */
public class QuestSys {
    private ArrayList<Quest> questData = new ArrayList<>();

    /**
     * Create a new quest to be tracked and added to the Array List with just an ID specified,
     * Checks that the supplied questID is not already in use by another stored quest.
     * @param questID A specified String to be used as the ID for the Quest object.
     * @return The Quest object that was created. This will return null if not unique.
     */
    public Quest createQuest(String questID) {
        Quest created = new Quest(questID);
        if (uniqueCheck(questID)) {
            questData.add(created);
            return created;
        } else {
            System.out.println("Proposed quest does not have a unique ID.");
            return null;
        }
    }

    /**
     * Add a new Quest object to be tracked and added to the Array List. Checks that the
     * supplied Quest object uses an ID that is not already in use.
     * @param quest Quest object to be added and tracked.
     * @return The Quest object that was added. This will return null if not unique.
     */
    public Quest createQuest(Quest quest) {
        if (uniqueCheck(quest.getID())) {
            questData.add(quest);
            return quest;
        } else {
            System.out.println("Proposed quest does not have a unique ID.");
            return null;
        }
    }

    //Get Methods

    /**
     * Returns a specific Quest object that has the supplied questID.
     * @param questID A specified String that will be used to search.
     * @return Quest object with matching questID. Will return null if none found.
     */
    public Quest getQuestByID(String questID) {
        if (questData != null) {
            for (Quest n : questData) {
                if (n.getID().equals(questID)) {
                    return n;
                }
            }
        }
        return null;
    }

    /**
     * Gets all Quest objects that share the specified Quest Status, will return null if none
     * found.
     * @param status Enum that represents the status of the Quest object.
     * @return An Array List containing all matching Quest objects found.
     */
    public ArrayList<Quest> getQuestByStatus(Quest.Status status) {
        ArrayList<Quest> byStatus = new ArrayList<>();
        if (questData != null) {
            for (Quest n : questData) {
                if (n.getStatus() == status) {
                    byStatus.add(n);
                }
            }
        }
        return byStatus;
    }

    /**
     * Gets all Quest objects that share the specified Quest Statuses (two), will return null if
     * none found.
     * @param firstStatus The first status to search for.
     * @param secondStatus The second status to search for.
     * @return An Array List containing all matching Quest objects found.
     */
    public ArrayList<Quest> getQuestByStatus(Quest.Status firstStatus, Quest.Status secondStatus) {
        ArrayList<Quest> byStatus = getQuestByStatus(firstStatus);
        byStatus.addAll(getQuestByStatus(secondStatus));
        return byStatus;
    }

    /**
     * Gets all Quest objects.
     * @return An Array List containing all currently tracked Quest objects.
     */
    public ArrayList<Quest> getAllQuests() {
        return questData;
    }

    /**
     * Gets the number of Quest objects currently being stored.
     * @return The number of Quests stored.
     */
    public int getListLength() {
        return questData.size();
    }

    //End of Get Methods

    /**
     * For use just by QuestSys, will check that no Quests are currently using the supplied String
     * as their identifier.
     * @param questID The String to search for
     * @return boolean that will return false if the supplied String is found, otherwise true.
     */
    private boolean uniqueCheck(String questID) {
        for (Quest n : questData) {
            if (n.getID().equals(questID)) {
                return false;
            }
        }
        return true;
    }

    /**
     * For any Quest object that has the status of ACCEPTED, this method will check all attached
     * Stages. Should all stages be marked as FINISHED then the Quest object shall be marked as
     * COMPLETE. If the first available Stage is not marked as either FINISHED or STARTED then
     * this Stage will be changed to STARTED.
     */
    public void updateQuests() {
        for (Quest q : getQuestByStatus(Quest.Status.ACCEPTED)) {
            if (q.getAllStages().size() > 0) {
                boolean cCheck = true;
                for (Stage s : q.getAllStages()) {
                    if (s.getStatus() != Stage.Status.FINISHED) {
                        cCheck = false;
                        if (s.getStatus() != Stage.Status.STARTED) {
                            s.setStatus(Stage.Status.STARTED);
                        }
                        break;
                    }
                }
                if (cCheck) {
                    q.setStatus(Quest.Status.COMPLETE);
                }
            }
        }
    }
}
