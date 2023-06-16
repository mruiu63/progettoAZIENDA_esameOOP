import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
public class Reparto implements Serializable, Comparable,Iterable<Reparto> {
    private String nome;
    private String CFcapo;
    ArrayList<String> listaCFimpiegati=new ArrayList<>();
    public Reparto(String n, String cf, ArrayList<String> listaCF)//costruttore con parametri
    {
        this.setNome(n);
        this.setCFcapo(cf);
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
        return "REPARTO\n[NOME:"+getNome()+"\\CAPO:"+getCFcapo()+"]";
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
    ArrayList<Reparto>repartoArrayList=new ArrayList<>();
    @Override
    public Iterator<Reparto> iterator() {
        return repartoArrayList.iterator();
    }
}
