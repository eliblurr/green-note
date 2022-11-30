package org.tlc.microservices.userservice.utils;

import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

final public class Utils {

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
