package org.myorg.quickstart.sources;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.connector.file.src.FileSource;
import org.apache.flink.core.fs.Path;
import org.apache.flink.formats.csv.CsvReaderFormat;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.dataformat.csv.CsvMapper;
import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.apache.flink.util.function.SerializableFunction;
import org.myorg.quickstart.entities.CityPojo;

import java.io.File;
import java.time.Duration;

public class MyCsvSource {
    String path;
    public MyCsvSource(String path) {
        this.path = path;
    }
    SerializableFunction<CsvMapper, CsvSchema> schemaGenerator = mapper ->
            mapper.schemaFor(CityPojo.class).withoutQuoteChar().withColumnSeparator('|');

    CsvReaderFormat<CityPojo> csvFormat =
            CsvReaderFormat.forSchema(CsvMapper::new, schemaGenerator, TypeInformation.of(CityPojo.class));

    public FileSource<CityPojo> get(){
        FileSource<CityPojo> source =
                FileSource.forRecordStreamFormat(csvFormat, Path.fromLocalFile(new File(path)))
                        .monitorContinuously(Duration.ofSeconds(10)).build();
        return source;
    }
}
