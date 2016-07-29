package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Entity
@Table(name = "Bus")
public class Bus {
    private Integer idBus;
    private String plaque;
    private int seats;
    private Users saveBy;
    private Date savedDate;
    private boolean voided;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBus")
    public Integer getIdBus() {
        return idBus;
    }

    public void setIdBus(Integer idBus) {
        this.idBus = idBus;
    }
    @Column(unique = true)
    @NotNull
    @NotBlank
    public String getPlaque() {
        return plaque;
    }

    public void setPlaque(String plaque) {
        this.plaque = plaque;
    }
    @Column(name = "seats", nullable = false, length = 100)
    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }


    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

    public void setSeats(int seats) {
        this.seats = seats;
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

    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }
}
