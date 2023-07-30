package repositories;

import entities.DongSanPham;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DongSanPhamRepository {

    private Session hSession;
    private List<DongSanPham> DongSanPhamList;

    public DongSanPhamRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.DongSanPhamList = new ArrayList<>();
    }

    public DongSanPham getById(UUID id) {
        String hql = "SELECT obj FROM DongSanPham obj WHERE obj.id = ?1";
        TypedQuery<DongSanPham> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        DongSanPham ch = query.getSingleResult();
        return ch;
    }

    public DongSanPham getByMa(String ma) {
        String hql = "SELECT obj FROM DongSanPham obj WHERE obj.ma = ?1";
        TypedQuery<DongSanPham> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        DongSanPham ch = query.getSingleResult();
        return ch;
    }

    public List<DongSanPham> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<DongSanPham> query = this.hSession.createQuery("SELECT ch from DongSanPham ch", DongSanPham.class);
            DongSanPhamList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return DongSanPhamList;
    }

    public void insert(DongSanPham ch) {
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

    public void update(DongSanPham ch) {
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

    public void delete(DongSanPham ch) {
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
