package com.zerotoheroes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Entity
@Table(name = "card_stat", indexes = {
        @Index(columnList = "creationDate", name = "ix_creationDate"),
        @Index(columnList = "userId", name = "ix_userId"),
        @Index(columnList = "userMachineId", name = "ix_userMachineId"),
        @Index(columnList = "userName", name = "ix_userName"),
        @Index(columnList = "setId", name = "ix_setId"),
        @Index(columnList = "cardId", name = "ix_cardId"),
        @Index(columnList = "type", name = "ix_type"),
        @Index(columnList = "rarity", name = "ix_rarity"),
        @Index(columnList = "isNew", name = "ix_isNew"),
})
@AllArgsConstructor()
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CardStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;

    private String userId;
    private String userMachineId;
    private String userName;
    private String setId;
    private String cardId;
    private String type;
    private String rarity;
    private boolean isNew;

    private CardStat() {
        // Needed by JPA
    }
}
