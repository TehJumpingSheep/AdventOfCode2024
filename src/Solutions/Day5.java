package Solutions;

import java.util.*;

public class Day5 implements Solution {

    public void runPart1(List<String> input) {
        int sum = 0;
        boolean switchToUpdates = false;

        List<String> orderRules = new ArrayList<>();
        List<String> updates = new ArrayList<>();

        for (String line : input){
            if(switchToUpdates){
                updates.add(line);
            } else if (line.isEmpty()){
                switchToUpdates = true;
            } else {
                orderRules.add(line);
            }
        }

        for (String update : updates) {
            List<String> updateList = List.of(update.split(","));


            boolean bad = false;

            for (String rule : orderRules) {

                int numberBeforePos = updateList.indexOf(rule.split("\\|")[0]);
                int numberAfterPos = updateList.indexOf(rule.split("\\|")[1]);

                if(numberAfterPos < 0 || numberBeforePos < 0){
                    continue;
                }

                if(numberBeforePos > numberAfterPos){
                    bad = true;
                }

            }

            if(!bad){
                sum += Integer.parseInt(updateList.get(((updateList.size() - 1) / 2)));
            }
        }
        System.out.println(sum);
    }

    public void runPart2(List<String> input) {
        int sum = 0;
        boolean switchToUpdates = false;

        List<String> orderRules = new ArrayList<>();
        List<List<Integer>> updates = new ArrayList<>();

        for (String line : input){
            if(switchToUpdates){
                List<String> updateList = List.of(line.split(","));
                List<Integer> list = new ArrayList<>();

                for (String update: updateList) {
                    list.add(Integer.parseInt(update));
                }

                updates.add(list);
            } else if (line.isEmpty()){
                switchToUpdates = true;
            } else {
                orderRules.add(line);
            }
        }

        for (List<Integer> update : updates) {

            Map<Integer, Set<Integer>> graph = new HashMap<>();
            Map<Integer, Integer> inDegree = new HashMap<>();

            for (String rule : orderRules) {
                String[] parts = rule.split("\\|");
                int before = Integer.parseInt(parts[0]);
                int after = Integer.parseInt(parts[1]);

                if (update.contains(before) && update.contains(after)){
                    graph.computeIfAbsent(before, k -> new HashSet<>()).add(after);
                    inDegree.put(after, inDegree.getOrDefault(after, 0) + 1);
                    inDegree.putIfAbsent(before, 0);
                }
            }

            List<Integer> globalOrder = topologicalSort(graph, inDegree);

            List<Integer> original = new ArrayList<>(update);
            update.sort(Comparator.comparingInt(globalOrder::indexOf));

            if(!original.equals(globalOrder)){
                sum += update.get((update.size() - 1) / 2);
            }
        }
        System.out.println(sum);
    }

    private List<Integer> topologicalSort(Map<Integer, Set<Integer>> graph, Map<Integer, Integer> inDegree) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : inDegree.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            if (graph.containsKey(node)) {
                for (int neighbor : graph.get(node)) {
                    inDegree.put(neighbor, inDegree.get(neighbor) - 1);
                    if (inDegree.get(neighbor) == 0) {
                        queue.add(neighbor);
                    }
                }
            }
        }

        return result;
    }
}
