package repositories;

import entities.CuaHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CuaHangRepository {
    private Session hSession;
    private List<CuaHang> cuaHangList;

    public CuaHangRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        cuaHangList = new ArrayList<>();
    }

    public CuaHang getById(UUID id) {
        String hql = "SELECT obj FROM CuaHang obj WHERE obj.id = ?1";
        TypedQuery<CuaHang> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        CuaHang ch = query.getSingleResult();
        return ch;
    }

    public CuaHang getByMa(String ma) {
        String hql = "SELECT obj FROM CuaHang obj WHERE obj.ma = ?1";
        TypedQuery<CuaHang> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        CuaHang ch = query.getSingleResult();
        return ch;
    }

    public List<CuaHang> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<CuaHang> query = this.hSession.createQuery("SELECT ch from CuaHang ch", CuaHang.class);
            cuaHangList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return cuaHangList;
    }

    public void insert(CuaHang ch) {
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

    public void update(CuaHang ch) {
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

    public void delete(CuaHang ch) {
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
