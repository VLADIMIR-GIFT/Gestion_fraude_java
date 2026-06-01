package fr.eseo.gestionfraudes.service;

import java.io.FileWriter;
import java.io.IOException;

public class ExportCSV {

    public static boolean exporterStatistiques(Statistiques stats, String chemin) {
        try {
            FileWriter writer = new FileWriter(chemin);

            writer.write("indicateur;valeur\n");

            writer.write("nombre_formulaires;" + stats.getNombreFormulaires() + "\n");
            writer.write("nombre_etudiants_distincts;" + stats.getNombreEtudiantsDistincts() + "\n");
            writer.write("nombre_total_fraudes;" + stats.getNombreTotalFraudes() + "\n");
            writer.write("moyenne_fraudes_par_formulaire;" + stats.getMoyenneFraudesParFormulaire() + "\n");
            writer.write("ecart_type;" + stats.getEcartType() + "\n");

            writer.close();
            return true;
        } catch (IOException e) {
            System.out.println("Erreur lors de l'ecriture du fichier CSV : " + e.getMessage());
            return false;
        }
    }
}
