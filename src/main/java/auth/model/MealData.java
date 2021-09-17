package auth.model;

import javax.persistence.*;

@Entity(name="meal_data")
public class MealData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uid")
    private Integer uid;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="date_added")
    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "meal_id", referencedColumnName = "meal_id")
    private MealList mealList;

    public MealData() { }

    public MealData(Integer uid, Integer userId, String date, MealList mealList) {
        this.uid = uid;
        this.userId = userId;
        this.date = date;
        this.mealList = mealList;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public MealList getMealList() {
        return mealList;
    }

    public void setMealList(MealList mealList) {
        this.mealList = mealList;
    }
}
