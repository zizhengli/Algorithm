package special.consistenthashing;

/**
 * Created by lim20 on 6/25/2018.
 */
public class VirtualNode<T extends Node> implements Node {

    final T physicalNode;
    final int replicaIndex;

    public VirtualNode(T physicalNode, int replicaIndex) {
        this.replicaIndex = replicaIndex;
        this.physicalNode = physicalNode;
    }

    public boolean isVirtualNodeOf(T pNode) {
        return physicalNode.getKey().equals(pNode.getKey());
    }

    public T getPhysicalNode() {
        return physicalNode;
    }

    @Override
    public String getKey() {
        return physicalNode.getKey() + "-" + replicaIndex;
    }
}
