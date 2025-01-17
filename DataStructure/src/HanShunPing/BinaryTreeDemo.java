package HanShunPing;

import javax.xml.bind.Binder;

public class BinaryTreeDemo {

    public static void main(String[] args) {
        //现需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        //说明：先手动创建该二叉树，后面再学习使用递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);
        //测试
        System.out.println("前序遍历");//1,2,3,4
        //1,2,3,5,4
        binaryTree.preOrder();
        //测试
        System.out.println("中序遍历");//2,1,3,4
        //21534
        binaryTree.infixOrder();
        //测试
        System.out.println("后序遍历");//2,4,3,1
        //25431
        binaryTree.postOrder();

//        //前序查找
//        //前序查找：4次
//        System.out.println("前序遍历方式~~~");
//        int number = 5;
//        HeroNode resNode = binaryTree.preOrderSearch(number);
//        if (resNode != null) {
//            System.out.printf("找到了，信息位no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no=%d的英雄", number);
//
//        }

//        //中序查找
//        //中序查找：3次
////        System.out.println("进入中序遍历查找");
////        不要放在第一行，要放在判断当前节点是不是的前面一行
//        System.out.println("中序遍历方式~~~");
//        int number = 5;
//        HeroNode resNode = binaryTree.infixOrderSearch(number);
//        if (resNode != null) {
//            System.out.printf("找到了，信息位no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no=%d的英雄", number);
//
//        }

//        //后序查找
//        //后序查找：2次
////        System.out.println("进入中序遍历查找");
////        不要放在第一行，要放在判断当前节点是不是的前面一行
//        System.out.println("后序遍历方式~~~");
//        int number = 5;
//        HeroNode resNode = binaryTree.postOrderSearch(number);
//        if (resNode != null) {
//            System.out.printf("找到了，信息位no=%d name=%s", resNode.getNo(), resNode.getName());
//        } else {
//            System.out.printf("没有找到no=%d的英雄", number);
//
//        }

//        //删除前
//        System.out.println("删除前，前序遍历：");
//        binaryTree.preOrder();
//        binaryTree.delNode(3);
//        System.out.println("删除后，前序遍历：");
//        binaryTree.preOrder();

    }
}

//定义BinaryTree二叉树
class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //删除结点
    public void delNode(int no) {
        if (root != null) {
            //如果只有一个root结点，这里立即判断root是不是要删除的结点
            if (root.getNo() == no) {
                root = null;
            } else {
                //递归删除
                root.delNode(no);
            }
        } else {
            System.out.println("空树，不能删除~");
        }
    }

    //前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序查找
    public HeroNode preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    //中序查找
    public HeroNode infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    //后序查找
    public HeroNode postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }
}

//先创建HeroNode结点
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;

    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" + "no=" + no + ", name='" + name + '\'' + '}';
    }

    //递归删除结点
    //1. 如果删除的结点是叶子节点，则删除该结点
    //2. 如果删除的结点是非叶子节点，则删除该子树
    public void delNode(int no) {
        //思路

        /*
         1. 因为我们的二叉树是单向的，所以我们是判断"当前结点的子结点"是否需要删除结点，而不能去判断"当前这个结点"是不是需要删除结点
         2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)
         3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
         4. 如果第2和第3步没有删除结点,那么我们就需要向左子树进行递归删除
         5. 如果第4步也没有删除结点,则应当向右子树进行递归删除。
         */


        // 2. 如果当前结点的左子结点不为空，并且左子结点就是要删除结点，就将this.left = null;并且就返回(结束递归删除)

        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 3. 如果当前结点的右子结点不为空，并且右子结点就是要删除结点，就将this.right= null ;并且就返回(结束递归删除)
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 4. 我们就需要向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 5. 我们就需要向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }


    }


    //编写前序遍历的方法
    public void preOrder() {
        //先输出父节点
        System.out.println(this);
        //递归向左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        //判断，递归向右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {

        //递归向左子树中序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //判断，递归向右子树中序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    /**
     * @param no 查找no
     * @return 如果找到就返回该Node，否则返回null
     */
    //前序遍历查找
    public HeroNode preOrderSearch(int no) {
        System.out.println("进入前序遍历查找");
        //比较当前结点是不 是
        if (this.no == no) {
            return this;
        }
        //则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到结点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(no);
        }
        if (resNode != null) {
            //说明我们左子树找到了
            return resNode;
        }
        //如果左递归前序查找，找到结点，则返回，否则继续判断
        //当前结点的右子结点是否为空，如果不空，则继续向右递归前序查找
        if (this.right != null) {
            resNode = this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no) {
        //则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到结点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.infixOrderSearch(no);
        }
        if (resNode != null) {
            //说明我们左子树找到了
            return resNode;
        }
        //比较当前结点是不是，如果找到，则返回，如果没有找到，就和当前结点比较，如果是则返回当前结点
        System.out.println("进入中序遍历查找");
        //不要放在第一行，要放在判断当前节点是不是的前面一行
        if (this.no == no) {
            return this;
        }
        //否则继续进行右递归的中序查找
        if (this.right != null) {
            resNode = this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no) {
        //则判断当前结点的左子结点是否为空，如果不为空，则递归前序查找
        //如果左递归前序查找，找到结点，则返回
        HeroNode resNode = null;
        if (this.left != null) {
            resNode = this.left.postOrderSearch(no);
        }
        if (resNode != null) {
            //说明我们左子树找到了
            return resNode;
        }
        //如果左子树没有找到，则向右子树递归进行后序遍历查找
        if (this.right != null) {
            resNode = this.right.postOrderSearch(no);
//            return resNode;
        }

        if (resNode != null) {
            return resNode;
        }
        System.out.println("进入后序遍历查找");
        //不要放在第一行，要放在判断当前节点是不是的前面一行
        //如果左右子树都没有找到，就比较当前结点是不是
        if (this.no == no) {
            return this;
        }
        return resNode;
    }


}
