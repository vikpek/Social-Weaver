/**
 * 
 */
package at.ac.uibk.socialweaver.commentbox;

import java.io.Serializable;
import java.util.Stack;

import at.ac.uibk.socialweaver.domain.HookContent;

/**
 * @author Viktor Pekar
 * 
 * Just manages comments that can be added and retrieved. 
 * A CommentBox implements HookContent and will therefore be 
 * used in this context.
 * 
 */
public class CommentBox implements HookContent, Serializable {

	private static final long serialVersionUID = 1L;

	private Stack<Comment> commentStack = new Stack<Comment>();

	public boolean addComment(Comment comment) {
		return commentStack.add(comment);
	}

	public Comment getLatestComment() {
		return commentStack.lastElement();
	}

	public Comment[] getComments() {
		return (Comment[]) commentStack.toArray();
	}

	public Comment[] getLatestComments(int limit) {
		if (commentStack.size() < limit) {
			return getComments();
		} else {
			return (Comment[]) commentStack.subList(
					commentStack.size() - limit, commentStack.size()).toArray();
		}
	}
	
	public int countComments(){
		return commentStack.size();
	}
}
