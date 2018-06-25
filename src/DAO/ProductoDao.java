/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Conexion.Conexion;
import Interface.Metodos;
import Modelo.Producto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LN710Q
 */
public class ProductoDao implements Metodos<Producto>{
    
    private static final String SQL_INSERT = "INSERT INTO productos (nombre, codigo, tipo, cantidad, precio, disponibilidad) VALUES (?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "UPDATE productos SET nombre = ? codigo = ?, tipo=?, cantidad=? , precio=? , disponibilidad=? WHERE id=?";
    private static final String SQL_DELETE = "DELETE FROM productos WHERE id=?";

    private static final String SQL_READ = "SELECT * FROM productos WHERE id=?";
    private static final String SQL_READALL = "SELECT * FROM productos ";
    private static final Conexion con = Conexion.conectar();
    
    @Override
    public boolean create(Producto g) {
        PreparedStatement ps;
        try {
            ps = con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getNombre());
            ps.setString(2, g.getCodigo());
            ps.setString(3, g.getTipo());
            ps.setInt(4, g.getCantidad());
            ps.setDouble(5, g.getPrecio());
            ps.setBoolean(6, g.isDisponibilidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {

        try {
            PreparedStatement ps;
            ps = con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1, key.toString());

            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Producto c) {
        try {
            PreparedStatement ps;
            System.out.println(c.getCodigo());
            ps = con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCodigo());
            ps.setString(3, c.getTipo());
            ps.setInt(4, c.getCantidad());
            ps.setDouble(5, c.getPrecio());
            ps.setBoolean(6, c.isDisponibilidad());
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return false;

    }

    @Override
    public Producto read(Object key) {

        Producto p = null;
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = con.getCnx().prepareStatement(SQL_READ);
            ps.setString(1, key.toString());
            rs = ps.executeQuery();
            while (rs.next()){
                p = new Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getBoolean(6));
            }
            rs.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return p;
    }

    @Override
    public ArrayList<Producto> readAll() {

        ArrayList<Producto> all = new ArrayList();
        Statement s;
        ResultSet rs;
        try {
            s = con.getCnx().prepareStatement(SQL_READALL);
            rs = s.executeQuery(SQL_READALL);
            while (rs.next()) {
                all.add(new Producto(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), rs.getBoolean(6)));
            }
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return all;
    }
}
