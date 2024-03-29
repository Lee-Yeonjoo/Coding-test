import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        MyArrayList<Double> list = new MyArrayList<>(1000); //리스트의 사이즈는 문제 조건

        int N = Integer.parseInt(br.readLine());
        double average;
        StringTokenizer st = new StringTokenizer(br.readLine());

        double max =0; //최고 점수
        for (int i = 0; i < N; i++) {
            double score = Integer.parseInt(st.nextToken());
            list.append(score); //공백 기준으로 잘라서 리스트에 추가
            if (score > max) {
                max = score;
            }
        }
        //System.out.println("max= " +max);

        double sum =0;
        for (int i = 0; i < list.len(); i++) {
            list.set(i, list.get(i)/max*100);
            sum += list.get(i);
        }

        average = sum / list.len();
        bw.write(Double.toString(average));

        bw.flush();
        bw.close();
    }
}

class MyArrayList<E> {
    private E[] item;
    private int numItems;
    private static final int DEFAULT_SIZE = 100;

    public MyArrayList() {
        item = (E[]) new Object[DEFAULT_SIZE];
        numItems = 0;
    }

    public MyArrayList(int size) {
        item = (E[]) new Object[size];
        numItems =0;
    }

    public void add(int index, E x) {
        if (item.length >= numItems && index >= 0 && index <= numItems) {
            for (int i = numItems-1; i >= index; i--) {
                item[i + 1] = item[i];
            }
            item[index] = x;
            numItems++;
        }
    }

    public void append(E x) {
        if (numItems > item.length) {
            return;
        } else {
            item[numItems++] = x;
        }
    }

    public E remove(int index) {
        if (isEmpty() || index < 0 || index > numItems - 1) {
            return null;
        } else {
            E tmp = item[index];
            for (int i = index; i < numItems-1; i++) {
                item[i] = item[i + 1];
            }
            numItems--;
            return tmp;
        }
    }

    public boolean removeItem(E x) {
        int k = 0;
        while (k < numItems && ((Comparable) item[k]).compareTo(x) != 0) {
            k++;
        }

        if (k == numItems) {
            return false;
        } else {
            remove(k);
            return true;
        }
    }

    public E get(int index) {
        if (index >= 0 && index < numItems) {
            return item[index];
        }
        else return null;
    }

    public void set(int index, E x) {
        if (index >= 0 && index < numItems) {
            item[index] = x;
        }
        else return;
    }

    private final int NOT_FOUND = -12345;
    public int indexOf(E x) {
        for (int i = 0; i < numItems; i++) {
            if (((Comparable) item[i]).compareTo(x) == 0) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public int len() {
        return numItems;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public void clear() {
        item = (E[])new Object[item.length];
        numItems = 0;
    }
}
