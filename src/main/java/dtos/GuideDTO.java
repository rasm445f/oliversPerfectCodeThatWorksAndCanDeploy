package dtos;

import entities.Guide;
import entities.Traveller;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A DTO for the {@link entities.Guide} entity
 */
public class GuideDTO implements Serializable {
    private final Integer id;
    @Size(max = 45)
    private final String gender;
    @Size(max = 45)
    private final String birthYear;
    @Size(max = 45)
    private final String profile;
    @Size(max = 45)
    private final String imageUrl;

    public GuideDTO(Integer id, String gender, String birthYear, String profile, String imageUrl) {
        this.id = id;
        this.gender = gender;
        this.birthYear = birthYear;
        this.profile = profile;
        this.imageUrl = imageUrl;
    }
    public GuideDTO(Guide guide){
        this.id = guide.getId();
        this.gender = guide.getGender();
        this.birthYear = guide.getBirthYear();
        this.profile = guide.getProfile();
        this.imageUrl = guide.getImageUrl();
    }

    public static List<GuideDTO> getGuideDTOs(List<Guide> guides) {
        List<GuideDTO> guideDTOS = new ArrayList<>();
        guides.forEach(guide -> {
            guideDTOS.add(new GuideDTO(guide));
        });
        return guideDTOS;
    }
    public Integer getId() {
        return id;
    }

    public String getGender() {
        return gender;
    }

    public String getBirthYear() {
        return birthYear;
    }

    public String getProfile() {
        return profile;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuideDTO entity = (GuideDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.gender, entity.gender) &&
                Objects.equals(this.birthYear, entity.birthYear) &&
                Objects.equals(this.profile, entity.profile) &&
                Objects.equals(this.imageUrl, entity.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, gender, birthYear, profile, imageUrl);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "gender = " + gender + ", " +
                "birthYear = " + birthYear + ", " +
                "profile = " + profile + ", " +
                "imageUrl = " + imageUrl + ")";
    }
}