package com.springboot.utils.date;

import org.apache.commons.lang3.StringUtils;
import java.io.IOException;
import java.net.*;
import java.util.Enumeration;

/**
 */
public class NetworkUtil {

    private volatile static String ipStr;

    private volatile static Long ipLong;

    /**
     * 移除端口
     *
     * @param ip
     * @return
     */
    public static String removePort(String ip) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        int i = ip.indexOf(":");
        if (i != -1) {
            return ip.substring(0, i);
        }
        return ip;
    }

    /**
     * 获取主机IP地址
     *
     * @return
     */
    public static String getHostAddress() {
        if (ipStr == null) {
            synchronized (NetworkUtil.class) {
                if (ipStr == null) {
                    ipStr = "127.0.0.1";
                    Enumeration allNetInterfaces;
                    try {
                        allNetInterfaces = NetworkInterface.getNetworkInterfaces();
                    } catch (SocketException e) {
                        return ipStr;
                    }
                    InetAddress ip;
                    while (allNetInterfaces.hasMoreElements()) {
                        NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                        Enumeration addresses = netInterface.getInetAddresses();
                        while (addresses.hasMoreElements()) {
                            ip = (InetAddress) addresses.nextElement();
                            if (ip != null && ip instanceof Inet4Address && !ip.getHostAddress().equals(ipStr)) {
                                ipStr = ip.getHostAddress();
                                return ipStr;
                            }
                        }
                    }
                }
            }
        }
        return ipStr;
    }

    /**
     * 获取Long类型ip
     *
     * @return
     */
    public static Long getIpLong() {
        if (ipLong == null) {
            synchronized (NetworkUtil.class) {
                if (ipLong == null) {
                    String ip = getHostAddress().trim();
                    String[] ips = ip.split("\\.");
                    long ip1 = Integer.parseInt(ips[0]);
                    long ip2 = Integer.parseInt(ips[1]);
                    long ip3 = Integer.parseInt(ips[2]);
                    long ip4 = Integer.parseInt(ips[3]);
                    ipLong = 1L * ip1 * 256 * 256 * 256 + ip2 * 256 * 256 + ip3 * 256 + ip4;
                }
            }
        }
        return ipLong;
    }

    /**
     * 获取主机计算机名
     *
     * @return
     */
    public static String getHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
//            logger.error("NetworkUtil.getHostName error:", e);
        }
        return getMachineName();
    }

    private static String getMachineName() {
        String result = "localhost";
        Enumeration allNetInterfaces;
        try {
            allNetInterfaces = NetworkInterface.getNetworkInterfaces();
        } catch (SocketException e) {
            e.printStackTrace();
            return result;
        }
        InetAddress ip;
        while (allNetInterfaces.hasMoreElements()) {
            NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
            Enumeration addresses = netInterface.getInetAddresses();
            while (addresses.hasMoreElements()) {
                ip = (InetAddress) addresses.nextElement();
                if (ip != null && ip instanceof Inet4Address && !ip.getHostName().equals(result)) {
                    return ip.getHostName();
                }
            }
        }
        return result;
    }

    /**
     * 判断端口是否被占用
     *
     * @param port
     * @return
     */
    public static boolean isLoclePortUsing(int port) {
        boolean flag = true;
        try {
            flag = isPortUsing("127.0.0.1", port);
        } catch (Exception e) {
        }
        return flag;
    }

    public static boolean isPortUsing(String host, int port) throws UnknownHostException {
        boolean flag = false;
        InetAddress theAddress = InetAddress.getByName(host);
        System.out.println(theAddress);
        try {
            ServerSocket socket = new ServerSocket(port);
            flag = true;
        } catch (IOException e) {
//            logger.error("isPortUsing error=>" + host + ":" + port, e);
        }
        return flag;
    }
}

