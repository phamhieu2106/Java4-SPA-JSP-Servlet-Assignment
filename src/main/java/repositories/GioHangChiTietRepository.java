package repositories;

import entities.GioHangChiTiet;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GioHangChiTietRepository {
    private Session hSession = HibernateUtil.getFACTORY().openSession();
    private List<GioHangChiTiet> list = new ArrayList<>();

    public List<GioHangChiTiet> getAll() {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<GioHangChiTiet> query = this.hSession.createQuery("SELECT ch FROM GioHangChiTiet ch", GioHangChiTiet.class);
            list = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return list;
    }

    public void add(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(gioHangChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void xoa(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(gioHangChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(GioHangChiTiet gioHangChiTiet) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.merge(gioHangChiTiet);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public List<GioHangChiTiet> findById(UUID gioHangID, UUID ctspID) {
        TypedQuery<GioHangChiTiet> query = this.hSession.createQuery(
                "SELECT ch FROM GioHangChiTiet ch WHERE ch.gioHangChiTietED.ctspId = ?1 " +
                        "and ch.gioHangChiTietED.gioHangId = ?2", GioHangChiTiet.class);
        query.setParameter(1, ctspID);
        query.setParameter(2, gioHangID);
        return query.getResultList();
    }

    public GioHangChiTiet findOneById(UUID ctspID) {
        TypedQuery<GioHangChiTiet> query = this.hSession.createQuery(
                "SELECT ch FROM GioHangChiTiet ch WHERE ch.gioHangChiTietED.ctspId = ?1", GioHangChiTiet.class);
        query.setParameter(1, ctspID);
        return query.getSingleResult();
    }
}
