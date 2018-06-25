package special.consistenthashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * A simple example of implementing consistent hash.
 * 1. Using TreeMap to store all key values in order and find the nearest node(virtual) by getting tailmap.
 * 2. Adding a limited number of virtual node when adding a new physical node to distribute evenly requests.
 * 3. Using a hashmap to map physical node to several virtual nodes for quick find.
 */
public class ConsistentHashRouter<T extends Node> {

    private final SortedMap<Long, VirtualNode<T>> ring = new TreeMap<>();
    private final HashFunction hashFunction;
    private final Map<String, Set<String>> nodeMap = new HashMap<>();


    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount) {
        this(pNodes,vNodeCount, new MD5Hash());
    }

    public ConsistentHashRouter(Collection<T> pNodes, int vNodeCount, HashFunction hashFunction) {
        if (hashFunction == null) {
            throw new NullPointerException("Hash Function is null");
        }
        this.hashFunction = hashFunction;
        if (pNodes != null) {
            for (T pNode : pNodes) {
                addNode(pNode, vNodeCount);
            }
        }
    }

    /**
     * Add physic node to the hash ring with some virtual nodes
     **/
    public void addNode(T pNode, int vNodeCount) {
        if(vNodeCount < 0) {
            throw new IllegalArgumentException("illegal virtual node counts :" + vNodeCount);
        }
        int existingReplicas = getExistingReplicas(pNode);
        for (int i = 0; i < vNodeCount; i++) {
            VirtualNode<T> vNode = new VirtualNode<>(pNode, i + existingReplicas);
            ring.put(hashFunction.hash(vNode.getKey()), vNode);
            if(nodeMap.containsKey(pNode.getKey())) {
                nodeMap.get(pNode.getKey()).add(vNode.getKey());
            } else {
                Set<String> set = new HashSet<>();
                set.add(vNode.getKey());
                nodeMap.put(pNode.getKey(), set);
            }
        }
    }

    public void removeNode(T pNode) {
        if(!nodeMap.containsKey(pNode.getKey())) {
            return;
        }
        for(String vNodeKey : nodeMap.get(pNode.getKey())) {
            ring.remove(hashFunction.hash(vNodeKey));
        }
        nodeMap.remove(pNode.getKey());
    }

    public T routeNode(String objectKey) {
        if (ring.isEmpty()) {
            return null;
        }
        Long hashVal = hashFunction.hash(objectKey);
        SortedMap<Long,VirtualNode<T>> tailMap = ring.tailMap(hashVal);
        Long nodeHashVal = tailMap.isEmpty() ? ring.firstKey() : tailMap.firstKey();
        return ring.get(nodeHashVal).getPhysicalNode();
    }

    public int getExistingReplicas(T pNode) {
        return nodeMap.containsKey(pNode.getKey()) ? nodeMap.get(pNode.getKey()).size() : 0;
    }

    private static class MD5Hash implements HashFunction {
        MessageDigest instance;
        public MD5Hash() {
            try {
                instance = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                System.err.print(e);
            }
        }
        @Override
        public long hash(String key) {
            instance.reset();
            instance.update(key.getBytes());
            byte[] digest = instance.digest();

            long h = 0;
            for (int i = 0; i < 4; i++) {
                h <<= 8;
                h |= ((int) digest[i]) & 0xFF;
            }
            return h;
        }
    }
}
