package level1;

import java.util.Set;
import java.util.LinkedHashSet;
// 문제 제목: << 중요한 단어를 스포 방지 (Lv.1) >>
// 스포 처리된 문자를 다 감싸고 잘라내서 Set에 넣는 것으로 해결

// 아쉬운 점
// 처음부터 방법을 잘 잡고 갔는데 단 한가지 테스트케이스에서 계속 런타임에러 발생
// 스포방지 처리에 공백이 있을 때 돌리는 while 문에서 charAt(start)에 접근할 수 없을 때 발생한 오류였다.
// 아무것도 실행 안 하는 25행을 넣어서 해결했는데 뭔가...찝찝...

// 앞으로의 방향
// while문 돌릴 때 양 끝 값 확인하기. 두번 세번 확인하기.
class Solution002 {
    public int solution(String message, int[][] spoiler_ranges) {
        StringBuilder sb = new StringBuilder(message); // 스포 방지를 -로 표시할 빌더
        Set<String> set = new LinkedHashSet<>();

        // 스포일러 문자가 중간에 끊기지 얂게 모두 감싸도록 int[][] 수정
        for(int i=0;i<spoiler_ranges.length;i++){
            int start = spoiler_ranges[i][0];
            int end = spoiler_ranges[i][1];
            if(start!=0&&start!=message.length()-1){
                if(message.charAt(start)==' '){}
                else{
                    while(!(message.charAt(start)==' ')){
                        start--;
                        if(start==0) {
                            start--;
                            break;
                        }
                    }
                    spoiler_ranges[i][0] = start+1;
                }
            }
            if(end!=message.length()-1){
                while(!(message.charAt(end)==' ')){
                    end++;
                    if(end==message.length()-1){
                        break;
                    }
                }
            }
            spoiler_ranges[i][1] = end;
            
            // 빌더에 스포일러 문자를 -로 표시
            if(spoiler_ranges[i][1]==message.length()-1) sb.setCharAt(message.length()-1, '-');
            sb.replace(spoiler_ranges[i][0], spoiler_ranges[i][1],"-".repeat(spoiler_ranges[i][1]-spoiler_ranges[i][0]));

            // 스포 문자를 집합에 담기
            String temp;
            if(spoiler_ranges[i][1]==message.length()-1) temp = message.substring(spoiler_ranges[i][0]);
            else temp = message.substring(spoiler_ranges[i][0],spoiler_ranges[i][1]);
            if(temp.contains(" ")){ // 여러 단어가 들어갈 경우 나눔
                String[] split = temp.split(" ");
                for(String s : split){
                    if(s.length()==0) continue;
                    set.add(s);
                }
            }
            else set.add(temp);
        }

        String chmessage = sb.toString();
        chmessage = chmessage.replace("-", "");
        System.out.println(chmessage);
        String[] check = chmessage.split(" "); // 스포 아닌 문자들이 담김
        int answer = 0;
        for(String i:set){
            boolean flag = true;
            for(String j:check){
                if(i.equals(j)){
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
        }
        return answer;
    }
}
public class Q002 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
