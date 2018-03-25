package com.craw.httpHandler;

import com.craw.config.BaseConfig;
import com.craw.model.Student;
import com.springboot.dao.AreaDao;
import com.springboot.utils.http.HttpUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zbww on 2018/3/22.
 */
public class httpHandler {

    @Autowired
    private AreaDao areaDao;

    public static void main(String[] args) {

        String s = HttpUtil.get("http://avlang18.com/thread-htm-fid-115-page-" + 1 + ".html", "gbk");

//        Document doc = Jsoup.parse(s);

//        Elements allElements = doc.body().getAllElements();
//
//        for (Element elements : allElements) {
//            elements.getAllElements();
//            String body = elements.getElementsByTag("body").toString();
//            //(subject">)$
//            Pattern pattern = Pattern.compile("^(<a)");
//            Matcher matcher = pattern.matcher(body);
//            while (matcher.find()) {
//                System.out.println(matcher.group(1));
//            }
//        }

    }

    @Test
    public void test() {
        //String s="<p id=km> <a href=http://down.yourweb.com>空间</a> | <a ";
        //String s = "</p><p style=height:14px><a href=http://mb.yourweb.com>企业推广</a> | <a href=http://code.yourweb.com>搜索风云榜</a> | <a href=/home.html>关于百度</a> | <a href=http://www.yourweb.com>About Baidu</a></p><p id=b>©2008 Baidu <a href=http://www.yourweb.com>使用百度前必读</a> <a href=http://www.miibeian.gov.cn target=_blank>京ICP证03xxxx号</a> <a href=http://www.jb51.net><img src=/get_pic/2013/11/22/20131122031447947.gif></a></p></center></body></html><!--543ff95f18f36b11-->";

        List<Map<String, String>> resultList = new ArrayList<>();

        List<Student> insertList = new ArrayList<>();


        for (int i = 1; i <= 360; i++) {
            String s = HttpUtil.get("http://avlang18.com/thread-htm-fid-115-page-" + i + ".html", "gbk");
            //String s = HttpUtil.get("http://avlang18.com/thread-htm-fid-4-page-" + i + ".html", "gbk");


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
                    sbf.append("http://avlang18.com"+"/"+url);
                }
                for (String str : split) {
                    if (sbf.toString().contains(str)) {
                        String[] results = sbf.toString().split("&&");
                        Map<String, String> map = new HashMap<>();
                        map.put("title", results[0]);
                        String[] urls = results[1].split(" ");
                        map.put("url", urls[0]);
                        resultList.add(map);

                    }
                }
            }
        }

        for (Map<String, String> str : resultList) {
            System.out.println(str);
        }
    }
}
