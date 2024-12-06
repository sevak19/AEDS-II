import java.util.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class Main {
    public static void main(String[] args) {
        String path = "/tmp/pokemon.csv";
        ArrayList<Pokemon> pokedex = new ArrayList<Pokemon>();

        try {
            Scanner sc = new Scanner(new File(path));
            sc.nextLine();
        while (sc.hasNextLine()) {
            pokedex.add(new Pokemon(sc.nextLine()));
        }
        sc.close();
        } catch (Exception e) { System.out.println("error");}

        Scanner sc = new Scanner(System.in);

        Alvinegra pkmns = new Alvinegra();

        String input = sc.nextLine();
        while(!input.equals("FIM")) {
            int number = Integer.parseInt(input);
            try {
                pkmns.inserir(pokedex.get(number - 1));
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
            input = sc.nextLine();
        }

        String nome = sc.nextLine();
        while(!nome.equals("FIM")) {
            System.out.println(nome);
            if(pkmns.pesquisar(nome)) {
                System.out.println("SIM");
            } else {
                System.out.println("NAO");
            }
            nome = sc.nextLine();
        }

        sc.close();
    }
}

class NoAN {
   public boolean cor;
   public Pokemon elemento;
   public NoAN esq, dir;
 
   public NoAN() {
     this(null);
   }
 
   public NoAN(Pokemon elemento) {
     this(elemento, false, null, null);
   }
 
   public NoAN(Pokemon elemento, boolean cor) {
     this(elemento, cor, null, null);
   }
 
   public NoAN(Pokemon elemento, boolean cor, NoAN esq, NoAN dir) {
     this.cor = cor;
     this.elemento = elemento;
     this.esq = esq;
     this.dir = dir;
   }
 }

class Alvinegra {
   private NoAN raiz;
 

   public Alvinegra() {
      raiz = null;
   }
 
   public boolean pesquisar(String elemento) {
    System.out.printf("raiz ");  
    return pesquisar(elemento, raiz);
   }
 
   private boolean pesquisar(String elemento, NoAN i) {
      boolean resp;
      if (i == null) {
         resp = false;
      } else if (elemento.compareTo(i.elemento.getName()) == 0) {
         resp = true;
      } else if (elemento.compareTo(i.elemento.getName()) < 0) {
         System.out.printf("esq ");
         resp = pesquisar(elemento, i.esq);
      } else {
         System.out.printf("dir ");
         resp = pesquisar(elemento, i.dir);
      }
      return resp;
   }

   public void inserir(Pokemon elemento) throws Exception {
      // Se a arvore estiver vazia
      if (raiz == null) {
         raiz = new NoAN(elemento);

      // Senao, se a arvore tiver um elemento
      } else if (raiz.esq == null && raiz.dir == null) {
         if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
            raiz.esq = new NoAN(elemento);
         } else {
            raiz.dir = new NoAN(elemento);
         }

      // Senao, se a arvore tiver dois elementos (raiz e dir)
      } else if (raiz.esq == null) {
         if (elemento.getName().compareTo(raiz.elemento.getName()) < 0) {
            raiz.esq = new NoAN(elemento);
            
         } else if (elemento.getName().compareTo(raiz.dir.elemento.getName()) < 0) {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            
         } else {
            raiz.esq = new NoAN(raiz.elemento);
            raiz.elemento = raiz.dir.elemento;
            raiz.dir.elemento = elemento;
    
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, se a arvore tiver dois elementos (raiz e esq)
      } else if (raiz.dir == null) {
         if (elemento.getName().compareTo(raiz.elemento.getName()) > 0) {
            raiz.dir = new NoAN(elemento);
            

         } else if (elemento.getName().compareTo(raiz.esq.elemento.getName()) > 0) {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = elemento;
            

         } else {
            raiz.dir = new NoAN(raiz.elemento);
            raiz.elemento = raiz.esq.elemento;
            raiz.esq.elemento = elemento;
            
         }
         raiz.esq.cor = raiz.dir.cor = false;

      // Senao, a arvore tem tres ou mais elementos
      } else {
         inserir(elemento, null, null, null, raiz);
      }
      raiz.cor = false;
   }
 
    private void balancear(NoAN bisavo, NoAN avo, NoAN pai, NoAN i) {
       // Se o pai tambem e preto, reequilibrar a arvore, rotacionando o avo
       if (pai.cor == true) {
          // 4 tipos de reequilibrios e acoplamento
          if (pai.elemento.getName().compareTo(avo.elemento.getName()) > 0) { // rotacao a esquerda ou direita-esquerda
             if (i.elemento.getName().compareTo(pai.elemento.getName()) > 0) {
                avo = rotacaoEsq(avo);
             } else {
                avo = rotacaoDirEsq(avo);
             }
          } else { // rotacao a direita ou esquerda-direita
             if (i.elemento.getName().compareTo(pai.elemento.getName()) < 0) {
                avo = rotacaoDir(avo);
             } else {
                avo = rotacaoEsqDir(avo);
             }
          }
          if (bisavo == null) {
             raiz = avo;
          } else if (avo.elemento.getName().compareTo(bisavo.elemento.getName()) < 0) {
             bisavo.esq = avo;
          } else {
             bisavo.dir = avo;
          }
          // reestabelecer as cores apos a rotacao
          avo.cor = false;
          avo.esq.cor = avo.dir.cor = true;
       } // if(pai.cor == true)
    }
 
    private void inserir(Pokemon elemento, NoAN bisavo, NoAN avo, NoAN pai, NoAN i) throws Exception {
       if (i == null) {
          if (elemento.getName().compareTo(pai.elemento.getName()) < 0) {
             i = pai.esq = new NoAN(elemento, true);
          } else {
             i = pai.dir = new NoAN(elemento, true);
          }
          if (pai.cor == true) {
             balancear(bisavo, avo, pai, i);
          }
       } else {
          // Achou um 4-no: eh preciso fragmeta-lo e reequilibrar a arvore
          if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
             i.cor = true;
             i.esq.cor = i.dir.cor = false;
             if (i == raiz) {
                i.cor = false;
             } else if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
             }
          }
          if (elemento.getName().compareTo(i.elemento.getName()) < 0) {
             inserir(elemento, avo, pai, i, i.esq);
          } else if (elemento.getName().compareTo(i.elemento.getName()) > 0) {
             inserir(elemento, avo, pai, i, i.dir);
          } else {
             throw new Exception("Erro inserir (elemento repetido)!");
          }
       }
    }
 
    private NoAN rotacaoDir(NoAN no) {
       NoAN noEsq = no.esq;
       NoAN noEsqDir = noEsq.dir;
 
       noEsq.dir = no;
       no.esq = noEsqDir;
 
       return noEsq;
    }
 
    private NoAN rotacaoEsq(NoAN no) {
       NoAN noDir = no.dir;
       NoAN noDirEsq = noDir.esq;
 
       noDir.esq = no;
       no.dir = noDirEsq;
       return noDir;
    }
 
    private NoAN rotacaoDirEsq(NoAN no) {
       no.dir = rotacaoDir(no.dir);
       return rotacaoEsq(no);
    }
 
    private NoAN rotacaoEsqDir(NoAN no) {
       no.esq = rotacaoEsq(no.esq);
       return rotacaoDir(no);
    }
 }
 
class Pokemon {
    private int id;
    private int generation;
    private String name;
    private String description;
    private ArrayList<String> types;
    private ArrayList<String> abilities;
    private double weight;
    private double height;
    private int captureRate;
    private boolean isLegendary;
    private LocalDate captureDate;

    public Pokemon () { }

    public Pokemon (int id,
                   int generation,
                   String name,
                   String description,
                   ArrayList<String> types,
                   ArrayList<String> abilities,
                   double weight,
                   double heigth,
                   int captureRate,
                   boolean isLegendary,
                   LocalDate captureDate
                   ) {
        setId(id);
        setGeneration(generation);
        setName(name);
        setDescription(description);
        setTypes(types);
        setAbilities(abilities);
        setWeight(weight);
        setHeight(height);
        setCaptureRate(captureRate);
        setIsLegendary(isLegendary);
        setCaptureDate(captureDate);
    }

    public Pokemon (String linha) {
        String[] item = linha.split(",");

        setId(Integer.parseInt(item[0]));

        setGeneration(Integer.parseInt(item[1]));

        setName(item[2]);

        setDescription(item[3]);

        ArrayList<String> types = new ArrayList<>();
        types.add(item[4]);
        if (!item[5].isEmpty())
            types.add(item[5]);
        setTypes(types);

        int counter = 6;
        boolean IsDouble = false;
        ArrayList<String> abilities = new ArrayList<>();
        while (!IsDouble) {
            try {
                Double.parseDouble(item[counter]); 
                IsDouble = true;
            } catch (NumberFormatException e) {
                if(!item[counter].isEmpty()){
                    String abilitie = item[counter].replaceAll("[\\[\\]'\"']", "").trim();
                    abilities.add(abilitie);
                    counter++;
                }else{
                    IsDouble = true;
                }
            }
        }
        setAbilities(abilities);

        if(!item[counter].isEmpty())
            setWeight(Double.parseDouble(item[counter]));
        counter++;

        if(!item[counter].isEmpty())
            setHeight(Double.parseDouble(item[counter]));
        counter++;

        setCaptureRate(Integer.parseInt(item[counter]));
        counter++;

        setIsLegendary(item[counter].equals("1"));
        counter++;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        setCaptureDate(LocalDate.parse(item[counter], formatter));
    }

    public Pokemon clone() {
        Pokemon clone = new Pokemon(getId(), getGeneration(), getName(), getDescription(), getTypes(), getAbilities(), getWeight(), getHeight(), getCaptureRate(), getIsLegendary(), getCaptureDate());
        return clone;
    }
 
    public void imprimir() {
        System.out.printf("[#" + id + " -> " + name + ": " + description + " - [");
        int counter = 1;
        for(String type : types) {
            if(counter == 1){
                System.out.printf("'" + type + "'");
                counter++;
            }else{
                System.out.printf(", '" + type + "'");
            }
        }
        System.out.printf("] - [");
        counter = 1;
        for(String abilitie : abilities) {
            if(counter == 1){
                System.out.printf("'" + abilitie + "'");
                counter++;
            }else{
                System.out.printf(", '" + abilitie + "'");
            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("] - " + weight + "kg - " + height + "m - " + captureRate + "%% - " + isLegendary + " - " + generation + " gen] - " + captureDate.format(formatter));
        System.out.println();
    }

    public void setId (int id) {
        this.id = id;
    }
    public int getId () {
        return id;
    }

    public void setGeneration (int generation) {
        this.generation = generation;
    }
    public int getGeneration () {
        return generation;
    }

    public void setName (String name) {
        this.name = name;
    }
    public String getName () {
        return name;
    }

    public void setDescription (String description) {
        this.description = description;
    }
    public String getDescription () {
        return description;
    }

    public void setTypes (ArrayList<String> types) {
        this.types = types;
    }
    public ArrayList<String> getTypes () {
        return types;
    }

    public void setAbilities (ArrayList<String> abilities) {
        this.abilities = abilities;
    }
    public ArrayList<String> getAbilities () {
        return abilities;
    }

    public void setWeight (double weight) {
        this.weight = weight;
    }
    public double getWeight () {
        return weight;
    }

    public void setHeight (double height) {
        this.height = height;
    }
    public double getHeight () {
        return height;
    }

    public void setCaptureRate (int captureRate) {
        this.captureRate = captureRate;
    }
    public int getCaptureRate () {
        return captureRate;
    }

    public void setIsLegendary (boolean isLegendary) {
        this.isLegendary = isLegendary;
    }
    public boolean getIsLegendary () {
        return isLegendary;
    }

    public void setCaptureDate(LocalDate captureDate) {
        this.captureDate = captureDate;
    }
    public LocalDate getCaptureDate() {
        return captureDate;
    }
}