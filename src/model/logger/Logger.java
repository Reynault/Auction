package model.logger;

public abstract class Logger{
    public abstract void addEntry(int nbAuction, String name, String bid, boolean open);
}
