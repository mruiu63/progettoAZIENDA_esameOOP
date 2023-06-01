import java.util.ArrayList;

public class Sede {
    private String codice;
    private String comune;
    private String CFdirettore;
    ArrayList<Reparto> listareparti=new ArrayList<>();
    public Sede(String cod, String com,String cfdir)//costruttore con parametri
    {
        this.codice=cod;
        this.comune=com;
        this.CFdirettore=cfdir;
    }
    public String getCodice() {
        return codice;
    }
    public void setCodice(String codice) {
        this.codice = codice;
    }
    public String getComune() {
        return comune;
    }
    public void setComune(String comune) {
        this.comune = comune;
    }
    public String getCFdirettore() {
        return CFdirettore;
    }
    public void setCFdirettore(String CFdirettore) {
        this.CFdirettore = CFdirettore;
    }
    @Override
    public String toString()
    {
        return "SEDE[Codice:"+getCodice()+"\\Comune:"+getComune()+"\\Direttore:"+getCFdirettore()+"]\n";
    }
}
