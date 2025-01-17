package chap3.LinQueueSeqQueue;

//顺序循环队列类设计 测试类
public class SeqQueueTest {
    public static void main(String[] args) {

        LinQueue myQueue = new LinQueue();

        int[] test = {1, 3, 5, 7, 9};
        int n = 5;

        try {
            for (int i = 0; i < n; i++) {
                System.out.print(test[i]);
                myQueue.append(test[i]);
            }

            System.out.println("当前队头元素为：" + myQueue.getFront());

            System.out.print("出队列元素序列为：");
            while (myQueue.notEmpty())
                System.out.print(myQueue.delete() + "  ");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
