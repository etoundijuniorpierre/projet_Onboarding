package com.logisticsCompany.entities;
import com.logisticsCompany.entities.Enum.Status;
import jakarta.persistence.*;
import lombok.Data;



@Data
@Entity
public class PackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column()
    public String description;

    @Column()
    public Integer weight;

    @Column()
    public boolean fragile;

    @Enumerated(EnumType.STRING)
    public Status status;

}
