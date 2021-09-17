package auth.service;

import auth.exception.CustomException;
import auth.model.MealList;
import auth.repository.MealListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MealListService {

    @Autowired
    private MealListRepository mealListRepository;

    public List<MealList> getAllMealListItems() {
        return mealListRepository.findAll();
    }

    public void addNewMealListItem(MealList mealItem) {
        mealListRepository.save(mealItem);
    }

    public void deleteMealListItem(Integer id) {
        try{
            mealListRepository.deleteByMealId(id);
        } catch (Exception e) {
            if (e instanceof EmptyResultDataAccessException) {
                throw new CustomException("No such meal list exist!", HttpStatus.UNPROCESSABLE_ENTITY);
            } else {
                System.out.println(e);
                throw new CustomException("Something went wrong!", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    public void updateMealListItem(MealList mealList) {
        Optional<MealList> os = mealListRepository.findById(mealList.getMealId());
        if(os.isPresent()){
            mealListRepository.save(mealList);
        } else {
            throw new CustomException("No such meal list item exist!", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
