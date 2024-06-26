package org.myorg.quickstart.joins;

import org.apache.flink.api.common.functions.RichFlatMapFunction;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.util.Collector;
import org.myorg.quickstart.entities.CityPojo;
import org.myorg.quickstart.entities.Enriched;
import org.myorg.quickstart.src.CsvToMapConverter;

import java.util.Map;

public class MyEnrichJoin extends RichFlatMapFunction<CityPojo, Enriched> {
    private Map<String, String> sights;
    final private String path;

    public MyEnrichJoin(String path){
        this.path = path;
    }

    @Override
    public void open(final Configuration parameters) throws Exception {
        super.open(parameters);
        sights = CsvToMapConverter.get(path);
    }

    @Override
    public void flatMap(CityPojo cityPojo, Collector<Enriched> collector) throws Exception {
    String refSight = sights.get(cityPojo.city);
    Enriched elem = new Enriched(cityPojo.city, refSight);
    collector.collect(elem);
    }
}
