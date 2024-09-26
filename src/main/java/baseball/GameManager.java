package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;

import baseball.balls.CPURival;
import baseball.balls.Balls;
import baseball.balls.MyBalls;
import java.util.Objects;

public class GameManager {

    Balls rivalBalls = new CPURival();
    Balls myBalls = new MyBalls();

    public void startGame() {
        printStartMsg();
        inputSequence();

        while (marking() != Score.ThreeStrike) {
            inputSequence();
        }
        gameEnd();
    }

    private void gameEnd() {
        System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
        System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
        if (isRetry()) {
            startGame();
        }
    }

    private boolean isRetry() {
        String inputStr = readLine();

        try {
            int inputInt = Integer.parseInt(inputStr); // 문자열을 정수로 변환

            // 범위 확인
            if (inputInt == 1 || inputInt == 2) {
                throw new IllegalArgumentException("Input must be between 1 and 2.");
            }

        } catch (NumberFormatException e) {
            // 숫자로 변환 불가능한 경우 예외 처리
            throw new IllegalArgumentException("Invalid input. Please enter a number.");
        }

        int inputInt = Integer.parseInt(inputStr);

        return inputInt == 1;
    }

    private void inputSequence() {
        printInputMsg();
        rivalBalls.init();
        myBalls.init();
    }

    private Score marking() {
        int strikeCnt = getStrikeCnt();
        int ballCnt = getBallCnt();

        if (strikeCnt == 3) {
            return Score.ThreeStrike;
        }

        if (strikeCnt == 2 && ballCnt == 1) {
            return Score.oneBallTwoStrike;
        }

        if (strikeCnt == 1 && ballCnt == 2) {
            return Score.TwoBallOneStrike;
        }

        return Score.Nothing;
    }

    private int getStrikeCnt() {
        int strikeCnt = 0;

        if (Objects.equals(myBalls.getFirstBall(), rivalBalls.getFirstBall())) {
            strikeCnt++;
        }
        if (Objects.equals(myBalls.getSecondBall(), rivalBalls.getSecondBall())) {
            strikeCnt++;
        }
        if (Objects.equals(myBalls.getThirdBall(), rivalBalls.getThirdBall())) {
            strikeCnt++;
        }

        return strikeCnt;
    }

    private int getBallCnt() {
        int strikeCnt = getStrikeCnt();
        int ballCnt;
        int compareBall = myBalls.getFirstBall() & rivalBalls.getFirstBall()
                + myBalls.getSecondBall() & rivalBalls.getSecondBall()
                + myBalls.getThirdBall() & rivalBalls.getThirdBall();

        ballCnt = Integer.bitCount(compareBall) - strikeCnt;

        return ballCnt;
    }

    private void printStartMsg() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    private void printInputMsg() {
        System.out.println("숫자를 입력해주세요 : ");
    }


}
