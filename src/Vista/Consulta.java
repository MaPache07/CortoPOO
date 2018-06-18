/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.FiltroDao;
import Modelo.Filtro;
import java.awt.Container;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LN710Q
 */
public class Consulta extends JPanel{
    
    public JLabel lblNombre, lblCodigo, lblPrecio, lblCantidad, lblDisp, lblTipo;
    public JTextField txtNombre, txtCodigo, txtPrecio, txtCantidad;
    public JComboBox cobTipo;
    public JCheckBox checkD1, checkD2;
    public JPanel tabla;
    public JTable Results;
    public JButton Actualizar, Eliminar, Limpiar, Insertar, Buscar;
    DefaultTableModel tm;
    
    private static final int WIDTH = 100, HEIGHT = 20;
    
    public Consulta(){
        setLayout(null);
        setSize(720, 400);
        setPreferredSize(new Dimension(620, 400));
        Labels();
        add(lblNombre);
        add(lblCodigo);
        add(lblPrecio);
        add(lblCantidad);
        add(lblDisp);
        add(lblTipo);
        add(txtNombre);
        add(txtCodigo);
        add(txtPrecio);
        add(txtCantidad);
        add(cobTipo);
        add(checkD1);
        add(checkD2);
        add(tabla);
        add(Actualizar);
        add(Eliminar);
        add(Limpiar);
        add(Insertar);
        add(Buscar);
    }
    
    public void Labels(){
        lblNombre = new JLabel("Nombre:");
        lblCodigo = new JLabel("Código:");
        lblPrecio = new JLabel("Precio:");
        lblCantidad = new JLabel("Cantidad:");
        lblDisp = new JLabel("Disponibilidad:");
        lblTipo = new JLabel("Tipo:");
        txtNombre = new JTextField();
        txtCodigo = new JTextField();
        txtPrecio = new JTextField();
        txtCantidad = new JTextField();
        cobTipo = new JComboBox();
        checkD1 = new JCheckBox();
        checkD2 = new JCheckBox();
        tabla = new JPanel();
        Results = new JTable();
        Actualizar = new JButton("Actualizar");
        Eliminar = new JButton("Eliminar");
        Limpiar = new JButton("Limpiar");
        Insertar = new JButton("Insertar");
        Buscar = new JButton("Buscar");
        tabla.add(new JScrollPane(Results));
        
        lblNombre.setBounds(10, 15, 50, HEIGHT);
        lblCodigo.setBounds(10, 45, 50, HEIGHT);
        lblPrecio.setBounds(10, 75, 50, HEIGHT);
        lblCantidad.setBounds(10, 105, 60, HEIGHT);
        lblDisp.setBounds(10, 135, 90, HEIGHT);
        lblTipo.setBounds(10, 165, 50, HEIGHT);
        txtNombre.setBounds(70, 15, WIDTH, HEIGHT);
        txtCodigo.setBounds(70, 45, WIDTH, HEIGHT);
        txtPrecio.setBounds(70, 75, WIDTH, HEIGHT);
        txtCantidad.setBounds(70, 105, WIDTH, HEIGHT);
        cobTipo.setBounds(70, 165, WIDTH, HEIGHT);
        checkD1.setBounds(100, 135, 20, HEIGHT);
        checkD2.setBounds(135, 135, 20, HEIGHT);
        tabla.setBounds(10, 195, 600, 200);
        Actualizar.setBounds(180, 165, WIDTH, HEIGHT);
        Eliminar.setBounds(290, 165, WIDTH, HEIGHT);
        Limpiar.setBounds(400, 165, WIDTH, HEIGHT);
        Insertar.setBounds(510, 165, WIDTH, HEIGHT);
        Buscar.setBounds(180, 15, WIDTH, HEIGHT);
    }
    
    private void llenarTabla() {
        tm = new DefaultTableModel() {
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return String.class;
                    case 1:
                        return String.class;
                    case 2:
                        return String.class;
                    default:
                        return Boolean.class;
                }
            }
        };

        tm.addColumn("Codigo");
        tm.addColumn("Marca");
        tm.addColumn("Stock");
        tm.addColumn("Stock en Sucursal");

        FiltroDao fd = new FiltroDao();
        ArrayList<Filtro> filtros = fd.readAll();

        for (Filtro fi : filtros) {
            tm.addRow(new Object[]{fi.getNombre(), fi.getCant(), fi.getTipo(), fi.getPrecio()});
        }

        Results.setModel(tm);
    }
}
