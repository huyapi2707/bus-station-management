package com.busstation.dtos;

import com.busstation.pojo.Station;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RouteDTO {
    private Long id;
    private String name;
    private CompanyPublicDTO company;

    private Double seatPrice;
    private Double cargoPrice;
    private Station fromStation;
    private Station toStation;
}
