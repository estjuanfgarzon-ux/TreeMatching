import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] adj;
    static int[][] dp;

    static void dfs(int u, int parent) {

        for (int v : adj[u]) {

            if (v == parent) continue;

            dfs(v, u);

            dp[u][0] += Math.max(dp[v][0], dp[v][1]);
        }

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

        FastScanner fs = new FastScanner(System.in);

        int n = fs.nextInt();

        adj = new ArrayList[n + 1];
        dp = new int[n + 1][2];

        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {

            int a = fs.nextInt();
            int b = fs.nextInt();

            adj[a].add(b);
            adj[b].add(a);
        }

        dfs(1, 0);

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    // Entrada rápida
    static class FastScanner {

        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];

        private int ptr = 0, len = 0;

        FastScanner(InputStream is) {
            in = is;
        }

        private int read() throws IOException {

            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;

                if (len <= 0) return -1;
            }

            return buffer[ptr++];
        }

        int nextInt() throws IOException {

            int c;

            while ((c = read()) <= ' ') {
                if (c == -1) return -1;
            }

            int sign = 1;

            if (c == '-') {
                sign = -1;
                c = read();
            }

            int val = 0;

            while (c > ' ') {
                val = val * 10 + (c - '0');
                c = read();
            }

            return val * sign;
        }
    }
}