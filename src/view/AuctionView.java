package view;

import controller.MainAuctionController;
import manager.ConstantManager;
import model.Auction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class AuctionView extends BaseView{
    private JButton stop;
    private JButton start;

    public AuctionView(String name, Auction model, MainAuctionController controller) {
        super(name, model, controller);
    }

    @Override
    public void buildFrame() {
        super.buildFrame();

        stop = new JButton("stop");
        start = new JButton("start");

        JPanel contentPane = (JPanel) getFrame().getContentPane();
        stop.setEnabled(false);

        contentPane.add(start);
        contentPane.add(stop);

        start.setAlignmentX(Component.CENTER_ALIGNMENT);
        stop.setAlignmentX(Component.CENTER_ALIGNMENT);

        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stop.setEnabled(true);
                start.setEnabled(false);
                getController().start();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                stop.setEnabled(false);
                start.setEnabled(true);
                getController().stop();
            }
        });

        getFrame().pack();
        getFrame().setVisible(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        ConstantManager parameter = (ConstantManager) o;
        switch(parameter){
            case OVERBID:
                // if overbid, change current
                getLastBid().setText("Last bid: " + getModel().getLastBid());
                break;
            case STOP:
                if(!getModel().isSoldOut()){
                    getArticle().setText("Article: " + getModel().getArticleName());
                    getLastBid().setText("Last bid: " + getModel().getLastBid());
                    getPrice().setText("Price: " + getModel().getArticlePrice());
                }else{
                    getArticle().setText("Sold out");
                    getPrice().setText("");
                    getLastBid().setText("");
                    start.setEnabled(false);
                }
                break;
        }
    }

}
