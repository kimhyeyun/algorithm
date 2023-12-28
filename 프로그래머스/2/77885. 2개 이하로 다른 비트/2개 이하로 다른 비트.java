class Solution {
    public static long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for(int i = 0; i < numbers.length; i++){
            if(numbers[i] % 2 == 0){
                answer[i] = numbers[i] + 1;
            }
            else{
                StringBuilder sb = new StringBuilder();
                String binary = Long.toBinaryString(numbers[i]);
                if(!binary.contains("0")){
                    // 1로만 이루어진 경우 
                    // 11 -> 101, 111 -> 1011
                    sb.append("10");
                    for(int j = 0; j < binary.length()-1; j++)
                        sb.append("1");
                }

                else{
                    // 0이 존재하는 경우
                    // 101 -> 110, 1101 -> 1110, 11001 -> 11010
                    int lastZero = binary.length()-1;
                    for(int j = binary.length()-1; j >= 0; j--){
                        if(binary.charAt(j) == '0'){
                            lastZero = j;
                            break;
                        }
                    }

                    for(int j = 0; j < lastZero;j++){
                        sb.append(binary.charAt(j));
                    }

                    sb.append("10");

                    for(int j = lastZero+2;j < binary.length(); j++){
                        sb.append(binary.charAt(j));
                    }
                }

                answer[i] = Long.parseLong(sb.toString(), 2);
            }
        }

        return answer;
    }
}