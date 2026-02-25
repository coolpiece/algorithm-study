package p15686;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 윤소윤_15686 {
    static int [][] map;
    static int N,M;

    static int H; // 집 개수
    static int [][] houses;

    static int C; // 치킨집 개수
    static int [][] chickens;

    static int [][] mid;
    static int [] selected;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        H = 0;
        C = 0;
        houses = new int[N * N][2];
        chickens = new int[N * N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            // 집 좌표, 개수와 치킨집 좌표, 개수를 저장
            for (int j = 0; j < N; j++) {
                int v = Integer.parseInt(st.nextToken());

                if (v == 1) { // 집 좌표
                    houses[H][0] = i;
                    houses[H][1] = j;
                    H++;
                } else if (v == 2) { // 치킨집 좌표
                    chickens[C][0] = i;
                    chickens[C][1] = j;
                    C++;
                }
            }
        }

        mid = new int [H][C];
        // 치킨집 별 집 과의 거리 계산
        for (int i = 0; i < H; i++) {
            int hx = houses[i][0], hy = houses[i][1];
            for(int j = 0; j < C; j++) {
                int cx = chickens[j][0], cy = chickens[j][1];
                mid[i][j] = Math.abs(hx - cx) + Math.abs(hy - cy);
            }
        }
        selected = new int[M];
        // 조합 계산
        comb(0,0);

        System.out.println(ans);

    }
    static void comb(int start, int cnt) {
        if(cnt == M) {
            ans = Math.min(ans, calcDist());
            return;
        }
        if(start == C) return;
        for(int i = start; i < C; i++) {
            selected[cnt] = i;
            comb(i + 1, cnt + 1);
        }
    }

    // 선택된 치킨집과 집 간의 거리 계산
    private static int calcDist() {
        int sum = 0;

        for(int h = 0; h < H; h++) {
            int best = Integer.MAX_VALUE;
            for(int t = 0; t < M; t++) {
                int Idx = selected[t];
                best = Math.min(best, mid[h][Idx]);
            }
            sum += best;

            if(sum >= ans) return sum;
        }
        return sum;
    }
}
