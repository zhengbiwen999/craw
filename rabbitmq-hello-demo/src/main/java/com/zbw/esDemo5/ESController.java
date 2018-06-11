package com.zbw.esDemo5;

import com.zbw.esDemo5.module.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ESController {

    @Autowired
    private CityService cityService;

    /**
     * http://localhost:8083/api/city
      {
        "id":"1234",
        "provinceid":"1",
        "cityname":"啊啊啊啊",
        "description":"abcdad"
     }
     */
    @RequestMapping(value = "/api/city", method = RequestMethod.POST)
    public Long createCity(@RequestBody City city) {
        return cityService.saveCity(city);
    }

    @RequestMapping(value = "/api/citys", method = RequestMethod.GET)
    public List<City> getCitys() {
        return cityService.getCitys();
    }

    @RequestMapping(value = "/api/city/search", method = RequestMethod.GET)
    public List<City> searchCity(@RequestParam(value = "pageNumber") Integer pageNumber,
                                 @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                 @RequestParam(value = "searchContent") String searchContent) {
        return cityService.searchCity(pageNumber,pageSize,searchContent);
    }

}
