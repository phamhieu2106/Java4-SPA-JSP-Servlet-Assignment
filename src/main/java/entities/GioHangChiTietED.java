package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
@Setter
public class GioHangChiTietED implements Serializable {

    //    @ManyToOne
//    @JoinColumn(name = "IdGioHang")
    private UUID gioHangId;

    //    @ManyToOne
//    @JoinColumn(name = "IdChiTietSP")
    private UUID ctspId;

}
