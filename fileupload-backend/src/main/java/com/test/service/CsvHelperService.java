package com.test.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import com.test.dto.Response;

@Service
public class CsvHelperService {

	public static List<Response> csvToTutorials(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<Response> data = new ArrayList<Response>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			int count = 0;
			for (CSVRecord csvRecord : csvRecords) {

				if (count != 0) {
					Response tutorial = new Response(Float.parseFloat(csvRecord.get("x")),
							Float.parseFloat(csvRecord.get("y")));

					data.add(tutorial);
				}else {
					count++;
				}
			}

			return data;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}
