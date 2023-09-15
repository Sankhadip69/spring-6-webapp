package guru.springframework.spring6restmvc.service.impl;

import com.opencsv.bean.CsvToBeanBuilder;
import guru.springframework.spring6restmvc.dto.BeerCSVRecord;
import guru.springframework.spring6restmvc.service.BeerCsvService;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

@Service
public class BeerCsvServiceImpl implements BeerCsvService {


    @Override
    public List<BeerCSVRecord> convertCSV(File csvFile) {

        try {
            List<BeerCSVRecord> beerCSVRecords = new CsvToBeanBuilder<BeerCSVRecord>(new FileReader(csvFile))
                    .withType(BeerCSVRecord.class)
                    .build().parse();
            return beerCSVRecords;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
