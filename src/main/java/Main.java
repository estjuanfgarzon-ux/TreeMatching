import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adj;
    static int[][] dp;

    static void dfs(int u, int parent) {

        // Caso donde u NO se conecta con hijos
        for (int v : adj[u]) {

            if (v == parent) continue;

            dfs(v, u);

            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
        }

        // Caso donde u SÍ se conecta con un hijo
        for (int v : adj[u]) {

            if (v == parent) continue;

            dp[u][1] = Math.max(
                dp[u][1],
                1
                + dp[v][0]
                + (dp[u][0] - Math.max(dp[v][0], dp[v][1]))
            );
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        adj = new ArrayList[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }
}