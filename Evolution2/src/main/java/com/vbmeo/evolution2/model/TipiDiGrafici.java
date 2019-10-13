package com.vbmeo.evolution2.model;

public class TipiDiGrafici {

	
	public static enum Tipo {
		//macro
		Calorie("Calorie"),
		Carbo("Carbo"),
		Alcool_e_grassi_eccedenti("Alcool_e_grassi_eccedenti"),
		Alcool_e_grassi("Alcool_e_grassi"),
		Grassi("Grassi"),
		Alcool("Alcool"),
		Proteine("Proteine"),
		//misure
		Peso("Peso"),
		Ombelico("Ombelico"),
		Parte_pi�_sporgente_addome("Parte_pi�_sporgente_addome"),
		Torace_altezza_capezzoli("Torace_altezza_capezzoli"),
		Torace_altezza_massima("Torace_altezza_massima"),
		Bracio_SX_steso("Bracio_SX_steso"),
		Bracio_DX_steso("Bracio_DX_steso"),
		Bracio_SX_in_tiro("Bracio_SX_in_tiro"),
		Bracio_DX_in_tiro("Bracio_DX_in_tiro"),
		Plico_SX_ombelico("Plico_SX_ombelico"),
		Plico_DX_ombelico("Plico_DX_ombelico"),
		Plico_medio_SX("Plico_medio_SX"),
		Plico_medio_DX("Plico_medio_DX"),
		Plico_fianco_SX("Plico_fianco_SX"),
		Plico_fianco_DX("Plico_fianco_DX"),
		Peso_mattina_a_vuoto("Peso_mattina_a_vuoto"),
		Ombelico_mattina_a_vuoto("Ombelico_mattina_a_vuoto"),
		//attivit�
		Tutte_le_attivit�_in_calorie("Tutte_le_attivit�_in_calorie"),
		Tutte_le_attivit�_in_ore("Tutte_le_attivit�_in_ore"),
		Attivit�_non_airobiche_ore("Attivit�_non_airobiche_ore"),
		Attivit�_non_airobiche_calorie_bruciate("Attivit�_non_airobiche_calorie_bruciate"),
		Attivit�_aerobiche_ore("Attivit�_aerobiche_ore"),
		Attivit�_aerobiche_calorie("Attivit�_aerobiche_calorie"),
		Attivit�_a_stomaco_vuoto_ore("Attivit�_a_stomaco_vuoto_ore"),
		Attivit�_a_stomaco_vuoto_calorie("Attivit�_a_stomaco_vuoto_calorie"),
		Attivit�_a_stomaco_pieno_ore("Attivit�_a_stomaco_pieno_ore"),
		Attivit�_a_stomaco_pieno_calorie("Attivit�_a_stomaco_pieno_calorie");

	    private final String tipoString;

	    /**
	     * @param text
	     */
	    Tipo(final String text) {
	        this.tipoString = text;
	    }

	    /* (non-Javadoc)
	     * @see java.lang.Enum#toString()
	     */
	    @Override
	    public String toString() {
	        return tipoString;
	    }
	    
	}
	
	
	
}
