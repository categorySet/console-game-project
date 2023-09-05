package config;

import player.Player;

import java.util.Scanner;

public class UIController {
    private final int WIDTH = 60;

    public int getWIDTH() {
        return WIDTH;
    }

    public void printTitle(String title){
        String line = "=".repeat(WIDTH);
        System.out.printf(line +"%n");
        System.out.printf("%" + (line.length() - title.length()) / 2 + "s%s%" + (line.length() - title.length()) / 2 + "s%n", "", title, "");
    }

    public void printSubTitle(String subTitle) {
        printTitle(subTitle);
        System.out.println("-".repeat(WIDTH));
    }

    public void printMenu(String menu) {
        String line = "=".repeat(WIDTH);
        System.out.printf(line +"%n");
        System.out.printf("%s%n", menu);
    }

    public int printInput(Scanner sc) {
        System.out.print("메뉴 입력 >> ");
        int menu = sc.nextInt();
        return menu;
    }

    public void printMyInfo(Player findPlayer) {
        System.out.println("==== 내 정보 ====");
        System.out.println("아이디 : " + findPlayer.getLoginId());
        System.out.println("닉네임 : " + findPlayer.getNickname());
        System.out.println("보유 크레딧 : " + findPlayer.getCredit());
        System.out.println("가입일 : " + findPlayer.getCreateDate());
    }

    public void printSubLine() {
        System.out.println("-".repeat(WIDTH));
    }

}
