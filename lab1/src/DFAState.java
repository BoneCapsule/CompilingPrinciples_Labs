import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:23
 */
public class DFAState {

    int id;

    boolean isEndState ;
    String reg_name ;

    ArrayList<State> states;

    Map<Character, DFAState> link;

    public DFAState(int id, ArrayList<State> states) {
        this.id = id;
        this.states = states;

        this.link = new HashMap<>();
        this.isEndState = false;
        for (State state : states) {
            if (state.isEndState == true) {
                this.isEndState = true;
            }
        }
    }

    public void addTransition(Character symbol, DFAState dfaState){
        this.link.put(symbol, dfaState);
    }

    public void setToEndState() {
        this.isEndState = true;
    }

    public Map<Character,DFAState> getNextDFAState(){
        return this.link;
    }

    public void print() {
        System.out.println("This DFA state ID is:" + this.id);
        System.out.println("Its states:");
        System.out.println("EndState:"+isEndState);
    }

    public void setName() {
        for (State s : states) {
            if (s.reg_name.length() > 0) {
                this.reg_name = s.reg_name;
                break;
            }
        }
    }

    public ArrayList<State> getStates() {
        return this.states;
    }
}
