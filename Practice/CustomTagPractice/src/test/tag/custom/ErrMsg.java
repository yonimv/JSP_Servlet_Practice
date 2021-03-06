package test.tag.custom;

import java.io.*;
import javax.servlet.jsp.*;
import javax.servlet.jsp.tagext.*;
/**
 * Error Message JSP Custom Tag, simple tag, no tag body
 *
 */
public class ErrMsg extends TagSupport {
	private static final long serialVersionUID = -2121640315534820214L;

	private String _msg; //-- error message
	private String _msgDescription; //-- error description
	private String _styleClass; //-- css class
	private String _onClick; // script to execute when clicked
	/**
	 * tag attribute setter
	 * @param msg The error messsage
	 */
	public void setMsg(String msg) {
		_msg = msg;
	}
	/**
	 * tag attribute setter
	 * @param msgDescription The error description
	 */
	public void setMsgDescription(String msgDescription) {
		_msgDescription = msgDescription;
	}
	/**
	 * tag attribute setter
	 * @param msgStyle The css class
	 */
	public void setStyleClass(String styleClass){
		_styleClass = styleClass;
	}
	/**
	 * tag attribute setter
	 * @param onClick The script to execute while clicked
	 */
	public void setOnClick (String onClick) {
		_onClick = onClick;
	}
	/**
	 * do start tag, this function will be called when parsing &lt;errMsg msg=...&gt;
	 */
	public int doStartTag() throws JspException {
		try {
			JspWriter out = pageContext.getOut();
			if(_msg != null){
				//-- output span's start tag
				out.print("<span");

				//-- output default style or specified class
				if (_styleClass == null) {
					out.print(" style=\"color: red; font-weight:bold;font-style:italic;\"");
				} else {
					out.print(" class=\""
								+ _styleClass
								+ "\"");
				}
				//-- output default script or specified function
				if (_onClick == null) {
					out.print(" onclick=\"alert('"
								+ _msg
								+ (_msgDescription == null? "" : ": "+_msgDescription)
								+ "');\"");
				} else {
					out.print(" onclick=\""
								+ _onClick+"\"");
				}
				// finish span's start tag, output _msg.
				out.print(">" + _msg);
			}
		} catch (Exception e) {
			throw new JspException("Error: IOException while writing to client");
		}
		// simple tag, no body.
		return SKIP_BODY;
	}
	/**
	 * do end tag, this function will be called when parsing &lt;/errMsg&gt;
	 */
	public int doEndTag() throws JspException {
		try {
			// output span's end tag
			pageContext.getOut().print("</span>");
		} catch (IOException ioe) {
			throw new JspException("Error: IOException while writing to client");
		}
		release();
		//-- continue processing the page
		return EVAL_PAGE;
	}
	/**
	 * reset attributes, container maybe reuse this object
	 */
	public void release() {
		_msg = null;
		_msgDescription = null;
		_styleClass = null;
		_onClick = null;
	}
}