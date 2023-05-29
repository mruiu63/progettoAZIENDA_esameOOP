import java.util.ArrayList;

public class Reparto {
    private String nome;
    private String CFcapo;
    ArrayList<String> listaCFimpiegati=new ArrayList<>();
    public Reparto(String n, String cf)
    {
        this.setNome(n);
        this.CFcapo=cf;
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
}
