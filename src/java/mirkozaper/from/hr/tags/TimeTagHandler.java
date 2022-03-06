/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mirkozaper.from.hr.tags;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author mirko
 */
public class TimeTagHandler extends SimpleTagSupport {

    private LocalDateTime datetime;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
           DateTimeFormatter dtf=DateTimeFormatter.ofPattern("hh:mm:ss");
            out.println(datetime.format(dtf));
        } catch (java.io.IOException ex) {
            throw new JspException("Error in TimeTagHandler tag", ex);
        }
    }

    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }
    
}
