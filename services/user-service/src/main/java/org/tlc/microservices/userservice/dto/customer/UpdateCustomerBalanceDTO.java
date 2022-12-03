package org.tlc.microservices.userservice.dto.customer;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@RequiredArgsConstructor
public class UpdateCustomerBalanceDTO {
    @NonNull private Double balance;
}
