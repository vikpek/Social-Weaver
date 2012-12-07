/**
 * 
 */
package at.ac.uibk.socialweaver.commentbox;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Viktor Pekar
 * 
 */
public class CommentBoxTest {

	/**
	 * 
	 */
	private static final String TEST_TERM_01 = "random comment";
	private static CommentBox cb = new CommentBox();
	
	@Test
	public void testCommentCount1() {
		assertEquals(0, cb.countComments());
	}

	// TODO excepting exception...how?
	@Test
	public void testFailingLatestComment(){
		final Comment result = cb.getLatestComment();
		assertEquals(TEST_TERM_01, result.getComment());
	}
	
	@Test
	public void testAddComment() {
		final Comment c = new Comment(TEST_TERM_01);
		assertEquals(cb.addComment(c), true);
	}
	
	@Test 
	public void testCommentCount2(){
		assertEquals(1, cb.countComments());
	}
	
	@Test
	public void testGetLatestComment(){
		final Comment result = cb.getLatestComment();
		assertEquals(TEST_TERM_01, result.getComment());
	}
}
