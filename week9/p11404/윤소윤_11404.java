package week9.p11404;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.util.StringTokenizer;

public class 윤소윤_11404 {


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new java.io.OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine()); // 도시 개수
        int M = Integer.parseInt(br.readLine()); // 버스 개수
        int[][] map = new int[N + 1][N + 1];

        // 초기값
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.MAX_VALUE;

                if (i == j) map[i][j] = 0;
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            map[a][b] = Math.min(map[a][b], c);
        }

        // 플로이드
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (map[i][k] != Integer.MAX_VALUE && map[k][j] != Integer.MAX_VALUE) {
                        if (map[i][j] > map[i][k] + map[k][j]) {
                            map[i][j] = map[i][k] + map[k][j];
                        }
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                if(map[i][j] == Integer.MAX_VALUE) map[i][j] = 0;
                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
