package utilites;

import entities.*;
import jakarta.persistence.TypedQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class HibernateUtil {
    private static final SessionFactory FACTORY;

    static {
        Configuration conf = new Configuration();

        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.SQLServerDialect");
        properties.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL, "jdbc:sqlserver://localhost:1433;databaseName=FINALASS_FPOLYSHOP_FA22_SOF205__SOF2041");
        properties.put(Environment.USER, "sa");
        properties.put(Environment.PASS, "1");
        properties.put(Environment.SHOW_SQL, "true");
        conf.setProperties(properties);

        conf.addAnnotatedClass(CuaHang.class);
        conf.addAnnotatedClass(ChucVu.class);
        conf.addAnnotatedClass(DongSanPham.class);
        conf.addAnnotatedClass(KhachHang.class);
        conf.addAnnotatedClass(MauSac.class);
        conf.addAnnotatedClass(NhanVien.class);
        conf.addAnnotatedClass(NhaSanXuat.class);
        conf.addAnnotatedClass(SanPham.class);
        conf.addAnnotatedClass(ChiTietSanPham.class);

        conf.addAnnotatedClass(GioHang.class);
        conf.addAnnotatedClass(GioHangChiTiet.class);

        conf.addAnnotatedClass(HoaDon.class);
        conf.addAnnotatedClass(HoaDonChiTiet.class);

        ServiceRegistry registry = new StandardServiceRegistryBuilder()
                .applySettings(conf.getProperties()).build();
        FACTORY = conf.buildSessionFactory(registry);

    }

    public static SessionFactory getFACTORY() {
        return FACTORY;
    }

    public static void main(String[] args) {
//        Session hSession = getFACTORY().openSession();
//        String ma = "222";
//        String hql = "SELECT cvObj FROM CuaHang cvObj WHERE cvObj.ma = ?1";
//        TypedQuery<CuaHang> query = hSession.createQuery(hql, CuaHang.class);
//        query.setParameter(1, ma);
//        CuaHang cv = query.getSingleResult();
//        System.out.println(cv.getTen());

    }
}
