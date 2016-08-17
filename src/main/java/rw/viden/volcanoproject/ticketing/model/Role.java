package rw.viden.volcanoproject.ticketing.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Viden ltd on 24/05/2016.
 */

public enum Role {
    ADMIN, EMPLOYEE, MANAGER
}
