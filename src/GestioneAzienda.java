import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;

import java.util.*;

public class GestioneAzienda {
    ArrayList<Impiegato> listaimpiegati=new ArrayList<>();
    ArrayList<Sede> listasedi=new ArrayList<>();
    ArrayList<Contratto> listacontratti=new ArrayList<>();
    ConsoleOutputManager out= new ConsoleOutputManager();
    ConsoleInputManager in= new ConsoleInputManager();
    Calendar calendar = new GregorianCalendar();
    public GestioneAzienda()//costruttore vuoto
    {
    }
    public void add_sede(String cod,String com)//metodo per l'aggiunta della sede
    {
        Impiegato dir=assumiDir();
        listaimpiegati.add(dir);
        Sede s= new Sede(cod,com,dir.getCF());
        int nrep=in.readInt("Quanti reparti vuoi aprire?");
        for(int i=0; i<nrep; i++)
        {
            s.listareparti.add(creaRep());
        }
        listasedi.add(s);
    }
    public Reparto creaRep()//creazione nuovo reparto nella sede inerente
    {
        ArrayList<String> listaCFimpiegatireparto=new ArrayList<>();
        String nomerep=in.readLine("Nome reparto:");
        Impiegato caporep=assumiCapoRep(nomerep);
        listaimpiegati.add(caporep);
        int s=0;
        do{
            out.println("1)Assumi impiegato");
            out.println("0)Termina assunzioni");
            s=in.readInt();
            if(s==1)
            {
                Impiegato imp=assumiImp();
                listaimpiegati.add(imp);
                listaCFimpiegatireparto.add(imp.getCF());
            }
        }while(s!=0);
        Reparto r= new Reparto(nomerep, caporep.getCF(), listaCFimpiegatireparto);
        return r;
    }
    public Impiegato assumiDir()//assunzione direttore
    {
        out.println("Assunzione direttore sede");
        String nome=in.readLine("Inserisci nome direttore");
        String cog=in.readLine("Inserisci cognome direttore");
        Impiegato imp= new Impiegato(nome,cog, "DIR");
        // Genera una data casuale firma contratto tra il 1 gennaio 2024 e il 31 dicembre 2023
        Date randomDate1= imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
        double stip=in.readDouble("Inserisci importo stipendio contratto a tempo indeterminato");
        imp.add_contract(randomDate1, null, stip);
        return imp;
    }
    public Impiegato assumiCapoRep(String nomer)//assunzione capo reparto
    {
        out.println("Assunzione caporeparto "+nomer);
        String n=in.readLine("Inserisci nome caporeparto");
        String c=in.readLine("Inserisci cognome caporeparto");
        Impiegato imp= new Impiegato(n,c, "CRP");
        // Genera una data casuale firma contratto tra il 1 gennaio 2024 e il 31 dicembre 2023
        Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
        double stip=in.readDouble("Inserisci importo stipendio contratto a tempo indeterminato");
        imp.add_contract(randomDate1, null, stip);
        listaimpiegati.add(imp);
        return imp;
    }
    public Impiegato assumiImp()//assumi impiegati normali
    {
        Random random= new Random();
        String n=in.readLine("Inserisci nome impiegato");
        String c=in.readLine("Inserisci cognome impiegato");
        Impiegato imp= new Impiegato(n,c, "IMP");
        //genero un numero random compreso tra 0 e 9, se n=9 tempo indeterminato
        int nrandom=random.nextInt(10);
        if(nrandom<9)
        {
            // Genera una data casuale tra il 1 gennaio 2023 e il 20 giugno 2023
            Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
            // Genera una data casuale tra il 1 gennaio 2024 e il 31 dicembre 2028
            Date randomDate2 = imp.generateDateContract(calendar, 2024, 0, 1, 2028, 11, 31);
            double stip=in.readDouble("Inserisci importo stipendio contratto a tempo determinato");
            imp.add_contract(randomDate1,randomDate2, stip);//contratto tempo determinato
        }
        else {
            // Genera una data casuale tra il 1 gennaio 2024 e il 31 dicembre 2023
            Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
            double stip=in.readDouble("Inserisci importo stipendio contratto a tempo indeterminato");
            imp.add_contract(randomDate1, null, stip);
        }
        return imp;
    }
}
