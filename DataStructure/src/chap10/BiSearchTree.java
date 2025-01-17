package chap10;
//作业10-9 设计递归结构的二叉排序树查找成员函数 searchBST
//作业10-10 设计循环结构的二叉排序树插入成员函数 insertBST

//二叉排序树类
public class BiSearchTree {
    //根节点
    private BiTreeNode root;
//    Vector vector = new Vector();

    //中序
    private void inOrder(BiTreeNode t, Visit vs) {
        if (t != null) {
            inOrder(t.getLeftChild(), vs);
            vs.print(new Integer(t.getData()));
            inOrder(t.getRightChild(), vs);
        }
    }

    //前序
    private void preOrder(BiTreeNode t, Visit vs) {
        if (t != null) {
            vs.print(new Integer(t.getData()));
            preOrder(t.getLeftChild(), vs);
            preOrder(t.getRightChild(), vs);
        }
    }

    //空树
    public BiSearchTree() {
        root = null;
    }

    //传根节点创造树
    public void setRoot(BiTreeNode t) {
        root = t;
    }

    public BiTreeNode getRoot() {
        return root;
    }

    //中序遍历
    public void inOrder(Visit vs) {
        inOrder(root, vs);
    }

    //前序遍历
    public void preOrder(Visit vs) {
        preOrder(root, vs);
    }

    public BiTreeNode getLeftChild(BiTreeNode current) {
        return current != null ? current.getLeftChild() : null;
    }

    public BiTreeNode getRightChild(BiTreeNode current) {
        return current != null ? current.getRightChild() : null;
    }


    //查找(中序、循环结构)
    public BiTreeNode find(int item) {
        if (root != null) {
            BiTreeNode temp = root;
            while (temp != null) {
                if (temp.getData() == item) return temp;//查找成功

                if (temp.getData() < item)
                    temp = temp.getRightChild();            //在右子树继续
                else
                    temp = temp.getLeftChild();            //在左子树继续
            }
        }
        return null;                                    //查找失败
    }

    //作业10-9 设计递归结构的二叉排序树查找成员函数
    //TODO:查找改写为递归结构

    /**
     * 二叉排序树的查找 （递归实现）
     * <p>
     * 查找可以有两种实现方法，一种是递归，一种是while 循环。
     * 在插入和删除操作中也首先需要查询操作，这时使用while 循环比较好，可以知道要查询结点的双亲结点
     * 但是递归更为简便
     *
     * @param root
     * @param item
     * @return 返回
     */
    public boolean searchBST(BiTreeNode root, int item) {
        if (root == null) {
            return false;
        } else if (item == root.getData()) {
            return true;
        } else if (item < root.getData()) {
            return searchBST(root.getLeftChild(), item);
        } else {
            return searchBST(root.getRightChild(), item);
        }
    }

    //添加（递归）
    public void insert(BiTreeNode ptr, int item) {
        if (item < ptr.getData()) {
            if (ptr.getLeftChild() == null) {
                BiTreeNode temp = new BiTreeNode(item);    //生成新结点
                temp.setParent(ptr);    //把ptr结点设为temp结点的父结点
                ptr.setLeftChild(temp);    //把temp结点设为ptr结点的左孩子结点
            } else insert(ptr.getLeftChild(), item);        //在左子树递归
        } else if (item > ptr.getData()) {
            if (ptr.getRightChild() == null) {
                BiTreeNode temp = new BiTreeNode(item); //生成新结点
                temp.setParent(ptr); //把ptr结点设为temp结点的父结点
                ptr.setRightChild(temp); //把temp结点设为ptr结点的右孩子结点
            } else insert(ptr.getRightChild(), item);        //在右子树递归
        }
    }

    //TODO:改写为循环结构
    //作业10-10 设计循环结构的二叉排序树插入成员函数

    /**
     * 二叉排序树的插入
     *
     * @param key
     */
    public void insertBST(int key) {
        BiTreeNode p = root;
        /**记录查找结点的前一个结点*/
        BiTreeNode prev = null;
        /**一直查找下去，直到到达满足条件的结点位置*/
        while (p != null) {
            prev = p;
            if (key < p.getData())
                p = p.getLeftChild();
            else if (key > p.getData())
                p = p.getRightChild();
            else
                return;
        }
        /**prve是要安放结点的父节点，依据结点值得大小，放在对应的位置*/
        if (root == null)
            root = new BiTreeNode(key);
        else if (key < prev.getData())
            prev.setLeftChild(new BiTreeNode(key));
        else prev.setRightChild(new BiTreeNode(key));
    }

    //删除
    public void delete(BiTreeNode ptr, int item) {
        if (ptr != null) {
            if (item < ptr.getData())
                //在左子树递归
                delete(ptr.getLeftChild(), item);
            else if (item > ptr.getData())
                //在右子树递归
                delete(ptr.getRightChild(), item);
            else if (ptr.getLeftChild() != null && ptr.getRightChild() != null) {
                //要删除结点寻找到，并且要删除结点左右子树均存在的情况
                BiTreeNode min;
                min = ptr.getRightChild();            //取当前结点的右孩子结点
                while (min.getLeftChild() != null)
                    min = min.getLeftChild();        //min取到最左孩子结点
                ptr.setData(min.getData());//把min的数据值赋给ptr结点
                delete(ptr.getRightChild(), min.getData());
                //在ptr结点的右子树中递归删除min结点
            } else {
                if (ptr.getLeftChild() == null && ptr.getRightChild() != null) {
                    //要删除结点寻找到，并且要删除结点只有右子树的情况
                    ptr.getParent().setRightChild(ptr.getRightChild());
                    //让ptr双亲的右孩子指针指向ptr的右孩子结点
                    ptr.getRightChild().setParent(ptr.getParent());
                    //让ptr右孩子的双亲指向ptr的双亲结点
                } else if (ptr.getRightChild() == null && ptr.getLeftChild() != null) {
                    //要删除结点寻找到，并且要删除结点只有左子树的情况
                    ptr.getParent().setLeftChild(ptr.getLeftChild());
                    //让ptr双亲的左孩子结点指向ptr结点的左孩子结点
                    ptr.getLeftChild().setParent(ptr.getParent());
                    //让ptr左孩子的双亲指向ptr的双亲结点
                } else {
                    //要删除结点寻找到，并且要删除结点为叶结点的情况
                    BiTreeNode p = ptr.getParent();
                    if (p.getLeftChild() == ptr)    //若要删除结点在双亲的左孩子上
                        p.setLeftChild(null);    //把双亲的左孩子置空
                    else                //若要删除结点在双亲的右孩子上
                        p.setRightChild(null);    //把双亲的右孩子置空
                }
            }
        }
    }
}
