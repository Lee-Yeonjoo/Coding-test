import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long W = Integer.parseInt(st.nextToken());
        long H = Integer.parseInt(st.nextToken());
        long f = Integer.parseInt(st.nextToken());
        long c = Integer.parseInt(st.nextToken());
        long x1 = Integer.parseInt(st.nextToken());
        long y1 = Integer.parseInt(st.nextToken());
        long x2 = Integer.parseInt(st.nextToken());
        long y2 = Integer.parseInt(st.nextToken());

        long area = (x2 - x1) * (y2 - y1);  //색칠하는 사각형 면적
        //f를 기준으로 접은거에 대해 잘린 채 색칠된 면적
        long cutArea = 0;
        //f를 기준으로 잘랐을 때 너비 길이
        long cutWeight = Math.max(f, W - f) - Math.abs(2 * f - W);
        if (cutWeight - x1 >= 0) {  //음수면 안됨!! 체크 필수!!
            cutArea = (Math.min(cutWeight, x2) - x1) * (y2 - y1);
        }

        System.out.println((W * H) - ((area + cutArea) * (c + 1)));  //(c+1)등분 했으므로 그만큼 곱해주면 색칠된 면적의 크기이므로, 전체 면적에서 빼주어 색칠되지 않은 면적을 구한다.
    }
}
