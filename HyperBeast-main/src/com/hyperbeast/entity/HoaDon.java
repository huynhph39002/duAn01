/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

/**
 *
 * @author Admin
 */
public class HoaDon {
    private int maHoaDon;
    private String ngayTao;
    private String ngayCapNhat;
    private String trangThai;
    private float tongTien;
    private int maKhachHang;
    private String tenNhanVien;
    String ghiChu;

    public HoaDon(int maHoaDon, String ngayTao, String ngayCapNhat, String trangThai, float tongTien, int maKhachHang, String tenNhanVien, String ghiChu) {
        this.maHoaDon = maHoaDon;
        this.ngayTao = ngayTao;
        this.ngayCapNhat = ngayCapNhat;
        this.trangThai = trangThai;
        this.tongTien = tongTien;
        this.maKhachHang = maKhachHang;
        this.tenNhanVien = tenNhanVien;
        this.ghiChu = ghiChu;
    }

    public HoaDon() {
    }

    
    
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(String ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public float getTongTien() {
        return tongTien;
    }

    public void setTongTien(float tongTien) {
        this.tongTien = tongTien;
    }

    public int getMaKhachHang() {
        return maKhachHang;
    }

    public void setMaKhachHang(int maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    
}
