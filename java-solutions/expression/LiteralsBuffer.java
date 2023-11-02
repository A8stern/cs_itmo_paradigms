package expression;

import java.util.ArrayList;

public class LiteralsBuffer {
    private ArrayList<LiteralsAndNums<String>> list;

    private int index;
    private int len;

    public LiteralsBuffer(ArrayList<LiteralsAndNums<String>> list){
        this.list = list;
        this.index = 0;
        this.len = list.size();
    }

    public String next(){
        if (index < len){
            index++;
            return list.get(index - 1).toString();
        } else {
            return "Error";
        }
    }

    public Literals getType(int n){
        return list.get(n).getType();
    }

    public int getIndex(){
        return index;
    }

    public boolean hasNext(){
        if (index < len){
            return true;
        }
        return false;
    }

    public void getBack(){
        index--;
    }
}
