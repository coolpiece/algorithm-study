import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 윤소윤2_12891 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String DNA = br.readLine();
        int [] count = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 4; i++) {
            count[i] = Integer.parseInt(st.nextToken());
        }
        int [] cur = new int[4];

        // 처음 세팅
        for(int i = 0; i < P; i++) {
            cur[idx(DNA.charAt(i))]++;
        }

        int ans = 0;
        if(isOk(cur, count)) ans++;

        // 슬라이딩
        for(int i = 1; i <= S-P; i++) {
            char out = DNA.charAt(i - 1);
            char in = DNA.charAt(i + P -1);

            cur[idx(out)]--;
            cur[idx(in)]++;

            if(isOk(cur, count)) ans++;
        }
        System.out.println(ans);
    }

    // 문자열 개수 세기
    static int idx(char c) {
        if(c == 'A') return 0;
        if(c == 'C') return 1;
        if(c == 'G') return 2;
        return 3;
    }

    // DNA 문자열과 부분문자열 포함 최소개수 비교
    static boolean isOk(int[] cur, int[] count) {
        for(int i = 0; i < 4; i++) {
            if(cur[i] < count[i]) return false;
        }
        return true;
    }
}
