package fr.eseo.gestionfraudes;

import fr.eseo.gestionfraudes.graphe.GrapheFraudeurs;
import fr.eseo.gestionfraudes.service.DonneesTest;
import fr.eseo.gestionfraudes.service.ExportCSV;
import fr.eseo.gestionfraudes.service.GestionFraudes;
import fr.eseo.gestionfraudes.service.Statistiques;

/**
 * Point d'entree de l'application.
 *
 * On charge un jeu de donnees de test, on affiche les formulaires, le graphe
 * des fraudeurs et les statistiques, puis on exporte ces dernieres en CSV.
 * La vue interactive (menu) sera ajoutee ensuite.
 */
public class Main {

    public static void main(String[] args) {
        GestionFraudes gestion = new GestionFraudes();
        DonneesTest.initialiser(gestion);

        System.out.println("=== Gestion des fraudes ESEO ===");
        System.out.println(gestion.getFormulaires());

        GrapheFraudeurs graphe = new GrapheFraudeurs();
        graphe.construire(gestion.getFormulaires());
        System.out.println(graphe.afficherTextuel());

        Statistiques stats = new Statistiques(gestion.getFormulaires());
        System.out.println(stats);

        ExportCSV.exporterStatistiques(stats, "statistiques.csv");
        System.out.println("\nStatistiques exportees dans statistiques.csv");
    }
}
