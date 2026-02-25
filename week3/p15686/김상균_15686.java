package week3.p15686;

import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static int[][] map;

    static List<int[]> chickens;
    static List<int[]> houses;

    // 선택된 치킨집의 인덱스를 저장
    static int[] selected;

    static int result;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        chickens = new ArrayList<>();
        houses = new ArrayList<>();
        selected = new int[M];

        result = Integer.MAX_VALUE;

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine(), " ");

            for(int j=0;j<N;j++) {
                int num = Integer.parseInt(st.nextToken());

                if(num == 1) {
                    houses.add(new int[] {i, j});
                }

                if(num == 2) {
                    chickens.add(new int[] {i, j});
                }

                map[i][j] = num;
            }
        }

        combination(0, 0);

        System.out.println(result);


    }

    static void combination(int start, int size) {
        if(size == M) {
            // 거리 체크
            int sum = 0;

            for(int[] housePos : houses) {
                int houseX = housePos[0];
                int houseY = housePos[1];

                int distance = Integer.MAX_VALUE;
                for(int index : selected) {
                    int[] chickenPos = chickens.get(index);

                    int chickenX = chickenPos[0];
                    int chickenY = chickenPos[1];

                    distance = Math.min(distance, Math.abs(houseX - chickenX) + Math.abs(houseY - chickenY));
                }

                sum += distance;
            }

            result = Math.min(result,  sum);
            return;
        }

        for(int i=start;i<chickens.size();i++) {
            selected[size] = i;
            combination(i + 1, size + 1);
        }
    }

}

