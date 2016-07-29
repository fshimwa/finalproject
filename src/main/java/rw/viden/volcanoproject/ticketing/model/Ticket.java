package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Entity
public class Ticket {
    private Integer idTicket;
    private Reservation reservation;
    private Journey journey;
    private Payment payment;
    private Bus bus;
    private Users saveBy;
    private Date savedDate;
    private boolean voided;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    public Integer getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(Integer idTicket) {
        this.idTicket = idTicket;
    }

    @OneToOne(cascade = CascadeType.ALL,targetEntity = Reservation.class)
    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
    @OneToOne(cascade = CascadeType.ALL,targetEntity = Journey.class)
    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }
    @OneToOne(cascade = CascadeType.ALL,targetEntity = Payment.class)
    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
    @ManyToOne(cascade = CascadeType.ALL, targetEntity = Bus.class)
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
