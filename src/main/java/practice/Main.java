package practice;

import practice.algorithm.QuickSort;
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
        System.out.println("Log from main class");
//        WeeklyContest.log();
        // LeetCodeProblem.leetcode2601();
        Leetcode leetcode = new Leetcode();
        leetcode.log();
        QuickSort qs = new QuickSort();
        // qs.log();
    }
}