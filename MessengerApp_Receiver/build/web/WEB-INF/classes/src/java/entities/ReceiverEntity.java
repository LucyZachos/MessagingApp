package entities;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.QueueBrowser;
import jakarta.jms.Session;
import java.util.Enumeration;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ReceiverEntity {
    @Resource(lookup = "jms/connection")
    private ConnectionFactory connectionFactory;

    @Resource(lookup = "jms/destination")
    private Destination myQueue;

    public List<String> browseMessages() {
        List<String> messages = new ArrayList<>();

        try {
            // Create a JMS connection to the messaging system
            Connection connection = connectionFactory.createConnection();

            // Create a JMS session for message processing
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Create a QueueBrowser to browse messages in the queue
            QueueBrowser browser = session.createBrowser((jakarta.jms.Queue) myQueue);

            // Start the JMS connection
            connection.start();

            // Get the enumeration of messages in the queue
            Enumeration<?> enumeration = browser.getEnumeration();

            while (enumeration.hasMoreElements()) {
                // Cast the message to TextMessage and extract the text content
                Message message = (Message) enumeration.nextElement();
                if (message instanceof jakarta.jms.TextMessage) {
                    jakarta.jms.TextMessage textMessage = (jakarta.jms.TextMessage) message;
                    String messageText = textMessage.getText();
                    messages.add(messageText);
                }
            }

            // Close the JMS browser, session, and connection
            browser.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            // Handle JMS exceptions (e.g., connection or session errors)
            e.printStackTrace();
        }

        return messages;
    }
}
