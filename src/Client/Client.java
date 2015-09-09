/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Lucas
 */
public class Client extends javax.swing.JFrame {

    private Socket clientSocket;
    
    private BufferedReader input;
    private DataInputStream dataInput;
    private PrintStream output;
    private DataOutputStream dataOutput;
    
    private final String actionOne = "Action 1";
    private final String actionTwo = "Action 2";
    private final String actionThree = "Action 3";
    
    private Scanner scan = new Scanner(System.in);
    final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Creates new form Client
     */
    public Client() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        clientTextArea = new javax.swing.JTextArea();
        clientComboBox = new javax.swing.JComboBox();
        clientTextField = new javax.swing.JTextField();
        clientButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        clientTextArea.setColumns(20);
        clientTextArea.setRows(5);
        jScrollPane1.setViewportView(clientTextArea);

        clientComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Choose an Action", "hello", "ok", "yes", "no" }));

        clientButton.setText("Send");
        clientButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientButton, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {clientButton, clientComboBox, clientTextField, jScrollPane1});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clientButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientButtonActionPerformed
        // TODO add your handling code here:
        if (clientSocket.isConnected()) {
//            System.out.println("Working? " + (String)clientComboBox.getSelectedItem());
//            (String)clientComboBox.getSelectedItem();
            
            if (clientComboBox.getSelectedIndex() != 0) {
//                if (!"".equals(clientTextField.getText())) {
                    //            String reply = scan.nextLine();
                    String reply = clientTextField.getText();
                    Message message = new Message(clientComboBox.getSelectedIndex());
                    
                    try {
                        dataOutput.writeInt(message.getByteArray().length); // write length of the message
                        dataOutput.write(message.getByteArray());           // write the message
                    } catch (IOException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
//                    output.println(reply);
//                    clientTextArea.append("Client: " + reply + "\n");
//                    System.out.println("Client: " + reply);

                    clientTextField.setText("");
//                }
            }
            
            
            
            
        }

    }//GEN-LAST:event_clientButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Client.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Client().setVisible(true);
//            }
//        });
        Client client = new Client();
        client.setVisible(true);
        client.run();
    }

    public void run() {
        try {
//            new Client().setVisible(true);

            // IP address to the PC to connect to.
            // Port number to connect to.
            clientSocket = new Socket("200.19.188.1", 20400);

            output = new PrintStream(clientSocket.getOutputStream());
            dataOutput = new DataOutputStream(clientSocket.getOutputStream());
//            output.println("Hello Server");
            
            Message sendThis = new Message(1);
            dataOutput.writeInt(sendThis.getByteArray().length); // write length of the message
            dataOutput.write(sendThis.getByteArray());           // write the message
            
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            dataInput = new DataInputStream(clientSocket.getInputStream());

            while (clientSocket.isConnected()) {
//                String message = input.readLine();
//                clientTextArea.append("Server: " + message + "\n");
//                System.out.println("Server: " + message);
                
                int length = dataInput.readInt();                    // read length of incoming message
                if(length > 0) {
                    byte[] testMessage = new byte[length];
                    dataInput.readFully(testMessage, 0, testMessage.length); // read the message
                    System.out.println("Byte Aray: " + bytesToHexString(testMessage));
                    clientTextArea.append("Byte Aray: " + bytesToHexString(testMessage) + "\n");
                    
                    Message message = new Message(testMessage);
//                    System.out.println("Byte 1: " + message.idByte);
//                    System.out.println("Byte 2: " + message.payloadLengthByte);
//                    System.out.println("Byte 3: " + message.messageCodeByte);
                    
                }
                

//                String reply = scan.nextLine();
//                output.println(reply);
            }
            System.out.println("\nDisconnected?\n");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                                 + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }
   
    public static String bytesToHexString(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for ( int j = 0; j < bytes.length; j++ ) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clientButton;
    private javax.swing.JComboBox clientComboBox;
    private javax.swing.JTextArea clientTextArea;
    private javax.swing.JTextField clientTextField;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
