import java.util.*;

/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 17:17, 2018/10/30
 */
public class NFAToDFA {

    private static HashSet<Character> symbol;

    public NFAToDFA() {
        symbol = new HashSet<>();
        symbol.add('N');
        symbol.add('L');
        symbol.add('.');
    }

    public DFA NFA_To_DFA(NFA nfa) {
        int stateId = 0 ;
        DFA dfa = new DFA();
        LinkedList<State> states = nfa.getStates();
        State start = states.getFirst();
        Set<State> set1 = new HashSet<>();
        set1.add(start);
        Set<State> set2 = e_closure(set1);

        DFAState dfaStart = new DFAState(set2, stateId++);
        dfa.getDFAStates().addLast(dfaStart);
        LinkedList<DFAState> dfaStates = new LinkedList<>();  // 不能到达的
        dfaStates.addLast(dfaStart);

        while (!dfaStates.isEmpty()) {
            DFAState dfaState = dfaStates.removeLast();
            for (char ch : symbol) {
                set1 = new HashSet<>();
                set2 = new HashSet<>();
                move (dfaState, ch, set1);
                set2 = e_closure(set1) ;

                boolean isFound = false;
                DFAState dfaState1 = null;
                for (int i = 0 ; i < dfa.getDFAStates().size(); i++) {
                    dfaState1 = dfa.getDFAStates().get(i);
                    if (dfaState1.getStates().containsAll(set2)) {
                        isFound = true;
                        break;
                    }
                }

                // 如果不在DFA集合里，添加到新DFAState
                if (!isFound) {
                    DFAState newDfaState = new DFAState(set2, stateId++);
                    newDfaState.setName();
                    dfaStates.addLast(newDfaState);
                    dfa.getDFAStates().addLast(newDfaState);
                    dfaState.addTransition(ch, newDfaState);
                }
                else {
                    dfaState.addTransition(ch,dfaState1);
                }
            }
        }
        return dfa;
    }

    public void move(DFAState dfaState, char ch, Set<State> set){
        ArrayList<State> stateList = new ArrayList<>();
        Set<State> states = dfaState.getStates();
        for (State s : states) {
            stateList.add(s);
        }
        for (State state : stateList) {
            ArrayList<State> list = state.getLinkStates(ch);
            for(State s : list) {
                set.add(s);
            }
        }
    }


    public Set<State> e_closure(Set<State> set) {
        Set<State> result = set;
        Stack<State> stack = new Stack<>();
        for(State s : set) {
            stack.push(s) ;
        }

        while(!stack.empty()){
            State s = stack.pop();
            ArrayList<State> epsilonStates = s.searchEpsilon();
            for (State s1 : epsilonStates) {
                if (!result.contains(s1)) {
                    result.add(s1);
                    stack.push(s1);
                }
            }
        }
        return result;
    }
}
