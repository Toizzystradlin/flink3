package org.myorg.quickstart.entities;

import org.apache.flink.shaded.jackson2.com.fasterxml.jackson.annotation.JsonPropertyOrder;

//Has to match the exact order of columns in the CSV file
@JsonPropertyOrder({"city","sight"})
public class Sight {
    public String city;
    public String sight;
};
