package business;

import core.Helper;
import dao.HotelDao;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao = new SeasonDao();
    private final HotelDao hotelDao = new HotelDao();

    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }

    public ArrayList<Season> findAll() {
        return this.seasonDao.findAll();
    }

    public ArrayList<Object[]> getForTable(int size ,ArrayList<Season>seasonList){
        ArrayList<Object[]> seasonObjList = new ArrayList<>();
        for(Season obj : seasonList){
            int i = 0;

            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = this.hotelDao.getById(obj.getHotelId()).getHotelName();
            rowObject[i++] = obj.getHotelId();
            rowObject[i++] = obj.getStartDate();
            rowObject[i++] = obj.getFinishDate();
            seasonObjList.add(rowObject);


        }
        return  seasonObjList;

    }

    public boolean save(Season season) {
        if(this.getById(season.getId())!=null){
            Helper.showMsg("error","");
            return false ;
        }
        return this.seasonDao.save(season);
    }

    public boolean update(Season season) {

        if(this.getById(season.getId())==null){
            Helper.showMsg(season.getId()+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.seasonDao.update(season);
    }

    public boolean delete(int id) {
        if(this.getById(id)==null){
            Helper.showMsg(id+" ID kayıtlı model bulunamadı","");
            return false;
        }
        return this.seasonDao.delete(id);
    }

    public ArrayList<Season> getByListSeasonId(int hotelId) {
        return this.seasonDao.getByListSeasonId(hotelId);
    }
}
