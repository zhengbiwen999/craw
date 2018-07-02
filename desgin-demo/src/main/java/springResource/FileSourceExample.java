package springResource;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.WritableResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileSourceExample {

    public static void main(String[] args) throws IOException {

        String filePath = "/Users/bwz/Downloads/text.txt";

        // 使用系统文件路径方式加载文件
        WritableResource res1 = new PathResource(filePath);

        // 使用类路径方式加载
        //new ClassPathResource()

        //使用 writableResource接口写资源文件
        OutputStream stream1 =  res1.getOutputStream();
        stream1.write("欢迎光临".getBytes());
        stream1.close();

        //使用 Resource 接口读取资源文件
        InputStream ins1 =  res1.getInputStream();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = ins1.read()) != -1){
            baos.write(i);
        }

        System.out.println(baos.toString());
        System.out.println("res1 : "+res1.getFilename());
    }

}
