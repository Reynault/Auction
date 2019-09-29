package model.logger;

import java.util.Date;

public class OutputLogger extends Logger{
    @Override
    public void addEntry(int nbAuction, String name, String bid, boolean open) {
        // Get current date
        Date date = new Date();
        StringBuilder sb = new StringBuilder();
        sb.append(date.toString());
        sb.append(" Auction "+nbAuction+" for");
        sb.append(" "+name+" - Bid="+bid);
        sb.append(" - Open="+open);
        System.out.println(sb.toString());
    }
}
