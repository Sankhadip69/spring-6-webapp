package guru.springframework.spring6restmvc.service;

import guru.springframework.spring6restmvc.dto.BeerCSVRecord;

import java.io.File;
import java.util.List;

public interface BeerCsvService {

    List<BeerCSVRecord> convertCSV(File csvFile);
}
