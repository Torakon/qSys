
import java.util.ArrayList;

public class QuestSys {
    private ArrayList<Quest> questData = new ArrayList<>();

    public void createQuest(String questID) {
        questData.add(new Quest(questID));
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
        ArrayList<Quest> statusQuests = new ArrayList<>();
        if (questData != null) {
            for (Quest n : questData) {
                if (n.getStatus() == status) {
                    statusQuests.add(n);
                }
            }
        }
        return statusQuests;
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

}
