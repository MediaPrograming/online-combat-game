package game.config;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Takuya Isaki on 2021/02/05
 * @project online-combat-game
 */


public class IPEndpoint {

    @SerializedName
            ("ip")
    @Expose

    private String ip;
    @SerializedName
            ("port")
    @Expose

    private Integer port;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

}