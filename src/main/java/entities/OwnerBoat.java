package entities;

import javax.persistence.*;

@Entity
@Table(name = "Owner_Boat")
public class OwnerBoat {
    @EmbeddedId
    private OwnerBoatId id;

    @MapsId("idOwner")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idOwner", nullable = false)
    private Owner idOwner;

    @MapsId("idBoat")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "idBoat", nullable = false)
    private Boat idBoat;

    public OwnerBoatId getId() {
        return id;
    }

    public void setId(OwnerBoatId id) {
        this.id = id;
    }

    public Owner getIdOwner() {
        return idOwner;
    }

    public void setIdOwner(Owner idOwner) {
        this.idOwner = idOwner;
    }

    public Boat getIdBoat() {
        return idBoat;
    }

    public void setIdBoat(Boat idBoat) {
        this.idBoat = idBoat;
    }

}