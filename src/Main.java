import controller.MainAuctionController;
import model.Auction;
import model.article.Article;
import model.article.Assembly;
import model.article.Brut;
import model.article.Computer;
import model.discounts.Discount;
import model.discounts.NbComponentDiscount;
import model.discounts.WeekendDiscount;
import model.logger.OutputLogger;
import view.AuctionView;
import view.BidderView;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        // Articles

        ArrayList<Article> brut = new ArrayList<>();
        ArrayList<Article> component = new ArrayList<>();

        Computer c1 = new Computer( 50, "Chine", "Macbook trop cher", null, component);

        Assembly motherboard = new Assembly(200, "Beglique", "MotherBoard", c1, brut);

        brut.add(new Brut(80, "Belgique", "SSD 500 Go", motherboard));
        brut.add(new Brut(300, "Allemagne", "Intel 3Ghz", motherboard));

        component.add(motherboard);
        component.add(new Brut(80, "Metz", "HDD 1 To", c1));


        ArrayList<Article> articles = new ArrayList<>();
        articles.add(motherboard);

        // Discounts
        ArrayList<Discount> discounts = new ArrayList<>();
        discounts.add(new WeekendDiscount());
        discounts.add(new NbComponentDiscount());

        Auction auction = new Auction(articles, new OutputLogger(), discounts);
        MainAuctionController controller = new MainAuctionController(auction);

        AuctionView auct = new AuctionView("Auctioneer", auction, controller);
        BidderView bidder = new BidderView("Bidder", auction, controller);
        BidderView bidder2 = new BidderView("Bidder", auction, controller);

        auction.addObserver(auct);
        auction.addObserver(bidder);
        auction.addObserver(bidder2);
    }
}
