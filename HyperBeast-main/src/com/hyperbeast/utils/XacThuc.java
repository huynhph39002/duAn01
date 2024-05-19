/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hyperbeast.utils;

import com.hyperbeast.entity.nhanVien;

/**
 *
 * @author Admin
 */
public class XacThuc {
    public static nhanVien user = null;
    public static void clear() {
        XacThuc.user = null;
    }
    public static boolean isLogin() {
        if(XacThuc.user == null) {
            return true;
        } else {
            return false;
        }
    }
    
    public static String isManager() {
        return  user.getTrangThai();
    }
}
