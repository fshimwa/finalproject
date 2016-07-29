package rw.viden.volcanoproject.ticketing.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by Viden ltd on 7/8/2016.
 */
@Entity
public class Ligne {
    private Integer idLigne;
    private String fromDestination;
    private String toDestination;
    private int price;
    private int timeLength;
    private List<Bus> assignedBus;
    private Users saveBy;
    private Date savedDate;
    private boolean voided;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getIdLigne() {
        return idLigne;
    }

    public void setIdLigne(Integer idLigne) {
        this.idLigne = idLigne;
    }
    @Column
    public String getFromDestination() {
        return fromDestination;
    }

    public void setFromDestination(String fromDestination) {
        this.fromDestination = fromDestination;
    }
    @Column
    public String getToDestination() {
        return toDestination;
    }

    public void setToDestination(String toDestination) {
        this.toDestination = toDestination;
    }
    @Column
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    @Column
    public int getTimeLength() {
        return timeLength;
    }
    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    @OneToMany(cascade = CascadeType.ALL, targetEntity = Bus.class)
    public List<Bus> getAssignedBus() {
        return assignedBus;
    }

    public void setAssignedBus(List<Bus> assignedBus) {
        this.assignedBus = assignedBus;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ligne)) return false;

        Ligne ligne = (Ligne) o;

        if (getPrice() != ligne.getPrice()) return false;
        if (getTimeLength() != ligne.getTimeLength()) return false;
        if (isVoided() != ligne.isVoided()) return false;
        if (!getIdLigne().equals(ligne.getIdLigne())) return false;
        if (!getFromDestination().equals(ligne.getFromDestination())) return false;
        return getToDestination().equals(ligne.getToDestination());

    }


}
