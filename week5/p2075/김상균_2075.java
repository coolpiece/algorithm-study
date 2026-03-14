package week5.p2075;

import java.io.*;
import java.util.*;

public class 김상균_2075 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        List<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0;j<N;j++) {
                list.add(Integer.parseInt(st.nextToken()));
            }
        }

        Collections.sort(list, Collections.reverseOrder());

        System.out.println(list.get(N-1));
    }

}
