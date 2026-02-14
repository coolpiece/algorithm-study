package week3.p6603;

import java.io.*;
import java.util.*;

public class Main {

    static int[] num;
    static int k;

    static int[] selected;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            k = Integer.parseInt(st.nextToken());

            if(k == 0) {
                break;
            }

            num = new int[k];
            selected = new int[6];

            for(int i=0;i<k;i++) {
                num[i] = Integer.parseInt(st.nextToken());
            }

            combination(0, 0);

            sb.append("\n");
        }

        System.out.println(sb);


    }

    static void combination(int start, int size) {
        if(size == 6) {
            for(int i=0;i<6;i++) {
                sb.append(selected[i] + " ");
            }
            sb.append("\n");

            return;
        }

        for(int i=start;i<k;i++) {
            selected[size] = num[i];
            combination(i + 1, size + 1);
        }
    }

}
