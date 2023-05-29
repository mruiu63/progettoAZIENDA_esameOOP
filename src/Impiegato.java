import prog.io.ConsoleOutputManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class Impiegato {
    private String CF;
    private String nome;
    private String cognome;
    private String ruolo;
    ArrayList<Contratto> listacontratti=new ArrayList<>();
    ConsoleOutputManager out= new ConsoleOutputManager();
    public Impiegato(String n, String c, String r)
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
    public String creaCF(String n, String c)
    {
        //cognome
        this.CF+=c.substring(0,3).toUpperCase();
        if(c.length()<3)
            this.CF+=String.format("%-"+(3-c.length())+"s","").replace(' ','X');
        //nome
        this.CF+=n.substring(0,3).toUpperCase();
        if(n.length()<3)
            this.CF+=String.format("%-"+(3-n.length())+"s","").replace(' ','X');
        return this.CF;
    }
    public void add_contract(Date di, Date df, double stipendio)
    {
        Contratto c= new Contratto(getCF(), di, df, stipendio);
        listacontratti.add(c);
        for(Contratto contratto: listacontratti)
            out.println(contratto);
    }
    public Date generateDateContract(Calendar calendar, int startYear, int startMonth, int startDay, int endYear, int endMonth, int endDay) {
        Random random= new Random();
        int year = startYear + random.nextInt(endYear - startYear + 1);
        int month = startMonth + random.nextInt(12 - startMonth);
        int day = startDay + random.nextInt(calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - startDay + 1);
        calendar.set(year, month, day);
        return calendar.getTime();
    }
}
