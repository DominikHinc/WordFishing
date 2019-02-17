package pl.dominikhinc.wordfishing.service;

public class SplitText {


    public String splitText(int border, String givenText){
        String resault = null;
        String[] shownText_temp =  new String[2];
        char[] answerArray = givenText.toCharArray();
        String tempor = givenText.substring(border,givenText.length());
        if(givenText.contains(" ")){
            int toBack = border;
            int toFront = border;
            while(answerArray[toBack] != ' '  && (toBack > 0)){
                toBack--;
            }
            if(tempor.contains(" ")){
                while(answerArray[toFront] != ' ' &&  toFront < givenText.length()-1){
                    toFront++;
                }
            }else{
                toFront = 99;
            }

            toBack = border - toBack;
            toFront = toFront - border;
            if(toBack <= toFront){
                shownText_temp[0] = givenText.substring(0,border-toBack);
                shownText_temp[1] = givenText.substring(border-toBack ,givenText.length());
                resault = shownText_temp[0] +"\n"+ shownText_temp[1];
            }
            if(toFront < toBack){
                shownText_temp[0] = givenText.substring(0,toFront+border);
                shownText_temp[1] = givenText.substring(toFront+border ,givenText.length());
                resault = shownText_temp[0] +"\n"+ shownText_temp[1];
            }

        }else{
            shownText_temp[0] = givenText.substring(0,border+1);
            shownText_temp[1] = givenText.substring(border+1 ,givenText.length());
            resault = shownText_temp[0]+ "-" +"\n"+ shownText_temp[1];
        }


        return resault;
    }

}
