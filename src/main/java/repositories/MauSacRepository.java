package repositories;

import entities.MauSac;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MauSacRepository {

    private Session hSession;
    private List<MauSac> MauSacList;

    public MauSacRepository() {
        this.hSession = HibernateUtil.getFACTORY().openSession();
        this.MauSacList = new ArrayList<>();
    }

    public MauSac getById(UUID id) {
        String hql = "SELECT obj FROM MauSac obj WHERE obj.id = ?1";
        TypedQuery<MauSac> query = this.hSession.createQuery(hql);
        query.setParameter(1, id);
        MauSac ch = query.getSingleResult();
        return ch;
    }

    public MauSac getByMa(String ma) {
        String hql = "SELECT obj FROM MauSac obj WHERE obj.ma = ?1";
        TypedQuery<MauSac> query = this.hSession.createQuery(hql);
        query.setParameter(1, ma);
        MauSac ch = query.getSingleResult();
        return ch;
    }

    public List<MauSac> getAll() {
        Transaction transaction = this.hSession.getTransaction();
        try {
            transaction.begin();
            TypedQuery<MauSac> query = this.hSession.createQuery("SELECT ch from MauSac ch", MauSac.class);
            MauSacList = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return MauSacList;
    }

    public void insert(MauSac ch) {
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

    public void update(MauSac ch) {
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

    public void delete(MauSac ch) {
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
