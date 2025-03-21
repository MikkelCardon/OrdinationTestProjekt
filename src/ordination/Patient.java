package ordination;

import java.util.ArrayList;

public class Patient {
    private String cprnr;
    private String navn;
    private double vaegt;

    ArrayList<Ordination> ordinationArrayList = new ArrayList<>();

    public Patient(String cprnr, String navn, double vaegt) {
        this.cprnr = cprnr;
        this.navn = navn;
        this.vaegt = vaegt;

        if (vaegt <= 0){
            throw new RuntimeException("Vægt skal være over 0 kg");
        }
    }

    public String getCprnr() {
        return cprnr;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public double getVaegt(){
        return vaegt;
    }

    public void setVaegt(double vaegt){
        this.vaegt = vaegt;
    }

    public void addOrdination(Ordination ordination){
        if (!ordinationArrayList.contains(ordination)){
            ordinationArrayList.add(ordination);
        }
    }

    public ArrayList<Ordination> getOrdinationer() {
        return ordinationArrayList;
    }


    @Override
    public String toString(){
        return navn + "  " + cprnr;
    }

}
