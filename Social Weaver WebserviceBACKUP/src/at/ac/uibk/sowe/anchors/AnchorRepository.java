package at.ac.uibk.sowe.anchors;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * AnchorRepository in Social Weaver Webservice
 * User: Viktor Pekar
 * Date: 27.03.13
 */

@Stateless
@LocalBean
public class AnchorRepository {

    @PersistenceContext(unitName = "SocialWeaverPU")
    EntityManager em;

    public AnchorRepository() {
    }

    public AnchorRepository(EntityManager em) {
        this.em = em;
    }

    public AnchorEntity create(AnchorEntity entity) {
        em.persist(entity);
        return entity;
    }

    public void delete(AnchorEntity entity) {
        AnchorEntity found = em.find(AnchorEntity.class, entity.getAncestorId());
        em.remove(found);
    }

    public List<AnchorEntity> findAll() {
        Query findAllQuery;
        findAllQuery = em.createNamedQuery("AnchorEntity.findAll");
        List<AnchorEntity> resultList = findAllQuery.getResultList();
        return resultList;
    }

}
