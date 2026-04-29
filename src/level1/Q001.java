package level1;

import java.util.*;
// 달리기 경주
// 추월한 선수를 계속 찾아가며 순서를 바꾸면 될 문제이나, 시간 초과 발생
// 해시맵을 둘 만들어서 찾는 시간을 줄임

// 아쉬운 점
// 1. 맵이 하나면 된다.
// 다른 사람의 풀이를 보니 그냥 이름 키인 해시맵만 만들고 배열에 바로 반영하면 된다.
// 그럼 배열에서 그 등수인 이름을 뽑아서 맵의 키로 쓰면 돠니까.

// 앞으로의 방향
// String 타입은 찾는데 시간이 많이 걸려서 Map이 도움이 된다.
// 그런데 이거 DB로 관리하게 되지 않나..?
class Solution {
	public String[] solution(String[] players, String[] callings) {
		Map<String,Integer> playerMapS = new HashMap<>();
		Map<Integer,String> playerMapI = new HashMap<>();
		int idx = 0;
		for(String i:players){
			playerMapS.put(i, idx);
			playerMapI.put(idx++, i);
		}
		for(String i:callings){
			int tempRank = playerMapS.get(i);
			String changePl = playerMapI.get(tempRank-1);
			playerMapS.replace(i, tempRank-1);
			playerMapS.replace(changePl, tempRank);
			playerMapI.replace(tempRank, changePl);
			playerMapI.replace(tempRank-1, i);
		}
		for(int i=0;i<players.length;i++) {
			players[i] = playerMapI.get(i);
		}
		return players;
	}
}
public class Q001 {
	public static void main(String[] args) {

	}

}
