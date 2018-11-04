import java.util.HashMap;
import java.util.Set;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 15:24, 2018/10/30
 */
public class DFAState {

    private int id;
    private Set<State> states;
    private HashMap<Character,DFAState> link;

    private boolean isEndState ;
    private String tagName;

    public DFAState(Set<State> states, int id){
        this.id = id;
        this.states = states;
        this.link = new HashMap<>();
        this.isEndState = false;
        for(State s : states){
            if(s.isEndState()) {
                this.isEndState = true;
                break;
            }
        }
    }

    public Set<State> getStates(){
        return this.states ;
    }

    public void addTransition(Character symbol,DFAState dfaState){
        this.link.put(symbol,dfaState);
    }

    public void setToEndState() {
        this.isEndState = true;
    }

    public HashMap<Character, DFAState> getNextDFAState(){
        return this.link;
    }


    public void setName() {
        for(State  s : states) {
            if(s.getTagName().length() > 0) {
                this.tagName = s.getTagName();
                break;
            }
        }
    }

    public String getTagName(){
        return this.tagName;
    }

    public boolean isEndState() {
        return isEndState;
    }

    public void setEndState(boolean endState) {
        isEndState = endState;
    }
}
