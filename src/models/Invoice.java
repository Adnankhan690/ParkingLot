package models;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class Invoice {
    private String number;
    private Date exitTime;
    private Ticket ticket;
    private Gate generatedAt;
    private Operator generatedBy;
    private PaymentType paymentType;
}
