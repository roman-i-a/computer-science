package chapter4;

import java.util.ArrayList;
import java.util.List;

public class Edge {
    public final int u;
    public final int v;

    public Edge(int u, int v) {
        this.u = u;
        this.v = v;
    }

    public Edge reversed() {
        return new Edge(v, u);
    }

    @Override
    public String toString() {
        return u + " -> " + v;
    }


    public static void main(String[] args) {
        List<Edge> le = List.of(
        new Edge(1, 2),
        new Edge(2, 3),
        new Edge(3, 4),
        new Edge(4, 1));
        

        for (Edge e : le) {
            System.out.println(e);
        }
    }
}
