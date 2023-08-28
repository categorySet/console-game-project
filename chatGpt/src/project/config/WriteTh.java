package project.config;

import java.io.PrintWriter;
import java.util.Scanner;

public class WriteTh extends Thread{
    private PrintWriter out;
    private Scanner sc;

    public WriteTh(PrintWriter out, Scanner sc) {
        this.out = out;
        this.sc = sc;
    }

    @Override
    public void run() {
        System.out.print("사용 할 닉네임 입력 : ");
        String nickname = new Scanner(System.in).next();
        //닉네임 서버 전송
        out.println(nickname);
        out.flush();

        while (true) {
            System.out.println("내용:");
            String str = sc.nextLine();
            out.println(nickname + " : " + str);    //소켓에 출력 => 상대방에게 전송
            out.flush();
            if (str.startsWith("/stop")) {
                break;
            }
        }
    }


}
