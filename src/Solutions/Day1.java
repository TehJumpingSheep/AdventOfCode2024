package Solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 *  Day 1 and day 2 are both converted from python (see root/python) to java, I did not feel like rewriting these, so I used AI
 */
public class Day1 implements Solution {

    public void runPart1(List<String> input) {
        int sum = 0;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (String line : input) {
            String[] parts = line.split(" ");
            list1.add(Integer.parseInt(parts[0]));
            list2.add(Integer.parseInt(parts[3]));
        }

        Collections.sort(list1);
        Collections.sort(list2);

        for (int idx = 0; idx < list1.size(); idx++) {
            sum += Math.abs((list1.get(idx)) - list2.get(idx));
        }

        System.out.println(sum);
    }

    public void runPart2(List<String> input) {
        int sum = 0;

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        for (String line : input) {
            String[] parts = line.split(" ");
            list1.add(Integer.parseInt(parts[0]));
            list2.add(Integer.parseInt(parts[3]));
        }

        for (int item : list1) {
            sum += Collections.frequency(list2, item) * item;
        }

        System.out.println(sum);
    }
}
