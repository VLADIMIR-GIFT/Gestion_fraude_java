package fr.eseo.gestionfraudes;

import fr.eseo.gestionfraudes.service.DonneesTest;
import fr.eseo.gestionfraudes.service.GestionFraudes;
import fr.eseo.gestionfraudes.vue.Menu;

public class Main {

    public static void main(String[] args) {
        GestionFraudes gestion = new GestionFraudes();
        DonneesTest.initialiser(gestion);

        Menu menu = new Menu(gestion);
        menu.demarrer();
    }
}
