// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package at.ac.uibk.qe.sowe;

import at.ac.uibk.qe.sowe.Anchor;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

privileged aspect Anchor_Roo_Jpa_Entity {
    
    declare @type: Anchor: @Entity;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long Anchor.id;
    
    @Version
    @Column(name = "version")
    private Integer Anchor.version;
    
    public Long Anchor.getId() {
        return this.id;
    }
    
    public void Anchor.setId(Long id) {
        this.id = id;
    }
    
    public Integer Anchor.getVersion() {
        return this.version;
    }
    
    public void Anchor.setVersion(Integer version) {
        this.version = version;
    }
    
}
