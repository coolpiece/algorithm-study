import java.io.*;
import java.util.*;

public class 윤소윤_17144 {

    static int R, C;
    static int [][] map;

    public static void main (String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        map = new int [R][C];

        for(int i=0;i<R;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<C;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int t = 0; t < T; t++) {
            dust();
            clean();
        }

        int ans = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] > 0) ans += map[i][j];
            }
        }

        System.out.println(ans);
    }

    public static void dust() {
        int [] dx = {-1, 1, 0, 0};
        int [] dy = {0, 0, -1, 1};

        int [][] res = new int [R][C];
        for(int i = 0; i < R; i++) {
            if(map[i][0] == -1) res[i][0] = -1;
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if (map[i][j] <= 0) continue;

                int spread = map[i][j] / 5;
                int cnt = 0;

                for(int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if(map[nx][ny] == -1) continue;

                    res[nx][ny] += spread;
                    cnt++;
                }
                res[i][j] += map[i][j] - spread * cnt;

            }
        }
        map = res;
    }

    public static void clean() {
        int N1 = 0;
        int N2 = 0;

        // 공기청정기 위치 찾기
        for (int i = 0; i < R; i++) {
            if (map[i][0] == -1) {
                N1 = i;
                N2 = i + 1;
                break;
            }
        }

        // ---------------- 위쪽 (반시계 방향) ----------------
        // 위로
        for (int i = N1 - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }

        // 왼쪽
        for (int j = 0; j < C - 1; j++) {
            map[0][j] = map[0][j + 1];
        }

        // 아래로
        for (int i = 0; i < N1; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        // 오른쪽
        for (int j = C - 1; j > 1; j--) {
            map[N1][j] = map[N1][j - 1];
        }

        map[N1][1] = 0; // 청정기에서 나오는 바람


        // ---------------- 아래쪽 (시계 방향) ----------------
        // 아래로
        for (int i = N2 + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }

        // 왼쪽
        for (int j = 0; j < C - 1; j++) {
            map[R - 1][j] = map[R - 1][j + 1];
        }

        // 위로
        for (int i = R - 1; i > N2; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        // 오른쪽
        for (int j = C - 1; j > 1; j--) {
            map[N2][j] = map[N2][j - 1];
        }

        map[N2][1] = 0; // 청정기 바람

        // 공기청정기 위치 유지
        map[N1][0] = -1;
        map[N2][0] = -1;
    }
}

