package baseball.balls;

import static camp.nextstep.edu.missionutils.Console.readLine;

import java.util.ArrayList;
import java.util.List;

public class MyBalls implements Balls {
    private List<Integer> myBalls = new ArrayList<>();

    @Override
    public void init() {
        inputMyBall();
    }

    private void inputMyBall() {
        String inputStr = readLine();
        try {
            int inputInt = Integer.parseInt(inputStr); // 문자열을 정수로 변환

            // 범위 확인
            if (inputInt <= 0 || inputInt > 999) {
                throw new IllegalArgumentException("Input must be between 1 and 999.");
            }

        } catch (NumberFormatException e) {
            // 숫자로 변환 불가능한 경우 예외 처리
            throw new IllegalArgumentException("Invalid input. Please enter a number.");
        }

        copyInput(inputStr);

    }

    private void copyInput(String input) {
        for (int i = 0; i < 3; i++) {
            char tmp = input.charAt(i);
            myBalls.add(tmp - '0');
        }
    }

    @Override
    public Integer getFirstBall() {
        return myBalls.get(0);
    }

    @Override
    public Integer getSecondBall() {
        return myBalls.get(1);
    }

    @Override
    public Integer getThirdBall() {
        return myBalls.get(2);
    }
}
