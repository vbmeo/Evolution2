package com.vbmeo.evolution2.util.json;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;



public class JsonExportManager {
	private static final String FILE_SEPARATOR = System.getProperty("file.separator");
	
	public static void writeObjects(ObjectMapper mapper, String baseDir, String fileSuffix, Collection<? extends JSonBaseElement> elements) {
		if (elements != null) {
			// logger.info("writeObjects " + fileSuffix + ": (" + elements.size() + "), inizio");
			for (JSonBaseElement element : elements) {
				try {
					String dir = element.getCodiceFiscaleMMG() + "_" + element.getCodiceFiscaleAssistito();
					String filename = element.getCodiceFiscaleMMG() + "_" + element.getCodiceFiscaleAssistito() + "_" + fileSuffix;
					File file = new File(baseDir + FILE_SEPARATOR + dir + FILE_SEPARATOR + filename + ".json");
					Path pathToFile = Paths.get(file.getAbsolutePath());
					Files.createDirectories(pathToFile.getParent());
					// logger.warn("Operazioni in writeObjects su file {} con pathCompleto {}", file.toString(), pathToFile.toString());
					if (element instanceof JSonArrayElement<?>) {
						if (!((JSonArrayElement<?>) element).getElementsList().isEmpty())
							mapper.writeValue(file, ((JSonArrayElement<?>) element).getElementsList());
					} else {
						mapper.writeValue(file, element);
					}

				} catch (JsonGenerationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			// logger.info("writeObjects " + fileSuffix + ": (" + elements.size() + "), fine");
		}
	}
}
