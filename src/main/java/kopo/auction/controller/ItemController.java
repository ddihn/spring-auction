package kopo.auction.controller;

import kopo.auction.domain.Item;
import kopo.auction.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> listItems() {
        return itemService.getAllItems();
    }

    @PostMapping
    public ResponseEntity<Item> addItem(@RequestBody Item item) {
        Item created = itemService.createItem(item);
        return ResponseEntity
                .created(URI.create("/api/items/" + created.getId()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id) {
        boolean ok = itemService.deleteItem(id);
        if (!ok) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
