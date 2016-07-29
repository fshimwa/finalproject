package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 24/05/2016.
 */
@Entity
public class Role {
    private Integer id;
    private String userRole;
    private Users saveBy;
    private Date savedDate;

    private boolean voided;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Users.class)
    public Users getSaveBy() {
        return saveBy;
    }


    public void setSaveBy(Users saveBy) {
        this.saveBy = saveBy;
    }
    @Column
    public Date getSavedDate() {
        return savedDate;
    }


    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    @Column(unique = false)
    @NotNull
    @NotBlank

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Column
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
}
