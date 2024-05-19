/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class KhachHang {
    int MaCTKH;
    String tenKH;
    String soDienThoai;
    Date ngayTao;
    Date ngayCN;
    int trangThai;

    public KhachHang(int MaCTKH, String tenKH, String soDienThoai, Date ngayTao, Date ngayCN, int trangThai) {
        this.MaCTKH = MaCTKH;
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.ngayTao = ngayTao;
        this.ngayCN = ngayCN;
        this.trangThai = trangThai;
    }

    public KhachHang() {
    }

    public int getMaCTKH() {
        return MaCTKH;
    }

    public void setMaCTKH(int MaCTKH) {
        this.MaCTKH = MaCTKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgayCN() {
        return ngayCN;
    }

    public void setNgayCN(Date ngayCN) {
        this.ngayCN = ngayCN;
    }

    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }
    
}
