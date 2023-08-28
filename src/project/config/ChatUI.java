package project.config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class ChatUI  extends JFrame{
    private PrintWriter out;//소켓에 출력 스트림
    private JTextArea content;//채팅 내역을 출력할 창

    public void init(PrintWriter out) {
        this.out = out;
        setSize(300, 500);// 가로, 세로 길이

        setLayout(new BorderLayout());// 레이아웃설정. 컴포넌트 배치 형태를 결정
        content = new JTextArea();
        content.setSize(300, 400);
        content.setText("채팅내용. 첫 입력은 닉네임.\n");
        //ui 컴포넌트를 윈도우에 붙임. CENTER:위치 가운데
        add(content, BorderLayout.CENTER);

        //1줄 입력 박스
        JTextField input = new JTextField();
        input.setSize(300, 80);

        ////1줄 입력 박스 엔터 이벤트 처리
        input.addActionListener(new ActionListener() {// 익명 클래스

            // 핸들러. 이벤트가 발생할 때마다 자동 호출되는 메서드
            @Override
            public void actionPerformed(ActionEvent e) {
                String str = input.getText();//입력박스에 입력된 텍스트 반환
                out.println(str);//입력 내용을 서버로 전송
                out.flush();// 버퍼 강제 출력
                input.setText("");//입력내용지움
                if (str.startsWith("/stop")) {// 종료 메시지
                    System.exit(0);// 프로그램 종료
                }
            }
        });

        //ui 컴포넌트를 jframe에 붙임. 남쪽에 배치
        add(input, BorderLayout.SOUTH);
        setVisible(true);// 보이게 설정
    }

    //서버가 보낸 메시지를 파람으로 받아서 textarea에 출력
    public void printUI(String msg) {
        content.setText(content.getText() + msg + "\n");
    }

}
