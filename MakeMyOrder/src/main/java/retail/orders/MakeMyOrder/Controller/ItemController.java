package retail.orders.MakeMyOrder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import retail.orders.MakeMyOrder.Entity.Item;
import retail.orders.MakeMyOrder.Repository.ItemRepository;
import retail.orders.MakeMyOrder.Service.ItemService;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("items/add")
    Item addItem(@RequestBody Item item){
        return itemService.addItem(item);
    }
}
