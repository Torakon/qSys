import java.util.ArrayList;

public class QuestSys {
    private ArrayList<Quest> questData = new ArrayList<Quest>();

    public void createQuest(String questID) {
        Quest questObj = new Quest(questID);
        questData.add(questObj);
    }

    public int getListLength(){
        return questData.size();
    }
}
