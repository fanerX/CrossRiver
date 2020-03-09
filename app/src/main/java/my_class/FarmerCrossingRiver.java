package my_class;

import java.util.ArrayList;
import java.util.List;

public class FarmerCrossingRiver {


    public FarmerCrossingRiver(){

    }

    int farmerPosition(int x){
        if((x & 8) == 8){
            return 1;
        }else {
            return 0;
        }
    }

    int wolfPosition(int x){
        if((x & 4)==4){
            return 1;
        }else {
            return 0;
        }
    }

    int sheepPosition(int x){
        if((x & 2)==2){
            return 1;
        }else {
            return 0;
        }
    }

    int cabbagePosition(int x){
        if((x & 1)==1){
            return 1;
        }else {
            return 0;
        }
    }

    boolean is_safety(int x){
        int f,w,s,c;
        f=farmerPosition(x);
        w=wolfPosition(x);
        s=sheepPosition(x);
        c=cabbagePosition(x);
        if((f != w)&&(w == s)){ //农夫不与狼在一起，狼与羊在一起，不安全
            return false;
        }else if((f != s)&&( s == c)){ //农夫不与羊在一起，羊与白菜在一起，不安全
            return false;
        }
        return true;
    }

    public List<Integer>  BFS_route(){
        int [] route = new int[16];
        int [] book = new int[16];
        int i,position,next;
        for(i=0;i<16;i++){
            route[i]=-1;
            book[i]=0;
        }

        position=0;
        route[0] = -2;
        Queue queue = new Queue();
        queue.push(position);
        while (!queue.empty() && route[15]==-1){
            position=queue.pop();
            book[position]=1;
            for(i=1;i<=8;i<<=1){
                if(((position&8)!=0)==((position&i)!=0)){
                    next=position^(8|i);
                    if(is_safety(next) && book[next]==0){
                        route[next]=position;
                        queue.push(next);
                    }
                }
            }
        }
        List<Integer> list = new ArrayList<>();
        position=15;
        int n=0;



        while (position != -2){
            book[n++]=position;
            position=route[position];
        }
        for(i=n-1;i>=0;i--){
            list.add(book[i]);
        }
        return list;
    }

    public List<Integer> DFS_route(){
        int [] route = new int[16];
        int i,position,next;
        for(i=0;i<16;i++){
            route[i]=-1;
        }
        route[0]=-2;
        position=0;
        Stack stack =new Stack();
        stack.push(position);
        while (!stack.empty()&&route[15]==-1){
            position= stack.getTop();
            for(i=1;i<=8;i<<=1){
                next=position^(8|i);
                if(is_safety(next)&&route[next]==-1){
                    route[next]=position;
                    stack.push(next);
                    break;
                }
            }
            if(i>8){
                stack.pop();
            }
        }
        int [] path = new int[16];
        int n=0;
        position=15;
        while (position!=-2){
            path[n++]=position;
            position=route[position];
        }
        List<Integer> list=new ArrayList<>();
        for (i=n-1;i>=0;i--){
            list.add(path[i]);
        }
        return list;
    }

    class Queue{
        private int front,rear;
        private int [] data;
        public Queue(){
            front=rear=0;
            data=new int[50];
        }
        int pop(){
            if(front==rear){
                return -1;
            }
            return data[front++];
        }
        void push(int d){
            if(rear>=50){
                return;
            }
            data[rear++]=d;
        }
        boolean empty(){
            return rear-front == 0;
        }
    }

    class Stack{
        private int top;
        private int [] data;
        public Stack(){
            top=0;
            data=new int[50];
        }
        public void push(int d){
            if(top>=50){
                return;
            }
            data[top++]=d;
        }
        public int pop(){
            if(top>0){
                return data[top--];
            }
            return -1;
        }
        public int getTop(){
            if(top>0){
                return data[top-1];
            }
            return -1;
        }
        public boolean empty(){
            return top == 0;
        }
    }

}
