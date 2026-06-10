package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

public class FraudeIAGConnectee extends FraudeIAG {

    private String adresseIP;

    public FraudeIAGConnectee() {
    }

    public FraudeIAGConnectee(LocalDate dateReleve, String description, String contenu, String nomService, String adresseIP) {
        super(dateReleve, description, contenu, nomService);
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
