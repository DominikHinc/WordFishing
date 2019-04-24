package pl.dominikhinc.wordfishing.service;

public class IdCalculate {
    public static int calculate (String s){
        char[] arr = s.toCharArray();
        int id = 0;
        for(char c:arr){
            id += (int) c;
        }
        return id;
    }

}
