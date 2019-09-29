package model.article;

public abstract class Component extends Article {

    public Component(int price, String depot, String name, Article container) {
        super(price, depot, name, container);
    }

}
