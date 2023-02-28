import java.util.Scanner;



class Main {public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Input: ");
    String input = scanner.nextLine();
    System.out.println(calc(input.toUpperCase())); //передаем строку
    //в верхнем регистре в метод calc
}

    public static String calc(String input) throws Exception {
        int num1;
        int num2;
        String oper;
        String result;
        boolean isRoman;
        String[] operands = input.split("[+\\-*/]"); //делим строку на массив
        //операндов по знаку и проверяем на количество операндов
        if (operands.length != 2) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        oper = detectOperation(input); //проверяем на наличие нужного нам оператора
        //если такого не находим - вбрасываем исключение
        if (oper == null) throw new Exception("т.к. формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        //если оба числа римские
        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
            //конвертируем оба числа в арабские для вычесления действия
            num1 = Roman.convertToArabian(operands[0]);
            num2 = Roman.convertToArabian(operands[1]);
            isRoman = true;
        }
        //если оба числа арабские
        else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
            num1 = Integer.parseInt(operands[0]);
            num2 = Integer.parseInt(operands[1]);
            isRoman = false;
        }
        //если одни число римское, а другое - арабское
        else {
            throw new Exception("throws Exception //т.к. используются одновременно разные системы счисления");
        }
        if (num1 > 10 || num2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10");
        }
        int arabian = calc(num1, num2, oper);
        if (isRoman) {
            //если римское число получилось меньше либо равно нулю, генерируем ошибку
            if (arabian <= 0) {
                throw new Exception("т.к. в римской системе нет отрицательных чисел");
            }
            //конвертируем результат операции из арабского в римское
            result = Roman.convertToRoman(arabian);
        } else {
            //Конвертируем арабское число в тип String
            result = String.valueOf(arabian);
        }
        //возвращаем результат
        return result;
    }
//создаем метод для определения операции
    static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else if (expression.contains("-")) return "-";
        else if (expression.contains("*")) return "*";
        else if (expression.contains("/")) return "/";
        else return null; // знак в строке не подходит в наше условие - возвращаем null
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
        else return a / b;
    }

}

class Roman {//массив от 0 до 100, где i соответствует арабскому значению римской цифры
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI",
            "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX", "XXI", "XXII", "XXIII", "XXIV",
            "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI",
            "XXXVII", "XXXVIII", "XXXIX", "XL", "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII",
            "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX", "LXI", "LXII",
            "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX", "LXXI", "LXXII", "LXXIII", "LXXIV",
            "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX", "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV",
            "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC", "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII",
            "XCVIII", "XCIX", "C"};

//проверяем полученные строки на соответствие числам в массиве,
    //проходимся по массиву, пока не совпадет c одним из знаков массива

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;// иначе возвращаем ошибку
    }
//по схожему принципу производим конвертацию, т.е. сравниваем наше римское число с массивом
    //начиная с нуля и прибавляя 1 к i на каждом шаге
    //т.о. в конце получим i эквивалентное нужному арабскому числу
    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }

}
