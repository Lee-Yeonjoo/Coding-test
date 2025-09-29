import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

//BFS
public class Main {
    static int S;
    static class Node {
        int emoticonCount;  //화면의 임티 개수
        int clipboard;  //클립보드의 임티 개수
        int cost;  //걸리는 시간

        Node(int emoticonCount, int clipboard, int cost) {
            this.emoticonCount = emoticonCount;
            this.clipboard = clipboard;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = Integer.parseInt(br.readLine());
        bfs();
    }

    static void bfs() {
        Queue<Node> q = new LinkedList<>();
        boolean[][] visited = new boolean[10000][10000];  //행 = 화면의 이모티콘 수, 열 = 클립보드의 수
        q.add(new Node(1, 0, 0));
        visited[1][0] = true;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            //이모티콘이 S개 만들어진 경우
            if (curr.emoticonCount == S) {
                System.out.println(curr.cost);
                return;
            }

            //복사하는 경우
            if (!visited[curr.emoticonCount][curr.emoticonCount]) {
                q.add(new Node(curr.emoticonCount, curr.emoticonCount, curr.cost + 1));
                visited[curr.emoticonCount][curr.emoticonCount] = true;
            }

            //붙여넣는 경우 -> 클립보드가 1 이상이어야 함
            if (curr.clipboard >= 1 && !visited[curr.emoticonCount + curr.clipboard][curr.clipboard]) {
                q.add(new Node(curr.emoticonCount + curr.clipboard, curr.clipboard, curr.cost + 1));
                visited[curr.emoticonCount + curr.clipboard][curr.clipboard] = true;
            }

            //삭제하는 경우 -> 화면에 2개 이상
            if (curr.emoticonCount >= 2 && !visited[curr.emoticonCount - 1][curr.clipboard]) {
                q.add(new Node(curr.emoticonCount - 1, curr.clipboard, curr.cost + 1));
                visited[curr.emoticonCount - 1][curr.clipboard] = true;
            }
        }
    }
}
