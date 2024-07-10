package module.snakegame;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;

import java.util.*;

public class SnakeGameProcess implements Observer {
    KeyListener keyListener;
    ConsoleMode consoleMode = new ConsoleMode();

    public SnakeGameProcess(KeyListener Listener){
        keyListener = Listener;
        keyListener.addObserver(this);
        keyListener.registerHook();
    }

    private String direction = "RIGHT";
    private int tableX = 25;
    private int tableY = 50;

    private int score = 0;

    private char[][] tableData = new char[tableX][tableY];
    private int[] fruit = new int[2]; // 과일의 x,y축 위치
    private List<int[]> snakeData = new ArrayList<>(); // 뱀 위치 저장소

    private long tableUpdateTime = (long)1000/10;
    private int gameSpeed = 8;

    private Thread updateGui = null;
    private Thread speedGame = null;

    private boolean isUpdateGui = true;
    private boolean isSpeedGame = true;

    @Override
    public void update(Object arg) {
        int keyCode = (int) arg;

        if(keyCode == NativeKeyEvent.VC_UP && !direction.equals("DOWN")){
            direction = "UP";
        }
        if(keyCode == NativeKeyEvent.VC_DOWN && !direction.equals("UP")){
            direction = "DOWN";
        }
        if(keyCode == NativeKeyEvent.VC_LEFT && !direction.equals("RIGHT")){
            direction = "LEFT";
        }
        if(keyCode == NativeKeyEvent.VC_RIGHT && !direction.equals("LEFT")){
            direction = "RIGHT";
        }
        if(keyCode == NativeKeyEvent.VC_ESCAPE){
            isUpdateGui = false;
            isSpeedGame = false;

            System.out.println("ESC key pressed. Exiting...");
            keyListener.removeHook();
        }
    }

    public void start() {
        resetGame();
        printTable();

        updateGui = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isUpdateGui){
                        // console clear
                        new ProcessBuilder("cmd", "/c", "cls" ).inheritIO().start().waitFor();
                        // disable console echo mode
                        consoleMode.setupWindowsConsole();

                        printTable();
                        System.out.println("Score : " + score);
                        Thread.sleep(tableUpdateTime);
                        consoleMode.restoreWindowsConsole();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    stopAllbyError();
                }
            }
        });

        speedGame = new Thread(new Runnable() {
            @Override
            public void run() {
                long speed = (1000 - (gameSpeed * 100));
                try {
                    while(isSpeedGame){
                        resetSnakeInTable();

                        updateSnakePosition();

                        insertSnakeInTable();
                        insertFruitInTable();

                        Thread.sleep(speed > 50 ? speed : 50);
                    }
                } catch (Exception e){
                    e.printStackTrace();
                    stopAllbyError();
                }
            }
        });

        updateGui.start();
        speedGame.start();


        try {
            updateGui.join();
            speedGame.join();
            keyListener.removeHook();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void updateSnakePosition() {
        int [] head = snakeData.get(0);

        // 무조건 이동시  뱀 길이 1칸씩 늘이기
        if(direction.equals("UP")){
            snakeData.add(0, new int[] {head[0] - 1, head[1]});
        }
        if(direction.equals("DOWN")){
            snakeData.add(0, new int[] {head[0]+1, head[1]});
        }
        if(direction.equals("LEFT")){
            snakeData.add(0, new int[] {head[0], head[1] - 1 });
        }
        if(direction.equals("RIGHT")){
            snakeData.add(0, new int[] {head[0], head[1] + 1});
        }

        if (head[0] == fruit[0] && head[1] == fruit[1]){
            createRandomFruit();
            score++;
        }else{
            // 과일 먹지 못하면 뱀 길이 1칸 줄이기
            snakeData.remove(snakeData.size() - 1);
        }

        int [] newHead = snakeData.get(0);

        //Head Collition with any wall
        if (newHead[0] <= 0 || newHead[0] >= tableX -1 || newHead[1] <= 0 || newHead[1] >= tableY -1){
            stopAllbyError();
        }

        //Head Collition with body 
        for (int [] body : snakeData.subList(1, snakeData.size())){
            if(body[0] == newHead[0] && body[1] == newHead[1]){
                stopAllbyError();
                break;
            }
        }
    }

    private void stopAllbyError() {
        isUpdateGui = false;
        isSpeedGame = false;
        keyListener.removeHook();
    }

    private void printTable() {
        for(int i = 0; i < tableX; i++){
            for(int j = 0; j < tableY; j++){
                System.out.print(tableData[i][j]);
            }
            System.out.println();
        }
    }

    private void resetSnakeInTable(){
        for(int[] snake : snakeData){
            tableData[snake[0]][snake[1]] = ' ';
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
        try {
            for(int[] snake : snakeData){
                tableData[snake[0]][snake[1]] = '■';
            }
            insertSnakeByDirection();
        } catch (ArrayIndexOutOfBoundsException e){
            e.printStackTrace();
            resetGame();
        }
    }

    private void insertSnakeByDirection() {
        // head
        int[] head = snakeData.get(0);
        int[] tail1 = snakeData.get(snakeData.size()-2);
        int[] tail2 = snakeData.get(snakeData.size()-1);

        if(direction.equals("UP")){
            tableData[head[0]][head[1]] = '△';
            tableData[tail1[0]][tail1[1]] = '▼';
            tableData[tail2[0]][tail2[1]] = '↓';
        }
        if(direction.equals("DOWN")){
            tableData[head[0]][head[1]] = '▽';
            tableData[tail1[0]][tail1[1]] = '▲';
            tableData[tail2[0]][tail2[1]] = '↑';
        }
        if(direction.equals("LEFT")){
            tableData[head[0]][head[1]] = '◁';
            tableData[tail1[0]][tail1[1]] = '▶';
            tableData[tail2[0]][tail2[1]] = '→';
        }
        if(direction.equals("RIGHT")){
            tableData[head[0]][head[1]] = '▷';
            tableData[tail1[0]][tail1[1]] = '◀';
            tableData[tail2[0]][tail2[1]] = '←';
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
