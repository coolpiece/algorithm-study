package week2.p21939;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 윤소윤_21939 {
    public static void main (String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 정렬 기준: 레벨 오름차순, 같으면 번호 오름차순
        TreeSet<Prob> set = new TreeSet<>(
                (a, b) -> {
                    if (a.lev != b.lev) return a.lev - b.lev;
                    return a.num - b.num;
                }
        );

        int N = Integer.parseInt(br.readLine());

        Map<Integer, Integer> problem = new HashMap<>();

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int lev = Integer.parseInt(st.nextToken());

            set.add(new Prob(num, lev));
            problem.put(num,lev);
        }

        int n = Integer.parseInt(br.readLine());
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String mid = st.nextToken();

            if (mid.equals("add")) {
                int num = Integer.parseInt(st.nextToken());
                int lev = Integer.parseInt(st.nextToken());

                set.add(new Prob(num, lev));
                problem.put(num,lev);
            }
            else if (mid.equals("recommend")) {
                int rec = Integer.parseInt(st.nextToken());
                if(rec == 1) {
                    sb.append(set.last().num).append("\n");
                }
                else {
                    sb.append(set.first().num).append("\n");
                }
            }
            else if (mid.equals("solved")) {
                int num = Integer.parseInt(st.nextToken());
                int lev = problem.get(num);

                set.remove(new Prob(num, lev));
                problem.remove(num);
            }
        }
        System.out.print(sb);

    }
    static class Prob {
        int num;
        int lev;

        Prob(int num, int lev) {
            this.num = num;
            this.lev = lev;
        }
    }
}
