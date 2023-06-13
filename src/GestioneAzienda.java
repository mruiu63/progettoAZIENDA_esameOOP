import java.io.*;
import prog.io.ConsoleInputManager;
import prog.io.ConsoleOutputManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.*;

public class GestioneAzienda implements Serializable{
    ArrayList<Impiegato> listaimpiegati=new ArrayList<>();
    ArrayList<String> listaCFimpFree=new ArrayList<>();
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
        Sede s = null;
        if(listaCFimpFree.isEmpty())
        {
            Impiegato dir=assumiDir();
            listaimpiegati.add(dir);
            s= new Sede(cod,com,dir.getCF());
            int nrep=in.readInt("Quanti reparti vuoi aprire?");
            for(int i=0; i<nrep; i++)
            {
                s.listareparti.add(creaRep());
            }
            Collections.sort(listaimpiegati);
            listasedi.add(s);
        }
        else
        {
            boolean dirinserito=false;
            for(Impiegato imp:listaimpiegati)
            {
                for(String CF: listaCFimpFree)
                {
                    if(imp.getCF().equals(CF) && imp.getRuolo().equals("DIR"))
                    {
                        listaCFimpFree.remove(CF);
                        s=new Sede(cod,com,imp.getCF());//direttore preso dalla lista dei lavoratori senza nessuna assegnazione
                        dirinserito=true;
                        break;
                    }
                }
            }
            if(!dirinserito)
            {
                Impiegato dir=assumiDir();
                listaimpiegati.add(dir);
                s= new Sede(cod,com,dir.getCF());
            }
            int nrep=in.readInt("Quanti reparti vuoi aprire?");
            for(int i=0; i<nrep; i++)
            {
                s.listareparti.add(creaRep());
            }
            Collections.sort(listaimpiegati);
            listasedi.add(s);
        }
    }
    public Reparto creaRep()//creazione nuovo reparto nella sede inerente
    {
        Reparto r=null;
        ArrayList<String> listaCFimpiegatireparto = new ArrayList<>();
        if(listaCFimpFree.isEmpty()) {
            String nomerep = in.readLine("Nome reparto:");
            Impiegato caporep = assumiCapoRep(nomerep);
            listaimpiegati.add(caporep);
            int s = 0;
            do {
                out.println("1)Assumi impiegato");
                out.println("0)Termina assunzioni");
                s = in.readInt();
                if (s == 1) {
                    Impiegato imp = assumiImp();
                    listaimpiegati.add(imp);
                    listaCFimpiegatireparto.add(imp.getCF());
                }
            } while (s != 0);
            Collections.sort(listaimpiegati);
            r = new Reparto(nomerep, caporep.getCF(), listaCFimpiegatireparto);
        }
        else
        {
            String CFcrep=null;
            String nomerep = in.readLine("Nome reparto:");
            boolean crpinserito=false;
            for(Impiegato imp:listaimpiegati)
            {
                for(String CF: listaCFimpFree)
                {
                    if(imp.getCF().equals(CF) && imp.getRuolo().equals("CRP"))
                    {
                        listaCFimpFree.remove(CF);
                        CFcrep=CF;
                        crpinserito=true;//caporeparto inserito dalla lista degli impiegati liberi in base al suo ruolo
                        break;
                    }
                }
            }
            if(!crpinserito)
            {
                Impiegato caporep = assumiCapoRep(nomerep);
                listaimpiegati.add(caporep);
            }
            int s = 0;
            do {
                out.println("1)Assumi impiegato");
                out.println("0)Termina assunzioni");
                s = in.readInt();
                if (s == 1) {
                    if(!listaCFimpFree.isEmpty()) {
                        for (Impiegato imp : listaimpiegati) {
                            for (String CF : listaCFimpFree) {
                                if (imp.getCF().equals(CF) && imp.getRuolo().equals("IMP")) {
                                    listaCFimpFree.remove(CF);
                                    listaCFimpiegatireparto.add(imp.getCF());//impiegato assegnato al reparto dalla lista di quelli liberi
                                    break;
                                }
                            }
                        }
                    }
                    else
                    {
                        Impiegato imp = assumiImp();
                        listaimpiegati.add(imp);
                        listaCFimpiegatireparto.add(imp.getCF());
                    }
                }
            } while (s != 0);
            r = new Reparto(nomerep, CFcrep, listaCFimpiegatireparto);
        }
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
        Contratto c = new Contratto(imp.getCF(), randomDate1, null, stip);
        imp.add_contract(randomDate1, null, stip);
        listacontratti.add(c);
        return imp;
    }
    public Impiegato assumiCapoRep(String nomer)//assunzione capo reparto
    {
        out.println("Assunzione caporeparto "+nomer);
        String nome=in.readLine("Inserisci nome caporeparto");
        String cog=in.readLine("Inserisci cognome caporeparto");
        Impiegato imp= new Impiegato(nome,cog, "CRP");
        // Genera una data casuale firma contratto tra il 1 gennaio 2024 e il 31 dicembre 2023
        Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
        double stip=in.readDouble("Inserisci importo stipendio contratto a tempo indeterminato");
        Contratto c = new Contratto(imp.getCF(), randomDate1, null, stip);
        imp.add_contract(randomDate1, null, stip);
        listacontratti.add(c);
        return imp;
    }
    public Impiegato assumiImp()//assumi impiegati normali
    {
        Random random= new Random();
        String nome=in.readLine("Inserisci nome impiegato");
        String cog=in.readLine("Inserisci cognome impiegato");
        Impiegato imp= new Impiegato(nome,cog, "IMP");
        //genero un numero random compreso tra 0 e 9, se n=9 tempo indeterminato
        int nrandom=random.nextInt(10);
        if(nrandom<9)
        {
            // Genera una data casuale tra il 1 gennaio 2023 e il 20 giugno 2023
            Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
            // Genera una data casuale tra il 1 gennaio 2024 e il 31 dicembre 2028
            Date randomDate2 = imp.generateDateContract(calendar, 2024, 0, 1, 2028, 11, 31);
            double stip=in.readDouble("Inserisci importo stipendio contratto a tempo determinato");
            Contratto c = new Contratto(imp.getCF(), randomDate1, randomDate2, stip);
            imp.add_contract(randomDate1, randomDate2, stip);
            listacontratti.add(c);
        }
        else {
            // Genera una data casuale tra il 1 gennaio 2024 e il 31 dicembre 2023
            Date randomDate1 = imp.generateDateContract(calendar, 2023, 0, 1, 2023, 11, 31);
            double stip=in.readDouble("Inserisci importo stipendio contratto a tempo indeterminato");
            Contratto c = new Contratto(imp.getCF(), randomDate1, null, stip);
            imp.add_contract(randomDate1, null, stip);
            listacontratti.add(c);
        }
        return imp;
    }
    public void VisualizzaDati()
    {
        if(!listasedi.isEmpty())
        {
            for(Sede sede: listasedi)
            {
                out.println(sede);
                for(Reparto reparto:sede.listareparti)
                {
                    out.println(reparto);
                    for(String CF: reparto.listaCFimpiegati)
                    {
                        out.println(CF);
                    }
                }
            }
            out.println("Impiegati presenti ma senza assegnazione a sede/reparto");
            for(String CF:listaCFimpFree)
            {
                out.println(CF);
            }
        }
        else
        {
            out.println("L'azienda non ha sedi aperte");
            if(!listaCFimpFree.isEmpty()) {
                out.println("Impiegati presenti ma senza assegnazione a sede/reparto");
                for (String CF : listaCFimpFree) {
                    out.println(CF);
                }
            }
        }
    }
    public Reparto RicercaRep(String nomerep)
    {
        Reparto r = null;
        for(Sede s: listasedi)
        {
            for(Reparto rep: s.listareparti)
            {
                if(nomerep.equals(rep.getNome()))
                {
                    r=rep;
                }
            }
        }
        return r;
    }
    public void spostamentoRepImp(String nomeImp, String nuovorep)
    {
        for(Sede s: listasedi)
        {
            for(Reparto rep: s.listareparti)
            {
                for(String CF: rep.listaCFimpiegati)
                {
                    if(nomeImp.equals(CF))
                    {
                        rep.listaCFimpiegati.remove(nomeImp);
                        break;
                    }
                }
            }
        }
        for(Sede s:listasedi)
        {
            for(Reparto rep: s.listareparti)
            {
                if(nuovorep.equals(rep.getNome()))
                {
                    rep.listaCFimpiegati.add(nomeImp);
                    break;
                }
            }
        }
    }
    public void ApriNuoviRep(Sede sede) {
        int nrep=in.readInt("Quanti nuovi reparti vuoi aprire?");
        for(int i=0; i<nrep; i++)
        {
            sede.listareparti.add(creaRep());
        }
    }
    public void chiudiSede(String codice)
    {
        for(Sede sede:listasedi)
        {
            if (sede.getCodice().equals(codice)) {
                listaCFimpFree.add(sede.getCFdirettore());
                for (Reparto rep : sede.listareparti) {
                    listaCFimpFree.add(rep.getCFcapo());
                    listaCFimpFree.addAll(rep.listaCFimpiegati);
                }
                listasedi.remove(sede);
            }
            if(listasedi.size()==0)
                break;
        }
    }
    public boolean chiudiReparto(String repToCanc)
    {
        boolean repesistente=false;
        //scansione sedi per trovare rep
        for(Sede sede: listasedi)
        {
            for(Reparto rep: sede.listareparti)
            {
                if(rep.getNome().equals(repToCanc))
                {
                    repesistente=true;
                    listaCFimpFree.add(rep.getCFcapo());
                    listaCFimpFree.addAll(rep.listaCFimpiegati);
                    sede.listareparti.remove(rep);
                }
            }
        }
        return repesistente;
    }
}
