package week4.p2573;

import java.io.*;
import java.util.*;

public class 김상균_2573 {

    static int N, M;
    static int[][] map;

    static List<Glacier> glaciers;

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        glaciers = new ArrayList<>();

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++) {
                int num = Integer.parseInt(st.nextToken());
                map[i][j] = num;
                if(num != 0) {
                    glaciers.add(new Glacier(i, j));
                }
            }
        }

        boolean flag = false;
        int year = 0;

        while(!glaciers.isEmpty()) {

            int glacierCount = 0;
            boolean[][] visited = new boolean[N][M];
            for(Glacier g : glaciers) {
                if(!visited[g.x][g.y]) {
                    glacierCount++;
                    visit(g.x, g.y, visited);
                }
            }

            if(glacierCount >= 2) {
                flag = true;
                break;
            }

            year++;
            int[] forDecrease = new int[glaciers.size()];

            for(int i = 0; i < glaciers.size(); i++) {
                Glacier g = glaciers.get(i);
                int seaCount = 0;
                for(int j=0; j<4; j++) {
                    int nx = g.x + dr[j];
                    int ny = g.y + dc[j];
                    if(map[nx][ny] == 0) seaCount++;
                }
                forDecrease[i] = seaCount;
            }

            List<Glacier> nextGlaciers = new ArrayList<>();
            for(int i = 0; i < glaciers.size(); i++) {
                Glacier g = glaciers.get(i);
                map[g.x][g.y] = Math.max(0, map[g.x][g.y] - forDecrease[i]);

                if(map[g.x][g.y] > 0) {
                    nextGlaciers.add(g);
                }
            }
            glaciers = nextGlaciers;
        }

        System.out.println(flag ? year : 0);
    }

    static void visit(int x, int y, boolean[][] visited) {
        visited[x][y] = true;
        for(int i=0; i<4; i++) {
            int nextX = x + dr[i];
            int nextY = y + dc[i];

            if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M) {
                if(map[nextX][nextY] != 0 && !visited[nextX][nextY]) {
                    visit(nextX, nextY, visited);
                }
            }
        }
    }

    static class Glacier {
        int x, y;
        Glacier(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
