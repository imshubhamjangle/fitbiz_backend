package auth.model;

import javax.persistence.*;

@Entity
@Table(name = "Tags_tbl")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "tag_name")
    private String tagName;

    public Tag() { }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
