package my.presentation;

import entities.SenderEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named("SenderBean")
@SessionScoped
public class SenderBean implements Serializable {

    @Inject
    private SenderEntity jmsEntity;

    private String message;
    private String confirmationMessage;
    

    public void sendMessages() {
        jmsEntity.sendMessages(message);
        confirmationMessage = "Message sent successfully.";
    }

    // Getter and setter for the message property
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
      public String getConfirmationMessage() {
        return confirmationMessage;
    }

    public void setConfirmationMessage(String confirmationMessage) {
        this.confirmationMessage = confirmationMessage;
    }
}
