package level5;

import java.util.HashMap;
// 문제 제목: << 방의 개수 (Lv.5) >>
// 이미 가 본 적 있는 점일 때 길이 열려있다면 방의 개수를 추가.
// 단, 직전에 방문한 점인 경우 추가하지 않는다.
// 한 칸 내에서 x 경로 일 때 많은 문제를 겪음 -> 결국 좌료를 다시 나눠 해결(for문을 또 넣을 수밖에. ..)

// 아쉬운 점
// 1. 처음 짤 때 열린 경로, 닫힌 경로 표시를 String charAt()으로 arrows 관리가 쉬워서 그렇게 설정.
// 하지만, boolean배열로 저장했으면 좀 더 성능이 좋았을 같다.
// 2. 직전에 방문한 점을 표시 하는 참조변수가 지저분하다.

// 앞으로의 방향
// 해시에서 hashCode(), equals() 오버라이딩 공부 필요
// x y 값이 같으면 같은 객체다를 설정하는 부분에서 검색의 도움을 받았다.

// 개인적 코멘트
// 답안 제출하고 다른 사람 코드를 보는데 나와 비슷해서 너무 놀람. for(for()) 마저..
class Solution001 {
    static HashMap<Point,String> myPoints = new HashMap<>(); // 만들어진 점과 각 점의 열린 길을 표시할 맵
    static int x = 0; // 현재 점의 x좌표
    static int y = 0; // 현재 점의 y좌표
    // x, y 좌표를 담을 점
    class Point {
        int x;
        int y;
        Point(){}
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        // x y 가 같다면 같은 점
        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return 31 * x + y;
        }
        // 테스트용 출력
        @Override
        public String toString() {
            return "("+x+", "+y+")";
        }
    }
    public int solution(int[] arrows) {
        int answer = 0;
        Point p3 = new Point(); // 맨 처음에는 첫 출발지 (0,0) 점 생성, 이후에는 직전에 들린 점을 담을 참조 변수로 사용
        myPoints.put(p3,"00000000");
        for(int i=0;i<arrows.length;i++){
            Point p4 = new Point(x, y); // 현재 위치의 점 임시로 받아둠(직전에 들린 점을 저장하기 위함)
            for(int j=0;j<2;j++){
                Point p = new Point(x, y); // 안에서 작게 만들어질(x모양 체크) 점 생성
                StringBuilder sb = new StringBuilder(myPoints.get(p));
                sb.setCharAt(arrows[i],'1');
                myPoints.replace(p, sb.toString());
                switch (arrows[i]) {
                    case 0:
                        y++;
                        break;
                    case 1:
                        x++;
                        y++;
                        break;
                    case 2:
                        x++;
                        break;
                    case 3:
                        x++;
                        y--;
                        break;
                    case 4:
                        y--;
                        break;
                    case 5:
                        x--;
                        y--;
                        break;
                    case 6:
                        x--;
                        break;
                    case 7:
                        x--;
                        y++;
                        break;
                }
                Point p2 = new Point(x, y); // 이동 후의 점
                int a = arrows[i]>3 ? arrows[i]-4 : arrows[i]+4;
                if(myPoints.containsKey(p2)){ // 이미 들린 점인지 확인
                    if(!p3.equals(p2)){ // 직전에 들린 점인지 확인
                        if(myPoints.get(p2).charAt(a)=='0') { // 길이 열려있는지 확인
                            answer++; // 여기까지 왔다면 방이 1개 생성됨
                        }
                    }
                }
                else { // 들리지 않은 점을 추가
                    myPoints.put(p2, "00000000");
                }
                StringBuilder sb2 = new StringBuilder(myPoints.get(p2));
                sb2.setCharAt(a,'1'); // 왔던 길 막기
                myPoints.replace(p2, sb2.toString());
            }
            p3 = p4; // 직전에 들린 점 저장
        }
        return answer;
    }
}
public class Q001 {
	public static void main(String[] args) {
		
	}
}