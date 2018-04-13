package zbw.netty4.model;

import lombok.Data;

@Data
public class Request {

    private short module;

    private short cmd;

    private byte[] data;

//    public Request(short module, short cmd, byte[] data) {
//        this.module = module;
//        this.cmd = cmd;
//        this.data = data;
//    }
}
