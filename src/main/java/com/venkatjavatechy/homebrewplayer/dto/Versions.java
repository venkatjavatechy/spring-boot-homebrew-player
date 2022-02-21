package com.venkatjavatechy.homebrewplayer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Versions {
    @JsonProperty("stable")
    private String stable;
}
