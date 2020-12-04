package util;
import store.Store;
import view.main.MainWindow;

import java.util.Scanner;

public class Entry {
    public static void main(String argv[]) {

        Store.mainWindow = new MainWindow("MainWindow", 1920, 1080);
//        while (true) {
//            Scanner scan = new Scanner(System.in);
//            if(scan == null) continue;
//            if (scan.nextLine() == "exit"){
//                return;
//            }
//        }
    }
}
