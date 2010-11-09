package at.ticketline.dao.jpa;

import java.util.List;

public class Data {

    private List<Integer> input;
    
    private List<List<Integer>> output;
    
    public Data(List<Integer> in, List<List<Integer>> out) {
        this.input = in;
        this.output = out;
    }

    public List<Integer> getInput() {
        return input;
    }

    public void setInput(List<Integer> input) {
        this.input = input;
    }

    public List<List<Integer>> getOutput() {
        return output;
    }

    public void setOutput(List<List<Integer>> output) {
        this.output = output;
    }
    
    private String print(List<Integer> inp) {
        String string = "";
        for (Integer in:inp) {
            if (string.equals("")) string = in.toString();
            else string += ", " + in.toString();
        }
        return string;
    }
    
    public String printInput() {
        return print(input);
    }
    
    public String printOutput() {
        String string = "";
        for (List<Integer> list:output) {
            if (string.equals("")) string = print(list);
            else string += "\n" + print(list);
        }
        return string;
    }
}
