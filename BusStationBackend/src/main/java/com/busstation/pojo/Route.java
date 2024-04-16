package com.busstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "bus_station_route", schema = "bus-station-db", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Route {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "gg_from_location", nullable = false, length = -1)
    private String ggFromLocation;
    @Basic
    @Column(name = "gg_to_location", nullable = false, length = -1)
    private String ggToLocation;
    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Basic
    @Column(name = "from_location", nullable = false, length = 255)
    private String fromLocation;
    @Basic
    @Column(name = "to_location", nullable = false, length = 255)
    private String toLocation;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private TransportationCompany company;
    @OneToMany(mappedBy = "route")
    private Collection<Trip> trips;


}
