import java.util.*;
import java.io.*;

public class Matrix {
    private byte[][] data = null;
    private int rows = 0, cols = 0;
    
    public Matrix(int r, int c) {
        data = new byte[r][c];
        rows = r;
        cols = c;
    }
    
    public Matrix(byte[][] tab) {
        rows = tab.length;
        cols = tab[0].length;
        data = new byte[rows][cols];
        for (int i = 0 ; i < rows ; i ++)
            for (int j = 0 ; j < cols ; j ++) 
                data[i][j] = tab[i][j];
    }
    
    public int getRows() {
        return rows;
    }
    
    public int getCols() {
        return cols;
    }
    
    public byte getElem(int i, int j) {
        return data[i][j];
    }
    
    public void setElem(int i, int j, byte b) {
        data[i][j] = b;
    }
    
    public boolean isEqualTo(Matrix m){
        if ((rows != m.rows) || (cols != m.cols))
            return false;
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                if (data[i][j] != m.data[i][j])
                    return false;
                return true;
    }
    
    public void shiftRow(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < cols; i++){
            tmp = data[a][i];
            data[a][i] = data[b][i];
            data[b][i] = tmp;
        }
    }
    
    public void shiftCol(int a, int b){
        byte tmp = 0;
        for (int i = 0; i < rows; i++){
            tmp = data[i][a];
            data[i][a] = data[i][b];
            data[i][b] = tmp;
        }
    }
    
    public void addRow(int a, int b){
        for (int i = 0; i < cols; i++){
            data[b][i] = (byte) ((data[a][i] + data[b][i]) % 2);
        }
    }

    public void addCol(int a, int b){
        for (int i = 0; i < rows; i++){
            data[i][b] = (byte) ((data[i][a] + data[i][b]) % 2);
        }
    }  

    public Matrix copy(){
        Matrix result = new Matrix(rows, cols);
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                result.data[i][j] = data[i][j];
        return result;
    }

    public Matrix sysTransform(){
        Matrix result = this.copy();
        int i_col = cols - rows;
        int pivot = 0;
        for (int i = i_col; i < i_col + rows; i++ ){
            for (int j = pivot; j < rows; j++){
                if (result.data[j][i] == 1){
                    if(pivot == i - i_col){
                        result.shiftRow(j, i - i_col);
                        pivot ++;
                    } else {
                        result.addRow(pivot - 1, j);
                    }
                }
            }
        }
        for (int i = i_col + rows - 1; i > i_col; i--){
            for (int j = i - i_col - 1; j >= 0; j--){
                if (result.data[j][i] == 1){
                    result.addRow(i - i_col, j);
                }
            }
        }

        return result;
    }

    public Matrix getChildMatrix(int r_start, int r_end, int c_start, int c_end){
        Matrix result = new Matrix(r_end - r_start, c_end - c_start);
        int r_cols = result.getCols();
        int r_rows = result.getRows();
        for (int i = 0; i < r_rows; i++){
            for (int j = 0; j < r_cols; j++){
                result.setElem(i, j, this.getElem(i + r_start, j + c_start));
            }
        }
        return result;
    }

    public Matrix genG(){
        Matrix p = this.sysTransform().getChildMatrix(0, rows, 0, cols - rows);
        Matrix pT = p.transpose();
        int r_cols = pT.getCols() + pT.getRows();
        int r_rows = pT.getRows();
        Matrix result = new Matrix(r_rows, r_cols);
        for (int i = 0; i < r_rows; i++){
            for (int j = 0; j < r_cols; j++){
                if (j < r_rows){
                    if (j == i){
                        result.setElem(i, j, (byte)1);
                    } else {
                        result.setElem(i, j, (byte)0);
                    }
                } else {
                    result.setElem(i, j, pT.getElem(i, j - r_rows));
                }
            }
        }
        return result;
    }

    public void display() {
        System.out.print("[");
        for (int i = 0; i < rows; i++) {
            if (i != 0) {
                System.out.print(" ");
            }
            
            System.out.print("[");
            
            for (int j = 0; j < cols; j++) {
                System.out.printf("%d", data[i][j]);
                
                if (j != cols - 1) {
                    System.out.print(" ");
                }
            }
            
            System.out.print("]");
            
            if (i == rows - 1) {
                System.out.print("]");
            }
            
            System.out.println();
        }
        System.out.println();
    }
    
    public Matrix transpose() {
        Matrix result = new Matrix(cols, rows);
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                result.data[j][i] = data[i][j];
    
        return result;
    }
    
    public Matrix add(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if ((m.rows != rows) || (m.cols != cols))
            System.out.printf("Erreur d'addition\n");
        
        for (int i = 0; i < rows; i++) 
            for (int j = 0; j < cols; j++) 
                r.data[i][j] = (byte) ((data[i][j] + m.data[i][j]) % 2);
        return r;
    }
    
    public Matrix multiply(Matrix m){
        Matrix r = new Matrix(rows,m.cols);
        
        if (m.rows != cols)
            System.out.printf("Erreur de multiplication\n");
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < m.cols; j++) {
                r.data[i][j] = 0;
                for (int k = 0; k < cols; k++){
                    r.data[i][j] =  (byte) ((r.data[i][j] + data[i][k] * m.data[k][j]) % 2);
                }
            }
        }
        
        return r;
    }
}

