package fr.eseo.gestionfraudes.modele.fraude;

import java.time.LocalDate;

public class FraudeIAG extends Fraude {

    private String nomService;

    public FraudeIAG() {
    }

    public FraudeIAG(LocalDate dateReleve, String description, String nomService) {
        super(dateReleve, description);
        this.nomService = nomService;
    }

    @Override
    public TypeFraude getType() {
        return TypeFraude.IAG;
    }

    public String getNomService() {
        return nomService;
    }

    public void setNomService(String nomService) {
        this.nomService = nomService;
    }
}
