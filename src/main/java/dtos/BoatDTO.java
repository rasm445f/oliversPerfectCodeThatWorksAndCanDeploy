package dtos;

import entities.Boat;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BoatDTO {
    private int id;
    private String brand;
    private String make;
    private String image;
    private String name;

    public BoatDTO(Boat boat){
        this.id = boat.getId();
        this.brand = boat.getBrand();
        this.make = boat.getMake();
        this.image = boat.getImage();
        this.name = boat.getName();
    }

    public BoatDTO(int id, String brand, String make, String image, String name) {
        this.id = id;
        this.brand = brand;
        this.make = make;
        this.image = image;
        this.name = name;
    }

    public static List<BoatDTO> getBoatDTOs(List<Boat> boats) {
        List<BoatDTO> boatDTOList = new ArrayList<>();
        boats.forEach(boat -> {
            boatDTOList.add(new BoatDTO(boat));
        });
        return boatDTOList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoatDTO)) return false;
        BoatDTO boatDTO = (BoatDTO) o;
        return getId() == boatDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "BoatDTO{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", make='" + make + '\'' +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
