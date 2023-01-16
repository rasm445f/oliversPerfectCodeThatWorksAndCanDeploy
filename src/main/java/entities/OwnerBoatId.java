package entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OwnerBoatId implements Serializable {
    private static final long serialVersionUID = 1729808012801527834L;
    @NotNull
    @Column(name = "idOwner", nullable = false)
    private Integer idOwner;

    @NotNull
    @Column(name = "idBoat", nullable = false)
    private Integer idBoat;

    public Integer getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Integer idOwner) {
        this.idOwner = idOwner;
    }

    public Integer getIdBoat() {
        return idBoat;
    }

    public void setIdBoat(Integer idBoat) {
        this.idBoat = idBoat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OwnerBoatId entity = (OwnerBoatId) o;
        return Objects.equals(this.idBoat, entity.idBoat) &&
                Objects.equals(this.idOwner, entity.idOwner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idBoat, idOwner);
    }

}