package entities;

import dtos.TravellerDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Traveller {
    @Id
    @Column(name = "idTraveller", nullable = false)
    private Integer id;

    @Size(max = 45)
    @NotNull
    @Column(name = "Phone", nullable = false, length = 45)
    private String phone;

    @Size(max = 45)
    @Column(name = "Email", length = 45)
    private String email;

    @Size(max = 45)
    @Column(name = "Birth_year", length = 45)
    private String birthYear;

    @Size(max = 45)
    @Column(name = "Gender", length = 45)
    private String gender;

    @ManyToMany
    @JoinTable(name = "Traveller_Trip",
            joinColumns = @JoinColumn(name = "fkidTraveller"),
            inverseJoinColumns = @JoinColumn(name = "fkidTrip"))
    private List<Trip> trips = new ArrayList<>();

    @ManyToMany(mappedBy = "travellers", cascade = CascadeType.PERSIST)
    private List<Trip> tripsNEW = new ArrayList<>();

    public List<Trip> getTripsNEW() {
        return tripsNEW;
    }

    public void setTripsNEW(List<Trip> tripsNEW) {
        this.tripsNEW = tripsNEW;
    }

    public Traveller() {
    }

    public Traveller(String phone, String email, String birthYear, String gender, List<Trip> trips) {
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.gender = gender;
        this.tripsNEW = trips;
    }

    /*public Traveller(TravellerDTO travellerDTO){
        this.birthYear = travellerDTO.getBirthYear();
        this.email = travellerDTO.getEmail();
        this.gender = travellerDTO.getGender();
        this.trips = travellerDTO.getTrips();
        this.phone = travellerDTO.getPhone();
    }
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(String birthYear) {
        this.birthYear = birthYear;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Trip> getTrips() {
        return tripsNEW;
    }

    public void setTrips(List<Trip> trips) {
        this.tripsNEW = trips;
    }

}