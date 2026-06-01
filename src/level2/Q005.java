package level2;

import java.util.Arrays;
// 문제 제목: << 주식가격 (Lv.2) >>
// 스택, 큐로 문제가 분류되어 있지만 일단 떠오른 방식(일일이 비교)으로 구현
// 이후 다른 사람 풀이로 스택에 인덱스 넣는 것보고 감탄했다.

class Solution005 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        for(int i=0;i<answer.length;i++) {
        	int temp = 0;
        	for(int j=i+1;j<answer.length;j++) {
        		temp++;
        		if(prices[i]>prices[j]) break;
        	}
        	answer[i] = temp;
        }
        return answer;
    }
}
public class Q005 {
	public static void main(String[] args) {
		Solution005 s = new Solution005();
		int[] arr = {2,1,2,3,1,1,2};
		System.out.println(Arrays.toString(s.solution(arr)));
	}
}
