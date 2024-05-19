/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

/**
 *
 * @author Admin
 */
public class SanPham {
    int maSP;
    String tenSP;
    String ngayNhap;
    String ngayCN;
    String trangThai;
    String tenDanhMuc;

    public SanPham(int maSP, String tenSP, String ngayNhap, String ngayCN, String trangThai, String tenDanhMuc) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.ngayNhap = ngayNhap;
        this.ngayCN = ngayCN;
        this.trangThai = trangThai;
        this.tenDanhMuc = tenDanhMuc;
    }

    

    public String getNgayCN() {
        return ngayCN;
    }

    public void setNgayCN(String ngayCN) {
        this.ngayCN = ngayCN;
    }

    public SanPham() {
    }

    public int getMaSP() {
        return maSP;
    }

    public void setMaSP(int maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }
    
}
