import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ConsoleOutputManager out=new ConsoleOutputManager();
        ConsoleInputManager in=new ConsoleInputManager();
        GestioneAzienda azienda= new GestioneAzienda();
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
                    String com=in.readLine("In quale comune vuoi aprire la sede?");//inserimento comune dove aprire la sede
                    String cod=in.readLine("Assegna un codice identificativo alla sede");//inserimento codice della sede
                    azienda.add_sede(cod,com);//richiamo metodo per aggiungere la sede
                }break;
                case 2:
                {
                    //assunzione impiegati
                    out.println("Quanti impiegati vuoi assumere?");
                    int nI=in.readInt();
                    for(int i=0; i<nI; i++)
                    {
                        Impiegato imp= azienda.assumiImp();
                        azienda.listaimpiegati.add(imp);
                    }
                }break;
                case 3:
                {
                    for(Impiegato imp: azienda.listaimpiegati)
                    {
                        out.println(imp.toString());
                    }
                }break;
                case 4:
                {
                    for(Sede sede: azienda.listasedi)
                    {
                        out.println(sede.getCodice());
                    }
                }break;
                case 5:
                {

                }break;
            }
        }while(s!=0);
    }
}