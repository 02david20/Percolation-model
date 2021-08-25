//Store like a tree
//Id[i] is the parent of i
//Root is id[id[id[....[id[i]]]]]
//merge by set the root of one to be the child of the other

/*****Note that you can improve your code 
 * by flatten the tree(reduce height) each time you find root****/
public class QuickUnion {
     private int[] id;

     public QuickUnion(int N) {
          for(int i = 0 ; i < N; i++){
               id[i] = i;
          }
     }

     private int root(int i) {
          while(i != id[i]) { 
               /*id[i] = id[id[i]] set the grandparent to be parent 
               until reach the root
               it will reduce the size of the tree
               */
               i = id[i];
          }
          return i;
     }
     
     public boolean connected (int p , int q) {
          if(root(p) == root(q)) return true;
          return false;
     }

     public void union (int p , int q) {
          int pid = root(p);
          int qid = root(q);
          id[pid] = qid;
     }
    
}
