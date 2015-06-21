package zybo_client.gui.jpanels;

import zybo_client.gui.handlers.FtpMenuHandler;
import java.io.IOException;

public class JPanelFtpMenu extends javax.swing.JPanel
{

    private final FtpMenuHandler handler;

    public JPanelFtpMenu(String ip) throws IOException, InterruptedException
    {
        // Create FtpMenuHandler:
        handler = new FtpMenuHandler();
        initComponents();
        // Connect to FTP-server:
        boolean answer = handler.connectFTP(ip);
        if (answer)
            setText("Connected on " + ip + ":21 (FTP)\n");
        else
            setText("Error connecting to FTP-server");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        inputTextField = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        HelpButton = new javax.swing.JButton();
        ListButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        GetButton = new javax.swing.JButton();
        SendButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 153));

        inputTextField.setToolTipText("Type command or filename here");
        inputTextField.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                inputTextFieldActionPerformed(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        jTextArea.setEditable(false);
        jTextArea.setBackground(new java.awt.Color(0, 51, 0));
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("OpenSansEmoji", 0, 16)); // NOI18N
        jTextArea.setForeground(new java.awt.Color(255, 204, 0));
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        HelpButton.setText("Help");
        HelpButton.setToolTipText("Get list of commands");
        HelpButton.setFocusable(false);
        HelpButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                HelpButtonActionPerformed(evt);
            }
        });

        ListButton.setText("List");
        ListButton.setToolTipText("List files on server");
        ListButton.setFocusable(false);
        ListButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ListButtonActionPerformed(evt);
            }
        });

        ExitButton.setText("Exit");
        ExitButton.setToolTipText("Quit to desktop");
        ExitButton.setFocusable(false);
        ExitButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                ExitButtonActionPerformed(evt);
            }
        });

        GetButton.setText("Get");
        GetButton.setToolTipText("Download file from server");
        GetButton.setFocusable(false);
        GetButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GetButtonActionPerformed(evt);
            }
        });

        SendButton.setText("Send");
        SendButton.setToolTipText("Send command to server");
        SendButton.setFocusable(false);
        SendButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SendButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(HelpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(ListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(GetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {ExitButton, GetButton, HelpButton, ListButton, SendButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SendButton)
                    .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(HelpButton)
                    .addComponent(ListButton)
                    .addComponent(ExitButton)
                    .addComponent(GetButton))
                .addGap(19, 19, 19))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ExitButton, GetButton, HelpButton, ListButton, SendButton, inputTextField});

    }// </editor-fold>//GEN-END:initComponents

    private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inputTextFieldActionPerformed
    {//GEN-HEADEREND:event_inputTextFieldActionPerformed

    }//GEN-LAST:event_inputTextFieldActionPerformed

    private void HelpButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_HelpButtonActionPerformed
    {//GEN-HEADEREND:event_HelpButtonActionPerformed
        try
        {
            String answer = handler.sendCmd("HELP");
            appendText("\n" + answer);
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }        
    }//GEN-LAST:event_HelpButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitButtonActionPerformed
    {//GEN-HEADEREND:event_ExitButtonActionPerformed
        handler.exit();
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ListButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ListButtonActionPerformed
    {//GEN-HEADEREND:event_ListButtonActionPerformed
        try
        {
            String answer = handler.list();
            appendText("\n" + answer);
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_ListButtonActionPerformed

    private void GetButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GetButtonActionPerformed
    {//GEN-HEADEREND:event_GetButtonActionPerformed
        try
        {
            if (handler.saveFile(inputTextField.getText()))
                appendText("\n" + inputTextField.getText() + " retrieved succesfuly");
            else
                appendText("\nFailed to open file.");
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_GetButtonActionPerformed

    private void SendButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SendButtonActionPerformed
    {//GEN-HEADEREND:event_SendButtonActionPerformed
        try
        {
            String data = inputTextField.getText();
            if (data.length() > 0)            
                appendText("\n" + handler.sendCmd(data));
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_SendButtonActionPerformed

    public void setText(String in)
    {
        jTextArea.setText(in);
    }

    public void appendText(String in)
    {
        jTextArea.append(in);
    }
    
    public void disconnect()
    {
        handler.disconnect();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton GetButton;
    private javax.swing.JButton HelpButton;
    private javax.swing.JButton ListButton;
    private javax.swing.JButton SendButton;
    private javax.swing.JTextField inputTextField;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables
}
