package com.kasina.automobileapi.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="role_id")
    private Long id;
    @Column(unique = true)
    private String name;
}
