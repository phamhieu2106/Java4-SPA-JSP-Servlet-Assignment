package repositories;

import entities.HoaDonChiTiet;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class HoaDonChiTietRepository {

    private Session hSession = HibernateUtil.getFACTORY().openSession();

    public void add(HoaDonChiTiet hoaDonChiTiet) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(hoaDonChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public List<HoaDonChiTiet> findAll(UUID idHoaDon, UUID idCTSP) {
        TypedQuery<HoaDonChiTiet> query = hSession.createQuery("SELECT obj FROM" +
                " HoaDonChiTiet obj WHERE obj.hoaDonChiTietED.idChiTietSP = ?1" +
                "and obj.hoaDonChiTietED.idHoaDon = ?2");
        query.setParameter(1, idHoaDon);
        query.setParameter(2, idCTSP);
        return query.getResultList();
    }
}
