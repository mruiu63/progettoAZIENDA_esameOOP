import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConsoleOutputManager out=new ConsoleOutputManager();
        ConsoleInputManager in=new ConsoleInputManager();
        Random random= new Random();
        GestioneAzienda ga= new GestioneAzienda();
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
                    out.println("APERTURA NUOVA SEDE");
                    String com=in.readLine("In quale comune vuoi aprire la sede?");
                    String cod=in.readLine("Assegna un codice identificativo alla sede");
                    ga.add_sede(cod,com);
                }break;
                case 2:
                {
                    out.println("Quanti impiegati vuoi assumere?");
                    int nI=in.readInt();
                    for(int i=0; i<nI; i++)
                    {
                        Impiegato imp= ga.assumiImp();
                        ga.listaimpiegati.add(imp);
                    }
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