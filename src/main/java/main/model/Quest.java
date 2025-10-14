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
public class Quest {
    //● id – UUID, primary key
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    //● title – String, column not-null, column unique
    @Column(nullable = false,unique = true)
    private String title;
    //● description – String, column not-null
    @Column(nullable = false)
    private String description;

    //● xp – double, column not-null
    @Column(nullable = false)
    private double xp;
    //● bannerUrl – String, column not-null, column unique
    @Column(nullable = false,unique = true)
    private String bannerUrl;
    //● eligibleClass – Enum (PlayerClass), column not-null
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PlayerClass eligibleClass;

    //● rewardItem – Item, column not-null– an item could be associated with multiple quests
    @ManyToOne
    @JoinColumn(name = "reward_item_id", nullable = false)
    private Item rewardItem;

    //● capturer – Player, nullable – a player could capture multiple quests
    @ManyToOne
    @JoinColumn(name = "capturer_id")
    private Player capturer;
    //todo

    //● createdOn – LocalDateTime, column not-null
    @Column(nullable = false)
    private LocalDateTime createdOn;
    //● updatedOn – LocalDateTime, column not-null
    @Column(nullable = false)
    private LocalDateTime updatedOn;
    //● createdBy – String, column not-null
    @Column(nullable = false)
    private String createdBy;
    //● updatedBy – String, column not-null
    @Column(nullable = false)
    private String updatedBy;

}

