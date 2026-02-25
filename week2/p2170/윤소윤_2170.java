package week2.p2170;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main (String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] seg = new int[N][2];

        for(int i = 0; i < N; i++) {
            String line = br.readLine();
            while(line != null && line.trim().isEmpty()) {
                line = br.readLine();
            }

            StringTokenizer st = new StringTokenizer(line);
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            seg[i][0] = Math.min(start, end);
            seg[i][1] = Math.max(start, end);
        }
        // 시작점 기준 정렬, 시작점이 같다면 끝점으로 정렬
        Arrays.sort(seg, (x,y) -> {
            if(x[0] != y[0]) return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });

        long total = 0; // 길이
        int curL = seg[0][0];
        int curR = seg[0][1];

        for(int i = 1; i < N; i++) {
            int l = seg[i][0];
            int r = seg[i][1];

            if (l <= curR) { // 겹쳐있거나 붙어있는 경우
                if (r > curR) curR = r;
            } else { // 떨어져있는 경우
                total += (long)curR - curL;
                curL = l;
                curR = r;
            }
        }
        total += (long)curR - curL;
        System.out.println(total);
    }
}
