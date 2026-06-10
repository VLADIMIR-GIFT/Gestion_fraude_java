package fr.eseo.gestionfraudes.vue;

import fr.eseo.gestionfraudes.graphe.GrapheFraudeurs;
import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Epreuve;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;
import fr.eseo.gestionfraudes.modele.fraude.FraudeCalculatrice;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAG;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAGConnectee;
import fr.eseo.gestionfraudes.modele.fraude.FraudePapier;
import fr.eseo.gestionfraudes.service.ExportCSV;
import fr.eseo.gestionfraudes.service.GestionFraudes;
import fr.eseo.gestionfraudes.service.Statistiques;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private GestionFraudes gestion;
    private Scanner scanner;

    public Menu(GestionFraudes gestion) {
        this.gestion = gestion;
        this.scanner = new Scanner(System.in);
    }

    public void demarrer() {
        boolean continuer = true;
        while (continuer) {
            afficherMenu();
            int choix = lireEntier("Votre choix : ");
            switch (choix) {
                case 1: listerFormulaires(); break;
                case 2: ajouterFormulaire(); break;
                case 3: retirerFormulaire(); break;
                case 4: rechercherParEtudiant(); break;
                case 5: rechercherParEpreuve(); break;
                case 6: rechercherParNom(); break;
                case 7: afficherStatistiques(); break;
                case 8: afficherGraphe(); break;
                case 9: exporterCsv(); break;
                case 0: continuer = false; System.out.println("Au revoir."); break;
                default: System.out.println("Choix invalide. Reessayez.");
            }
        }
    }

    private void afficherMenu() {
        System.out.println("\n=== Gestion des fraudes ESEO ===");
        System.out.println("1. Lister les formulaires");
        System.out.println("2. Ajouter un formulaire");
        System.out.println("3. Retirer un formulaire");
        System.out.println("4. Rechercher par etudiant (numero)");
        System.out.println("5. Rechercher par epreuve (code ECUE)");
        System.out.println("6. Rechercher un etudiant par nom");
        System.out.println("7. Afficher les statistiques");
        System.out.println("8. Afficher le graphe des fraudeurs");
        System.out.println("9. Exporter les statistiques en CSV");
        System.out.println("0. Quitter");
    }

    private void listerFormulaires() {
        if (gestion.getFormulaires().isEmpty()) {
            System.out.println("Aucun formulaire enregistre.");
            return;
        }
        for (Formulaire f : gestion.getFormulaires()) {
            System.out.println(f);
        }
    }

    private void ajouterFormulaire() {
        Formulaire formulaire = new Formulaire();

        System.out.print("Code ECUE de l'epreuve : ");
        String code = scanner.nextLine();
        formulaire.setEpreuve(new Epreuve(code, null, null, null));

        boolean ajouterEtudiant = true;
        while (ajouterEtudiant) {
            System.out.print("Numero apprenant : ");
            String num = scanner.nextLine();
            System.out.print("Nom : ");
            String nom = scanner.nextLine();
            System.out.print("Prenom : ");
            String prenom = scanner.nextLine();
            Cursus cursus = lireCursus();
            formulaire.ajouterEtudiant(new Etudiant(num, nom, prenom, cursus));

            System.out.print("Ajouter un autre etudiant ? (o/n) : ");
            ajouterEtudiant = scanner.nextLine().equalsIgnoreCase("o");
        }

        boolean ajouterFraude = true;
        while (ajouterFraude) {
            ajouterUneFraude(formulaire);
            System.out.print("Ajouter une autre fraude ? (o/n) : ");
            ajouterFraude = scanner.nextLine().equalsIgnoreCase("o");
        }

        gestion.ajouterFormulaire(formulaire);
        System.out.println("Formulaire ajoute (id " + formulaire.getIdentifiant() + ").");
    }

    private void ajouterUneFraude(Formulaire formulaire) {
        System.out.println("Type de fraude : 1=Papier 2=Calculatrice 3=IAG 4=IAG connectee");
        int type = lireEntier("Votre choix : ");
        System.out.print("Description : ");
        String description = scanner.nextLine();
        System.out.print("Contenu : ");
        String contenu = scanner.nextLine();

        if (type == 1) {
            System.out.print("Dimensions : ");
            String dimensions = scanner.nextLine();
            System.out.print("Plie ? (o/n) : ");
            boolean plie = scanner.nextLine().equalsIgnoreCase("o");
            formulaire.ajouterFraude(new FraudePapier(LocalDate.now(), description, contenu, dimensions, plie));
        } else if (type == 2) {
            System.out.print("Marque : ");
            String marque = scanner.nextLine();
            System.out.print("Programme : ");
            String programme = scanner.nextLine();
            formulaire.ajouterFraude(new FraudeCalculatrice(LocalDate.now(), description, contenu, marque, programme));
        } else if (type == 3) {
            System.out.print("Nom du service IA : ");
            String service = scanner.nextLine();
            formulaire.ajouterFraude(new FraudeIAG(LocalDate.now(), description, contenu, service));
        } else if (type == 4) {
            System.out.print("Nom du service IA : ");
            String service = scanner.nextLine();
            System.out.print("Adresse IP : ");
            String ip = scanner.nextLine();
            formulaire.ajouterFraude(new FraudeIAGConnectee(LocalDate.now(), description, contenu, service, ip));
        } else {
            System.out.println("Type inconnu, fraude ignoree.");
        }
    }

    private void retirerFormulaire() {
        listerFormulaires();
        int id = lireEntier("Identifiant du formulaire a retirer : ");
        if (gestion.retirerFormulaire(id)) {
            System.out.println("Formulaire retire.");
        } else {
            System.out.println("Identifiant inconnu.");
        }
    }

    private void rechercherParEtudiant() {
        System.out.print("Numero apprenant : ");
        String num = scanner.nextLine();
        Etudiant etudiant = gestion.trouverEtudiantParNumero(num);
        if (etudiant == null) {
            System.out.println("Aucun etudiant avec ce numero.");
            return;
        }
        List<Formulaire> trouves = gestion.rechercherFormulairesParEtudiant(etudiant);
        afficherFormulaires(trouves);
    }

    private void rechercherParEpreuve() {
        System.out.print("Code ECUE : ");
        String code = scanner.nextLine();
        afficherFormulaires(gestion.rechercherFormulairesParEpreuve(code));
    }

    private void rechercherParNom() {
        System.out.print("Nom recherche : ");
        String nom = scanner.nextLine();
        List<Etudiant> trouves = gestion.rechercherEtudiantsParNom(nom);
        if (trouves.isEmpty()) {
            System.out.println("Aucun etudiant trouve.");
        } else {
            for (Etudiant e : trouves) {
                System.out.println(e);
            }
        }
    }

    private void afficherStatistiques() {
        Statistiques stats = new Statistiques(gestion.getFormulaires());
        System.out.println(stats);
    }

    private void afficherGraphe() {
        GrapheFraudeurs graphe = new GrapheFraudeurs();
        graphe.construire(gestion.getFormulaires());
        System.out.println(graphe.afficherTextuel());
    }

    private void exporterCsv() {
        Statistiques stats = new Statistiques(gestion.getFormulaires());
        if (ExportCSV.exporterStatistiques(stats, "statistiques.csv")) {
            System.out.println("Statistiques exportees dans statistiques.csv");
        } else {
            System.out.println("Echec de l'export CSV.");
        }
    }

    private void afficherFormulaires(List<Formulaire> formulaires) {
        if (formulaires.isEmpty()) {
            System.out.println("Aucun formulaire trouve.");
        } else {
            for (Formulaire f : formulaires) {
                System.out.println(f);
            }
        }
    }

    private int lireEntier(String invite) {
        System.out.print(invite);
        String ligne = scanner.nextLine();
        try {
            return Integer.parseInt(ligne.trim());
        } catch (NumberFormatException e) {
            System.out.println("Veuillez saisir un nombre.");
            return -1;
        }
    }

    private Cursus lireCursus() {
        System.out.print("Cursus (E1, E2, E3E, E3A, E4, E5) : ");
        String ligne = scanner.nextLine().trim().toUpperCase();
        try {
            return Cursus.valueOf(ligne);
        } catch (IllegalArgumentException e) {
            System.out.println("Cursus invalide, E1 par defaut.");
            return Cursus.E1;
        }
    }
}
