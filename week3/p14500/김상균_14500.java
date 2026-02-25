package week3.p14500;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;
    static boolean[][] visited;

    static int maxValue = Integer.MIN_VALUE;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<M;j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                maxValue = Math.max(num,  maxValue);
            }
        }

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                visited[i][j] = true;
                dfs(i, j, 1, map[i][j]);
                visited[i][j] = false;
            }
        }

        System.out.println(result);

    }

    static void dfs(int x, int y, int size, int sum) {
        if(sum + maxValue * (4 - size) <= result) {
            return;
        }

        if(size == 4) {
            result = Math.max(result, sum);
            return;
        }

        for(int i=0;i<4;i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if(nextX < 0 || nextX >= N || nextY < 0 || nextY >= M) {
                continue;
            }

            if(!visited[nextX][nextY]) {
                visited[nextX][nextY] = true;

                // 두번째 좌표에 도달하면, 십자가 탐색을 하기 위해 세번째 좌표를 방문처리하고 다시 두번째 좌표에서부터 재탐색한다.
                if(size == 2) {
                    dfs(x, y, size + 1, sum + map[nextX][nextY]);
                }

                dfs(nextX, nextY, size + 1, sum + map[nextX][nextY]);


                visited[nextX][nextY] = false;
            }
        }
    }



}

