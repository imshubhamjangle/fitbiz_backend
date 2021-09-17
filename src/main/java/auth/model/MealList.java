package auth.model;

import javax.persistence.*;

@Entity(name="meal_list")
public class MealList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_id")
    private Integer mealId;

    @Column(name="meal_name")
    private String mealName;

    @Column(name="calories")
    private String calories;

    @Column(name="carbs")
    private String carbs;

    @Column(name = "fats")
    private String fats;

    @Column(name = "proteins")
    private String proteins;

    public MealList() {}

    public MealList(Integer mealId, String mealName, String calories, String carbs, String fats, String proteins) {
        this.mealId = mealId;
        this.mealName = mealName;
        this.calories = calories;
        this.carbs = carbs;
        this.fats = fats;
        this.proteins = proteins;
    }

    public Integer getMealId() {
        return mealId;
    }

    public void setMealId(Integer mealId) {
        this.mealId = mealId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getCarbs() {
        return carbs;
    }

    public void setCarbs(String carbs) {
        this.carbs = carbs;
    }

    public String getFats() {
        return fats;
    }

    public void setFats(String fats) {
        this.fats = fats;
    }

    public String getProteins() {
        return proteins;
    }

    public void setProteins(String proteins) {
        this.proteins = proteins;
    }
}
