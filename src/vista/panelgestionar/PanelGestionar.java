/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.panelgestionar;

import java.awt.BorderLayout;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import modelo.bo.Participante;
import vista.InterfazConcurso;
import vista.dialogoeditar.DialogoEditar;

/**
 *
 * @author YCA
 */
public class PanelGestionar extends JPanel {

    private PanelSuperior panelSuperior;

    private PanelCentral panelCentral;

    private PanelInferior panelInferior;
    
    private DialogoEditar dialogoEditar;

    private InterfazConcurso ventana;

    public PanelGestionar(InterfazConcurso unaVentana) {

        ventana = unaVentana;

        setLayout(new BorderLayout());

        panelSuperior = new PanelSuperior(ventana);
        add(panelSuperior, BorderLayout.NORTH);

        panelCentral = new PanelCentral(ventana);
        add(panelCentral, BorderLayout.CENTER);

        panelInferior = new PanelInferior(ventana);
        add(panelInferior, BorderLayout.SOUTH);
    }

    public String darBuscar() {
        return panelSuperior.darBuscar();
    }
    
    public void limpiarBuscar(){
        panelSuperior.limpiarBuscar();
    }

    public void actualizarListaParticipantes(List<Participante> unosParticipantes) {
        panelCentral.limpiarTabla();
        for (Participante p : unosParticipantes) {
            panelCentral.actualizarListaParticipantes(p.toArray());
        }
    }
    
    public String darParticipanteSeleccionado(){
        return panelCentral.darParticipanteSeleccionado();
    }
    
    public void dialogoEditar(Participante unParticipante){
        
        dialogoEditar = new DialogoEditar(ventana);
        
        dialogoEditar.cambiarAlias(unParticipante.getAlias());
        dialogoEditar.cambiarNombre(unParticipante.getNombre());
        dialogoEditar.cambiarApellido(unParticipante.getApellido());
        dialogoEditar.cambiarCorreo(unParticipante.getEmail());
        dialogoEditar.cambiarCelular(unParticipante.getCelular());
        dialogoEditar.cambiarContrasena(unParticipante.getContrasena());
        dialogoEditar.cambiarFecha(unParticipante.getFehcaNacimiento().toString());
        
        dialogoEditar.setVisible(true);
        
    }
    
    public Participante darParticipanteEditado(){
        
        String alias = dialogoEditar.darAlias();
        String nombre = dialogoEditar.darNombre();
        String apellido = dialogoEditar.darApellido();
        String email = dialogoEditar.darCorreo();
        String celular = dialogoEditar.darCelular();
        String contrasena = dialogoEditar.darContrasena();
        String fechaNecimiento = dialogoEditar.darFecha();
        
        return new Participante(alias, nombre, apellido, email, celular, contrasena, new Date(Date.valueOf(fechaNecimiento).getTime()));
    
    }

}
