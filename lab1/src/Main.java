/**
 * @Author: Lijie
 * @Description:
 * @Date: Create in 10:22, 2018/11/1
 */
public class Main {

    public static void main(String[] args) {
        String case1 = "s45a 4654 11.465 884sa _daf 01asd 135dsa 0.852 Description on Labs .";
        String case3 = "645 al55 78a8f 545.798 _jlkds _455.kla";
        String case2 = "Lexical Analyzer Programming The programming language is not limited";
        REParser reParser = new REParser(case1);
        reParser.parse();
    }
}
