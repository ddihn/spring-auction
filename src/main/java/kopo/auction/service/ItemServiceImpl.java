package kopo.auction.service;

import kopo.auction.domain.Item;
import kopo.auction.mapper.ItemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemMapper itemMapper;
    public ItemServiceImpl(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> getAllItems() {
        return itemMapper.getAllItems();
    }

    @Override
    @Transactional
    public Item createItem(Item item) {
        if (!StringUtils.hasText(item.getTitle())) {
            throw new IllegalArgumentException("상품 제목은 필수입니다.");
        }
        if (item.getStartingPrice() == null ||
                item.getStartingPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("시작가는 0보다 커야 합니다.");
        }

        item.setHighestBid(item.getStartingPrice());
        item.setSold(false);

        int rows = itemMapper.addItem(item);
        if (rows != 1) {
            throw new RuntimeException("경매 상품 등록에 실패했습니다.");
        }
        return item;
    }

    @Override
    @Transactional
    public boolean deleteItem(Long id) {
        int deleted = itemMapper.deleteItem(id);
        return deleted == 1;
    }
}
