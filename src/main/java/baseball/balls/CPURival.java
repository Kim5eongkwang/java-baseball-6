package baseball.balls;


import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.List;

public class CPURival implements Balls {
    private List<Integer> computer = new ArrayList<>();


    @Override
    public void init() {
        computer.clear();
        while (computer.size() < 3) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!computer.contains(randomNumber)) {
                computer.add(randomNumber);
            }
        }
    }

    @Override
    public Integer getFirstBall() {
        return computer.get(0);
    }

    @Override
    public Integer getSecondBall() {
        return computer.get(1);
    }

    @Override
    public Integer getThirdBall() {
        return computer.get(2);
    }
}
