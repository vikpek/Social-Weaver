/**
 * 
 */
package at.ac.uibk.socialweaver.commentbox;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Viktor Pekar
 * 
 *         Created for for the comment box in the first place - but might be
 *         reused for different social content.
 */
public class Comment implements Serializable {

	private static final long serialVersionUID = 1L;
	private String userId;
	private String comment;
	private Date ts;

	public Comment() {

	}

	public Comment(final String comment) {
		this.comment = comment;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(final String comment) {
		this.comment = comment;
	}

	public Date getTs() {
		return ts;
	}

	public void setTs(final Date ts) {
		this.ts = ts;
	}
}
