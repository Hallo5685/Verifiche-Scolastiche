package Utility;

public class Contatto {

    public String nome;
    public String cognome;
    public String telefono;
    public double saldo;
    public String password = "cervo12";

    /*public double ricarica;
    public Contatto(String nome, String cognome) {
        this.nome=nome;
        this.ricarica=10;
    }
    public String getNome(){
        return this.nome.toUpperCase();
    }
    public void setTelefono(String telefono){
        this.telefono=telefono;
    }
    public String getTelefono(){
        return this.telefono;
    }*/

    public String stampa()
    {
        return String.format("Nome: %s Cognome: %s Telefono: %s, tipo: %s", nome, cognome, telefono);
    }

}
