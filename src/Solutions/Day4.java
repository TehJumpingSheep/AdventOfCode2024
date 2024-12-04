package Solutions;

import java.util.List;

public class Day4 implements Solution {

    public void runPart1(List<String> input) {
        int sum = 0;

        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {

                String horizontal = "";
                String vertical = "";
                String diagonalLeftUp = "";
                String diagonalRightUp = "";

                try {
                    for (int k = 0; k < 4; k++) {
                        horizontal += line.charAt(j + k);
                    }

                    if (horizontal.equals("XMAS")){
                        sum++;
                    }

                    if(reverseString(horizontal).equals("XMAS")){
                        sum++;
                    }
                } catch (Exception ignored){}

                try {
                    for (int k = 0; k < 4; k++) {
                        vertical += input.get(i + k).charAt(j);
                    }

                    if (vertical.equals("XMAS")){
                        sum++;
                    }

                    if(reverseString(vertical).equals("XMAS")){
                        sum++;
                    }
                } catch (Exception ignored){}

                try {
                    for (int k = 0; k < 4; k++) {
                        diagonalLeftUp += input.get(i - k).charAt(j - k);
                    }

                    if(diagonalLeftUp.equals("XMAS")){
                        sum++;
                    }

                    if(reverseString(diagonalLeftUp).equals("XMAS")){
                        sum++;
                    }
                } catch (Exception ignored) {}

                try {
                    for (int k = 0; k < 4; k++) {
                        diagonalRightUp += input.get(i - k).charAt(j + k);
                    }

                    if(diagonalRightUp.equals("XMAS")){
                        sum++;
                    }

                    if(reverseString(diagonalRightUp).equals("XMAS")){
                        sum++;
                    }
                } catch (Exception ignored) {}
            }
        }

        System.out.println(sum);
    }

    public void runPart2(List<String> input) {
        int sum = 0;
        for (int i = 0; i < input.size(); i++) {

            String line = input.get(i);
            for (int j = 0; j < line.length(); j++) {

                if(line.charAt(j) == 'A'){
                    try {
                        char topLeft = input.get(i - 1).charAt(j - 1);
                        char topRight = input.get(i - 1).charAt(j + 1);
                        char bottomLeft = input.get(i + 1).charAt(j - 1);
                        char bottomRight = input.get(i + 1).charAt(j + 1);

                        if (((topLeft == 'M' && bottomRight == 'S') || (topLeft == 'S' && bottomRight == 'M')) && ((topRight == 'M' && bottomLeft == 'S') || (topRight == 'S' && bottomLeft == 'M'))){
                            sum++;
                        }
                    } catch (Exception ignored) {}
                }
            }
        }

        System.out.println(sum);
    }

    public String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }
}
