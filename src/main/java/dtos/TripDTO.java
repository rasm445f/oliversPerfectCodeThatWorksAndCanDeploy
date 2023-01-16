package dtos;

import entities.Guide;
import entities.Traveller;
import entities.Trip;
import entities.User;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Trip} entity
 */
public class TripDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String date;
    @Size(max = 45)
    private final String time;
    @Size(max = 45)
    private final String location;
    @Size(max = 45)
    private final String duration;
    @Size(max = 45)
    private final String packingList;
    private final Guide fkidGuide;
    private List<Traveller> travellers = new ArrayList<>();

    public TripDTO(Integer id, String date, String time, String location, String duration, String packingList, Guide fkidGuide, List<Traveller> travellers) {
        this.id = id;
        this.date = date;
        this.time = time;
        this.location = location;
        this.duration = duration;
        this.packingList = packingList;
        this.fkidGuide = fkidGuide;
        this.travellers = travellers;
    }
    public TripDTO(Trip trip){
        this.id = trip.getId();
        this.date = trip.getDate();
        this.time = trip.getTime();
        this.location = trip.getLocation();
        this.duration = trip.getDuration();
        this.packingList = trip.getPackingList();
        this.fkidGuide = trip.getFkidGuide();
    }

    public static List<TripDTO> getTripDTOs(List<Trip> trips) {
        List<TripDTO> tripDTOList = new ArrayList<>();
        trips.forEach(trip -> {
            tripDTOList.add(new TripDTO(trip));
        });
        return tripDTOList;
    }

    public Integer getId() {
        return id;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }

    public String getDuration() {
        return duration;
    }

    public String getPackingList() {
        return packingList;
    }

    public Guide getFkidGuide() {
        return fkidGuide;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TripDTO entity = (TripDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.date, entity.date) &&
                Objects.equals(this.time, entity.time) &&
                Objects.equals(this.location, entity.location) &&
                Objects.equals(this.duration, entity.duration) &&
                Objects.equals(this.packingList, entity.packingList) &&
                Objects.equals(this.fkidGuide, entity.fkidGuide);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, time, location, duration, packingList, fkidGuide);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "date = " + date + ", " +
                "time = " + time + ", " +
                "location = " + location + ", " +
                "duration = " + duration + ", " +
                "packingList = " + packingList + ", " +
                "fkidGuide = " + fkidGuide + ")";
    }
}