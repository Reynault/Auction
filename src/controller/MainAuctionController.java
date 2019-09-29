package controller;

import model.Auction;

public class MainAuctionController {
    private Auction model;

    public MainAuctionController(Auction model) {
        this.model = model;
    }

    public void stop(){
        model.stop();
    }

    public void start(){
        model.start();
    }

    public boolean validateBid(String val){
        // Check if value is integer
        try {
            int bid = Integer.parseInt(val);
            model.overBid(bid);
            return true;
        }catch(NumberFormatException ex){
            return false;
        }
    }

}
