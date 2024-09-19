import java.util.*;

class Main {
    public static void main (String[] agrs) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        sc.nextLine();

        String entrada = new String();
        List<Integer> presentes = new ArrayList<>();

        for( int i = 0; i < N; i++) {
            entrada = sc.nextLine();
            int V = 0;
            if(entrada.equals("MIN")){
                if(presentes.size() > 0) {
                    int menor = 1000;
                    for ( int j = 0; j < presentes.size(); j++) {
                        if(presentes.get(j) < menor) {
                            menor = presentes.get(j);
                        }
                        for(int k = 1; k < presentes.size(); k++) {
                            if(presentes.get(k) < menor) {
                                menor = presentes.get(k);
                            }
                        }
                    }
                    System.out.println(menor);
                } else {
                    System.out.println("EMPTY");
                }
            }else if(entrada.equals("POP")){
                if(presentes.size() > 0) {
                    presentes.remove(presentes.size() - 1);
                } else {
                    System.out.println("EMPTY");
                }
            }else{
                V = Integer.parseInt(entrada.substring(5));
                presentes.add(V);
            }
        }
        sc.close();
    }
}