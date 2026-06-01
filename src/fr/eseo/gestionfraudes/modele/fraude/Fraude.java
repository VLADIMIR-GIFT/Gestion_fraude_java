package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

public abstract class Fraude {

    private LocalDate dateReleve;
    private String description;

    protected Fraude() {
    }

    protected Fraude(LocalDate dateReleve, String description) {
        this.dateReleve = dateReleve;
        this.description = description;
    }

    public abstract TypeFraude getType();

    public LocalDate getDateReleve() {
        return dateReleve;
    }

    public void setDateReleve(LocalDate dateReleve) {
        this.dateReleve = dateReleve;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return getType() + " : " + description;
    }
}
