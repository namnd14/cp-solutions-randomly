package practice.datastructure;

public class DisjointSetUnionNaive {
    public static void log() {
        int size = 5;
        DisjointSetUnionNaive disjointSetUnionNaive = new DisjointSetUnionNaive(size);
        disjointSetUnionNaive.union(1, 2);
        disjointSetUnionNaive.union(3, 4);
        boolean inSameSet = disjointSetUnionNaive.find(1) == disjointSetUnionNaive.find(2);
        System.out.println("Are 1 and 2 in the same set? " + inSameSet);
    }

    private int[] parent;

    public DisjointSetUnionNaive(int size) {
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
        }
    }

    public int find(int i) {
        if (parent[i] == i) {
            return i;
        }

        return find(parent[i]);
    }

    public void union(int i, int j) {
        int irep = find(i);
        int jrep = find(j);

        parent[irep] = jrep;
    }
}
