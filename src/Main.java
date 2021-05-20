import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = 0;
        int n = Integer.parseInt(br.readLine());
        SLL<Float> results = new SLL<Float>();
        while (i < n) {
            results.insertLast(Float.parseFloat(br.readLine()));
            i++;
        }
        float tmp1, tmp2, tmp3, tmp;
        float rmse = 0.0F;
        SLLNode<Float> node1 = results.getFirst();
        SLLNode<Float> node2 = node1.succ;
        while (node2 != null) {
            float rez = node1.element - node2.element;
            rez *= rez;
            rmse += rez;
            node1 = node2;
            node2 = node2.succ;
        }
        System.out.println("RMSE = " + Math.sqrt(rmse));
        System.out.println("Vnesete 1, za linearen predikator.");
        System.out.println("Vnesete 2, za moving average(2) predikator.");
        System.out.println("Vnesete 3, za moving average(3) predikator.");
        int algo = Integer.parseInt(br.readLine());
        int brTransmisii = 0;
        if (algo == 1) {
            System.out.println("Vnesete ja vrednosta za greska");
            float greska = Float.parseFloat(br.readLine());
            brTransmisii = 1;
            tmp1 = results.getFirst().element;
            SLLNode<Float> node = results.getFirst();
            while (node != null) {
                float rez = node.element - tmp1;
                if (rez < 0)
                    rez *= -1;
                if (rez >= greska) {
                    tmp1 = node.element;
                    brTransmisii++;
                }
                node = node.succ;
            }
            System.out.println("Procent na realizirani transmisii e " + ((float) brTransmisii / n) * 100 + "%");
        } else {
            if (algo == 2) {
                System.out.println("Vnesete ja vrednosta za greska");
                float greska = Float.parseFloat(br.readLine());
                brTransmisii = 2;
                tmp1 = results.getFirst().element;
                SLLNode<Float> node = results.getFirst();
                node = node.succ;
                tmp2 = node.element;
                node = node.succ;
                while (node != null) {
                    tmp = (tmp1 + tmp2) / 2;
                    float rez = node.element - tmp;
                    if (rez < 0)
                        rez *= -1;
                    if (rez >= greska) {
                        tmp1 = tmp2;
                        tmp2 = node.element;
                        brTransmisii++;
                    }
                    node = node.succ;
                }
                System.out.println("Procent na realizirani transmisii e " + ((float) brTransmisii / n) * 100 + "%");
            } else {
                System.out.println("Vnesete ja vrednosta za greska");
                float greska = Float.parseFloat(br.readLine());
                brTransmisii = 3;
                tmp1 = results.getFirst().element;
                SLLNode<Float> node = results.getFirst();
                node = node.succ;
                tmp2 = node.element;
                node = node.succ;
                tmp3 = node.element;
                node = node.succ;
                while (node != null) {
                    tmp = (tmp1 + tmp2 + tmp3) / 3;
                    float rez = node.element - tmp;
                    if (rez < 0)
                        rez *= -1;
                    if (rez >= greska) {
                        tmp1 = tmp2;
                        tmp2 = tmp3;
                        tmp3 = node.element;
                        brTransmisii++;
                    }
                    node = node.succ;
                }
                System.out.println("Procent na realizirani transmisii e " + ((float) brTransmisii / n) * 100 + "%");
            }
        }
    }
}
