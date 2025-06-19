package kopo.auction.domain;


import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Bids {

  private Long id;
  private Long itemId;
  private Long userId;
  private BigDecimal bidAmount;
  private LocalDateTime bidTime;

}
