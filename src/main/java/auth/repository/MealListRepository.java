package auth.repository;

import auth.model.MealList;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface MealListRepository extends JpaRepository<MealList,Integer> {
    @Transactional
    void deleteByMealId(Integer id);
}
