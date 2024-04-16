package com.busstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "bus_station_cargo", schema = "bus-station-db", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cargo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @Column(name = "receiver_name", nullable = false, length = 255)
    private String receiverName;
    @Basic
    @Column(name = "receiver_email", nullable = false, length = 254)
    private String receiverEmail;
    @Basic
    @Column(name = "receiver_phone", nullable = false, length = 20)
    private String receiverPhone;
    @Basic
    @Column(name = "receiver_address", nullable = false, length = 255)
    private String receiverAddress;
    @Basic
    @Column(name = "cargo_price", nullable = false, precision = 0)
    private Double cargoPrice;
    @Basic
    @Column(name = "sent_at", nullable = false)
    private Timestamp sentAt;
    @Basic
    @Column(name = "description", nullable = false, length = -1)
    private String description;

    @OneToOne
    @JoinColumn(name = "ticket_id", referencedColumnName = "id", nullable = false)
    private Ticket ticket;


}
