package level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 문제 제목: << [3차] 압축 (Lv.2) >>
// 해시맵을 이용해서 검색 속도를 높이고자 했다.
// while문을 통해서 맵에 존재 여부를 확인하고 while 내에서 외부 for문 조건을 확인하기 때문에 flag 변수 선언이 필요.

class Solution007 {
    static Map<String, Integer> dic = new HashMap<>(); // 압축사전
    static int val = 1; // 압축 값

    public int[] solution(String msg) {
        // dic 초기화
        for (char i = 'A'; i <= 'Z'; i++) {
            dic.put(i + "", val++);
        }

        // 반환할 int List
        List<Integer> list = new ArrayList<>();
        // 내부 조건으로 밖 for 문 제어
        Boolean flag = true;

        for (int idx = 0; idx < msg.length() && flag; idx--) {
            String check = "" + msg.charAt(idx++);
            while (dic.containsKey(check)) {
                if (idx >= msg.length()) { // check의 길이가 문자열 끝까지 도달하는 경우
                    flag = false; // 외부 for문도 더이상 돌지 않는다.
                    break;
                }
                check += msg.charAt(idx++);
            }
            if (flag) {
                list.add(dic.get(check.substring(0, check.length() - 1))); // 끝까지 돌지 않은 경우 이전 값을 저장
                dic.put(check, val++); // 현재 값을 사전에 추가
            } else {
                list.add(dic.get(check)); // 끝까지 돈 경우 마지막 값을 저장
            }
            // System.out.println(check);
        }
        // System.out.println(list);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        return answer;
    }
}

public class Q007 {
    public static void main(String[] args) throws Exception {
        Solution007 s = new Solution007();
        System.out.println(Arrays.toString((s.solution("TOBEORNOTTOBEORTOBEORNOT"))));
    }
}
