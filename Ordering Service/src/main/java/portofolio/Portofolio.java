package portofolio;

import order.Order;

import java.util.ArrayList;
import java.util.List;

public class Portofolio {
    String portofolioName;
    List<Order> orders = new ArrayList<>();

    public Portofolio(String portofolioName) {
        this.portofolioName = portofolioName;
    }

    public void addTrade(Order order){
        orders.add(order);
    }

    public String getPortofolioName() {
        return portofolioName;
    }

    public void setPortofolioName(String portofolioName) {
        this.portofolioName = portofolioName;
    }

    @Override
    public String toString() {
        return "Portofolio{" +
                "portofolioName='" + portofolioName + '\'' +
                ", orders=" + orders.toString() +
                '}';
    }
}
