package special.consistenthashing;

/**
 *
 */
public class PhysicalCacheNode implements Node {

    private final String idc;
    private final String ip;
    private final int port;

    public PhysicalCacheNode(String idc, String ip, int port) {
        this.idc = idc;
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getKey() {
        return idc + "-" + ip + ":" + port;
    }

    @Override
    public String toString(){
        return getKey();
    }
}
