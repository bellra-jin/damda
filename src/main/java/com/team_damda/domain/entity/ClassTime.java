package com.team_damda.domain.entity;

import com.team_damda.domain.dto.ClassTimeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="class_time")
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class ClassTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="class_start_at")
    private String classStartsAt;

    @Column(name="class_end_at")
    private String classEndsAt;

    @Column(name="headcount")
    private int headcount;

    @Column(name="class_date")
    private Date classDate;

    @ManyToOne
    @JoinColumn(name="class_id")
    private Class onedayClass;

    @OneToMany(mappedBy = "classTime")
    private List<Cart> cartList = new ArrayList<>();

    public ClassTimeDto toDto() {
        return ClassTimeDto.builder()
                .id(this.id)
                .classStartsAt(this.classStartsAt)
                .classEndsAt(this.classEndsAt)
                .headcount(this.headcount)
                .classDate(this.classDate)
                .className(this.onedayClass.getClassName())
                .build();
    }

}
