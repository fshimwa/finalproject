package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 18/05/2016.
 */
@Entity
public class Customer {
    private Integer id;
    private String name;
    private String telephone;
   // private int numberVoyage;
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
    @Column(unique = true)
    @NotNull
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {

        this.telephone = telephone;
    }
   // @Column
   // public Integer getNumberVoyage() {
      //  return numberVoyage;
   // }

    //public void setNumberVoyage(int numberVoyage) {
       // this.numberVoyage = numberVoyage;
    //}

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
        if (!(o instanceof Customer)) return false;

        Customer customer = (Customer) o;

        if (isVoided() != customer.isVoided()) return false;
        if (!getId().equals(customer.getId())) return false;
        if (!getName().equals(customer.getName())) return false;
        return getTelephone().equals(customer.getTelephone());

    }


}
