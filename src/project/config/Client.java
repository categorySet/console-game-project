package project.config;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {


    public static void main(String[] args) {
        try {
            //서버 연결
            Socket socket = new Socket("localhost", 1234);

            //Socket 입출력 스트림 생성
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            //ui 객체 생성
            ChatUI chatUI = new ChatUI();
            chatUI.init(out);   //ui 초기화

            //상대방 메세지 읽는 쓰레드 생성
            ReadTh readTh = new ReadTh(in, chatUI);

            //쓰레드 실행
            readTh.start();

//            socket.close();           //여기서 소켓을 닫아서 에러

        } catch(IOException e) {
            e.printStackTrace();
        }

    }






}
