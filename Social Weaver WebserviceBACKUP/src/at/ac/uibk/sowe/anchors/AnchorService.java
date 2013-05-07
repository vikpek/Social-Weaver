package at.ac.uibk.sowe.anchors;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;


/**
 * AnchorBean in Social Weaver Webservice
 * User: Viktor Pekar
 * Date: 27.03.13
 */

@Stateless
public class AnchorService {

    @EJB
    AnchorRepository anchorRepository;

    public AnchorEntity addAnchor(AnchorEntity anchorEntity){
        return anchorRepository.create(anchorEntity);

    }

    public List<AnchorEntity> getAllAnchors(){
        return anchorRepository.findAll();
    }

}
