import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Contratto implements Serializable,Comparable,Iterable<Contratto> {
    private String CFimpiegato;
    private Date datainizio;
    private Date datafine;
    private double stipendio;
    public Contratto(String CF, Date di, Date df, double stip)//costruttore con parametri
    {
        this.CFimpiegato=CF;
        this.datainizio=di;
        this.datafine=df;
        this.stipendio=stip;
    }
    public String getCFimpiegato() {
        return CFimpiegato;
    }
    public void setCFimpiegato(String CFimpiegato) {
        this.CFimpiegato = CFimpiegato;
    }
    public Date getDatainizio() {
        return datainizio;
    }
    public void setDatainizio(Date datainizio) {
        this.datainizio = datainizio;
    }
    public Date getDatafine() {
        return datafine;
    }
    public void setDatafine(Date datafine) {
        this.datafine = datafine;
    }
    public double getStipendio() {
        return stipendio;
    }
    public void setStipendio(double stipendio) {
        this.stipendio = stipendio;
    }
    public boolean eseguiRevisione()
    {
        Date dataCorrente=new Date();
        if(getDatafine()==null||getDatafine().after(dataCorrente))
        {
            return true;
        }
        else return false;
    }
    public String toString1()
    {
        String tipocont= (datafine==null)?"tempo indeterminato":"tempo determinato";
        if(tipocont=="tempo indeterminato")
        {
            return "Tipo:"+tipocont+"\\Inizio:"+datainizio+"\\Stipendio:"+stipendio+"]\n";
        }
        else return "Tipo:"+tipocont+"\\Inizio:"+datainizio+"\\Fine:"+datafine+"\\Stipendio:"+stipendio+"]\n";
    }
    @Override
    public String toString()//metodo toString per visualizzare i dati del contratto
    {
        String tipocont= (datafine==null)?"tempo indeterminato":"tempo determinato";
        if(tipocont=="tempo indeterminato")
        {
            return "Contratto\n[CF:"+CFimpiegato+"\\Tipo:"+tipocont+"\\Inizio:"+datainizio+"\\Stipendio:"+stipendio+"]";
        }
        else return "Contratto\n[CF:"+CFimpiegato+"\\Tipo:"+tipocont+"\\Inizio:"+datainizio+"\\Fine:"+datafine+"\\Stipendio:"+stipendio+"]";
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
    ArrayList<Contratto>contrattoArrayList=new ArrayList<>();
    @Override
    public Iterator<Contratto> iterator() {
        return contrattoArrayList.iterator();
    }
}
