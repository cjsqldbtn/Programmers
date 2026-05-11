package level1;
// 문제 제목: << 노란불 신호등 (Lv.1) >>
// 신호가 모두 노란불이 되는 시간을 리턴, 불가능하다면 -1리턴
// 노란불인지 확인하는 메서드를 만들어서 시간마다 상태를 확인하도록 함

// 아쉬운 점
// 1. 각 신호의 주기를 확인해서 최소공배수로 반복문을 돌려서 안 되는 경우가 있는지 확인해야하는데
// 최소공배수까지 구하지 않고 문제의 제한 조건을 보고 3200000을 넣어줬다.. ... 불필요한 연산이 될 수 있다.
class Solution004 {
	// 노란불일 경우 true리턴
    static boolean[] signal(int[] arr){
        boolean[] sign = new boolean[arr[0]+arr[1]+arr[2]];
        int idx=0;
        for(int i=0;i<arr[0];i++){
            sign[idx++]=false;
        }
        for(int i=0;i<arr[1];i++){
            sign[idx++]=true;
        }
        return sign;
    }
    
    public int solution(int[][] signals) {
        boolean[][] mysigns = new boolean[signals.length][]; // 신호별 노란불이 true표시 될 boolean배열
        for(int i=0;i<mysigns.length;i++){
            mysigns[i] = signal(signals[i]);
        }
        for(int i=0;i<3200000;i++){ // 공배수 대신 제한 조건으로 구한 반복 횟수 
            for(int j=0;j<mysigns.length;j++){
                if(!mysigns[j][i%mysigns[j].length]) break;
                if(j!=mysigns.length-1) continue; // 모든 신호가 될 때까지 확인
                return i+1;
            }
        }
        return -1;
    }
}
public class Q004 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
