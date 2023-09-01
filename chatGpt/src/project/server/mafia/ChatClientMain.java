package project.server.mafia;

import project.player.PlayerDao;
import project.player.PlayerService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

class WriteTh extends Thread {
	private PrintWriter out;// 소켓에 출력 스트림
	private Scanner sc; // 키보드 입력

	private boolean flag;

	private PlayerDao playerDao;

	public void setFlag(final boolean flag) {
		this.flag = flag;
	}

	public WriteTh(PrintWriter out, Scanner sc) {
		this.out = out;
		this.sc = sc;

		this.playerDao = new PlayerDao();

		flag = true;
	}

	@Override
	public void run() {
		// TODO 내 닉네임 색 바꾸기
		String name = playerDao.findByLoginId(PlayerService.getLoginId()).getNickname();

		System.out.println("마피아 게임에 오신 걸 환영합니다 " + name + "님");
		// 닉네임 서버 전송
		out.println(name);
		out.flush();

		while (flag) {
			// 키보드 입력
			String str = sc.nextLine();
			// 소켓에 출력 => 상대방에게 전송
			
			// TODO 입력 글자 색 바꾸기
			out.println(str);
			out.flush();// 버퍼 강제 출력
		}
	}

}

//상대방 메시지 읽는 쓰레드
class ReadTh extends Thread {
	private BufferedReader in;// 소켓 읽기 스트림

	private boolean flag;

	private WriteTh writeTh;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(final boolean flag) {
		this.flag = flag;
	}

	public ReadTh(BufferedReader in, WriteTh writeTh) {
		this.in = in;

		this.writeTh = writeTh;

		flag = true;
	}

	@Override
	public void run() {
		while (flag) {
			try {
				// 소켓에서 한줄 읽음
				String str = in.readLine();
				if (str.startsWith("/stop")) {// 메시지 내용이 /stop이면
					System.out.println("채팅을 종료합니다");
					flag = false;
					writeTh.setFlag(false);
					break;// 쓰레드 종료
				}
				System.out.println(str);
			} catch (IOException e) {
				System.out.println("채팅을 종료합니다.");
				flag = false;
				writeTh.setFlag(false);
				break;
			}
		}
		System.out.println("종료하려면 엔터키를 누르세요.");
	}

}

public class ChatClientMain extends Thread {

	private String host;
	private int port;

	public ChatClientMain(final String host, final int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		try {
			// 서버연결
			Socket socket = new Socket(host, port);

			// 소켓 입출력 스트림 생성
			BufferedReader in = 
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(socket.getOutputStream());

			// 키보드 입력받아서 소켓에 출력하는 쓰레드 생성
			WriteTh th2 = new WriteTh(out, new Scanner(System.in));

			// 상대방 메시지 읽는 쓰레드 생성
			ReadTh th1 = new ReadTh(in, th2);

			// 쓰레드 실행
			th1.start();
			th2.start();

			try {
				th2.join();
				th1.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
				th2.setFlag(false);
			} finally {
				in.close();
				out.close();
				socket.close();
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
