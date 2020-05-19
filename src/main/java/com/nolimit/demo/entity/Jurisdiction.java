package com.nolimit.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jurisdiction")
public class Jurisdiction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="jurisdiction_id")
    private Long id;

    @NotNull
    @Column(name="jurisdiction_code")
    private String code;

    @Column(name="jurisdiction_name")
    private String name;

    @OneToMany(mappedBy = "jurisdiction",cascade = CascadeType.ALL)
    private List<JurisdictionConfig> configurations = new ArrayList<>();

    public Jurisdiction() {
    }

    public Jurisdiction(@NotNull String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<JurisdictionConfig> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<JurisdictionConfig> configurations) {
        this.configurations = configurations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Jurisdiction that = (Jurisdiction) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
