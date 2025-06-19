package kopo.auction.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class Item {
    private Long id;
    private String title;
    private String description;
    private BigDecimal startingPrice;
    private BigDecimal highestBid;
    private Long highestBidderId;
    private boolean isSold;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
