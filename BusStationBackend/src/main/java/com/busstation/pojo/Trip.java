package com.busstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "bus_station_trip", schema = "bus-station-db", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Trip {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "seat_price", nullable = false, precision = 0)
    private Double seatPrice;
    @Basic
    @Column(name = "cargo_price", nullable = false, precision = 0)
    private Double cargoPrice;
    @Basic
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Basic
    @Column(name = "depart_at", nullable = false)
    private Timestamp departAt;


    @OneToMany(mappedBy = "trip")
    private Collection<Ticket> tickets;
    @ManyToOne
    @JoinColumn(name = "car_id", referencedColumnName = "id", nullable = false)
    private Car car;
    @ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "id", nullable = false)
    private Route route;



}
