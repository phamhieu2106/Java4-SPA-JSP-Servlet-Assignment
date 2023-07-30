package entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "Id")
    private int id;
    @Column(name = "Ma")
    private String ma;
    @Column(name = "Ten")
    private String ten;
    @Column(name = "TenDem")
    private String tenDem;
    @Column(name = "Ho")
    private String ho;
    @Column(name = "GioiTinh")
    private String gioiTinh;
    @Column(name = "NgaySinh")
    private String ngaySinh;
    @Column(name = "DiaChi")
    private String diaChi;
    @Column(name = "Sdt")
    private String sdt;
    @Column(name = "MatKhau")
    private String matKhau;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdCH", referencedColumnName = "Id")
    private CuaHang cuaHang;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "IdCV", referencedColumnName = "Id")
    private ChucVu chucVu;

    @Column(name = "TrangThai")
    private int trangThai;

}
