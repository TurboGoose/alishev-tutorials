package regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternMatcherDemo {
    public static void main(String[] args) {
        String text = "Hi dude, my email is bebrik@gmail.com. Yoo! Mine is whoami@mail.com";
        Pattern pattern = Pattern.compile("(\\w+)@((g?)mail)\\.(com|ru)");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            System.out.println(matcher.group(3));
            System.out.println(matcher.group());
        }
    }
}
