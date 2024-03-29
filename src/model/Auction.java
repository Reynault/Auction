package model;

import manager.ConstantManager;
import model.article.Article;
import model.discounts.Discount;
import model.logger.Logger;

import java.util.Iterator;
import java.util.List;
import java.util.Observable;

public class Auction extends Observable {
    private int lastBid;
    private int idAuction;

    private boolean soldOut;
    private boolean start;

    private Logger logger;
    private List<Discount> discounts;
    private Iterator<Article> iterator;
    private Article currentArticle;

    public Auction(List<Article> articles, Logger logger, List<Discount> discounts) {
        lastBid = 0;
        idAuction = 1;
        start = false;
        this.discounts = discounts;
        iterator = articles.iterator();
        if(iterator.hasNext()){
            currentArticle = iterator.next();
            soldOut = true;
        }else{
            soldOut = false;
        }
        this.logger = logger;
    }

    public void overBid(int value) {
        if (start) {
            if (value > lastBid) {
                lastBid = value;
                addEntry(String.valueOf(getLastBid()));
                notifyOb(ConstantManager.OVERBID);
            }
        }
    }

    public int getLastBid() {
        return lastBid;
    }

    public void start() {
        start = true;
        notifyOb(ConstantManager.START);
    }

    private void addEntry(String message){
        logger.addEntry(this.idAuction, getArticleName(), message, start);
    }

    private void endingCurrentAuction(){
        start = false;
        if(getLastBid() > getArticlePrice()) {
            addEntry(String.valueOf(getLastBid()));
        }else{
            addEntry("auction failed");
        }
        idAuction ++;
        lastBid = 0;
    }

    public void stop() {

        endingCurrentAuction();

        if(iterator.hasNext()){
            currentArticle = iterator.next();
            soldOut = true;
        }else{
            soldOut = false;
        }

        notifyOb(ConstantManager.STOP);
    }

    public void notifyOb(ConstantManager val) {
        this.setChanged();
        this.notifyObservers(val);
    }

    public String getArticleName() {
        if(soldOut){
            return currentArticle.getName() + " : " + currentArticle.getNbComponent() + " pieces";
        }else{
            return "None";
        }
    }

    public int getArticlePrice() {
        if(soldOut){
            return applyDiscounts();
        }else{
            return 0;
        }
    }

    public String getArticleDepot(){
        return currentArticle.getDepot();
    }

    public int applyDiscounts(){
        int price = currentArticle.getPrice();
        for(Discount d : discounts){
            price = d.getPrice(currentArticle, price);
        }
        return price;
    }

    public boolean hasContainer(){
        return currentArticle.hasContainer();
    }

    public void changeToContainer(){
        if(hasContainer()){
            currentArticle = currentArticle.getContainer();
            lastBid = 0;
            addEntry("Change to container");
            notifyOb(ConstantManager.CHANGETOCONTAINER);
        }
    }

    public boolean isSoldOut() {
        return !soldOut;
    }
}
