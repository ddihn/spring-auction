package kopo.auction.mapper;

import kopo.auction.domain.Item;

import java.util.List;

public interface ItemMapper {
    List<Item> getAllItems();
    int addItem(Item item);
    int deleteItem(Long id);
}
