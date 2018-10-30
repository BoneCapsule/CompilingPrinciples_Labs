import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:15
 */
public class State {

    String id;

    boolean isEndState;

    Map<Character, ArrayList<State>> link;  // 通过某个终结符能达到的所有状态

    String reg_name = "";

    public State(String id) {
        this.id = id;
        this.link = new HashMap<>();
    }

   public ArrayList<State> getNextStates(char c) {
        if (link.containsKey(c)) {
            return link.get(c);
        }
        return null;
   }

    public void addNextState(char c, State state){
        if(link.containsKey(c)) {
            link.get(c).add(state);
        }
        else {
            ArrayList<State> states = new ArrayList<>();
            states.add(state);
            link.put(c, states);
        }
    }

    public void print() {
        System.out.println("This State id is: " + this.id);
        System.out.println("Next State:");
        for(char c : link.keySet()){
            System.out.print(c + ", ");

            for(State s : link.get(c)) {
                System.out.print(s.id + " and ");
            }

            System.out.print("\n");
        }

    }

}
