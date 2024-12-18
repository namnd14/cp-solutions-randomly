package practice;

import practice.leetcodecontest.WeeklyContest;
import practice.leetcodeproblem.DailyChallenge;
import practice.leetcodeproblem.LeetCodeProblem;
import practice.leetcodeproblem.Leetcode;
import practice.leetcodeproblem.SlidingWindow;
import practice.leetcodeproblem.TreeProblem;
import practice.technique.BinarySearch;
import practice.technique.DynamicProgramming;
import practice.technique.ShortestPathDijkstraAdjacencyList;
import practice.technique.ShortestPathDijkstraAdjacencyMatrix;

public class Main {
    public static void main(String[] args) {
//        WeeklyContest.log();
        // LeetCodeProblem.leetcode2601();
        Leetcode leetcode = new Leetcode();
        // leetcode.log();
        DynamicProgramming dp = new DynamicProgramming();
        // dp.log();
        BinarySearch bs = new BinarySearch();
        // bs.log();
        TreeProblem tp = new TreeProblem();
        // tp.log();
        DailyChallenge dailyChallenge = new DailyChallenge();
        dailyChallenge.log();
        // ShortestPathDijkstraAdjacencyMatrix
        ShortestPathDijkstraAdjacencyMatrix s = new ShortestPathDijkstraAdjacencyMatrix();
        // s.log();
        ShortestPathDijkstraAdjacencyList t = new ShortestPathDijkstraAdjacencyList();
        // t.log();
    }
}