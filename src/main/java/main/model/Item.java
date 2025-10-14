package main.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    //id – UUID, primary key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    //name – String, column not-null, column unique
    @Column(nullable = false,unique = true)
    private String name;
    //type – Enum (ItemType), column not-null
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ItemType type;
    //xpBonusMultiplier – double
    @Column
    private double xpBonusMultiplier;

    //iconUrl – column not-null, column unique
    @Column(nullable = false,unique = true)
    private String iconUrl;
    //createdOn – LocalDateTime, column not-null
    @Column(nullable = false)
    private LocalDateTime createdOn;
    //updatedOn – LocalDateTime, column not-null
    @Column(nullable = false)
    private LocalDateTime updatedOn;

    //createdBy – String, column not-null
    @Column(nullable = false)
    private String createdBy;

    //updatedBy – String, column not-null
    @Column(nullable = false)
    private String updatedBy;
}

