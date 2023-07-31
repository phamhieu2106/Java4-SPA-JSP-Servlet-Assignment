package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet {

    private UUID idGioHang;
    private UUID idChiTietSP;
    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private double donGia;
    @Column(name = "DonGiaKhiGiam")
    private double donGiaKhiGiam;

}
