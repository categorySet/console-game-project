package project.config;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadTh extends Thread{
    private BufferedReader in;// 소켓 읽기 스트림
    private ChatUI ui;

    public ReadTh(BufferedReader in, ChatUI ui) {
        this.in = in;
        this.ui = ui;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        while (true) {
            try {
                // 소켓에서 한줄 읽음
                String readLine = in.readLine();
                if (readLine.startsWith("/stop")) {// 메시지 내용이 /stop이면
                    break;// 쓰레드 종료
                }
                ui.printUI(readLine);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }



}
