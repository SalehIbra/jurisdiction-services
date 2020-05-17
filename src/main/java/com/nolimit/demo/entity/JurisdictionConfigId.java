package com.nolimit.demo.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JurisdictionConfigId implements Serializable {

    @Column(name = "jurisdiction_id")
    private Long jurisdictionId;

    @Column(name = "configuration_id")
    private Long configurationId;

    public JurisdictionConfigId() {
    }

    public JurisdictionConfigId(Long jurisdictionId, Long configurationId) {
        this.jurisdictionId = jurisdictionId;
        this.configurationId = configurationId;
    }

    public Long getJurisdictionId() {
        return jurisdictionId;
    }

    public void setJurisdictionId(Long jurisdictionId) {
        this.jurisdictionId = jurisdictionId;
    }

    public Long getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(Long configurationId) {
        this.configurationId = configurationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JurisdictionConfigId that = (JurisdictionConfigId) o;

        if (jurisdictionId != null ? !jurisdictionId.equals(that.jurisdictionId) : that.jurisdictionId != null)
            return false;
        return configurationId != null ? configurationId.equals(that.configurationId) : that.configurationId == null;
    }

    @Override
    public int hashCode() {
        int result = jurisdictionId != null ? jurisdictionId.hashCode() : 0;
        result = 31 * result + (configurationId != null ? configurationId.hashCode() : 0);
        return result;
    }
}
