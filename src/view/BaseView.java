package view;

import controller.MainAuctionController;
import model.Auction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.Observer;

public abstract class BaseView implements Observer {

    protected static final int WIDTH = 200;
    protected static final int HEIGHT = 200;

    private JFrame frame;
    private JLabel article = new JLabel();
    private JLabel price = new JLabel();
    private JLabel depot = new JLabel();
    private JLabel lastBid = new JLabel();

    private Auction model;
    private MainAuctionController controller;

    public BaseView(String name, Auction model, MainAuctionController controller){
        frame = new JFrame(name);
        this.controller = controller;
        this.model = model;
        buildFrame();
    }

    public void buildFrame(){
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) getFrame().getContentPane();

        String articleName = model.getArticleName();
        article.setText("Article: "+articleName);
        price.setText("Price: "+model.getArticlePrice());
        depot.setText("Depot: "+model.getArticleDepot());
        lastBid.setText("Last bid: "+model.getLastBid());

        frame.setPreferredSize(new Dimension(WIDTH + (articleName.length() * 5),HEIGHT));

        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        contentPane.add(article);
        article.setBorder(new EmptyBorder(0, 0,10,0));
        contentPane.add(price);
        price.setBorder(new EmptyBorder(0, 0,10,0));
        contentPane.add(lastBid);
        depot.setBorder(new EmptyBorder(0, 0,10,0));
        contentPane.add(depot);
        lastBid.setBorder(new EmptyBorder(0, 0,10,0));
        contentPane.setBorder(new EmptyBorder(10,10,10,10));

        article.setAlignmentX(Component.CENTER_ALIGNMENT);
        price.setAlignmentX(Component.CENTER_ALIGNMENT);
        depot.setAlignmentX(Component.CENTER_ALIGNMENT);
        lastBid.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getArticle() {
        return article;
    }

    public JLabel getPrice() {
        return price;
    }

    public JLabel getLastBid() {
        return lastBid;
    }

    public MainAuctionController getController() {
        return controller;
    }

    public Auction getModel() {
        return model;
    }
}
