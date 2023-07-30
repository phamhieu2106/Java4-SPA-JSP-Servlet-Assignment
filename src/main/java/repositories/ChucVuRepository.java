package repositories;

import entities.ChucVu;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ChucVuRepository {

    private Session hSession;
    private List<ChucVu> chucVuList;

    public ChucVuRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.chucVuList = new ArrayList<>();
    }

    public ChucVu getById(UUID id) {
        String hql = "SELECT obj FROM ChucVu obj WHERE obj.id = ?1";
        TypedQuery<ChucVu> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        ChucVu ch = query.getSingleResult();
        return ch;
    }

    public ChucVu getByMa(String ma) {
        String hql = "SELECT obj FROM ChucVu obj WHERE obj.ma = ?1";
        TypedQuery<ChucVu> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        ChucVu ch = query.getSingleResult();
        return ch;
    }

    public List<ChucVu> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<ChucVu> query = this.hSession.createQuery("SELECT ch from ChucVu ch", ChucVu.class);
            chucVuList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return chucVuList;
    }

    public void insert(ChucVu ch) {
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

    public void update(ChucVu ch) {
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

    public void delete(ChucVu ch) {
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
