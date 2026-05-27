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
}
