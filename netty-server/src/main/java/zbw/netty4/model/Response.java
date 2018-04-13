package zbw.netty4.model;

import lombok.Data;

@Data
public class Response {
	
	/**
	 * 模块号
	 */
	private short module;
	
	/**
	 * 命令号
	 */
	private short cmd;
	
	/**
	 * 结果码
	 */
	private int stateCode = ResultCode.SUCCESS;
	
	/**
	 * 数据
	 */
	private byte[] data;
	
	public Response() {
	}
	
	public Response(Request message) {
		this.module = message.getModule();
		this.cmd = message.getCmd();
	}
	
	public Response(short module, short cmd, byte[] data){
		this.module = module;
		this.cmd = cmd;
		this.data = data;
	}
}
