package fr.eseo.gestionfraudes.graphe;

import fr.eseo.gestionfraudes.modele.Etudiant;
import fr.eseo.gestionfraudes.modele.Formulaire;

import java.util.ArrayList;
import java.util.List;


public class GrapheFraudeurs {

    private List<Etudiant> sommets;
    private List<List<Etudiant>> voisins;

    public GrapheFraudeurs() {
        this.sommets = new ArrayList<>();
        this.voisins = new ArrayList<>();
    }


    public void construire(List<Formulaire> formulaires) {
        sommets = new ArrayList<>();
        voisins = new ArrayList<>();

        for (Formulaire f : formulaires) {
            List<Etudiant> etudiants = f.getEtudiants();


            for (Etudiant e : etudiants) {
                ajouterSommet(e);
            }


            for (int i = 0; i < etudiants.size(); i++) {
                for (int j = i + 1; j < etudiants.size(); j++) {
                    ajouterArete(etudiants.get(i), etudiants.get(j));
                }
            }
        }
    }


    private void ajouterSommet(Etudiant e) {
        if (!sommets.contains(e)) {
            sommets.add(e);
            voisins.add(new ArrayList<>());
        }
    }


    private void ajouterArete(Etudiant a, Etudiant b) {
        List<Etudiant> voisinsDeA = voisins.get(sommets.indexOf(a));
        List<Etudiant> voisinsDeB = voisins.get(sommets.indexOf(b));

        if (!voisinsDeA.contains(b)) {
            voisinsDeA.add(b);
        }
        if (!voisinsDeB.contains(a)) {
            voisinsDeB.add(a);
        }
    }


    public int nombreSommets() {
        return sommets.size();
    }


    public int nombreAretes() {
        int total = 0;
        for (List<Etudiant> liste : voisins) {
            total += liste.size();
        }
        return total / 2;
    }


    public String afficherTextuel() {
        if (sommets.isEmpty()) {
            return "Graphe vide.";
        }

        String texte = "Graphe des fraudeurs (" + nombreSommets()
                + " sommet(s), " + nombreAretes() + " arete(s)) :\n";

        for (int i = 0; i < sommets.size(); i++) {
            Etudiant e = sommets.get(i);
            List<Etudiant> sesVoisins = voisins.get(i);

            texte = texte + "  " + e.getNumApprenant() + " (" + e.getNom() + ") -> ";

            if (sesVoisins.isEmpty()) {
                texte = texte + "(isole)";
            } else {
                for (int j = 0; j < sesVoisins.size(); j++) {
                    texte = texte + sesVoisins.get(j).getNumApprenant();
                    if (j < sesVoisins.size() - 1) {
                        texte = texte + ", ";
                    }
                }
            }
            texte = texte + "\n";
        }
        return texte;
    }
}
