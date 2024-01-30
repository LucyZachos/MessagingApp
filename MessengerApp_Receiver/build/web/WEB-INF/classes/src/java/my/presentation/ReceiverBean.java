package my.presentation;

import entities.ReceiverEntity;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named("ReceiverBean")
@SessionScoped
public class ReceiverBean implements Serializable {

    @Inject // Dependency injection to obtain an instance of ReceiverEntity
    private ReceiverEntity receiverEntity;

    private List<String> receivedMessages; // Stores received messages

    // Getter method for retrieving the received messages
    public List<String> getReceivedMessages() {
        return receivedMessages;
    }

    // Method to retrieve messages by invoking the corresponding method in ReceiverEntity
    public void retrieveMessages() {
        receivedMessages = receiverEntity.browseMessages();
    }
}
