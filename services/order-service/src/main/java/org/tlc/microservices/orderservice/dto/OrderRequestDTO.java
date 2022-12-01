package org.tlc.microservices.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.tlc.microservices.orderservice.dto.enums.*;

@Component
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class OrderRequestDTO {
   // @Autowired
    private static final ModelMapper modelMapper = new ModelMapper();

    private int clientID;
    private String product;
    private double price;
    private int quantity;
    private int portfolioID;
    private Side side;
    private OrderPosition position;


 @Override
 public String toString() {
  return "OrderRequestDTO{" +
          "clientID=" + clientID +
          ", product='" + product + '\'' +
          ", price=" + price +
          ", quantity=" + quantity +
          ", side=" + side +
          ", portfolioID=" + portfolioID +
          ", position=" + position +
          '}';
 }
}
