package model.article;

public class Brut extends Component {

    public Brut(int price, String depot, String name, Article container) {
        super(price, depot, name, container);
    }

    @Override
    public int getNbComponent() {
        return 1;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public String getName() {
        return name;
    }

}
