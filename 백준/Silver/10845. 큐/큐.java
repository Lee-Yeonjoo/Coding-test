

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int N;
        String input;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        Que q = new Que();
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            input = st.nextToken();
            switch (input) {
                case "push":
                    q.push(Integer.parseInt(st.nextToken()));
                    break;

                case "pop":
                    sb.append(q.pop()).append("\n");
                    break;

                case "size":
                    sb.append(q.size()).append("\n");
                    break;

                case "empty":
                    sb.append(q.empty()).append("\n");
                    break;

                case "front":
                    sb.append(q.front()).append("\n");
                    break;

                case "back":
                    sb.append(q.back()).append("\n");
                    break;
            }
        }

        System.out.println(sb);
    }
}

class Que {
    private List<Integer> queue;

    public Que() {
        this.queue = new ArrayList<>();
    }

    public void push(int x) {
        queue.add(x);
    }

    public int pop() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.remove(0);
    }

    public int size() {
        return queue.size();
    }

    public int empty() {
        return queue.isEmpty()? 1 : 0;
    }

    public int front() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.get(0);
    }

    public int back() {
        if (queue.isEmpty()) {
            return -1;
        }
        return queue.get(queue.size()-1);
    }
}
