package special.consistenthashing;

import java.util.Arrays;

/**
 *
 */
public class ConsistentHashTest {

    public static void main(String[] args) {

        PhysicalCacheNode node1 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8080);
        PhysicalCacheNode node2 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8081);
        PhysicalCacheNode node3 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8082);
        PhysicalCacheNode node4 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8084);
        PhysicalCacheNode node5 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8085);
        PhysicalCacheNode node6 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8086);
        PhysicalCacheNode node7 = new PhysicalCacheNode("IDC1", "127.0.0.1", 8087);

        ConsistentHashRouter<PhysicalCacheNode> consistentHashRouter =
                new ConsistentHashRouter<>(Arrays.asList(node1, node2), 10);

        String requestIP1 = "192.168.0.1";
        String requestIP2 = "192.168.0.2";
        String requestIP3 = "192.168.0.3";
        String requestIP4 = "192.168.0.4";
        String requestIP5 = "192.168.0.5";

        goRoute(consistentHashRouter,requestIP1,requestIP2,requestIP3,requestIP4,requestIP5);

        consistentHashRouter.addNode(node3, 10);
        consistentHashRouter.addNode(node4, 10);
        consistentHashRouter.addNode(node5, 10);
        consistentHashRouter.addNode(node6, 10);
        consistentHashRouter.addNode(node7, 10);
        //consistentHashRouter.addNode(node7, 10);

        System.out.println("-------------putting new node online ------------");

        String requestIp = "192.168.0.";
        for(int i = 0; i < 50; i++) {
            System.out.println(requestIp + i + " is route to " + consistentHashRouter.routeNode(requestIp + i));
        }

        System.out.println("-------------deleting node online ------------");
        consistentHashRouter.removeNode(node7);

        String requestIp1 = "192.168.0.";
        for(int i = 0; i < 50; i++) {
            System.out.println(requestIp1 + i + " is route to " + consistentHashRouter.routeNode(requestIp1 + i));
        }
    }

    private static void goRoute(ConsistentHashRouter<PhysicalCacheNode> consistentHashRouter , String ... requestIps){
        for (String requestIp: requestIps) {
            System.out.println(requestIp + " is route to " + consistentHashRouter.routeNode(requestIp));
        }
    }

}
