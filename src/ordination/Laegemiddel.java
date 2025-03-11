package ordination;

public class Laegemiddel {
    private String navn;
    private double enhedPrKgPrDoegnLet;   // faktor der anvendes hvis patient vejer < 25 kg
    private double enhedPrKgPrDoegnNormal;// faktor der anvendes hvis 25 kg <= patient vægt <= 120 kg
    private double enhedPrKgPrDoegnTung;  // faktor der anvendes hvis patient vægt > 120 kg 
    private String enhed;

    public Laegemiddel(String navn, double enhedPrKgPrDoegnLet, double enhedPrKgPrDoegnNormal, 
            double enhedPrKgPrDoegnTung, String enhed) {
        this.navn = navn;
        this.enhedPrKgPrDoegnLet = enhedPrKgPrDoegnLet;
        this.enhedPrKgPrDoegnNormal = enhedPrKgPrDoegnNormal;
        this.enhedPrKgPrDoegnTung = enhedPrKgPrDoegnTung;
        this.enhed = enhed;
    }

    public String getEnhed() {
        return enhed;
    }

    public String getNavn() {
        return navn;
    }

    //ToDo: ENHED pr. kilo. Der er derfor ganget op med vægt
    public double anbefaletDosisPrDoegn(double vaegt) {
        if (vaegt < 25) return enhedPrKgPrDoegnLet*vaegt;
        else if (vaegt > 120) return enhedPrKgPrDoegnTung*vaegt;
        else return enhedPrKgPrDoegnNormal*vaegt;
    }

    @Override
    public String toString(){
        return navn;
    }
}
