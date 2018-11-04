/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 2018/10/30 16:33
 */
public class REParser {

    private NFA nfa;

    private DFA dfa;

    private String expression;

    public REParser(String expression) {
        this.expression = expression;
    }

    public void parse() {
        REToNFA nfaParser = new REToNFA();
        NFAToDFA dfaParser = new NFAToDFA();

        // 使用thompson算法构成NFA
        State s0 = new State(0);
        NFA nfa = new NFA();
        nfa.getStates().addFirst(s0);

        String regular;
        String regName;
        for (int i = 0; i < RE.tag.length; i++) {
            regular = RE.tag[i];
            regName = RE.type[i];
            NFA newNfa = nfaParser.RE_To_NFA(regular, regName);
            s0.addNextState('ε', newNfa.getStates().getFirst());
            for(State s : newNfa.getStates()) {
                nfa.getStates().add(s);
            }
        }
        this.setNfa(nfa);

        // 使用子集构造法构造DFA
        DFA newDFA = dfaParser.NFA_To_DFA(this.getNfa());
        this.setDfa(newDFA);
        String splitor = " "; // 分隔符
        String[] testCase = this.expression.split(splitor);
        for (String str : testCase) {
            System.out.println( "Token: <" + ErrorHandling.output(getDfa(), str) + ">" ) ;
        }
    }


    public DFA getDfa() {
        return dfa;
    }

    public void setDfa(DFA dfa) {
        this.dfa = dfa;
    }

    public NFA getNfa() {
        return nfa;
    }

    public void setNfa(NFA nfa) {
        this.nfa = nfa;
    }
}
