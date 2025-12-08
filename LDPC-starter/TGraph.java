import java.util.Map;

public class TGraph {
    private int w_r;
    private int w_c;
    private int n_r;
    private int n_c;
    private int[][] left;
    private int[][] right;

    public TGraph(Matrix H, int wc, int wr){
        w_r = wr;
        w_c = wc;
        n_c = H.getCols();
        n_r = H.getRows();
        left = new int[n_r][w_r + 1];
        right = new int[n_c][w_c + 1];
        for (int i = 0; i < n_r; i++) {
            int idx = 0;
            for (int j = 0; j < n_c; j++) {
                if (H.getElem(i, j) == 1) {
                    idx++;
                    left[i][idx] = j;
                }
            }
        }
        for (int j = 0; j < n_c; j++) {
            int idx = 0;
            for (int i = 0; i < n_r; i++) {
                if (H.getElem(i, j) == 1) {
                    idx++;
                    right[j][idx] = i;
                }
            }
        }
    }

    public void display(){
        System.out.println("Left adjacency list:");
        for (int i = 0; i < n_r; i++) {
            for (int j = 0; j < w_r + 1; j++) {
                System.out.print(left[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("Right adjacency list:");
        for (int j = 0; j < n_c; j++) {
            for (int i = 0; i < w_c + 1; i++) {
                System.out.print(right[j][i] + " ");
            }
            System.out.println();
        }
    }

    public Matrix decode(Matrix code, int rounds ){
        for (int j = 0; j < n_c; j++){
            right[j][0] = code.getElem(0, j);
        }
        for (int i = 0; i < rounds; i++){
            // Calculate parity bits for left nodes
            for (int r = 0; r < n_r; r++){
                left[r][0] = 0;
                for (int k = 1; k <= w_r; k++){
                    int c_idx = left[r][k];
                    left[r][0] ^= right[c_idx][0];
                }
            }
            // Verify parity-check equations
            boolean allSatisfied = true;
            for (int r = 0; r < n_r; r++){
                if (left[r][0] != 0){
                    allSatisfied = false;
                    break;
                }
            }
            if (allSatisfied){
                Matrix decoded = new Matrix(1, n_c);
                for (int j = 0; j < n_c; j++){
                    decoded.setElem(0, j, ((byte)right[j][0]));
                }
                return decoded;
            }

            // Calculate max
            int max = 0;
            Map<Integer, Integer> countMap = new java.util.HashMap<>();
            for (int j = 0; j < n_c; j++){
                countMap.put(j, 0);
                for (int k = 0; k < w_c; k++){
                    int r_idx = right[j][k + 1];
                    countMap.put(j, countMap.get(j) + left[r_idx][0]);
                }
                if (countMap.get(j) > max){
                    max = countMap.get(j);
                }
            }

            // Bit flipping
            for (int j = 0; j < n_c; j++){
                if (countMap.get(j) == max){
                    right[j][0] = 1 - right[j][0];
                }
            }
        }
        Matrix failed = new Matrix(1, n_c);
        for (int j = 0; j < n_c; j++){
            failed.setElem(0, j, (byte)-1);
        }
        return failed;
    }
}
