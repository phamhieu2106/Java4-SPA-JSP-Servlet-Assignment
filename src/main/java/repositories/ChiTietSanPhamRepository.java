package repositories;

import entities.ChiTietSanPham;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.List;
import java.util.UUID;

public class ChiTietSanPhamRepository {
    private Session hSession;
    private List<ChiTietSanPham> chiTietSanPhamList;

    public ChiTietSanPhamRepository() {
        this.hSession = new HibernateUtil().getFACTORY().openSession();
    }

    public ChiTietSanPham getById(UUID id) {
        String hql = "SELECT obj FROM ChiTietSanPham obj WHERE obj.id = ?1";
        TypedQuery<ChiTietSanPham> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        ChiTietSanPham ch = query.getSingleResult();
        return ch;
    }


    public List<ChiTietSanPham> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<ChiTietSanPham> query = this.hSession.createQuery("SELECT ch from ChiTietSanPham ch", ChiTietSanPham.class);
            chiTietSanPhamList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return chiTietSanPhamList;
    }

    public void insert(ChiTietSanPham ch) {
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

    public void update(ChiTietSanPham ch) {
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

    public void delete(ChiTietSanPham ch) {
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
