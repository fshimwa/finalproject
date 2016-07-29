package rw.viden.volcanoproject.ticketing.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Viden ltd on 19/05/2016.
 */
@Entity
public class Payment {
    private Integer id;
   private Reservation reservation;
    private int amount=0;
    private Date datePayment;
    private String timePayment;
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
    @OneToOne(cascade = CascadeType.ALL,targetEntity = Reservation.class)
   public Reservation getReservation() {
        return reservation;
   }

    public void setReservation(Reservation reservation) {
       this.reservation = reservation;
   }
    @Column()
    public Date getDatePayment() {
        return datePayment;
    }

    public void setDatePayment(Date datePayment) {
        this.datePayment = datePayment;
    }

    @Column(columnDefinition = "default 0")
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Column()
    public String getTimePayment() {
        return timePayment;
    }

    public void setTimePayment(String timePayment) {
        this.timePayment = timePayment;
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
    @Column
    public void setSavedDate(Date savedDate) {
        this.savedDate = savedDate;
    }

    @Column()
    public boolean isVoided() {
        return voided;
    }

    public void setVoided(boolean voided) {
        this.voided = voided;
    }


}
