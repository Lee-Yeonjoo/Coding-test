import java.util.*;
public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Deque dq = new Deque();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[] x = new int[M+1];
        int start=1, end=N;
        int cnt=0;

        for(int i=0; i<N; i++){
            dq.push_back(i+1);
        }

        for(int i=0; i<M; i++){
            x[i] = sc.nextInt();
        }

        int i=0;
        while(x[i]!=0){ //0 입력될 일이 없으니까
            if(dq.front()!=x[i]){
                //System.out.println("x의 인덱스"+(dq.indexOf(x[i])+1));
                if((start+end)/2 >= dq.indexOf(x[i])+1){
                    while(dq.front()!=x[i]){
                        dq.push_back(dq.pop_front());
                        cnt++;
                    }
                }
                else{
                    while(dq.front()!=x[i]){
                        dq.push_front(dq.pop_back());
                        cnt++;
                    }
                }
            }
            else{
                dq.pop_front();
                end--;
                i++;
            }
        }
        System.out.println(cnt);
    }
}

class Deque {
    LinkedList<Integer> l;

    public Deque(){
        l = new LinkedList<Integer>();
    }

    public void push_front(int X){
        l.addFirst(X);
    }

    public void push_back(int X){
        l.addLast(X);
    }

    public int pop_front(){
        if(l.isEmpty())
            return -1;
        else
            return l.removeFirst();
    }

    public int pop_back(){
        if(l.isEmpty())
            return -1;
        else
            return l.removeLast();
    }

    public int size(){
        return l.size();
    }

    public boolean empty(){
        return l.isEmpty();
    }

    public int front(){
        if(l.isEmpty())
            return -1;
        else
            return l.getFirst();
    }

    public int back(){
        if(l.isEmpty())
            return -1;
        else
            return l.getLast();
    }

    public int indexOf(int x){
        return l.indexOf(x);
    }
}