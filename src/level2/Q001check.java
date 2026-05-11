package level2;

import java.util.ArrayList;
import java.util.List;
// 문제 제목: << 기능개발 (Lv.2) >>
// 개발이 완료되지 않은 기능을 yetList로 관리
// 완료된 일을 list에서 제거하면서 list 사이즈가 달라져 뒤에 있는 다른 업무가 진행되지 않는 문제를 겪음
// -> 완료된 일을 -1로 바꿔서 -1을 제거 하는 식으로 해결

// 아쉬운 점
// 1. List 사용 시 제거해서 인덱스나 사이즈가 달라지는 것은 늘 겪는 문제다. 휴먼에러가 너무 자주 일어난다..
// 2. 큐/스택으로 문제가 분류되어 있었는데... 스택은 아닌 것 같고 큐인걸까? 좀 더 고민이 필요하다.

// 앞으로의 방향
// 큐/스택에 대한 공부가 필요하다
// 어느정도 준비가 되면 이 문제 다시 풀어보자.
class Solution001 {
	public int[] solution(int[] progresses, int[] speeds) {
		List<Integer> answerList = new ArrayList<>(); // 몇개의 기능이 배포되는지 담을 리스트
		List<Integer> yetList = new ArrayList<>(); // 아직 개발이 완료 되지 않은 기능의 인덱스
		for(int i=0;i<progresses.length;i++) {
			yetList.add(i);
		}
		for(int i=0;i<yetList.size();) {
			while(progresses[yetList.get(0)]<100) { // 전부 개발 될 때까지 진행
				for(int j=0;j<yetList.size();j++) {
					progresses[yetList.get(j)]+=speeds[yetList.get(j)];
				}
				for(int j=0;j<yetList.size();j++) {
					if(j!=0&&progresses[yetList.get(j)]>=100) {
						yetList.set(j,-1); // 완료된 기능을 -1 처리
					}
				}
				while (yetList.contains(-1)) {
					yetList.remove(yetList.indexOf(-1)); // 완료된 기능 지우기
				}
			}
			int cnt = 0; // 한번에 배포될 완료된 기능의 수
			for(int j=yetList.get(0);j<progresses.length;j++) {
				if(progresses[j]<100) break;
				cnt++;
			}
			answerList.add(cnt);
			yetList.remove(0); // 다음 개발되지 않는 인덱스로 이동
		}
		// System.out.println(answerList);
		int[] answer = new int[answerList.size()]; // 배열로 변경
		for(int i=0;i<answer.length;i++) {
			answer[i]=answerList.get(i);
		}
		return answer;
	}
}
public class Q001check {
	public static void main(String[] args) {
		Solution001 a = new Solution001();
		System.out.println(a.solution(new int[] {95,95,95,95}, new int[] {4,3,2,1}));
	}

}
