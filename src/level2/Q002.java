package level2;

import java.util.LinkedList;
import java.util.Queue;
// [1차] 캐시
// 해시셋으로 만드려다가 큐가 더 좋을 것 같다 생각이 들었다.

// 앞으로의 방향
// 아직 큐를 많이 안 써봐서 첫 선언에서 어떻게하나 찾아봐야 했다. 복습 필요.
class Solution002 {
    public int solution(int cacheSize, String[] cities) {
    	if(cacheSize==0) return 5*cities.length;
    	Queue<String> cacheQue = new LinkedList<>();
        int answer = 0;
        for(String i:cities) {
        	if(cacheQue.contains(i.toLowerCase())) {
        		answer++;
        		cacheQue.remove(i.toLowerCase());
        		cacheQue.add(i.toLowerCase());
        		continue;
        	}
        	if(cacheQue.size()<cacheSize) {
        		cacheQue.add(i.toLowerCase());
        	}else {
        		cacheQue.add(i.toLowerCase());
        		cacheQue.remove();
			}
        	answer+=5;
        }
        return answer;
    }
}
public class Q002 {

	public static void main(String[] args) {
		Solution002 s = new Solution002();
		String[] arr = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		System.out.println(s.solution(3, arr));
	}

}
