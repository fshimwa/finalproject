package rw.viden.volcanoproject.ticketing.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Entity
public class Journey {
    private Integer id;
    private Ligne ligne;
    private Bus bus;
    private String timeDeparture;
    private Date date;
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
    @OneToOne(cascade = CascadeType.ALL,targetEntity = Ligne.class)
    public Ligne getLigne() {
        return ligne;
    }
    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }



@OneToOne(cascade = CascadeType.ALL,targetEntity = Bus.class)
    public Bus getBus() {
        return bus;
    }

    public void setBus(Bus bus) {
        this.bus = bus;
    }
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    @Column
    public String getTimeDeparture() {
        return timeDeparture;
    }

    public void setTimeDeparture(String timeDeparture) {
        this.timeDeparture = timeDeparture;
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

    @Column
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }

}
