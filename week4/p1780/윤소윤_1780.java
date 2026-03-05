package p1780;

import java.io.*;
import java.util.*;

public class 윤소윤_1780 {
    public static int [][] map;
    public static int Gray = 0, White = 0, Black = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        map = new int [N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        partition(0,0,N);
        System.out.println(Gray);
        System.out.println(White);
        System.out.println(Black);
    }

    public static void partition(int row, int col, int size) {
        if(isSame(row, col, size)) {
            switch (map[row][col]) {
                case -1: Gray++; break;
                case 0: White++; break;
                case 1: Black++; break;
            }
        }

        int newSize = size / 3;
        partition(row,col,newSize); // 왼쪽위
        partition(row,col+newSize,newSize); // 중앙위
        partition(row, col + 2*newSize, newSize); // 오른쪽위

        partition(row+newSize, col, newSize);
        partition(row+newSize,col+newSize,newSize);
        partition(row+newSize, col + 2*newSize, newSize);

        partition(row+2*newSize, col, newSize);
        partition(row+2*newSize,col+newSize,newSize);
        partition(row+2*newSize, col + 2*newSize, newSize);

    }

    // 2차원 배열이 모두 같은 수로 이루어졌는지 확인
    public static boolean isSame(int row, int col, int size) {
        int n = map[row][col];

        for (int i = row; i < row+size; i++) {
            for (int j = col; j < col+size; j++) {
                if(n != map[i][j]) return false;
            }
        }
        return true;
    }
}