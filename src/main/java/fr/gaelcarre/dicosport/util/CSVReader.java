package fr.gaelcarre.dicosport.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

public class CSVReader {

	private static Logger logger = LoggerFactory.getLogger(CSVReader.class);

	public static CSVResult read(String filename) {

		logger.debug("Read file " + filename);

		BufferedReader br = null;
		String line = "";

		CSVResult result = new CSVResult();

		File file;
		try {
			file = new ClassPathResource(filename).getFile();
			br = new BufferedReader(new FileReader(file));
			while ((line = br.readLine()) != null) {
				String[] l = line.split(";");
				if (l.length != 2)
					logger.debug(line);
				logger.debug("" + l.length);
				result.put(l[0], l[1]);
			}
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		return result;
	}

}
