package module;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import manager.GameUtil;

public class NumberBaseball implements Playable {
    private static final int NUMBER_LENGTH = 3; // 정답 숫자의 길이
    private List<Integer> computerNumbers; // 컴퓨터가 생성한 숫자들

    @Override
    public void init() {
        computerNumbers = generateRandomNumbers();
        System.out.println("숫자가 설정되었습니다. 게임을 시작하세요!");
    }

    // 1부터 9까지 숫자를 섞어서 첫 3개를 선택
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
                System.out.println("잘못된 입력입니다. 숫자 세 개를 입력하세요.");
                continue;
            }

            attempts++;
            gameFinished = evaluateGuess(playerGuess);
            if (gameFinished) {
                System.out.println("축하합니다! " + attempts + "번 만에 맞췄습니다!");
            }
        }
        scanner.close();
    }

    // 사용자 입력을 숫자 리스트로 변환
    private List<Integer> parseInput(String input) {
        if (input.length() != NUMBER_LENGTH) {
            return Collections.emptyList();
        }

        List<Integer> guess = new ArrayList<>();
        for (char c : input.toCharArray()) {
            if (!Character.isDigit(c)) {
                return Collections.emptyList();
            }
            guess.add(c - '0');
        }
        return guess;
    }

    // 사용자의 추측을 평가
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
