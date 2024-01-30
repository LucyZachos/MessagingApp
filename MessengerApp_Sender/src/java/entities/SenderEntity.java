package entities;

import jakarta.annotation.Resource;
import jakarta.ejb.Stateless;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;


@Stateless
public class SenderEntity {
    @Resource(lookup = "jms/connection")
    private ConnectionFactory connectionFactory;
    
    @Resource(lookup = "jms/destination")
    private Destination myQueue;

    public void sendMessages(String message) {

        try {

            Connection connection = connectionFactory.createConnection();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            MessageProducer producer = session.createProducer(myQueue);

            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);

            // Log the message to the console
            System.out.println("Message sent: " + textMessage.getText());

        } catch (jakarta.jms.JMSException e) {
            // Handle exceptions
            e.printStackTrace();
        }
    }
}
