package edu.uni.userBaseInfo1.utils;

import edu.uni.userBaseInfo1.bean.*;
import edu.uni.userBaseInfo1.service.*;
import edu.uni.userBaseInfo1.utils.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author laizhouhao
 * @Description
 * @Date 19:06 2019/5/8
 **/
@Component
public class GetAddrDetail{

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

    public UserInfo reviceAddrDetail(List<Address> addresses){
        UserInfo userInfo = new UserInfo();
        userInfo.setAddresses(addresses);
        //获取亲属的具体地址信息
        List<AddrCountry> addrCountries = new ArrayList<>();
        List<AddrState> addrStates = new ArrayList<>();
        List<AddrCity> addrCities = new ArrayList<>();
        List<AddrArea> addrAreas = new ArrayList<>();
        List<AddrStreet> addrStreets = new ArrayList<>();
        for(int i=0; i<addresses.size(); i++){
            addrCountries.add(addrCountryService.selectAddrCountryById(addresses.get(i).getCountry()));
            addrStates.add(addrStateService.selectAddrStateById(addresses.get(i).getState()));
            addrCities.add(addrCityService.selectAddrCityById(addresses.get(i).getCity()));
            addrAreas.add(addrAreaService.selectAddrAreaById(addresses.get(i).getArea()));
            addrStreets.add(addrStreetService.selectAddrStreetById(addresses.get(i).getStreet()));
        }
        userInfo.setAddrCountries(addrCountries);
        userInfo.setAddrStates(addrStates);
        userInfo.setAddrCities(addrCities);
        userInfo.setAddrAreas(addrAreas);
        userInfo.setAddrStreets(addrStreets);
        return userInfo;
    }
}
