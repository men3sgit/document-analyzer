package com.edu.nlu.document.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "documents")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "so_den", nullable = false)
    private String soDen;

    @Column(name = "so_ki_hieu_van_ban", nullable = false)
    private String soKiHieuVanBan;

    @Column(name = "co_quan_ban_hanh", nullable = false)
    private String coQuanBanHanh;

    @Column(name = "loai_van_ban", nullable = false)
    private String loaiVanBan;

    @Column(name = "trich_yeu", nullable = false, length = 1000)  // Adjust length as needed
    private String trichYeu;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @Column(name = "van_ban_chi_dao")
    private boolean vanBanChiDao;

    @Column(name = "co_bang_giay")
    private boolean coBangGiay;

    @Column(name = "van_ban_QPPL")
    private boolean vanBanQPPL;

    @Column(name = "ngay_den", nullable = false)
    private Date ngayDen;

    @Column(name = "ngay_ban_hanh", nullable = false)
    private Date ngayBanHanh;

    @Column(name = "cap_co_quan")
    private String capCoQuan;

    @Column(name = "linh_vuc")
    private String linhVuc;

    @Column(name = "thoi_gian_xu_ly")
    private Integer thoiGianXuLy;

    @Column(name = "thoi_gian_tao")
    private Date thoiGianTao = new Date();



//    @Lob
//    @Column(name = "dinh_kem_file")
//    private byte[] dinhKemFile;
}
