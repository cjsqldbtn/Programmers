package level2;

import java.util.Arrays;
// 문제 제목: << 전화번호 목록 (Lv.2) >>
// 트리와 비슷한 구조를 생각. 완전 탐색할 때 전체를 한 번만 훑으면 됨
// 단점: 계속 트리를 그리고 비교 해야하기 때문에 메모리를 많이 잡는다.
// 될.. 까?
// 중간에 그냥 다 정렬하고 검사하려는 문자열이 이전 문자열의 길이보다 작아지면 
// break 걸면 연산 수가 비슷할 것이라는 생각이 들었으나, 유사 트리 구현해보기로 결정.

// 자체 구현한 트리에서 사용할 Node 클래스
class Node {
	Node[] numArr;
	Node(){
		numArr = new Node[10];
	}
}

class Solution003 {
	public boolean solution(String[] phoneBook) {
		Arrays.sort(phoneBook, (a,b)->b.length()-a.length());
		System.out.println(Arrays.toString(phoneBook));
		Node[] firstNumArr = new Node[10]; // 맨 첫 숫자를 담아줄 배열
		int cnt = 0; // 생성된 노드의 수
		for(String i : phoneBook) {
			int check = cnt; // 이전 노드의 수
			Node[] tempNodeArr = firstNumArr;
			for(int j=0;j<i.length();j++) { // 해당 문자열의 숫자들에 대응되는 노드를 생성
				int temp = i.charAt(j)-'0';
				if(tempNodeArr[temp]==null) { // 노드가 없다면 생성
					tempNodeArr[temp] = new Node();
					tempNodeArr = tempNodeArr[temp].numArr;
					cnt++;
				}
				else { // 해당 노드로 이동
					tempNodeArr = tempNodeArr[temp].numArr;
				}
			}
			// 노드가 생성되지 않았기에 앞부분이 동일함
			if(cnt==check) return false;
		}
		// 여기까지 왔다면 모두가 시작되지 않음
		return true;
	}
}

public class Q003 {
	public static void main(String[] args) {
		Solution003 s = new Solution();
		System.out.println(s.solution(new String[] {"123","12"}));
	}
}
