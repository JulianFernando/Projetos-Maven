package br.com.rps.managedbeans;

import br.com.rps.facade.UserFacade;
import br.com.rps.model.User;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author julian.fernando
 */
@RequestScoped
@ManagedBean
public class UserMB {
    
    @EJB
    private UserFacade userFacade;
    
    private User user;

    public User getUser() {
        if (user == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userEmail = context.getUserPrincipal().getName();
            user = userFacade.findUserByEmail(userEmail);
        }
        return user;
    }
    
    public boolean isUserAdmin() {
        return getRequest().isUserInRole("ADMIN");
    }
    
    public String logout() {
        getRequest().getSession().invalidate();
        return "logout";
    }
    private HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
    
}
