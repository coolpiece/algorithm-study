import java.util.*;

public class java_3986_윤소윤 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int ans = 0;
		for (int i = 0; i < N; i++) {
			String word = sc.next();
			if(isWord(word)) ans++;
		}
		System.out.println(ans);
		sc.close();
	}
	
	static boolean isWord(String str) {
		boolean ans = true;
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			
			if(stack.isEmpty()) stack.push(c);
			else {
				if(c == stack.peek()) stack.pop();
				else {
					stack.push(c);
				}
			}
		}
		
		if (!stack.isEmpty()) ans = false;
		
		return ans;
	}
}
