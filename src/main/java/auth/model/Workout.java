package auth.model;

import javax.persistence.*;

@Entity(name="workout_data")
public class Workout {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="uid")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="date")
    private String date;

    @Column(name="workout_name")
    private String workoutName;

    @Column(name="weight1")
    private String weight1;

    @Column(name="weight2")
    private String weight2;

    @Column(name="weight3")
    private String weight3;

    @Column(name="rep1")
    private String rep1;

    @Column(name="rep2")
    private String rep2;

    @Column(name="rep3")
    private String rep3;

    public Workout() { }

    public Workout(Integer id, Integer user_id, String date, String workoutName, String weight1, String weight2, String weight3, String rep1, String rep2, String rep3) {
        this.id = id;
        this.userId = user_id;
        this.date = date;
        this.workoutName = workoutName;
        this.weight1 = weight1;
        this.weight2 = weight2;
        this.weight3 = weight3;
        this.rep1 = rep1;
        this.rep2 = rep2;
        this.rep3 = rep3;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getWeight1() {
        return weight1;
    }

    public void setWeight1(String weight1) {
        this.weight1 = weight1;
    }

    public String getWeight2() {
        return weight2;
    }

    public void setWeight2(String weight2) {
        this.weight2 = weight2;
    }

    public String getWeight3() {
        return weight3;
    }

    public void setWeight3(String weight3) {
        this.weight3 = weight3;
    }

    public String getRep1() {
        return rep1;
    }

    public void setRep1(String rep1) {
        this.rep1 = rep1;
    }

    public String getRep2() {
        return rep2;
    }

    public void setRep2(String rep2) {
        this.rep2 = rep2;
    }

    public String getRep3() {
        return rep3;
    }

    public void setRep3(String rep3) {
        this.rep3 = rep3;
    }
}
