package business;

import core.Helper;
import dao.HotelDao;
import dao.PensionDao;
import dao.PensionDao;
import dao.UserDao;
import entity.Hotel;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private  PensionDao pensionDao;
    private  HotelDao hotelDao;

    public PensionManager() {
        
        this.pensionDao = new PensionDao();
        this.hotelDao = new HotelDao();
        
    }

    

    public Pension getById(int id) {
        return this.pensionDao.getById(id);
    }

    public ArrayList<Pension> findAll() {
        return this.pensionDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size ,ArrayList<Pension>pensionList){
        ArrayList<Object[]> pensionObjList = new ArrayList<>();
        for(Pension obj : pensionList){
            int i = 0;

            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getPensionId();
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getPensionType();

            pensionObjList.add(rowObject);
        }
        return  pensionObjList;

    }

    public boolean save(Pension pension) {
        if(this.getById(pension.getPensionId())!=null){
            Helper.showMsg("error","");
            return false ;
        }
        return this.pensionDao.save(pension);
    }

    public boolean update(Pension pension) {

        if(this.getById(pension.getPensionId()) == null){
            Helper.showMsg(pension.getPensionId()+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    public boolean delete(int id) {
        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.pensionDao.delete(id);
    }

//    public ArrayList<Pension> getByListpensionId(int hotelId) {
//        return this.pensionDao.getByListpensionId(hotelId);
//    }

}
