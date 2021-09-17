package auth.controller;

import auth.dto.MealDataDTO;
import auth.model.MealData;
import auth.service.MealDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class MealDataController {

    @Autowired
    private MealDataService mealDataService;

    @GetMapping("/meal-data-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public List<MealData> getAllMealDataByUserIdController(HttpServletRequest req) {
        return mealDataService.getAllMealDataByUserId(req);
    }

    @PostMapping("/meal-data-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void addMealDataItemController(HttpServletRequest req, @RequestBody MealDataDTO mealDataDTO) {
        mealDataService.addMealDataItem(req, mealDataDTO);
    }

    @DeleteMapping("/meal-data-user/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void deleteMealDataItemController(HttpServletRequest req, @PathVariable Integer id) {
        mealDataService.deleteMealDataItem(req, id);
    }

    @PutMapping("/meal-data-user")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_CLIENT')")
    public void updateMealDataItemController(HttpServletRequest req, @RequestBody MealData mealData) {
        mealDataService.updateMealDataItem(req, mealData);
    }
}
