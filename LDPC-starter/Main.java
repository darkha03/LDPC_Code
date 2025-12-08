import java.util.*;
import java.io.*;

public class Main {
    
    public static Matrix loadMatrix(String file, int r, int c) {
        byte[] tmp =  new byte[r * c];
        byte[][] data = new byte[r][c];
        try {
            FileInputStream fos = new FileInputStream(file);
            fos.read(tmp);
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0; i < r; i++)
            for (int j = 0; j< c; j++)
                data[i][j] = tmp[i * c + j];
            return new Matrix(data);
    }
    
    public static void main(String[] arg){
        
        byte[][] tab = {{1,0,0},{0,1,0},{0,0,1}};
        Matrix m = new Matrix(tab);
        m.display();
        
        Matrix hbase = loadMatrix("data/matrix-15-20-3-4", 15, 20);
        Matrix g = hbase.genG();
        System.out.println("Matrice G generee :");
        g.display();

        byte[][] u = {{1,0,1,0,1}};
        Matrix msg = new Matrix(u);
        System.out.println("Message :");
        msg.display();
        System.out.println("Mot de code :");
        Matrix code = msg.multiply(hbase.genG());
        code.display();
        
        System.out.println("Tanner Graph :");
        TGraph tg = new TGraph(hbase, 3, 4);

        byte[][] erreur1 = {{0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Matrix erreurMatrix1 = new Matrix(erreur1);
        System.out.println("Vecteur d'erreur e1 :");
        erreurMatrix1.display();
        Matrix received = code.add(erreurMatrix1);
        System.out.println("Mot de code bruité y1 = x + e1 :");
        received.display();
        Matrix sydrome1 = hbase.multiply(received.transpose());
        System.out.println("Syndrome de y1 :");
        sydrome1.transpose().display();
        Matrix decoded = tg.decode(received, 100);
        System.out.println("Message correct:");
        decoded.display();

        byte[][] erreur2 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};
        Matrix erreurMatrix2 = new Matrix(erreur2);
        System.out.println("Vecteur d'erreur e2 :");
        erreurMatrix2.display();
        Matrix received2 = code.add(erreurMatrix2);
        System.out.println("Mot de code bruité y2 = x + e2 :");
        received2.display();
        Matrix sydrome2 = hbase.multiply(received2.transpose());
        System.out.println("Syndrome de y2 :");
        sydrome2.transpose().display();
        Matrix decoded2 = tg.decode(received2, 100);
        System.out.println("Message correct:");
        decoded2.display();

        byte[][] erreur3 = {{0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0}};
        Matrix erreurMatrix3 = new Matrix(erreur3);
        System.out.println("Vecteur d'erreur e3 :");
        erreurMatrix3.display();
        Matrix received3 = code.add(erreurMatrix3);
        System.out.println("Mot de code bruité y3 = x + e3 :");
        received3.display();
        Matrix sydrome3 = hbase.multiply(received3.transpose());
        System.out.println("Syndrome de y3 :");
        sydrome3.transpose().display();
        Matrix decoded3 = tg.decode(received3, 100);
        System.out.println("Message correct:");
        decoded3.display();

        byte[][] erreur4 = {{0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
        Matrix erreurMatrix4 = new Matrix(erreur4);
        System.out.println("Vecteur d'erreur e4 :");
        erreurMatrix4.display();
        Matrix received4 = code.add(erreurMatrix4);
        System.out.println("Mot de code bruité y4 = x + e4 :");
        received4.display();
        Matrix sydrome4 = hbase.multiply(received4.transpose());
        System.out.println("Syndrome de y4 :");
        sydrome4.transpose().display();
        Matrix decoded4 = tg.decode(received4, 100);
        System.out.println("Message correct:");
        decoded4.display();
        
    }
}
