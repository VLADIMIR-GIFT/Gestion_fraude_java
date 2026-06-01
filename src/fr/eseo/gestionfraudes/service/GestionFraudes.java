package fr.eseo.gestionfraudes.service;

import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;

import java.util.ArrayList;
import java.util.List;

public class GestionFraudes {

    private List<Formulaire> formulaires;

    public GestionFraudes() {
        this.formulaires = new ArrayList<>();
    }

    public void ajouterFormulaire(Formulaire formulaire) {
        formulaires.add(formulaire);
    }

    public boolean retirerFormulaire(int identifiant) {
        for (Formulaire f : formulaires) {
            if (f.getIdentifiant() == identifiant) {
                formulaires.remove(f);
                return true;
            }
        }
        return false;
    }

    public List<Formulaire> getFormulaires() {
        return formulaires;
    }

    public List<Formulaire> rechercherFormulairesParEtudiant(Etudiant etudiant) {
        List<Formulaire> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            if (f.getEtudiants().contains(etudiant)) {
                resultat.add(f);
            }
        }
        return resultat;
    }

    public List<Formulaire> rechercherFormulairesParEpreuve(String codeEcue) {
        List<Formulaire> resultat = new ArrayList<>();
        for (Formulaire f : formulaires) {
            if (f.getEpreuve() != null && f.getEpreuve().getCodeEcue().equals(codeEcue)) {
                resultat.add(f);
            }
        }
        return resultat;
    }

    public List<Etudiant> rechercherEtudiantsParNom(String nom) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Etudiant e : collecterEtudiants()) {
            if (e.getNom().toLowerCase().contains(nom.toLowerCase())) {
                resultat.add(e);
            }
        }
        return resultat;
    }

    public List<Etudiant> rechercherEtudiantsParPrenom(String prenom) {
        List<Etudiant> resultat = new ArrayList<>();
        for (Etudiant e : collecterEtudiants()) {
            if (e.getPrenom().toLowerCase().contains(prenom.toLowerCase())) {
                resultat.add(e);
            }
        }
        return resultat;
    }

    public Etudiant trouverEtudiantParNumero(String numApprenant) {
        for (Etudiant e : collecterEtudiants()) {
            if (e.getNumApprenant().equals(numApprenant)) {
                return e;
            }
        }
        return null;
    }

    public List<Etudiant> collecterEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        for (Formulaire f : formulaires) {
            for (Etudiant e : f.getEtudiants()) {
                if (!etudiants.contains(e)) {
                    etudiants.add(e);
                }
            }
        }
        return etudiants;
    }
}
