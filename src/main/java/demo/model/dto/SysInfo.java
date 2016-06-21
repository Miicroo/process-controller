package demo.model.dto;

import java.util.Map;

public class SysInfo {
    private Map<String, String> sysMap, userMap, networkMap;

    public SysInfo(Map<String, String> sysMap, Map<String, String> userMap, Map<String, String> networkMap) {
        this.sysMap = sysMap;
        this.userMap = userMap;
        this.networkMap = networkMap;
    }

    public Map<String, String> getSysMap() {
        return sysMap;
    }

    public Map<String, String> getUserMap() {
        return userMap;
    }

    public Map<String, String> getNetworkMap() {
        return networkMap;
    }
}
