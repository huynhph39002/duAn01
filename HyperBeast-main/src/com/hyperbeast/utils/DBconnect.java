/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.utils;

import com.hyperbeast.entity.SanPham;
import com.hyperbeast.model.hoaDonModel;
import com.hyperbeast.model.sanPhamModel;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFileChooser;

/**
 *
 * @author Admin
 */
public class DBconnect {
    public static final String HOSTNAME = "localhost";
    public static final String PORT = "1433";
    public static final String DBNAME = "HyperBeastTest";
    public static final String USERNAME = "sa";
    public static final String PASSWORD = "123456";
    
    public static Connection getConnection() {

        String connectionUrl = "jdbc:sqlserver://" + HOSTNAME + ":" + PORT + ";"
                + "databaseName=" + DBNAME + ";encrypt=true;trustServerCertificate=true;";
        try {
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        } 
        catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
    public static void main(String[] args) {

//        ArrayList listMCTSP = new ArrayList<>();
//        String query = "select MaCTSP from CHI_TIET_SAN_PHAM Where MaSP = 11";
//        try {
//            Connection conn = DBconnect.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            ResultSet rs =  pstmt.executeQuery();
//            while (rs.next()) {                
//                int maSPCT = rs.getInt("MaCTSP");
//                listMCTSP.add(maSPCT);
//            }
//            System.out.println(listMCTSP.get(0));
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        ArrayList listmsp = new ArrayList<>();
//        ArrayList listmspct = new ArrayList<>();
//        sanPhamModel model = new sanPhamModel();
//        
//        listmsp = model.getMaSP();
//        listmspct = model.getMaCTSP();
//        
//        for (int i = 0; i < listmspct.size(); i++) {
//            int maspct = (int) listmspct.get(i);
//            if(maspct == 13) {
//                System.out.println(listmspct.get(i));
//            }
//        }
        
//        String query = "INSERT INTO SAN_PHAM( TenSP,  NgayNhap, NgayCapNhat, TrangThai, MaDM)" +
//                        "VALUES ('test', '12-07-2023','12-07-2023', N'đang kinh doanh', 2)";
//        try {
//            Connection conn = DBconnect.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.execute();
//            listsp = model.getSanPham();
//            for (SanPham sanPham : listsp) {
//                System.out.println(sanPham.getTenSP());
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
        //listsp = model.getMaSP();
//        int maSP = (int) listsp.get(listsp.size() - 1);
//        
//        String query = "INSERT INTO CHI_TIET_SAN_PHAM( MaSP,SoLuong, DonGia, MaMS, MaSize,MaCL,MaCLDe,MaBarCode)\n" +
//                        "VALUES (?,5,3669000 ,3,5,1,1,'0411230513669')";
//        try {
//            Connection conn = DBconnect.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, maSP);
//            pstmt.execute();
////            listsp = model.getSanPham();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
          
//            int maCTSP = (int) listsp.get(listsp.size() - 1);
//        
//        String query = "INSERT INTO ANH_SAN_PHAM(MaCTSP, TenAnh, TRANGTHAI)\n" +
//                        "VALUES(?,'Air_Jordan_1_Mid.png',1)";
//        try {
//            Connection conn = DBconnect.getConnection();
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setInt(1, maCTSP);
//            pstmt.execute();
////            listsp = model.getSanPham();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
    }
    
    
}
