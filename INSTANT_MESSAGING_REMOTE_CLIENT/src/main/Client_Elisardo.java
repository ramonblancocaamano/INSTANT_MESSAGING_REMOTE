package main;

import topicmanager.TopicManagerStub;


/**
 * @Author: BLANCO CAAMANO, Ramon <ramonblancocaamano@gmail.com>
 */
public class Client_Elisardo {

  public static void main(String[] args) {
    
   /*
    * Execute one client at a time to open separate windows on Netbeans, and 
    * also because otherwise all clients share the same WebSocket connection.
    */
    javax.swing.SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        SwingClient client = new SwingClient(new TopicManagerStub("Elisardo"));
        client.createAndShowGUI();
      }
    });
    
  }
}