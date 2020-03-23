package com.trackodds.trackodds.models.jsonobject.marketcatalogue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
public class Ex {

    @JsonProperty("availableToBack")
    private List<AvailableToBack> availableToBack;

    @JsonProperty("availableToLay")
    private List<AvailableToLay> availableToLay;

    public List<AvailableToBack> getAvailableToBack() {
        return availableToBack;
    }

    public void setAvailableToBack(List<AvailableToBack> availableToBack) {
        this.availableToBack = availableToBack;
    }

    public List<AvailableToLay> getAvailableToLay() {
        return availableToLay;
    }

    public void setAvailableToLay(List<AvailableToLay> availableToLay) {
        this.availableToLay = availableToLay;
    }
}
