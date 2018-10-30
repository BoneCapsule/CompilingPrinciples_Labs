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

    Map<Character, ArrayList<State>> connection;  // 通过某个终结符能达到的所有状态

    String reg_name = "";

    public State(String id) {
        this.id = id;
        this.connection = new HashMap<>();
    }

    public ArrayList<State> getTransitions(Character c){
        if(connection.containsKey(c))
            return connection.get(c);
        else
            return new ArrayList<>();
    }

    public void addNextState(Character key,State state){
        if(connection.containsKey(key))
        {
            connection.get(key).add(state);
        }
        else{
            ArrayList<State> list = new ArrayList<>();
            list.add(state);
            connection.put(key,list);
        }
    }

}
