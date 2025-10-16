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
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false,unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private PlayerRole role;

    @Column
    @Enumerated(EnumType.STRING)
    private PlayerClass playerClass;

    @Column
    private double xp;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    @Column(nullable = false)
    private LocalDateTime updatedOn;

    public int getLevel(){

        return (int)(this.xp / 10) + 1;
    }

    public double getProgress(){

        return Math.max(1, ((this.xp % 10) / 10.0) * 100);
    }

}

