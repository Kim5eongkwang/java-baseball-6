package baseball;

import baseball.ball.CPURival;
import baseball.ball.Balls;
import baseball.ball.MyBalls;
import java.util.Objects;

public class GameManager {

    Balls rivalBalls = new CPURival();
    Balls myBalls = new MyBalls();

    public void startGame() {
        printStartMsg();
        inputSequence();

        while (marking() == Score.ThreeStrike) {
            inputSequence();
        }
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
