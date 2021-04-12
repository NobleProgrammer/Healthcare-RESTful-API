/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.health.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Amar
 */
@Entity
@Table(name = "TOPIC")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t")
    , @NamedQuery(name = "Topic.findById", query = "SELECT t FROM Topic t WHERE t.id = :id")
    , @NamedQuery(name = "Topic.findByTitle", query = "SELECT t FROM Topic t WHERE t.title = :title")})
public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ID")
    private Integer id;
    //@Size(max = 255)
    @Column(name = "TITLE")
    private String title;
    @Lob
    //@Size(max = 32700)
    @Column(name = "BODY")
    private String body;
    @JoinColumn(name = "AUTHOR_ID", referencedColumnName = "ID")
    @ManyToOne
    private Account authorId;

    public Topic() {
    }

    public Topic(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Account getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Account authorId) {
        this.authorId = authorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topic)) {
            return false;
        }
        Topic other = (Topic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.health.entity.Topic[ id=" + id + " ]";
    }
    
}
