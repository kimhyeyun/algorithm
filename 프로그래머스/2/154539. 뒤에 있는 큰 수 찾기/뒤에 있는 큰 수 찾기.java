import java.util.*;
class Solution {

 public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];

        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (int i = 0; i < numbers.length; i++) {
            int n = numbers[i];

            while (!q.isEmpty() && q.peek()[1] < n){
                answer[q.poll()[0]] = n;
            }

            q.add(new int[]{i, n});
        }

        while (!q.isEmpty()) {
            answer[q.poll()[0]] = -1;
        }
        
        return answer;
    }
}