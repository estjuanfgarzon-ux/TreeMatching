Problema



Dado un árbol con n nodos, debemos encontrar la máxima cantidad de aristas que se pueden escoger sin repetir nodos.



A esto se le llama un matching.

&#x20;**Commit 1**

import java.io.\*;

import java.util.\*;



public class Main {



&#x20;   static ArrayList<Integer>\[] adj;

&#x20;   static int\[]\[] dp;



&#x20;   static void dfs(int u, int parent) {



&#x20;       // Caso donde u NO se conecta con hijos

&#x20;       for (int v : adj\[u]) {



&#x20;           if (v == parent) continue;



&#x20;           dfs(v, u);



&#x20;           dp\[u]\[0] += Math.max(dp\[v]\[0], dp\[v]\[1]);

&#x20;       }



&#x20;       // Caso donde u SÍ se conecta con un hijo

&#x20;       for (int v : adj\[u]) {



&#x20;           if (v == parent) continue;



&#x20;           dp\[u]\[1] = Math.max(

&#x20;               dp\[u]\[1],

&#x20;               1

&#x20;               + dp\[v]\[0]

&#x20;               + (dp\[u]\[0] - Math.max(dp\[v]\[0], dp\[v]\[1]))

&#x20;           );

&#x20;       }

&#x20;   }



&#x20;   @SuppressWarnings("unchecked")

&#x20;   public static void main(String\[] args) throws Exception {



&#x20;       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));



&#x20;       int n = Integer.parseInt(br.readLine());



&#x20;       adj = new ArrayList\[n + 1];

&#x20;       dp = new int\[n + 1]\[2];



&#x20;       for (int i = 1; i <= n; i++) {

&#x20;           adj\[i] = new ArrayList<>();

&#x20;       }



&#x20;       for (int i = 0; i < n - 1; i++) {



&#x20;           StringTokenizer st = new StringTokenizer(br.readLine());



&#x20;           int a = Integer.parseInt(st.nextToken());

&#x20;           int b = Integer.parseInt(st.nextToken());



&#x20;           adj\[a].add(b);

&#x20;           adj\[b].add(a);

&#x20;       }



&#x20;       dfs(1, 0);



&#x20;       System.out.println(Math.max(dp\[1]\[0], dp\[1]\[1]));

&#x20;   }

}

**explicación:**

import java.io.\*

Sirve para leer datos.



import java.util.\*

Sirve para usar ArrayList y otras estructuras.



ArrayList\[] adj

Guarda el árbol.

Cada posición tiene los vecinos del nodo.

int\[]\[] dp



Guarda respuestas.



dp\[u]\[0]

dp\[u]\[1]

dfs(int u, int parent)



Recorre el árbol.

if (v == parent) continue



Evita devolverse al padre.

dfs(v, u)



Primero procesa hijos.

dp\[u]\[0]



Caso donde el nodo no se conecta.

dp\[u]\[1]



Caso donde el nodo sí se conecta.

BufferedReader



Lee datos desde consola.

StringTokenizer



Separa números de cada línea.

Problema del commit



La lógica funcionaba.

Pero era lento para entradas muy grandes.



En este primer commit se implementó la lógica principal del problema.

La idea fue:

Recorrer el árbol

Analizar cada nodo

Calcular la mejor cantidad de parejas posibles

Guardar resultados parciales

Idea principal

Para cada nodo se analizaron dos casos:



Caso 1

El nodo NO se conecta con hijos.

Entonces los hijos pueden escoger libremente la mejor opción.



Caso 2

El nodo SÍ se conecta con un hijo.

Entonces:

ganamos una arista

el hijo queda ocupado

ese hijo ya no puede volver a conectarse

Cómo funcionaba



El algoritmo recorría el árbol.

Primero resolvía los hijos.

Luego calculaba la respuesta del padre usando las respuestas de los hijos.

Problema encontrado

Aunque la lógica era correcta, el programa era lento para entradas muy grandes.

El problema principal era la lectura de datos.



**commit 2:**

import java.io.\*;

import java.util.\*;



public class Main {



&#x20;   static ArrayList<Integer>\[] adj;

&#x20;   static int\[]\[] dp;



&#x20;   static void dfs(int u, int parent) {



&#x20;       for (int v : adj\[u]) {



&#x20;           if (v == parent) continue;



&#x20;           dfs(v, u);



&#x20;           dp\[u]\[0] += Math.max(dp\[v]\[0], dp\[v]\[1]);

&#x20;       }



&#x20;       for (int v : adj\[u]) {



&#x20;           if (v == parent) continue;



&#x20;           dp\[u]\[1] = Math.max(

&#x20;               dp\[u]\[1],

&#x20;               1

&#x20;               + dp\[v]\[0]

&#x20;               + (dp\[u]\[0] - Math.max(dp\[v]\[0], dp\[v]\[1]))

&#x20;           );

&#x20;       }

&#x20;   }



&#x20;   @SuppressWarnings("unchecked")

&#x20;   public static void main(String\[] args) throws Exception {



&#x20;       FastScanner fs = new FastScanner(System.in);



&#x20;       int n = fs.nextInt();



&#x20;       adj = new ArrayList\[n + 1];

&#x20;       dp = new int\[n + 1]\[2];



&#x20;       for (int i = 1; i <= n; i++) {

&#x20;           adj\[i] = new ArrayList<>();

&#x20;       }



&#x20;       for (int i = 0; i < n - 1; i++) {



&#x20;           int a = fs.nextInt();

&#x20;           int b = fs.nextInt();



&#x20;           adj\[a].add(b);

&#x20;           adj\[b].add(a);

&#x20;       }



&#x20;       dfs(1, 0);



&#x20;       System.out.println(Math.max(dp\[1]\[0], dp\[1]\[1]));

&#x20;   }



&#x20;   // Entrada rápida

&#x20;   static class FastScanner {



&#x20;       private final InputStream in;

&#x20;       private final byte\[] buffer = new byte\[1 << 16];



&#x20;       private int ptr = 0, len = 0;



&#x20;       FastScanner(InputStream is) {

&#x20;           in = is;

&#x20;       }



&#x20;       private int read() throws IOException {



&#x20;           if (ptr >= len) {

&#x20;               len = in.read(buffer);

&#x20;               ptr = 0;



&#x20;               if (len <= 0) return -1;

&#x20;           }



&#x20;           return buffer\[ptr++];

&#x20;       }



&#x20;       int nextInt() throws IOException {



&#x20;           int c;



&#x20;           while ((c = read()) <= ' ') {

&#x20;               if (c == -1) return -1;

&#x20;           }



&#x20;           int sign = 1;



&#x20;           if (c == '-') {

&#x20;               sign = -1;

&#x20;               c = read();

&#x20;           }



&#x20;           int val = 0;



&#x20;           while (c > ' ') {

&#x20;               val = val \* 10 + (c - '0');

&#x20;               c = read();

&#x20;           }



&#x20;           return val \* sign;

&#x20;       }

&#x20;   }

}

¿Qué cambió?



Antes se usaba:BufferedReader

Ahora se usa:FastScanner

¿Qué se mejoró?



En el primer commit se usaba:



BufferedReader

StringTokenizer



Esto funciona bien para muchos problemas.



Pero para200000 nodos

la lectura empezaba a ser lenta.



Cómo funciona internamente

Buffer

private final byte\[] buffer = new byte\[1 << 16];

Se guarda un bloque grande de datos en memoria.

Método read()

private int read()

Lee carácter por carácter desde el buffer.

Método nextInt()

int nextInt()

Construye números enteros manualmente.

**commit 3:**

import java.io.\*;

import java.util.\*;



public class Main {



&#x20;   static ArrayList<Integer>\[] adj;

&#x20;   static int\[]\[] dp;

&#x20;   static int\[] parent;

&#x20;   static int\[] order;



&#x20;   @SuppressWarnings("unchecked")

&#x20;   public static void main(String\[] args) throws Exception {



&#x20;       FastScanner fs = new FastScanner(System.in);



&#x20;       int n = fs.nextInt();



&#x20;       adj = new ArrayList\[n + 1];

&#x20;       dp = new int\[n + 1]\[2];

&#x20;       parent = new int\[n + 1];

&#x20;       order = new int\[n];



&#x20;       for (int i = 1; i <= n; i++) {

&#x20;           adj\[i] = new ArrayList<>();

&#x20;       }



&#x20;       for (int i = 0; i < n - 1; i++) {



&#x20;           int a = fs.nextInt();

&#x20;           int b = fs.nextInt();



&#x20;           adj\[a].add(b);

&#x20;           adj\[b].add(a);

&#x20;       }



&#x20;       // DFS iterativo

&#x20;       Stack<Integer> stack = new Stack<>();



&#x20;       stack.push(1);

&#x20;       parent\[1] = -1;



&#x20;       int idx = 0;



&#x20;       while (!stack.isEmpty()) {



&#x20;           int u = stack.pop();



&#x20;           order\[idx++] = u;



&#x20;           for (int v : adj\[u]) {



&#x20;               if (v == parent\[u]) continue;



&#x20;               parent\[v] = u;

&#x20;               stack.push(v);

&#x20;           }

&#x20;       }



&#x20;       // Procesamos de abajo hacia arriba

&#x20;       for (int i = n - 1; i >= 0; i--) {



&#x20;           int u = order\[i];



&#x20;           for (int v : adj\[u]) {



&#x20;               if (v == parent\[u]) continue;



&#x20;               dp\[u]\[0] += Math.max(dp\[v]\[0], dp\[v]\[1]);

&#x20;           }



&#x20;           for (int v : adj\[u]) {



&#x20;               if (v == parent\[u]) continue;



&#x20;               dp\[u]\[1] = Math.max(

&#x20;                   dp\[u]\[1],

&#x20;                   1

&#x20;                   + dp\[v]\[0]

&#x20;                   + (dp\[u]\[0] - Math.max(dp\[v]\[0], dp\[v]\[1]))

&#x20;               );

&#x20;           }

&#x20;       }



&#x20;       System.out.println(Math.max(dp\[1]\[0], dp\[1]\[1]));

&#x20;   }



&#x20;   static class FastScanner {



&#x20;       private final InputStream in;

&#x20;       private final byte\[] buffer = new byte\[1 << 16];



&#x20;       private int ptr = 0, len = 0;



&#x20;       FastScanner(InputStream is) {

&#x20;           in = is;

&#x20;       }



&#x20;       private int read() throws IOException {



&#x20;           if (ptr >= len) {

&#x20;               len = in.read(buffer);

&#x20;               ptr = 0;



&#x20;               if (len <= 0) return -1;

&#x20;           }



&#x20;           return buffer\[ptr++];

&#x20;       }



&#x20;       int nextInt() throws IOException {



&#x20;           int c;



&#x20;           while ((c = read()) <= ' ') {

&#x20;               if (c == -1) return -1;

&#x20;           }



&#x20;           int sign = 1;



&#x20;           if (c == '-') {

&#x20;               sign = -1;

&#x20;               c = read();

&#x20;           }



&#x20;           int val = 0;



&#x20;           while (c > ' ') {

&#x20;               val = val \* 10 + (c - '0');

&#x20;               c = read();

&#x20;           }



&#x20;           return val \* sign;

&#x20;       }

&#x20;   }

}

Problema restante



El recorrido del árbol seguía usando recursión.



Con árboles muy profundos Java empieza a fallar.



Eso producía:



TIME LIMIT EXCEEDED

explicación:

Estructuras principales

static ArrayList<Integer>\[] adj;

static int\[]\[] dp;

static int\[] parent;

static int\[] order;

adj → guarda el árbol (conexiones)

dp → guarda la mejor respuesta en cada nodo

parent → guarda el nodo padre

order → guarda el orden de recorrido

Lectura de datos



Se leen los n nodos y las n-1 conexiones del árbol.



Cada conexión se guarda en ambos sentidos:



adj\[a].add(b);

adj\[b].add(a);

Recorrido del árbol



Se usa una pila:



Stack<Integer> stack = new Stack<>();



Se empieza desde el nodo 1:



stack.push(1);



Se recorren todos los nodos guardando el orden en order\[].

Procesamiento del árbol



Se recorre el árbol de abajo hacia arriba:



for (int i = n - 1; i >= 0; i--)

Caso 1: no conectar el nodo

dp\[u]\[0] += Math.max(dp\[v]\[0], dp\[v]\[1]);



Cada hijo toma la mejor opción posible.

Caso 2: conectar el nodo

dp\[u]\[1] = Math.max(

&#x20;   dp\[u]\[1],

&#x20;   1 + dp\[v]\[0] + (dp\[u]\[0] - Math.max(dp\[v]\[0], dp\[v]\[1]))

);

+1 porque usamos una arista

el hijo queda ocupado

se ajusta la contribución anterior
Respuesta final

System.out.println(Math.max(dp\[1]\[0], dp\[1]\[1]));



Se escoge la mejor opción en la raíz.



