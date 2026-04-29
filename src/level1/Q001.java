package level1;

import java.util.*;
// 미해결
// 달리기 경주
// 추월한 선수를 계속 찾아가며 순서를 바꾸면 될 문제이나, 시간 초과 발생
// 효율을 생각해서 등수와 불린 수를 Map에 담아보려 했음
// 그러나.. 등수가 바뀌지 않아서 정확한 값이 나오지 않음
// 어제 자다가 생각났는데 그냥 맵을 두개 만들면 될 것 같음.. 유레카.
class Solution {
	public String[] solution(String[] players, String[] callings) {
		Map<String,Integer[]> playerMap = new HashMap<>();
		List<String> list = new ArrayList<>();
		int idx = 1;
		for(String i:players){
			Integer[] temp = new Integer[2];
			temp[0] = idx++;
			temp[1] = 0;
			playerMap.put(i, temp);
			list.add(i);
		}
		for(String i:callings){
			Integer[] temp = playerMap.get(i);
			temp[0]--; //등수 값을 앞으로
			temp[1]++; //불린 수 카운트
			playerMap.replace(i, temp);
		}
		list.sort(new Comparator<String>() {
			@Override
			public int compare(String str1, String str2) {
				Integer[] o1 = playerMap.get(str1);
				Integer[] o2 = playerMap.get(str2);
				if(o1[0]>o2[0]) return 1;
				else if(o1[0]==o2[0]){
					if(o1[1]>o2[1]) return -1;
					else return 1;
				}
				return -1;
			}
		});
		String[] answer = new String[list.size()];
		for(int i=0;i<answer.length;i++){
			answer[i]=list.get(i);
		}
		return answer;
	}
}
public class Q001 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
