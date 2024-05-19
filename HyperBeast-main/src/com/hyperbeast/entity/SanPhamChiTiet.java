/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

/**
 *
 * @author Admin
 */
public class SanPhamChiTiet {
    int maSP;
    int maCTSP;
    String tenSP;
    int soLuong;
    float donGia;
    String tenMau;
    int kichThuoc;
    String tenChatLieu;
    String tenChatLieuDe;
    String maBarCode;
    String moTa;
    String tenAnh;

    public SanPhamChiTiet(int maSP, int maCTSP, String tenSP, int soLuong, float donGia, String tenMau, int kichThuoc, String tenChatLieu, String tenChatLieuDe, String maBarCode, String moTa, String tenAnh) {
        this.maSP = maSP;
        this.maCTSP = maCTSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.tenMau = tenMau;
        this.kichThuoc = kichThuoc;
        this.tenChatLieu = tenChatLieu;
        this.tenChatLieuDe = tenChatLieuDe;
        this.maBarCode = maBarCode;
        this.moTa = moTa;
        this.tenAnh = tenAnh;
    }

    public int getMaCTSP() {
        return maCTSP;
    }

    public void setMaCTSP(int maCTSP) {
        this.maCTSP = maCTSP;
    }

    

    

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    
    public SanPhamChiTiet() {
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public int getKichThuoc() {
        return kichThuoc;
    }

    public void setKichThuoc(int kichThuoc) {
        this.kichThuoc = kichThuoc;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
    }

    public String getTenChatLieuDe() {
        return tenChatLieuDe;
    }

    public void setTenChatLieuDe(String tenChatLieuDe) {
        this.tenChatLieuDe = tenChatLieuDe;
    }

    public String getMaBarCode() {
        return maBarCode;
    }

    public void setMaBarCode(String maBarCode) {
        this.maBarCode = maBarCode;
    }

    public String getTenAnh() {
        return tenAnh;
    }

    public void setTenAnh(String tenAnh) {
        this.tenAnh = tenAnh;
    }
    
    
}
