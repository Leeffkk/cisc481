package main;

public class Action {

    public int from;
    public int to;
    public String direction;

    public Action(String dicrection, int from, int to) {
        this.direction = dicrection;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return direction + " " + from + " " + to;
    }
}
