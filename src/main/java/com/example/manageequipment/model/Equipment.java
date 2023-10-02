package com.example.manageequipment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "id")
    private User owner = null;

    @ManyToMany( fetch = FetchType.LAZY)
    @JoinTable(name = "transferHistory",
            joinColumns = @JoinColumn(name = "equipment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnore
    private Set<User> transferredUser = new HashSet<>();

    private String imageUrl;

    @Override
    public String toString() {
        return "Equipment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                '}';
    }
}
