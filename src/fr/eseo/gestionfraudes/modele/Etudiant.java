package fr.eseo.gestionfraudes.modele;


public class Etudiant {

    private String numApprenant;
    private String nom;
    private String prenom;
    private Cursus cursus;

    public Etudiant() {
    }

    public Etudiant(String numApprenant, String nom, String prenom, Cursus cursus) {
        this.numApprenant = numApprenant;
        this.nom = nom;
        this.prenom = prenom;
        this.cursus = cursus;
    }

    public String getNumApprenant() {
        return numApprenant;
    }

    public void setNumApprenant(String numApprenant) {
        this.numApprenant = numApprenant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Cursus getCursus() {
        return cursus;
    }

    public void setCursus(Cursus cursus) {
        this.cursus = cursus;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Etudiant)) {
            return false;
        }
        Etudiant autre = (Etudiant) o;
        return numApprenant != null && numApprenant.equals(autre.numApprenant);
    }

    @Override
    public int hashCode() {
        return numApprenant == null ? 0 : numApprenant.hashCode();
    }

    @Override
    public String toString() {
        return prenom + " " + nom + " (" + numApprenant + ", " + cursus + ")";
    }
}
