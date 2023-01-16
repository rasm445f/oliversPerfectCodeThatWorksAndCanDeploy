package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class OwnerDTO {
    private int id;
    private String ownerName;
    private String address;
    private int phoneNumber;
    private List<Boat> boats;

    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.ownerName = owner.getName();
        this.address = owner.getAddress();
        this.phoneNumber = owner.getPhone();
        this.boats = owner.getBoats();
    }

    public static List<OwnerDTO> getOwnerDTOs(List<Owner> owners) {
        List<OwnerDTO> OwnerDTOList = new ArrayList<>();
        owners.forEach(owner -> {
            OwnerDTOList.add(new OwnerDTO(owner));
        });
        return OwnerDTOList;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Boat> getBoats() {
        return (List<Boat>) boats;
    }

    public void setBoats(List<Boat> boats) {
        this.boats = boats;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OwnerDTO)) return false;
        OwnerDTO ownerDTO = (OwnerDTO) o;
        return getId() == ownerDTO.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "OwnerDTO{" +
                "id=" + id +
                ", ownerName='" + ownerName + '\'' +
                ", address='" + address + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", boats=" + boats +
                '}';
    }
}
