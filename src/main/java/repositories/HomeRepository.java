//package repositories;
//
//import entities.ChiTietSanPham;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//import utilites.HibernateUtil;
//
//import java.util.List;
//
//public class HomeRepository {
//
//    private Session hSession;
//    private ChiTietSanPhamRepository chiTietSanPhamRepository;
//    private Transaction transaction;
//
//    public HomeRepository() {
//        this.hSession = HibernateUtil.getFACTORY().openSession();
//        transaction = hSession.getTransaction();
//        chiTietSanPhamRepository = new ChiTietSanPhamRepository();
//    }
//
//    public List<ChiTietSanPham> getAllSanPham() {
//        try {
//            return chiTietSanPhamRepository.getAll();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public boolean addProductToCart(ChiTietSanPham chiTietSanPham){
//        try {
//            transaction.begin();
//            Gio
//
//            transaction.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            transaction.rollback();
//        }
//        return false;
//    }
//}
