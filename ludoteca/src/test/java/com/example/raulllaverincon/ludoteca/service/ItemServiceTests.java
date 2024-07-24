package com.example.raulllaverincon.ludoteca.service;

import com.example.raulllaverincon.ludoteca.model.Item;
import com.example.raulllaverincon.ludoteca.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ItemServiceTests {

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testFindAll() {
        List<Item> items = itemService.findAllItems();
        assertThat(items).isNotEmpty();
    }

    @Test
    public void testFindById() {
        Item item = new Item();
        item.setTitle("Test Item");
        item.setType("Test Type");
        item.setBorrowedDate(LocalDate.now());
        item = itemRepository.save(item);

        Item foundItem = itemService.findById(item.getId());
        assertThat(foundItem).isNotNull();
        assertThat(foundItem.getTitle()).isEqualTo("Test Item");
    }

    @Test
    @Transactional
    @Rollback
    public void testSaveItem() {
        Item item = new Item();
        item.setTitle("New Item");
        item.setType("New Type");
        item.setBorrowedDate(LocalDate.now());

        Item savedItem = itemService.saveItem(item);
        assertThat(savedItem).isNotNull();
        assertThat(savedItem.getId()).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateItem() {
        Item item = new Item();
        item.setTitle("Original Item");
        item.setType("Original Type");
        item.setBorrowedDate(LocalDate.now());
        item = itemRepository.save(item);

        Item itemDetails = new Item();
        itemDetails.setTitle("Updated Item");
        itemDetails.setType("Updated Type");
        itemDetails.setBorrowedDate(LocalDate.now());

        itemService.updateItem(item.getId(), itemDetails);

        Item updatedItem = itemService.findById(item.getId());
        assertThat(updatedItem).isNotNull();
        assertThat(updatedItem.getTitle()).isEqualTo("Updated Item");
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteItem() {
        Item item = new Item();
        item.setTitle("To Be Deleted");
        item.setType("Type");
        item.setBorrowedDate(LocalDate.now());
        item = itemRepository.save(item);

        itemService.deleteItem(item.getId());
        Item deletedItem = itemService.findById(item.getId());
        assertThat(deletedItem).isNull();
    }

    @Test
    public void testFindOverdueItems() {
        Item item = new Item();
        item.setTitle("Overdue Item");
        item.setType("Type");
        item.setBorrowedDate(LocalDate.now().minusDays(20));
        item = itemRepository.save(item);

        List<Item> overdueItems = itemService.findOverdueItems();
        assertThat(overdueItems).contains(item);
    }
}