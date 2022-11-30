package org.tlc.microservices.loggingservice.utils;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    private Utils(){};

    public static List<Sort.Order> generateSortOrders(String[] orders){
        // convert to streams
        List<Sort.Order> f_orders = new ArrayList<Sort.Order>();
        if (orders[0].contains(",")){
            for (String str: orders){
                String[] tmp = str.split(",");
                f_orders.add(new Sort.Order(tmp[1] == "desc" ? Sort.Direction.DESC : Sort.Direction.ASC, tmp[0]));
            }
        } else {
            f_orders.add(new Sort.Order(orders[0] == "desc" ? Sort.Direction.DESC : Sort.Direction.ASC, orders[0]));
        }
        return f_orders;
    }
}
