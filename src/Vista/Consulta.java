/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.ProductoDao;
import Modelo.Producto;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class Consulta extends JFrame{
    
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
        super("Productos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        Labels();
        Formulario();
        llenarTabla();
        Container container = getContentPane();
        container.add(lblNombre);
        container.add(lblCodigo);
        container.add(lblPrecio);
        container.add(lblCantidad);
        container.add(lblDisp);
        container.add(lblTipo);
        container.add(txtNombre);
        container.add(txtCodigo);
        container.add(txtPrecio);
        container.add(txtCantidad);
        container.add(cobTipo);
        container.add(checkD1);
        container.add(checkD2);
        container.add(tabla);
        container.add(Actualizar);
        container.add(Eliminar);
        container.add(Limpiar);
        container.add(Insertar);
        container.add(Buscar);
        setSize(720, 400);
        
    }
    
    public void Labels(){
        lblNombre = new JLabel("Nombre:");
        lblCodigo = new JLabel("Codigo:");
        lblPrecio = new JLabel("Precio:");
        lblCantidad = new JLabel("Cantidad:");
        lblDisp = new JLabel("Disponibilidad:");
        lblTipo = new JLabel("Tipo:");
        
        lblNombre.setBounds(10, 15, 50, HEIGHT);
        lblCodigo.setBounds(10, 45, 50, HEIGHT);
        lblPrecio.setBounds(10, 75, 50, HEIGHT);
        lblCantidad.setBounds(10, 105, 60, HEIGHT);
        lblDisp.setBounds(10, 135, 90, HEIGHT);
        lblTipo.setBounds(10, 165, 50, HEIGHT);
        
    }
    
    public void Formulario(){
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
                    case 3:
                        return int.class;
                    case 4:
                        return double.class;
                    case 5:
                        return boolean.class;
                }
                return null;
            }
        };

        tm.addColumn("Nombre");
        tm.addColumn("Codigo");
        tm.addColumn("Precio");
        tm.addColumn("Cantidad");
        tm.addColumn("Disponibilidad");
        tm.addColumn("Tipo");

        ProductoDao fd = new ProductoDao();
        ArrayList<Producto> productos = fd.readAll();

        for (Producto fi : productos) {
            tm.addRow(new Object[]{fi.getNombre(), fi.getCodigo(), fi.getTipo(), fi.getCantidad(), fi.getPrecio(), fi.isDisponibilidad()});
        }
        Results.setModel(tm);
    }
    
    public void eventos(){
        Insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ProductoDao fd = new ProductoDao();
                Producto f = new Producto(txtNombre.getText(), txtCodigo.getText(), cobTipo.getSelectedItem().toString(), Integer.parseInt(txtCantidad.getText()), ));
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crar el filtro");
                    
                }
            }        

            @Override
            public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        Actualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = new Filtro(codigo.getText(),marca.getSelectedItem().toString(),
                        Integer.parseInt(stock.getText()),true);
                if(no.isSelected()){
                    f.setExistencia(false);
                }
                if(fd.create(f)){
                    JOptionPane.showMessageDialog(null,"Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crar el filtro");
                    
                }
            } 
        });
        Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                if(fd.delete(codigo.getText())){
                    JOptionPane.showMessageDialog(null,"Filtro registrado con exito");
                    limpiarCampos();
                    llenarTabla();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ocurrio un problema al momento de crar el filtro");
                    
                }
            } 
        });
        Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltroDao fd = new FiltroDao();
                Filtro f = fd.read(codigo.getText());
                if(f == null){
                    JOptionPane.showMessageDialog(null,"Filtro registrado con exito");
                }
                else{
                    codigo.setText(f.getCodigo());
                    marca.setSelectedItem(f.getMarca());
                    stock.setText(Integer.toString(f.getStock()));
                    if(f.getExistencia()){
                        si.setSelected(true);
                    }
                    else{
                        no.setSelected(true);
                    }
                }
            } 
        });
        Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiarCampos();
            } 
        });
    }
    
    public void limpiarCampos(){
        codigo.setText("");
        marca.setSelectedItem("FRAM");
        stock.setText("");
    }
}
