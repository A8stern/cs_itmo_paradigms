package expression;

import java.util.ArrayList;
import java.util.Objects;

public class MainExpression {
    final String line;

    public MainExpression(String line) {
        this.line = line;
    }
    public static String FindFullNumber (String line, int CurrIndex){
        int StartIndex = CurrIndex;
        char CurrElem = line.charAt(CurrIndex);
        int NumberOfElements = 1;
        while (CurrElem <= '9' && CurrElem >= '0' && CurrIndex < line.length() - 1){
            CurrIndex++;
            NumberOfElements++;
            CurrElem = line.charAt(CurrIndex);
        }
        if (CurrElem <= '9' && CurrElem >= '0'){
            return line.substring(StartIndex, StartIndex + NumberOfElements);
        } else {
            return line.substring(StartIndex, StartIndex + NumberOfElements - 1);
        }
    }
    public static InterfaceCreate parse(String line) {
        //System.err.println(line);
        ArrayList<LiteralsAndNums<String>> al = FindLiterals(line);
        if (al.size() == 0){
            return null;
        }
        LiteralsBuffer buf = new LiteralsBuffer(al);
        return thiLevel(buf);
    }

    public static InterfaceCreate thiLevel(LiteralsBuffer buf){
        InterfaceCreate res = secLevel(buf);
        while (true){
            buf.next();
            int curr = buf.getIndex();
            Literals lit = buf.getType(curr - 1);
            switch (lit) {
                case Sum -> res = new Add(res, secLevel(buf));
                case Sub -> res = new Subtract(res, secLevel(buf));
                default -> {
                    buf.getBack();
                    return res;
                }
            }
        }
    }
    public static InterfaceCreate secLevel(LiteralsBuffer buf){
        InterfaceCreate res = firLevel(buf);
        while (true){
            buf.next();
            int curr = buf.getIndex();
            Literals lit = buf.getType(curr - 1);
            switch (lit) {
                case Mul -> res = new Multiply(res, firLevel(buf));
                case Div -> res = new Divide(res, firLevel(buf));
                default -> {
                    buf.getBack();
                    return res;
                }
            }
        }
    }
    public static InterfaceCreate firLevel(LiteralsBuffer buf){
        String temp = buf.next();
        int curr = buf.getIndex();
        Literals lit = buf.getType(curr - 1);
        switch (lit){
            case Num:
                if (Objects.equals(Integer.valueOf(temp), Integer.MIN_VALUE)){
                    return new Const(Integer.MIN_VALUE);
                }
                return new Const(Integer.parseInt(temp));
            case Const:
                return new Variable(temp);
            case UnMin:
                return new UnaryMinus(firLevel(buf));
            case Left_Bracket:
                InterfaceCreate res = thiLevel(buf);
                buf.next();
                return res;
            default:
                return null;
        }
    }

    private static ArrayList<LiteralsAndNums<String>> FindLiterals(String line){
        ArrayList<LiteralsAndNums<String>> ListOfLiterals = new ArrayList<>();
        int lineLength = line.length();
        int CurrIndex = 0;
        char CurrElem;
        boolean wasMinus = false;
        while (CurrIndex < lineLength){
            CurrElem = line.charAt(CurrIndex);
            switch (CurrElem){
                case '(':
                    ListOfLiterals.add(new LiteralsAndNums<>("(", Literals.Left_Bracket));
                    CurrIndex++;
                    break;
                case ')':
                    ListOfLiterals.add(new LiteralsAndNums<>(")", Literals.Right_Bracket));
                    CurrIndex++;
                    break;
                case '+':
                    ListOfLiterals.add(new LiteralsAndNums<>("+", Literals.Sum));
                    CurrIndex++;
                    break;
                case '-':
                    char nextElem = line.charAt(CurrIndex + 1);
                    if (ListOfLiterals.size() == 0){
                        if (nextElem <= '9' && nextElem >= '0'){
                            wasMinus = true;
                        } else {
                            ListOfLiterals.add(new LiteralsAndNums<>("-", Literals.UnMin));
                        }
                        CurrIndex++;
                        break;
                    } else {
                        Literals temp = ListOfLiterals.get(ListOfLiterals.size() - 1).getType();
                        switch (temp){
                            case Left_Bracket, Sum, Sub, Mul, Div, UnMin:
                                if (nextElem <= '9' && nextElem >= '0'){
                                    wasMinus = true;
                                } else {
                                    ListOfLiterals.add(new LiteralsAndNums<>("-", Literals.UnMin));
                                }
                                break;
                            default:
                                ListOfLiterals.add(new LiteralsAndNums<>("-", Literals.Sub));
                                break;
                        }
                    }
                    CurrIndex++;
                    break;
                case '*':
                    ListOfLiterals.add(new LiteralsAndNums<>("*", Literals.Mul));
                    CurrIndex++;
                    break;
                case '/':
                    ListOfLiterals.add(new LiteralsAndNums<>("/", Literals.Div));
                    CurrIndex++;
                    break;
                case 'x':
                    ListOfLiterals.add(new LiteralsAndNums<>("x", Literals.Const));
                    CurrIndex++;
                    break;
                case 'y':
                    ListOfLiterals.add(new LiteralsAndNums<>("y", Literals.Const));
                    CurrIndex++;
                    break;
                case 'z':
                    ListOfLiterals.add(new LiteralsAndNums<>("z", Literals.Const));
                    CurrIndex++;
                    break;
                default:
                    if (CurrElem <= '9' && CurrElem >= '0'){
                        String value = FindFullNumber(line, CurrIndex);
                        CurrIndex += value.length();
                        if (wasMinus) {
                            //ListOfLiterals.add(new LiteralsAndNums<>("(", Literals.Left_Bracket));
                            ListOfLiterals.add(new LiteralsAndNums<>(("-" + value), Literals.Num));
                            //ListOfLiterals.add(new LiteralsAndNums<>(")", Literals.Right_Bracket));
                            wasMinus = false;
                        } else{
                            ListOfLiterals.add(new LiteralsAndNums<>(value, Literals.Num));
                        }
                    } else{
                        CurrIndex++;
                    }
                    break;
            }
        }
        //ListOfLiterals.add((new LiteralsAndNums("", Literals.End)));
        return ListOfLiterals;
    }

}