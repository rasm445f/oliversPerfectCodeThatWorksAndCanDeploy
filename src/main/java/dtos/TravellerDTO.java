package dtos;

import entities.Traveller;
import entities.Trip;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Traveller} entity
 */
public class TravellerDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    @NotNull
    private final String phone;
    @Size(max = 45)
    private final String email;
    @Size(max = 45)
    private final String birthYear;
    @Size(max = 45)
    private final String gender;
    private final List<Trip> trips;

    public TravellerDTO(Integer id, String phone, String email, String birthYear, String gender, List<Trip> trips) {
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.birthYear = birthYear;
        this.gender = gender;
        this.trips = trips;
    }

    public TravellerDTO(Traveller traveller){
        this.id = traveller.getId();
        this.phone = traveller.getPhone();
        this.email = traveller.getEmail();
        this.birthYear = traveller.getBirthYear();
        this.gender = traveller.getGender();
        this.trips = traveller.getTripsNEW();
    }

    public static List<TravellerDTO> getTravellersDTOs(List<Traveller> travellers) {
        List<TravellerDTO> travellerDTOS = new ArrayList<>();
        travellers.forEach(traveller -> {
            travellerDTOS.add(new TravellerDTO(traveller));
        });
        return travellerDTOS;
    }
    public Integer getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getGender() {
        return gender;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TravellerDTO entity = (TravellerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.phone, entity.phone) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.birthYear, entity.birthYear) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.trips, entity.trips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone, email, birthYear, gender, trips);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "phone = " + phone + ", " +
                "email = " + email + ", " +
                "birthYear = " + birthYear + ", " +
                "gender = " + gender + ", " +
                "trips = " + trips + ")";
    }
}