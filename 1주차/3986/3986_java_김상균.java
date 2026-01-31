import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 마지막으로 나온 게 A인데 그 다음 A가 나오기 전까지 B가 홀수개 나오면 좋은 단어가 아님

        // 마찬가지로 마지막으로 나온 게 B인데 그 다음 B가 나오기 전까지 A가 홀수개 나오면 좋은 단어가 아님

        int N = Integer.parseInt(br.readLine());

        int result = 0;

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            char[] arr = input.toCharArray();

            Stack<Character> stack = new Stack<>();

            for (int j = 0; j < arr.length; j++) {
                char c = arr[j];

                if (c == 'A') {
                    if (!stack.isEmpty()) {
                        char top = stack.peek();

                        if (top == 'A') {
                            stack.pop();
                        } else {
                            stack.push('A');
                        }
                    } else {
                        stack.push('A');
                    }
                } else {
                    if (!stack.isEmpty()) {
                        char top = stack.peek();

                        if (top == 'B') {
                            stack.pop();
                        } else {
                            stack.push('B');
                        }
                    } else {
                        stack.push('B');
                    }
                }
            }


            if (stack.isEmpty()) {
                result++;
            }

        }

        System.out.println(result);
    }

}
