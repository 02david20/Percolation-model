public class QuicKUnion_improved {
     private int[] id;
     private int[] size;

     public void QuickUnion(int N) {
          for(int i = 0 ; i < N; i++){
               id[i] = i;
               size[i] = 1;
          }
     }

     private int root(int i) {
          while(i != id[i]) { 
               i = id[i];
          }
          return i;
     }
     
     public boolean connected (int p , int q) {
          if(root(p) == root(q)) return true;
          return false;
     }

     public void union (int p , int q) {
          int i = root(p);
          int j = root(q);
          if(i == j) return;
          else if(size[i] < size[j] ) {
               id[i] = j;
               size[j] += size[i];
          } else {
               id[j] = i;
               size[i] += size[j];
          }
          
     }
}
