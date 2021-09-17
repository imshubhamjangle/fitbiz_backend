package auth.controller;

import auth.model.MealList;
import auth.service.MealListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class MealListController {

    @Autowired
    private MealListService mealListService;

    @GetMapping("/meal-list")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<MealList> getAllMealListItemsController() {
        return mealListService.getAllMealListItems();
    }


    @PostMapping("/meal-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addNewMealListItemController(@RequestBody MealList mealItem) {
        mealListService.addNewMealListItem(mealItem);
    }

    @DeleteMapping("/meal-list/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteMealListItemController(@PathVariable Integer id) {
        mealListService.deleteMealListItem(id);
    }

    @PutMapping("/meal-list")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateMealListItemController(@RequestBody MealList mealList) {
        mealListService.updateMealListItem(mealList);
    }

}
