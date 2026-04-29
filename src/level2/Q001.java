package level2;

import java.util.ArrayList;
import java.util.List;
// 기능개발
// 개발이 완료되지 않은 기능을 yetlist로 관리
// 완료된 일을 list에서 제거하면서 list 사이즈가 달라져 뒤에 있는 다른 업무가 진행되지 않는 문제를 겪음
// -> 완료된 일을 -1로 바꿔서 -1을 제거 하는 식으로 해결

// 아쉬운 점
// 1. List 사용 시 제거해서 인덱스나 사이즈가 달라지는 것은 늘 겪는 문제다. 휴먼에러가 너무 자주 일어난다..
// 2. 큐/스택으로 문제가 분류되어 있었는데... 스택은 아닌 것 같고 큐인걸까? 좀 더 고민이 필요하다.

// 앞으로의 방향
// 큐/스택에 대한 공부가 필요하다
class Solution001 {
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
public class Q001 {
	public static void main(String[] args) {
		Solution a = new Solution();
		System.out.println(a.solution(new int[] {95,95,95,95}, new int[] {4,3,2,1}));
	}

}
