package level1;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
// [PCCE 기출문제] 10번 / 공원
// 행, 열 다 들어갈 수 있는 지 확인해서 리턴.

class Solution003 {
	// 들어갈 수 있는 자리를 표시할 포인트
    class Point {
        int x; //열
        int y; //행
        Point(int x,int y) {this.x = x; this.y = y;}
        @Override
        public String toString() {
            return "("+x+", "+y+")";
        }
    }
    public int solution(int[] mats, String[][] park) {
    	// 각 사이즈에 들어갈 매트마다 위치를 담아줄 리스트 연결
        Arrays.sort(mats); // 매트 크기 정렬
        List<List> checkmats = new ArrayList<>();
        for(int i=0;i<mats.length;i++){
            checkmats.add(new ArrayList<Point>());
        }
        
        // 인덱스오류 방지 공원 확장, 연속된 칸들에 수 라벨링
        int[][] intpark = new int[park.length+2][park[0].length+2];
        for(int i=0;i<park.length;i++){
            for(int j=0;j<park[0].length;j++){
                if(park[i][j].equals("-1")) {
                    intpark[i+1][j+1] = intpark[i+1][j]+1; // 연속된 경우 늘려줌
                    for(int k=0;k<mats.length;k++){
                        if(i>park.length-mats[k]) continue; // 옆으로 여분의 공간이 없음
                        if(intpark[i+1][j+1]>=mats[k]) checkmats.get(k).add(new Point(i+1, j+1)); //들어갈 수 있는 가로 포인트
                    }
                }
            }
        }
        
        // 각각의 가능한 가로 포인트마다 열도 들어갈 수 있는 지 확인
        for(int i=0;i<mats.length;i++){
            int idx = mats.length-1-i; // 가장 큰 매트부터 확인
            List<Point> temp = checkmats.get(idx);
            for(int j=0;j<temp.size();j++){
                int temx = temp.get(j).x;
                int temy = temp.get(j).y;
                boolean flag = true;
                for(int k=1;k<mats[idx];k++){
                    if(intpark[temx+k][temy]<mats[idx]) {
                        flag = false; //열을 만족하지 못함
                        break;
                    }
                }
                if(flag) return mats[idx];
            }
        }
        return -1; //여기까지 왔다면 들어갈 수 없음
    }
}
public class Q003 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
