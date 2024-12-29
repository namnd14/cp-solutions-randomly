package practice.technique;

public class GraphDetectCycle {
    public static void log() {
        int v = 3;
        int e = 3;
        GraphDetectCycle graph = new GraphDetectCycle(v, e);

        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;

        // add edge 1-2
        graph.edge[1].src = 1;
        graph.edge[1].dest = 2;

        // add edge 0-2
        graph.edge[2].src = 0;
        graph.edge[2].dest = 2;

        if (graph.isCycle(graph) == 1) {
            System.out.println("Graph contains cycle");
        } else {
            System.out.println("Graph doesn't contain cycle");
        }
    }

    int vertices;
    int edges;
    Edge[] edge;

    GraphDetectCycle(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        edge = new Edge[edges];
        for (int i = 0; i < edges; i++) {
            edge[i] = new Edge();
        }
    }

    class Edge {
        int src;
        int dest;
    }

    class Subset {
        int parent;
        int rank;
    }

    int find(Subset[] subsets, int i) {
        if (subsets[i].parent != i) {
            subsets[i].parent = find(subsets, subsets[i].parent);
        }

        return subsets[i].parent;
    }

    void union(Subset[] subsets, int x, int y) {
        int xroot = find(subsets, x);
        int yroot = find(subsets, y);

        if (subsets[xroot].rank < subsets[yroot].rank) {
            subsets[xroot].parent = yroot;
        } else if (subsets[yroot].rank < subsets[xroot].rank) {
            subsets[yroot].parent = xroot;
        } else {
            subsets[xroot].parent = yroot;
            subsets[yroot].rank++;
        }
    }

    int isCycle(GraphDetectCycle graph) {
        int vertices = graph.vertices;
        int edges = graph.edges;

        Subset[] subsets = new Subset[vertices];
        for (int v = 0; v < vertices; v++) {

            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        for (int e = 0; e < edges; e++) {
            int x = find(subsets, graph.edge[e].src);
            int y = find(subsets, graph.edge[e].dest);
            if (x == y) {
                return 1;
            }
            union(subsets, x, y);
        }

        return 0;
    }
}
