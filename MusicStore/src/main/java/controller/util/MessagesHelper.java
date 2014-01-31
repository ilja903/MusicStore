package controller.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public enum MessagesHelper {
	INSTANCE;

	/**
	 * @param entityName
	 *            name of entity which was persisted
	 */
	public void showCreatedSuccessfullyMessage(final String entityName) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage successMessage = new FacesMessage("The " + entityName
				+ " was succesfully created!");
		successMessage.setSeverity(FacesMessage.SEVERITY_INFO);
		facesContext.addMessage(null, successMessage);
	}

	public void showNotCreatedMessage(final String entityName) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage failMessage = new FacesMessage("The " + entityName
				+ " was not succesfully created!");
		failMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		facesContext.addMessage(null, failMessage);
	}

	public void showGeneralWarnMessage(final String message) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage warnMessage = new FacesMessage(message);
		warnMessage.setSeverity(FacesMessage.SEVERITY_WARN);
		facesContext.addMessage(null, warnMessage);
	}

}
