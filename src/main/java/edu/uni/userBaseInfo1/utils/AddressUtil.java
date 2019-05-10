package edu.uni.userBaseInfo1.utils;
import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @Author chenenru
 * @ClassName AddressUtil
 * @Description 地址的操作工具类
 *  这是关于地址的工具类，前端传回国家id，后台根据国家id返回此国家的所有省份，
 *  前端选中某一个省份的id并传回该省份的id,后台根据该省份的id查询并返回该城市下的所有城市，以此类推...
 * @Date 2019/5/8 18:24
 * @Version 1.0
 **/
@Component
public class AddressUtil {

    @Autowired
    private AddrCountryService addrCountry;
    @Autowired
    private  AddrStateService addrState;
    @Autowired
    private AddrCityService addrCity;
    @Autowired
    private AddrAreaService addrArea;
    @Autowired
    private AddrStreetService addrStreet;
    private static AddrCountryService addrCountryService;
    private static AddrStateService addrStateService;
    private static AddrCityService addrCityService;
    private static AddrAreaService addrAreaService;
    private static AddrStreetService addrStreetService;

    @PostConstruct
    public void init() {
        addrCountryService = this.addrCountry;
        addrStateService = this.addrState;
        addrCityService = this.addrCity;
        addrAreaService = this.addrArea;
        addrStreetService = this.addrStreet;
    }

    /**
     * Author: chenenru 19:01 2019/5/8
     * @param
     * @return
     * @apiNote: 查询所有的国家
     */
    public List<AddrCountry> SelectCountries(){
       return addrCountryService.selectAllAddrCountrys();
    }
    /**
     * Author: chenenru 18:51 2019/5/8
     * @param countryCode
     * @return 某国家的所有省份
     * @apiNote: 根据国家的id查询某国家的所有省份
     */
    public List<AddrState> SelectStates(long countryCode){
        return  addrStateService.selectAllAddrStatesByCountryCode(countryCode);
    }

    /**
     * Author: chenenru 19:34 2019/5/8
     * @param stateCode
     * @return List<AddrCity>
     * @apiNote: 根据省份的编码查询所有的城市
     */
    public List<AddrCity> SelectCities(long stateCode){
        return addrCityService.selectAllAddrCitysByStateCode(stateCode);
    }

    /**
     * Author: chenenru 19:41 2019/5/8
     * @param cityCode
     * @return List<AddrArea>
     * @apiNote: 根据城市的编码查询所有的县/区
     */
    public List<AddrArea> SelectAreas(long cityCode){
        return  addrAreaService.selectAllAddrAreasByCityCode(cityCode);
    }

    /**
     * Author: chenenru 20:07 2019/5/8
     * @param areaCode
     * @return List<AddrStreet>
     * @apiNote: 根据县/区的编码查询所有的街道
     */
    public List<AddrStreet> SelectStreets(long areaCode){
        return addrStreetService.selectStreetsByAreaCode(areaCode);
    }

}
