package baseball;

import static camp.nextstep.edu.missionutils.Console.readLine;

import baseball.balls.CPURival;
import baseball.balls.Balls;
import baseball.balls.MyBalls;

public class GameManager {

    Balls rivalBalls = new CPURival();
    Balls myBalls = new MyBalls();

    public void startGame() {
        printStartMsg();
        rivalBalls.init();

        do {
            inputSequence();
            showScore();
        } while (marking() != Score.ThreeStrike);
        gameEnd();
    }

    private void showScore() {
        Score myScore = marking();
        if (myScore == Score.ThreeStrike) {
            System.out.println("3스트라이크");
        }
        if (myScore == Score.TwoStrike) {
            System.out.println("2스트라이크");
        }
        if (myScore == Score.OneBallTwoStrike) {
            System.out.println("1볼 2스트라이크");
        }
        if (myScore == Score.TwoBallOneStrike) {
            System.out.println("2볼 1스트라이크");
        }
        if (myScore == Score.OneBallOneStrike) {
            System.out.println("1볼 1스트라이크");
        }
        if (myScore == Score.OneStrike) {
            System.out.println("1스트라이크");
        }
        if (myScore == Score.OneBall) {
            System.out.println("1볼");
        }
        if (myScore == Score.TwoBall) {
            System.out.println("2볼");
        }
        if (myScore == Score.ThreeBall) {
            System.out.println("3볼");
        }
        if (myScore == Score.Nothing) {
            System.out.println("낫싱");
        }
    }

    private void gameEnd() {
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
            if (!(inputInt == 1 || inputInt == 2)) {
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
        myBalls.init();
    }

    private Score marking() {
        int strikeCnt = getStrikeCnt();
        int ballCnt = getBallCnt();

        if (strikeCnt == 3 && ballCnt == 0) {
            return Score.ThreeStrike;
        }
        if (strikeCnt == 2 && ballCnt == 0) {
            return Score.TwoStrike;
        }
        if (strikeCnt == 2 && ballCnt == 1) {
            return Score.OneBallTwoStrike;
        }
        if (strikeCnt == 1 && ballCnt == 2) {
            return Score.TwoBallOneStrike;
        }
        if (strikeCnt == 1 && ballCnt == 1) {
            return Score.OneBallOneStrike;
        }
        if (strikeCnt == 1 && ballCnt == 0) {
            return Score.OneStrike;
        }
        if (strikeCnt == 0 && ballCnt == 1) {
            return Score.OneBall;
        }
        if (strikeCnt == 0 && ballCnt == 2) {
            return Score.TwoBall;
        }
        if (strikeCnt == 0 && ballCnt == 3) {
            return Score.ThreeBall;
        }
        return Score.Nothing;
    }

    private int getStrikeCnt() {
        int strikeCnt = 0;

        if (myBalls.getFirstBall() == rivalBalls.getFirstBall()) {
            strikeCnt++;
        }
        if (myBalls.getSecondBall() == rivalBalls.getSecondBall()) {
            strikeCnt++;
        }
        if (myBalls.getThirdBall() == rivalBalls.getThirdBall()) {
            strikeCnt++;
        }

        return strikeCnt;
    }

    private int getBallCnt() {
        int strikeCnt = getStrikeCnt();
        int my = 0, rival = 0;

        my += (1 << myBalls.getFirstBall()) + (1 << myBalls.getSecondBall()) + (1 << myBalls.getThirdBall());
        rival += (1 << rivalBalls.getFirstBall()) + (1 << rivalBalls.getSecondBall()) + (1 << rivalBalls.getThirdBall());

        my = my & rival;

        return Integer.bitCount(my) - strikeCnt;
    }

    private void printStartMsg() {
        System.out.println("숫자 야구 게임을 시작합니다.");
    }

    private void printInputMsg() {
        System.out.println("숫자를 입력해주세요 : ");
    }


}
