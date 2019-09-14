package com.khabazze.jcpj.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Version
    protected Long version;

    @CreationTimestamp
    protected LocalDateTime createdOn;

    @UpdateTimestamp
    protected LocalDateTime updatedOn;

    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "permission_role", joinColumns = {
            @JoinColumn(name = "role_id", referencedColumnName = "id")
        }, inverseJoinColumns = {
            @JoinColumn(name = "permission_id", referencedColumnName = "id")
        })
    private List<Permission> permissions;

    @EventListener
    public void appReady(ApplicationReadyEvent event) {

    }
}
