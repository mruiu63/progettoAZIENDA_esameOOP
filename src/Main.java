import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConsoleOutputManager out=new ConsoleOutputManager();
        ConsoleInputManager in=new ConsoleInputManager();
        int s;
        do {
            out.println("1)Apri nuova sede");
            out.println("2)Inserisci nuovi impiegati");
            out.println("3)Visualizza impiegati");
            out.println("4)Visualizza sedi");
            out.println("5)Visualizza tutti i dati dell'azienda");
            out.println("0)Ferma esecuzione");
            s = in.readInt();
            switch (s) {
                case 1:
                {

                }break;
                case 2:
                {

                }break;
                case 3:
                {

                }break;
                case 4:
                {

                }break;
                case 5:
                {

                }break;
            }
        }while(s!=0);
    }
}