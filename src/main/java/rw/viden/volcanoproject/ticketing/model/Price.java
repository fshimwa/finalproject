package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 7/5/2016.
 */
@Entity
public class Price {
    private Integer id;
    private Ligne ligne;
    private int priceAmount;

    private boolean actualAmount;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL, targetEntity = Ligne.class)
    public Ligne getLigne() {
        return ligne;
    }

    public void setLigne(Ligne ligne) {
        this.ligne = ligne;
    }

    public int getPriceAmount() {
        return priceAmount;
    }

    public void setPriceAmount(int priceAmount) {
        this.priceAmount = priceAmount;
    }

    public boolean isActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(boolean actualAmount) {
        this.actualAmount = actualAmount;
    }
}
