package repositories;

import entities.NhanVien;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NhanVienRepository {
    private Session hSession;
    private List<NhanVien> nhanVienList;

    public NhanVienRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        nhanVienList = new ArrayList<>();
    }

    public NhanVien getById(UUID id) {
        String hql = "SELECT obj FROM NhanVien obj WHERE obj.id = ?1";
        TypedQuery<NhanVien> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        NhanVien ch = query.getSingleResult();
        return ch;
    }

    public NhanVien getByMa(String ma) {
        String hql = "SELECT obj FROM NhanVien obj WHERE obj.ma = ?1";
        TypedQuery<NhanVien> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        NhanVien ch = query.getSingleResult();
        return ch;
    }

    public List<NhanVien> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<NhanVien> query = this.hSession.createQuery("SELECT ch from NhanVien ch", NhanVien.class);
            nhanVienList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return nhanVienList;
    }

    public void insert(NhanVien ch) {
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

    public void update(NhanVien ch) {
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

    public void delete(NhanVien ch) {
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
