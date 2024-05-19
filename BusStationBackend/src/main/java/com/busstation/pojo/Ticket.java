package com.busstation.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

@Entity
@Table(name = "bus_station_ticket", schema = "bus-station-db", catalog = "")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Ticket {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Basic
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;
    @Basic
    @NotNull(message = "{ticket.seatPrice.notNull}")
    @Min(value = 0, message = "{ticket.seatPrice.min}")
    @Column(name = "seat_price", nullable = false, precision = 0)
    private Double seatPrice;

    @Basic
    @Column(name = "paid_at")
    private Timestamp paidAt;

    @OneToOne(mappedBy = "ticket")
    private Cargo cargo;
    @OneToOne(mappedBy = "ticket")
    private Review review;
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id", nullable = false)
    private User customer;
    @ManyToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id", nullable = false)
    private PaymentMethod paymentMethod;
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "id", nullable = false)
    private Trip trip;

    @ManyToOne
    @JoinColumn(name = "seat_id", referencedColumnName = "id", nullable = false)
    private Seat seat;

}
