/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vista.panelgestionar;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import vista.InterfazConcurso;

/**
 *
 * @author YCA
 */
public class PanelCentral extends JPanel implements ActionListener{
    
    private static final String[] NOMBRE_COLUMNAS = {"Alias","Nombre","Apellido","E-mail","Celular","Contraseña","Fecha de nacimiento"};
    
    private JTable tblParticipante;
    
    private DefaultTableModel tablaModelo;
    
    private JScrollPane scrlParticipante;
    
    private JButton btnACtualizar;
    
    private GridBagConstraints gbc;
    
    private InterfazConcurso ventana;

    public PanelCentral(InterfazConcurso unaVentana) {
        
        ventana = unaVentana;
        
        setLayout(new GridBagLayout());
        setPreferredSize(new Dimension(0, 250));
        setBorder(BorderFactory.createTitledBorder("Lista de participantes"));
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        tblParticipante = new JTable();
        tblParticipante.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        tablaModelo = new DefaultTableModel();
        tablaModelo.setColumnIdentifiers(NOMBRE_COLUMNAS);
        tblParticipante.setModel(tablaModelo);
        
        scrlParticipante = new JScrollPane(tblParticipante);
        add(scrlParticipante,gbc);
        
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        btnACtualizar = new JButton("Refrescar tabla");
        btnACtualizar.addActionListener(this);
        add(btnACtualizar,gbc);
    }
    
    public void limpiarTabla() {
        if (tablaModelo != null) {
            tablaModelo = null;
        }
        tablaModelo = new DefaultTableModel();
        tablaModelo.setColumnIdentifiers(NOMBRE_COLUMNAS);
        tblParticipante.setModel(tablaModelo);
    }
    
    public void actualizarListaParticipantes(String[] unContenido){
        tablaModelo.addRow(unContenido);
        tblParticipante.setModel(tablaModelo);
    }
    
    public String darParticipanteSeleccionado(){
        int columna = 0;
        int fila = tblParticipante.getSelectedRow();
        return tblParticipante.getModel().getValueAt(fila, columna).toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ventana.buscarTodosParticipantes();
    }
    
    
    
}
