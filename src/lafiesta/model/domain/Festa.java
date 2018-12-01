package lafiesta.model.domain;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.SocketTimeoutException;

public class Festa {

    private int id;
    private String nome_festa;
    private String data;
    private String local;
    private int convidados;

    public Festa() {}

    public Festa(int id, String nome_festa, String data, String local, int convidados){
        this.setId(id);
        this.setNome_festa(nome_festa);
        this.setData(data);
        this.setLocal(local);
        this.setConvidados(convidados);
    }

    public String getEndereco(String CEP) throws IOException {

        //***************************************************
        try{

            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                    .timeout(120000)
                    .get();
            Elements urlPesquisa = doc.select("span[itemprop=streetAddress]");
            for (Element urlEndereco : urlPesquisa) {
                return urlEndereco.text();
            }

        } catch (SocketTimeoutException e) {
            System.out.println("1");
        } catch (HttpStatusException w) {
            System.out.println("2");
        }

        return CEP;
    }

    public String getBairro(String CEP) throws IOException {

        //***************************************************
        try{

            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                    .timeout(120000)
                    .get();
            Elements urlPesquisa = doc.select("td:gt(1)");
            for (Element urlBairro : urlPesquisa) {
                return urlBairro.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }

    public String getCidade(String CEP) throws IOException {

        //***************************************************
        try{

            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                    .timeout(120000)
                    .get();
            Elements urlPesquisa = doc.select("span[itemprop=addressLocality]");
            for (Element urlCidade : urlPesquisa) {
                return urlCidade.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }

    public String getUF(String CEP) throws IOException {

        //***************************************************
        try{

            Document doc = Jsoup.connect("http://www.qualocep.com/busca-cep/"+CEP)
                    .timeout(120000)
                    .get();
            Elements urlPesquisa = doc.select("span[itemprop=addressRegion]");
            for (Element urlUF : urlPesquisa) {
                return urlUF.text();
            }

        } catch (SocketTimeoutException e) {

        } catch (HttpStatusException w) {

        }
        return CEP;
    }

    public String getNome_festa() {
        return nome_festa;
    }

    public void setNome_festa(String nome_festa) {
        this.nome_festa = nome_festa;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public int getConvidados() {
        return convidados;
    }

    public void setConvidados(int convidados) {
        this.convidados = convidados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
