package view;

import controller.MainAuctionController;
import manager.ConstantManager;
import model.Auction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class BidderView extends BaseView{
    private JTextPane bid;
    private JButton validate;

    public BidderView(String name, Auction model, MainAuctionController controller) {
        super(name, model, controller);
    }

    @Override
    public void buildFrame() {
        super.buildFrame();

        bid = new JTextPane();
        validate = new JButton("Bid");
        
        JPanel contentPane = (JPanel) getFrame().getContentPane();
        contentPane.add(bid);
        contentPane.add(validate);

        bid.setAlignmentX(Component.CENTER_ALIGNMENT);
        validate.setAlignmentX(Component.CENTER_ALIGNMENT);

        bid.setPreferredSize(new Dimension((int)(WIDTH * 0.8), 10));
        bid.setBorder(new EmptyBorder(0, 0,10,0));
        validate.setPreferredSize(new Dimension((int)(WIDTH * 0.8), 20));
        validate.setEnabled(false);

        validate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(getController().validateBid(bid.getText())){
                    bid.setText("");
                }
            }
        });

        getFrame().pack();
        getFrame().setVisible(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        ConstantManager parameter = (ConstantManager) o;
        switch (parameter){
            case OVERBID:
                // if overbid, change current
                getLastBid().setText("Last bid: " + getModel().getLastBid());
                break;
            case START:
                // if start, change bid button state to enabled
                validate.setEnabled(true);
                break;
            case STOP:
                validate.setEnabled(false);
                if (!getModel().isSoldOut()) {
                    getArticle().setText("Article: " + getModel().getArticleName());
                    getLastBid().setText("Last bid: " + getModel().getLastBid());
                    getPrice().setText("Price: " + getModel().getArticlePrice());
                }else{
                    getArticle().setText("Sold out");
                    getPrice().setText("");
                    getLastBid().setText("");
                    validate.setEnabled(false);
                }
                break;
        }
    }
}
