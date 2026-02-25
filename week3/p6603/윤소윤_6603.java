package p6603;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 윤소윤_6603 {
    static int [] pick;
    static int k;
    static int [] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true) {
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());

            if(k == 0) break;

            arr = new int[k];
            for(int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            // 조합 계산 로직
            pick = new int[6];
            comb(0,0);
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }

    private static void comb(int idx, int cnt) {
        if(cnt == 6) {
            for(int i = 0; i < 6; i++) {
                sb.append(pick[i]).append(i == 5? "\n" : " ");
            }
            return;
        }
        if(idx == k) return;

        pick[cnt] = arr[idx];
        comb(idx+1, cnt+1);

        comb(idx+1, cnt);
    }
}
