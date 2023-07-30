package repositories;

import entities.SanPham;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SanPhamRepository {

    private Session hSession;
    private List<SanPham> SanPhamList;

    public SanPhamRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.SanPhamList = new ArrayList<>();
    }

    public SanPham getById(UUID id) {
        String hql = "SELECT obj FROM SanPham obj WHERE obj.id = ?1";
        TypedQuery<SanPham> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        SanPham ch = query.getSingleResult();
        return ch;
    }

    public SanPham getByMa(String ma) {
        String hql = "SELECT obj FROM SanPham obj WHERE obj.ma = ?1";
        TypedQuery<SanPham> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        SanPham ch = query.getSingleResult();
        return ch;
    }

    public List<SanPham> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<SanPham> query = this.hSession.createQuery("SELECT ch from SanPham ch", SanPham.class);
            SanPhamList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return SanPhamList;
    }

    public void insert(SanPham ch) {
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

    public void update(SanPham ch) {
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

    public void delete(SanPham ch) {
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
