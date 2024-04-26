package business;

import core.Helper;
import dao.HotelDao;
import dao.PensionDao;
import entity.Pension;
import java.util.ArrayList;

public class PensionManager {
    private PensionDao pensionDao;
    private HotelDao hotelDao;

    public PensionManager() {
        // Initializing PensionDao and HotelDao objects
        this.pensionDao = new PensionDao();
        this.hotelDao = new HotelDao();
    }

    // Method to retrieve a pension by its ID
    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    // Method to retrieve all pensions
    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    // Method to create row objects for each pension in the table
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensionList) {
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for (Pension obj : pensionList) {
            int i = 0;
            Object[] rowObject = new Object[size];

            rowObject[i++] = obj.getPensionId();
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPensionType();

            pensionObjList.add(rowObject);
        }
        return pensionObjList;
    }

    // Method to save a new pension
    public boolean save(Pension pension) {
//        if (this.getById(pension.getPensionId()) != null) {
//            Helper.showMsg("error", "");
//            return false;
//        }
        return this.pensionDao.save(pension);
    }

    // Method to update an existing pension
    public boolean update(Pension pension) {
        if (this.getById(pension.getPensionId()) == null) {
            Helper.showMsg(pension.getPensionId() + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    // Method to delete a pension by its ID
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı", "");
            return false;
        }
        return this.pensionDao.delete(id);
    }

    // Method to find pensions by hotel ID
    public ArrayList<Pension> findByHotelId(int hotelId) {
        return this.pensionDao.findByHotelId(hotelId);
    }
}
