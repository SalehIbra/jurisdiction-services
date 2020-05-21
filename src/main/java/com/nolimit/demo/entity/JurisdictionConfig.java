package com.nolimit.demo.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "jurisdiction_config")
public class JurisdictionConfig {

    @EmbeddedId
    private JurisdictionConfigId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("jurisdictionId")
    @JoinColumn(name = "jurisdiction_id")
    private Jurisdiction jurisdiction;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("configurationId")
    @JoinColumn(name = "configuration_id")
    private Configuration configuration;

    @NotNull
    @Column(name = "value")
    private String value;

    @Column(name = "comment")
    private String comment;

    public JurisdictionConfig() {
    }


    public JurisdictionConfig(Jurisdiction jurisdiction, Configuration configuration, String value, String comment) {
        this.jurisdiction = jurisdiction;
        this.configuration = configuration;
        this.value = value;
        this.comment = comment;
        this.id = new JurisdictionConfigId(jurisdiction.getId(),configuration.getId());
    }


    public JurisdictionConfigId getId() {
        return id;
    }

    public void setId(JurisdictionConfigId id) {
        this.id = id;
    }

    public Jurisdiction getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Jurisdiction jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JurisdictionConfig that = (JurisdictionConfig) o;

        return Objects.equals(jurisdiction,that.jurisdiction) &&
                Objects.equals(configuration,that.configuration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jurisdiction,configuration);
    }
}
