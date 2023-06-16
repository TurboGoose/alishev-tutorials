package regex;

import java.util.Arrays;

public class RegexDemo {
    /*
        (abc|cde) - одна строка из множества
        [a-zA-z0-9] - символ из одной из рейнджей
        [^abc] - не символы a, b, c

        //d - цифра [0-9]
        //w - буква = [a-zA-Z]
        . - любой символ

        + - 1 или более символов
        * - 0 или более символов
        ? - 0 или 1 символ
        {2} - ровно 2 повтора предыдущего символа
        {2, } - от 2 повторов
        {2, 4} - от 2 до 4 повторов

     */

    public static void main(String[] args) {
        String str = "Hey There! So, lets go.";
        String[] split = str.split("[.,! ]+");
        System.out.println(Arrays.toString(split));

        System.out.println(str.replaceAll("[.,! ]+", " | "));
        System.out.println("asjdf;a3o4u59j;aljs90fujasjf94".replaceFirst("\\d+", "_"));
    }

    static void batchMatch(String regex, String... strings) {
        for (String str : strings) {
            System.out.println(str + " : " + str.matches(regex));
        }
    }
}
