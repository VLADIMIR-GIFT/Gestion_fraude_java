package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

/** Fraude au moyen d'une antiseche papier. */
public class FraudePapier extends Fraude {

    private String dimensions;
    private boolean plie;

    public FraudePapier() {
    }

    public FraudePapier(LocalDate dateReleve, String description, String dimensions, boolean plie) {
        super(dateReleve, description);
        this.dimensions = dimensions;
        this.plie = plie;
    }

    @Override
    public TypeFraude getType() {
        return TypeFraude.PAPIER;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public boolean isPlie() {
        return plie;
    }

    public void setPlie(boolean plie) {
        this.plie = plie;
    }
}
