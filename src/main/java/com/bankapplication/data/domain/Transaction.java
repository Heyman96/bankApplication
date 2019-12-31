//package bankapplication.data.domain;
//
//import lombok.Data;
//import org.springframework.data.annotation.Id;
//
//import javax.persistence.*;
//import java.math.BigDecimal;
//
//@Entity
//@Data
//public class Transaction {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(nullable = false)
//    private BigDecimal moneyAmount;
//
//    @Column(nullable = false)
//    private String currency;
//
//    @OneToMany
//    @JoinColumn(name = "bank_account_id")
//
//}

//TODO: Дописать отдельную сущность Transaction. Добавить нужные поля, которые прийдут в голову.
