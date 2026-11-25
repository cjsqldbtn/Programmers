package level1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

// 문제 제목: << 카카오 블라인드 채용 : 신고 결과 받기 (Lv.1) >>
// 동일한 신고 결과는 반영하지 않음 -> 중복 제거가 필요하므로 set 이용

class Solution008 {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 중복 제거를 위한 Set
        Set<String> reportSet = new HashSet<>();
        for (int i = 0; i < report.length; i++) {
            reportSet.add(report[i]);
        }

        // 신고 결과를 저장할 Map
        Map<String, Integer> resultMap = new HashMap<>();
        for (String r : reportSet) {
            String[] split = r.split(" ");
            String reported = split[1];

            resultMap.put(reported, resultMap.getOrDefault(reported, 0) + 1);
        }

        // 정지된 아이디 추출
        Set<String> stopIds = new HashSet<>();
        for (String id : resultMap.keySet()) {
            if (resultMap.get(id) >= k) {
                stopIds.add(id);
            }
        }

        // 메일 결과를 저장할 Map
        Map<String, Integer> resultMailMap = new HashMap<>();
        for (String r : reportSet) {
            String[] split = r.split(" ");
            String reporter = split[0];
            String reported = split[1];

            if (stopIds.contains(reported)) {
                resultMailMap.put(reporter, resultMailMap.getOrDefault(reporter, 0) + 1);
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            answer[i] = resultMailMap.getOrDefault(id, 0);
        }
        return answer;
    }
}

// 다른 사람의 풀이
// 아름다운 Stream이다...

class SolutionOther008 {
    public int[] solution(String[] id_list, String[] report, int k) {
        List<String> list = Arrays.stream(report).distinct().collect(Collectors.toList());
        HashMap<String, Integer> count = new HashMap<>();
        for (String s : list) {
            String target = s.split(" ")[1];
            count.put(target, count.getOrDefault(target, 0) + 1);
        }

        return Arrays.stream(id_list).map(_user -> {
            final String user = _user;
            List<String> reportList = list.stream().filter(s -> s.startsWith(user + " ")).collect(Collectors.toList());
            return reportList.stream().filter(s -> count.getOrDefault(s.split(" ")[1], 0) >= k).count();
        }).mapToInt(Long::intValue).toArray();
    }
}

public class Q008 {
    public static void main(String[] args) {
        Solution008 solution = new Solution008();
        String[] id_list = { "muzi", "frodo", "apeach", "neo" };
        String[] report = { "muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi" };
        int k = 2;
        int[] result = solution.solution(id_list, report, k);
        for (int i : result) {
            System.out.print(i + " ");
        }
    }
}
