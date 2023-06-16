import prog.io.ConsoleOutputManager;
import java.io.Serializable;
import java.util.*;
public class Impiegato implements Comparable<Impiegato>, Serializable, Iterable<Impiegato> {
    private String CF="";
    private String nome;
    private String cognome;
    private String ruolo;//codice per ruolo assunto nell'azienda
    /*
    CRP=capo reparto
    DIR= direttore
    IMP= impiegato
    */
    ArrayList<Contratto> listacontratti=new ArrayList<>();
    ConsoleOutputManager out= new ConsoleOutputManager();
    public Impiegato(String n, String c, String r)//costruttore con parametri
    {
        this.nome=n;
        this.cognome=c;
        //codice fiscale
        this.CF=creaCF(n, c);
        this.ruolo=r;
    }
    public String getCF() {
        return CF;
    }
    public void setCF(String CF) {
        this.CF = CF;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCognome() {
        return cognome;
    }
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }
    public String getRuolo() {
        return ruolo;
    }
    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    public String creaCF(String n, String c)//metodo per generare un codice fiscale
    {
        Random random=new Random();
        //cognome
        if(c.length()<3)
        {
            this.CF+=c.toUpperCase();
            for(int i=c.length(); i<3;i++)
            {
                this.CF+="X";
            }
        }
        else {
            this.CF += c.substring(0, 3).toUpperCase();
        }
        //nome
        if(n.length()<3)
        {
            this.CF+=n.toUpperCase();
            for(int i=n.length(); i<3;i++)
            {
                this.CF+="X";
            }
        }
        else {
            this.CF += n.substring(0, 3).toUpperCase();
        }
        for(int i=0; i<3;i++)
        {
            //genero numero casuale e poi lo converto in lettere utilizzando la codifica Unicode per le lettere maiuscole
            char letteraRnd=(char)(random.nextInt(26)+'A');
            this.CF+=letteraRnd;
        }
        return this.CF;
    }
    public void add_contract(Date di, Date df, double stipendio)//metodo di aggiunta contratto per l'impiegato
    {
        Contratto c= new Contratto(getCF(), di, df, stipendio);
        listacontratti.add(c);
    }
    public Date generateDateContract(Calendar calendar, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay)//metoto di generazione data automatica per il contratto
    {
        Random random= new Random();
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = startMonth + random.nextInt(12 - startMonth);
        int day = startDay + random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - startDay + 1);
        calendar.set(year, month, day);
        return calendar.getTime();
    }
    @Override
    public String toString()
    {
        return "Impiegato\n[CF="+CF+"\\"+cognome+" "+nome+"\\ruolo:"+ruolo+"]";
    }
    @Override
    public boolean equals(Object o)
    {
        if(this ==o) return true;
        if(o==null||getClass()!=o.getClass()) return false;
        Impiegato imp=(Impiegato) o;
        return Objects.equals(CF, imp.CF);
    }
    @Override
    public int compareTo(Impiegato o) {
        return this.CF.compareTo(o.CF);
    }
    ArrayList<Impiegato>impiegatoArrayList=new ArrayList<>();
    @Override
    public Iterator<Impiegato> iterator() {
        return impiegatoArrayList.iterator();
    }
}
