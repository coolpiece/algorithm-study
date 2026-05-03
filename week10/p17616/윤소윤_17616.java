import java.io.*;
import java.util.*;

public class 윤소윤_17616 {

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 질문 횟수
        int X = Integer.parseInt(st.nextToken()); // 알고 싶은 학생

        ArrayList<ArrayList<Integer>> parents = new ArrayList<>();
        ArrayList<ArrayList<Integer>> child = new ArrayList<>();

        for (int i = 0; i < N + 1; i++) {
            parents.add(new ArrayList<>());
            child.add(new ArrayList<>());
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            parents.get(a).add(b);
            child.get(b).add(a);
        }

        boolean[] visit1 = new boolean[N + 1];
        visit1[X] = true;
        int low = dfs(X, parents, 0, visit1);

        boolean[] visit2 = new boolean[N + 1];
        visit2[X] = true;
        int high = dfs(X, child, 0, visit2);

        System.out.println((high + 1) + " " + (N - low));

    }
    static int dfs(int x, ArrayList<ArrayList<Integer>> arr, int count, boolean[] visit) {
        for(int i = 0; i < arr.get(x).size(); i++) {
            if(!visit[arr.get(x).get(i)]) {
                visit[arr.get(x).get(i)] = true;
                count += dfs(arr.get(x).get(i), arr, 1, visit);
            }
        }
        return count;
    }
}

