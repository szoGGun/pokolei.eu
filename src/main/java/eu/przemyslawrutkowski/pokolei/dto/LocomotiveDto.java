package eu.przemyslawrutkowski.pokolei.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocomotiveDto {
    private Long locomotiveId;
    private String name;
    private String drivingSpeed;
    private String weight;
    private String pictureLink;
    private int order;
    private String additionalInfo;
}
