package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// 문제 제목: << 튜플 (Lv.2) >>
// 문자열에서 숫자만 남을 수 있도록 변환했다.
// 단점: 문자열 배열에서 각각의 문자열을 비교하고 새 배열을 만드는 작업에 시간이 많이 든다.
// 일단 가능하도록 만들어보고 시간 초과 발생 시 코드를 수정할 예정.

class Solution004 {
    public int[] solution(String s) {
    	List<Integer> list = new ArrayList<>(); // 실제 모양대로 담을 숫자 리스트
    	int[] numCnt = new int[100001];
        s = s.replace("{{", "").replace("}}", "");
        if(!s.contains("},{")) return new int[] {Integer.valueOf(s)}; // 입력 튜플이 하나일 경우
        
        String[] tuple = s.split("\\},\\{");
        Arrays.sort(tuple, (a,b) -> a.length() - b.length()); // 길이 순으로 정렬
        
        list.add(Integer.valueOf(tuple[0])); // 첫번째 수 저장
        numCnt[Integer.valueOf(tuple[0])]++;
        
        for(int i=1;i<tuple.length;i++) { // 각 숫자들 마다 숫자 개수를 비교해 늘어난 수를 list에 담기
        	String[] temp = tuple[i].split(",");
        	int[] tempNumCnt = new int[100001];
        	for(int j=0;j<temp.length;j++) {
        		tempNumCnt[Integer.valueOf(temp[j])]++;
        	}
        	for(int j=0;j<numCnt.length;j++) {
        		if(tempNumCnt[j] != numCnt[j]) {
        			list.add(j);
        			numCnt[j]++;
        			break;
        		}
        	}
        }
        
        int[] answer = new int[list.size()];
        for(int i=0;i<answer.length;i++) {
        	answer[i] = list.get(i);
        }
        return answer;
    }
}

public class Q004 {
	public static void main(String[] args) {
		Solution004 s = new Solution004();
		String str = "{{123}}";
		System.out.println(Arrays.toString(s.solution(str)));
	}
}
