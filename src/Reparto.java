import java.util.ArrayList;

public class Reparto {
    private String nome;
    private String CFcapo;
    ArrayList<String> listaCFimpiegati=new ArrayList<>();
    public Reparto(String n, String cf, ArrayList<String> listaCF)//costruttore con parametri
    {
        this.setNome(n);
        this.CFcapo=cf;
        listaCFimpiegati.addAll(listaCF);
    }

    public String getCFcapo() {
        return CFcapo;
    }

    public void setCFcapo(String CFcapo) {
        this.CFcapo = CFcapo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    @Override
    public String toString()
    {
        String repartostr="REPARTO["+nome+"\\CAPO:"+CFcapo+"]\nIMPIEGATI:";
        for(String CF: listaCFimpiegati)
        {
            repartostr+=CF+"\\";
        }
        return repartostr;
    }
}
