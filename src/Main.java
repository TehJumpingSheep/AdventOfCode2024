import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        String day = selectDay();
        Class<?> dynamicClass = getDayClass(day);
        Object dynamicObject = getDynamicClassObject(dynamicClass);

        List<String> input = getInputAsList(day);

        runSolutions(dynamicClass, dynamicObject, input);
    }

    public static String selectDay(){
        int day = 1;
        boolean validInput = false;

        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter day");

        while (!validInput) {
            try {
                day = Integer.parseInt(scanner.nextLine());
                // there's only 25 days in aoc
                if(day > 0 && day < 26){
                    validInput = true;
                } else {
                    throw new Exception("sukkel");
                }
            } catch (Exception e) {
                System.out.println("Please put a valid day " + e.getMessage());
            }
        }

        return String.valueOf(day);
    }

    public static Class<?> getDayClass(String day){
        try {
            return Class.forName("Solutions.Day" + day);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object getDynamicClassObject(Class<?> dynamicClass){
        try {
            Object dynamicObject = dynamicClass.newInstance();
            return dynamicObject;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getInputAsList(String day) {
        BufferedReader reader;

        try {
            reader = new BufferedReader(new FileReader("src/Inputs/Day" + day + ".txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        String line = null;
        try {
            line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> list = new ArrayList<>();

        while (line != null) {
            list.add(line);
            try {
                line = reader.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return list;
    }

    public static void runSolutions(Class<?> dynamicClass, Object dynamicObject, List<String> input){

        /*
        *  Thx @fqrb voor deze prachtige lines
        */
        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Successfully loaded. Running Part 1");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        runPart1(dynamicClass, dynamicObject, input);

        System.out.println();
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("Finished part 1. Running part 2:");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println();

        runPart2(dynamicClass, dynamicObject, input);

    }

    public static void runPart1(Class<?> dynamicClass, Object dynamicObject, List<String> input){
        try {
            dynamicClass.getMethod("runPart1", List.class).invoke(dynamicObject, input);
        } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void runPart2(Class<?> dynamicClass, Object dynamicObject, List<String> input){
        try {
            dynamicClass.getMethod("runPart2", List.class).invoke(dynamicObject, input);
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}