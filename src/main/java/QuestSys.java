
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
    public Quest getQuestByID(String questID){
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
    public Quest getQuestByIndex(int index) { //needed? - removal pending
        return questData.get(index);
    }
    public int getListLength() {
        return questData.size();
    }
    public String getQuestID(int index) {
        return questData.get(index).getID();
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

}
