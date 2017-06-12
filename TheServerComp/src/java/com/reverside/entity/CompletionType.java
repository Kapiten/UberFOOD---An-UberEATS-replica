/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.reverside.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author StrettO
 */
@Entity
@Table(name = "completion_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompletionType.findAll", query = "SELECT c FROM CompletionType c")
    , @NamedQuery(name = "CompletionType.findByCompletionTypeId", query = "SELECT c FROM CompletionType c WHERE c.completionTypeId = :completionTypeId")
    , @NamedQuery(name = "CompletionType.findByName", query = "SELECT c FROM CompletionType c WHERE c.name = :name")
    , @NamedQuery(name = "CompletionType.findByDescription", query = "SELECT c FROM CompletionType c WHERE c.description = :description")})
public class CompletionType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "completion_type_id")
    private Integer completionTypeId;
    @Size(max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 300)
    @Column(name = "description")
    private String description;

    public CompletionType() {
    }

    public CompletionType(Integer completionTypeId) {
        this.completionTypeId = completionTypeId;
    }

    public Integer getCompletionTypeId() {
        return completionTypeId;
    }

    public void setCompletionTypeId(Integer completionTypeId) {
        this.completionTypeId = completionTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (completionTypeId != null ? completionTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompletionType)) {
            return false;
        }
        CompletionType other = (CompletionType) object;
        if ((this.completionTypeId == null && other.completionTypeId != null) || (this.completionTypeId != null && !this.completionTypeId.equals(other.completionTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.reverside.entity.CompletionType[ completionTypeId=" + completionTypeId + " ]";
    }
    
}
