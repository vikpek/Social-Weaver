// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package at.ac.uibk.qe.sowe;

import at.ac.uibk.qe.sowe.Anchor;
import at.ac.uibk.qe.sowe.AnchorDataOnDemand;
import at.ac.uibk.qe.sowe.AnchorIntegrationTest;
import java.util.Iterator;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AnchorIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AnchorIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AnchorIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
    
    declare @type: AnchorIntegrationTest: @Transactional;
    
    @Autowired
    AnchorDataOnDemand AnchorIntegrationTest.dod;
    
    @Test
    public void AnchorIntegrationTest.testCountAnchors() {
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", dod.getRandomAnchor());
        long count = Anchor.countAnchors();
        Assert.assertTrue("Counter for 'Anchor' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AnchorIntegrationTest.testFindAnchor() {
        Anchor obj = dod.getRandomAnchor();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to provide an identifier", id);
        obj = Anchor.findAnchor(id);
        Assert.assertNotNull("Find method for 'Anchor' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Anchor' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AnchorIntegrationTest.testFindAllAnchors() {
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", dod.getRandomAnchor());
        long count = Anchor.countAnchors();
        Assert.assertTrue("Too expensive to perform a find all test for 'Anchor', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Anchor> result = Anchor.findAllAnchors();
        Assert.assertNotNull("Find all method for 'Anchor' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Anchor' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AnchorIntegrationTest.testFindAnchorEntries() {
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", dod.getRandomAnchor());
        long count = Anchor.countAnchors();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Anchor> result = Anchor.findAnchorEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Anchor' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Anchor' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AnchorIntegrationTest.testFlush() {
        Anchor obj = dod.getRandomAnchor();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to provide an identifier", id);
        obj = Anchor.findAnchor(id);
        Assert.assertNotNull("Find method for 'Anchor' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAnchor(obj);
        Integer currentVersion = obj.getVersion();
        obj.flush();
        Assert.assertTrue("Version for 'Anchor' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnchorIntegrationTest.testMergeUpdate() {
        Anchor obj = dod.getRandomAnchor();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to provide an identifier", id);
        obj = Anchor.findAnchor(id);
        boolean modified =  dod.modifyAnchor(obj);
        Integer currentVersion = obj.getVersion();
        Anchor merged = obj.merge();
        obj.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Anchor' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AnchorIntegrationTest.testPersist() {
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", dod.getRandomAnchor());
        Anchor obj = dod.getNewTransientAnchor(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Anchor' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Anchor' identifier to be null", obj.getId());
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
        Assert.assertNotNull("Expected 'Anchor' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AnchorIntegrationTest.testRemove() {
        Anchor obj = dod.getRandomAnchor();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Anchor' failed to provide an identifier", id);
        obj = Anchor.findAnchor(id);
        obj.remove();
        obj.flush();
        Assert.assertNull("Failed to remove 'Anchor' with identifier '" + id + "'", Anchor.findAnchor(id));
    }
    
}
