public class MetodosNativos{
    public static void main(String[] args){
        String original = " abcD EfGH Ijkl abc bb ";
        String s01 = original.toLowerCase(); //tranforma todas as letras da string em MINUSCULAS
        String s02 = original.toUpperCase(); //tranforma todas as letras da string em MAIUSCULAS
        String s03 = original.trim(); // tira os espaços nos cantos
        String s04 = original.substring(6); // nova string apartir de uma posição digitado
        String s05 = original.substring(6, 10);  // comeca a nova string a partir da posição digitada e vai ate o limite digitado
        String s06 = original.replace('a', 'x');  // onde tiver 'a' sera trocada por 'x'
        String s07 = original.replace("abc", "xy"); // onde tiver "abc" sera trocado por "xy"
        int i = original.indexOf("bc"); // onde esta localizada a primeira ocorrencia de "bc"
        int j = original.lastIndexOf("bc"); // onde esta localizada a ultima ocorrencia de "bc"

        System.out.println("Original: -" + original + "-");
        System.out.println("toLowerCase: -" + s01 + "- // tranforma todas as letras da string em MINUSCULAS");
        System.out.println("toUpperCase: -" + s02 + "- // tranforma todas as letras da string em MAIUSCULAS");
        System.out.println("trim: -" + s03 + "- // tira os espaços nos cantos");
        System.out.println("substring(6): -" + s04 + "- // nova string apartir de uma posição digitado");
        System.out.println("substring(6, 10): -" + s05 + "- // comeca a nova string a partir da posição digitada e vai ate o limite digitado");
        System.out.println("replace('a', 'x'): -" + s06 + "- // onde tiver 'a' sera trocada por 'x'");
        System.out.println("replace('abc', xy'): -" + s07 + "- // onde tiver 'abc' sera trocado por 'xy'");
        System.out.println("indexOf('bc'): " + i + " // onde esta localizada a primeira ocorrencia de 'bc'");
        System.out.println("LastIndexOf('bc'): " + j + " // onde esta localizada a ultima ocorrencia de 'bc'");
        

        String s = "batata maca limao";
        String[] vetor = s.split(" ");
        String palavra1 = vetor[0];
        String palavra2 = vetor[1];
        String palavra3 = vetor[2];

        System.out.println("split: palavra1 = " + palavra1 + ", palavra2 = " + palavra2 + ", palavra3 = " + palavra3);
    }
}