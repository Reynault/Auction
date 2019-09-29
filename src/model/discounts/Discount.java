package model.discounts;

import model.article.Article;

public abstract class Discount {
    public abstract int getPrice(Article a, int price);
}
