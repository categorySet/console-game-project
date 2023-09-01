import project.Menu;
import project.config.db.Sequence;
import project.config.db.Table;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        Table table = new Table();
        Sequence sequence = new Sequence();

//        table.dropAll();
//        table.createAll();
//
//        sequence.dropAll();
//        sequence.createAll();

        menu.run(sc);

        sc.close();
    }
}