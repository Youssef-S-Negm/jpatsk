package com.youssef.jpatsk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "T_RATING")
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "NUMBER")
    private Double number;

    @ManyToOne
    @JoinColumn(name = "COURSE_ID")
    private Course course;

    public Rating(Long id, Double number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getNumber() {
        return number;
    }

    public void setNumber(Double number) {
        this.number = number;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
