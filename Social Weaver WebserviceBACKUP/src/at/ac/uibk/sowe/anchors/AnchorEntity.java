package at.ac.uibk.sowe.anchors;

import javax.ejb.Stateless;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;

/**
 * AnchorEntity in Social Weaver Webservice
 * User: Viktor Pekar
 * Date: 12.04.13 11:15
 */
@Table(name = "anchor", schema = "public", catalog = "socialweaver")
@Entity
@Stateless
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AnchorEntity.findAll", query = "SELECT a FROM anchor")
})


public class AnchorEntity {
    private long anchorOid;

    @javax.persistence.Column(name = "anchorOid", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    @Id
    public long getAnchorOid() {
        return anchorOid;
    }

    public void setAnchorOid(long anchorOid) {
        this.anchorOid = anchorOid;
    }

    private BigInteger ancestorId;

    @javax.persistence.Column(name = "ancestorId", nullable = true, insertable = true, updatable = true, length = 131089, precision = 0)
    @Basic
    public BigInteger getAncestorId() {
        return ancestorId;
    }

    public void setAncestorId(BigInteger ancestorId) {
        this.ancestorId = ancestorId;
    }

    private String anchorUrl;

    @javax.persistence.Column(name = "anchorURL", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getAnchorUrl() {
        return anchorUrl;
    }

    public void setAnchorUrl(String anchorUrl) {
        this.anchorUrl = anchorUrl;
    }

    private String anchorElementId;

    @javax.persistence.Column(name = "anchorElementId", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getAnchorElementId() {
        return anchorElementId;
    }

    public void setAnchorElementId(String anchorElementId) {
        this.anchorElementId = anchorElementId;
    }

    private String anchorText;

    @javax.persistence.Column(name = "anchorText", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getAnchorText() {
        return anchorText;
    }

    public void setAnchorText(String anchorText) {
        this.anchorText = anchorText;
    }

    private String annotationText;

    @javax.persistence.Column(name = "annotationText", nullable = true, insertable = true, updatable = true, length = 2147483647, precision = 0)
    @Basic
    public String getAnnotationText() {
        return annotationText;
    }

    public void setAnnotationText(String annotationText) {
        this.annotationText = annotationText;
    }


}
