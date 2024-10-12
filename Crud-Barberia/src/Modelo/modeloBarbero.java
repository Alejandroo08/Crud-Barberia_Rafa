/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Vista.FRMBarberia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gerst
 */
public class modeloBarbero {

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getNombreB() {
        return NombreB;
    }

    public void setNombreB(String NombreB) {
        this.NombreB = NombreB;
    }

    public int getEdadB() {
        return EdadB;
    }

    public void setEdadB(int EdadB) {
        this.EdadB = EdadB;
    }

    public Double getPesoB() {
        return PesoB;
    }

    public void setPesoB(Double PesoB) {
        this.PesoB = PesoB;
    }

    public String getCorreoB() {
        return CorreoB;
    }

    public void setCorreoB(String CorreoB) {
        this.CorreoB = CorreoB;
    }
    
    private String UUID;
    private String NombreB;
    private int EdadB;
    private Double PesoB;
    private String CorreoB;
    
    public void Guardar() {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();
        try {
            //Variable que contiene la Query a ejecutar
            String sql = "INSERT INTO tbBarbero values (?, ?, ?, ?, ?)";
            //Creamos el PreparedStatement que ejecutará la Query
            PreparedStatement pstmt = conexion.prepareStatement(sql);
            //Establecer valores de la consulta SQL
            pstmt.setString(1, getUUID());
            pstmt.setString(2, getNombreB());
            pstmt.setInt(3, getEdadB());
            pstmt.setDouble(4, getPesoB());
            pstmt.setString(5, getCorreoB());

            //Ejecutar la consulta
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("este es el error en el modelo:metodo guardar " + ex);
        }
    }
    
    public void Actualizar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        //obtenemos que fila seleccionó el usuario
        int filaSeleccionada = tabla.getSelectedRow();

        if (filaSeleccionada != -1) {
            //Obtenemos el id de la fila seleccionada
            String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();

            try {
                //Ejecutamos la Query
                String sql = "update tbBarbero set UUID_Barbero = ?, Nombre_Barbero = ?, Edad_Barbero = ?, Peso_Barbero = ?, Correo_Barbero = ? where UUID_Barbero = ?";
                PreparedStatement updateUser = conexion.prepareStatement(sql);

                updateUser.setString(1, getUUID());
                updateUser.setString(2, getNombreB());
                updateUser.setInt(3, getEdadB());
                updateUser.setDouble(4, getPesoB());
                updateUser.setString(5, getCorreoB());
                updateUser.setString(6, UUID);
                updateUser.executeUpdate();

            } catch (Exception e) {
                System.out.println("este es el error en el metodo de actualizar" + e);
            }
        } else {
            System.out.println("no se pudo actualizar");
        }
    }
    
     public void cargarDatos(FRMBarberia vista) {
        // Obtén la fila seleccionada 
        int filaSeleccionada = vista.jtbBarbero.getSelectedRow();

        // Debemos asegurarnos que haya una fila seleccionada antes de acceder a sus valores
        if (filaSeleccionada != -1) {
            String UUID = vista.jtbBarbero.getValueAt(filaSeleccionada, 0).toString();
            String NomBombero = vista.jtbBarbero.getValueAt(filaSeleccionada, 1).toString();
            String EdadBombero = vista.jtbBarbero.getValueAt(filaSeleccionada, 2).toString();
            String PesoBombero = vista.jtbBarbero.getValueAt(filaSeleccionada, 3).toString();
            String CorreoBombero = vista.jtbBarbero.getValueAt(filaSeleccionada, 4).toString();
            

            // Establece los valores en los campos de texto
            vista.txtUUID.setText(UUID);
            vista.txtNombre.setText(NomBombero);
            vista.txtEdad.setText(EdadBombero);
            vista.txtPeso.setText(PesoBombero);
            vista.txtCorreo.setText(CorreoBombero);
            
        }
    }
     
    public int Eliminar(JTable tabla) {
        //Creamos una variable igual a ejecutar el método de la clase de conexión
        Connection conexion = ClaseConexion.getConexion();

        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada != -1) {
            //obtenemos que fila seleccionó el usuario
            String UUID = tabla.getValueAt(filaSeleccionada, 0).toString();
            //Obtenemos el id de la fila seleccionada


            //borramos 
            try {
                String sql = "delete from tbBarbero where UUID_Barbero = ?";
                PreparedStatement deleteEstudiante = conexion.prepareStatement(sql);
                deleteEstudiante.setString(1, UUID);
                int respuesta = deleteEstudiante.executeUpdate();
                return respuesta;
            } catch (Exception e) {
                System.out.println("este es el error metodo de eliminar" + e);
                return 0;
            }
        }
        else {
            System.out.println("no");
            return -1;
        }
    } 
    
    public void Mostrar(JTable tabla){
        Connection conexion = ClaseConexion.getConexion();
        //Definimos el modelo de la tabla
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.setColumnIdentifiers(new Object[]{"UUID", "Nombre Barbero", "Edad Barbero", "Peso Barbero", "Correo Barbero"});
        
        try
        {
            String query = "Select * from tbBarbero";
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                //Llenamos el modelo por cada vez que recorremos el resultSet
                modelo.addRow(new Object[]{
                    rs.getString("UUID_Barbero"), 
                    rs.getString("Nombre_Barbero"), 
                    rs.getInt("Edad_Barbero"), 
                    rs.getDouble("Peso_Barbero"),
                    rs.getString("Correo_Barbero")
                }   
                );
                
            }
            //tabla.getColumnModel().getColumn(10).setCellRenderer(new TableActionCellRender());
            tabla.setModel(modelo);
            tabla.getColumnModel().getColumn(0).setMinWidth(0);
            tabla.getColumnModel().getColumn(0).setMaxWidth(0);
            tabla.getColumnModel().getColumn(0).setWidth(0);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
        
    }
    public void limpiar(FRMBarberia vista) {
        vista.txtUUID.setText("");
        vista.txtNombre.setText("");
        vista.txtEdad.setText("");
        vista.txtPeso.setText("");
        vista.txtCorreo.setText("");
    }
}
