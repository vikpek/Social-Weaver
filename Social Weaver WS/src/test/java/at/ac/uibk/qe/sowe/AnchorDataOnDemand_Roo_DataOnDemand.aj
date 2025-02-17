// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package at.ac.uibk.qe.sowe;

import at.ac.uibk.qe.sowe.Anchor;
import at.ac.uibk.qe.sowe.AnchorDataOnDemand;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

privileged aspect AnchorDataOnDemand_Roo_DataOnDemand {
    
    declare @type: AnchorDataOnDemand: @Component;
    
    private Random AnchorDataOnDemand.rnd = new SecureRandom();
    
    private List<Anchor> AnchorDataOnDemand.data;
    
    public Anchor AnchorDataOnDemand.getNewTransientAnchor(int index) {
        Anchor obj = new Anchor();
        setBinaryPayload(obj, index);
        setLastModifiedTimestamp(obj, index);
        setOid(obj, index);
        setPayload(obj, index);
        setUrl(obj, index);
        return obj;
    }
    
    public void AnchorDataOnDemand.setBinaryPayload(Anchor obj, int index) {
        byte[] binaryPayload = String.valueOf(index).getBytes();
        obj.setBinaryPayload(binaryPayload);
    }
    
    public void AnchorDataOnDemand.setLastModifiedTimestamp(Anchor obj, int index) {
        Long lastModifiedTimestamp = new Integer(index).longValue();
        obj.setLastModifiedTimestamp(lastModifiedTimestamp);
    }
    
    public void AnchorDataOnDemand.setOid(Anchor obj, int index) {
        Long oid = new Integer(index).longValue();
        obj.setOid(oid);
    }
    
    public void AnchorDataOnDemand.setPayload(Anchor obj, int index) {
        String payload = "payload_" + index;
        obj.setPayload(payload);
    }
    
    public void AnchorDataOnDemand.setUrl(Anchor obj, int index) {
        String url = "url_" + index;
        obj.setUrl(url);
    }
    
    public Anchor AnchorDataOnDemand.getSpecificAnchor(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Anchor obj = data.get(index);
        Long id = obj.getId();
        return Anchor.findAnchor(id);
    }
    
    public Anchor AnchorDataOnDemand.getRandomAnchor() {
        init();
        Anchor obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Anchor.findAnchor(id);
    }
    
    public boolean AnchorDataOnDemand.modifyAnchor(Anchor obj) {
        return false;
    }
    
    public void AnchorDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Anchor.findAnchorEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Anchor' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Anchor>();
        for (int i = 0; i < 10; i++) {
            Anchor obj = getNewTransientAnchor(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
