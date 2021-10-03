/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.panelgestionar;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import vista.InterfazConcurso;

/**
 *
 * @author YCA
 */
public class PanelInferior extends JPanel implements ActionListener{
    
    private JButton btnActualizar, btnEliminar;

    private InterfazConcurso ventana;
    
    public PanelInferior(InterfazConcurso unaVentana) {
        
        ventana = unaVentana;
        
        setLayout(new GridLayout(2, 1,5,5));
        setBorder(BorderFactory.createTitledBorder("Acciones"));
        
        btnActualizar = new JButton("Actualizar");
        btnActualizar.addActionListener(this);
        add(btnActualizar);
        
        btnEliminar = new JButton("Eliminar");
        btnEliminar.addActionListener(this);
        add(btnEliminar);
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnEliminar){
            ventana.eliminarParticipante();
        }
        
        if(e.getSource() == btnActualizar){
            ventana.dialogoEditar();
        }
    }
    
    
    
    
}
