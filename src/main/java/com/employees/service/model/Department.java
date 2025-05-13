package com.employees.service.model;

import com.employees.service.model.embedded.AuditModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(indexes = {@Index(name = "idx_department_teamlead", columnList = "team_lead_id")})
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "teamLead")
public class Department extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_lead_id")
    private Employee teamLead;
}
