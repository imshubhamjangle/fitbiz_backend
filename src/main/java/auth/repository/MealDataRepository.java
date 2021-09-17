package auth.repository;

import auth.model.MealData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface MealDataRepository extends JpaRepository<MealData, Integer> {
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM meal_data WHERE UID = :id",nativeQuery = true)
    void deleteByMealDataUid(@Param("id") Integer id);
}
