package com.trackodds.trackodds.resource;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class SetupTracker {

    public void trackOddsServ(String marketIdToTrack, String priceToTrack, long selectionIdToTrack, GetMatchInfo getMatchInfo) throws Exception {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        TrackOdds trackOdds = new TrackOdds(marketIdToTrack, priceToTrack, selectionIdToTrack, getMatchInfo);

        Runnable run = () -> {
            try {
                String result = trackOdds.call();
                if(result.equals("CLOSED")){
                    service.shutdown();
                } else if (result.equals("JUMP")){
                    sendAlert();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        ScheduledFuture<?> future = service
                .scheduleAtFixedRate(run, 30, 30, TimeUnit.SECONDS);
    }

    private void sendAlert() {
        System.out.println("SEND ALERT");
    }
}
