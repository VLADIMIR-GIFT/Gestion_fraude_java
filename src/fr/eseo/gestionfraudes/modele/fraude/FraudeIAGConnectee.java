package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

/**
 * Cas particulier d'une fraude IAG : l'IA etait connectee depuis une adresse IP.
 * Elle herite de FraudeIAG pour reutiliser nomService.
 */
public class FraudeIAGConnectee extends FraudeIAG {

    private String adresseIP;

    public FraudeIAGConnectee() {
    }

    public FraudeIAGConnectee(LocalDate dateReleve, String description, String nomService, String adresseIP) {
        super(dateReleve, description, nomService);
        this.adresseIP = adresseIP;
    }

    @Override
    public TypeFraude getType() {
        return TypeFraude.IAG_CONNECTEE;
    }

    public String getAdresseIP() {
        return adresseIP;
    }

    public void setAdresseIP(String adresseIP) {
        this.adresseIP = adresseIP;
    }
}
