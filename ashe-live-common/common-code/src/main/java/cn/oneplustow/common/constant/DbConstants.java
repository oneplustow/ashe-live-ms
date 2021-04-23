package cn.oneplustow.common.constant;

/**
 * @author cc
 */
public class DbConstants {
    public interface SteamServerStatus{
        String WAIT_CONNECTION = "0";
        String CONNECTION_SUCCESS = "1";
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
