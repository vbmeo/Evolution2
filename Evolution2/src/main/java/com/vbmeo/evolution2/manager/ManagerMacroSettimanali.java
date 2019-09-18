package com.vbmeo.evolution2.manager;

import java.sql.Date;

import com.vbmeo.evolution2.model.MacroSettimanali;



public class ManagerMacroSettimanali {

	
	
	//**************************************************************************************
		//**************************************************************************************
		//								CALCOLI
		//**************************************************************************************
		//**************************************************************************************
		
	
	/**
	 * Usato per controllare che i dati immessi corrispondano al totale calorie nel caso in cui alcool venga inserito
	 * @param macro
	 * @return
	 */
		public static boolean verificaVeridicitaDatiCalorieConAlcool(MacroSettimanali macro){
			Integer apportoDiTutti = calcolaApportoTotaleConAlcool(macro);
			Integer resto = (macro.getCalorie_sett()%apportoDiTutti);
			if (resto<5)
				return true;
			else
				return false;
		}
	
	

		public static Integer calcolaApportoCaloricoAlcoolSettimanale(MacroSettimanali macro){
			if (macro.getCalorie_sett()!=0)
				if (macro.getCarboidrati_sett()!=0)
					if (macro.getProteine_sett()!=0)
						if (macro.getGrassi_sett()!=0){
							Integer appostoDiTuttiTranneAlcool = calcolaApportoSenzaAlcool(macro);
							return macro.getCalorie_sett() - appostoDiTuttiTranneAlcool;
						}
			return 0;	
		}
		
		public static Integer calcolaApportoGrammiAlcoolSettimanale(MacroSettimanali macro){
			Integer apporto = calcolaApportoCaloricoAlcoolSettimanale(macro);
			if (apporto!=null)
				return apporto/7;
			return 0;
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
		private static Integer calcolaApportoAlcool(Integer alcool){
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
		
		/**
		 * ritorna calorie totali
		 * @param macro
		 * @return
		 */
		public static Integer calcolaApportoTotaleConAlcool(MacroSettimanali macro){
			return calcolaApportoGrassi(macro.getGrassi_sett()) +
					calcolaApportoProteine(macro.getProteine_sett())+
					 calcolaApportoCarboidrati(macro.getCarboidrati_sett())+
					  calcolaApportoAlcool(macro.getAlcool_sett())
					 ;
		}




		
		
}
