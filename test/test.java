import main.Action;
import main.Problem;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class test {

    ArrayList<int[]> YARD;
    HashMap<Integer, LinkedList<String>> INIT;
    HashMap<Integer, LinkedList<String>> GOAL;

    @After
    public void finalize(){

        Problem.printYard(YARD);
        System.out.println("Initial state:\n" + INIT);
        System.out.println("Initial state:\n" + GOAL);

//        Problem.BFS(YARD, INIT, GOAL);
//        Problem.DFS(YARD, INIT, GOAL);
        Problem.AStar(YARD, INIT, GOAL);
    }


    @Test
    public void testYard1(){

        YARD = new ArrayList<>();
        YARD.add(new int[]{1, 2});
        YARD.add(new int[]{1, 3});
        YARD.add(new int[]{3, 5});
        YARD.add(new int[]{4, 5});
        YARD.add(new int[]{2, 6});
        YARD.add(new int[]{5, 6});
        INIT = new HashMap<>();
        INIT.put(1, new LinkedList<>(){{add("*");}});
        INIT.put(2, new LinkedList<>(){{add("e");}});
        INIT.put(3, new LinkedList<>());
        INIT.put(4, new LinkedList<>(){{add("b");add("c");add("a");}});
        INIT.put(5, new LinkedList<>());
        INIT.put(6, new LinkedList<>(){{add("d");}});
        GOAL = new HashMap<>();
        GOAL.put(1, new LinkedList<>(){{add("*");add("a");add("b");add("c");add("d");add("e");}});
        GOAL.put(2, new LinkedList<>());
        GOAL.put(3, new LinkedList<>());
        GOAL.put(4, new LinkedList<>());
        GOAL.put(5, new LinkedList<>());
        GOAL.put(6, new LinkedList<>());

    }

    @Test
    public void testYard2(){

        YARD = new ArrayList<>();
        YARD.add(new int[]{1, 2});
        YARD.add(new int[]{1, 5});
        YARD.add(new int[]{2, 3});
        YARD.add(new int[]{2, 4});
        INIT = new HashMap<>();
        INIT.put(1, new LinkedList<>(){{add("*");}});
        INIT.put(2, new LinkedList<>(){{add("d");}});
        INIT.put(3, new LinkedList<>(){{add("b");}});
        INIT.put(4, new LinkedList<>(){{add("a");add("e");}});
        INIT.put(5, new LinkedList<>(){{add("c");}});
        GOAL = new HashMap<>();
        GOAL.put(1, new LinkedList<>(){{add("*");add("a");add("b");add("c");add("d");add("e");}});
        GOAL.put(2, new LinkedList<>());
        GOAL.put(3, new LinkedList<>());
        GOAL.put(4, new LinkedList<>());
        GOAL.put(5, new LinkedList<>());

    }

    @Test
    public void testYard3(){

        YARD = new ArrayList<>();
        YARD.add(new int[]{1, 2});
        YARD.add(new int[]{1, 3});
        INIT = new HashMap<>();
        INIT.put(1, new LinkedList<>(){{add("*");}});
        INIT.put(2, new LinkedList<>(){{add("a");}});
        INIT.put(3, new LinkedList<>(){{add("b");}});
        GOAL = new HashMap<>();
        GOAL.put(1, new LinkedList<>(){{add("*");add("a");add("b");}});
        GOAL.put(2, new LinkedList<>());
        GOAL.put(3, new LinkedList<>());

    }

    @Test
    public void testYard4(){

        YARD = new ArrayList<>();
        YARD.add(new int[]{1, 2});
        YARD.add(new int[]{1, 3});
        YARD.add(new int[]{1, 4});
        INIT = new HashMap<>();
        INIT.put(1, new LinkedList<>(){{add("*");}});
        INIT.put(2, new LinkedList<>(){{add("a");}});
        INIT.put(3, new LinkedList<>(){{add("b");add("c");}});
        INIT.put(4, new LinkedList<>(){{add("d");}});
        GOAL = new HashMap<>();
        GOAL.put(1, new LinkedList<>(){{add("*");add("a");add("b");add("c");add("d");}});
        GOAL.put(2, new LinkedList<>());
        GOAL.put(3, new LinkedList<>());
        GOAL.put(4, new LinkedList<>());

    }

    @Test
    public void testYard5(){

        YARD = new ArrayList<>();
        YARD.add(new int[]{1, 2});
        YARD.add(new int[]{1, 3});
        YARD.add(new int[]{1, 4});
        INIT = new HashMap<>();
        INIT.put(1, new LinkedList<>(){{add("*");}});
        INIT.put(2, new LinkedList<>(){{add("a");}});
        INIT.put(3, new LinkedList<>(){{add("c");add("b");}});
        INIT.put(4, new LinkedList<>(){{add("d");}});
        GOAL = new HashMap<>();
        GOAL.put(1, new LinkedList<>(){{add("*");add("a");add("b");add("c");add("d");}});
        GOAL.put(2, new LinkedList<>());
        GOAL.put(3, new LinkedList<>());
        GOAL.put(4, new LinkedList<>());

    }

}
