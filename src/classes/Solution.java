package classes;

import enumeration.RomanNumber;
import exception.MyException;

import java.util.List;
import java.util.Scanner;

public class Solution {

    private String a;
    private String b;
    private String operation;
    private int numberA;
    private int numberB;
    private boolean isArab;

    public Solution() {
    }

    public void input(){
        System.out.print("Введите ваше выражение: ");
        Scanner scanner = new Scanner(System.in);
        a = scanner.next();
        operation = scanner.next();
        b = scanner.next();
    }

    public void check() throws MyException {
        if (isNumber(a) && isNumber(b)) {
            isArab = true;
            numberA = stringToInt(a);
            numberB = stringToInt(b);
        }
        else if (!isNumber(a) && !isNumber(b)) {
            isArab = false;
            numberA = romanToArab(a);
            numberB = romanToArab(b);
        }
        else throw new MyException("Ошибка ввода чисел! Нельзя вводить и римские и арабские числа!");
    }

    public void calculation() throws MyException {
        int result = 0;
        switch (operation){
            case "+":
                result = numberA + numberB;
                break;
            case "*":
                result = numberA * numberB;
                break;
            case "/":
                result = numberA / numberB;
                break;
            case "-":
                result = numberA - numberB;
                break;
            default:
                throw new MyException("Ошибка! Неправильно введена операция над числами!");

        }
        if (isArab) System.out.println("Решение: " + result);
        else System.out.println("Решение: " + arabToRoman(result));
    }

    public boolean isNumber(String en) throws MyException {
        boolean flag = false;
        for (char c : en.toCharArray()){
            if (flag && !Character.isDigit(c)) throw new MyException("Ошибка ввода чисел!");
            flag = Character.isDigit(c);
        }
        return flag;
    }

    public int stringToInt(String input) throws MyException {
        int result = Integer.parseInt(input);
        if (result > 10 || result < 1) throw new MyException("Ошибка! Число не входит в заданный промежуток! ");
        return result;
    }

    public int romanToArab(String input) throws MyException {
        int result = 0;
        input = input.toUpperCase();

        List<RomanNumber> romanList = RomanNumber.getReverseSortedValues();

        int i = 0;

        while (input.length() > 0 && i < romanList.size()){
            RomanNumber romanNumber = romanList.get(i);
            if (input.startsWith(romanNumber.name())){
                result += romanNumber.getValue();
                input = input.substring(romanNumber.name().length());
            }else i++;
        }

        if (input.length() > 0) throw new IllegalArgumentException("Ошибка конвертации римского числа!");
        if (result < 0 || result > 10) throw new MyException("Ошибка! Число не входит в заданный промежуток! ");
        return result;
    }

    public String arabToRoman(int input){
        List<RomanNumber> romanNumerals = RomanNumber.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((input > 0) && (i < romanNumerals.size())) {
            RomanNumber currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= input) {
                sb.append(currentSymbol.name());
                input -= currentSymbol.getValue();
            } else {
                i++;
            }
        }
        return sb.toString();
    }


}
