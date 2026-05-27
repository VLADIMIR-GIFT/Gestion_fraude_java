package fr.eseo.gestionfraudes.modele;

import fr.eseo.gestionfraudes.modele.fraude.Fraude;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class Formulaire {

    private static int compteur = 1;

    private int identifiant;
    private LocalDateTime dateCreation;
    private Epreuve epreuve;
    private List<Etudiant> etudiants;
    private List<Fraude> fraudes;

    public Formulaire() {
        this.identifiant = compteur;
        compteur++;
        this.dateCreation = LocalDateTime.now();
        this.etudiants = new ArrayList<>();
        this.fraudes = new ArrayList<>();
    }

    public void ajouterEtudiant(Etudiant etudiant) {
        etudiants.add(etudiant);
    }

    public void ajouterFraude(Fraude fraude) {
        fraudes.add(fraude);
    }

    public int getIdentifiant() {
        return identifiant;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }

    public List<Etudiant> getEtudiants() {
        return etudiants;
    }

    public List<Fraude> getFraudes() {
        return fraudes;
    }

    @Override
    public String toString() {
        return "Formulaire #" + identifiant
                + " (epreuve=" + (epreuve == null ? "?" : epreuve.getCodeEcue())
                + ", " + etudiants.size() + " etudiant(s), "
                + fraudes.size() + " fraude(s))";
    }
}
