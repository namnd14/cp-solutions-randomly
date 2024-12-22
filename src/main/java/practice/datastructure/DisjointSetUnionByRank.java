package practice.datastructure;

public class DisjointSetUnionByRank {
    public static void log() {
        int n = 5;
        DisjointSetUnionByRank disjointSetUnionByRank = new DisjointSetUnionByRank(n);

        disjointSetUnionByRank.union(0, 2);
        disjointSetUnionByRank.union(4, 2);
        disjointSetUnionByRank.union(3, 1);

        if (disjointSetUnionByRank.find(4) == disjointSetUnionByRank.find(0)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

        // Check if 1 is a friend of 0
        if (disjointSetUnionByRank.find(1) == disjointSetUnionByRank.find(0)) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
    }

    int[] rank;
    int[] parent;
    int n;

    // Constructor
    public DisjointSetUnionByRank(int n) {
        rank = new int[n];
        parent = new int[n];
        this.n = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }

        return parent[x];
    }

    void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if (xRoot == yRoot) {
            return;
        }

        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[yRoot] < rank[xRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }
}
