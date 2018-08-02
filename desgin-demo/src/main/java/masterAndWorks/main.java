package masterAndWorks;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class main {

    public static void main(String[] args) throws Exception{

        long begin = System.currentTimeMillis();

        Master m = new Master(new PlusWorker(),30);
        for(int i=0;i<1000;i++){
            m.submit(i);
        }

        m.execute();

        int result = 0;

        Map<String,Object> resultMap = m.getResultMap();

        //不需要等所有worker执行完，即可开始计算最终结果
        while (resultMap.size() >0 || !m.isComplete()){
            Set<String> keys = resultMap.keySet();
            String key = null;

            for(String k : keys){
                key = k;
                break;
            }

            Integer i = null;
            if(key != null)
                i = (Integer) resultMap.get(key);
            if(i != null)
                result += i;                //最终结果
            if(key != null)
                resultMap.remove(key);  //移除已计算过的
        }
        long end = System.currentTimeMillis();
        Thread.sleep(10000);
        System.out.println("耗时："+(end-begin) + "，结果："+result);
    }

    @Test
    public void test() throws Exception{
        long begin = System.currentTimeMillis();
        Integer result = 0;
        for(int i=0;i<1000;i++){
            Thread.sleep(20);
            result +=i*i*i;
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-begin) + "，结果："+result);
    }

}
