package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Item;
import com.example.raulllaverincon.ludoteca.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> findAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findItemById(Long id) {
        return itemRepository.findById(id);
    }

    public Item saveItem(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> updateItem(Long id, Item itemDetails) {
        return itemRepository.findById(id).map(item -> {
            item.setTitle(itemDetails.getTitle());
            item.setType(itemDetails.getType());
            item.setBorrowedDate(itemDetails.getBorrowedDate());
            return itemRepository.save(item);
        });
    }

    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }

    public Item findById(Long id) {
        return null;
    }

    public List<Item> findOverdueItems() {
        return null;
    }
}