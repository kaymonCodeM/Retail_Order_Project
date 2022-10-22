package retail.orders.MakeMyOrder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import retail.orders.MakeMyOrder.Entity.Item;
import retail.orders.MakeMyOrder.Service.ItemService;

import java.util.List;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/item/all")
    List<Item> getAllItems(){
        return itemService.getItems();
    }
    @PostMapping("/item/add")
    Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }

    @PutMapping("/item/update")
    Item updateItem(@RequestBody Item item){
        return itemService.updateItem(item);
    }

    @GetMapping("/item/{itemId}")
    Item getItemById(@PathVariable String itemId){
        return itemService.getItemById(Long.parseLong(itemId));
    }

    @DeleteMapping("item/{itemId}")
    String deleteItem(@PathVariable String itemId){
        return itemService.removeItemById(Long.parseLong(itemId));
    }



}
