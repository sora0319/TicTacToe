package module;

import manager.GameUtil;

import java.util.*;

public class NumberBaseball implements Playable {
    private static final int NUMBER_LENGTH = 3;
    private List<Integer> computerNumbers;

    @Override
    public void init() {
        computerNumbers = generateRandomNumbers();
        System.out.println("숫자가 설정되었습니다. 게임을 시작하세요!");
        playGame();
    }

    private List<Integer> generateRandomNumbers() {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        return numbers.subList(0, NUMBER_LENGTH);
    }

    @Override
    public void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameFinished = false;
        int attempts = 0;

        while (!gameFinished) {
            System.out.print("숫자 세 개를 입력하세요 (예: 123): ");
            String input = scanner.nextLine();
            List<Integer> playerGuess = parseInput(input);

            if (playerGuess.isEmpty()) {
                continue;
            }

            attempts++;
            gameFinished = evaluateGuess(playerGuess);
            if (gameFinished) {
                System.out.println("정답입니다! " + attempts + "번의 시도 만에 맞췄습니다!");
            }
        }

    }

    private List<Integer> parseInput(String input) {
        if (input.length() != NUMBER_LENGTH) {
            showError("잘못된 입력입니다. 정확히 세 개의 숫자를 입력하세요.");
            return Collections.emptyList();
        }

        List<Integer> guess = new ArrayList<>();
        for (char c : input.toCharArray()) {
            int digit = c - '0';
            if (digit == 0 || !Character.isDigit(c)) {
                showError("숫자는 1부터 9까지이며, 0을 포함할 수 없습니다.");
                return Collections.emptyList();
            }
            guess.add(digit);
        }

        if (new HashSet<>(guess).size() != NUMBER_LENGTH) {
            showError("중복되는 숫자가 있습니다.");
            return Collections.emptyList();
        }

        return guess;
    }

    private void showError(String message) {
        System.out.println(message);
    }

    private boolean evaluateGuess(List<Integer> guess) {
        int strikes = 0;
        int balls = 0;
        for (int i = 0; i < NUMBER_LENGTH; i++) {
            int guessedNumber = guess.get(i);
            if (guessedNumber == computerNumbers.get(i)) {
                strikes++;
            } else if (computerNumbers.contains(guessedNumber)) {
                balls++;
            }
        }
        System.out.println(GameUtil.fontColor("red", strikes + " 스트라이크") + ", " + GameUtil.fontColor("green", balls + " 볼"));
        return strikes == NUMBER_LENGTH;
    }
}
