/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.model;

import com.hyperbeast.entity.SanPham;
import com.hyperbeast.entity.SanPhamChiTiet;
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
public class sanPhamModel {
    
    public ArrayList getMaMS () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from MAU_SAC";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaMS");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList getMaKT () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from SIZE";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaSIZE");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList getMaDM () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from DANH_MUC";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaDM");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList getMaCL () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from CHAT_LIEU";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaCL");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList getMaCLD () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from CHAT_LIEU_DE_GIAY";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaCLDe");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getMaSP () {
        ArrayList listMSP = new ArrayList<>();
        String query = "select * from SAN_PHAM";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                int maSP = rs.getInt("MaSP");
                listMSP.add(maSP);
            }
            return listMSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getMaCTSP (String tenSanPham) {
        ArrayList listMCTSP = new ArrayList<>();
        String query = "select * from CHI_TIET_SAN_PHAM join SAN_PHAM on SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP WHERE TenSP like ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ tenSanPham +"%");
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
    public ArrayList getMaCTSP2 (int maSP) {
        ArrayList listMCTSP = new ArrayList<>();
        String query = "select MaCTSP from CHI_TIET_SAN_PHAM Where MaSP = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maSP);
            ResultSet rs =  pstmt.executeQuery();
            while (rs.next()) {                
                int maSPCT = rs.getInt("MaCTSP");
                listMCTSP.add(maSPCT);
            }
            return listMCTSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<SanPham> getSanPham2 () {
        ArrayList<SanPham> listSP = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP, TenSP, NgayNhap, SAN_PHAM.TrangThai, NgayCapNhat, TenDanhMuc from SAN_PHAM " +
                "join DANH_MUC on DANH_MUC.MaDM = SAN_PHAM.MaDM\n";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setNgayCN(rs.getString("NgayCapNhat"));
                sp.setTrangThai(rs.getString("TrangThai"));
                sp.setTenDanhMuc(rs.getString("TenDanhMuc"));
                listSP.add(sp);
            }
            return listSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<SanPham> getSanPham (int pageSelect) {
        ArrayList<SanPham> listSP = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP, TenSP, NgayNhap, SAN_PHAM.TrangThai, NgayCapNhat, TenDanhMuc from SAN_PHAM " +
                "join DANH_MUC on DANH_MUC.MaDM = SAN_PHAM.MaDM\n"
                +"order by SAN_PHAM.MaSP\n" +
                "offset ? row\n" +
                "fetch next 5 ROWS ONLY";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, pageSelect);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {                
                SanPham sp = new SanPham();
                sp.setMaSP(rs.getInt("MaSP"));
                sp.setTenSP(rs.getString("TenSP"));
                sp.setNgayNhap(rs.getString("NgayNhap"));
                sp.setNgayCN(rs.getString("NgayCapNhat"));
                sp.setTrangThai(rs.getString("TrangThai"));
                sp.setTenDanhMuc(rs.getString("TenDanhMuc"));
                listSP.add(sp);
            }
            return listSP;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList<SanPhamChiTiet> getSanPhamCT2 ( String tenSanPham) {
        ArrayList<SanPhamChiTiet> listSPCT = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe\n" + 
                                "Where tenSP like ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ tenSanPham +"%");
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
    
    public ArrayList<SanPhamChiTiet> getSPCTAll () {
        ArrayList<SanPhamChiTiet> listSPCT = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
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
    
    public ArrayList<SanPhamChiTiet> getSanPhamCT (int pageSelect, String tenSanPham) {
        ArrayList<SanPhamChiTiet> listSPCT = new ArrayList<>();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe\n" +
"                               Where tenSP like ?\n" +
"                               order by SAN_PHAM.MaSP\n" +
"                               offset ? row\n" +
"                               fetch next 5 ROWS ONLY";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ tenSanPham +"%");
            pstmt.setInt(2, pageSelect);
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
    
    public int getSoluongCTSP (int maCTSP) {
        int soLuong = 0;
        String query = "select SoLuong from CHI_TIET_SAN_PHAM Where MaCTSP = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            ResultSet rs =  pstmt.executeQuery();
            while (rs.next()) {                
                soLuong = rs.getInt("SoLuong");
            }
            return soLuong;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        }
    }
    
    public ArrayList getDanhMuc () {
        ArrayList listDM = new ArrayList<>();
        String query = "select * from DANH_MUC";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {                
                if(rs.getInt("TrangThai") == 1) {
                    String tenDanhMuc = rs.getString("TenDanhMuc");
                    listDM.add(tenDanhMuc);
                }
            }
            return listDM;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    public ArrayList getMauSac () {
        ArrayList listMS = new ArrayList<>();
        String query = "select * from MAU_SAC";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {  
                if(rs.getInt("TrangThai") == 1) {
                    String tenMau = rs.getString("TenMau");
                    listMS.add(tenMau);
                }
                
            }
            return listMS;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getChatLieu () {
        ArrayList listCL = new ArrayList<>();
        String query = "select * from CHAT_LIEU";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {   
                if(rs.getInt("TrangThai") == 1) {
                    String tenChatLieu = rs.getString("TenChatLieu");
                    listCL.add(tenChatLieu);
                }
                
            }
            return listCL;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getChatLieuDe () {
        ArrayList listCLD = new ArrayList<>();
        String query = "select * from CHAT_LIEU_DE_GIAY";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {     
                if(rs.getInt("TrangThai") == 1) {
                    String tenChatLieuDe = rs.getString("TenChatLieuDe");
                    listCLD.add(tenChatLieuDe);
                }
                
            }
            return listCLD;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public ArrayList getKichThuoc () {
        ArrayList listKT = new ArrayList<>();
        String query = "select * from SIZE";
        try {
            Connection conn = DBconnect.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {   
                if(rs.getInt("TrangThai") == 1) {
                    String kichThuoc = rs.getString("KichThuoc");
                    listKT.add(kichThuoc);
                }
                
            }
            return listKT;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    
    
    
    public ArrayList searchSanPham (String tenSP, String trangThai, String tenDanhMuc) {
        ArrayList listTimKiem = new ArrayList();
        String query = "select SAN_PHAM.MaSP, TenSP, NgayNhap, SAN_PHAM.TrangThai,SAN_PHAM.MaDM, TenDanhMuc from SAN_PHAM join CHI_TIET_SAN_PHAM on SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"				join DANH_MUC on DANH_MUC.MaDM = SAN_PHAM.MaDM\n" +
"				where TenSP like ? and SAN_PHAM.TrangThai like ? and TenDanhMuc like ? ";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ tenSP +"%");
            pstmt.setString(2, "%"+ trangThai +"%");
            pstmt.setString(3, "%"+ tenDanhMuc +"%");
            ResultSet  rs = pstmt.executeQuery();
            while (rs.next()) {                
                int maSP = rs.getInt("MaSP");
                listTimKiem.add(maSP);
            }
            return listTimKiem;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    public ArrayList searchSanPhamCT (String tenSP, String tenMau, String kichThuoc, String tenChatLieu, String tenChatLieuDe) {
        ArrayList<SanPhamChiTiet> listTimKiem = new ArrayList();
        String query = "select SAN_PHAM.MaSP,SAN_PHAM.TenSP,CHI_TIET_SAN_PHAM.MaCTSP, SoLuong, DonGia, TenMau, KichThuoc, TenChatLieu, TenChatLieuDe,MaBarCode, TenAnh, MoTa  from CHI_TIET_SAN_PHAM join MAU_SAC on MAU_SAC.MaMS = CHI_TIET_SAN_PHAM.MaMS\n" +
"				join SAN_PHAM ON SAN_PHAM.MaSP = CHI_TIET_SAN_PHAM.MaSP\n" +
"                               join SIZE on SIZE.MaSize = CHI_TIET_SAN_PHAM.MaSize\n" +
"				join CHAT_LIEU on CHAT_LIEU.MaCL = CHI_TIET_SAN_PHAM.MaCL\n" +
"				join CHAT_LIEU_DE_GIAY on CHAT_LIEU_DE_GIAY.MaCLDe = CHI_TIET_SAN_PHAM.MaCLDe\n" +
"				where tenSP like ? and TenMau like ? and KichThuoc like ? and TenChatLieu like ? and TenChatLieuDe like ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%"+ tenSP +"%");
            pstmt.setString(2, "%"+ tenMau +"%");
            pstmt.setString(3, "%"+ kichThuoc +"%");
            pstmt.setString(4, "%"+ tenChatLieu +"%");
            pstmt.setString(5, "%"+ tenChatLieuDe +"%");
            ResultSet  rs = pstmt.executeQuery();
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
                listTimKiem.add(spct);
            }
            return listTimKiem;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        }
    }
    
    public boolean insertSanPham(String tenSP, String ngayNhap, String ngayCN, String trangThai, int maDM) {
        String query = "INSERT INTO SAN_PHAM( TenSP,  NgayNhap, NgayCapNhat, TrangThai, MaDM)" +
                        " VALUES (?, ?,?, ?, ?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenSP);
            pstmt.setString(2, ngayNhap);
            pstmt.setString(3, ngayCN);
            pstmt.setString(4, trangThai);
            pstmt.setInt(5, maDM);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean insertSanPhamCT(int maSP, int soLuong, float donGia, int maMS, int maSIZE, int maCL, int maCLD, String maBarCode, String tenAnh, String moTa) {
        String query = "INSERT INTO CHI_TIET_SAN_PHAM( MaSP,SoLuong, DonGia, MaMS, MaSize,MaCL,MaCLDe,MaBarCode, tenAnh, MoTa)" +
                        " VALUES (?,?,? ,?,?,?,?,?,?,?)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maSP);
            pstmt.setInt(2, soLuong);
            pstmt.setFloat(3, donGia);
            pstmt.setInt(4, maMS);
            pstmt.setInt(5, maSIZE);
            pstmt.setInt(6, maCL);
            pstmt.setInt(7, maCLD);
            pstmt.setString(8, maBarCode);
            pstmt.setString(9, tenAnh);
            pstmt.setString(10, moTa);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean insertAnhSP(int maCTSP, String tenAnh) {
         String query = "INSERT INTO ANH_SAN_PHAM(MaCTSP, TenAnh, TRANGTHAI)" +
                        " VALUES(?,?, 1)";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, maCTSP);
            pstmt.setString(2, tenAnh);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateSanPham(int maSP, String tenSP, String ngayNhap, String ngayCN, String trangThai, int maDM) {
         String query = "UPDATE SAN_PHAM\n" +
                        "SET TenSP = ?, NgayNhap = ?, NgayCapNhat = ?, TrangThai = ?, MaDM = ?\n" +
                        "WHERE MaSP = ?;";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenSP);
            pstmt.setString(2, ngayNhap);
            pstmt.setString(3, ngayCN);
            pstmt.setString(4, trangThai);
            pstmt.setInt(5, maDM);
            pstmt.setInt(6, maSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateSPCT(int maCTSP, int soLuong, float donGia, int maMS, int maSIZE, int maCL, int maCLD, String maBarCode, String tenAnh, String moTa) {
         String query = "UPDATE CHI_TIET_SAN_PHAM\n" +
                        "SET DonGia = ?, SoLuong = ?, MaCLDe = ?, MaSize = ?,MaMS= ?, MaCL = ?, MaBarCode = ?, TenAnh = ?, MoTa = ?\n" +
                        "WHERE MaCTSP = ?;";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setFloat(1, donGia);
            pstmt.setInt(2, soLuong);
            pstmt.setInt(3, maCLD);
            pstmt.setInt(4, maSIZE);
            pstmt.setInt(5, maMS);
            pstmt.setInt(6, maCL);
            pstmt.setString(7, maBarCode);
            pstmt.setString(8, tenAnh);
            pstmt.setString(9, moTa);
            pstmt.setInt(10, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    public boolean updateAnhSP(int maCTSP, String tenAnh) {
         String query = "UPDATE ANH_SAN_PHAM\n" +
                        "SET TenAnh = ?, TRANGTHAI = 1 \n" +
                        "WHERE MaCTSP = ?;";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, tenAnh);
            pstmt.setInt(2, maCTSP);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }
    }
    
}
