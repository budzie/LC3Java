package zybo_client.gui.jpanels;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import zybo_client.gui.handlers.TcpMenuHandler;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import zybo_client.gui.handlers.SensorStateHandler;

public class JPanelTcpMenu extends javax.swing.JPanel
{

    private final TcpMenuHandler handler;
    private SensorStateHandler ssHandler;
    private final boolean[] sensorStates;
    private boolean checkButton;

    public JPanelTcpMenu(String ip) throws IOException, InterruptedException
    {
        checkButton = false;
        initComponents();
        // Create TcpMenuHandler:
        handler = new TcpMenuHandler();
        handler.connect(ip, 8001);
        // User-message:
        setText("Connected on " + ip + ":" + 8001 + " (TCP)\n");
        // Boolean-array to hold sensor-states:
        sensorStates = new boolean[8];
        ssHandler = new SensorStateHandler(ip, handler);
        Thread ssH = new Thread(ssHandler);
        ssH.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea = new javax.swing.JTextArea();
        ListButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        StartButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jRadioButton5 = new javax.swing.JRadioButton();
        SensorPlusButton = new javax.swing.JButton();
        SensorMinusButton = new javax.swing.JButton();
        jRadioButton1 = new javax.swing.JRadioButton();
        StopButton = new javax.swing.JButton();
        StatusButton = new javax.swing.JButton();
        jRadioButton8 = new javax.swing.JRadioButton();
        jRadioButton6 = new javax.swing.JRadioButton();
        jRadioButton7 = new javax.swing.JRadioButton();
        GetLogButton = new javax.swing.JButton();

        setBackground(new java.awt.Color(153, 153, 153));
        setFocusable(false);

        jScrollPane1.setAutoscrolls(true);

        jTextArea.setEditable(false);
        jTextArea.setBackground(new java.awt.Color(0, 51, 0));
        jTextArea.setColumns(20);
        jTextArea.setFont(new java.awt.Font("OpenSansEmoji", 0, 16)); // NOI18N
        jTextArea.setForeground(new java.awt.Color(255, 204, 0));
        jTextArea.setRows(5);
        jScrollPane1.setViewportView(jTextArea);

        ListButton.setText("List");
        ListButton.setToolTipText("Get list of sensors from server");
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

        StartButton.setText("Start Logging");
        StartButton.setToolTipText("Start sensor(s) and save to file");
        StartButton.setFocusable(false);
        StartButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StartButtonActionPerformed(evt);
            }
        });

        DeleteButton.setText("Clear Log");
        DeleteButton.setToolTipText("Clear log file");
        DeleteButton.setFocusable(false);
        DeleteButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                DeleteButtonActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Sensor 2");
        jRadioButton2.setContentAreaFilled(false);
        jRadioButton2.setFocusable(false);
        jRadioButton2.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton2ActionPerformed(evt);
            }
        });

        jRadioButton3.setText("Sensor 3");
        jRadioButton3.setContentAreaFilled(false);
        jRadioButton3.setFocusable(false);
        jRadioButton3.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton3ActionPerformed(evt);
            }
        });

        jRadioButton4.setText("Sensor 4");
        jRadioButton4.setContentAreaFilled(false);
        jRadioButton4.setFocusable(false);
        jRadioButton4.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton4ActionPerformed(evt);
            }
        });

        jRadioButton5.setText("Sensor 5");
        jRadioButton5.setContentAreaFilled(false);
        jRadioButton5.setFocusable(false);
        jRadioButton5.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton5ActionPerformed(evt);
            }
        });

        SensorPlusButton.setText("Sample Rate +");
        SensorPlusButton.setToolTipText("Increase sample rate");
        SensorPlusButton.setFocusable(false);
        SensorPlusButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SensorPlusButtonActionPerformed(evt);
            }
        });

        SensorMinusButton.setText("Sample Rate -");
        SensorMinusButton.setToolTipText("Decrease sample rate");
        SensorMinusButton.setFocusable(false);
        SensorMinusButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                SensorMinusButtonActionPerformed(evt);
            }
        });

        jRadioButton1.setText("Sensor 1");
        jRadioButton1.setContentAreaFilled(false);
        jRadioButton1.setFocusable(false);
        jRadioButton1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton1ActionPerformed(evt);
            }
        });

        StopButton.setText("Stop Logging");
        StopButton.setToolTipText("Stop logging to file");
        StopButton.setFocusable(false);
        StopButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StopButtonActionPerformed(evt);
            }
        });

        StatusButton.setText("Status");
        StatusButton.setToolTipText("Show sensors on/off-state");
        StatusButton.setFocusable(false);
        StatusButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                StatusButtonActionPerformed(evt);
            }
        });

        jRadioButton8.setText("Sensor 8");
        jRadioButton8.setContentAreaFilled(false);
        jRadioButton8.setFocusable(false);
        jRadioButton8.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton8ActionPerformed(evt);
            }
        });

        jRadioButton6.setText("Sensor 6");
        jRadioButton6.setContentAreaFilled(false);
        jRadioButton6.setFocusable(false);
        jRadioButton6.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton6ActionPerformed(evt);
            }
        });

        jRadioButton7.setText("Sensor 7");
        jRadioButton7.setContentAreaFilled(false);
        jRadioButton7.setFocusable(false);
        jRadioButton7.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jRadioButton7ActionPerformed(evt);
            }
        });

        GetLogButton.setText("Get Log");
        GetLogButton.setToolTipText("Clear log file");
        GetLogButton.setFocusable(false);
        GetLogButton.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                GetLogButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jRadioButton2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addComponent(jRadioButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jRadioButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jRadioButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jRadioButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(GetLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(102, 102, 102)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(StartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StopButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SensorPlusButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(StatusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(SensorMinusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ListButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(ExitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(252, 252, 252))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {DeleteButton, ExitButton, ListButton, SensorMinusButton, SensorPlusButton, StartButton, StatusButton, StopButton});

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5, jRadioButton6, jRadioButton7, jRadioButton8});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jRadioButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jRadioButton4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jRadioButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(GetLogButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StartButton)
                    .addComponent(SensorPlusButton)
                    .addComponent(DeleteButton)
                    .addComponent(StatusButton))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(StopButton)
                    .addComponent(SensorMinusButton)
                    .addComponent(ListButton)
                    .addComponent(ExitButton))
                .addGap(39, 39, 39))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {ExitButton, ListButton, StartButton, StatusButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {SensorMinusButton, SensorPlusButton});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jRadioButton1, jRadioButton2, jRadioButton3, jRadioButton4, jRadioButton5, jRadioButton6, jRadioButton7, jRadioButton8});

    }// </editor-fold>//GEN-END:initComponents

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ExitButtonActionPerformed
    {//GEN-HEADEREND:event_ExitButtonActionPerformed
        handler.exit();
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void ListButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_ListButtonActionPerformed
    {//GEN-HEADEREND:event_ListButtonActionPerformed
        try
        {
            appendText("\n" + handler.getList());
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_ListButtonActionPerformed

    private void StartButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_StartButtonActionPerformed
    {//GEN-HEADEREND:event_StartButtonActionPerformed
        int i = 1;
        // Check all sensor-states and start sensor if true:
        for (boolean state : sensorStates)
        {
            try
            {
                if (state)
                {
                    String data = handler.start(i);
                        appendText("\n" + data);
                        setButtonLockOn();
                }
                i++;
            }
            catch (IOException ex)
            {
                setText("Connection to server lost. Please reconnect.");
            }
        }
    }//GEN-LAST:event_StartButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_DeleteButtonActionPerformed
    {//GEN-HEADEREND:event_DeleteButtonActionPerformed
        try
        {
            appendText("\n" + handler.delete());
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton2ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton2ActionPerformed
        sensorStates[1] = !sensorStates[1];
        if (sensorStates[1])
        {
            setButtonLockOn(jRadioButton2);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton3ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton3ActionPerformed
        sensorStates[2] = !sensorStates[2];
        if (sensorStates[2])
        {
            setButtonLockOn(jRadioButton3);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton4ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton4ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton4ActionPerformed
        sensorStates[3] = !sensorStates[3];
        if (sensorStates[3])
        {
            setButtonLockOn(jRadioButton4);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton4ActionPerformed

    private void jRadioButton5ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton5ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton5ActionPerformed
        sensorStates[4] = !sensorStates[4];
        if (sensorStates[4])
        {
            setButtonLockOn(jRadioButton5);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton5ActionPerformed

    private void SensorPlusButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SensorPlusButtonActionPerformed
    {//GEN-HEADEREND:event_SensorPlusButtonActionPerformed
        int i = 1;
        // Check all sensor-states and start sensor if true:
        for (boolean state : sensorStates)
        {
            try
            {
                if (state)
                {
                    String data = handler.incrSensor(i);
                    if (!data.equals("null"))
                    {
                        appendText("\n" + data);
                    }
                }
                i++;
            }
            catch (IOException ex)
            {
                setText("Connection to server lost. Please reconnect.");
            }
        }
    }//GEN-LAST:event_SensorPlusButtonActionPerformed

    private void SensorMinusButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_SensorMinusButtonActionPerformed
    {//GEN-HEADEREND:event_SensorMinusButtonActionPerformed
        int i = 1;
        // Check all sensor-states and start sensor if true:
        for (boolean state : sensorStates)
        {
            try
            {
                if (state)
                {
                    String data = handler.decrSensor(i);
                    if (!data.equals("null"))
                    {
                        appendText("\n" + data);
                    }
                }
                i++;
            }
            catch (IOException ex)
            {
                setText("Connection to server lost. Please reconnect.");
            }
        }
    }//GEN-LAST:event_SensorMinusButtonActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton1ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton1ActionPerformed
        sensorStates[0] = !sensorStates[0];
        if (sensorStates[0])
        {
            setButtonLockOn(jRadioButton1);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void StopButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_StopButtonActionPerformed
    {//GEN-HEADEREND:event_StopButtonActionPerformed
        int i = 1;
        //Component button;
        // Check all sensor-states and start sensor if true:
        for (boolean state : sensorStates)
        {
            try
            {
                if (state)
                {
                    String data = handler.stop(i);
                    if (!data.equals("null"))
                    {
                        appendText("\n" + data);
                        setButtonLockOff();
                        setButtonSelectedOff();
                    }
                }
                i++;
            }
            catch (IOException ex)
            {
                setText("Connection to server lost. Please reconnect.");
            }
        }
    }//GEN-LAST:event_StopButtonActionPerformed

    private void StatusButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_StatusButtonActionPerformed
    {//GEN-HEADEREND:event_StatusButtonActionPerformed
        try
        {
            appendText("\n" + handler.status());
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_StatusButtonActionPerformed

    private void jRadioButton7ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton7ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton7ActionPerformed
        sensorStates[6] = !sensorStates[6];
        if (sensorStates[6])
        {
            setButtonLockOn(jRadioButton7);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton7ActionPerformed

    private void jRadioButton6ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton6ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton6ActionPerformed
        sensorStates[5] = !sensorStates[5];
        if (sensorStates[5])
        {
            setButtonLockOn(jRadioButton6);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton6ActionPerformed

    private void jRadioButton8ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jRadioButton8ActionPerformed
    {//GEN-HEADEREND:event_jRadioButton8ActionPerformed
        sensorStates[7] = !sensorStates[7];
        if (sensorStates[7])
        {
            setButtonLockOn(jRadioButton8);
        }
        else
        {
            setButtonLockOff();
        }
    }//GEN-LAST:event_jRadioButton8ActionPerformed

    private void GetLogButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_GetLogButtonActionPerformed
    {//GEN-HEADEREND:event_GetLogButtonActionPerformed
        try
        {
            appendText("\n" + handler.getData());
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
    }//GEN-LAST:event_GetLogButtonActionPerformed

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

    public void setButtonLockOff()
    {
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
        jRadioButton3.setEnabled(true);
        jRadioButton4.setEnabled(true);
        jRadioButton5.setEnabled(true);
        jRadioButton6.setEnabled(true);
        jRadioButton7.setEnabled(true);
        jRadioButton8.setEnabled(true);
    }   
    
    public void setButtonSelectedOff()
    {
        jRadioButton1.setSelected(false);
        sensorStates[0] = false;
        jRadioButton2.setSelected(false);
        sensorStates[1] = false;
        jRadioButton3.setSelected(false);
        sensorStates[2] = false;
        jRadioButton4.setSelected(false);
        sensorStates[3] = false;
        jRadioButton5.setSelected(false);
        sensorStates[4] = false;
        jRadioButton6.setSelected(false);
        sensorStates[5] = false;
        jRadioButton7.setSelected(false);
        sensorStates[6] = false;
        jRadioButton8.setSelected(false);
        sensorStates[7] = false;
    }
    
    public void setButtonLockOn()
    {
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setEnabled(false);
        jRadioButton4.setEnabled(false);
        jRadioButton5.setEnabled(false);
        jRadioButton6.setEnabled(false);
        jRadioButton7.setEnabled(false);
        jRadioButton8.setEnabled(false);
    }
    
    public void setButtonLockOn(Component button)
    {
        jRadioButton1.setEnabled(false);
        jRadioButton2.setEnabled(false);
        jRadioButton3.setEnabled(false);
        jRadioButton4.setEnabled(false);
        jRadioButton5.setEnabled(false);
        jRadioButton6.setEnabled(false);
        jRadioButton7.setEnabled(false);
        jRadioButton8.setEnabled(false);
        button.setEnabled(true);
    }

    private void paintOval(Graphics g, Component c, Color f)
    {
        g.setColor(f);
        g.fillOval(c.getX() - 15, c.getY() + 6, 10, 10);
        g.setColor(Color.DARK_GRAY);
        g.drawOval(c.getX() - 15, c.getY() + 6, 10, 10);
    }

    public void paintStates(Graphics g) throws IOException,
            InterruptedException
    {
        Component button = null;
        String states = ssHandler.getStates();
        if (!states.equals("null"))
        {
            for (int i = 1; i < 9; i++)
            {
                switch (i)
                {
                    case 1:
                        button = jRadioButton1;
                        break;
                    case 2:
                        button = jRadioButton2;
                        break;
                    case 3:
                        button = jRadioButton3;
                        break;
                    case 4:
                        button = jRadioButton4;
                        break;
                    case 5:
                        button = jRadioButton5;
                        break;
                    case 6:
                        button = jRadioButton6;
                        break;
                    case 7:
                        button = jRadioButton7;
                        break;
                    case 8:
                        button = jRadioButton8;
                        break;
                }
                Color color = Color.RED;
                if (states.contains(i + ""))
                {
                    color = Color.GREEN;
                }
                paintOval(g, button, color);
                repaint();

            }
        }
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        try
        {
            paintStates(g);
        }
        catch (IOException ex)
        {
            setText("Connection to server lost. Please reconnect.");
        }
        catch (InterruptedException ex)
        {
            //ex.printStackTrace();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton ExitButton;
    private javax.swing.JButton GetLogButton;
    private javax.swing.JButton ListButton;
    private javax.swing.JButton SensorMinusButton;
    private javax.swing.JButton SensorPlusButton;
    private javax.swing.JButton StartButton;
    private javax.swing.JButton StatusButton;
    private javax.swing.JButton StopButton;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JRadioButton jRadioButton5;
    private javax.swing.JRadioButton jRadioButton6;
    private javax.swing.JRadioButton jRadioButton7;
    private javax.swing.JRadioButton jRadioButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea;
    // End of variables declaration//GEN-END:variables

}
