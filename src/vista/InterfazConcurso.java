package vista;

import controlador.ParticipanteCtrl;
import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.sql.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import modelo.bo.Participante;
import vista.panelgestionar.PanelGestionar;
import vista.panelagregar.PanelAgregar;

/**
 *
 * @author YCA
 */
public class InterfazConcurso extends JFrame {

    public final static String EXPRESION_REGULAR_CORREO = "^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$";

    public final static String EXPRESION_REGULAR_CELULAR = "^3\\d{9}";

    private PanelAgregar panelAgregar;

    private PanelGestionar panelGestionar;

    private JTabbedPane panelPestanas;

    private Pattern pat;

    private Matcher mat;

    private ParticipanteCtrl participanteCtrl;

    public InterfazConcurso() throws HeadlessException {

        participanteCtrl = new ParticipanteCtrl();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Concurso");

        panelAgregar = new PanelAgregar(this);
        panelGestionar = new PanelGestionar(this);

        panelPestanas = new JTabbedPane();

        panelPestanas.addTab("Agregar participante", panelAgregar);
        panelPestanas.addTab("Gestionar participante", panelGestionar);

        add(panelPestanas, BorderLayout.CENTER);

        buscarTodosParticipantes();

        //setSize(200, 300);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void agregarParticipante() {

        String alias = panelAgregar.darAlias();
        String nombre = panelAgregar.darNombre();
        String apellido = panelAgregar.darApellido();
        String email = panelAgregar.darCorreo();
        String celular = panelAgregar.darCelular();
        String contrasena = panelAgregar.darContrasena();
        String fehcaNacimiento = panelAgregar.darFecha();

        if (alias.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() || celular.isEmpty() || contrasena.isEmpty() || fehcaNacimiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos estan vacíos", "Concurso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pat = Pattern.compile(EXPRESION_REGULAR_CORREO);
        mat = pat.matcher(email);

        if (!mat.matches()) {
            JOptionPane.showMessageDialog(this, "El formato de correo no es adecuado", "Concurso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pat = Pattern.compile(EXPRESION_REGULAR_CELULAR);
        mat = pat.matcher(celular);

        if (!mat.matches()) {
            JOptionPane.showMessageDialog(this, "El formato de número celular no es adecuado", "Concurso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Participante unParticipante = new Participante(alias, nombre, apellido, email, celular, contrasena, new Date(Date.valueOf(fehcaNacimiento).getTime()));

        if (participanteCtrl.guardar(unParticipante)) {
            JOptionPane.showMessageDialog(this, "Participante guardado", "Concurso", JOptionPane.INFORMATION_MESSAGE);
            panelAgregar.limpiarCampos();
            buscarTodosParticipantes();
        } else {
            JOptionPane.showMessageDialog(this, participanteCtrl.darMensaje(), "Concurso", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void buscarTodosParticipantes() {
        List<Participante> unosParticipantes = participanteCtrl.buscarTodos();
        panelGestionar.actualizarListaParticipantes(unosParticipantes);
        panelGestionar.limpiarBuscar();
    }

    public void buscarParticipante() {

        String aliasParticipante = panelGestionar.darBuscar();
        
        if(aliasParticipante.isEmpty()){
            JOptionPane.showMessageDialog(this, "El campo de búsqueda está vacío", "Concurso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Participante unParticipante = new Participante();
        unParticipante.setAlias(aliasParticipante);

        List<Participante> unosParticipantes = participanteCtrl.buscarTodos(unParticipante);
        panelGestionar.actualizarListaParticipantes(unosParticipantes);

    }

    public void dialogoEditar() {

        String aliasParticipante = panelGestionar.darParticipanteSeleccionado();
        Participante pAux = new Participante();
        pAux.setAlias(aliasParticipante);

        Participante unParticipante = participanteCtrl.buscarPorClave(pAux);

        panelGestionar.dialogoEditar(unParticipante);

    }

    public void actualizarParticipante() {

        Participante unParticipante = panelGestionar.darParticipanteEditado();

        if (unParticipante.getAlias().isEmpty() || unParticipante.getNombre().isEmpty() || unParticipante.getApellido().isEmpty() || unParticipante.getEmail().isEmpty() || unParticipante.getCelular().isEmpty() || unParticipante.getContrasena().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Algunos campos estan vacíos", "Concurso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        pat = Pattern.compile(EXPRESION_REGULAR_CORREO);
        mat = pat.matcher(unParticipante.getEmail());

        if (!mat.matches()) {
            JOptionPane.showMessageDialog(this, "El formato de correo no es adecuado", "Concurso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        pat = Pattern.compile(EXPRESION_REGULAR_CELULAR);
        mat = pat.matcher(unParticipante.getCelular());

        if (!mat.matches()) {
            JOptionPane.showMessageDialog(this, "El formato de número celular no es adecuado", "Concurso", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (participanteCtrl.actualizarParticipante(unParticipante)) {
            JOptionPane.showMessageDialog(this, "Participante " + unParticipante.getAlias() + " actualizado", "Concurso", JOptionPane.INFORMATION_MESSAGE);
            if (panelGestionar.darBuscar().length() == 0) {
                buscarTodosParticipantes();
            } else {
                buscarParticipante();
            }
        } else {
            JOptionPane.showMessageDialog(this, participanteCtrl.darMensaje(), "Concurso", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void eliminarParticipante() {

        String aliasParticipante = panelGestionar.darParticipanteSeleccionado();

        int opcion = JOptionPane.showConfirmDialog(this, "Seguro que quiere eliminar el participante " + aliasParticipante, "Concurso", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (opcion == JOptionPane.YES_OPTION) {
            Participante unParticipante = new Participante();
            unParticipante.setAlias(aliasParticipante);

            if (participanteCtrl.eliminar(unParticipante)) {
                JOptionPane.showMessageDialog(this, "Participante " + aliasParticipante + " eliminado", "Concurso", JOptionPane.INFORMATION_MESSAGE);
                if (panelGestionar.darBuscar().length() == 0) {
                    buscarTodosParticipantes();
                } else {
                    buscarParticipante();
                }
            } else {
                JOptionPane.showMessageDialog(this, participanteCtrl.darMensaje(), "Concurso", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
