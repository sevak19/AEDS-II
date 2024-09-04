import java.io.*;
import java.net.*;
import java.util.*;

class Main {
	public static void Processamento(String html, String nome){
        int a = 0;
        int e = 0;
        int i = 0;
        int o = 0;
        int u = 0;
        int a2 = 0;  // 'á'
        int e2 = 0;  // 'é'
        int i2 = 0;  // 'í'
        int o2 = 0;  // 'ó'
        int u2 = 0;  // 'ú'
        int a3 = 0;  // 'ã'
        int e3 = 0;  // 'è'
        int i3 = 0;  // 'ì'
        int o3 = 0;  // 'ò'
        int u3 = 0;  // 'ù'
        int a4 = 0;  // 'â'
        int o4 = 0;  // 'ô'
        int a5 = 0;  // 'ê'
        int e5 = 0;  // 'ê'
        int i5 = 0;  // 'î'
        int o5 = 0;  // 'ô'
        int u5 = 0;  // 'û'

        int consoantes = 0;
        int br = 0;
        int table = 0;

        for (int n = 0; n < html.length(); n++) {
            if (html.charAt(n) == 'a') {
                a++;
            }else if (html.charAt(n) == 'e') {
                e++;
            } else if (html.charAt(n) == 'i') {
                i++;
            } else if (html.charAt(n) == 'o') {
                o++;
            } else if (html.charAt(n) == 'u') {
                u++;
            } else if (html.charAt(n) == '\u00e1') {
                a2++;
            } else if (html.charAt(n) == '\u00e9') {
                e2++;
            } else if (html.charAt(n) == '\u00ed') {
                i2++;
            } else if (html.charAt(n) == '\u00f3') {
                o2++;
            } else if (html.charAt(n) == '\u00fa') {
                u2++;
            } else if (html.charAt(n) == '\u00e0') {
                a3++;
            } else if (html.charAt(n) == '\u00e8') {
                e3++;
            } else if (html.charAt(n) == '\u00ec') {
                i3++;
            } else if (html.charAt(n) == '\u00f2') {
                o3++;
            } else if (html.charAt(n) == '\u00f9') {
                u3++;
            } else if (html.charAt(n) == '\u00e3') {
                a4++;
            } else if (html.charAt(n) == '\u00f5') {
                o4++;
            } else if (html.charAt(n) == '\u00e2') {
                a5++;
            } else if (html.charAt(n) == '\u00ea') {
                e5++;
            } else if (html.charAt(n) == '\u00ee') {
                i5++;
            } else if (html.charAt(n) == '\u00f4') {
                o5++;
            } else if (html.charAt(n) == '\u00fb') {
                u5++;
            }else if (html.charAt(n) >= 'a' && html.charAt(n) <= 'z') {
                consoantes++;
            }else if (html.charAt(n) == '<') {
                if (html.charAt(n+1) == 'b' && html.charAt(n + 2) == 'r' && html.charAt(n + 3) == '>') {
                    br++;
                    consoantes--;
                    consoantes--;
                } else if (html.charAt(n+1) == 't' && html.charAt(n + 2) == 'a' && html.charAt(n + 3) == 'b' && html.charAt(n + 4) == 'l' && html.charAt(n + 5) == 'e' && html.charAt(n + 6) == '>') {
                    table++;
                    consoantes--;
                    a--;
                    consoantes--;
                    consoantes--;
                    e--;
                }
            }

        }
        System.out.println("a(" + a + ") e(" + e + ") i(" + i + ") o(" + o + ") u(" + u + ") \u00e1(" + a2 + ") \u00e9(" + e2 + ") \u00ed("
        + i2 + ") \u00f3(" + o2 + ") \u00fa(" + u2 + ") \u00e0(" + a3 + ") \u00e8(" + e3 + ") \u00ec(" + i3 + ") \u00f2(" + o3 + ") \u00f9(" + u3
        + ") \u00e3(" + a4 + ") \u00f5(" + o4 + ") \u00e2(" + a5 + ") \u00ea(" + e5 + ") \u00ee(" + i5 + ") \u00f4(" + o5 + ") \u00fb(" + u5
        + ") consoante(" + consoantes + ") <br>(" + br + ") <table>(" + table + ") " + nome);


    }
	
	public static String getHtml(String endereco){
		URL url;
        InputStream is = null;
        BufferedReader br;
        String resp = "", line;

        try {
            url = new URL(endereco);
            is = url.openStream(); // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                resp += line + "\n";
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        try {
            is.close();
        } catch (IOException ioe) {
            // nothing to see here
        }

        return resp;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        String endereco, html;
		String nome = sc.nextLine().trim();
		while(!nome.equals("FIM")){
			endereco = sc.nextLine().trim();
			html = getHtml(endereco);
			Processamento(html, nome);
			nome = sc.nextLine().trim();
		}
        sc.close();
	}
}