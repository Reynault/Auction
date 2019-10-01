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
                getController().start();
            }
        });

        stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                getController().stop();
            }
        });

        getFrame().pack();
        getFrame().setVisible(true);
    }

    @Override
    public void update(Observable observable, Object o) {
        super.update(observable, o);
        ConstantManager parameter = (ConstantManager) o;
        switch(parameter){
            case OVERBID:
                // if overbid, change current
                getLastBid().setText("Last bid: " + getModel().getLastBid());
                break;
            case STOP:
                stop.setEnabled(false);
                if(getModel().isSoldOut()){
                    start.setEnabled(false);
                }else{
                    start.setEnabled(true);
                }
                break;
            case START:
                stop.setEnabled(true);
                start.setEnabled(false);
                break;
        }
    }

}
