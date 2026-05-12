import java.io.*;
import java.util.*;

public class Main {
//Arbol
    static ArrayList<Integer>[] adj;//Lista de vecinos del árbol, ejemplo: 1 conectado con 2 y 3
   //Matriz de resultados
    static int[][] dp;//Aquí se guardan los mejores resultados posibles para cada nodo, cada nodo guarda dos valores
    static int[] parent;//padres, guarda el padre de cada nodo y este sirve para no devolverse
    static int[] order;//Guarda el orden del recorrido, despues se procesa al reves, de hojas hacia arriba 

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {

        FastScanner fs = new FastScanner(System.in);//lee datos rapido

        int n = fs.nextInt();//Cantidad de nodos
//creo estructuras auxiliares
        adj = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        parent = new int[n + 1];
        order = new int[n];
// inicializar listas
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }// cada nodo tiene su lista de vecinos
//leemos conexiones
        for (int i = 0; i < n - 1; i++) {
//recordar que el arbol tiene n-1 conexiones
            int a = fs.nextInt();
            int b = fs.nextInt();//lee dos nodos conectados 

            adj[a].add(b);
            adj[b].add(a);//como el arbol no tiene direccion a<->b
        }

        //pila
        Stack<Integer> stack = new Stack<>();//uso una pila para recorrer el arbol
//empezar en 1
        stack.push(1);//mete el nodo raiz
        parent[1] = -1;//padre de la raiz, la raiz no tiene padre.
//indice
        int idx = 0;//sirve para llenar order
//recorrido
        while (!stack.isEmpty()) {//mientras haya nodos pendientes

            int u = stack.pop();//Saca un nodo de la pila

            order[idx++] = u;//guarda el orden recorrido
//recorrer vecinios
            for (int v : adj[u]) {//recorre vecinos

                if (v == parent[u]) continue;//evita devolverse es decir no vuelve al padre

                parent[v] = u;//marca el padre del hijo
                stack.push(v);//agrega el hijo, lo mete en la pila
            }
        }

        // Proceso de abajo hacia arriba
        for (int i = n - 1; i >= 0; i--) {//primero hojas luego padres

            int u = order[i];//nodo actual
//primer caso
            for (int v : adj[u]) {//recorre hijos

                if (v == parent[u]) continue;//ignorar padre
//Si u no se conecta
                dp[u][0] += Math.max(dp[v][0], dp[v][1]);// aca el nodo u no se conecta con los hijos entocnes cada hijo escoge lo que mas le convenga
            }
//segundo caso
            for (int v : adj[u]) {//ahora pruebo conectar u con un hijo

                if (v == parent[u]) continue;

                dp[u][1] = Math.max(// busco la mejor conexion posible 
                    dp[u][1],
                    1// representa la conexion entre u y v
                    + dp[v][0]// si u se conecta con v, v ya no puede conectarse con otros
                    + (dp[u][0] - Math.max(dp[v][0], dp[v][1]))//ajuste, quito el calculo anterior de v para remplazarlo por el caso donde si se conecta con u
                );
            }
        }

        System.out.println(Math.max(dp[1][0], dp[1][1]));//imprimo
    }

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