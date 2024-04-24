package com.busstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "bus_station_transportationcompany", schema = "bus-station-db", catalog = "")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TransportationCompany {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic

    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @Basic
    @Column(name = "avatar", nullable = false, length = 255)
    private String avatar;
    @Basic
    @Column(name = "phone", nullable = false, length = 50)
    private String phone;
    @Basic
    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified;
    @Basic
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;
    @Basic
    @Column(name = "email", nullable = false, length = 254)
    private String email;
    @Basic
    @Column(name = "is_cargo_transport", nullable = false)
    private Boolean isCargoTransport;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "company")
    private Collection<Review> reviews;
    @OneToMany(mappedBy = "company")
    private Collection<Route> routes;
    @OneToOne
    @JoinColumn(name = "manager_id", referencedColumnName = "id", nullable = false)
    private User manager;


}
