public class Main {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }

    public void RE_To_NFA(String re) {



    }

    private String parseExpression(String expr) {
        for (int i = 0; i < expr.length() - 1; i++) {
            char ch = expr.charAt(i);
            char next = expr.charAt(i + 1);
        }
        return null;
    }

    private boolean isLetterOrNumber(char ch){
        if (( 'a'<=ch && ch<='z')||('A'<=ch && ch<='Z')||('0'<=ch && ch<='9')||ch=='.'||ch=='_') {
            return true;
        }
        return false;
    }

    private boolean isOperator(char ch) {
        if (ch == '*' || ch == '|' || ch == '(' || ch == ')') {
            return true;
        }
        return false;
    }

    public String Legalize(String expression) {
        //elimate illegal character
        StringBuilder expr = new StringBuilder();
        for(int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i) ;
            if( isLetterOrNumber(ch) || isOperator(ch) ) {
                expr.append(ch);
            }
        }
        return expr.toString();
    }
}
