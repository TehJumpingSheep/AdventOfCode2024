package Solutions;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 implements Solution {

    public void runPart1(List<String> input) {
        Pattern pattern = Pattern.compile("(mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\))");
        int sum = 0;

        for (String string : input) {
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                String[] split = matcher.group().replace("mul(", "").split("[,\\)]");
                for (int i = 0; i < split.length; i += 2) {
                    sum += Integer.parseInt(split[i]) * Integer.parseInt(split[i + 1]);
                }
            }
        }

        System.out.println(sum);
    }

    public void runPart2(List<String> input) {
        Pattern pattern = Pattern.compile("(mul\\([1-9][0-9]{0,2},[1-9][0-9]{0,2}\\)|do\\(\\)|don't\\(\\))");
        int sum = 0;

        boolean shouldAdd = true;

        for (String string : input) {
            Matcher matcher = pattern.matcher(string);

            while (matcher.find()) {
                if(matcher.group().equals("do()")){
                    shouldAdd = true;
                } else if(matcher.group().equals("don't()")) {
                    shouldAdd = false;
                } else if(shouldAdd){
                    String[] split = matcher.group().replace("mul(", "").split("[,\\)]");
                    for (int i = 0; i < split.length; i += 2) {
                        sum += Integer.parseInt(split[i]) * Integer.parseInt(split[i + 1]);
                    }
                }
            }
        }

        System.out.println(sum);
    }
}
