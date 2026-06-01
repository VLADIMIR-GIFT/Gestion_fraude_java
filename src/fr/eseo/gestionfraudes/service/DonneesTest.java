package fr.eseo.gestionfraudes.service;

import fr.eseo.gestionfraudes.modele.Cursus;
import fr.eseo.gestionfraudes.modele.Epreuve;
import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;
import fr.eseo.gestionfraudes.modele.Modalite;
import fr.eseo.gestionfraudes.modele.fraude.FraudeCalculatrice;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAG;
import fr.eseo.gestionfraudes.modele.fraude.FraudeIAGConnectee;
import fr.eseo.gestionfraudes.modele.fraude.FraudePapier;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DonneesTest {

    public static void initialiser(GestionFraudes gestion) {
        
        Etudiant alice = new Etudiant("A2024001", "Dupont", "Alice", Cursus.E1);
        Etudiant bob = new Etudiant("A2024002", "Martin", "Bob", Cursus.E3E);
        Etudiant charlie = new Etudiant("A2024003", "Bernard", "Charlie", Cursus.E4);
        Etudiant diana = new Etudiant("A2024004", "Petit", "Diana", Cursus.E5);

        Epreuve java = new Epreuve("INF-JAVA-01",
                LocalDateTime.of(2026, 1, 15, 9, 0), Duration.ofMinutes(120), Modalite.EXAMEN_ECRIT);
        Epreuve reseau = new Epreuve("INF-RES-02",
                LocalDateTime.of(2026, 2, 10, 14, 0), Duration.ofMinutes(90), Modalite.SUR_ORDINATEUR);

        Formulaire f1 = new Formulaire();
        f1.setEpreuve(java);
        f1.ajouterEtudiant(alice);
        f1.ajouterEtudiant(bob);
        f1.ajouterFraude(new FraudePapier(LocalDate.of(2026, 1, 15), "Antiseche cachee", "A6", true));
        f1.ajouterFraude(new FraudeCalculatrice(LocalDate.of(2026, 1, 15), "Programme interdit", "TI-83", "FORMULES"));
        gestion.ajouterFormulaire(f1);

        Formulaire f2 = new Formulaire();
        f2.setEpreuve(reseau);
        f2.ajouterEtudiant(charlie);
        f2.ajouterFraude(new FraudeIAG(LocalDate.of(2026, 2, 10), "Utilisation d'une IA", "ChatGPT"));
        gestion.ajouterFormulaire(f2);

        Formulaire f3 = new Formulaire();
        f3.setEpreuve(reseau);
        f3.ajouterEtudiant(diana);
        f3.ajouterEtudiant(bob);
        f3.ajouterFraude(new FraudeIAGConnectee(LocalDate.of(2026, 2, 10), "IA connectee", "Claude", "192.168.1.50"));
        gestion.ajouterFormulaire(f3);
    }
}
