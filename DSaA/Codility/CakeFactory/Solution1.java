import java.util.Arrays;

public class Solution1 {
    class Node{
        int firstLayer;
        int layerCount;


        public Node(int firstLayer, int layerCount){
            this.firstLayer = firstLayer;
            this.layerCount = layerCount;
        }

        public void merge(Node otherNode){
            //to current one we add the other one
            if (this.firstLayer == -1 || otherNode.firstLayer == -1){ // if one is bad - merged will be bad too
                this.firstLayer = -1;
                this.layerCount = 0;
            }
            else if (this.layerCount == 0){     //copy the whole node
                this.firstLayer = otherNode.firstLayer;
                this.layerCount = otherNode.layerCount;
            }
            else if (otherNode.layerCount == 0){    //if the other is empty - do nothing
                return;
            }
            // (1,3) + (4,2) ==> (1,2,3) + (4,5) ==> (1,2,3,4,5)
            else if (this.firstLayer + this.layerCount == otherNode.firstLayer){ //after connection will be still correct
                this.layerCount += otherNode.layerCount;
            }
            else {
                this.firstLayer = -1;
                this.layerCount = 0;
            }
        }

        public void clear(){
            this.firstLayer = 0;
            this.layerCount = 0;
        }

        @Override
        public String toString(){
            return "(" + this.firstLayer + ", " + this.layerCount + ")";
        }
    }

    ///initialize the segment tree
    int base;
    Node[] tree;
    int perfectOnes = 0;

    public void createTree(int neededLeafCount){
        int base = 1;
        while (base < neededLeafCount){
            base *= 2;
        }
        this.base = base;
        this.tree = new Node[base*2];
        for (int i = 0; i < base * 2; i++){
            this.tree[i] = new Node(0, 0);
        }
    }

    public void add(int start, int end, int layerValue){
        _add(1,1, this.base, start, end, layerValue);
    }

    public void _add(int currNode, int nodeStart, int nodeEnd, int start, int end, int layerValue){
        if (end < nodeStart || start > nodeEnd){ //intervals completely miss each other (no intersection)
            return;
        }
        else if (start <= nodeStart && nodeEnd <= end){
            // intervals match perfectly or is updated one is longer (we do not care about the part that is not in node range)
            // (it will be handled by other node)
            this.tree[currNode].merge(new Node(layerValue, 1));
//            System.out.println("Combine index: " + currNode + "," + this.tree[currNode]);
        }
        else{
            // only part of current Node range is updated so we need to go down (both ways)
            // move current Node value down
            this.tree[currNode * 2].merge(this.tree[currNode]);
            this.tree[currNode * 2 + 1].merge(this.tree[currNode]);
            this.tree[currNode].clear();

            // update lower Nodes
            int nodeMidPoint = (nodeStart + nodeEnd) / 2;
            _add(currNode * 2, nodeStart, nodeMidPoint, start, end, layerValue);
            _add(currNode * 2 + 1, nodeMidPoint + 1, nodeEnd, start, end, layerValue);
        }
    }

    public Node getNode(int index){
        return this.tree[index];
    }

    public void showTree(){
        System.out.println("Tree : " + Arrays.toString(this.tree));
    }

    public void countPerfectOnes(int K, int currNode){
//        if (this.tree[currNode].firstLayer == -1){
//            return;
//        }
//        else if (this.tree[currNode].firstLayer == 1 && this.tree[currNode].layerCount == K){
//            if (currNode > base -1){
//                this.perfectOnes++;
//            }
//            else if (currNode % 2 == 0){
//                this.perfectOnes += (base / currNode);
//                return;
//            }
//            else {
//                this.perfectOnes += (base / (currNode - 1));
//                return;
//            }
//        }
//        else if (currNode > base-1){
//            return;
//        }
//        else if (this.tree[currNode].firstLayer != 1 && this.tree[currNode].firstLayer != 0){
//            this.tree[currNode * 2].merge(this.tree[currNode]);
//            this.tree[currNode * 2 + 1].merge(this.tree[currNode]);
//            this.tree[currNode].clear();
//
//            int nodeMidPoint = (nodeStart + nodeEnd) / 2;
//            countPerfectOnes(K, currNode * 2, nodeStart, nodeMidPoint);
//            countPerfectOnes(K, currNode * 2 + 1, nodeMidPoint + 1, nodeEnd);
//        }
//        else{
//            //we go deeper if we can
//            boolean canWeGoDeeper = true;
//            if (currNode > base-1){
//                canWeGoDeeper = false;
//            }
//
//            if (canWeGoDeeper) {
//                int nodeMidPoint = (nodeStart + nodeEnd) / 2;
//                countPerfectOnes(K, currNode * 2, nodeStart, nodeMidPoint);
//                countPerfectOnes(K, currNode * 2 + 1, nodeMidPoint + 1, nodeEnd);
//            }
//        }
        if (currNode > base -1){ // if we are at the leaf then check if cake is correct
            if (this.tree[currNode].firstLayer == 1 && this.tree[currNode].layerCount == K){
                this.perfectOnes++;
            }
        }
        else{
            // move all values to the bottom
            tree[currNode * 2].merge(this.tree[currNode]);
            tree[currNode * 2 + 1].merge(this.tree[currNode]);
            countPerfectOnes(K, currNode * 2);
            countPerfectOnes(K, currNode * 2 + 1);
        }
    }

    public int solution(int N, int K, int[] A, int[] B, int[] C){
        createTree(N);
//        showTree();

        for (int i = 0; i < A.length; i++){
            int start = A[i];
            int end = B[i];
            int layerValue = C[i];
//            System.out.println("Operation: " + start + " - " + end + " L: " + layerValue);
            add(start, end, layerValue);
//            showTree();
//            System.out.println('\n');
        }

        countPerfectOnes(K, 1);
        return perfectOnes;
    }

}
