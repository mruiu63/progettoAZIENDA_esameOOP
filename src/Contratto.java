import java.util.Date;

public class Contratto {
    private String CFimpiegato;
    private Date datainizio;
    private Date datafine;
    private double stipendio;

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
}
