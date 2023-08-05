package repositories;

import entities.HoaDon;
import entities.KhachHang;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utilites.HibernateUtil;

import java.time.LocalTime;
import java.util.Random;
import java.util.UUID;

public class HoaDonRepository {
    private static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 10; // Độ dài của chuỗi ngẫu nhiên
    private Session hSession = HibernateUtil.getFACTORY().openSession();
    private KhachHangRepository khachHangRepository = new KhachHangRepository();

    public static String generateRandomString() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder(LENGTH);

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public String add() {
        Transaction transaction = hSession.getTransaction();
        KhachHang khachHang = khachHangRepository.getByMa("kh1");
        String ma = generateRandomString();
        try {
            transaction.begin();

            KhachHang attachedKhachHang = (KhachHang) hSession.merge(khachHang); // Đưa khách hàng về trạng thái attached

            HoaDon hoaDon1 = new HoaDon(null, attachedKhachHang, null, ma, null,
                    null, null, null, 1,
                    attachedKhachHang.getMa(), attachedKhachHang.getDiaChi(), attachedKhachHang.getSdt());

            hSession.persist(hoaDon1);

            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
        return ma;
    }

    public void update(HoaDon hoaDon) {
        Transaction transaction = hSession.getTransaction();
        try {
            transaction.begin();
            this.hSession.merge(hoaDon);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        }
    }

    public HoaDon findOne(String time) {
        String hql = "SELECT obj FROM HoaDon obj WHERE obj.ma = ?1";
        TypedQuery<HoaDon> query = this.hSession.createQuery(hql);
        query.setParameter(1, time);
        HoaDon ch = query.getSingleResult();
        return ch;
    }
}
