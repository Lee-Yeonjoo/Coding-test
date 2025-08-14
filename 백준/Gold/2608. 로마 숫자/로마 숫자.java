import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//구현
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String romanNum1 = br.readLine();
        String romanNum2 = br.readLine();

        int arabicNum = toArabic(romanNum1) + toArabic(romanNum2);
        System.out.println(arabicNum);
        System.out.println(toRoman(arabicNum));
    }

    //로마 -> 아라비아 변환 함수
    static int toArabic(String roman) {
        int arabic = 0;
        for (int i = 0; i < roman.length(); i++) {
            char x = roman.charAt(i);

            //마지막 글자가 아니고, I, X, C라면 뒷 문자 체크
            char xNext = ' ';
            if (i < roman.length() - 1) {
                xNext = roman.charAt(i + 1);
            }
            if (x == 'I') {
                if (xNext == 'V') {
                    arabic += 4;
                    i++;
                } else if (xNext == 'X') {
                    arabic += 9;
                    i++;
                } else {
                    arabic += 1;
                }
            } else if (x == 'X') {
                if (xNext == 'L') {
                    arabic += 40;
                    i++;
                } else if (xNext == 'C') {
                    arabic += 90;
                    i++;
                } else {
                    arabic += 10;
                }
            } else if (x == 'C') {
                if (xNext == 'D') {
                    arabic += 400;
                    i++;
                } else if (xNext == 'M') {
                    arabic += 900;
                    i++;
                } else {
                    arabic += 100;
                }
            } else if (x == 'V') {
                arabic += 5;
            } else if (x == 'L') {
                arabic += 50;
            } else if (x == 'D') {
                arabic += 500;
            } else if (x == 'M') {
                arabic += 1000;
            }
        }

        return arabic;
    }

    //아라비아 -> 로마 변환 함수
    static String toRoman(int arabic) {
        String roman = "";

        //1000의 자리 수
        int q = arabic / 1000;
        arabic %= 1000;
        for (int i = 0; i < q; i++) {
            roman += "M";
        }

        //100의 자리 수
        q = arabic / 100;
        arabic %= 100;
        if (q < 5) {
            if (q == 4) {
                roman += "CD";
            } else {
                for (int i = 0; i < q; i++) {
                    roman += "C";
                }
            }
        } else {
            if (q == 9) {
                roman += "CM";
            } else {
                roman += "D";
                q -= 5;
                for (int i = 0; i < q; i++) {
                    roman += "C";
                }
            }
        }

        //10의 자리 수
        q = arabic / 10;
        arabic %= 10;
        if (q < 5) {
            if (q == 4) {
                roman += "XL";
            } else {
                for (int i = 0; i < q; i++) {
                    roman += "X";
                }
            }
        } else {
            if (q == 9) {
                roman += "XC";
            } else {
                roman += "L";
                q -= 5;
                for (int i = 0; i < q; i++) {
                    roman += "X";
                }
            }
        }

        //1의 자리 수
        if (arabic < 5) {
            if (arabic == 4) {
                roman += "IV";
            } else {
                for (int i = 0; i < arabic; i++) {
                    roman += "I";
                }
            }
        } else {
            if (arabic == 9) {
                roman += "IX";
            } else {
                roman += "V";
                arabic -= 5;
                for (int i = 0; i < arabic; i++) {
                    roman += "I";
                }
            }
        }
        return roman;
    }
}
