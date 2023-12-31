package repositories;

import entities.KhachHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class KhachHangRepository {

    private Session hSession;
    private List<KhachHang> khachHangList;

    public KhachHangRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        khachHangList = new ArrayList<>();
    }

    public KhachHang getById(UUID id) {
        String hql = "SELECT obj FROM KhachHang obj WHERE obj.id = ?1";
        TypedQuery<KhachHang> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        KhachHang ch = query.getSingleResult();
        return ch;
    }

    public KhachHang getByMa(String ma) {
        String hql = "SELECT obj FROM KhachHang obj WHERE obj.ma = ?1";
        TypedQuery<KhachHang> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        KhachHang ch = query.getSingleResult();
        return ch;
    }

    public List<KhachHang> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<KhachHang> query = this.hSession.createQuery("SELECT ch from KhachHang ch", KhachHang.class);
            khachHangList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return khachHangList;
    }

    public void insert(KhachHang ch) {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.persist(ch);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void update(KhachHang ch) {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.merge(ch);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public void delete(KhachHang ch) {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.delete(ch);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }
}
