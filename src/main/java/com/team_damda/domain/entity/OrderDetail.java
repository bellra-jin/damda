package com.team_damda.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="order_detail")
@Builder
public class OrderDetail {
    //주문id 기본키
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    //예약날짜
    @Column(name="reservation_date")
    private LocalDateTime reservationDate;

    //결제날짜
    @Column(name="order_date")
    private LocalDateTime orderDate;

    //클래스이름
    @Column(name="class_name")
    private String className;

    //총인원
    @Column(name="total_headcount")
    private long totalHeadcount;

    @Column(name="total_price")
    private long totalPrice;


    //양방향 매핑
    @OneToOne(mappedBy="orderDetail")
    private ClassReservation reservations;

    //예약id-reservation
    @ManyToOne
    @JoinColumn(name="reserve_id")
    private ClassReservation classReservation;

    //유저아이디-member
    @ManyToOne
    @JoinColumn(name="user_id")
    private Member member;
}