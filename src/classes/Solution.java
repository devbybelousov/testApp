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
            numberA = stringToInt(a);
            numberB = stringToInt(b);
        }
        else if (!isNumber(a) && !isNumber(b)) {
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
        System.out.println("Решение: " + result);
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

    public int romanToArab(String input){
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
        return result;
    }


}
