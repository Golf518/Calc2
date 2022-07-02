


import java.util.Scanner;                             
import java.util.*;
public class Calculat{
	
    public static void main(String[] args)throws Exception{
        Scanner scanner = new Scanner(System.in);
        Main output = new Main();
        System.out.println("Input:");                   
        String numberOfArabOrRom = scanner.nextLine();           
        String answer = output.calc(numberOfArabOrRom);        


        System.out.println("Output:\n" + answer);   
        
    }
}

class Main{
    public static String calc(String input)throws Exception{
        boolean romanOrArab = false;                      
                   
        Main romanToNumber = new Main();               
        Main arabToRoman = new Main();                    
        int result = 0;                                   
        String[] arraySplit = input.split(" ");
        if (arraySplit.length != 3){
           throw new Exception("т.к. строка не является математической операцией");                             
        }
        
        Integer firstNumber = 0;
        Integer secondNumber = 0;
        try {
            firstNumber = Integer.valueOf(arraySplit[0]);
            secondNumber = Integer.valueOf(arraySplit[2]);
        } catch (NumberFormatException e) {                          
            try {
                firstNumber = romanToNumber.romanToArab(arraySplit[0]);
                secondNumber = romanToNumber.romanToArab(arraySplit[2]);
                romanOrArab = true;
            } catch (NumberFormatException ex) {                     
            	throw new Exception("т.к. используются одновременно разные системы счисления"); 
            }
        }

        if ((firstNumber < 1) || (firstNumber > 10) || (secondNumber < 1) || (secondNumber > 10)){
        	throw new Exception("т.к. в калькуляторе число должно быть меньше или равно 10");                                       
        }

        String operate = arraySplit[1];
        switch (operate) {
            case "+" -> result = firstNumber + secondNumber;
            case "-" -> result = firstNumber - secondNumber;
            case "*" -> result = firstNumber * secondNumber;
            case "/" -> result = firstNumber / secondNumber;
            default -> {
            	throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");                                     
            }
        }

        String output;                                              

        if (romanOrArab){
            if(result < 1){
            	throw new Exception("т.к. в калькуляторе число должно быть меньше или равно 10");
            } else {
                output = arabToRoman.arabToRome(result);
            }
        } else {
            output = Integer.toString(result);
            if(result<0) {
            	throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
        }

        return output;
    }

    Integer romanToArab(String romanInput){                           
        int result = 0;
        int[] arab = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        String[] roman = {"X", "IX", "VIII", "VII", "VI","V", "IV", "III", "II", "I"};
        for (int i = 0; i < arab.length; i++ ) {
            while (romanInput.indexOf(roman[i]) == 0) {
                result += arab[i];
                romanInput = romanInput.substring(roman[i].length());  
            }
        }

        return result;
    }

    String arabToRome(int arabInput){                                 
        String result = "";
        int value = 0;
        int[] arab = {100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        for (int i = 0; i < arab.length; i++){
            value = arabInput / arab[i];
            for (int j = 0; j < value; j++){
                result = result.concat(roman[i]);
            }
            arabInput = arabInput % arab[i];
        }
    return result;
    }
}