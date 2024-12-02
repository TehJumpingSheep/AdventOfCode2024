package Solutions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

/*
 *  Day 1 and day 2 are both converted from python (see root/python) to java, I did not feel like rewriting these, so I used AI
 */
public class Day2 implements Solution {

    public void runPart1(List<String> input) {
        int sum = 0;

        for (String line : input) {
            String[] split = line.split(" ");
            List<Integer> actualArray = new ArrayList<>();
            for (String item : split) {
                if (!item.equals(" ")) {
                    actualArray.add(Integer.parseInt(item));
                }
            }

            boolean isIncreasing = true;
            boolean isDecreasing = true;
            for (int i = 0; i < actualArray.size() - 1; i++) {
                if (actualArray.get(i) >= actualArray.get(i + 1)) {
                    isIncreasing = false;
                }
                if (actualArray.get(i) <= actualArray.get(i + 1)) {
                    isDecreasing = false;
                }
            }

            if (isIncreasing || isDecreasing) {
                System.out.println(actualArray);
                List<Integer> differences = new ArrayList<>();
                for (int i = 0; i < actualArray.size() - 1; i++) {
                    differences.add(Math.abs(actualArray.get(i + 1) - actualArray.get(i)));
                }
                System.out.println(differences);
                boolean allLessThanFour = true;
                for (int diff : differences) {
                    if (diff >= 4) {
                        allLessThanFour = false;
                        break;
                    }
                }
                if (allLessThanFour) {
                    sum++;
                }
            }
        }

        System.out.println(sum);
    }

    public void runPart2(List<String> input) {
        int sum = 0;

        for (String line : input) {
            String[] split = line.split(" ");
            List<Integer> actualarray = new ArrayList<>();
            for (String item : split) {
                if (!item.trim().isEmpty()) {
                    actualarray.add(Integer.parseInt(item));
                }
            }

            if (isMonotonic(actualarray) && hasSmallDifferences(actualarray)) {
                sum++;
                continue;
            }

            for (int i = 0; i < actualarray.size(); i++) {
                List<Integer> temp = new ArrayList<>(actualarray);
                actualarray.remove(i);

                if (isMonotonic(actualarray) && hasSmallDifferences(actualarray)) {
                    sum++;
                    actualarray = temp;
                    break;
                }

                actualarray = temp;
            }
        }

        System.out.println(sum);
    }

    private static boolean isMonotonic(List<Integer> list) {
        return IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i) < list.get(i + 1))
                || IntStream.range(0, list.size() - 1).allMatch(i -> list.get(i) > list.get(i + 1));
    }

    private static boolean hasSmallDifferences(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
                .allMatch(i -> Math.abs(list.get(i + 1) - list.get(i)) < 4);
    }
}
