package cn.oneplustow.common.constant;

/**
 * @author cc
 */
public class DbConstants {
    public interface SteamServerStatus{
        /**等待连接*/
        String WAIT_CONNECTION = "0";
         /**连接成功*/
        String CONNECTION_SUCCESS = "1";
        /**连接失败*/
        String CONNECTION_FAILURE = "2";
    }
    public interface PalyRoomStatus{
        /**未开始*/
        String NOT_START = "0";
        /**等待推流*/
        String WAIT_PUSH = "1";
        /**开始直播*/
        String START = "2";
    }
}
