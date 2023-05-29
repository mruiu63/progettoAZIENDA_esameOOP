import java.util.Date;

public class Contratto {
    private String CFimpiegato;
    private Date datainizio;
    private Date datafine;
    private double stipendio;
    public Contratto(String CF, Date di, Date df, double stip)
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
    @Override
    public String toString()
    {
        String tipocont= (datafine==null)?"tempo indeterminato":"tempo determinato";
        return "Contratto [CF: "+CFimpiegato+", Inizio: "+datainizio+", Fine: "+datafine+", Stipendio: "+stipendio+", Tipo:"+tipocont+"]";
    }
}
