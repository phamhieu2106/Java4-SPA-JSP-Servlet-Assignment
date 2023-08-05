package entities;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Table(name = "HoaDonChiTiet")
public class HoaDonChiTiet {

    @AttributeOverrides({
            @AttributeOverride(name = "idHoaDon", column = @Column(name = "IdHoaDon")),
            @AttributeOverride(name = "idChiTietSP", column = @Column(name = "IdChiTietSP"))
    })
    @EmbeddedId
    private HoaDonChiTietED hoaDonChiTietED;

    @Column(name = "SoLuong")
    private int soLuong;
    @Column(name = "DonGia")
    private double donGia;

}
