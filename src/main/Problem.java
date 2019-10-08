package main;

import java.util.*;

public class Problem {



    public static int locateEngine(HashMap<Integer, LinkedList<String>> state) {
        for (Map.Entry<Integer, LinkedList<String>> entry : state.entrySet()) {
            for (String truck : entry.getValue()) {
                if (truck.equals("*")){
                    return entry.getKey();
                }
            }
        }
        return -1;
    }

    public static ArrayList<Action> possible_actions(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> state){

        ArrayList<Action> result = new ArrayList<>();
        int engineLoc = locateEngine(state);

        for (int[] edge : yard){
            if (edge[0]==engineLoc || edge[1]==engineLoc) {
                if (!state.get(edge[1]).isEmpty()) {
                    result.add(new Action("Left", edge[1], edge[0]));
                }
                if (!state.get(edge[0]).isEmpty()) {
                    result.add(new Action("Right", edge[0], edge[1]));
                }
            }
        }

        return result;
    }

    public static HashMap<Integer, LinkedList<String>> result(Action action, HashMap<Integer, LinkedList<String>> cur_state){
        HashMap<Integer, LinkedList<String>> result = new HashMap<>();

        for (Map.Entry<Integer, LinkedList<String>> entry : cur_state.entrySet()){
            result.put(entry.getKey(), (LinkedList<String>)entry.getValue().clone());
        }

        LinkedList<String> fromLL = result.get(action.from);
        LinkedList<String> toLL = result.get(action.to);

        if (action.direction.equals("Left")) {
            toLL.addLast(fromLL.removeFirst());
        }
        if (action.direction.equals("Right")) {
            toLL.addFirst(fromLL.removeLast());
        }

        return result;
    }

    public static LinkedList<HashMap<Integer, LinkedList<String>>> expand(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> state){
        ArrayList<Action> possible_actions = possible_actions(yard, state);
        LinkedList<HashMap<Integer, LinkedList<String>>> result = new LinkedList<>();

        for (Action action : possible_actions) {
            result.add(result(action, state));
        }

        return result;
    }

    public static void BFS(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> init, HashMap<Integer, LinkedList<String>> goal) {
        Queue<HashMap<Integer, LinkedList<String>>> state_queue = new LinkedList<>();
        HashSet<HashMap<Integer, LinkedList<String>>> state_seen = new HashSet<>();

        HashMap<HashMap<Integer, LinkedList<String>>, HashMap<Integer, LinkedList<String>>> tracking = new HashMap<>();

        state_queue.add(init);
        tracking.put(init, null);

        HashMap<Integer, LinkedList<String>> tmp = null;

        int states_traversed = 0;

        while (!state_queue.isEmpty()) {

            tmp = state_queue.remove();
            states_traversed++;
            if (state_seen.contains(tmp)) {
                continue;
            }

            if (tmp.equals(goal)) {
                System.out.println("Found!!!");
                break;
            }

            // ---------- uncomment this part for better performance
//            state_queue.addAll(expand(yard, tmp));


            // ---------- comment out this part for better performance
            LinkedList<HashMap<Integer, LinkedList<String>>> expand = expand(yard, tmp);
            state_queue.addAll(expand);
            for (HashMap<Integer, LinkedList<String>> state : expand) {
                if (!tracking.containsKey(state)) {
                    tracking.put(state, tmp);
                }
            }
            // ---------- comment out this part for better performance

            state_seen.add(tmp);
        }

        LinkedList<HashMap<Integer, LinkedList<String>>> steps = new LinkedList<>();
        LinkedList<Action> step_actions = new LinkedList<>();
        HashMap<Integer, LinkedList<String>> tmp_to = null;
        while (tmp!=null) {
            steps.addFirst(tmp);

            if (null != tmp_to) {
                step_actions.addFirst(fromAction(yard, tmp, tmp_to));
            }
            tmp_to = tmp;
            tmp = tracking.get(tmp);
        }
        System.out.println("Actions:");

        for (HashMap<Integer, LinkedList<String>> step : steps) {
            System.out.println(step);
        }

        System.out.println("Actions:");
        for (Action action : step_actions){
            System.out.println(action);
        }

//        System.out.println("Number of states traversed: " + states_traversed);

    }

    public static void DFS(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> init, HashMap<Integer, LinkedList<String>> goal) {
        Stack<HashMap<Integer, LinkedList<String>>> state_stack = new Stack<>();
        HashSet<HashMap<Integer, LinkedList<String>>> state_seen = new HashSet<>();

        HashMap<HashMap<Integer, LinkedList<String>>, HashMap<Integer, LinkedList<String>>> tracking = new HashMap<>();


        state_stack.push(init);
        tracking.put(init, null);

        HashMap<Integer, LinkedList<String>> tmp = null;

        while (!state_stack.isEmpty()) {
            tmp = state_stack.pop();

            if (state_seen.contains(tmp)) {
                continue;
            }

            if (tmp.equals(goal)) {
                System.out.println("Found!!!");
                break;
            }

//            state_stack.addAll(expand(yard, tmp));

            LinkedList<HashMap<Integer, LinkedList<String>>> expand = expand(yard, tmp);
//
//            state_stack.addAll(expand);

            for (HashMap<Integer, LinkedList<String>> state : expand) {
                state_stack.add(state);
                if (!tracking.containsKey(state)) {
                    tracking.put(state, tmp);
                }
            }
            // ---------- comment out this part for better performance

            state_seen.add(tmp);

        }

        LinkedList<HashMap<Integer, LinkedList<String>>> steps = new LinkedList<>();
        while (tmp!=null) {
            steps.addFirst(tmp);
            tmp = tracking.get(tmp);
        }
        System.out.println("Actions:");

        for (HashMap<Integer, LinkedList<String>> step : steps) {
            System.out.println(step);
        }

    }

    public static void AStar(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> init, HashMap<Integer, LinkedList<String>> goal) {

        HashMap<HashMap<Integer, LinkedList<String>>, Integer> gscore = new HashMap<>();
        HashMap<HashMap<Integer, LinkedList<String>>, Integer> fscore = new HashMap<>();
        HashSet<HashMap<Integer, LinkedList<String>>> closed = new HashSet<>();
        HashMap<HashMap<Integer, LinkedList<String>>, HashMap<Integer, LinkedList<String>>> came_from = new HashMap<>();
        PriorityQueue<HashMap<Integer, LinkedList<String>>> open = new PriorityQueue<>(Comparator.comparingInt(o -> fscore.get(o)));

        gscore.put(init, 0);
        fscore.put(init, heuristics(init, goal));

        open.add(init);

        came_from.put(init, null);

        HashMap<Integer, LinkedList<String>> current = null;

//        int states_traversed = 0;

        while (!open.isEmpty()) {

            current = open.poll();

//            states_traversed++;

            if (current.equals(goal)) {
                System.out.println("solution found!!!\n" + current);
                break;
            }

            closed.add(current);

            LinkedList<HashMap<Integer, LinkedList<String>>> expand = expand(yard, current);

            for (HashMap<Integer, LinkedList<String>> state : expand) {
                if (closed.contains(state)) {
                    continue;
                }
                Integer tmp_gscore = gscore.get(current) + 1;
                if (!gscore.containsKey(state) || tmp_gscore < gscore.get(state)) {
                    came_from.put(state, current);
                    gscore.put(state, tmp_gscore);
                    fscore.put(state, tmp_gscore + heuristics(state, goal));
                    open.add(state);
                }
            }

        }

        LinkedList<HashMap<Integer, LinkedList<String>>> step_states = new LinkedList<>();
        LinkedList<Action> step_actions = new LinkedList<>();

        HashMap<Integer, LinkedList<String>> tmp_to = null;
        while (current!=null) {
            step_states.addFirst(current);
            if (null != tmp_to) {
                step_actions.addFirst(fromAction(yard, current, tmp_to));
            }
            tmp_to = current;
            current = came_from.get(current);
        }

        System.out.println("States:");
        for (HashMap<Integer, LinkedList<String>> step : step_states) {
            System.out.println(step);
        }

        System.out.println("Actions:");
        for (Action action : step_actions){
            System.out.println(action);
        }
//        System.out.println("Number of states traversed: " + states_traversed);

    }


    public static int heuristics(HashMap<Integer, LinkedList<String>> state, HashMap<Integer, LinkedList<String>> goal) {

        int result = 0;

        LinkedList<String> cur_1 = state.get(1);
        LinkedList<String> goal_1 = goal.get(1);

        for (int i=0; i<goal_1.size(); i++) {
            if (i<cur_1.size() && !cur_1.get(i).equals(goal_1.get(i))){
                result ++;
            }
            result *= 2;
        }

        return result;
    }

    public static Action fromAction(ArrayList<int[]> yard, HashMap<Integer, LinkedList<String>> from, HashMap<Integer, LinkedList<String>> to) {
        for (Action action : possible_actions(yard, from)) {
            if (to.equals(result(action, from))) {
                return action;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("Please run test.java!!!");
    }

    public static void printYard(ArrayList<int[]> yard){
        System.out.println("yard:");
        for (int[] arr : yard){
            System.out.printf("(%s, %s) ", arr[0], arr[1]);
        }
        System.out.println();
    }

}
