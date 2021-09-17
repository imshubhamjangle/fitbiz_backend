package auth.model;

import javax.persistence.*;

@Entity
@Table(name = "Steps_tbl")
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private Integer id;

    @Column(name = "step_description")
    private String description;

    public Step() { }

    public Step(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
