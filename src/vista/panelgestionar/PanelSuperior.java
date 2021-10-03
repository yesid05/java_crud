/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.panelgestionar;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import vista.InterfazConcurso;

/**
 *
 * @author YCA
 */
public class PanelSuperior extends JPanel implements ActionListener{

    private JLabel lblBuscar;
    
    private JTextField txtBuscar;
    
    private JButton btnBuscar;
    
    private GridBagConstraints gbc;
    
    private InterfazConcurso ventana;
    
    public PanelSuperior(InterfazConcurso unaVentana) {
        
        ventana = unaVentana;
        
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Buscar participante"));
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        lblBuscar = new JLabel("Buscar:");
        add(lblBuscar,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        txtBuscar = new JTextField(15);
        add(txtBuscar,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        btnBuscar = new JButton("Buscar participante");
        btnBuscar.addActionListener(this);
        add(btnBuscar,gbc);
        
    }
    
    public void limpiarBuscar(){
        txtBuscar.setText("");
    }
    
    public String darBuscar(){
        return txtBuscar.getText();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ventana.buscarParticipante();
    }
    
    
    
}
