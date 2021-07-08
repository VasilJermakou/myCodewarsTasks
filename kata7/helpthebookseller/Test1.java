package myCodewarsTasks.kata7.helpthebookseller;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    public static void main(String[] args) {

        /**
         * A bookseller has lots of books classified in 26 categories labeled A, B, ... Z.
         * Each book has a code c of 3, 4, 5 or more characters. The 1st character of a code is a capital letter which defines the book category.
         *
         * In the bookseller's stocklist each code c is followed by a space and by a positive integer n (int n >= 0)
         * which indicates the quantity of books of this code in stock.
         *
         * For example an extract of a stocklist could be:
         * L = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"}.
         * or
         * L = ["ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"] or ....
         *
         * You will be given a stocklist (e.g. : L) and a list of categories in capital letters e.g :
         * M = {"A", "B", "C", "W"}
         * or
         * M = ["A", "B", "C", "W"] or ...
         * and your task is to find all the books of L with codes belonging to each category of M and to sum their quantity according to each category.
         *
         * For the lists L and M of example you have to return the string (in Haskell/Clojure/Racket a list of pairs):
         * (A : 20) - (B : 114) - (C : 50) - (W : 0)
         * where A, B, C, W are the categories, 20 is the sum of the unique book of category A,
         * 114 the sum corresponding to "BKWRK" and "BTSQZ", 50 corresponding to "CDXEF" and 0 to category 'W' since there are no code beginning with W.
         *
         * If L or M are empty return string is "" (Clojure and Racket should return an empty array/list instead).
         *
         * Note:
         * In the result codes and their values are in the same order as in M.
         * */

        //Test #1
        String[] L = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"};
        String[] M = {"A", "B", "C", "W"};
        String answerString = Test1.stockSummary(L, M);
        System.out.println(answerString);                   // => (A : 20) - (B : 114) - (C : 50) - (W : 0)


        //Test #2
        String[] L2 = {"ABAR 200", "CDXE 500", "BKWR 250", "BTSQ 890", "DRTY 600"};
        String[] M2 = {"A", "B"};
        answerString = Test1.stockSummary(L2, M2);          // => (A : 200) - (B : 1140)
        System.out.println(answerString);

    }

    /* static methods */

    /**
     * 1st parameter is the stocklist (L in example),
     * L = {"ABART 20", "CDXEF 50", "BKWRK 25", "BTSQZ 89", "DRTYM 60"}
     *
     * 2nd parameter is list of categories (M in example)
     * M = {"A", "B", "C", "W"}
     */
    public static String stockSummary(String[] lstOfArt, String[] lstOf1stLetter) {

        String result = null;

        Integer counter = 0;
        String articleFirstLetter = "";
        Map<String, Integer> nodes = new HashMap<>();

        if(lstOfArt.length == 0 || lstOf1stLetter.length == 0){
            result = "";
            result = result.trim();
        }else{
            for(String s : lstOf1stLetter){
                for(String art : lstOfArt){
                    articleFirstLetter = Test1.getFirstLetter(art);
                    if(s.equalsIgnoreCase(articleFirstLetter)){
                        counter += Test1.findNumberInString(art);
                    }
                }
                nodes.put(s, counter);
                counter = 0;
            }
        }

        result = Test1.createAnswerFormatString(nodes);
        result = result.trim();
        return result;
    }

    //#1.1
    public static String getFirstLetter(String word){
        String firstLetter = word.substring(0, 1);
        return firstLetter;
    }

    //#1.2
    public static Integer findNumberInString(String stringWithNumber){
        stringWithNumber = stringWithNumber.replaceAll("[^0-9]+", "");
        Integer resultNum = Integer.parseInt(stringWithNumber);
        return  resultNum;
    }

    //#1.3
    //(A : 20) - (B : 114) - (C : 50) - (W : 0)
    public static String createAnswerFormatString(Map<String, Integer> nodes){
        StringBuilder builder = new StringBuilder("");

        for(Map.Entry<String, Integer> entry :nodes.entrySet()){
            String key = entry.getKey();
            Integer value = entry.getValue();
            String pattern = "(" + key + " : " + value + ") - ";
            builder.append(pattern);
        }

        String result = builder.toString();
        result = result.substring(0, (result.length()-2));
        return result;
    }
}
