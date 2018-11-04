import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:15
 */
public class State {

    private char epsilon;

    private int id;

    private HashMap<Character,ArrayList<State>> link;

    private boolean isEndState;

    private String tagName;

    public State(int id){
        this.id = id;
        this.link = new HashMap<>();
        tagName = "";
        epsilon = 'ε';
    }

    public ArrayList<State> getLinkStates(char c){
        if(link.containsKey(c)) {
            return link.get(c);
        }
        return new ArrayList<>();
    }

    // 添加下一状态
    public void addNextState(char key, State state){
        if(link.containsKey(key)) {
            link.get(key).add(state);
        }
        else{
            ArrayList<State> list = new ArrayList<>();
            list.add(state);
            link.put(key,list);
        }
    }

    public void setToEndState() {
        this.isEndState = true;
    }

    // 寻找ε达到的所有状态
    public ArrayList<State> searchEpsilon(){
        if(link.containsKey(epsilon)) {
            return link.get(epsilon) ;
        }
        return new ArrayList<>();
    }

    public void print(){
        System.out.println("This State id is: " + this.id);
        System.out.println("The next State: ");
        for(char c: link.keySet()){
            System.out.print(c + ", ");

            for(State s: link.get(c)) {
                System.out.print(s.id + " and ");
            }
            System.out.print("\n");
        }

    }

    public void set_name(String name){
        this.tagName =name;
    }

    public String getTagName(){
        return this.tagName;
    }

    public boolean isEndState() {
        return isEndState;
    }


}
