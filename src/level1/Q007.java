package level1;

import java.time.LocalTime;
import java.util.Arrays;

// 문제 제목: << [PCCP 기출문제] 1번 / 동영상 재생기 (Lv.1) >>
// 오프닝 구간에 있는 경우 건너뛴다 -> 해당 부분은 입력이 없어도 진행되어야하기 때문애 반복적으로 사용될 것이다.

class Solution007 {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int currentHealth = health;
        int lastAttackTime = 0;

        Arrays.sort(attacks, (a, b) -> Integer.compare(a[0], b[0])); // 공격 시간 기준으로 정렬

        for (int i = 0; i < attacks.length; i++) {
            int attackTime = attacks[i][0];
            int damage = attacks[i][1];

            // 공격 사이 붕대 사용
            currentHealth = Math.min(currentHealth + (attackTime - lastAttackTime - 1) / bandage[0] * bandage[2] // 연속
                                                                                                                 // 성공 힐
                    + (attackTime - lastAttackTime - 1) * bandage[1], maxHealth);
            // 공격 받음
            currentHealth -= damage;
            lastAttackTime = attackTime;

            if (currentHealth <= 0) {
                break; // 체력이 0 이하이면 더 이상 진행하지 않음
            }
        }
        return currentHealth > 0 ? currentHealth : -1;
    }
}

public class Q007 {
    public static void main(String[] args) {
        Solution007 solution = new Solution007();
        System.out.println(
                solution.solution(new int[] { 5, 1, 5 }, 30,
                        new int[][] { { 2, 10 }, { 9, 15 }, { 10, 5 }, { 11, 5 } }));
    }
}
