/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.modeloBarbero;
import Vista.FRMBarberia;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author gerst
 */
public class ControladorBarberia implements MouseListener {
    
    private modeloBarbero modelo;
    private FRMBarberia vista;
    
    public ControladorBarberia(modeloBarbero modelo, FRMBarberia vista)
    {
        this.modelo = modelo;
        this.vista = vista;
        
        vista.btnAgregar.addMouseListener(this);
        vista.btnActualizar.addMouseListener(this);
        vista.btnEliminar.addMouseListener(this);
        vista.jtbBarbero.addMouseListener(this);
        modelo.Mostrar(vista.jtbBarbero);
        
       
        
        
    } 

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getSource() == vista.btnAgregar) {
            if (vista.txtUUID.getText().isEmpty() ||vista.txtNombre.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtEdad.getText().isEmpty() 
                    || vista.txtCorreo.getText().isEmpty()) 
            {

                JOptionPane.showMessageDialog(vista, "Debes llenar todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo
                    modelo.setUUID(vista.txtUUID.getText());
                    modelo.setNombreB(vista.txtNombre.getText());
                    int edad = Integer.parseInt(vista.txtEdad.getText());
                    modelo.setEdadB(edad);
                    Double peso = Double.parseDouble(vista.txtPeso.getText());
                    modelo.setPesoB(peso);
                    modelo.setCorreoB(vista.txtCorreo.getText());
                    
                    //Ejecutar el metodo 
                    modelo.Guardar();
                    modelo.Mostrar(vista.jtbBarbero);
                    modelo.limpiar(vista);
                                        
                     JOptionPane.showMessageDialog(vista, "Los datos han sido registrados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudo completar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource() == vista.btnActualizar) {
            if (vista.txtUUID.getText().isEmpty() || vista.txtNombre.getText().isEmpty() || vista.txtPeso.getText().isEmpty() || vista.txtEdad.getText().isEmpty() 
                    || vista.txtCorreo.getText().isEmpty()) {
                JOptionPane.showMessageDialog(vista, "Debes seleccionar un registro para actualizar", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    //Asignar lo de la vista al modelo al momento de darle clic a actualizar
                    modelo.setUUID(vista.txtUUID.getText());
                    modelo.setNombreB(vista.txtNombre.getText());
                    int edad = Integer.parseInt(vista.txtEdad.getText());
                    modelo.setEdadB(edad);
                    Double peso = Double.parseDouble(vista.txtPeso.getText());
                    modelo.setPesoB(peso);
                    modelo.setCorreoB(vista.txtCorreo.getText());

                    //Ejecutar el método    
                    modelo.Actualizar(vista.jtbBarbero);
                    modelo.Mostrar(vista.jtbBarbero);
                    modelo.limpiar(vista);
                    JOptionPane.showMessageDialog(vista, "Los datos han sido actualizados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(vista, "No se pudieron actualizar los datos", "Error", JOptionPane.WARNING_MESSAGE);
                }
            }
        }
        if (e.getSource() == vista.btnEliminar) {
            try{
                int valorRetornado = modelo.Eliminar(vista.jtbBarbero);;

                        if(valorRetornado == 1){
                            JOptionPane.showMessageDialog(vista, "Los datos han sido eliminados exitosamente", "Proceso completado", JOptionPane.INFORMATION_MESSAGE);
                            modelo.Mostrar(vista.jtbBarbero);
                            modelo.limpiar(vista);
                        }
            }
            catch(Exception ex){
                JOptionPane.showMessageDialog(vista, "No se pudo eliminar el registro.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        }
        if (e.getSource() == vista.jtbBarbero) {
            modelo.cargarDatos(vista);
        }        
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    
}
