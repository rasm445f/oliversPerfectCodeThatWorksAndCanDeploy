package entities;

import dtos.GuideDTO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
public class Guide {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGuide", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "Gender", length = 45)
    private String gender;

    @Size(max = 45)
    @Column(name = "Birth_year", length = 45)
    private String birthYear;

    @Size(max = 45)
    @Column(name = "Profile", length = 45)
    private String profile;

    @Size(max = 45)
    @Column(name = "Image_Url", length = 45)
    private String imageUrl;

   /* @OneToMany(mappedBy = "fkidGuide")
    private List<Trip> trips = new ArrayList<>();

    */

    public Guide() {
    }

    public Guide(String gender, String birthYear, String profile, String imageUrl) {
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageUrl = imageUrl;
        //this.trips = trips;
    }

    /*
    public Guide(GuideDTO guideDTO){
        this.birthYear = guideDTO.getBirthYear();
        this.gender = guideDTO.getGender();
        this.profile = guideDTO.getProfile();
        this.trips = guideDTO.getTrips();
    }
     */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

   /* public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    */

    @Override
    public String toString() {
        return "Guide{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", profile='" + profile + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Guide)) return false;
        Guide guide = (Guide) o;
        return Objects.equals(getId(), guide.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}