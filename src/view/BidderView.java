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
    private JButton changeToContainer;

    public BidderView(String name, Auction model, MainAuctionController controller) {
        super(name, model, controller);
    }

    @Override
    public void buildFrame() {
        super.buildFrame();

        bid = new JTextPane();
        validate = new JButton("Bid");
        changeToContainer = new JButton("Get container");
        
        JPanel contentPane = (JPanel) getFrame().getContentPane();
        contentPane.add(bid);
        contentPane.add(validate);
        contentPane.add(changeToContainer);

        bid.setAlignmentX(Component.CENTER_ALIGNMENT);
        validate.setAlignmentX(Component.CENTER_ALIGNMENT);
        changeToContainer.setAlignmentX(Component.CENTER_ALIGNMENT);

        validate.setEnabled(false);
        changeToContainer.setEnabled(false);

        changeToContainer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getController().changeToContainer();
            }
        });

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
        super.update(observable, o);
        ConstantManager parameter = (ConstantManager) o;
        switch (parameter){
            case OVERBID:
                // if overbid, change current
                getLastBid().setText("Last bid: " + getModel().getLastBid());
                break;
            case START:
                // if start, change bid button state to enabled
                validate.setEnabled(true);
                if(getModel().hasContainer()){
                    changeToContainer.setEnabled(true);
                }
                break;
            case STOP:
                validate.setEnabled(false);
                changeToContainer.setEnabled(false);
                if (getModel().isSoldOut()) {
                    validate.setEnabled(false);
                }
                break;
        }
    }
}
