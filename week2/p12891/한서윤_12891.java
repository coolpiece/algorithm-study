import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int s = Integer.parseInt(st.nextToken()), p = Integer.parseInt(st.nextToken());
		String str = br.readLine() + " ";
		st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken()), c = Integer.parseInt(st.nextToken()),
				g = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken()), rst = 0;
		for (int i = 0; i < p; i++) { // 초기값.
			if (str.charAt(i) == 'A')
				a--;
			else if (str.charAt(i) == 'C')
				c--;
			else if (str.charAt(i) == 'G')
				g--;
			else if (str.charAt(i) == 'T')
				t--;
		}
		for (int i = p; i < s + 1; i++) { // 한 칸씩 오른쪽으로 이동.
			if (a <= 0 && c <= 0 && g <= 0 && t <= 0)
				rst++;
			if (str.charAt(i - p) == 'A')
				a++;
			else if (str.charAt(i - p) == 'C')
				c++;
			else if (str.charAt(i - p) == 'G')
				g++;
			else if (str.charAt(i - p) == 'T')
				t++;
			if (str.charAt(i) == 'A')
				a--;
			else if (str.charAt(i) == 'C')
				c--;
			else if (str.charAt(i) == 'G')
				g--;
			else if (str.charAt(i) == 'T')
				t--;
		}
		System.out.println(rst);
	}
}