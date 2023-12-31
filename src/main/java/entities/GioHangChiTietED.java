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

    private UUID gioHangId;

    private UUID ctspId;

}
