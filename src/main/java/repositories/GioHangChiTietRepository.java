package repositories;

import entities.GioHangChiTiet;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

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
}
