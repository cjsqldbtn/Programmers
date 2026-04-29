package level1;

import java.util.ArrayList;
import java.util.List;
// 택배 상자 꺼내기
// 꼬불하게 쌓는 형태 자체를 list에 저장했다.

// 앞으로의 방향
// 배열 안에 리스트를 넣는 과정에서 노란 경고줄을 확인
// List<Integer>[] 가 내부적으로 List<?>[] 로 받아들여지면서 오류가 생길 수 있음을 암시
// 제너릭타입에 대한 공부가 필요.
class Solution005 {
    public int solution(int n, int w, int num) {
        List<Integer>[] arr = new ArrayList[w];
        for(int i=0;i<w;i++){
            arr[i] = new ArrayList<>();
        }
        for(int i=0;i<n;i++){
            if(i/w%2==0) arr[i%w].add(i+1);
            else arr[w-1-(i%w)].add(i+1);
        }
        for(List<Integer> i : arr){
            if(i.contains(num)) {
                return i.size() - i.indexOf(num);
            }
        }
        return 0;
    }
}
public class Q005 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
