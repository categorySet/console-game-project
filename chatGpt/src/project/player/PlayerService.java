package project.player;

import java.util.Scanner;

public class PlayerService {

    private static String loginId = null;


    public boolean isLogin() {
        return (this.loginId != null) ? true : false;
    }

    public void testLogin() {
        this.loginId = "testPlayer";
    }


    public String checkLogin() {
        return this.loginId;
    }

    public void signup(Scanner sc) {

    }
}
