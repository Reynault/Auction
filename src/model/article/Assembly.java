package model.article;

import java.util.Iterator;
import java.util.List;

public class Assembly extends Component {

    private List<Article> composants;

    public Assembly(int price, String depot, String name, Article container, List<Article> composants) {
        super(price, depot, name, container);
        this.composants = composants;
    }

    @Override
    public int getNbComponent() {
        int nbComponent = 1;
        for(Article a: composants){
            nbComponent += a.getNbComponent();
        }
        return nbComponent;
    }

    @Override
    public int getPrice() {
        int price = this.price;
        for(Article a: composants){
            price += a.getPrice();
        }
        return price;
    }

    @Override
    public String getName() {

        StringBuilder name = new StringBuilder();
        name.append(this.name);
        Iterator<Article> i = composants.iterator();

        if (composants.size() > 0) {
            name.append("( ");
        }
        while (i.hasNext()) {
            name.append(i.next().getName());
            if (i.hasNext()) {
                name.append(" ,");
            }
        }
        if (composants.size() > 0) {
            name.append(" )");
        }

        return name.toString();
    }
}
