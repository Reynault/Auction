package model.article;


import java.util.Iterator;
import java.util.List;

public class Computer extends Article {

    private List<Article> partitions;

    public Computer(int price, String depot, String name, Article container, List<Article> partitions) {
        super(price, depot, name, container);
        this.partitions = partitions;
    }

    @Override
    public int getPrice() {
        int price = this.price;
        for (Article part : partitions) {
            price += part.getPrice();
        }
        return price;
    }

    @Override
    public String getName() {
        StringBuilder name = new StringBuilder();
        name.append(this.name);
        Iterator<Article> i = partitions.iterator();

        if (partitions.size() > 0) {
            name.append("( ");
        }
        while (i.hasNext()) {
            name.append(i.next().getName());
            if (i.hasNext()) {
                name.append(" ,");
            }
        }
        if (partitions.size() > 0) {
            name.append(" )");
        }

        return name.toString();
    }

    @Override
    public int getNbComponent() {
        int nbComponent = 1;
        for (Article part : partitions) {
            nbComponent += part.getNbComponent();
        }
        return nbComponent;
    }
}
