package level1;

import java.time.LocalTime;

// 문제 제목: << [PCCP 기출문제] 1번 / 동영상 재생기 (Lv.1) >>
// 오프닝 구간에 있는 경우 건너뛴다 -> 해당 부분은 입력이 없어도 진행되어야하기 때문애 반복적으로 사용될 것이다.

class Solution006 {
    public LocalTime skipOpening(LocalTime currentPosition, LocalTime openingStartTime, LocalTime openingEndTime) {
        if ((currentPosition.isAfter(openingStartTime) && currentPosition.isBefore(openingEndTime))
                || currentPosition.equals(openingStartTime) || currentPosition.equals(openingEndTime)) {
            return openingEndTime;
        }
        return currentPosition;
    }

    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        LocalTime currentPosition = LocalTime.parse("00:" + pos);
        LocalTime endTime = LocalTime.parse("00:" + video_len);
        LocalTime openingStartTime = LocalTime.parse("00:" + op_start);
        LocalTime openingEndTime = LocalTime.parse("00:" + op_end);

        currentPosition = skipOpening(currentPosition, openingStartTime, openingEndTime);

        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];

            switch (command) {
                case "next":
                    currentPosition = currentPosition.plusSeconds(10);
                    if (currentPosition.isAfter(endTime)) {
                        currentPosition = endTime;
                    }
                    break;
                case "prev":
                    if (currentPosition.compareTo(LocalTime.of(0, 0, 10)) <= 0) {
                        currentPosition = LocalTime.of(0, 0, 0);
                    } else {
                        currentPosition = currentPosition.minusSeconds(10);
                    }
                    break;
                default:
                    // Invalid command, ignore
                    break;
            }

            // 어떤 경우이든 마지막 지점이 오프닝인 경우 건너뛴다.
            currentPosition = skipOpening(currentPosition, openingStartTime, openingEndTime);
        }

        String answer = String.format("%02d", currentPosition.getMinute()) + ":"
                + String.format("%02d", currentPosition.getSecond());
        return answer;
    }
}

public class Q006 {
    public static void main(String[] args) {
        Solution006 solution = new Solution006();
        System.out.println(
                solution.solution("30:00", "00:08", "00:00", "00:05", new String[] { "prev" }));
    }
}
