package kopo.auction.service;

import kopo.auction.domain.Item;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ItemService{
    List<Item> getAllItems();
    Item createItem(Item item);
    boolean deleteItem(Long id);
}
