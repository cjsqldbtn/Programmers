package level5;

import java.util.HashMap;
// 방의 개수
// 이미 가 본 적 있는 점일 때 길이 열려있다면 방의 개수를 추가.
// 단, 직전에 방문한 점인 경우 추가하지 않는다.
// 한 칸 내에서 x 경로 일 때 많은 문제를 겪음 -> 결국 좌료를 다시 나눠 해결

// 아쉬운 점
// 처음 코드를 짤 때 String으로 열린 경로와 닫힌 경로를 저장했는데
// boolean배열로 저장했으면 좀 더 성능이 좋았을 같다.
// 직전에 방문한 점을 표시 하는 참조변수가 지저분하다.

// 앞으로의 방향
// 해시에 대한 공부가 되어 있지 않아, x y 값이 같으면 같은 객체다를 설정하는 부분에서 검색의 도움을 받았다.
class Solution {
    static HashMap<Point,String> myPoints = new HashMap<>();
    static int x = 0;
    static int y = 0;
    class Point {
        int x;
        int y;
        Point(){}
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        @Override
        public boolean equals(Object obj) {
            Point p = (Point) obj;
            return this.x == p.x && this.y == p.y;
        }
        @Override
        public int hashCode() {
            return 31 * x + y;
        }
        @Override
        public String toString() {
            return "("+x+", "+y+")";
        }
    }
    public int solution(int[] arrows) {
        int answer = 0;
        myPoints.put(new Point(),"00000000");
        Point p3 = new Point();
        for(int i=0;i<arrows.length;i++){
            Point p4 = new Point(x, y);
            for(int j=0;j<2;j++){
                Point p = new Point(x, y);
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
                Point p2 = new Point(x, y);
                int a = arrows[i]>3 ? arrows[i]-4 : arrows[i]+4;
                if(myPoints.containsKey(p2)){
                    if(!p3.equals(p2)){
                        if(myPoints.get(p2).charAt(a)=='0') {
                            answer++;
                        }
                    }
                }
                else {
                    myPoints.put(p2, "00000000");
                }
                StringBuilder sb2 = new StringBuilder(myPoints.get(p2));
                sb2.setCharAt(a,'1');
                myPoints.replace(p2, sb2.toString());
            }
            p3 = p4;
        }
        return answer;
    }
}
public class Q1 {
	public static void main(String[] args) {
		
	}
}