package com.trackodds.trackodds.resource;

import java.util.concurrent.*;

public class SetupTracker {

    public void trackOddsServ(String marketIdToTrack, String priceToTrack, long selectionIdToTrack) throws Exception {

        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        TrackOdds trackOdds = new TrackOdds(marketIdToTrack, priceToTrack, selectionIdToTrack);
        ScheduledFuture<String> future = service
                .scheduleAtFixedRate(trackOdds.call(), 30, 30, TimeUnit.SECONDS);
    }
}
