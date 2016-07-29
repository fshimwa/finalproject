package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Entity
public class Reservation {
    private Integer id;
    private Customer customer;
    private Date date;
    private String time;
    private Ligne ligne;
    private Users saveBy;
    private Date savedDate;
    private boolean voided;
    private boolean paid = false;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Customer.class)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

   @OneToOne(cascade = CascadeType.ALL,targetEntity = Ligne.class)
    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
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
    @Column
    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

}