package webmodel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HoaDonSanPham {

    private String tenSanPham;
    private int soLuong;
    private double gia;

}
