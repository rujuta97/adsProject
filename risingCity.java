import java.io.*;
import java.lang.*;
import java.util.*;

//creation of the building class where the building parameters : building num, execution time and total time is initialised.
class Building{
    int buildingNum;
    int exTime;
    int tTime;
    Building (int buildingNum, int exTime, int tTime){
        this.buildingNum = buildingNum;
        this.exTime = exTime;
        this.tTime = tTime;
    }

}

public class risingCity {

    private static Building[] Heap;
    private int size=0;
    private int max;

    private static final int FRONT = 1;


    public risingCity(int max)
    {
        this.max = max;
        this.size = 0;
        Heap = new Building[this.max + 1];
    }

    //this function checks if the node is a leaf node or not
    boolean isLeaf(int loc)
    {
        if (loc >= ((size / 2)+1) && loc <= size) {
            return true;
        }
        return false;
    }
    //to swap the building number with smallest execution time
    void exchange(int first, int sec)
    {
        Building temp;
        temp = Heap[first];
        Heap[first] = Heap[sec];
        Heap[sec] = temp;
    }
    //to insert a new node into the heap
    public void insert(Building b)
    {
        if(size>=max){
            return;
        }
           Heap[++size] = b;
        int curr = size;
 
        }
        
    
    //used to call the heap creation function to ensure the the heap is always min heapified
    public void minHeap()
    {
        for (int curr = (size / 2); curr >= 1; curr--) {
            heapCreation(curr);
        }
    }
    // to print the building data 
    public void print (int buildingNum){
        /*for(int i=1 ; i< size ; i++){
             //System.out.println(Heap[i].buildingNum + "," + Heap[i].exTime  + "," + Heap[i].tTime);
            System.out.print(" pp : " + Heap[i].buildingNum
                    + " LEFT CHILD : " + Heap[2*i].buildingNum
                    + " RIGHT CHILD :" + (Heap[2*i+1].buildingNum));
            System.out.println();
        }*/

        System.out.println(1);
        
    }
    // prints the building data for all buildings having building number between b1 and b2
    public void printHeap(int b1, int b2)
    {

        for (int i = 1; i < size; i++) {
            if(Heap[i].buildingNum >= b1 || Heap[i].buildingNum <= b2)
            {
                System.out.println(Heap[i].buildingNum);
                System.out.println(Heap[i].tTime);
                System.out.println(Heap[i].exTime);
            } else{
                System.out.println("0,0,0");
            }
        }
    }
    // to remove all the buildings from the minheap for which executed time = total time
    public void remove(int global_time)
    {
        System.out.println(Heap[1].buildingNum+" "+global_time);
        if(size>0) {
            Heap[1] = Heap[size--];
            Heap[size+1] = null;
            minHeap();

        }
    }
    //minheapify function
    private void heapCreation(int curr)
    {
        if (!isLeaf(curr)) {
            if (Heap[2*curr+1] != null && Heap[2*curr] != null) {
                //when the execution time of current node is greater than the execution time of its left and right children
                if (Heap[curr].exTime > Heap[2 * curr].exTime
                        || Heap[curr].exTime > Heap[2 * curr + 1].exTime) {
                    //compares which of the children(i.e left or right) have a smaller execution time
                    if (Heap[2 * curr].exTime < Heap[2 * curr + 1].exTime) {
                        // if left child has a smaller value, exchange with left
                        exchange(curr, 2 * curr);
                        heapCreation(2 * curr);
                    } else if (Heap[2 * curr].exTime > Heap[2 * curr + 1].exTime){
                        //if right child has a smaller value, exchange with right
                        exchange(curr, 2 * curr + 1);
                        heapCreation(2 * curr + 1);
                    }
                    //if the execution time of both left child and right child is the same, swap according to the building number 
                    else {
                        //checks if the building number of left child is smaller than the building number of right child
                        if(Heap[2 * curr].buildingNum < Heap[2 * curr + 1].buildingNum){
                            //if building number of left child is smaller, exchange current with left child
                            exchange(curr, 2 * curr);
                            heapCreation(2 * curr);
                        } 
                        else{
                            //if building number of right child is smaller, exchange current with right child
                            exchange(curr, 2 * curr+1);
                            heapCreation(2 * curr+1);
                        }
                    }
                }
                //if the execution time of left child, right child and and parent is the same
                else if(Heap[curr].exTime == Heap[2 * curr].exTime
                        && Heap[curr].exTime == Heap[2 * curr +1].exTime){
                    //checks if building number of parent node is greater than its left or right child
                    if (Heap[curr].buildingNum > Heap[2 * curr].buildingNum
                            || Heap[curr].buildingNum > Heap[2 * curr + 1].buildingNum){
                        //checks if the building number of the left child is smaller than the right child, exchange with left child
                        if (Heap[2 * curr].buildingNum < Heap[2 * curr + 1].buildingNum){
                            exchange(curr, 2 * curr);
                            heapCreation(2 * curr);
                        }
                        //if the building number of right child is smaller, exchange with the right child 
                        else{
                            exchange(curr, 2 * curr + 1);
                            heapCreation(2 * curr + 1);
                        }
                    }
                }
                //if the execution time of parent and left child is the same
                else if (Heap[curr].exTime == Heap[2 * curr].exTime) {
                    //if building number of parent is greater than the left child , exchange with the left child
                    if (Heap[curr].buildingNum > Heap[2 * curr].buildingNum) {
                        exchange(curr, 2 * curr);
                        heapCreation(2 * curr);
                    }

                } // if the execution time of parent and right child is the same
                else if (Heap[curr].exTime == Heap[2 * curr + 1].exTime) {
                    //if the building number of parent is greater than the right child, exchange with the right child  
                    if (Heap[curr].buildingNum > Heap[2 * curr + 1].buildingNum) {
                        exchange(curr, 2 * curr + 1);
                        heapCreation(2 * curr + 1);
                    }
                }
            }
            // if right child is null and left child is not null 
            if(Heap[2*curr+1] == null && Heap[2 * curr] != null) {
                //checks if the execution time of parent node is greater than the left child
                // if it is greater,exchange with the left child
                if (Heap[curr].exTime > Heap[2 * curr].exTime) {
                    exchange(curr, 2 * curr);
                    heapCreation(2 * curr);
                } 
                //if both parent node and left child have the same execution time
                else if (Heap[curr].exTime == Heap[2 * curr].exTime) {
                    //check if the building number of parent node is greater than the left child
                    //if it is greater, exchange with left child
                    if (Heap[curr].buildingNum > Heap[2 * curr].buildingNum) {
                        exchange(curr, 2 * curr);
                        heapCreation(2 * curr);
                    }
                }
            }
             // if left child is null and right child is not null
            if(Heap[2*curr] == null && Heap[2 * curr+1] != null) {
                //checks if the execution time of parent node is greater than the right child
                // if it is greater,exchange with the right child
                if (Heap[curr].exTime > Heap[2 * curr+1].exTime) {
                    exchange(curr, 2 * curr+1);
                    heapCreation(2 * curr+1);
                }
                 //if both parent node and right child have the same execution time
                else if (Heap[curr].exTime == Heap[2 * curr+1].exTime) {
                    //check if the building number of parent node is greater than the right child
                    //if it is greater, exchange with right child
                    if (Heap[curr].buildingNum > Heap[2 * curr+1].buildingNum) {
                        exchange(curr, 2 * curr+1);
                        heapCreation(2 * curr+1);
                    }
                }
            }
        }
    }


    public static void main(String[] args) throws IOException
    {
        try{

            System.out.println("Buildings sorted according to their execution time :");
            // creates an object of the minHeap
            risingCity minHeap = new risingCity(2000);
            //Reads the input file
            BufferedReader br = new BufferedReader(new FileReader(args[0]));
            //writes the output to an output file
            BufferedWriter w = new BufferedWriter(new FileWriter("output_file.txt"));
            String s;
            //
            int global_time=0;
            //creates an object of the rbtree
            RedBlackTree rbtree = new RedBlackTree();
            Building c;
            int init=0;
            Building o;
            // Initialisation of array of objects of building
            Heap[0] = new Building(0,-999,0);

            //loop executes till all the data is read from the input file
            while((s = br.readLine())!=null){
                //Checks the global time
                while (Integer.valueOf(s.substring(0, s.indexOf(':'))) >= global_time) {
                    //Loop is executed if the operation to be performed is printBuilding
                    if(Integer.parseInt(s.substring(0, s.indexOf(':'))) == global_time && s.substring(s.indexOf(':') + 2, 
                        s.indexOf(':') + 15).equals("PrintBuilding")){
                        //checks if there are 1 or 2 parameters 
                        if(s.indexOf(',')!=-1){
                            //stores both parameters 
                            String m = rbtree.printRange(Integer.parseInt(s.substring(s.indexOf("(")+1,
                            s.indexOf(","))),Integer.parseInt(s.substring(s.indexOf(",")+1,s.indexOf(")"))));
                            System.out.println("In"+m);
                            m = m.substring(0,m.length()-1);
                            m = m + "\n";
                            w.write(m);
                            System.out.println(m);
            }
            //stores one parameter
            else{
            String m = rbtree.printSP(Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(")"))))+"\n";
                        w.write(m);
        }
    }       
            //checks if the executed time is equal to the total time
            if(minHeap.size != 0 && Heap[1].tTime == Heap[1].exTime){
                c = new Building(0,0,0);
                c = Heap[1];
                String q = "("+Heap[1].buildingNum+","+global_time+")\n";
                w.write(q);
                rbtree.OUT(2,c);
                //removes the element for which work is done
                minHeap.remove(global_time);
                //resets the counter
                init = 0;

            }
                    //Loop is executed if the operation to be performed is insert
                    // Extracts the building number and total time from the input file
                    if (s.substring(s.indexOf(':')+2, s.indexOf(':')+8).equals("Insert")
                            && Integer.valueOf(s.substring(0, s.indexOf(':'))) == global_time) {
                        c = new Building(Integer.parseInt(s.substring(s.indexOf("(")+1,s.indexOf(","))),0,Integer.parseInt(s.substring(s.indexOf(",")+1,s.indexOf(")"))));
                        minHeap.insert(c);
                       
                        rbtree.OUT(1,c);
                        //calls the minheap function after every 5 days
                        if(global_time%5 == 0){
                            minHeap.minHeap();
                        }
                    } 
        
                    //continuously increments the global_time and init variable
                    global_time += 1;
                    init += 1;
                    if (minHeap.size != 0)
                        Heap[1].exTime += 1;
                    // checks if the executed time is equal to total time for any building in the heap.
                    if(minHeap.size != 0)

                    // Minheapify function is called after every 5 days and the reset counter becomes 0.
                    if(init == 5 && Heap[1].tTime != Heap[1].exTime){
                        minHeap.minHeap();
                        init = 0;
                    }


                }

            }
            //once all the inserts are performed the global time value is decremented by 5 if the value isnt a multiple of 5
            if(global_time %5 != 0) {
                Heap[1].exTime -= global_time % 5;
                global_time -= global_time % 5;

            }

            init = 0;
            //checks if the execution time is not equal to total time and size of minheap is not zero
            while(Heap[1].exTime!=Heap[1].tTime && minHeap.size > 0) {
                //checks if the remaining execution time for a building is less than 5
                if (Heap[1].tTime - Heap[1].exTime < 5) {
                    //increase the global_time by the remaining execution time
                    global_time += Heap[1].tTime - Heap[1].exTime;
                    Heap[1].exTime += (Heap[1].tTime - Heap[1].exTime);
                    String x = "("+Heap[1].buildingNum+","+global_time+")\n";
                    w.write(x);
                    //removes the building for which the execution time is equal to the total time
                    minHeap.remove(global_time);
    }
    //if the remaining time is equal to 5, increment execution time and global time by 5
    else if(Heap[1].tTime - Heap[1].exTime == 5){
        Heap[1].exTime += 5;
        global_time += 5;
        String y = "("+Heap[1].buildingNum+","+global_time+")\n";
        w.write(y);
        minHeap.remove(global_time);
    }
    //if the remaining time is greater than 5, increment global_time and execution time of that building by 5 
    else{
        Heap[1].exTime += 5;
        global_time += 5;
        minHeap.minHeap();
    }
    // checks if the size of heap is zero
    if (minHeap.size == 0)
        break;
            }
            w.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
class RedBlackTree {
    //initialise the red and black colour
    private final int rColour = 0;
    private final int bColour = 1;

    private class Node {

        Building pointer;
        int colour = bColour;
        Node lChild = nl, rChild = nl, pp = nl;

        Node(Building pointer) {
            this.pointer = pointer;
        }
    }
    //creates a new instance of the node(i.e the building)
    private final Node nl = new Node(new Building(-1,-1,-1));
    private Node root = nl;
    //used to print the leftchild and rightchild of a node
    public void PRTree(Node node) {
        if (node == nl) {
            return;
        }
        PRTree(node.lChild);
        PRTree(node.rChild);
    }

    private Node nodeLookup(Node nodeLookup, Node node) {
        if (root == nl) {
            return nl;
        }

        if (nodeLookup.pointer.buildingNum < node.pointer.buildingNum) {
            if (node.lChild != nl) {
                return nodeLookup(nodeLookup, node.lChild);
            }
        } else if (nodeLookup.pointer.buildingNum > node.pointer.buildingNum) {
            if (node.rChild != nl) {
                return nodeLookup(nodeLookup, node.rChild);
            }
        } else if (nodeLookup.pointer.buildingNum == node.pointer.buildingNum) {
            return node;
        }
        return nl;
    }
    //when only one node is present i.e node is equal to root, it is initialised to black
    private void insert(Node node) {
        Node tmp = root;
        if (root == nl) {
            root = node;
            node.colour = bColour;
            node.pp = nl;
        }
        //if not root new node inserted is initialised to red colour and root becomes black
        else {
            node.colour = rColour;
            while (true) {
                if (node.pointer.buildingNum < tmp.pointer.buildingNum) {
                    if (tmp.lChild == nl) {
                        tmp.lChild = node;
                        node.pp = tmp;
                        break;
                    } else {
                        tmp = tmp.lChild;
                    }
                } else if (node.pointer.buildingNum >= tmp.pointer.buildingNum) {
                    if (tmp.rChild == nl) {
                        tmp.rChild = node;
                        node.pp = tmp;
                        break;
                    } else {
                        tmp = tmp.rChild;
                    }
                }
            }
            FixT(node);
        }
    }

    
    private void FixT(Node node) {
        while (node.pp.colour == rColour) {
            Node rel = nl;
            if (node.pp == node.pp.pp.lChild) {
                rel = node.pp.pp.rChild;
                //checks if the uncle is not root and uncle's colour is red
                if (rel != nl && rel.colour == rColour) {
                    //make the colour of parent as black
                    node.pp.colour = bColour;
                    //make uncle's colour as black
                    rel.colour = bColour;
                    //make the colour of grandparent as red
                    node.pp.pp.colour = rColour;
                    //make node's grandparent as the node
                    node = node.pp.pp;
                    continue;
                }
                if (node == node.pp.rChild) {
                    
                    node = node.pp;
                    rotateLC(node);
                }
                node.pp.colour = bColour;
                node.pp.pp.colour = rColour;
                
                rotateRC(node.pp.pp);
            } else {
                rel = node.pp.pp.lChild;
                //if uncle is not root and uncle's colour is red
                if (rel != nl && rel.colour == rColour) {
                    //make parent black
                    node.pp.colour = bColour;
                    //make uncle's colour black
                    rel.colour = bColour;
                    //make grandparent's colour black
                    node.pp.pp.colour = rColour;
                    //make node's grandparent as node
                    node = node.pp.pp;
                    continue;
                }
                if (node == node.pp.lChild) {
                    
                    node = node.pp;
                    rotateRC(node);
                }
                node.pp.colour = bColour;
                node.pp.pp.colour = rColour;
                
                rotateLC(node.pp.pp);
            }
        }
        root.colour = bColour;
    }
    //rotate left child
    //incase of left left rotate, rotate grandparent to right and exchange the colours of parent and grandparent
    //incase of left right rotate, first left rotate the parent and then right rotate grandparent exchange the colours of parent and grandparent.
    void rotateLC(Node node) {
        //checks if parent is not root
        if (node.pp != nl) {
            if (node == node.pp.lChild) {
                node.pp.lChild = node.rChild;
            } else {
                node.pp.rChild = node.rChild;
            }
            node.rChild.pp = node.pp;
            node.pp = node.rChild;
            if (node.rChild.lChild != nl) {
                node.rChild.lChild.pp = node;
            }
            node.rChild = node.rChild.lChild;
            node.pp.lChild = node;
        } else {
        
            Node rChild = root.rChild;
            root.rChild = rChild.lChild;
            rChild.lChild.pp = root;
            root.pp = rChild;
            rChild.lChild = root;
            rChild.pp = nl;
            root = rChild;
        }
    }
    //rotate right child
    //incase of right right rotate first left rotate the grandparent and then exchange the colour of parent and grandparent.
    //incase of right left rotate first right rotate the parent and then left rotate the grandparent and then exchange the colours of parent and grandparent.
    void rotateRC(Node node) {
        if (node.pp != nl) {
            if (node == node.pp.lChild) {
                node.pp.lChild = node.lChild;
            } else {
                node.pp.rChild = node.lChild;
            }

            node.lChild.pp = node.pp;
            node.pp = node.lChild;
            if (node.lChild.rChild != nl) {
                node.lChild.rChild.pp = node;
            }
            node.lChild = node.lChild.rChild;
            node.pp.rChild = node;
        } else {
            Node lChild = root.lChild;
            root.lChild = root.lChild.rChild;
            lChild.rChild.pp = root;
            root.pp = lChild;
            lChild.rChild = root;
            lChild.pp = nl;
            root = lChild;
        }
    }
    
    void shift(Node pt, Node with){
        if(pt.pp == nl){
            root = with;
        }else if(pt == pt.pp.lChild){
            pt.pp.lChild = with;
        }else
            pt.pp.rChild = with;
        with.pp = pt.pp;
    }
    //prints the building data for all the buildings that lie between the range of buildingNum1 and buildingNum2
    String printRange(int b1,int b2)
    {
        Node cur=root;
        String s="";
        while(cur!=nl){
            if(cur.pointer.buildingNum>=b1 && cur.pointer.buildingNum<=b2){
                s = recurPrint(cur,b1,b2);
                return s;
                
            }
            else if(cur.pointer.buildingNum<b1){
                cur=cur.rChild;
            }
            else{
                cur=cur.lChild;
            }
        }
        return s;
    }

    String recurPrint(Node cur,int b1,int b2){
        if(cur== nl ) return "";
        if(cur.pointer.buildingNum==b1){
            return(recurPrint(cur.rChild,b1,b2)+"("+cur.pointer.buildingNum+","+ cur.pointer.exTime+","+ cur.pointer.tTime+"),");
        }
        else if(cur.pointer.buildingNum==b2){
            return(recurPrint(cur.lChild,b1,b2)+"("+cur.pointer.buildingNum+","+ cur.pointer.exTime+","+ cur.pointer.tTime+"),");
            
        }
        else
        {
            String m="";
            if(cur.lChild!= nl && inRange(cur.lChild.pointer.buildingNum, b1,b2)){
                m += recurPrint(cur.lChild,b1,b2);
            }
            m += "("+cur.pointer.buildingNum+","+ cur.pointer.exTime+","+ cur.pointer.tTime+"),";

            if(cur.rChild!= nl && inRange(cur.rChild.pointer.buildingNum,b1,b2))
                m += recurPrint(cur.rChild,b1,b2);
            return m;
        }
    }

    boolean inRange(int x,int l,int h){
        if(x>=l && x<=h)
            return true;
        return false;
    }
    //used to delete the given node from the tree
    boolean delete(Node z){
        if((z = nodeLookup(z, root))==nl)return false;
        Node x;
        Node y = z; 
        int y_original_colour = y.colour;

        if(z.lChild == nl){
            x = z.rChild;
            shift(z, z.rChild);
        }else if(z.rChild == nl){
            x = z.lChild;
            shift(z, z.lChild);
        }else{
            y = treeMin(z.rChild);
            y_original_colour = y.colour;
            x = y.rChild;
            if(y.pp == z)
                x.pp = y;
            else{
                shift(y, y.rChild);
                y.rChild = z.rChild;
                y.rChild.pp = y;
            }
            shift(z, y);
            y.lChild = z.lChild;
            y.lChild.pp = y;
            y.colour = z.colour;
        }
        if(y_original_colour==bColour)
            delFixUp(x);
        return true;
    }

    //checks if the remaining red black tree follows the properties of red black tree after deletion of node
    void delFixUp(Node x){
        while(x!=root && x.colour == bColour){
            if(x == x.pp.lChild){
                Node w = x.pp.rChild;
                if(w.colour == rColour){
                    w.colour = bColour;
                    x.pp.colour = rColour;
                    rotateLC(x.pp);
                    w = x.pp.rChild;
                }
                if(w.lChild.colour == bColour && w.rChild.colour == bColour){
                    w.colour = rColour;
                    x = x.pp;
                    continue;
                }
                else if(w.rChild.colour == bColour){
                    w.lChild.colour = bColour;
                    w.colour = rColour;
                    rotateRC(w);
                    w = x.pp.rChild;
                }
                if(w.rChild.colour == rColour){
                    w.colour = x.pp.colour;
                    x.pp.colour = bColour;
                    w.rChild.colour = bColour;
                    rotateLC(x.pp);
                    x = root;
                }
            }else{
                Node w = x.pp.lChild;
                if(w.colour == rColour){
                    w.colour = bColour;
                    x.pp.colour = rColour;
                    rotateRC(x.pp);
                    w = x.pp.lChild;
                }
                if(w.rChild.colour == bColour && w.lChild.colour == bColour){
                    w.colour = rColour;
                    x = x.pp;
                    continue;
                }
                else if(w.lChild.colour == bColour){
                    w.rChild.colour = bColour;
                    w.colour = rColour;
                    rotateLC(w);
                    w = x.pp.lChild;
                }
                if(w.lChild.colour == rColour){
                    w.colour = x.pp.colour;
                    x.pp.colour = bColour;
                    w.lChild.colour = bColour;
                    rotateRC(x.pp);
                    x = root;
                }
            }
        }
        x.colour = bColour;
    }

    Node treeMin(Node sTree){
        while(sTree.lChild!=nl){
            sTree = sTree.lChild;
        }
        return sTree;
    }

    String printSP(int building_name){
        Node cur=root;
        String m="";
        while(cur!=nl){
            if(cur.pointer.buildingNum == building_name){
                m = "("+cur.pointer.buildingNum+","+ cur.pointer.exTime+","+ cur.pointer.tTime+")";
                return m;
                
            }
            else if(building_name>cur.pointer.buildingNum)
                cur=cur.rChild;
            else
                cur=cur.lChild;
        }
        return m;
    }
    //decides for a building if insert/print is to be performed and for which tree it is to be performed
    public void OUT(int choice,Building b) {
        Scanner scan = new Scanner(System.in);
    
        Node node;
        switch (choice) {
            case 1:
                node = new Node(b);
                insert(node);
            
                break;
            case 2:
                node = new Node(b);
                
                if (delete(node)) {
                    
                } else {
                    System.out.print(": does not exist!");
                }
                
                break;
            
            case 4:
                PRTree(root);
                break;
            
        }
    }
}

