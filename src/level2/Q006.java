package level2;

import java.util.HashSet;
import java.util.Set;
//문제 제목: << [1차] 뉴스 클러스터링 (Lv.2) >>
//해시셋을 이용해서 검색 속도를 높이고자 했다.
//합집합은 최종 해시셋의 크기가 될 것이고, 교집합은 따로 개수를 저장했다.
//아.. 특수문자 처리가 있네... 일단 만든다. 특수문자 없는 문자열까지는 ok.

class Solution006 {
    public int solution(String str1, String str2) {
    	str1 = str1.toLowerCase();
    	str2 = str2.toLowerCase();
    	Set<String> set = new HashSet<>();
    	for(int i=0;i<str1.length()-1;i++) {
    		set.add("" + str1.charAt(i) + str1.charAt(i+1));
    	}
        int intersect = 0;
        for(int i=0;i<str2.length()-1;i++) {
        	String temp = "" + str2.charAt(i) + str2.charAt(i+1);
        	if(set.contains(temp)) intersect++;
        	set.add(temp);
        }
        return set.isEmpty() ? 1 : (int)(1.0*intersect/set.size()*65536); //둘 모두 공집합이라면 1 리턴
    }
}
public class Q006 {
	public static void main(String[] args) {
		Solution006 s = new Solution006();
		String str1 = "FRANCE";
		String str2 = "french";
		System.out.println(s.solution(str1, str2));
	}
}
