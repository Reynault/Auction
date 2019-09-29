package model.discounts;

import model.article.Article;

public class NbComponentDiscount extends Discount{
    @Override
    public int getPrice(Article a, int price) {
        if(a.getNbComponent() > 4){
            return (int)(price * 0.8);
        }else if(a.getNbComponent() > 2){
            return (int)(price * 0.9);
        }
        return price;
    }
}
