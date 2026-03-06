import java.io.*;
import java.util.*;

public class 윤소윤_2146 {
    // 하 상 우 좌
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    static int[][] map;
    static boolean[][] visitedLabel; // 섬 라벨링용
    static int[][] vis;              // 방문 체크
    static int stamp = 1;

    static int min = Integer.MAX_VALUE;
    static int n;

    // 섬별 해안 리스트를 모아둘 배열
    static ArrayList<int[]>[] borders;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        visitedLabel = new boolean[n][n];
        vis = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int number = 2; // 섬 라벨링
        borders = new ArrayList[n * n + 2]; // 해안 저장 배열

        for (int i = 0; i < borders.length; i++) borders[i] = new ArrayList<>();

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (map[r][c] == 1 && !visitedLabel[r][c]) {
                    labelIslandAndCollectBorder(r, c, number);
                    number++;
                }
            }
        }

        for (int island = 2; island < number; island++) {
            if (borders[island].isEmpty()) continue;
            bfsFromBorders(island, borders[island]);
        }

        System.out.println(min);
    }

    // 섬 라벨링(BFS) + 바다와 인접한 육지 수집
    static void labelIslandAndCollectBorder(int sr, int sc, int number) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{sr, sc});
        visitedLabel[sr][sc] = true;
        map[sr][sc] = number;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            boolean isBorder = false;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;

                if (map[nr][nc] == 0) {
                    // 바다와 인접하면 해안
                    isBorder = true;
                } else if (map[nr][nc] == 1 && !visitedLabel[nr][nc]) {
                    visitedLabel[nr][nc] = true;
                    map[nr][nc] = number;
                    q.add(new int[]{nr, nc});
                }
            }

            if (isBorder) borders[number].add(new int[]{r, c});
        }
    }

    // 특정 섬의 해안 칸들에서만 바다로 BFS
    static void bfsFromBorders(int island, ArrayList<int[]> starts) {
        ArrayDeque<int[]> q = new ArrayDeque<>();
        stamp++;

        // 해안 육지에서 시작
        for (int[] s : starts) {
            int r = s[0], c = s[1];
            vis[r][c] = stamp;
            q.add(new int[]{r, c, 0});
        }

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1], dist = cur[2];

            if (dist >= min) continue;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (nr < 0 || nc < 0 || nr >= n || nc >= n) continue;
                if (vis[nr][nc] == stamp) continue;

                // 다른 섬 도착
                if (map[nr][nc] != 0 && map[nr][nc] != island) {
                    min = Math.min(min, dist);
                    return;
                }

                // 바다로 확장
                if (map[nr][nc] == 0) {
                    vis[nr][nc] = stamp;
                    q.add(new int[]{nr, nc, dist + 1});
                }
            }
        }
    }
}