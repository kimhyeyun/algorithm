import java.util.*;
class Solution {
       public static int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int curWeight = 0;
        Queue<Integer> truckOnBridge = new LinkedList<>();
        Queue<Integer> distTruck = new LinkedList<>();
        Queue<Integer> truckWeight = new LinkedList<>();

        for(int t : truck_weights){
            truckWeight.add(t);
        }

        while(true){
            int size = distTruck.size();

            for(int i = 0; i < size; i++){
                int dist = distTruck.poll();

                if(dist <= 1){
                    curWeight -= truckOnBridge.poll();
                    continue;
                }
                
                else{
                    distTruck.add(dist - 1);
                }
            }

            if(truckWeight.size() > 0 && curWeight + truckWeight.peek() <= weight){
                truckOnBridge.add(truckWeight.peek());
                curWeight += truckWeight.peek();
                distTruck.add(bridge_length);
                truckWeight.poll();
            }

            answer++;

            if(truckWeight.size() == 0 && truckOnBridge.size() == 0){
                break;
            }
        }

        return answer;
    }
}