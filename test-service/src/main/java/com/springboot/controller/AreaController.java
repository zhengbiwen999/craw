package com.springboot.controller;

import com.craw.config.BaseConfig;
import com.craw.model.Student;
import com.springboot.domain.AreaEntity;
import com.springboot.service.AreaService;
import com.springboot.utils.http.HttpUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class AreaController {

    private final Logger logger = Logger.getLogger(AreaController.class);

    @Autowired
    private AreaService areaService;

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/")
    public String ping(){
        ServiceInstance localServiceInstance = client.getLocalServiceInstance();
        logger.info(localServiceInstance.getHost()+" : "+localServiceInstance.getPort());
        return "OK";
    }

    @RequestMapping("/getAll")
    public List<AreaEntity> getAllAreas(){
        return areaService.getAllArea();
    }

    @RequestMapping("/insert")
    public int insert(){

        List<Student> insertList = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            String s = HttpUtil.get("http://avlang18.com/thread-htm-fid-115-page-" + i + ".html", "gbk");
//        Document doc = Jsoup.parse(s);
            String regex = "<a.*?/a>";
            //String regex = "<a.*>(.*)</a>";
            Pattern pt = Pattern.compile(regex);
            Matcher mt = pt.matcher(s);

            BaseConfig config = new BaseConfig();
            String[] split = config.getKeyWords().split(",");

            while (mt.find()) {
                System.out.println();
                String s2 = ">.*?</a>";//标题部分
                String s3 = "href=.*?>";
                //标题
                Pattern pt2 = Pattern.compile(s2);
                Matcher mt2 = pt2.matcher(mt.group());
                //网址
                Pattern pt3 = Pattern.compile(s3);
                Matcher mt3 = pt3.matcher(mt.group());
                StringBuffer sbf = new StringBuffer();

                while (mt2.find()) {
                    sbf.append(mt2.group().replaceAll(">|</a>", "") + "&&");
                }
                while (mt3.find()) {
                    String url = mt3.group().replaceAll("href=|>", "").toString();
                    sbf.append(url);
                }
                for (String str : split) {
                    if (sbf.toString().contains(str)) {
                        String[] results = sbf.toString().split("&&");
//                        Map<String, String> map = new HashMap<>();
//                        map.put("title", results[0]);
                        String[] urls = results[1].split(" ");
//                        map.put("url", urls[0]);
                        Student student = new Student();
                        student.setStuid(results[0]);
                        student.setStunm("http://avlang18.com/"+urls[0]);
//                        resultList.add(map);
                        insertList.add(student);

                    }
                }
            }
        }

        return areaService.bashInsert(insertList);
    }

}
