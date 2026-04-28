package level2;

import java.util.ArrayList;
import java.util.List;

// 달리기 경주 (미해결)
class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		List<Integer> answerlist = new ArrayList<>();
		List<Integer> yetlist = new ArrayList<>();
		for(int i=0;i<progresses.length;i++) {
			yetlist.add(i);
		}
		for(int i=0;i<yetlist.size();) {
			while(progresses[yetlist.get(0)]<100) {
				for(int j=0;j<yetlist.size();j++) {
					progresses[yetlist.get(j)]+=speeds[yetlist.get(j)];
				}
				for(int j=0;j<yetlist.size();j++) {
					if(j!=0&&progresses[yetlist.get(j)]>=100) {
						yetlist.set(j,-1);
					}
				}
				while (yetlist.contains(-1)) {
					yetlist.remove(yetlist.indexOf(-1));
				}
			}
			int cnt = 0;
			for(int j=yetlist.get(0);j<progresses.length;j++) {
				if(progresses[j]<100) break;
				cnt++;
			}
			answerlist.add(cnt);
			yetlist.remove(0);
		}
		System.out.println(answerlist);
		int[] answer = new int[answerlist.size()];
		for(int i=0;i<answer.length;i++) {
			answer[i]=answerlist.get(i);
		}
		return answer;
	}
}
public class Q1 {
	public static void main(String[] args) {
		Solution a = new Solution();
		System.out.println(a.solution(new int[] {95,95,95,95}, new int[] {4,3,2,1}));
	}

}
