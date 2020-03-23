package com.trackodds.trackodds.resource;

import com.trackodds.trackodds.models.jsonobject.marketcatalogue.Market;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

public class TrackOdds implements Callable<String> {

    private final String marketIdToTrack;
    private final long selectionIdToTrack;
    private final String priceToTrack;
    GetMatchInfo getMatchInfo;

    public TrackOdds(String marketIdToTrack, String priceToTrack, long selectionIdToTrack) {
        this.marketIdToTrack = marketIdToTrack;
        this.selectionIdToTrack = selectionIdToTrack;
        this.priceToTrack = priceToTrack;
    }


    @Override
    public String call() throws Exception {
        System.out.println("Tracking odds for market " + marketIdToTrack + " at price " + priceToTrack);
        List<Market> checkPriceList = getMatchInfo.getPricesForMarkets(marketIdToTrack);
        double priceDouble = Double.parseDouble(priceToTrack);
        double newPriceDouble = 0;
        if(checkPriceList.get(0).getStatus().equals("CLOSED")){
            return "CLOSED";
        } else {
            System.out.println("getting new price");
            String newPrice = checkPriceList.get(0).getRunners().stream()
                    .filter(runner -> runner.getSelectionId() == selectionIdToTrack)
                    .map(e -> e.getEx().getAvailableToLay().get(0).getPrice())
                    .findFirst()
                    .map(Objects::toString)
                    .orElse("");
            System.out.println("new price is " + newPrice);
            if(!newPrice.isEmpty()) newPriceDouble = Double.parseDouble(newPrice);
        }
        boolean oddsJump = priceDouble - newPriceDouble > 0.2;
        return oddsJump ? "JUMP" : "cONTINUE" ;
    }
}
