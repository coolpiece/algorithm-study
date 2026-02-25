package p14500;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 윤소윤_14500 {
    static int N, M;
    static int max = Integer.MIN_VALUE;
    static int [][] map;
    static boolean [][] visit;

    // 상하좌우
    static int [] dx = {-1, 1, 0, 0};
    static int [] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 입력
        map = new int [N][M];
        visit = new boolean [N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 탐색
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                visit[i][j] = true;
                solve(i,j,map[i][j],1);
                visit[i][j] = false;
            }
        }
        System.out.println(max);
    }

    static void solve(int row, int col, int sum, int cnt) {
        if(cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int CR = row + dx[i];
            int CC = col + dy[i];

            // 범위 벗어난 경우
            if(CR < 0 || CR >= N || CC < 0 || CC >= M) {
                continue;
            }

            if(!visit[CR][CC]) {

                // ㅗ 모양 만들기
                if(cnt == 2) {
                    visit[CR][CC] = true;
                    solve(row, col, sum+map[CR][CC], cnt+1);
                    visit[CR][CC] = false;
                }
                visit[CR][CC] = true;
                solve(CR, CC, sum+map[CR][CC], cnt+1);
                visit[CR][CC] = false;
            }

        }
    }
}
