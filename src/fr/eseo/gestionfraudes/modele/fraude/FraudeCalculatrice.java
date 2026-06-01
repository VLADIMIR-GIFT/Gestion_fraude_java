package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

public class FraudeCalculatrice extends Fraude {

    private String marque;
    private String programme;

    public FraudeCalculatrice() {
    }

    public FraudeCalculatrice(LocalDate dateReleve, String description, String marque, String programme) {
        super(dateReleve, description);
        this.marque = marque;
        this.programme = programme;
    }

    @Override
    public TypeFraude getType() {
        return TypeFraude.CALCULATRICE;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getProgramme() {
        return programme;
    }

    public void setProgramme(String programme) {
        this.programme = programme;
    }
}
