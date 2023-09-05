import config.db.InsertData;
import config.db.Sequence;
import config.db.Table;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Menu menu = new Menu();

        Table table = new Table();
        Sequence sequence = new Sequence();
        InsertData insertData = new InsertData();

//        table.dropAll();
//        table.createAll();
//        sequence.dropAll();
//        sequence.createAll();
//        insertData.player();
//        insertData.blacklist();
//        insertData.game();
//        insertData.item();


        menu.run(sc);

        sc.close();
    }
}