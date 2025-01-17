package JavaBook.chap8;
//import java.io.*;

public class StudentClass {
    private String name;             //班级名称
    static int capacity = 40;        //最大容量
    private Student students[];      //学生
    private int size;                //实际人数

    public StudentClass(String name, int size) {
        this.name = name;
        this.size = size;
        students = new Student[capacity];
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Student[] getStudents() {
        return students;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setStudents(Student[] students) {
        for (int i = 0; i < size; i++)
            this.students[i] = new Student(students[i]);
    }

    public String toString() {
        String s;
        s = " 班级:" + name + "\t" + "容量:" + capacity + "\t" + "实际人数:" + size + "\n\n";
        s = s + "学号" + "\t" + "姓名" + "\t" + "英语" + "\t" + "数学" + "\t" + "计算机" + "\t" + "总成绩\n";
        for (int i = 0; i < size; i++)
            s = s + students[i].getId() + "\t" + students[i].getName() + "\t" + students[i].getEng() + "\t" + students[i].getMath() + "\t" + students[i].getComp() + "\t" + students[i].getSum() + "\n";
        return s;
    }

    //顺序查找方法:已知学生的学号，查找此学生是否存在
    public int find(String id) {
        for (int i = 0; i < size; i++)
            if (students[i].getId().equals(id)) return i;
        return -1;
    }

    //数组元素的增加:在数组的末尾增加一个学生对象
    public boolean add(Student aStudent) {
        if (size == capacity) return false;
        if (find(aStudent.getId()) >= 0) return false;
        this.students[size] = new Student(aStudent.getId(), aStudent.getName(), aStudent.getEng(), aStudent.getMath(), aStudent.getComp());
        size++;
        return true;
    }

    //数组元素的删除:已知一个Student对象，将此对象从数组中删除
    public boolean del(Student aStudent) {
        int pos = find(aStudent.getId());
        if (pos == -1) return false;
        for (int i = pos + 1; i < size; i++)
            students[i - 1] = students[i];
        size--;
        return true;
    }

    //数组元素的删除:已知学号,删除一个学生
    public boolean del(String id) {
        int pos = find(id);
        if (pos == -1) return false;
        for (int i = pos + 1; i < size; i++)
            students[i - 1] = students[i];
        size--;
        return true;
    }

    //选择排序
    public void selectionSort() {
        Student temp;
        for (int i = 0; i < size - 1; i++)
            for (int j = i + 1; j < size; j++)
                if (students[j].compare(students[i]) > 0) {
                    temp = students[i];
                    students[i] = students[j];
                    students[j] = temp;
                }
    }

    //直接插入排序方法
    public void insertSort() {
        Student temp;
        for (int i = 1; i < size; i++) {
            temp = students[i];
            int j = i - 1;
            while (j > -1 && temp.compare(students[j]) > 0) {
                students[j + 1] = students[j];
                j--;
            }
            students[j + 1] = temp;
        }
    }

}




