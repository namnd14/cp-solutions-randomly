package practice.datastructure;

import java.util.Arrays;

public class DisjointSetUnionBySize {
    public static void log() {
        int n = 5;
        DisjointSetUnionBySize unionFind = new DisjointSetUnionBySize(n);

        unionFind.unionBySize(0, 1);
        unionFind.unionBySize(2, 3);
        unionFind.unionBySize(0, 4);

        for (int i = 0; i < n; i++) {
            System.out.println("Element " + i + ": Representative = " + unionFind.find(i));
        }
    }

    private int[] parent;
    private int[] size;

    public DisjointSetUnionBySize(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        size = new int[n];
        Arrays.fill(size, 1);
    }

    public int find(int i) {
        if (parent[i] != i) {
            parent[i] = find(parent[i]);
        }
        return parent[i];
    }

    public void unionBySize(int i, int j) {
        int irep = find(i);
        int jrep = find(j);

        if (irep == jrep) {
            return;
        }

        int isize = size[irep];
        int jsize = size[jrep];

        if (isize < jsize) {
            parent[irep] = jrep;
            size[jrep] += size[irep];
        } else {
            parent[jrep] = irep;
            size[irep] += size[jrep];
        }
    }
}
