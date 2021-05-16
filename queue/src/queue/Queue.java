package queue;

public class Queue {

    int data[] =  new int[10];
    int head;
    int tail;
    // 入队
    void push(int n){
        data[tail] =n;
        tail++;
    };
    // 出队
    void pop(){
        if(empty()) return;
        head++;
    };
    // 是否为空
    boolean empty(){
        return head==tail;
    }
    // 是否已满
    boolean full(){
        return tail==data.length;
    }
    // 队首元素
    int front(){
        if(empty()) return 0;
        return data[head];
    }
    // 队列大小
    int size(){
        return tail-head;
    }
    // 输出队列所有元素
    void outPut(){
        for (int i=head;i<tail;i++){
            System.out.println(data[i]);

        }
    }

}
