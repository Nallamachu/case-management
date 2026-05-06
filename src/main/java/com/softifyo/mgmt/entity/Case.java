package com.softifyo.mgmt.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cases")
public class Case {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true, nullable = false)
    private Integer id;
    @Column(nullable = false, length = 50)
    private String title;
    @Column(nullable = false, length = 500)
    private String description;
    @Column(nullable = false, length = 50)
    private String status;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "user_cases",
            joinColumns = { @JoinColumn(name = "case_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") })
    private Set<User> owner = new HashSet<>();
}
