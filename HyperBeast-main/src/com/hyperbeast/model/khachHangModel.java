/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.KhachHang;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class khachHangModel {
    public ArrayList getKhachHang () {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String query = "select * from THONG_TIN_KH";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaCTKH(rs.getInt("MaTTKH"));
                    kh.setTenKH(rs.getString("TenKH"));
                    kh.setSoDienThoai(rs.getString("SDT"));
                    kh.setNgayTao(rs.getDate("NgayTao"));
                    kh.setNgayCN(rs.getDate("NgayCN"));
                    kh.setTrangThai(rs.getInt("TrangThai"));
                    listKH.add(kh);
            }
            return listKH;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getKhachHangTheoHD (int maHD) {
        ArrayList<KhachHang> listKH = new ArrayList<>();
        String query = "select * from THONG_TIN_KH join HOA_DON ON HOA_DON.MaTTKH = THONG_TIN_KH.MaTTKH where MaHD = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                    KhachHang kh = new KhachHang();
                    kh.setMaCTKH(rs.getInt("MaTTKH"));
                    kh.setTenKH(rs.getString("TenKH"));
                    kh.setSoDienThoai(rs.getString("SDT"));
                    kh.setNgayTao(rs.getDate("NgayTao"));
                    kh.setNgayCN(rs.getDate("NgayCN"));
                    kh.setTrangThai(rs.getInt("TrangThai"));
                    listKH.add(kh);
            }
            return listKH;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
}
