/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.HoaDon;
import com.hyperbeast.entity.HoaDonChiTiet;
import com.hyperbeast.entity.SanPhamChiTiet;
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
public class hoaDonModel {
    public ArrayList<SanPhamChiTiet> getSanPhamCT (int pageSelect) {
        ArrayList<SanPhamChiTiet> listSPCT = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe\n" +
"                               where SAN_PHAM.TrangThai like N'Đang Kinh Doanh' and SoLuong > 0\n" +
"                               order by SAN_PHAM.MaSP\n" +
"                               offset ? row\n" +
"                               fetch next 5 ROWS ONLY";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pageSelect);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setMaSP(rs.getInt("MaSP"));
                spct.setMaCTSP(rs.getInt("MaCTSP"));
                spct.setTenSP(rs.getString("TenSP"));
                spct.setSoLuong(rs.getInt("SoLuong"));
                spct.setDonGia(rs.getFloat("DonGia"));
                spct.setTenMau(rs.getString("TenMau"));
                spct.setKichThuoc(rs.getInt("KichThuoc"));
                spct.setTenChatLieu(rs.getString("TenChatLieu"));
                spct.setTenChatLieuDe(rs.getString("TenChatLieuDe"));
                spct.setMaBarCode(rs.getString("MaBarCode"));
                spct.setTenAnh(rs.getString("TenAnh"));
                spct.setMoTa(rs.getString("MoTa"));
                listSPCT.add(spct);
            }
            return listSPCT;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getSizeCTSP () {
        ArrayList listMCTSP = new ArrayList<>();
        String query = "select * from CHI_TIET_SAN_PHAM\n" +
"			join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
                        "where SAN_PHAM.TrangThai like N'Đang Kinh Doanh'";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                int maCTSP = rs.getInt("MaCTSP");
                listMCTSP.add(maCTSP);
            }
            return listMCTSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getHoaDonTrangThai (String trangThai) {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String query = "select MaHD, HOA_DON.NgayTao,HOA_DON.TrangThai, HoTen from HOA_DON join TAI_KHOAN on TAI_KHOAN.MaTK = HOA_DON. MaTK\n" +
                        "Where HOA_DON.TrangThai like ? ";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ trangThai +"%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHD"));
                hoaDon.setNgayTao(rs.getString("NgayTao"));
                hoaDon.setTenNhanVien(rs.getString("HoTen"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public ArrayList getLichSuHoaDon () {
        ArrayList<HoaDon> listHoaDon = new ArrayList<>();
        String query = "select * from HOA_DON join TAI_KHOAN on HOA_DON.MaTK = TAI_KHOAN.MaTK";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                HoaDon hoaDon = new HoaDon();
                hoaDon.setMaHoaDon(rs.getInt("MaHD"));
                hoaDon.setNgayTao(rs.getString("NgayTao"));
                hoaDon.setTenNhanVien(rs.getString("HoTen"));
                hoaDon.setTrangThai(rs.getString("TrangThai"));
                hoaDon.setTongTien(rs.getFloat("TongTien"));
                hoaDon.setMaKhachHang(rs.getInt("MaTTKH"));
                //hoaDon.setGhiChu(rs.getString("GhiChu"));
                listHoaDon.add(hoaDon);
            }
            return listHoaDon;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public ArrayList getHDCT (int maHD) {
        ArrayList<HoaDonChiTiet> listHDCT = new ArrayList<>();
        String query = "select MaHDCT, MaHD, CHI_TIET_SAN_PHAM.MaCTSP, TenSP, HOA_DON_CHI_TIET.SoLuong, HOA_DON_CHI_TIET.DonGia from HOA_DON_CHI_TIET\n" +
"											join CHI_TIET_SAN_PHAM on CHI_TIET_SAN_PHAM.MaCTSP = HOA_DON_CHI_TIET.MaCTSP \n" +
"											join SAN_PHAM on SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"											where MaHD = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maHD);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setMaHDCT(rs.getInt("MaHDCT"));
                hdct.setMaHD(rs.getInt("MaHD"));
                hdct.setMaCTSP(rs.getInt("MaCTSP"));
                hdct.setTenSanPham(rs.getString("TenSP"));
                hdct.setSoLuong(rs.getInt("SoLuong"));
                hdct.setDonGia(rs.getFloat("DonGia"));
                listHDCT.add(hdct);
            }
            return listHDCT;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public int getSoLuongCT (int maCTSP) {
        int soLuong = 0;
        String query = "select SoLuong from CHI_TIET_SAN_PHAM where MaCTSP = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                soLuong = rs.getInt("SoLuong");
            }
            return soLuong;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public boolean insertHoaDonCho(String ngayTao, String ngayCapNhat, String trangThai, int maTaiKhoan) {
        String query = "INSERT INTO HOA_DON(NgayTao, NgayCapNhat, TrangThai, MaTK)" +
                        " VALUES (?, ?, ?, ?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ngayTao);
            pstmt.setString(2, ngayCapNhat);
            pstmt.setString(3, trangThai);
            pstmt.setInt(4, maTaiKhoan);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean insertThanhToan(int maHD,int maTKKH, String hinhThucThanhToan) {
        String query = "insert into THANH_TOAN (HinhThucThanhToan, MaTTKH, MaHD)\n" +
                        "values(?,?,?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, hinhThucThanhToan);
            pstmt.setInt(2, maTKKH);
            pstmt.setInt(3, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updateHoaDon(int maHD, String ngayCapNhat, String trangThai, int maTKKH, float tongTien) {
        String query = "update HOA_DON\n" +
                        "set NgayCapNhat = ?, TrangThai = ?, TongTien = ?, MaTTKH = ?\n" +
                        "where MaHD = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, ngayCapNhat);
            pstmt.setString(2, trangThai);
            pstmt.setFloat(3, tongTien);
            pstmt.setInt(4, maTKKH);
            pstmt.setInt(5, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    } 
    
    public boolean updateHuyHoaDon(int maHD, String trangThai, String ghiChu) {
        String query = "update HOA_DON\n" +
                        "set TrangThai = ?, GhiChu = ?\n" +
                        "where MaHD = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, trangThai);
            pstmt.setString(2, ghiChu);
            pstmt.setInt(3, maHD);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean insertHDCT(int maCTSP, int maHD, int soLuong, float donGia) {
        String query = "insert into HOA_DON_CHI_TIET(MaCTSP, MaHD, SoLuong, DonGia)\n" +
"                       values(?,?,?,?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            pstmt.setInt(2, maHD);
            pstmt.setInt(3, soLuong);
            pstmt.setFloat(4, donGia);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean updateCTSP(int maCTSP, int soLuong) {
        String query = "update CHI_TIET_SAN_PHAM\n" +
"                       set SoLuong = ? WHERE MaCTSP = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, soLuong);
            pstmt.setInt(2, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateHDCT(int maHD,int maCTSP,float donGia, int soLuong) {
        String query = "update HOA_DON_CHI_TIET\n" +
"                       set SoLuong = ?, DonGia = ? where MaHD = ? and MaCTSP = ? ";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, soLuong);
            pstmt.setFloat(2, donGia);
            pstmt.setInt(3, maHD);
            pstmt.setInt(4, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
    public boolean deleteHDCT(int maHDCT) {
        String query = "delete HOA_DON_CHI_TIET\n" +
"                       where MaHDCT = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maHDCT);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
}
