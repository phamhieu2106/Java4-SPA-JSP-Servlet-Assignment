package entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "GioHangChiTiet")
public class GioHangChiTiet implements Serializable {

    @AttributeOverrides({
            @AttributeOverride(name = "gioHangId", column = @Column(name = "IdGioHang")),
            @AttributeOverride(name = "ctspId", column = @Column(name = "IdChiTietSP"))
    })
    @EmbeddedId
    private GioHangChiTietED gioHangChiTietED;


    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private double donGia;
    @Column(name = "DonGiaKhiGiam")
    private double donGiaKhiGiam;

}
