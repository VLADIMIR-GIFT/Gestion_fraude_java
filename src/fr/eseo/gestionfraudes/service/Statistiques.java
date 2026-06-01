package fr.eseo.gestionfraudes.service;

import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;

import java.util.ArrayList;
import java.util.List;

public class Statistiques {

    private int nombreFormulaires;
    private int nombreEtudiantsDistincts;
    private int nombreTotalFraudes;
    private double moyenneFraudesParFormulaire;
    private double ecartType;

    public Statistiques(List<Formulaire> formulaires) {
        calculer(formulaires);
    }

    private void calculer(List<Formulaire> formulaires) {
        nombreFormulaires = formulaires.size();

        List<Etudiant> etudiantsVus = new ArrayList<>();
        nombreTotalFraudes = 0;
        for (Formulaire f : formulaires) {
            nombreTotalFraudes = nombreTotalFraudes + f.getFraudes().size();
            for (Etudiant e : f.getEtudiants()) {
                if (!etudiantsVus.contains(e)) {
                    etudiantsVus.add(e);
                }
            }
        }
        nombreEtudiantsDistincts = etudiantsVus.size();

        if (nombreFormulaires == 0) {
            moyenneFraudesParFormulaire = 0.0;
            ecartType = 0.0;
            return;
        }

        moyenneFraudesParFormulaire = (double) nombreTotalFraudes / nombreFormulaires;

        double sommeDesCarres = 0.0;
        for (Formulaire f : formulaires) {
            double ecart = f.getFraudes().size() - moyenneFraudesParFormulaire;
            sommeDesCarres = sommeDesCarres + ecart * ecart;
        }
        ecartType = Math.sqrt(sommeDesCarres / nombreFormulaires);
    }

    public int getNombreFormulaires() {
        return nombreFormulaires;
    }

    public int getNombreEtudiantsDistincts() {
        return nombreEtudiantsDistincts;
    }

    public int getNombreTotalFraudes() {
        return nombreTotalFraudes;
    }

    public double getMoyenneFraudesParFormulaire() {
        return moyenneFraudesParFormulaire;
    }

    public double getEcartType() {
        return ecartType;
    }

    @Override
    public String toString() {
        return "Statistiques :\n"
                + "  Formulaires          : " + nombreFormulaires + "\n"
                + "  Etudiants distincts  : " + nombreEtudiantsDistincts + "\n"
                + "  Fraudes (total)      : " + nombreTotalFraudes + "\n"
                + "  Moyenne par formulaire : " + moyenneFraudesParFormulaire + "\n"
                + "  Ecart-type           : " + ecartType;
    }
}
