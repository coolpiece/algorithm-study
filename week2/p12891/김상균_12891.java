package week2.p12891;

import java.util.*;
import java.io.*;

public class 김상균_12891 {

    static int result = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        String input = br.readLine();

        st = new StringTokenizer(br.readLine());

        Map<Character, Integer> indexMap = new HashMap<>();
        indexMap.put('A', 0);
        indexMap.put('C', 1);
        indexMap.put('G', 2);
        indexMap.put('T', 3);

        // 0 : 'A'
        // 1 : 'C'
        // 2 : 'G'
        // 3 : 'T'
        int[] condition = new int[4]; // => 조건

        for(int i=0;i<4;i++) {
            condition[i] = Integer.parseInt(st.nextToken());
        }

        int[] frequency = new int[4]; // 각 문자의 등장 횟수

        // 현재 문자열 초기화
        for(int i=0;i<P;i++) {
            char c = input.charAt(i);

            frequency[indexMap.get(c)]++;
        }

        if(check(frequency, condition)) result++;

        for(int i=1;i<= S - P;i++) {
            char head = input.charAt(i-1); // 이전 것 ( = 삭제할 것)
            char tail = input.charAt(P - 1 + i); // 다음 것 ( = 추가할 것);

            frequency[indexMap.get(head)]--;
            frequency[indexMap.get(tail)]++;

            if(check(frequency, condition)) result++;
        }

        System.out.println(result);


    }

    static boolean check(int[] frequency, int[] condition) {
        for(int i=0;i<4;i++) {
            if(frequency[i] < condition[i]) {
                return false;
            }
        }

        return true;
    }

}
