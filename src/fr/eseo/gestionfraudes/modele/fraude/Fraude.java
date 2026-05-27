package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

/**
 * Classe abstraite representant une fraude.
 * Une fraude "generique" n'existe pas : c'est toujours du papier, une
 * calculatrice ou une IA. On la rend donc abstraite et chaque sous-classe
 * doit indiquer son type via getType().
 */
public abstract class Fraude {

    private LocalDate dateReleve;
    private String description;

    protected Fraude() {
    }

    protected Fraude(LocalDate dateReleve, String description) {
        this.dateReleve = dateReleve;
        this.description = description;
    }

    /** Chaque sous-classe renvoie son propre type : c'est le polymorphisme. */
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
