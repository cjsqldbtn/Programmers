package level2;

import java.util.Arrays;
// 전화번호 목록
// 트리와 비슷한 구조를 생각. 완전 탐색할 때 전체를 한 번만 훑으면 됨
// 단점: 계속 트리를 그리고 비교 해야하기 때문에 메모리를 많이 잡는다.
// 될.. 까?
// 중간에 그냥 다 정렬하고 검사하려는 문자열이 이전 문자열의 길이보다 작아지면 
// break 걸면 연산 수가 비슷할 것이라는 생각이 들었으나, 유사 트리 구현해보기로 결정.
class Node {
	Node[] numarr;
	Node(){
		numarr = new Node[10];
	}
}
class Solution {
	public boolean solution(String[] phone_book) {
		Arrays.sort(phone_book, (a,b)->b.length()-a.length());
		System.out.println(Arrays.toString(phone_book));
		Node[] firstnumarr = new Node[10]; // 맨 첫 숫자를 담아줄 배열
		int cnt = 0;
		for(String i : phone_book) {
			int check = cnt;
			Node[] tempNodearr = firstnumarr;
			for(int j=0;j<i.length();j++) {
				int temp = i.charAt(j)-'0';
				if(tempNodearr[temp]==null) {
					tempNodearr[temp] = new Node();
					tempNodearr = tempNodearr[temp].numarr;
					cnt++;
				}
				else {
					tempNodearr = tempNodearr[temp].numarr;
				}
			}
			if(cnt==check) return false;
		}
		return true;
	}
}
public class Q003 {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.solution(new String[] {"123","12"}));
	}

}
