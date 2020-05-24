package com.nolimit.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "configuration")
public class Configuration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "configuration_id")
    private Long id;

    @Column(name = "config_key")
    private String key;

    @Column(name = "definition")
    private String definition;

    @OneToMany(mappedBy = "configuration", cascade = CascadeType.ALL)
    private List<JurisdictionConfig> jurisdictions = new ArrayList<>();

    public Configuration() {
    }

    public Configuration(String key, String definition) {
        this.key = key;
        this.definition = definition;
    }

    public Long getId() {
        return id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public List<JurisdictionConfig> getJurisdictions() {
        return jurisdictions;
    }

    public void setJurisdictions(List<JurisdictionConfig> jurisdictions) {
        this.jurisdictions = jurisdictions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Configuration that = (Configuration) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
