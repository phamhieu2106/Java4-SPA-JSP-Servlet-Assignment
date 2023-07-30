package repositories;

import entities.NhaSanXuat;
import entities.NhaSanXuat;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NhaSanXuatRepository {

    private Session hSession;
    private List<NhaSanXuat> NhaSanXuatList;

    public NhaSanXuatRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.NhaSanXuatList = new ArrayList<>();
    }

    public NhaSanXuat getById(UUID id) {
        String hql = "SELECT obj FROM NhaSanXuat obj WHERE obj.id = ?1";
        TypedQuery<NhaSanXuat> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        NhaSanXuat ch = query.getSingleResult();
        return ch;
    }

    public NhaSanXuat getByMa(String ma) {
        String hql = "SELECT obj FROM NhaSanXuat obj WHERE obj.ma = ?1";
        TypedQuery<NhaSanXuat> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        NhaSanXuat ch = query.getSingleResult();
        return ch;
    }

    public List<NhaSanXuat> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<NhaSanXuat> query = this.hSession.createQuery("SELECT ch from NhaSanXuat ch", NhaSanXuat.class);
            NhaSanXuatList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return NhaSanXuatList;
    }

    public void insert(NhaSanXuat ch) {
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

    public void update(NhaSanXuat ch) {
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

    public void delete(NhaSanXuat ch) {
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
