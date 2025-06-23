package com.logisticsCompany.entities;
import com.logisticsCompany.entities.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.jetbrains.annotations.NotNull;


@Data
@Entity
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(nullable = false)
    public String description;

    @Column(nullable = false)
    public Integer weight;


    public boolean fragile;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public Status status;

}
