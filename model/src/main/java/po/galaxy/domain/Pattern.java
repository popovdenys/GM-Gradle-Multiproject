/*
 * File : Pattern.java
 * Description : output templates
 * 
 * Author : Popov Denys
 * Created : 01 Feb, 2018
 * 
 * Modified : 01 Feb, 2018
 * Modified by: Popov Denys
 * 
 * Last modification : list of templates for standard output 
 */

package po.galaxy.domain;

import java.io.PrintWriter;
import java.util.function.Consumer;

public class Pattern {
	
	public enum InputType {
		CHECKBOX{public String get() { return "checkbox";}
		}, SUBMIT{public String get() { return "submit";}
		};
		public abstract String get();
	}

	/**
	 * Templates for JSP pages
	 * @return
	 */
	public static Consumer<Object> infoListNoteJSP() {
		return g -> String.format("<li>%s</li>", g.toString());
	}

	public static Consumer<? super Galaxy> inputGalaxyNoteJSP(String type) {
		return g -> String.format("<li>%s<input type='%s' name='galaxy-%d'></li>", g, type, g.getId());
	}


	/**
	 * Templates for Servlets
	 * @param out
	 * @return
	 */

	public static Consumer<Object> infoListNote(PrintWriter out) {
		return g -> out.println("<li>" + g + "</li>");
	}
	
	public static Consumer<? super Galaxy> inputGalaxyNote(PrintWriter out, String type) {
		return g -> out.println("<li>" + g + "<input type=\"" + type + "\" name=\"galaxy-" + g.getId() + "\"></li>");
	}
	
	public static void totalResultInfo(PrintWriter out, int count) {
		out.println("<p><span>" + count + " Galaxies in total</span></p>");
	}
	
	public static void routesFooter(PrintWriter out) {
		out.print("<p>Back to : <a href=\"galaxy.html\">Galaxy page</a> | ");
		out.println("<a href=\"index.html\">Design Patterns</a></p>");
	}
}
