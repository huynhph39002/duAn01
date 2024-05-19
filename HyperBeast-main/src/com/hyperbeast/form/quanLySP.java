/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.hyperbeast.form;

import com.hyperbeast.entity.SanPham;
import com.hyperbeast.entity.SanPhamChiTiet;
import com.hyperbeast.model.sanPhamModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class quanLySP extends javax.swing.JPanel  {

    /**
     * Creates new form test
     */
    private Frame main;
    sanPhamModel spModel = new sanPhamModel();
    ArrayList<SanPham> listSP = new ArrayList<>();
    ArrayList<SanPhamChiTiet> listCTSP = new ArrayList<>();
    JDialog d = new JDialog();
    int pageNumberSP;
    int pageNumberSPCT;
    int pageSP = 1;
    int pageSPCT = 1;
    int pageSelectSP;
    int pageSelectSPCT;
    ArrayList listSPSize;
    ArrayList listSPCTSize;
    public quanLySP() {
        initComponents();
        statusPageSP();
        getPageSP();
        getPageSPCT();
        fillSanPhamTbl();
        fillmau();
        fillKichThuoc();
        fillChatLieu();
        fillChatLieuDe();
        fillDanhMuc();
        clearLocSP();
        clearLocCTSP(1);
    }
    
    void statusPageSP() {
        listSPSize = spModel.getMaSP();
        pageNumberSP = (int) Math.ceil((listSPSize.size()/5.0));
        pageSPLbl.setText(pageSP + "/" + pageNumberSP);
    }
    
    void getPageSP() {
        pageSelectSP = (pageSP - 1) * 5;
    }
    void statusPageSPCT() {
        String tenSanPham = tenSPLbl.getText();
        String tenSanPham2 = tenSanPham.substring(tenSanPham.lastIndexOf("-") +2,tenSanPham.length());
        listSPCTSize = spModel.getMaCTSP(tenSanPham2);
        pageNumberSPCT = (int) Math.ceil((listSPCTSize.size()/5.0));
        pageCTSPLbl.setText(pageSPCT + "/" + pageNumberSPCT);
    }
    
    void getPageSPCT() {
        pageSelectSPCT = (pageSPCT - 1) * 5;
    }
    
    void validateSP( int choice) {
        sanPhamModel  model = new sanPhamModel();
        int maSP = 0;
        String tenSP = tenSPTxt.getText();
        String ngayNhap = ngayNhapTxt.getText();
        String ngayCN = ngayCapNhatTxt.getText();
        int danhMuc;
        String trangThai;
        int rowSelected = sanPhamTbl.getSelectedRow();
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        if(tenSP.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập tên sản phẩm");
            return;
        }
        if(danhMucCB.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn danh mục sản phẩm");
            return;
        } else {
            danhMuc = danhMucCB.getSelectedIndex() + 1;
        }
        if(trangThaiCB.getSelectedIndex() < 0) {
            JOptionPane.showMessageDialog(this, "Chưa chọn trạng thái sản phẩm");
            return;
        } else {
            trangThai = (String) trangThaiCB.getSelectedItem();
        }
        if(choice == 1) {
            try {
                model.insertSanPham(tenSP, ngayNhap, ngayCN, trangThai, danhMuc);
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công");
                statusPageSP();
                fillSanPhamTbl();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại");
            }
        }
        if(choice == 2) {
            try {
                if(rowSelected < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong bảng");
                    return;
                } else {
                    maSP = (int) sanPhamTbl.getValueAt(rowSelected, 0);
                }
                ngayCN = dateNow;
                model.updateSanPham(maSP, tenSP, ngayNhap, ngayCN, trangThai, danhMuc);
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công");
                statusPageSP();
                fillSanPhamTbl();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại");
            }
        }
    }
    
    void validateCTSP(int choice) {
        sanPhamModel  model = new sanPhamModel();
        File file;
        String tenSanPham = tenSPLbl.getText();
        String tenSanPham2 = tenSanPham.substring(tenSanPham.lastIndexOf("-") +2,tenSanPham.length());
        int rowSelected = sanPhamCTTbl.getSelectedRow();
        String maSPcheck = tenSPLbl.getText().substring(0, tenSPLbl.getText().indexOf(" "));
        int maCTSP;
        
        int maSP = Integer.parseInt(maSPcheck);
        int soLuong = 0;
        float donGia = 0;
        String moTa = "";
        int maMS = mauSacCB.getSelectedIndex() +1;
        String tenMau = mauSacCB.getSelectedItem().toString();
        int maSIZE = kichThuocCB.getSelectedIndex() +1;
        int kichThuoc = Integer.parseInt( "" +kichThuocCB.getSelectedItem());
        int maCL = chatLieuCB.getSelectedIndex() +1;
        String chatLieu = chatLieuCB.getSelectedItem().toString();
        int maCLD = chatLieuDCB.getSelectedIndex() +1;
        String chatlieuDe = chatLieuDCB.getSelectedItem().toString();
        String maBarCode;
        String iconfilename;
        String tenAnh;
        
        
            
        if(soLuongTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,"Chưa nhập số lượng");
            return;
        } else {
            try {
                soLuong = Integer.parseInt(soLuongTxt.getText());
                if(soLuong <= 0) {
                    JOptionPane.showMessageDialog(this, "Số lượng phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng số");
            }
        }
        
        if(donGiaTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập đơn giá");
            return;
        } else {
            try {
                donGia = Float.parseFloat(donGiaTxt.getText());
                if(donGia <= 0) {
                    JOptionPane.showMessageDialog(this, "Đơn giá phải lớn hơn 0");
                    return;
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Sai định dạng đơn giá");
            }
        }
        if(maBarCodeTxt.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã BarCode");
            return;
        } else {
            if(maBarCodeTxt.getText().length() == 13) {
                maBarCode = maBarCodeTxt.getText();
            } else {
                JOptionPane.showMessageDialog(this, "Mã BarCode phải đủ 13 ký tự");
                return;
            }
        }
        
        if(anhSPLbl.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Chưa thêm ảnh sản phẩm");
            return;
        } else {
            iconfilename = anhSPLbl.getIcon().toString();
            file = new File(iconfilename);
        }
        
        if(choice == 1) {
            ArrayList<SanPhamChiTiet> listSPCT2 = spModel.getSanPhamCT2(tenSanPham2);
            for (SanPhamChiTiet spct : listSPCT2) {
                if(tenMau.equalsIgnoreCase(spct.getTenMau()) && kichThuoc == spct.getKichThuoc() ) {
                    if(chatLieu.equals(spct.getTenChatLieu()) && chatlieuDe.equals(spct.getTenChatLieuDe())) {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại: sản phẩm đã tồn tại");
                        return;
                    }
                }
            }
            try {
                tenAnh = file.getName();
                spModel.insertSanPhamCT(maSP, soLuong, donGia, maMS, maSIZE, maCL, maCLD, maBarCode, tenAnh, moTa);
                JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm thành công");
                statusPageSPCT();
                fillCTSP();
                themCTBtn.setEnabled(false);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm chi tiết sản phẩm thất bại");
            }
        } 
        if(choice == 2){
            if(rowSelected < 0) {
                JOptionPane.showMessageDialog(this,"Chưa chọn sản phẩm");
                return;
            } else {
                maCTSP = (int) sanPhamCTTbl.getValueAt(rowSelected, 0);
            }
            try {
                if(rowSelected < 0) {
                    JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm trong bảng");
                    return;
                }
                tenAnh = file.getName();
                model.updateSPCT(maCTSP, soLuong, donGia, maMS, maSIZE, maCL, maCLD, maBarCode, tenAnh, moTa);
                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                statusPageSPCT();
                fillCTSP();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật thất bại");
            }
        }
    }
    
    void clearSanPham() {
        tenSPTxt.setText("");
        LocalDateTime ldt = LocalDateTime.now();
        String dateNow = (DateTimeFormatter.ofPattern("MM-dd-yyyy", Locale.ENGLISH).format(ldt));
        ngayNhapTxt.setText("" + dateNow);
        ngayCapNhatTxt.setText("" + dateNow);
        danhMucCB.setSelectedIndex(0);
    }
    
    void clearCTSP() {
        soLuongTxt.setText("");
        donGiaTxt.setText("");
        ghiChuTxt.setText("");
        mauSacCB.setSelectedIndex(0);
        kichThuocCB.setSelectedIndex(0);
        chatLieuCB.setSelectedIndex(0);
        chatLieuDCB.setSelectedIndex(0);
        maBarCodeTxt.setText("");
        anhSPLbl.setIcon(null);
        anhSPLbl.setText("Ảnh sản phẩm");
    }
    
    void clearLocSP() {
        timKiemTxt.setText("");
        locDanhMuc.setSelectedIndex(-1);
        locTrangThaiSP.setSelectedIndex(-1);
        fillSanPhamTbl();
    }
    
    void clearLocCTSP(int choice) {
        locCLCCB.setSelectedIndex(-1);
        locCLDCB.setSelectedIndex(-1);
        locKTCB.setSelectedIndex(-1);
        locMSCB.setSelectedIndex(-1);
        if(choice == 2) {
            statusPageSPCT();
            fillCTSP();
        }
    }
    
    void searchSanPham() {
        String tenSP = timKiemTxt.getText();
        String trangThai = (String) locTrangThaiSP.getSelectedItem();
        String tenDanhMuc = (String) locDanhMuc.getSelectedItem();
        if(trangThai == null) {
            trangThai = "";
        }
        if(tenDanhMuc == null) {
            tenDanhMuc = "";
        }
        ArrayList<SanPham> listSP2 = spModel.getSanPham2();
        ArrayList listTimKiem = spModel.searchSanPham(tenSP, trangThai, tenDanhMuc);
        if(listTimKiem.size() == 0) {
            JOptionPane.showMessageDialog(main, "Không tìm thấy sản phẩm");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) sanPhamTbl.getModel();
        model.setRowCount(0);
        for (Object maSP : listTimKiem) {
            for (int i = 0; i < listSP2.size(); i++) {
                 if(listSP2.get(i).getMaSP() == (int) maSP) {
                     Object[] data = {
                         listSP2.get(i).getMaSP(), listSP2.get(i).getTenSP(), listSP2.get(i).getNgayNhap(), listSP2.get(i).getNgayCN(),
                         listSP2.get(i).getTenDanhMuc(), listSP2.get(i).getTrangThai()
                     };
                     model.addRow(data);
                 }
            }
        }
    }
    
    
    
    
    void searchSanPhamCT() {
        String tenSanPham = tenSPLbl.getText();
        String tenSanPham2 = tenSanPham.substring(tenSanPham.lastIndexOf("-") +2,tenSanPham.length());
        String tenMau = (String) locMSCB.getSelectedItem();
        System.out.println(tenMau);
        String kichThuoc =  (String) locKTCB.getSelectedItem();
        String tenChatLieu = (String) locCLCCB.getSelectedItem();
        String tenChatLieuDe = (String) locCLDCB.getSelectedItem();
        if(tenMau == null) {
            tenMau = "";
        }
        System.out.println(tenMau);
        if(kichThuoc == null) {
            kichThuoc = "";
        }
        if(tenChatLieu == null) {
            tenChatLieu = "";
        }
        if(tenChatLieuDe == null) {
            tenChatLieuDe = "";
        }
        
        ArrayList<SanPhamChiTiet> listTimKiem = spModel.searchSanPhamCT(tenSanPham2, tenMau, kichThuoc, tenChatLieu, tenChatLieuDe);
        System.out.println(listTimKiem.size());
        if(listTimKiem.size() == 0) {
            JOptionPane.showMessageDialog(main, "Không tìm thấy sản phẩm");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) sanPhamCTTbl.getModel();
        model.setRowCount(0);
            for (int i = 0; i < listTimKiem.size(); i++) {
                Object[] data = {
                         listTimKiem.get(i).getMaCTSP(),listTimKiem.get(i).getTenSP(), listTimKiem.get(i).getSoLuong(), listTimKiem.get(i).getDonGia(), listTimKiem.get(i).getTenMau(),
                         listTimKiem.get(i).getKichThuoc(), listTimKiem.get(i).getTenChatLieu(), listTimKiem.get(i).getTenChatLieuDe(), listTimKiem.get(i).getMaBarCode(),
                         listTimKiem.get(i).getMoTa(),listTimKiem.get(i).getTenAnh()
                     };
                     model.addRow(data);
            }
    }
    
    
    void chonAnh() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setCurrentDirectory(new File("./src/com/hyperbeast/icon/"));
        int response = fileChooser.showOpenDialog(null);
        if(response == JFileChooser.APPROVE_OPTION) {
            File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            anhSPLbl.setText("");
            anhSPLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/" +file.getName())));
        }
    }

    void fillmau () {
        ArrayList<String> listMau = new ArrayList<>();
        listMau = spModel.getMauSac();
        DefaultComboBoxModel model = (DefaultComboBoxModel) mauSacCB.getModel();
        model.removeAllElements();
        String[] mauSac = new String[listMau.size()];
        for (int i = 0; i < listMau.size(); i++) {
            mauSac[i] = listMau.get(i);
        }
        mauSacCB.setModel(new javax.swing.DefaultComboBoxModel(mauSac));
        locMSCB.setModel(new javax.swing.DefaultComboBoxModel(mauSac));
    }
    void fillKichThuoc () {
        ArrayList<String> listKichTHuoc = new ArrayList<>();
        listKichTHuoc = spModel.getKichThuoc();
        DefaultComboBoxModel model = (DefaultComboBoxModel) kichThuocCB.getModel();
        model.removeAllElements();
        String[] kichThuoc = new String[listKichTHuoc.size()];
        for (int i = 0; i < listKichTHuoc.size(); i++) {
            kichThuoc[i] = listKichTHuoc.get(i);
        }
        kichThuocCB.setModel(new javax.swing.DefaultComboBoxModel(kichThuoc));
        locKTCB.setModel(new javax.swing.DefaultComboBoxModel(kichThuoc));
    }
    
    void fillChatLieu () {
        ArrayList<String> listChatLieu = new ArrayList<>();
        listChatLieu = spModel.getChatLieu();
        DefaultComboBoxModel model = (DefaultComboBoxModel) chatLieuCB.getModel();
        model.removeAllElements();
        String[] chatLieu = new String[listChatLieu.size()];
        for (int i = 0; i < listChatLieu.size(); i++) {
            chatLieu[i] = listChatLieu.get(i);
        }
        chatLieuCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieu));
        locCLCCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieu));
    }
    
    void fillChatLieuDe () {
        ArrayList<String> listChatLieuDe = new ArrayList<>();
        listChatLieuDe = spModel.getChatLieuDe();
        DefaultComboBoxModel model = (DefaultComboBoxModel) chatLieuDCB.getModel();
        model.removeAllElements();
        String[] chatLieuDe = new String[listChatLieuDe.size()];
        for (int i = 0; i < listChatLieuDe.size(); i++) {
            chatLieuDe[i] = listChatLieuDe.get(i);
        }
        chatLieuDCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieuDe));
        locCLDCB.setModel(new javax.swing.DefaultComboBoxModel(chatLieuDe));
    }
    
    void fillDanhMuc () {
        ArrayList<String> listDanhMuc = new ArrayList<>();
        listDanhMuc = spModel.getDanhMuc();
        DefaultComboBoxModel model = (DefaultComboBoxModel) danhMucCB.getModel();
        model.removeAllElements();
        String[] danhMuc = new String[listDanhMuc.size()];
        for (int i = 0; i < listDanhMuc.size(); i++) {
            danhMuc[i] = listDanhMuc.get(i);
        }
       danhMucCB.setModel(new javax.swing.DefaultComboBoxModel(danhMuc)); 
       locDanhMuc.setModel(new javax.swing.DefaultComboBoxModel(danhMuc));
    }
    
    void fillSanPhamTbl() {
        listSP = spModel.getSanPham(pageSelectSP);
        DefaultTableModel model = (DefaultTableModel) sanPhamTbl.getModel();
        model.setRowCount(0);
        for (SanPham sanPham : listSP) {
            Object[] data = {
                sanPham.getMaSP(), sanPham.getTenSP(), sanPham.getNgayNhap(), sanPham.getNgayCN(),
                sanPham.getTenDanhMuc(),sanPham.getTrangThai()
            };
            model.addRow(data);
        }
    }
    
    void fillCTSP() {
        String tenSanPham = tenSPLbl.getText();
        String tenSanPham2 = tenSanPham.substring(tenSanPham.lastIndexOf("-") +2,tenSanPham.length());
        listSP = spModel.getSanPham(pageSelectSP);
        listCTSP = spModel.getSanPhamCT(pageSelectSPCT, tenSanPham2);
        DefaultTableModel model = (DefaultTableModel) sanPhamCTTbl.getModel();
        model.setRowCount(0);
        //for (int i = 0; i < listSP.size(); i++) {
            for (int j = 0; j < listCTSP.size(); j++) {
                //if(listSP.get(i).getMaSP() == listCTSP.get(j).getMaSP()){
                    Object[] data = {
                        listCTSP.get(j).getMaCTSP(),listCTSP.get(j).getTenSP(), listCTSP.get(j).getSoLuong(), listCTSP.get(j).getDonGia(),
                        listCTSP.get(j).getTenMau(),listCTSP.get(j).getKichThuoc(), listCTSP.get(j).getTenChatLieu(),
                        listCTSP.get(j).getTenChatLieuDe(), listCTSP.get(j).getMaBarCode(),listCTSP.get(j).getMoTa(), listCTSP.get(j).getTenAnh()
                   };
                    model.addRow(data);
                //}
            }
       // }
    }
    
    
    void getDataSPSelected(int rowSelected) {
        tenSPTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 1));
        ngayNhapTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 2));
        ngayCapNhatTxt.setText((String) sanPhamTbl.getValueAt(rowSelected, 3));
        danhMucCB.setSelectedItem(sanPhamTbl.getValueAt(rowSelected, 4));
        trangThaiCB.setSelectedItem(sanPhamTbl.getValueAt(rowSelected, 5));
    }
    
    void getDataSPCTSelected (int rowSelected) {
        soLuongTxt.setText("" +sanPhamCTTbl.getValueAt(rowSelected, 2));
        donGiaTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 3));
        mauSacCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 4));
        //kichThuocCB.setSelectedIndex(3);
        kichThuocCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 5).toString());
        chatLieuCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 6));
        chatLieuDCB.setSelectedItem(sanPhamCTTbl.getValueAt(rowSelected, 7));
        maBarCodeTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 8));
        ghiChuTxt.setText("" + sanPhamCTTbl.getValueAt(rowSelected, 9));
        anhSPLbl.setText("");
        anhSPLbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/" + sanPhamCTTbl.getValueAt(rowSelected, 10))));
    } 
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabs = new tabbed.MaterialTabbed();
        panelSP = new javax.swing.JPanel();
        panelBorder1 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane1 = new javax.swing.JScrollPane();
        sanPhamTbl = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        pageSPLbl = new javax.swing.JLabel();
        timKiemTxt = new com.hyperbeast.swing.TextField();
        panelBorder2 = new com.hyperbeast.swing.PanelBorder();
        tenSPTxt = new com.hyperbeast.swing.TextField();
        ngayNhapTxt = new com.hyperbeast.swing.TextField();
        danhMucCB = new com.hyperbeast.swing.Combobox();
        jLabel2 = new javax.swing.JLabel();
        trangThaiCB = new com.hyperbeast.swing.Combobox();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        themBtn = new javax.swing.JButton();
        ngayCapNhatTxt = new com.hyperbeast.swing.TextField();
        locTrangThaiSP = new com.hyperbeast.swing.Combobox();
        locDanhMuc = new com.hyperbeast.swing.Combobox();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        themCTSPBtn = new javax.swing.JButton();
        panelCTSP = new javax.swing.JPanel();
        panelBorder3 = new com.hyperbeast.swing.PanelBorder();
        jScrollPane2 = new javax.swing.JScrollPane();
        sanPhamCTTbl = new javax.swing.JTable();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        pageCTSPLbl = new javax.swing.JLabel();
        locKTCB = new com.hyperbeast.swing.Combobox();
        locMSCB = new com.hyperbeast.swing.Combobox();
        locCLCCB = new com.hyperbeast.swing.Combobox();
        locCLDCB = new com.hyperbeast.swing.Combobox();
        jLabel1 = new javax.swing.JLabel();
        panelBorder4 = new com.hyperbeast.swing.PanelBorder();
        tenSPLbl = new javax.swing.JLabel();
        donGiaTxt = new com.hyperbeast.swing.TextField();
        soLuongTxt = new com.hyperbeast.swing.TextField();
        mauSacCB = new com.hyperbeast.swing.Combobox();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        ghiChuTxt = new javax.swing.JTextArea();
        kichThuocCB = new com.hyperbeast.swing.Combobox();
        chatLieuCB = new com.hyperbeast.swing.Combobox();
        chatLieuDCB = new com.hyperbeast.swing.Combobox();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        themCTBtn = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        panelBorder5 = new com.hyperbeast.swing.PanelBorder();
        anhSPLbl = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        panelBorder6 = new com.hyperbeast.swing.PanelBorder();
        maBarCodeTxt = new com.hyperbeast.swing.TextField();

        setForeground(new java.awt.Color(255, 255, 255));

        tabs.setForeground(new java.awt.Color(102, 102, 102));
        tabs.setEnabled(false);
        tabs.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tabs.setPreferredSize(new java.awt.Dimension(1076, 710));

        panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

        sanPhamTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã Sản phẩm", "Tên sản phẩm", "Ngày nhập", "Ngày cập nhật", "Danh mục sản phẩm", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sanPhamTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamTblMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(sanPhamTbl);

        jButton2.setBackground(new java.awt.Color(0, 102, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText(">>");
        jButton2.setBorderPainted(false);
        jButton2.setFocusable(false);
        jButton2.setPreferredSize(new java.awt.Dimension(85, 30));
        jButton2.setRequestFocusEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(0, 102, 255));
        jButton3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("<<");
        jButton3.setBorderPainted(false);
        jButton3.setFocusable(false);
        jButton3.setPreferredSize(new java.awt.Dimension(85, 30));
        jButton3.setRequestFocusEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        pageSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pageSPLbl.setText("Trang");

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pageSPLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pageSPLbl))
                .addContainerGap())
        );

        timKiemTxt.setBackground(new java.awt.Color(255, 255, 255));
        timKiemTxt.setLabelText("Tìm kiếm theo tên");

        panelBorder2.setBackground(new java.awt.Color(255, 255, 255));

        tenSPTxt.setLabelText("Tên sản phẩm");

        ngayNhapTxt.setEnabled(false);
        ngayNhapTxt.setLabelText("Ngày nhập");

        danhMucCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        danhMucCB.setSelectedIndex(-1);
        danhMucCB.setLabeText("Danh mục");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        trangThaiCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Ngừng kinh doanh", "Hết hàng", "Sản phẩm lỗi" }));
        trangThaiCB.setSelectedIndex(-1);
        trangThaiCB.setLabeText("Trạng thái");

        jButton4.setBackground(new java.awt.Color(0, 102, 255));
        jButton4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 255, 255));
        jButton4.setText("Cập Nhật");
        jButton4.setBorderPainted(false);
        jButton4.setFocusable(false);
        jButton4.setRequestFocusEnabled(false);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(0, 102, 255));
        jButton5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Mới");
        jButton5.setBorderPainted(false);
        jButton5.setFocusable(false);
        jButton5.setRequestFocusEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        themBtn.setBackground(new java.awt.Color(0, 102, 255));
        themBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themBtn.setForeground(new java.awt.Color(255, 255, 255));
        themBtn.setText("Thêm");
        themBtn.setBorderPainted(false);
        themBtn.setEnabled(false);
        themBtn.setFocusable(false);
        themBtn.setRequestFocusEnabled(false);
        themBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themBtnActionPerformed(evt);
            }
        });

        ngayCapNhatTxt.setEnabled(false);
        ngayCapNhatTxt.setLabelText("Ngày cập nhật");

        javax.swing.GroupLayout panelBorder2Layout = new javax.swing.GroupLayout(panelBorder2);
        panelBorder2.setLayout(panelBorder2Layout);
        panelBorder2Layout.setHorizontalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tenSPTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                    .addComponent(ngayNhapTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trangThaiCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addComponent(danhMucCB, javax.swing.GroupLayout.PREFERRED_SIZE, 394, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addGap(9, 9, 9))
        );
        panelBorder2Layout.setVerticalGroup(
            panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(danhMucCB, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(tenSPTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ngayNhapTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(trangThaiCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ngayCapNhatTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder2Layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addGroup(panelBorder2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(themBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        locTrangThaiSP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Đang kinh doanh", "Ngừng kinh doanh", "Hết hàng", "Sản phẩm lỗi" }));
        locTrangThaiSP.setSelectedIndex(-1);
        locTrangThaiSP.setLabeText("Trạng thái");

        locDanhMuc.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item2" }));
        locDanhMuc.setSelectedIndex(-1);
        locDanhMuc.setLabeText("Danh mục");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/arrow.png"))); // NOI18N
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 255));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Tìm");
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.setRequestFocusEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        themCTSPBtn.setBackground(new java.awt.Color(0, 102, 255));
        themCTSPBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themCTSPBtn.setForeground(new java.awt.Color(255, 255, 255));
        themCTSPBtn.setText("Thêm chi tiết sản phẩm");
        themCTSPBtn.setBorderPainted(false);
        themCTSPBtn.setFocusable(false);
        themCTSPBtn.setRequestFocusEnabled(false);
        themCTSPBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCTSPBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelSPLayout = new javax.swing.GroupLayout(panelSP);
        panelSP.setLayout(panelSPLayout);
        panelSPLayout.setHorizontalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSPLayout.createSequentialGroup()
                        .addComponent(themCTSPBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(timKiemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(locDanhMuc, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(locTrangThaiSP, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(panelBorder2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        panelSPLayout.setVerticalGroup(
            panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSPLayout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(panelBorder2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(timKiemTxt, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(locTrangThaiSP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(locDanhMuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(themCTSPBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        tabs.addTab("Sản Phẩm", panelSP);

        panelBorder3.setBackground(new java.awt.Color(255, 255, 255));

        sanPhamCTTbl.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã CTSP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Màu sắc", "Kích thước", "Chất liệu chính", "Chất liệu đế", "Mã barcode", "Ghi chú", "Ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sanPhamCTTbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sanPhamCTTblMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(sanPhamCTTbl);

        jButton9.setBackground(new java.awt.Color(0, 102, 255));
        jButton9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 255, 255));
        jButton9.setText(">>");
        jButton9.setBorderPainted(false);
        jButton9.setFocusable(false);
        jButton9.setPreferredSize(new java.awt.Dimension(75, 30));
        jButton9.setRequestFocusEnabled(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setBackground(new java.awt.Color(0, 102, 255));
        jButton10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 255, 255));
        jButton10.setText("<<");
        jButton10.setBorderPainted(false);
        jButton10.setFocusable(false);
        jButton10.setPreferredSize(new java.awt.Dimension(75, 30));
        jButton10.setRequestFocusEnabled(false);
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        pageCTSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pageCTSPLbl.setText("Trang");

        locKTCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        locKTCB.setSelectedIndex(-1);
        locKTCB.setLabeText("Kích thước");
        locKTCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locKTCBActionPerformed(evt);
            }
        });

        locMSCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        locMSCB.setSelectedIndex(-1);
        locMSCB.setLabeText("Màu sắc");
        locMSCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locMSCBActionPerformed(evt);
            }
        });

        locCLCCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        locCLCCB.setSelectedIndex(-1);
        locCLCCB.setLabeText("Chất liệu chính");
        locCLCCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locCLCCBActionPerformed(evt);
            }
        });

        locCLDCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "item1", "item 2" }));
        locCLDCB.setSelectedIndex(-1);
        locCLDCB.setLabeText("Chất liệu đế");
        locCLDCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                locCLDCBActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/arrow.png"))); // NOI18N
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelBorder3Layout = new javax.swing.GroupLayout(panelBorder3);
        panelBorder3.setLayout(panelBorder3Layout);
        panelBorder3Layout.setHorizontalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(panelBorder3Layout.createSequentialGroup()
                        .addComponent(locMSCB, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelBorder3Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(locKTCB, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(locCLCCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(locCLDCB, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3))
                            .addGroup(panelBorder3Layout.createSequentialGroup()
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(pageCTSPLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        panelBorder3Layout.setVerticalGroup(
            panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(locKTCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(locMSCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(locCLCCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(locCLDCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pageCTSPLbl)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(13, 13, 13))
        );

        panelBorder4.setBackground(new java.awt.Color(255, 255, 255));

        tenSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        tenSPLbl.setForeground(new java.awt.Color(0, 102, 255));

        donGiaTxt.setLabelText("Đơn giá");

        soLuongTxt.setLabelText("Số lượng");

        mauSacCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        mauSacCB.setSelectedIndex(-1);
        mauSacCB.setLabeText("Màu sắc");

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        jLabel7.setText("Ghi chú");

        ghiChuTxt.setColumns(20);
        ghiChuTxt.setRows(5);
        jScrollPane3.setViewportView(ghiChuTxt);

        kichThuocCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        kichThuocCB.setSelectedIndex(-1);
        kichThuocCB.setLabeText("Kích thước");

        chatLieuCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        chatLieuCB.setSelectedIndex(-1);
        chatLieuCB.setLabeText("Chất liệu chính");

        chatLieuDCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item1", "item2" }));
        chatLieuDCB.setSelectedIndex(-1);
        chatLieuDCB.setLabeText("Chất liệu đế");

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/hyperbeast/icon/add.png"))); // NOI18N
        jLabel10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel10MouseClicked(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(0, 102, 255));
        jButton13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Mới");
        jButton13.setBorderPainted(false);
        jButton13.setFocusable(false);
        jButton13.setRequestFocusEnabled(false);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        themCTBtn.setBackground(new java.awt.Color(0, 102, 255));
        themCTBtn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        themCTBtn.setForeground(new java.awt.Color(255, 255, 255));
        themCTBtn.setText("Thêm");
        themCTBtn.setBorderPainted(false);
        themCTBtn.setEnabled(false);
        themCTBtn.setFocusable(false);
        themCTBtn.setRequestFocusEnabled(false);
        themCTBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themCTBtnActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(0, 102, 255));
        jButton15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 255, 255));
        jButton15.setText("Cập nhật");
        jButton15.setBorderPainted(false);
        jButton15.setFocusable(false);
        jButton15.setRequestFocusEnabled(false);
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(0, 102, 255));
        jButton17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 255, 255));
        jButton17.setText("Trở về");
        jButton17.setBorderPainted(false);
        jButton17.setFocusable(false);
        jButton17.setRequestFocusEnabled(false);
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder4Layout = new javax.swing.GroupLayout(panelBorder4);
        panelBorder4.setLayout(panelBorder4Layout);
        panelBorder4Layout.setHorizontalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tenSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder4Layout.createSequentialGroup()
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane3)
                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(soLuongTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(panelBorder4Layout.createSequentialGroup()
                                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(themCTBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(donGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addComponent(mauSacCB, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelBorder4Layout.createSequentialGroup()
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jButton17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(chatLieuDCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(chatLieuCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
                                    .addComponent(kichThuocCB, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap())
        );
        panelBorder4Layout.setVerticalGroup(
            panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tenSPLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(donGiaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mauSacCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(soLuongTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(kichThuocCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(14, 14, 14)
                .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chatLieuCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(chatLieuDCB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE))
                    .addGroup(panelBorder4Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(panelBorder4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
                            .addComponent(themCTBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(12, 12, 12))
        );

        panelBorder5.setBackground(new java.awt.Color(255, 255, 255));

        anhSPLbl.setBackground(new java.awt.Color(187, 187, 187));
        anhSPLbl.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        anhSPLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        anhSPLbl.setText("Ảnh sản phẩm");
        anhSPLbl.setToolTipText("");
        anhSPLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                anhSPLblMouseClicked(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(0, 102, 255));
        jButton11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Chọn ảnh");
        jButton11.setBorderPainted(false);
        jButton11.setFocusable(false);
        jButton11.setRequestFocusEnabled(false);
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(0, 102, 255));
        jButton12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Chụp ảnh");
        jButton12.setBorderPainted(false);
        jButton12.setFocusable(false);
        jButton12.setRequestFocusEnabled(false);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelBorder5Layout = new javax.swing.GroupLayout(panelBorder5);
        panelBorder5.setLayout(panelBorder5Layout);
        panelBorder5Layout.setHorizontalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(anhSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder5Layout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        panelBorder5Layout.setVerticalGroup(
            panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(anhSPLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelBorder5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        panelBorder6.setBackground(new java.awt.Color(255, 255, 255));

        maBarCodeTxt.setLabelText("Mã barcode");

        javax.swing.GroupLayout panelBorder6Layout = new javax.swing.GroupLayout(panelBorder6);
        panelBorder6.setLayout(panelBorder6Layout);
        panelBorder6Layout.setHorizontalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maBarCodeTxt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelBorder6Layout.setVerticalGroup(
            panelBorder6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(maBarCodeTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelCTSPLayout = new javax.swing.GroupLayout(panelCTSP);
        panelCTSP.setLayout(panelCTSPLayout);
        panelCTSPLayout.setHorizontalGroup(
            panelCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCTSPLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(panelCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(panelCTSPLayout.createSequentialGroup()
                        .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(35, 35, 35)
                        .addGroup(panelCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(panelBorder5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(39, 39, 39))
        );
        panelCTSPLayout.setVerticalGroup(
            panelCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelCTSPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCTSPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelCTSPLayout.createSequentialGroup()
                        .addComponent(panelBorder5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(panelBorder6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(panelBorder4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelBorder3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18))
        );

        tabs.addTab("", panelCTSP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, 1110, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tabs, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void sanPhamTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamTblMouseClicked
        // TODO add your handling code here:
        int rowSelected = sanPhamTbl.getSelectedRow();
        themBtn.setEnabled(false);
        getDataSPSelected(rowSelected);
        if(rowSelected >= 0) {
            themCTSPBtn.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "Chỉ được chọn 1 sản phẩm");
        }
    }//GEN-LAST:event_sanPhamTblMouseClicked

    private void sanPhamCTTblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sanPhamCTTblMouseClicked
        // TODO add your handling code here:
        int rowSelected = sanPhamCTTbl.getSelectedRow();
        getDataSPCTSelected(rowSelected);
        themCTBtn.setEnabled(false);
    }//GEN-LAST:event_sanPhamCTTblMouseClicked

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        chonAnh();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        new anhJDialog(main, true).setVisible(true);
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        clearSanPham();
        themBtn.setEnabled(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        clearCTSP();
        if(tenSPLbl.getText().equals("")) {
            themCTBtn.setEnabled(false);
        } else {
            themCTBtn.setEnabled(true);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void themCTSPBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themCTSPBtnActionPerformed
        // TODO add your handling code here:
        int rowSelected = sanPhamTbl.getSelectedRow();
        if (rowSelected == -1) {
            JOptionPane.showMessageDialog(this,"Chưa chọn sản phẩm");
            return;
        }
        String tenSP = sanPhamTbl.getValueAt(rowSelected, 0) +" - " + sanPhamTbl.getValueAt(rowSelected, 1);
        tenSPLbl.setText(tenSP);
        if(!tenSP.equals("")) {
            themCTBtn.setEnabled(true);
            
        }
        //JDialog d = new JDialog();
        d.setSize(1140, 740);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - d.getWidth()) / 2;
        final int y = (screenSize.height - d.getHeight()) / 2;
        d.setLocation(x, y - 30);
        d.setResizable(false);
        d.setVisible(true);
        d.add(panelCTSP);
        statusPageSPCT();
        fillCTSP();
    }//GEN-LAST:event_themCTSPBtnActionPerformed

    private void themBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themBtnActionPerformed
        // TODO add your handling code here:
        validateSP(1);
    }//GEN-LAST:event_themBtnActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        validateSP(2);
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        new danhMucJDialog(main, true).setVisible(true);
        fillDanhMuc();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        new themMSJDialog(main, true).setVisible(true);
        fillmau();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
        new kichThuocJDialog(main, true).setVisible(true);
        fillKichThuoc();
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        new chatLieuJDialog(main, true).setVisible(true);
        fillChatLieu();
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel10MouseClicked
        // TODO add your handling code here:
        new chatLieuDeJDialog(main, true).setVisible(true);
        fillChatLieuDe();
    }//GEN-LAST:event_jLabel10MouseClicked

    private void themCTBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themCTBtnActionPerformed
        // TODO add your handling code here:
        validateCTSP(1);
    }//GEN-LAST:event_themCTBtnActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        validateCTSP(2);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void anhSPLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_anhSPLblMouseClicked
        // TODO add your handling code here:
        String urlIcon = anhSPLbl.getIcon().toString();
        File file = new File(urlIcon);
        System.out.println(file.getName());
    }//GEN-LAST:event_anhSPLblMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if(pageSP <= pageNumberSP) {
            if(pageSP == 1) {
                return;
            }
            pageSP--;
            getPageSP();
            statusPageSP();
            fillSanPhamTbl();

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(pageSP >= 1 && pageSP < pageNumberSP) {
            pageSP++;
            getPageSP();
            statusPageSP();
            fillSanPhamTbl();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        if(pageSPCT <= pageNumberSPCT) {
            if(pageSPCT == 1) {
                return;
            }
            pageSPCT--;
            getPageSPCT();
            statusPageSPCT();
            fillCTSP();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if(pageSPCT >= 1 && pageSPCT < pageNumberSPCT) {
            pageSPCT++;
            getPageSPCT();
            statusPageSPCT();
            fillCTSP();
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        clearCTSP();
        themCTBtn.setEnabled(false);
        d.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        clearLocCTSP(2);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        clearLocSP();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        searchSanPham();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void locMSCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locMSCBActionPerformed
        // TODO add your handling code here:
        if(tenSPLbl.getText().isEmpty()) {
            return;
        }
        searchSanPhamCT();
    }//GEN-LAST:event_locMSCBActionPerformed

    private void locKTCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locKTCBActionPerformed
        // TODO add your handling code here:
        if(tenSPLbl.getText().isEmpty()) {
            return;
        }
        searchSanPhamCT();
    }//GEN-LAST:event_locKTCBActionPerformed

    private void locCLCCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locCLCCBActionPerformed
        // TODO add your handling code here:
        if(tenSPLbl.getText().isEmpty()) {
            return;
        }
        searchSanPhamCT();
    }//GEN-LAST:event_locCLCCBActionPerformed

    private void locCLDCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_locCLDCBActionPerformed
        // TODO add your handling code here:
        if(tenSPLbl.getText().isEmpty()) {
            return;
        }
        searchSanPhamCT();
    }//GEN-LAST:event_locCLDCBActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel anhSPLbl;
    private com.hyperbeast.swing.Combobox chatLieuCB;
    private com.hyperbeast.swing.Combobox chatLieuDCB;
    private com.hyperbeast.swing.Combobox danhMucCB;
    private com.hyperbeast.swing.TextField donGiaTxt;
    private javax.swing.JTextArea ghiChuTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.hyperbeast.swing.Combobox kichThuocCB;
    private com.hyperbeast.swing.Combobox locCLCCB;
    private com.hyperbeast.swing.Combobox locCLDCB;
    private com.hyperbeast.swing.Combobox locDanhMuc;
    private com.hyperbeast.swing.Combobox locKTCB;
    private com.hyperbeast.swing.Combobox locMSCB;
    private com.hyperbeast.swing.Combobox locTrangThaiSP;
    private com.hyperbeast.swing.TextField maBarCodeTxt;
    private com.hyperbeast.swing.Combobox mauSacCB;
    private com.hyperbeast.swing.TextField ngayCapNhatTxt;
    private com.hyperbeast.swing.TextField ngayNhapTxt;
    private javax.swing.JLabel pageCTSPLbl;
    private javax.swing.JLabel pageSPLbl;
    private com.hyperbeast.swing.PanelBorder panelBorder1;
    private com.hyperbeast.swing.PanelBorder panelBorder2;
    private com.hyperbeast.swing.PanelBorder panelBorder3;
    private com.hyperbeast.swing.PanelBorder panelBorder4;
    private com.hyperbeast.swing.PanelBorder panelBorder5;
    private com.hyperbeast.swing.PanelBorder panelBorder6;
    private javax.swing.JPanel panelCTSP;
    private javax.swing.JPanel panelSP;
    private javax.swing.JTable sanPhamCTTbl;
    private javax.swing.JTable sanPhamTbl;
    private com.hyperbeast.swing.TextField soLuongTxt;
    private tabbed.MaterialTabbed tabs;
    private javax.swing.JLabel tenSPLbl;
    private com.hyperbeast.swing.TextField tenSPTxt;
    private javax.swing.JButton themBtn;
    private javax.swing.JButton themCTBtn;
    private javax.swing.JButton themCTSPBtn;
    private com.hyperbeast.swing.TextField timKiemTxt;
    private com.hyperbeast.swing.Combobox trangThaiCB;
    // End of variables declaration//GEN-END:variables
}
