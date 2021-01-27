package problems;

import java.util.*;

public class P1617_CountSubtreesWithMaxDistanceBetweenCities {

    public int[] countSubgraphsForEachDiameter(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        Queue<List<Integer>> q = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            List<Integer> l = new ArrayList<>();
            l.add(i);
            q.add(l);
        }

        while (!q.isEmpty()) {
            List<Integer> l = q.poll();

        }

        Map<String, Integer> m = new HashMap<>();


        return null;
    }

    class Transaction {
        int time;
        String name;
        String city;
        Transaction(int t, String n, String c) {
            time = t;
            name = n;
            city = c;
        }
    }

    public List<String> invalidTransactions(String[] transactions) {
        List<String> res = new ArrayList<>();

        Map<String, Transaction> m = new HashMap<>();

        for (String t : transactions) {
            String tr[] = t.split(",");

            String name = tr[0];
            int time = Integer.valueOf(tr[1]);
            int amount = Integer.valueOf(tr[2]);
            String city = tr[3];

            if (amount > 1000) {
                res.add(t);
            } else if (m.containsKey(name)) {
                Transaction tt = m.get(name);
                if (tt.time + 60 >= time && !tt.city.equals(city))
                    res.add(t);
            }

            m.put(name, new Transaction(time, name, city));
        }

        return res;
    }

}