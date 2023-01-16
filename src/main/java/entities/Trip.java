package entities;

import dtos.TripDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQuery(name = "Trip.deleteAllRows", query = "DELETE from Trip")
public class Trip {
    @Id()
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTrip", nullable = false)
    private Integer id;

    @Size(max = 45)
    @Column(name = "Date", length = 45)
    private String date;

    @Size(max = 45)
    @Column(name = "Time", length = 45)
    private String time;

    @Size(max = 45)
    @Column(name = "Location", length = 45)
    private String location;

    @Size(max = 45)
    @Column(name = "Duration", length = 45)
    private String duration;

    @Size(max = 45)
    @Column(name = "Packing_list", length = 45)
    private String packingList;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "fkidGuide")
    private Guide fkidGuide;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "Traveller_Trip",
            joinColumns = @JoinColumn(name = "fkidTrip"),
            inverseJoinColumns = @JoinColumn(name = "fkidTraveller"))
    private List<Traveller> travellers = new ArrayList<>();



    public Trip() {
    }

    public Trip(String date, String time, String location, String duration, String packingList, Guide fkidGuide, List<Traveller> travellers) {
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.fkidGuide = fkidGuide;
        this.travellers = travellers;
    }

    /*public Trip(TripDTO tripDTO){
        this.date = tripDTO.getDate();
        this.time = tripDTO.getTime();
        this.location = tripDTO.getLocation();
        this.duration = tripDTO.getDuration();
        this.packingList = tripDTO.getPackingList();
        this.fkidGuide = tripDTO.getFkidGuide();
    }
     */


    public List<Traveller> addTraveller(Traveller traveller){
        this.travellers.add(traveller);
        return travellers;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPackingList() {
        return packingList;
    }

    public void setPackingList(String packingList) {
        this.packingList = packingList;
    }

    public Guide getFkidGuide() {
        return fkidGuide;
    }

    public void setFkidGuide(Guide fkidGuide) {
        this.fkidGuide = fkidGuide;
    }

    public List<Traveller> getTravellers() {
        return travellers;
    }

    public void setTravellers(List<Traveller> travellers) {
        this.travellers = travellers;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", location='" + location + '\'' +
                ", duration='" + duration + '\'' +
                ", packingList='" + packingList + '\'' +
                ", fkidGuide=" + fkidGuide +
                ", travellers=" + travellers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trip)) return false;
        Trip trip = (Trip) o;
        return Objects.equals(getId(), trip.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
