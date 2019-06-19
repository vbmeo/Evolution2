package com.vbmeo.evolution2.manager;

import com.vbmeo.evolution2.model.MacroSettimanali;



public class ManagerMacroSettimanali {

	
	
	//**************************************************************************************
		//**************************************************************************************
		//								CALCOLI
		//**************************************************************************************
		//**************************************************************************************
		
		

		public static Integer calcolaApportoCaloricoAlcoolSettimanale(MacroSettimanali macro){
			if (macro.getCalorie_sett()!=0)
				if (macro.getCarboidrati_sett()!=0)
					if (macro.getProteine_sett()!=0)
						if (macro.getGrassi_sett()!=0){
							Integer appostoDiTuttiTranneAlcool = calcolaApportoSenzaAlcool(macro);
							return macro.getCalorie_sett() - appostoDiTuttiTranneAlcool;
						}
			return null;	
		}
		
		
		private static Integer calcolaApportoGrassi(Integer grassi){
			return grassi*9;
		}
		private static Integer calcolaApportoProteine(Integer proteine){
			return proteine*4;
		}
		private static Integer calcolaApportoCarboidrati(Integer carboidrati){
			return carboidrati*4;
		}
		
		/**
		 * fa solo la moltiplicazione dato i grammi
		 * @param alcool
		 * @return
		 */
		private Integer calcolaApportoAlcool(Integer alcool){
			return alcool*7;
		}
		
		/**
		 * ci deve essere già stato un controllo che grassi pro e carbonon siano nulli
		 * @param macro
		 * @return
		 */
		public static Integer calcolaApportoSenzaAlcool(MacroSettimanali macro){
			return calcolaApportoGrassi(macro.getGrassi_sett()) +
					calcolaApportoProteine(macro.getProteine_sett())+
					 calcolaApportoCarboidrati(macro.getCarboidrati_sett());
		}
		
		
		
}
