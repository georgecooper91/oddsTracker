package com.trackodds.trackodds.repository;

import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Market;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MarketRepo {

    private List<Market> marketRepo;

    public List<Market> getMarketRepo() {
        return marketRepo;
    }

    public void setMarketRepo(List<Market> marketRepo) {
        if(this.marketRepo != null){
            this.marketRepo = marketRepo;
        } else {
            this.marketRepo = new ArrayList<>(marketRepo);
        }
    }
}
