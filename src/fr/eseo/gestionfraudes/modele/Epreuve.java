package fr.eseo.gestionfraudes.modele;

import java.time.Duration;
import java.time.LocalDateTime;


public class Epreuve {

    private String codeEcue;
    private LocalDateTime datePassage;
    private Duration duree;
    private Modalite modalite;

    public Epreuve() {
    }

    public Epreuve(String codeEcue, LocalDateTime datePassage, Duration duree, Modalite modalite) {
        this.codeEcue = codeEcue;
        this.datePassage = datePassage;
        this.duree = duree;
        this.modalite = modalite;
    }

    public String getCodeEcue() {
        return codeEcue;
    }

    public void setCodeEcue(String codeEcue) {
        this.codeEcue = codeEcue;
    }

    public LocalDateTime getDatePassage() {
        return datePassage;
    }

    public void setDatePassage(LocalDateTime datePassage) {
        this.datePassage = datePassage;
    }

    public Duration getDuree() {
        return duree;
    }

    public void setDuree(Duration duree) {
        this.duree = duree;
    }

    public Modalite getModalite() {
        return modalite;
    }

    public void setModalite(Modalite modalite) {
        this.modalite = modalite;
    }

    @Override
    public String toString() {
        return codeEcue + " (" + modalite + ")";
    }
}
