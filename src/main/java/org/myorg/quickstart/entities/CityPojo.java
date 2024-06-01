package org.myorg.quickstart.entities;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.math.BigDecimal;

//Has to match the exact order of columns in the CSV file
@JsonPropertyOrder({"city","lat","lng","country","iso2",
        "adminName","capital","population"})
public class CityPojo {
    public String city;
    public BigDecimal lat;
    public BigDecimal lng;
    public String country;
    public String iso2;
    public String adminName;
    public String capital;
    public long population;
};
