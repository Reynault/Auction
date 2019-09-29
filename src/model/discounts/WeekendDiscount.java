package model.discounts;

import model.article.Article;

import java.util.Calendar;

public class WeekendDiscount extends Discount {
    @Override
    public int getPrice(Article a, int price) {
        Calendar calendar = Calendar.getInstance();
        if(calendar.DAY_OF_WEEK == 7 || calendar.DAY_OF_WEEK == 6){
            return (int)(price * 0.9);
        }
        return price;
    }
}
