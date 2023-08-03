package repositories;

import entities.GioHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.UUID;


public class GioHangRepository {

    private Session hSession = HibernateUtil.getFACTORY().openSession();

    public void add(GioHang gioHang) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(gioHang);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(GioHang gioHang) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            hSession.merge(gioHang);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public GioHang findByMa(String ma) {
        String hql = "SELECT obj FROM GioHang obj WHERE obj.ma = ?1";
        TypedQuery<GioHang> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        GioHang ch = query.getSingleResult();
        return ch;
    }
}
