/*
https://www.w3schools.com/java/java_date.asp
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.panelagregar;

import com.toedter.calendar.JDateChooser;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import vista.InterfazConcurso;

/**
 *
 * @author YCA
 */
public class PanelAgregar extends JPanel implements ActionListener{
    
    private JLabel lblAlias, lblNombre, lblApellido, lblEmail, lblCelular, lblContrasena, lblFechaNacmiento;
    
    private JTextField txtAlias, txtNombre, txtApellido, txtEmail, txtCelular;
    
    private JPasswordField txtContrasena;
    
    private JDateChooser txtFechaNacimiento;
    
    private JButton btnGuadar, btnCancelar;

    private GridBagConstraints gbc;
    
    private InterfazConcurso ventana;
    
    public PanelAgregar(InterfazConcurso unaVentana) {
        
        ventana = unaVentana;
        
        setLayout(new GridBagLayout());
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblAlias = new JLabel("Alias:");
        add(lblAlias,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtAlias = new JTextField(15);
        add(txtAlias,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblNombre = new JLabel("Nombre:");
        add(lblNombre,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtNombre = new JTextField(15);
        add(txtNombre,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblApellido = new JLabel("Apellido:");
        add(lblApellido,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtApellido = new JTextField(15);
        add(txtApellido,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblEmail = new JLabel("E-mail:");
        add(lblEmail,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtEmail = new JTextField(15);
        add(txtEmail,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblCelular = new JLabel("Celular:");
        add(lblCelular,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtCelular = new JTextField(15);
        add(txtCelular,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        lblContrasena = new JLabel("Contraseña:");
        add(lblContrasena,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtContrasena = new JPasswordField(15);
        add(txtContrasena,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.insets = new Insets(5, 5, 5, 5);
        lblFechaNacmiento = new JLabel("Fecha de nacimiento:");
        add(lblFechaNacmiento,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 7;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.BOTH;
        txtFechaNacimiento = new JDateChooser();
        add(txtFechaNacimiento,gbc);
        
        JPanel panelAux = new JPanel(new FlowLayout(FlowLayout.CENTER,5,5));
        
        btnGuadar = new JButton("Guardar");
        btnGuadar.addActionListener(this);
        panelAux.add(btnGuadar);
        
        btnCancelar = new JButton("Cancelar");
        panelAux.add(btnCancelar);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        add(panelAux,gbc);
    }
    
    public String darAlias(){
        return txtAlias.getText();
    }
    
    public String darNombre(){
        return txtNombre.getText();
    }
    
    public String darApellido(){
        return txtApellido.getText();
    }
    
    public String darCorreo(){
        return txtEmail.getText();
    }
    
    public String darCelular(){
        return txtCelular.getText();
    }
    
    public String darContrasena(){
        return new String(txtContrasena.getPassword());
    }
    
    public String darFecha(){
        Calendar calendario = txtFechaNacimiento.getCalendar();
        return ""+calendario.get(Calendar.YEAR)+"-"+(calendario.get(Calendar.MONTH)+1)+"-"+calendario.get(Calendar.DAY_OF_MONTH);
    }
    
    public void limpiarCampos(){
        txtAlias.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtEmail.setText("");
        txtCelular.setText("");
        txtContrasena.setText("");
        txtFechaNacimiento.cleanup();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnGuadar){
            ventana.agregarParticipante();
        }
    }
}
