import java.util.ArrayList;

public class QuestSys {
    private ArrayList<Quest> questData = new ArrayList<>();

    public void createQuest(String questID) {
        if (uniqueCheck(questID)) {
            questData.add(new Quest(questID));
        }
    }
    public void createQuest(Quest quest) {
        if (uniqueCheck(quest.getID())) {
            questData.add(quest);
        }
    }

    //Get Methods
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
    public ArrayList<Quest> getQuestByStatus(Quest.Status firstStatus, Quest.Status secondStatus) {
        ArrayList<Quest> byStatus = getQuestByStatus(firstStatus);
        byStatus.addAll(getQuestByStatus(secondStatus));
        return byStatus;
    }
    public int getListLength() {
        return questData.size();
    }

    //Set Methods //if needed

    //
    private boolean uniqueCheck(String questID) {
        for (Quest n : questData) {
            if (n.getID().equals(questID)) {
                return false;
            }
        }
        return true;
    }

    public void updateQuests() {
        for (Quest q : getQuestByStatus(Quest.Status.ACCEPTED)) {
            if (q.getAllStages().size() > 0) {
                boolean cCheck = true;
                for (Stage s : q.getAllStages()) {
                    if (!s.getStatus()) {
                        cCheck = false;
                    }
                }
                if (cCheck) {
                    q.setStatus(Quest.Status.COMPLETE);
                }
            }
        }
    }
}

//TODO: events? how would I do this considering adapting/wrapping this for FXGL demo
