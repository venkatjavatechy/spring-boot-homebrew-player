package com.venkatjavatechy.homebrewplayer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FormulaResponse {

    @JsonProperty(value = "name")
    private String name;
    @JsonProperty(value = "desc")
    private String description;
    @JsonProperty(value = "versions")
    private Versions version;
    @JsonProperty(value = "dependencies")
    private List<Object> dependencies;
    @JsonProperty(value = "deprecation_date")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private String generatedDate;

}
