package model.article;

public abstract class Article {
    protected int price;
    protected String depot;
    protected String name;

    protected Article container;

    public Article(int price, String depot, String name, Article container) {
        this.price = price;
        this.depot = depot;
        this.name = name;
        this.container = container;
    }

    public String getDepot(){
        if(container != null){
            return container.depot;
        }else{
            return depot;
        }
    }

    public abstract int getNbComponent();

    public abstract int getPrice();

    public abstract String getName();

    public boolean hasContainer(){
        return container != null;
    }

    public Article getContainer(){
        return container;
    }
}
