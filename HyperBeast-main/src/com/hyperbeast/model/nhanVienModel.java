/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.nhanVien;
import com.hyperbeast.utils.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class nhanVienModel {
    
    public ArrayList<nhanVien> getNhanVien (String tenDangNhap) {
        String query = "select * from TAI_KHOAN where TenDangNhap = ?";
        ArrayList<nhanVien> listNV = new ArrayList<>();
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenDangNhap);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {   
                nhanVien nv = new nhanVien();
                nv.setMaNV(rs.getInt("MaTK"));
                nv.setTenDangNhap(rs.getString("TenDangNhap"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setGioiTinh(rs.getBoolean("GioiTinh"));
                nv.setDienThoai(rs.getString("DienThoai"));
                nv.setEmail(rs.getString("Email"));
                nv.setDiaChi(rs.getString("DiaChi"));
                nv.setNgayTao(rs.getString("NgayTao"));
                nv.setNgayCN(rs.getString("NgayCapNhat"));
                nv.setTrangThai(rs.getString("TrangThai"));
                listNV.add(nv);
            }
            return listNV;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
}
