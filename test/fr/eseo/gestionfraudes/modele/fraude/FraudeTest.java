package fr.eseo.gestionfraudes.modele.fraude;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class FraudeTest {

	private static Collection<Object[]> dtTypes() {
		LocalDate d = LocalDate.of(2026, 1, 15);
		Object[][] data = new Object[][] {
			{new FraudePapier(d, "desc", "contenu", "A5", true), TypeFraude.PAPIER},
			{new FraudeCalculatrice(d, "desc", "contenu", "TI-83", "PROG"), TypeFraude.CALCULATRICE},
			{new FraudeIAG(d, "desc", "contenu", "ChatGPT"), TypeFraude.IAG},
			{new FraudeIAGConnectee(d, "desc", "contenu", "Claude", "10.0.0.1"), TypeFraude.IAG_CONNECTEE},
		};
		return Arrays.asList(data);
	}

	@ParameterizedTest
	@MethodSource("dtTypes")
	public void testGetType(Fraude fraude, TypeFraude typeAttendu) {
		assertEquals(typeAttendu, fraude.getType(), "Le type renvoye n'est pas le bon.");
	}

	@Test
	public void testFraudePapier() {
		FraudePapier papier = new FraudePapier(LocalDate.of(2026, 1, 15), "antiseche", "contenu", "A6", true);
		assertEquals("antiseche", papier.getDescription(), "La description n'est pas la bonne.");
		assertEquals("A6", papier.getDimensions(), "Les dimensions ne sont pas les bonnes.");
		assertTrue(papier.isPlie(), "Le papier devrait etre plie.");
	}

	@Test
	public void testFraudeCalculatrice() {
		FraudeCalculatrice calc = new FraudeCalculatrice(LocalDate.of(2026, 1, 15), "prog interdit", "contenu", "Casio", "ASMPROG");
		assertEquals("Casio", calc.getMarque(), "La marque n'est pas la bonne.");
		assertEquals("ASMPROG", calc.getProgramme(), "Le programme n'est pas le bon.");
		assertEquals("prog interdit", calc.getDescription(), "La description n'est pas la bonne.");
	}

	@Test
	public void testFraudeIagConnecteeHerite() {
		FraudeIAGConnectee iagc = new FraudeIAGConnectee(LocalDate.of(2026, 2, 1), "desc", "contenu", "Gemini", "192.168.1.1");
		assertTrue(iagc instanceof FraudeIAG, "FraudeIAGConnectee devrait heriter de FraudeIAG.");
		assertEquals("Gemini", iagc.getNomService(), "Le nom du service (herite) n'est pas le bon.");
		assertEquals("192.168.1.1", iagc.getAdresseIP(), "L'adresse IP n'est pas la bonne.");
	}
}
