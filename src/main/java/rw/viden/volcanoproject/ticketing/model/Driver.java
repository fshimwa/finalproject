package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Entity
public class Driver {
    private Integer id;
    private String name;
    private String drivingLincence;
    private Bus bus;
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
    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getDrivingLincence() {
        return drivingLincence;
    }

    public void setDrivingLincence(String drivingLincence) {
        this.drivingLincence = drivingLincence;
    }
    @ManyToOne (cascade = CascadeType.ALL, targetEntity = Bus.class)
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }

    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Users.class)
    public Users getSaveBy() {
        return saveBy;
    }

    public void setSaveBy(Users saveBy) {
        this.saveBy = saveBy;
    }
    public Date getSavedDate() {
        return savedDate;
    }
    @Column
    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
    @Column
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }
}
