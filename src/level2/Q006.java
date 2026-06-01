package level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 문제 제목: << [1차] 뉴스 클러스터링 (Lv.2) >>
// 해시맵을 이용해서 검색 속도를 높이고자 했다.
// 특수문자 처리까지는 완료 했으나, 다중집합에서 한 집합에서 같은 원소 aa, aa가 들어왔을 때 둘 다 저장해야함.
// 맵으로 바꾸고 int[]을 넣어서 각각 출현한 횟수를 저장해서 비교하는 식으로 변경.

class Solution006 {
    public int solution(String str1, String str2) {
    	str1 = str1.toLowerCase();
    	str2 = str2.toLowerCase();
    	Map<String,int[]> cntMap = new HashMap<>();
    	List<String> list = new ArrayList<>(); // key 담아둘 리스트
    	for(int i=0;i<str1.length()-1;i++) {
    		char c1 = str1.charAt(i);
    		char c2 = str1.charAt(i+1);
    		if(!(((c1>='A'&&c1<='Z')||(c1>='a'&&c1<='z'))&&((c2>='A'&&c2<='Z')||(c2>='a'&&c2<='z')))) continue;
    		String temp = "" + c1 + c2;
    		if(cntMap.containsKey(temp)) {
    			cntMap.get(temp)[0] += 1;
    		}else {
    			cntMap.put(temp, new int[] {1,0});
    			list.add(temp);
    		}
    	}
        for(int i=0;i<str2.length()-1;i++) {
        	char c1 = str2.charAt(i);
    		char c2 = str2.charAt(i+1);
    		if(!(((c1>='A'&&c1<='Z')||(c1>='a'&&c1<='z'))&&((c2>='A'&&c2<='Z')||(c2>='a'&&c2<='z')))) continue;
        	String temp = "" + c1 + c2;
        	if(cntMap.containsKey(temp)) {
    			cntMap.get(temp)[1] += 1;
    		}else {
    			cntMap.put(temp, new int[] {0,1});
    			list.add(temp);
    		}
        }
        int intersect = 0;
        int total = 0;
        for(int i=0;i<list.size();i++) {
        	int str1Num = cntMap.get(list.get(i))[0];
        	int str2Num = cntMap.get(list.get(i))[1];
        	if(str1Num>str2Num) {
        		intersect+=str2Num;
        		total+=str1Num;
        	}else {
				intersect+=str1Num;
				total+=str2Num;
			}
        }
        return cntMap.isEmpty() ? 65536 : (int)(1.0*intersect/total*65536); // 공집합이라면 1 리턴
    }
}
public class Q006 {
	public static void main(String[] args) {
		Solution006 s = new Solution006();
		String str1 = "aa1+aa2";
		String str2 = "AAAA12";
		System.out.println(s.solution(str1, str2));
	}
}
