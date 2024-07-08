package module.snakegame;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGameProcess {
    private String direction = "RIGHT";
    private int tableX = 25;
    private int tableY = 50;

    private char[][] tableData = new char[tableX][tableY];
    private int[] fruit = new int[2]; // 과일의 x,y축 위치
    private List<int[]> snakeData = new ArrayList<>(); // 뱀 위치 저장소

    private long tableUpdateTime = (long)1000;
    private int gameSpeed = 1;

    private boolean isUpdateGui = true;
    private boolean isSpeedGame = true;





    public void start() {
        resetGame();
        printTable();

        Thread updateGui = new Thread(new Runnable() {
            @Override
            public void run() {
                //System.out.println("thread gui start");
                try {

                    while (isUpdateGui){
                        // console clear
                        new ProcessBuilder("cmd", "/c", "cls" ).inheritIO().start().waitFor();

                        printTable();
                        Thread.sleep(tableUpdateTime);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    return;
                }
            }
        });

        Thread speedGame = new Thread(new Runnable() {
            @Override
            public void run() {
                long speed = (1000 - (gameSpeed * 100L));

                try{
                    while (true){
                        for (int [] body : snakeData) {
                            tableData[body[0]][body[1]] = ' ';
                        }


                        // update snake, fruit posiiton
                        insertSnakeInTable();
                        insertFruitInTable();

                        //Thread.sleep(speed > 50 ? speed : 50);
                    }
                }catch(Exception e){
                    e.printStackTrace();
                    return;
                }
            }

        });


        KeyListener keyListener = new KeyListener();
        updateGui.start();

    }

    private void printTable() {
        for(int i = 0; i < tableX; i++){
            for(int j = 0; j < tableY; j++){
                System.out.print(tableData[i][j]);
            }
            System.out.println();
        }
    }

    private void resetGame() {
        direction = "RIGHT";
        snakeData = new ArrayList<>();

        // 뱀 위치 (화면의 정 가운데)
        int middleX = tableX/2;
        int middleY = tableY/2;

        // 뱀 머리, 몸통, 꼬리 위치 저장
        snakeData.add(new int[]{middleX, middleY});
        snakeData.add(new int[]{middleX, middleY-1});
        snakeData.add(new int[]{middleX, middleY-2});
        snakeData.add(new int[]{middleX, middleY-3});

        createRandomFruit();

        // 테이블 값 빈칸 채우기
        for(int i = 1; i < tableX-1; i++){
            for(int j = 1; j < tableY-1; j++){
                tableData[i][j] = ' ';
            }
        }

        // 테이블 좌,우 벽 채우기
        for(int i = 1; i < tableX-1; i++){
            tableData[i][0] = '┃';
            tableData[i][tableY-1] = '┃';
        }

        // 테이블 상 하 채우기
        for(int i = 1; i < tableY-1; i++){
            tableData[0][i] = '━';
            tableData[tableX-1][i] = '━';
        }

        // 테이블 각 모서리 채우기
        tableData[0][0] = '┏';
        tableData[0][tableY-1] = '┓';
        tableData[tableX-1][0] = '┗';
        tableData[tableX-1][tableY-1] = '┛';

        insertSnakeInTable();
        insertFruitInTable();

    }

    private void insertFruitInTable() {
        tableData[fruit[0]][fruit[1]] = '@';
    }

    private void insertSnakeInTable() {
        int[] body;
        try {
            for(int[] snake : snakeData){
                tableData[snake[0]][snake[1]] = '■';
            }

            // head
            body = snakeData.get(0);
            tableData[body[0]][body[1]] = '▷';

            //tail 1
            body = snakeData.get(snakeData.size()-2);
            tableData[body[0]][body[1]] = '◀';

            //tail2
            body = snakeData.get(snakeData.size()-1);
            tableData[body[0]][body[1]] = '←';

        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            resetGame();
        }
    }



    private void createRandomFruit() {
        Random random = new Random();
        int[] snakeSpace = new int[snakeData.size()];

        for(int i = 0; i < snakeSpace.length; i++){
            int[] temp = snakeData.get(i);
            snakeSpace[i] = temp[0] * tableY + temp[1];
        }

        // table 각 가장자리 빼고 랜덤 수 생성
        // ((25-2)*100)+100
        int fruitX = 0;
        int fruitY = 0;
        int fruitSpace = 0;
        boolean spaceDuplicated = false;

        while(fruitX == 0 && fruitY == 0){
            fruitSpace = random.nextInt((tableX-2)*tableY)+tableY;
            for(int i : snakeSpace){
                if(fruitSpace == i || fruitSpace % tableY == 0){
                    spaceDuplicated = true;
                    break;
                }
            }
            if(!spaceDuplicated){
                fruitX = fruitSpace / tableY;
                fruitY = fruitSpace % tableY;
            }
            spaceDuplicated = false;
        }

        fruit[0] = fruitX;
        fruit[1] = fruitY;
    }
}
