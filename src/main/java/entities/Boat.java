package entities;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


@Entity
public class Boat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idBoat", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "brand", nullable = false, length = 45)
    private String brand;

    @Size(max = 45)
    @NotNull
    @Column(name = "make", nullable = false, length = 45)
    private String make;

    @Size(max = 45)
    @NotNull
    @Column(name = "name", nullable = false, length = 45)
    private String name;

    @Size(max = 45)
    @NotNull
    @Column(name = "image", nullable = false, length = 45)
    private String image;

    /*@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Owner_Boat",
            joinColumns = @JoinColumn(name = "idBoat"),
            inverseJoinColumns = @JoinColumn(name = "idOwner"))
    private List<Owner> owners = new ArrayList<>();

*/

    @OneToMany(mappedBy = "boatIdboat")
    private List<Harbour> harbours = new ArrayList<>();

    public Boat() {
    }

    public Boat(Integer id, String brand, String make, String name, String image, List<Owner> owners, List<Harbour> harbours) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        //this.owners = owners;
        this.harbours = harbours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    /*public List<Owner> getOwners() {
        return owners;
    }

    public void setOwners(List<Owner> owners) {
        this.owners = owners;
    }
*/
    public List<Harbour> getHarbours() {
        return harbours;
    }

    public void setHarbours(List<Harbour> harbours) {
        this.harbours = harbours;
    }

}