package ordination;

import java.time.LocalDate;
import java.util.ArrayList;

public class PN extends Ordination{
    private int antalGivninger = 0;
    private double antalEnheder;
    ArrayList<LocalDate> datoerGivet = new ArrayList<>();

    public PN(LocalDate startDato, LocalDate slutDato, Laegemiddel laegemiddel, double antalEnheder) {
        super(startDato, slutDato, laegemiddel);
        this.antalEnheder = antalEnheder;
    }

    /**
     * Registrerer at der er givet en dosis paa dagen givetDato
     * Returnerer true hvis givetDato er inden for ordinationens gyldighedsperiode og datoen huskes
     * Retrurner false ellers og datoen givetDato ignoreres
     * @param givetDato
     * @return
     */
    public boolean givDosis(LocalDate givetDato) {
        if(givetDato.isAfter(getStartDato().minusDays(1)) && givetDato.isBefore(getSlutDato().plusDays(1))){
           antalGivninger++;
           datoerGivet.add(givetDato);
           return true;
        }else{
            System.out.println("givetDato er ikke indenfor start og slut datoer.");
            return false;
        }
    }

    public double doegnDosis() {
        return (antalGivninger * antalEnheder) / (antalDage());
    }

    @Override
    public String getType() {
        return "PN";
    }


    public double samletDosis() {
        return antalGivninger * antalEnheder;
    }

    /**
     * Returnerer antal gange ordinationen er anvendt
     * @return
     */
    public int getAntalGangeGivet() {
        return antalGivninger;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    public ArrayList<LocalDate> getDatoerGivet() {
        return datoerGivet;
    }
}
