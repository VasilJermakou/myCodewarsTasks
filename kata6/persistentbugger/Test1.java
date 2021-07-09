package myCodewarsTasks.kata6.persistentbugger;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {

        /**
         * Write a function, persistence, that takes in a positive parameter num and returns its multiplicative persistence,
         * which is the number of times you must multiply the digits in num until you reach a single digit.
         *
         * For example:
         * persistence(39) == 3 // because 3*9 = 27, 2*7 = 14, 1*4=4
         *                      // and 4 has only one digit
         *
         * persistence(999) == 4 // because 9*9*9 = 729, 7*2*9 = 126,
         *                       // 1*2*6 = 12, and finally 1*2 = 2
         *
         * persistence(4) == 0 // because 4 is already a one-digit number
         * */

        //Test
        long testNum = 39;
        int answer = Test1.persistence(testNum);
        System.out.println("Answer = " + answer);       // Answer = 3

    }

    /* static methods */

    //1.1
    /**
     * given a positive integer produce its multiplicative persistence
     * @param n a positive number
     * @return the multiplicative persistence of n
     */
    public static int persistence(long n) {
        int answer = 0;
        int counter = 1;
        List<Long> numbers = new ArrayList<>();
        long testNum = 0;

        if(n >= 0 && n <= 9){
            counter = 0;
        }else{
            numbers = Test1.numDelimiter(n);
            testNum = numbers.stream().reduce( (accumulator, element) -> accumulator * element ).get();
            while(!(testNum >=0 && testNum < 10)){
                counter++;
                numbers = Test1.numDelimiter(testNum);
                testNum = numbers.stream().reduce( (accumulator, element) -> accumulator * element ).get();
            }
        }
        answer = counter;
        return  answer;
    }

    //1.2
    /**
     * @param num -> long num = 39,
     * @return List<Long> numbers = {9, 3};
     */
    public static List<Long> numDelimiter(long num){
        List<Long> numbers = new ArrayList<>();
        long testNum = num;
        while(!(testNum >0 && testNum < 10)){
            long left = testNum % 10;
            testNum = testNum / 10;
            numbers.add(left);
        }
        numbers.add(testNum);
        return numbers;
    }
}
