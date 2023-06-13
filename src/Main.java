import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class Main {
    static void scriviAziendaSuFile(GestioneAzienda azienda, String nomefile)
    {
        try(FileWriter writer=new FileWriter(nomefile))
        {
            for(Sede sede: azienda.listasedi)
            {
                writer.write("SEDE\n");
                writer.write("Codice:"+sede.getCodice()+"\\"+
                        "Comune:"+sede.getComune()+"\\"+
                        "CFdirettore:"+sede.getCFdirettore()+"\n");
                for(Impiegato imp:azienda.listaimpiegati)
                {
                        if(imp.getCF().equals(sede.getCFdirettore()) && imp.getRuolo().equals("DIR"))
                        {
                            writer.write("CONTRATTI Direttore\n");
                            for(Contratto c: imp.listacontratti)
                            {
                                writer.write(c.toString1());
                            }
                            break;
                        }
                }
                for(Reparto reparto: sede.listareparti)
                {
                    writer.write("REPARTO\n");
                    writer.write("Nome:"+reparto.getNome()+
                            "\\CF capo:"+reparto.getCFcapo()+"\n");
                    for(Impiegato imp:azienda.listaimpiegati)
                    {
                        if(imp.getCF().equals(reparto.getCFcapo()) && imp.getRuolo().equals("CRP"))
                        {
                            writer.write("CONTRATTI Capo reparto\n");
                            for(Contratto c: imp.listacontratti)
                            {
                                writer.write(c.toString1());
                            }
                            break;
                        }
                    }
                    writer.write("CF impiegati reparto:\n");
                    for(String CF: reparto.listaCFimpiegati)
                    {
                        for(Impiegato imp:azienda.listaimpiegati)
                        {
                            if(imp.getCF().equals(CF)) {
                                for(Contratto c: imp.listacontratti) {
                                    writer.write(CF + "->Contratto:"+c.toString1());
                                }
                            }
                        }
                    }
                }
            }
            if(!azienda.listaCFimpFree.isEmpty())
            {
                writer.write("IMPIEGATI vecchi o senza assegnazione di sede/reparto\n");
                for(Impiegato imp: azienda.listaimpiegati)
                {
                    for(String CF: azienda.listaCFimpFree) {
                        if (imp.getCF().equals(CF)) {
                            writer.write("Nome:" + imp.getNome() + "\\Cognome:" + imp.getCognome() + "\\CF:" + imp.getCF() + "\\Ruolo:" + imp.getRuolo() + "\n");
                            writer.write("CONTRATTI\n");
                            for (Contratto c : imp.listacontratti) {
                                writer.write(c.toString1());
                            }
                        }
                    }
                }
            }
            writer.write("\n");
            writer.flush();
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        ConsoleOutputManager out=new ConsoleOutputManager();
        ConsoleInputManager in=new ConsoleInputManager();
        GestioneAzienda azienda= new GestioneAzienda();
        String filename="C:\\Users\\robin\\IdeaProjects\\progettoAZIENDA_esameOOP\\src\\DatiAzienda.txt";
        int s;
        do {
            out.println("1)Apri nuova sede");
            out.println("2)Assumi nuovi impiegati");
            out.println("3)Visualizza impiegati");
            out.println("4)Visualizza sedi");
            out.println("5)Visualizza tutti i dati dell'azienda");
            out.println("6)Assegna impiegato ad un altro reparto");
            out.println("7)Apri nuovo reparto");
            out.println("8)Chiudi un reparto");
            out.println("9)Chiudi una sede");
            out.println("10)Revisione contratti");
            out.println("11)Esporta dati su file.txt");
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
                    if(azienda.listasedi.isEmpty())
                    {
                        out.println("L'azienda non ha sedi aperte, prima di assumere impiegati devi aprire una sede");
                        String com=in.readLine("In quale comune vuoi aprire la sede?");//inserimento comune dove aprire la sede
                        String cod=in.readLine("Assegna un codice identificativo alla sede");//inserimento codice della sede
                        azienda.add_sede(cod,com);//richiamo metodo per aggiungere la sede
                    }
                    else {
                        Reparto r;
                        //assunzione impiegati
                        String nomerep=in.readLine("Inserisci nome reparto in cui vuoi assumere dipendenti");
                        r=azienda.RicercaRep(nomerep);
                        out.println("Quanti impiegati vuoi assumere?");
                        int nI = in.readInt();
                        for (int i = 0; i < nI; i++) {
                            Impiegato imp = azienda.assumiImp();
                            r.listaCFimpiegati.add(imp.getCF());
                            azienda.listaimpiegati.add(imp);
                        }
                    }
                }break;
                case 3:
                {
                    if(azienda.listaimpiegati.isEmpty())
                    {
                        out.println("L'azienda non ha dipendenti");
                    }
                    else {
                        for (Impiegato imp : azienda.listaimpiegati) {
                            out.println(imp.toString());
                            for(Contratto c: imp.listacontratti)
                            {
                                out.println(c.toString());
                            }
                        }
                    }
                }break;
                case 4:
                {
                    if(azienda.listasedi.isEmpty())
                    {
                        out.println("L'azienda non ha sedi aperte");
                    }
                    else {
                        for (Sede sede : azienda.listasedi) {
                            out.println(sede.getCodice());
                        }
                    }
                }break;
                case 5:
                {
                    azienda.VisualizzaDati();
                }break;
                case 6:
                {
                    String CFimpiegato=in.readLine("Inserire codice fiscale del dipendente che si vuole spostare di reparto");
                    boolean repvecchio;
                    String nomerep;
                    do {
                        repvecchio=false;
                        nomerep=in.readLine("Inserire nome del nuovo reparto");
                        Reparto r=azienda.RicercaRep(nomerep);
                        for(String CF: r.listaCFimpiegati)
                        {
                            if(CF.equals(CFimpiegato));
                            {
                                repvecchio=true;
                            }
                        }
                    }while(repvecchio);
                    azienda.spostamentoRepImp(CFimpiegato,nomerep);
                }break;
                case 7:
                {
                    boolean sedeesistente=false;
                    Sede sede=null;
                    String codsede;
                    do {
                        codsede = in.readLine("Inserisci codice sede");
                        for(Sede sed:azienda.listasedi)
                        {
                            if(codsede.equals(sed.getCodice()))
                            {
                                sedeesistente=true;
                                sede=sed;
                            }
                        }
                    }while(!sedeesistente);
                    azienda.ApriNuoviRep(sede);
                }break;
                case 8:
                {
                    boolean continua=false;
                    do {
                        String repToCanc = in.readLine("Inserisci nome del reparto che vuoi chiudere");
                        if (azienda.chiudiReparto(repToCanc)) {
                            out.println("Reparto chiuso");
                            continua=true;
                        }
                        else
                        {
                            continua=in.readSiNo("Vuoi riprovare?");
                        }
                    }while(!continua);
                }break;
                case 9:
                {
                    if(azienda.listasedi.isEmpty())
                    {
                        out.println("Non puoi chiudere una sede senza mai averne aperto una");
                    }
                    else
                    {
                        boolean sedeesistente=false;
                        String codsede;
                        do {
                            codsede = in.readLine("Inserisci codice sede");
                            for(Sede sed:azienda.listasedi)
                            {
                                if(codsede.equals(sed.getCodice()))
                                {
                                    sedeesistente=true;
                                }
                            }
                        }while(!sedeesistente);
                        azienda.chiudiSede(codsede);
                    }
                }break;
                case 10:
                {
                    for(Impiegato imp: azienda.listaimpiegati)
                    {
                        imp.ControlloContratti();
                    }
                }break;
                case 11:
                {
                    scriviAziendaSuFile(azienda,filename);
                }break;
            }
        }while(s!=0);
    }
}